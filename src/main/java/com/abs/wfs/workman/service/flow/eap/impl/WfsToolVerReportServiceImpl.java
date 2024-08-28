package com.abs.wfs.workman.service.flow.eap.impl;


import com.abs.wfs.workman.dao.domain.toolVer.model.CnPosToolVer;
import com.abs.wfs.workman.dao.domain.toolVer.repository.ChPosToolVerRepository;
import com.abs.wfs.workman.dao.domain.toolVer.service.CnPosToolVerServiceImpl;
import com.abs.wfs.workman.service.flow.eap.WfsToolVerReport;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.eap.WfsToolVerReportIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WfsToolVerReportServiceImpl implements WfsToolVerReport {

    @Autowired
    CnPosToolVerServiceImpl cnPosToolVerService;

    @Autowired
    ChPosToolVerRepository chPosToolVerRepository;

    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsToolVerReportIvo wfsToolVerReportIvo) throws Exception {

        WfsToolVerReportIvo.Body body = wfsToolVerReportIvo.getBody();
        apFlowProcessVo.setApMsgBody(body);


        CnPosToolVer cnPosToolVer = this.cnPosToolVerService.saveToolVer(wfsToolVerReportIvo);



        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
    }

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }
}
