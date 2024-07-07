package com.abs.wfs.workman.service.flow.eap.impl;

import com.abs.wfs.workman.dao.query.dao.WorkDAO;
import com.abs.wfs.workman.dao.query.service.WfsQueryService;
import com.abs.wfs.workman.dao.query.service.WorkQueryService;
import com.abs.wfs.workman.dao.query.service.vo.UpdateEventNmByLotCarrIdRequestVo;
import com.abs.wfs.workman.dao.query.service.vo.UpdateWnWorkJobEventRequestVo;
import com.abs.wfs.workman.dao.query.service.vo.WorkInfoQueryRequestVo;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.eap.WfsProcEnded;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsProcEndedIvo;
import com.abs.wfs.workman.spec.out.brs.BrsLotProcEndedIvo;
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
public class WfsProcEndedServiceImpl implements WfsProcEnded {
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


    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsProcEndedIvo wfsProcEndedIvo) throws Exception {


        WfsProcEndedIvo.WfsProcEndedBody body = wfsProcEndedIvo.getBody();
        apFlowProcessVo.setApMsgBody(body);

        log.info(apFlowProcessVo.toString());

        String siteId = body.getSiteId(); String eqpId = body.getEqpId(); String lotId = body.getLotId(); String portId = body.getPortId();


        Optional<List<WorkInfoQueryRequestVo>> workInfoQuery =  this.workDAO.selectActiveWorkInfoQuery(WorkInfoQueryRequestVo.builder()
                                                                            .siteId(siteId)
                                                                            .eqpId(eqpId)
                                                                            .outPortId(portId)
                                                                            .lotId(lotId)
                                                                            .useStatCd(UseStatCd.Usable.name())
                                                                            .build());

       if(!workInfoQuery.isPresent()) {

           log.error("Unknown work is reported. Tell me what todo!. process vo : {}", apFlowProcessVo);
           // TODO un-known work is report.
       }

       int updateRowCount = this.workQueryService.updateWnWorkJobEvent(UpdateWnWorkJobEventRequestVo.builder()
                                                   .apFlowProcessVo(apFlowProcessVo)
                                                   .workId(workInfoQuery.get().get(0).getWorkId())
                                                   .jobSeqId(workInfoQuery.get().get(0).getJobSeqId())
                                                    .build());
       log.info("Work Job  Event has been update. Row count: {}", updateRowCount);


        BrsLotProcEndedIvo.BrsLotProcEndedBody brsLotProcEndedBody = new BrsLotProcEndedIvo.BrsLotProcEndedBody();
        brsLotProcEndedBody.setSiteId(siteId); brsLotProcEndedBody.setEqpId(eqpId); brsLotProcEndedBody.setLotId(lotId); brsLotProcEndedBody.setPortId(portId);
        brsLotProcEndedBody.setCarrId(body.getCarrId());


        this.messageSendService.sendMessageSend(BrsLotProcEndedIvo.system, BrsLotProcEndedIvo.cid,
                                                    this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), brsLotProcEndedBody));



        int updateEventName = this.wfsQueryService.updateEventNmByLotCarrId(UpdateEventNmByLotCarrIdRequestVo.builder().apFlowProcessVo(apFlowProcessVo)
                                                    .carrId(body.getCarrId())
                                                    .lotId(lotId)
                                                    .mdfyUserId(ApSystemCodeConstant.WFS)
                                                    .build());
        log.info("Wip event name has been update. Row count: {}", updateEventName);


        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
    }
}
