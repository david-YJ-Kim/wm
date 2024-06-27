package com.abs.wfs.workman.dao.domain.efemAlarm.service;


import com.abs.wfs.workman.dao.domain.efemAlarm.model.CnEfemAlarm;
import com.abs.wfs.workman.dao.domain.efemAlarm.repository.CnEfemAlarmRepository;
import com.abs.wfs.workman.dao.domain.efemAlarm.vo.CnEfemAlarmDto;
import com.abs.wfs.workman.spec.in.eap.WfsAlarmReportIvo;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@Slf4j
public class CnEfemAlarmServiceImpl {

    @Autowired
    CnEfemAlarmRepository cnEfemAlarmRepository;


    public CnEfemAlarm saveEfemAlarmDto (WfsAlarmReportIvo.WfsAlarmReportBody ivo) {


        CnEfemAlarm entity = null;
        log.info(ivo.toString());
        try{
            CnEfemAlarmDto dto = CnEfemAlarmDto.builder()
                    .siteId(ivo.getSiteId())
                    .eqpId(ivo.getEqpId())
                    .eqpType(ivo.getEqpType())
                    .alarmId(ivo.getAlarmId())
                    .alarmState(ivo.getAlarmState())
                    .alarmText(ivo.getAlarmText())
                    .alarmCd(ivo.getAlarmCode())
                    .useStatCd(UseStatCd.Usable)
                    .crtUserId(ApSystemCodeConstant.WFS)
                    .crtDt(Timestamp.from(Instant.now()))
                    .mdfyUserId(ApSystemCodeConstant.WFS)
                    .mdfyDt(Timestamp.from(Instant.now()))
                    .fnlEvntDt(Timestamp.from(Instant.now()))
                    .build();
            CnEfemAlarm result = cnEfemAlarmRepository.save(dto.toEntity());


        }catch (Exception e){
            e.printStackTrace();
            log.error(e.toString());
            throw e;
        }

        return entity;
    }

}
