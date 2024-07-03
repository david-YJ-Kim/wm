package com.abs.wfs.workman.service.flow.eap.impl;

import com.abs.wfs.workman.dao.domain.efemAlarm.model.CnEfemAlarm;
import com.abs.wfs.workman.dao.domain.efemAlarm.service.CnEfemAlarmServiceImpl;
import com.abs.wfs.workman.service.flow.eap.WfsAlarmReport;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsAlarmReportIvo;
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
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, String tid) {

        ApFlowProcessVo apFlowProcessVo = ApFlowProcessVo.builder()
                .eventName(cid)
                .trackingKey(trackingKey)
                .scenarioType(scenarioType)
                .executeStartTime(System.currentTimeMillis())
                .tid(tid)
                .build();

        log.info("Ready to process flow. ProcessVo: {}", apFlowProcessVo);
        return apFlowProcessVo;
    }


    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsAlarmReportIvo wfsAlarmReportIvo) throws Exception {


        try {

            CnEfemAlarm result = this.cnEfemAlarmService.saveEfemAlarmDto(wfsAlarmReportIvo.getBody());


        }catch (Exception e){
            apFlowProcessVo.setSuccessYn(SuccessYn.N);
        }
        apFlowProcessVo.setExecuteEndTime(System.currentTimeMillis());

        log.info("Complete to process flow. {}", apFlowProcessVo);
        return apFlowProcessVo;
    }


}
