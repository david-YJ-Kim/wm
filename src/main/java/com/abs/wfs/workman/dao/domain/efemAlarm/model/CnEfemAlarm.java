package com.abs.wfs.workman.dao.domain.efemAlarm.model;


import com.abs.wfs.workman.util.code.AlarmEqpType;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CN_EFEM_ALARM")
public class CnEfemAlarm {

    @Id
    @GenericGenerator(name = "CN_EFEM_ALARM_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "CN_EFEM_ALARM_SEQ_GENERATOR")
    @Column(name = "OBJ_ID")
    private String objId;
    private String siteId;
    private String alarmState;
    private String alarmId;
    private String eqpId;


    @Enumerated(EnumType.STRING)
    private AlarmEqpType eqpType;

    private String alarmText;
    private String alarmCd;

    @Enumerated(EnumType.STRING)
    private UseStatCd useStatCd;

    private String crtUserId;
    private Timestamp crtDt;
    private String mdfyUserId;
    private Timestamp mdfyDt; // NOT NULL ENABLE
    private Timestamp fnlEvntDt;
    private String tid;

    @Builder
    public CnEfemAlarm(String objId, String siteId, String alarmState, String alarmId, String eqpId, AlarmEqpType eqpType, String alarmText, String alarmCd, UseStatCd useStatCd, String crtUserId, Timestamp crtDt, String mdfyUserId, Timestamp mdfyDt, Timestamp fnlEvntDt, String tid) {
        this.objId = objId;
        this.siteId = siteId;
        this.alarmState = alarmState;
        this.alarmId = alarmId;
        this.eqpId = eqpId;
        this.eqpType = eqpType;
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
}
