package com.abs.wfs.workman.service.flow.eap.impl;

import com.abs.wfs.workman.dao.query.dao.WorkDAO;
import com.abs.wfs.workman.dao.query.service.WfsQueryService;
import com.abs.wfs.workman.dao.query.service.WorkQueryService;
import com.abs.wfs.workman.dao.query.service.vo.UpdateEventNmByLotCarrIdRequestVo;
import com.abs.wfs.workman.dao.query.service.vo.UpdateWnWorkJobEventRequestVo;
import com.abs.wfs.workman.dao.query.service.vo.WorkInfoQueryRequestVo;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.eap.WfsProcStarted;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.eap.WfsProcStartedIvo;
import com.abs.wfs.workman.spec.out.brs.BrsLotProcStartedIvo;
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
public class WfsProcStartedServiceImpl implements WfsProcStarted {


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
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsProcStartedIvo wfsProcStartedIvo) throws Exception {

        WfsProcStartedIvo.WfsProcStartedBody body = new WfsProcStartedIvo.WfsProcStartedBody();
        apFlowProcessVo.setApMsgBody(body);

        log.info(apFlowProcessVo.toString());

        String siteId = body.getSiteId(); String eqpId = body.getEqpId(); String lotId = body.getLotId(); String portId = body.getPortId();


        Optional<List<WorkInfoQueryRequestVo>> workInfoQuery =  this.workDAO.selectActiveWorkInfoQuery(WorkInfoQueryRequestVo.builder()
                                                                                                            .siteId(siteId)
                                                                                                            .eqpId(eqpId)
                                                                                                            .inPortId(portId)
                                                                                                            .lotId(lotId)
                                                                                                            .useStatCd(UseStatCd.Usable.name())
                                                                                                            .build());

        if(!workInfoQuery.isPresent()) {

            log.error("Unknown work is reported. Tell me what todo!. process vo : {}", apFlowProcessVo);
            // TODO un-known work is report. Check It is manual work.
        }

        int updateRowCount = this.workQueryService.updateWnWorkJobEvent(UpdateWnWorkJobEventRequestVo.builder()
                                                    .apFlowProcessVo(apFlowProcessVo)
                                                    .workId(workInfoQuery.get().get(0).getWorkId())
                                                    .jobSeqId(workInfoQuery.get().get(0).getJobSeqId())
                                                    .build());
        log.info("Work Job  Event has been update. Row count: {}", updateRowCount);


        BrsLotProcStartedIvo.BrsLotProcStartedBody ivo = new BrsLotProcStartedIvo.BrsLotProcStartedBody();
        ivo.setSiteId(siteId); ivo.setCarrId(body.getCarrId());ivo.setEqpId(eqpId);ivo.setPortId(portId);
        ivo.setLotId(lotId);

        this.messageSendService.sendMessageSend(BrsLotProcStartedIvo.system, BrsLotProcStartedIvo.cid,
                                                    this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), ivo));



        int updateEventName = this.wfsQueryService.updateEventNmByLotCarrId(UpdateEventNmByLotCarrIdRequestVo.builder().apFlowProcessVo(apFlowProcessVo)
                                                                        .carrId(body.getCarrId())
                                                                        .lotId(lotId)
                                                                        .mdfyUserId(ApSystemCodeConstant.WFS)
                                                                        .build());
        log.info("Wip event name has been update. Row count: {}", updateEventName);


        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
    }

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return  WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }
}
