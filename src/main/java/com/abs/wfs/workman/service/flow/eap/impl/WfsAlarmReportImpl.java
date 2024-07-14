package com.abs.wfs.workman.service.flow.eap.impl;

import com.abs.wfs.workman.dao.domain.efemAlarm.model.CnEfemAlarm;
import com.abs.wfs.workman.dao.domain.efemAlarm.service.CnEfemAlarmServiceImpl;
import com.abs.wfs.workman.service.flow.eap.WfsAlarmReport;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.eap.WfsAlarmReportIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.SuccessYn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WfsAlarmReportImpl implements WfsAlarmReport {

    @Autowired
    CnEfemAlarmServiceImpl cnEfemAlarmService;

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {

        return  WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);

    }


    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsAlarmReportIvo wfsAlarmReportIvo) throws Exception {

        apFlowProcessVo.setApMsgBody(wfsAlarmReportIvo.getBody());

        try {

            CnEfemAlarm result = this.cnEfemAlarmService.saveEfemAlarmDto(wfsAlarmReportIvo.getBody());
            log.info("Complete alarm data. Result: {}", result);


        }catch (Exception e){
            WorkManCommonUtil.setFailFlowProcessVo(apFlowProcessVo);
        }
        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
    }


}
