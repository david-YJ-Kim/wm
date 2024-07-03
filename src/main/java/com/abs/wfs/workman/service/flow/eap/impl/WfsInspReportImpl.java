package com.abs.wfs.workman.service.flow.eap.impl;


import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.eap.WfsInspReport;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsInspReportIvo;
import com.abs.wfs.workman.spec.out.eap.EapJobAbortReqIvo;
import com.abs.wfs.workman.spec.out.fis.FisFileReportIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WfsInspReportImpl implements WfsInspReport {

    @Autowired
    MessageSendService messageSendService;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;


    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, String tid) {

        ApFlowProcessVo apFlowProcessVo = ApFlowProcessVo.builder()
                .eventName(cid)
                .trackingKey(trackingKey)
                .scenarioType(scenarioType)
                .executeStartTime(System.currentTimeMillis())
                .build();

        log.info("Ready to process flow. ProcessVo: {}", apFlowProcessVo);
        return apFlowProcessVo;
    }

    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsInspReportIvo wfsInspReportIvo) throws Exception {

        WfsInspReportIvo.WfsInspReportBody body = wfsInspReportIvo.getBody();

        FisFileReportIvo.FisFileReportBody fisFileReportBody = FisFileReportIvo.FisFileReportBody.builder()
                .prodMtrlId(body.getProdMtrlId())
                .mtrlFace(body.getMtrlFace())
                .fileName(body.getFileName())
                .fileType(body.getFileType())
                .filePath(body.getFilePath())
                .build();
        fisFileReportBody.setSiteId(body.getSiteId());
        fisFileReportBody.setLotId(body.getLotId());

        this.messageSendService.sendMessageSend(WfsInspReportIvo.system, WfsInspReportIvo.cid,
                this.apPayloadGenerateService.generateBody(wfsInspReportIvo.getHead().getTid(), fisFileReportBody));

        WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);

        return apFlowProcessVo;
    }
}
