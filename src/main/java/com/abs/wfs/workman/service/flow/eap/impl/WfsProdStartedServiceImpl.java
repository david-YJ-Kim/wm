package com.abs.wfs.workman.service.flow.eap.impl;


import com.abs.wfs.workman.dao.domain.workJobSlotInfo.model.WnWorkJobSlotInfo;
import com.abs.wfs.workman.dao.domain.workJobSlotInfo.service.WnWorkJobSlotInfoServiceImpl;
import com.abs.wfs.workman.dao.query.dao.CommonDAO;
import com.abs.wfs.workman.dao.query.dao.WorkDAO;
import com.abs.wfs.workman.dao.query.model.QueryEqpVO;
import com.abs.wfs.workman.dao.query.model.QueryLotVO;
import com.abs.wfs.workman.dao.query.service.WfsQueryService;
import com.abs.wfs.workman.dao.query.service.WorkQueryService;
import com.abs.wfs.workman.dao.query.service.vo.UpdateWnWorkJobSlotInfoForStartTmReqVo;
import com.abs.wfs.workman.dao.query.service.vo.UpdateWorkStatusByLotIdForWorkStartReqVo;
import com.abs.wfs.workman.dao.query.service.vo.WorkInfoQueryRequestVo;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.UtilCommonService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.eap.WfsProdStarted;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.eap.WfsProdStartedIvo;
import com.abs.wfs.workman.spec.out.brs.BrsLotProdStartedIvo;
import com.abs.wfs.workman.spec.out.eap.EapToolCondReqIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import com.abs.wfs.workman.util.code.UseStatCd;
import com.abs.wfs.workman.util.code.WorkStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WfsProdStartedServiceImpl implements WfsProdStarted {

    @Autowired
    WorkDAO workDAO;

    @Autowired
    MessageSendService messageSendService;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;

    @Autowired
    CommonDAO commonDAO;


    @Autowired
    WnWorkJobSlotInfoServiceImpl wnWorkJobSlotInfoService;

    @Autowired
    WorkQueryService workQueryService;

    @Autowired
    WfsQueryService wfsQueryService;

    @Autowired
    UtilCommonService utilCommonService;


    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsProdStartedIvo wfsProdStartedIvo) throws Exception {
        WfsProdStartedIvo.Body body = wfsProdStartedIvo.getBody();
        apFlowProcessVo.setApMsgBody(body);

        log.info(apFlowProcessVo.toString());

        String siteId = body.getSiteId(); String eqpId = body.getEqpId(); String lotId = body.getLotId(); String portId = body.getPortId();


        Optional<List<WorkInfoQueryRequestVo>> workInfoQuery =  this.workDAO.selectActiveWorkInfoQuery(WorkInfoQueryRequestVo.builder()
                .siteId(siteId)
                .eqpId(eqpId)
                .lotId(lotId)
                .useStatCd(UseStatCd.Usable.name())
                .build());

        if(!workInfoQuery.isPresent()) {

            log.error("Unknown work is reported. Tell me what todo!. process vo : {}", apFlowProcessVo);
            // TODO un-known work is report.
        }

        String workId = workInfoQuery.get().get(0).getWorkId();
        String rsnCd = workInfoQuery.get().get(0).getRsnCd();

        QueryLotVO queryLotVO = this.commonDAO.getQueryLot(siteId, lotId);
        QueryEqpVO queryEqpVO = this.commonDAO.getQueryEqp(siteId,eqpId);

        // LOT Sgmnt 가 Run이 아니면.. Hold...??


        Optional<WnWorkJobSlotInfo> selectSlotJobSeq = this.wnWorkJobSlotInfoService.findPanelWithReportInfo(siteId, workId, body.getProdMtrlId(), body.getSlotNo(), body.getMtrlFace());
        if(selectSlotJobSeq.isPresent()){
            
        this.workQueryService.updateWnWorkJobSlotInfoForStartTm(UpdateWnWorkJobSlotInfoForStartTmReqVo.builder().vo(apFlowProcessVo)
                        .workId(workId)
                        .jobSeqId(selectSlotJobSeq.get().getJobSeqId())
                        .prodMtrlId(body.getProdMtrlId())
                        .slotNo(body.getSlotNo())
                .build());
        }else {
            log.error("Work is not registered."); // TODO 메뉴얼 Work 대응
        }

//        this.wfsQueryService.updateWipStatEventNmByCarrId(siteId, apFlowProcessVo.getEventName(), apFlowProcessVo.getTid(), body.getCarrId(), ApSystemCodeConstant.WFS);


        BrsLotProdStartedIvo.Body payload = new BrsLotProdStartedIvo.Body();
        payload.setSlotNo(siteId); payload.setEqpId(eqpId); payload.setSubEqpId(body.getSubEqpId()); payload.setPpId(body.getPpId());
        payload.setInPortId(portId); payload.setInCarrierId(body.getCarrId()); payload.setLotId(lotId); payload.setSlotNo(body.getSlotNo());
        payload.setProdMtrlId(body.getProdMtrlId());payload.setMtrlFaceCd(WorkManCommonUtil.convertEqpWorkFaceIntoMesWorkFace(body.getMtrlFace()));

        this.messageSendService.sendMessageSend(BrsLotProdStartedIvo.system, BrsLotProdStartedIvo.cid,
                this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), payload));


        this.wfsQueryService.updateWorkStatusByLotIdForWorkStart(UpdateWorkStatusByLotIdForWorkStartReqVo.builder().apFlowProcessVo(apFlowProcessVo)
                                                                .workStatCd(WorkStatCd.Process.name())
                                                                .build());

        boolean JudgeContinuousInput = this.utilCommonService.JudgeContinuousInput(apFlowProcessVo, body.getSlotNo(), body.getProdMtrlId(), body.getProdMtrlId());

        if(JudgeContinuousInput) {

            EapToolCondReqIvo.Body toolCondPayload = new EapToolCondReqIvo.Body();
            toolCondPayload.setSiteId(siteId); toolCondPayload.setEqpId(eqpId);
            toolCondPayload.setInPortId(portId); toolCondPayload.setOutPortId(portId);


            this.messageSendService.sendMessageSend(EapToolCondReqIvo.system, EapToolCondReqIvo.cid,
                             this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), toolCondPayload));
        }

        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
    }


    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return  WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }
}
