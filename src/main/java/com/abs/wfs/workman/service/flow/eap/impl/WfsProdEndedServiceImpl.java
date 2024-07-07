package com.abs.wfs.workman.service.flow.eap.impl;

import com.abs.wfs.workman.dao.domain.workJobSlotInfo.model.WnWorkJobSlotInfo;
import com.abs.wfs.workman.dao.domain.workJobSlotInfo.service.WnWorkJobSlotInfoServiceImpl;
import com.abs.wfs.workman.dao.query.dao.WorkDAO;
import com.abs.wfs.workman.dao.query.service.WfsQueryService;
import com.abs.wfs.workman.dao.query.service.WorkQueryService;
import com.abs.wfs.workman.dao.query.service.vo.UpdateEventNmByLotCarrIdRequestVo;
import com.abs.wfs.workman.dao.query.service.vo.UpdateWnWorkJobEventRequestVo;
import com.abs.wfs.workman.dao.query.service.vo.UpdateWnWorkJobSlotInfoForEndTmReqVo;
import com.abs.wfs.workman.dao.query.service.vo.WorkInfoQueryRequestVo;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.eap.WfsProdEnded;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsProdEndedIvo;
import com.abs.wfs.workman.spec.out.brs.BrsLotProcEndedIvo;
import com.abs.wfs.workman.spec.out.brs.BrsLotProdEndedIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class WfsProdEndedServiceImpl implements WfsProdEnded {
    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, String tid) {

        return ApFlowProcessVo.builder()
                .eventName(cid)
                .trackingKey(trackingKey)
                .scenarioType(scenarioType)
                .executeStartTime(System.currentTimeMillis())
                .tid(tid)
                .build();
    }

    @Autowired
    WorkDAO workDAO;

    @Autowired
    WorkQueryService workQueryService;

    @Autowired
    WfsQueryService wfsQueryService;

    @Autowired
    MessageSendService messageSendService;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;

    @Autowired
    WnWorkJobSlotInfoServiceImpl wnWorkJobSlotInfoService;


    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsProdEndedIvo wfsProdEndedIvo) throws Exception {


        WfsProdEndedIvo.WfsProdEndedBody body = wfsProdEndedIvo.getBody();
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
       Optional<WnWorkJobSlotInfo> selectSlotJobInfo = this.wnWorkJobSlotInfoService.findPanelWithReportInfo(siteId, workId, body.getProdMtrlId(), body.getSlotNo(), body.getMtrlFace());



       if(selectSlotJobInfo.isPresent()){

           this.workQueryService.updateWnWorkJobSlotInfoForEndTm(UpdateWnWorkJobSlotInfoForEndTmReqVo.builder().vo(apFlowProcessVo)
                   .workId(workId).jobSeqId(selectSlotJobInfo.get().getJobSeqId())
                   .prodMtrlId(body.getProdMtrlId()).slotNo(body.getSlotNo()).build());


       }else {
           log.error("Un-known panel work is reported. Seems it's manual work.");
           // TODO 메뉴얼 워크 대응
       }

        BrsLotProdEndedIvo.Body payload = new BrsLotProdEndedIvo.Body();
        payload.setSlotNo(siteId); payload.setEqpId(eqpId); payload.setSubEqpId(body.getSubEqpId()); payload.setPpId(body.getPpId());
        payload.setInPortId(portId); payload.setInCarrierId(body.getCarrId()); payload.setLotId(lotId); payload.setSlotNo(body.getSlotNo());
        payload.setProdMtrlId(body.getProdMtrlId());payload.setMtrlFaceCd(WorkManCommonUtil.convertEqpWorkFaceIntoMesWorkFace(body.getMtrlFace()));


        this.messageSendService.sendMessageSend(BrsLotProdEndedIvo.system, BrsLotProdEndedIvo.cid,
                                                    this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), payload));



        int updateEventName = this.wfsQueryService.updateEventNmByLotCarrId(UpdateEventNmByLotCarrIdRequestVo.builder().apFlowProcessVo(apFlowProcessVo)
                                                    .carrId(body.getCarrId())
                                                    .lotId(lotId)
                                                    .mdfyUserId(ApSystemCodeConstant.WFS)
                                                    .build());
        log.info("Wip event name has been update. Row count: {}", updateEventName);


        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
    }
}
