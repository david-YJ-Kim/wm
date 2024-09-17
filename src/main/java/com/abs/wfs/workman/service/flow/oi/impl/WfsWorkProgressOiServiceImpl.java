package com.abs.wfs.workman.service.flow.oi.impl;

import com.abs.wfs.workman.dao.domain.posEqpGrpRel.model.CnPosEqpGrpRel;
import com.abs.wfs.workman.dao.domain.posEqpGrpRel.service.CnPosEqpGrpRelServiceImpl;
import com.abs.wfs.workman.dao.domain.posManlResv.model.CnPosManlResv;
import com.abs.wfs.workman.dao.domain.posManlResv.service.CnPosManlResvServiceImpl;
import com.abs.wfs.workman.dao.domain.ppsEqpSchd.model.CnPpsEqpSchd;
import com.abs.wfs.workman.dao.domain.ppsEqpSchd.service.CnPpsEqpSchdServiceImpl;
import com.abs.wfs.workman.dao.query.dao.WipStatDAO;
import com.abs.wfs.workman.dao.query.dao.WorkDAO;
import com.abs.wfs.workman.dao.query.eqp.service.EqpServiceImpl;
import com.abs.wfs.workman.dao.query.model.QueryEqpVO;
import com.abs.wfs.workman.dao.query.model.TnPosEqp;
import com.abs.wfs.workman.dao.query.model.WnWipStat;
import com.abs.wfs.workman.dao.query.service.WfsCommonQueryService;
import com.abs.wfs.workman.dao.query.service.WfsQueryService;
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
    WipStatDAO wipStatDAO;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;

    @Autowired
    MessageSendService messageSendService;

    @Autowired
    WfsQueryService wfsQueryService;

    @Autowired
    WfsCommonQueryService commonQueryService;

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
            Optional<CnPosEqpGrpRel> autoTrackInExclusion = cnPosEqpGrpRelService.findBySiteIdAndUseStatCdAndEqpGrpIdAndEqpId(body.getSiteId(), UseStatCd.Usable, "AutoTrackInExclusion", body.getEqpId());

            if(autoTrackInExclusion.isPresent()) {
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
                                                break;
                                            case WorkManScenarioList.INOUT_INLINE_SINGLE:
                                                log.info(WorkManScenarioList.INOUT_INLINE_SINGLE);
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                    sendToolCondReq = true;
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
            //TOOL_COND_REQ Send
            log.info("TOOL_COND_REQ Send(eqpId : {})", body.getEqpId());
            EapToolCondReqIvo.Body toolCondReqIvoBody = new EapToolCondReqIvo.Body();
            toolCondReqIvoBody.setSiteId(body.getSiteId());
            toolCondReqIvoBody.setEqpId(body.getEqpId());
            toolCondReqIvoBody.setInPortId(w.getResvPortId());
            toolCondReqIvoBody.setOutPortId(w.getResvOutPortId());
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
