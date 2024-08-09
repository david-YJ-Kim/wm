package com.abs.wfs.workman.service.flow.oi.impl;

import com.abs.wfs.workman.dao.query.service.WfsCommonQueryService;
import com.abs.wfs.workman.dao.query.service.WfsQueryService;
import com.abs.wfs.workman.dao.query.service.vo.UpdatePortAutoUnloadYnReqVo;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.oi.WfsOiPortUnloadReq;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.eap.WfsUnloadReqIvo;
import com.abs.wfs.workman.spec.in.oia.WfsOiPortUnloadReqIvo;
import com.abs.wfs.workman.spec.out.eap.EapCarrCancelReq;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import com.abs.wfs.workman.util.code.UseStatCd;
import com.abs.wfs.workman.util.code.UseYn;
import com.abs.wfs.workman.util.code.WorkStatCd;
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

    @Autowired
    WfsQueryService wfsQueryService;


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
        log.info("{} update port unload able.", apFlowProcessVo.printLog());

        // WIp Status Standby로 변경
        this.wfsQueryService.updateWorkStatusByCarrId(siteId, apFlowProcessVo.getEventName(), apFlowProcessVo.getTid(),
                                                body.getCarrId(), ApSystemCodeConstant.WFS, WorkStatCd.Standby.name(), false);

        log.info("{} update wip stat to standby.", apFlowProcessVo.printLog());



        // WFS로 UNLOAD REQ 발송
//        WfsUnloadReqIvo.Body unloadReqIvo = new WfsUnloadReqIvo.Body();
//        unloadReqIvo.setSiteId(siteId); unloadReqIvo.setEqpId(eqpId); unloadReqIvo.setPortId(portId);
//        unloadReqIvo.setUserId(body.getUserId()); unloadReqIvo.setCarrId(body.getCarrId());
//        unloadReqIvo.setPortType(WorkManCommonUtil.extractPortTypWithPortId(portId));
//
//        this.messageSendService.sendMessageSend(WfsUnloadReqIvo.system, WfsUnloadReqIvo.cid,
//                this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), unloadReqIvo));


        //EAP로 CARR_CANCEL_REQ 발송
        EapCarrCancelReq.Body carrCancelReqIvo = new EapCarrCancelReq.Body();

        carrCancelReqIvo.setSiteId(siteId);
        carrCancelReqIvo.setEqpId(eqpId);
        carrCancelReqIvo.setPortId(portId);
        carrCancelReqIvo.setPortType(WorkManCommonUtil.extractPortTypWithPortId(portId));
        carrCancelReqIvo.setCarrId(body.getCarrId());
        carrCancelReqIvo.setUserId(body.getUserId());

        this.messageSendService.sendMessageSend(EapCarrCancelReq.system, EapCarrCancelReq.cid,
                this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), carrCancelReqIvo));

        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
    }
}
