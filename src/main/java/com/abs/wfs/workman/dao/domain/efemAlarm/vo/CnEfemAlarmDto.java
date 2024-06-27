package com.abs.wfs.workman.dao.domain.efemAlarm.vo;

import com.abs.wfs.workman.dao.domain.efemAlarm.model.CnEfemAlarm;
import com.abs.wfs.workman.util.code.AlarmEqpType;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class CnEfemAlarmDto {

    private String siteId;
    private String alarmState;
    private String alarmId;
    private String eqpId;
    private AlarmEqpType eqpType;
    private String alarmText;
    private String alarmCd;
    private UseStatCd useStatCd;
    private String crtUserId;
    private Timestamp crtDt;
    private String mdfyUserId;
    private Timestamp mdfyDt; // NOT NULL ENABLE
    private Timestamp fnlEvntDt;
    private String tid;


    @Builder
    public CnEfemAlarmDto(String siteId, String alarmState, String alarmId, String eqpId, AlarmEqpType eqpType,String alarmText, String alarmCd, UseStatCd useStatCd, String crtUserId, Timestamp crtDt, String mdfyUserId, Timestamp mdfyDt, Timestamp fnlEvntDt, String tid) {
        this.siteId = siteId;
        this.alarmState = alarmState;
        this.alarmId = alarmId;
        this.eqpType = eqpType;
        this.eqpId = eqpId;
        this.alarmText = alarmText;
        this.alarmCd = alarmCd;
        this.useStatCd = useStatCd;
        this.crtUserId = crtUserId;
        this.crtDt = crtDt;
        this.mdfyUserId = mdfyUserId;
        this.mdfyDt = mdfyDt;
        this.fnlEvntDt = fnlEvntDt;
        this.tid = tid;
    }


    public CnEfemAlarm toEntity(){
        return CnEfemAlarm.builder()
                .siteId(siteId)
                .alarmState(alarmState)
                .alarmId(alarmId)
                .eqpId(eqpId)
                .eqpType(eqpType)
                .alarmText(alarmText)
                .alarmCd(alarmCd)
                .useStatCd(useStatCd)
                .crtUserId(crtUserId)
                .crtDt(crtDt)
                .mdfyUserId(mdfyUserId)
                .mdfyDt(mdfyDt)
                .fnlEvntDt(fnlEvntDt)
                .build();

    }



}
