package com.abs.wfs.workman.service.flow.oi.impl;

import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.oi.WfsRechuckOi;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.oia.WfsDspInfoCreateOiIvo;
import com.abs.wfs.workman.spec.in.oia.WfsRechuckOiIvo;
import com.abs.wfs.workman.spec.out.eap.EapRechuckReqIvo;
import com.abs.wfs.workman.spec.out.eap.EapToolCondReqIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WfsRechuckOiServiceImpl implements WfsRechuckOi {

    @Autowired
    MessageSendService messageSendService;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;


    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsRechuckOiIvo wfsRechuckOiIvo) throws Exception {
        String lang = wfsRechuckOiIvo.getHead().getLang();
        log.info(apFlowProcessVo.toString());
        WfsRechuckOiIvo.Body body = wfsRechuckOiIvo.getBody();

        log.info("RECHUCK_REQ Send(eqpId : {})", body.getEqpId());
        EapRechuckReqIvo.Body rechuckReqBody = new EapRechuckReqIvo.Body();
        rechuckReqBody.setSiteId(body.getSiteId());
        rechuckReqBody.setEqpId(body.getEqpId());
        rechuckReqBody.setPortId(body.getPortId());
        rechuckReqBody.setCarrId(body.getCarrId());
        rechuckReqBody.setUserId(ApSystemCodeConstant.WFS);

        messageSendService.sendMessageSend(EapToolCondReqIvo.system, EapRechuckReqIvo.cid, apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), rechuckReqBody));


        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
    }

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }
}
