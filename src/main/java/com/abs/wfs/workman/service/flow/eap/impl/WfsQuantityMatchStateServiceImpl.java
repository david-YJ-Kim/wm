package com.abs.wfs.workman.service.flow.eap.impl;

import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.eap.WfsQuantityMatchState;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.eap.WfsQuantityMatchStateIvo;
import com.abs.wfs.workman.spec.out.eap.EapBuzzerReqIvo;
import com.abs.wfs.workman.spec.out.eap.EapTerminalMessageReqIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WfsQuantityMatchStateServiceImpl implements WfsQuantityMatchState {

    @Autowired
    MessageSendService messageSendService;


    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;


    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsQuantityMatchStateIvo wfsQuantityMatchStateIvo) throws Exception {


        /**
         *
         * SGMT_STAT_CD는 TrakckIn → Run
         * Track Out 시, Stay > Wait
         *
         * 1. 과적 (Over)
         *  → Future Hold
         * 2. 부족 (Under)
         *  → Future Hold
         */

        WfsQuantityMatchStateIvo.Body body = wfsQuantityMatchStateIvo.getBody();
        apFlowProcessVo.setApMsgBody(body);

        String siteId = body.getSiteId(); String trayId = body.getTrayId(); String lotId = body.getLotId();
        String workId = body.getWorkId(); String eventType = body.getEventType();


        /**
         * Buzzer 메시지 준비
         */
        EapBuzzerReqIvo.Body buzzerBody = new EapBuzzerReqIvo.Body();
        buzzerBody.setSiteId(siteId); buzzerBody.setEqpId(body.getEqpId()); buzzerBody.setUserId(ApSystemCodeConstant.WFS);
        buzzerBody.setSignal("ON");


        /**
         * Terminal 메시지 준비
         */
        EapTerminalMessageReqIvo.Body terminalBody = new EapTerminalMessageReqIvo.Body();



        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
    }

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }
}
/*
{
  "head": {
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "osrc": "",
    "srcEqp": "AP-MR-01-01",
    "src": "EAP",
    "tid": "AP-MR-01-01_00_20240604202856255",
    "cid": "WFS_QUANTITY_MATCH_STATE"
  },
  "body": {
    "carrId": "CAA0042",
    "siteId": "SVM",
    "lotId": "A24600060",
    "eventType": "Under",
    "eqpId": "AP-MR-01-01",
    "userId": "EAP",
    "workId": "WORK_2023032200005"
  }
}
 */