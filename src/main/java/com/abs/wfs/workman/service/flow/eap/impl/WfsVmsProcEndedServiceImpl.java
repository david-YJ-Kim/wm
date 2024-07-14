
package com.abs.wfs.workman.service.flow.eap.impl;

import com.abs.wfs.workman.dao.query.service.WfsQueryService;
import com.abs.wfs.workman.dao.query.service.vo.UpdateEventNmByLotCarrIdRequestVo;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.eap.WfsVmsProcEnded;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.eqp.WfsVmsProcEndedIvo;
import com.abs.wfs.workman.spec.out.brs.BrsLotProcEndedIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class WfsVmsProcEndedServiceImpl implements WfsVmsProcEnded {


    @Autowired
    WfsQueryService wfsQueryService;

    @Autowired
    MessageSendService messageSendService;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;


    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsVmsProcEndedIvo wfsVmsProcEndedIvo) throws Exception {


        WfsVmsProcEndedIvo.WfsVmsProcEndedBody body = wfsVmsProcEndedIvo.getBody();
        apFlowProcessVo.setApMsgBody(body);

        log.info(apFlowProcessVo.toString());

        String siteId = body.getSiteId(); String eqpId = body.getEqpId(); String lotId = body.getLotId(); String portId = body.getPortId();




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

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return  WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }
}
