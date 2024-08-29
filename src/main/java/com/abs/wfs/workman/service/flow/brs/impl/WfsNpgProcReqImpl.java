package com.abs.wfs.workman.service.flow.brs.impl;

import com.abs.wfs.workman.dao.domain.tnPort.model.TnPosPort;
import com.abs.wfs.workman.dao.domain.tnPort.service.TnPosPortServiceImpl;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.common.work.WorkManageService;
import com.abs.wfs.workman.service.flow.brs.WfsNpgProcReq;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.brs.WfsNpgProcReqIvo;
import com.abs.wfs.workman.spec.out.rtd.RtdDspWorkReqIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WfsNpgProcReqImpl implements WfsNpgProcReq {

    @Autowired
    TnPosPortServiceImpl tnPosPortService;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;

    @Autowired
    MessageSendService messageSendService;

    @Autowired
    WorkManageService workManageService;


    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsNpgProcReqIvo wfsNpgProcReqIvo) throws Exception {

        WfsNpgProcReqIvo.Body body = wfsNpgProcReqIvo.getBody();
        apFlowProcessVo.setApMsgBody(body);
        log.info("{} Set up message body: {}", apFlowProcessVo.printLog(), wfsNpgProcReqIvo.toString());

        Optional<List<TnPosPort>> portList = tnPosPortService.findByAvailPortList(body.getSiteId(), body.getEqpId());

        TnPosPort port;
        if(!portList.isPresent()) {
            throw new Exception("PORT not found");
        } else {
            port = portList.get().get(0);
        }


        RtdDspWorkReqIvo.RtdDspWorkReqBody dspWorkReqVo = new RtdDspWorkReqIvo.RtdDspWorkReqBody();

        dspWorkReqVo.setSiteId(body.getSiteId());
        dspWorkReqVo.setDspType("NPG");
        dspWorkReqVo.setLotId("");
        dspWorkReqVo.setEqpId(body.getEqpId());
        dspWorkReqVo.setPortId(port.getPortId());
        dspWorkReqVo.setCarrId("");
        dspWorkReqVo.setProdDefId("");
        dspWorkReqVo.setProcDefId("");
        dspWorkReqVo.setRsltCm("");
        dspWorkReqVo.setRsnCd("");
        dspWorkReqVo.setEvntCm("");
        dspWorkReqVo.setEvntUserId(body.getUserId());

        this.messageSendService.sendMessageSend(RtdDspWorkReqIvo.system, RtdDspWorkReqIvo.cid,
                this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), dspWorkReqVo));

        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
    }

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }
}
