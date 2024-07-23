package com.abs.wfs.workman.service.flow.oi.impl;

import com.abs.wfs.workman.dao.query.service.WfsCommonQueryService;
import com.abs.wfs.workman.dao.query.service.vo.UpdatePortAutoUnloadYnReqVo;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.oi.WfsOiPortUnloadReq;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.eap.WfsUnloadReqIvo;
import com.abs.wfs.workman.spec.in.oia.WfsOiPortUnloadReqIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.UseStatCd;
import com.abs.wfs.workman.util.code.UseYn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WfsOiPortUnloadReqServiceImpl implements WfsOiPortUnloadReq {
    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return  WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }

    @Autowired
    WfsCommonQueryService wfsCommonQueryService;

    @Autowired
    MessageSendService messageSendService;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;


    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsOiPortUnloadReqIvo wfsOiPortUnloadReqIvo) throws Exception {

        WfsOiPortUnloadReqIvo.Body body = wfsOiPortUnloadReqIvo.getBody();
        apFlowProcessVo.setApMsgBody(body);

        String siteId = body.getSiteId(); String portId = body.getPortId(); String eqpId = body.getEqpId();


        UpdatePortAutoUnloadYnReqVo updateVo = UpdatePortAutoUnloadYnReqVo.builder()

                .siteId(siteId)
                .cid(apFlowProcessVo.getEventName())
                .tid(apFlowProcessVo.getTid())
                .userId(body.getUserId())
                .autoUnloadYn(UseYn.Y.name())
                .eqpId(eqpId)
                .portId(portId)
                .build();
        // TN POS 테이블 N으로 변경
        this.wfsCommonQueryService.updatePortAutoUnloadYn(updateVo);


        // WFS로 UNLOAD REQ 발송
        WfsUnloadReqIvo.Body unloadReqIvo = new WfsUnloadReqIvo.Body();
        unloadReqIvo.setSiteId(siteId); unloadReqIvo.setEqpId(eqpId); unloadReqIvo.setPortId(portId);
        unloadReqIvo.setUserId(body.getUserId()); unloadReqIvo.setCarrId(body.getCarrId());
        unloadReqIvo.setPortType(WorkManCommonUtil.extractPortTypWithPortId(portId));

        this.messageSendService.sendMessageSend(WfsUnloadReqIvo.system, WfsUnloadReqIvo.cid,
                this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), unloadReqIvo));

        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
    }
}
