package com.abs.wfs.workman.service.flow.eap.impl;


import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.eap.WfsMaterialKitdekitReport;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsMaterialKitdekitReportIvo;
import com.abs.wfs.workman.spec.out.brs.BrsEqpDekitIvo;
import com.abs.wfs.workman.spec.out.eap.EapDurableInfoReqIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.UseYn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WfsMaterialKitdekitReportImpl implements WfsMaterialKitdekitReport {


    @Autowired
    MessageSendService messageSendService;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;


    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsMaterialKitdekitReportIvo wfsMaterialKitdekitReportIvo) throws Exception {

        WfsMaterialKitdekitReportIvo.Body body = wfsMaterialKitdekitReportIvo.getBody();
        apFlowProcessVo.setApMsgBody(body);

        String siteId = body.getSiteId(); String eqpId = body.getEqpId(); String specId = body.getSpecId(); String specTyp = body.getSpecType().name();


        boolean isKitYn = body.getAttachFlag().equals(UseYn.Y);
        if(isKitYn){
            // send kit
            log.info("Kit report, send request message to get all material/durable list.");

            EapDurableInfoReqIvo.Body durableInfoReq = new EapDurableInfoReqIvo.Body() ;
            durableInfoReq.setSiteId(siteId); durableInfoReq.setEqpId(eqpId); durableInfoReq.setSpecInfo("ALL");

            this.messageSendService.sendMessageSend(EapDurableInfoReqIvo.system, EapDurableInfoReqIvo.cid,
                    this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), durableInfoReq));

        }else {
            log.info("De-Kit report, send report message to BRS.");

            BrsEqpDekitIvo.Body eqpDeKit = new BrsEqpDekitIvo.Body();
            eqpDeKit.setSiteId(siteId); eqpDeKit.setEqpId(eqpId); eqpDeKit.setMtrlDrblId(specId); eqpDeKit.setMtrlDrblTyp(specTyp);

            this.messageSendService.sendMessageSend(BrsEqpDekitIvo.system, BrsEqpDekitIvo.cid,
                    this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), eqpDeKit));

        }




        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
    }

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, String tid) {
        return ApFlowProcessVo.builder()
                .eventName(cid)
                .trackingKey(trackingKey)
                .scenarioType(scenarioType)
                .executeStartTime(System.currentTimeMillis())
                .build();
    }
}
