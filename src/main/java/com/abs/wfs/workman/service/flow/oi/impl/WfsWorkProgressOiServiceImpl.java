package com.abs.wfs.workman.service.flow.oi.impl;

import com.abs.wfs.workman.dao.domain.posEqpGrpRel.model.CnPosEqpGrpRel;
import com.abs.wfs.workman.dao.domain.posEqpGrpRel.service.CnPosEqpGrpRelServiceImpl;
import com.abs.wfs.workman.dao.domain.posManlResv.model.CnPosManlResv;
import com.abs.wfs.workman.dao.domain.posManlResv.service.CnPosManlResvServiceImpl;
import com.abs.wfs.workman.dao.domain.ppsEqpSchd.model.CnPpsEqpSchd;
import com.abs.wfs.workman.dao.domain.ppsEqpSchd.service.CnPpsEqpSchdServiceImpl;
import com.abs.wfs.workman.dao.query.dao.CommonDAO;
import com.abs.wfs.workman.dao.query.dao.WipStatDAO;
import com.abs.wfs.workman.dao.query.dao.WorkDAO;
import com.abs.wfs.workman.dao.query.eqp.service.EqpServiceImpl;
import com.abs.wfs.workman.dao.query.model.QueryEqpVO;
import com.abs.wfs.workman.dao.query.model.TnPosEqp;
import com.abs.wfs.workman.dao.query.model.WnWipStat;
import com.abs.wfs.workman.dao.query.service.WfsCommonQueryService;
import com.abs.wfs.workman.dao.query.service.WfsQueryService;
import com.abs.wfs.workman.dao.query.service.WorkQueryService;
import com.abs.wfs.workman.dao.query.tool.vo.QueryEqpVo;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.common.work.WorkManageService;
import com.abs.wfs.workman.service.flow.oi.WfsWorkProgressOi;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.oia.WfsWorkProgressOiIvo;
import com.abs.wfs.workman.spec.out.brs.BrsLotDeassignCarr;
import com.abs.wfs.workman.spec.out.eap.EapToolCondReqIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.*;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class WfsWorkProgressOiServiceImpl implements WfsWorkProgressOi {

    @Autowired
    CnPosEqpGrpRelServiceImpl cnPosEqpGrpRelService;

    @Autowired
    CnPosManlResvServiceImpl cnPosManlResvService;

    @Autowired
    CnPpsEqpSchdServiceImpl cnPpsEqpSchdService;

    @Autowired
    EqpServiceImpl eqpService;

    @Autowired
    WorkDAO workDAO;

    @Autowired
    CommonDAO commonDAO;

    @Autowired
    WipStatDAO wipStatDAO;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;

    @Autowired
    MessageSendService messageSendService;

    @Autowired
    WfsQueryService wfsQueryService;

    @Autowired
    WfsCommonQueryService commonQueryService;

    @Autowired
    WorkQueryService workQueryService;

    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsWorkProgressOiIvo wfsWorkProgressOiIvo) throws Exception {
        String lang = wfsWorkProgressOiIvo.getHead().getLang();
        log.info(apFlowProcessVo.toString());
        WfsWorkProgressOiIvo.Body body = wfsWorkProgressOiIvo.getBody();

        QueryEqpVo eqpVo = eqpService.getQueryEqp(body.getSiteId(), body.getEqpId());

        WnWipStat w = null;

        boolean sendToolCondReq = false;

        String userId = body.getUserId();

        // 설비 Ready 현황 Track In Button
        if("WFS".equals(userId)) {
            log.info("WFS Maint Operation");
            sendToolCondReq = true;
        }
        // Lot 예약 현황 - Auto Track In 요청
        else {
            log.info("UserId : {}, Track In Request Operation", userId);
            CnPosEqpGrpRel autoTrackInExclusion = cnPosEqpGrpRelService.findBySiteIdAndUseStatCdAndEqpGrpIdAndEqpId(body.getSiteId(), UseStatCd.Usable, "AutoTrackInExclusion", body.getEqpId());

            if(autoTrackInExclusion != null) {
                log.info("Auto TrackIn Exclusion EQP : {}", body.getEqpId());
            }
            else {
                log.info("CN_POS_MANL_RESV Select");
                Optional<CnPosManlResv> autoTrackInResv = cnPosManlResvService.findBySiteIdAndEqpIdAndLotIdAndProcSgmtIdAndUseStatCd(body.getSiteId(), body.getEqpId(), body.getLotId(), body.getProcSgmtId(), UseStatCd.Usable);

                if(autoTrackInResv.isPresent()) {
                    log.info("CN_POS_MANL_RESV Info Exist");
                    log.info(autoTrackInResv.toString());

                    Optional<CnPpsEqpSchd> autoRelease = cnPpsEqpSchdService.findByAutoRelease(body.getSiteId(), body.getEqpId(), body.getLotId(), body.getProdDefId(), body.getProcDefId(), body.getProcSgmtId(), UseStatCd.Usable);
                    if(autoRelease.isPresent()) {
                        log.info("CN_PPS_EQP_SCHD Info Exist");

                        List<Map<String,String>> work = workDAO.selectWorkExist(body.getSiteId(), body.getEqpId(), body.getLotId());
                        if(work.size() > 0) {
                            log.info("Work Already Exist");
                        }
                        else {
                            List<WnWipStat> wipStatList = wipStatDAO.selectByLotId(body.getSiteId(), body.getLotId());

                            if(wipStatList.size() > 0) {
                                w = wipStatList.get(0);
                                if(w.getCrntEqpId().equals(body.getEqpId())) {
                                    if(w.getResvGrpId() == null) {

                                        if(!WorkStatCd.Ready.name().equals(w.getWorkStatCd())) {
                                            wfsQueryService.updateWorkStatusByCarrId(body.getSiteId(), apFlowProcessVo.getEventName(), apFlowProcessVo.getTid(),
                                                    w.getCarrId(), ApSystemCodeConstant.WFS, WorkStatCd.Ready.name(), false);
                                        }

                                        String dspId = commonQueryService.getID("DSP");
                                        log.info("Generate Dispatching ID : {}", dspId);

                                        //modelTyp
                                        switch(eqpVo.getModelTyp()) {
                                            case WorkManScenarioList.BP_SINGLE:
                                                log.info(WorkManScenarioList.BP_SINGLE);
                                                wfsQueryService.updateWipStatForDispatchigNormal(body.getSiteId(), apFlowProcessVo.getEventName(), apFlowProcessVo.getTid()
                                                        , w.getCarrId(), body.getLotId(), ApSystemCodeConstant.WFS, w.getCrntEqpId(), w.getCrntPortId(), dspId, w.getCrntEqpId(), w.getCrntPortId());
                                                break;
                                            case WorkManScenarioList.BP_BATCH:
                                                log.info(WorkManScenarioList.BP_BATCH);
                                                String batchId = commonQueryService.getID("BATCH");
                                                wfsQueryService.updateWipStatForDispatchigBatch(body.getSiteId(), apFlowProcessVo.getEventName(), apFlowProcessVo.getTid(), w.getCarrId()
                                                        , ApSystemCodeConstant.WFS, batchId,"1", w.getCrntEqpId(), w.getCrntPortId(), dspId);
                                                break;
                                            case WorkManScenarioList.INOUT_SINGLE:
                                                log.info(WorkManScenarioList.INOUT_SINGLE);

                                                Map<String,String> resvOpCarr = commonDAO.selectNoneResvOPCarr(body.getSiteId(), w.getCrntEqpId(), w.getCarrId());

                                                if(resvOpCarr != null){
                                                    //In
                                                    wfsQueryService.updateWipStatForDispatchigNormal(body.getSiteId(), apFlowProcessVo.getEventName(), apFlowProcessVo.getTid()
                                                            , w.getCarrId(), body.getLotId(), ApSystemCodeConstant.WFS, w.getCrntEqpId(), w.getCrntPortId(), dspId, resvOpCarr.get("CARR_ID"), resvOpCarr.get("PORT_ID"));

                                                    //Out
                                                    wfsQueryService.updateWipStatForDispatchigNormal(body.getSiteId(), apFlowProcessVo.getEventName(), apFlowProcessVo.getTid()
                                                            , resvOpCarr.get("CARR_ID"), "-", ApSystemCodeConstant.WFS, w.getCrntEqpId(), resvOpCarr.get("PORT_ID"), dspId, resvOpCarr.get("CARR_ID"), resvOpCarr.get("PORT_ID"));

                                                    //Out Wip Ready
                                                    wfsQueryService.updateWorkStatusByCarrId(body.getSiteId(), apFlowProcessVo.getEventName(), apFlowProcessVo.getTid()
                                                            , resvOpCarr.get("CARR_ID"), ApSystemCodeConstant.WFS, WorkStatCd.Ready.name(), false);
                                                } else {
                                                    log.info("INOUT DSP Resv Info Create >> Out Port : No Empty Carr");
                                                }

                                                break;
                                            case WorkManScenarioList.INOUT_INLINE_SINGLE:
                                                log.info(WorkManScenarioList.INOUT_INLINE_SINGLE);

                                                List<Map<String,String>> inlineEqpList = commonDAO.selectInlineEqpList(body.getSiteId(), eqpVo.getEqpInlineId());
                                                if(inlineEqpList != null) {
                                                    String lastEqpId = inlineEqpList.get(inlineEqpList.size()-1).get("EQP_ID");

                                                    Map<String,String> inlineLastEqpResvOpCarr = commonDAO.selectNoneResvOPCarr(body.getSiteId(), lastEqpId, w.getCarrId());

                                                    if(inlineLastEqpResvOpCarr != null) {

                                                        //In
                                                        wfsQueryService.updateWipStatForDispatchigNormal(body.getSiteId(), apFlowProcessVo.getEventName(), apFlowProcessVo.getTid()
                                                                , w.getCarrId(), body.getLotId(), ApSystemCodeConstant.WFS, w.getCrntEqpId(), w.getCrntPortId(), dspId, inlineLastEqpResvOpCarr.get("CARR_ID"), inlineLastEqpResvOpCarr.get("PORT_ID"));

                                                        //Out
                                                        wfsQueryService.updateWipStatForDispatchigNormal(body.getSiteId(), apFlowProcessVo.getEventName(), apFlowProcessVo.getTid()
                                                                , inlineLastEqpResvOpCarr.get("CARR_ID"), "-", ApSystemCodeConstant.WFS, lastEqpId, inlineLastEqpResvOpCarr.get("PORT_ID"), dspId, inlineLastEqpResvOpCarr.get("CARR_ID"), inlineLastEqpResvOpCarr.get("PORT_ID"));

                                                        //Out Wip Ready
                                                        wfsQueryService.updateWorkStatusByCarrId(body.getSiteId(), apFlowProcessVo.getEventName(), apFlowProcessVo.getTid()
                                                                , inlineLastEqpResvOpCarr.get("CARR_ID"), ApSystemCodeConstant.WFS, WorkStatCd.Ready.name(), false);


                                                        for(int i = 0; i < inlineEqpList.size(); i++) {
                                                            Map<String,String> eqp = inlineEqpList.get(i);
                                                            String portId ="";
                                                            if(i == 0)
                                                                portId =  w.getCrntPortId();
                                                            else if (i + 1 == inlineEqpList.size())
                                                                portId = inlineLastEqpResvOpCarr.get("PORT_ID");
                                                            else
                                                                portId = "";
                                                            workQueryService.createDspWorkInfo(body.getSiteId(), apFlowProcessVo.getEventName(), apFlowProcessVo.getTid(),
                                                                    ApSystemCodeConstant.WFS, dspId, eqp.get("EQP_ID"), portId, body.getLotId(), "");
                                                        }
                                                    }
                                                    else {
                                                        log.info("INOUT Inline DSP Resv Info Create >> Out Port : No Empty Carr");
                                                    }
                                                }
                                                else {
                                                    log.info("EQP : {} has no Eqp Inline ID" + body.getEqpId());
                                                }
                                                break;
                                            default:
                                                log.info("Model Type Not Exist : {}", eqpVo.getModelTyp());
                                                break;
                                        }
                                    }
                                    sendToolCondReq = true;
                                }
                                else {
                                    log.info("Lot Location Mismatch [ Event Eqp : {}, Wip Current Eqp : {}", body.getEqpId(), w.getCrntEqpId());
                                }
                            }
                        }
                    }
                    else {
                        log.info("CN_PPS_EQP_SCHD Info NotExist");
                    }
                }
                else {
                    log.info("CN_POS_MANL_RESV Info Not Exist");
                }
            }

        }




        if(sendToolCondReq) {
            List<WnWipStat> wipStatList = wipStatDAO.selectByLotId(body.getSiteId(), body.getLotId());
            //TOOL_COND_REQ Send
            log.info("TOOL_COND_REQ Send(eqpId : {})", body.getEqpId());
            EapToolCondReqIvo.Body toolCondReqIvoBody = new EapToolCondReqIvo.Body();
            toolCondReqIvoBody.setSiteId(body.getSiteId());
            toolCondReqIvoBody.setEqpId(body.getEqpId());
            toolCondReqIvoBody.setInPortId(wipStatList.get(0).getResvPortId());
            toolCondReqIvoBody.setOutPortId(wipStatList.get(0).getResvOutPortId());
            toolCondReqIvoBody.setUserId(ApSystemCodeConstant.WFS);

            messageSendService.sendMessageSend(EapToolCondReqIvo.system, EapToolCondReqIvo.cid,
                    apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), toolCondReqIvoBody));
        }


        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
    }

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }
}
