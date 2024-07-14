package com.abs.wfs.workman.dao.domain.scenarioEventRel.model;


import com.abs.wfs.workman.util.code.UseStatCd;
import com.abs.wfs.workman.util.code.UseYn;
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
@Entity(name = "WN_SCENARIO_EVENT_REL")
public class WnScenarioEventRel {

    @Id
    @GenericGenerator(name = "WN_SCENARIO_EVENT_REL_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "WN_SCENARIO_EVENT_REL_SEQ_GENERATOR")
    @Column(name = "OBJ_ID")
    private String objId;

    private String scenarioType;
    private Long flowSeq;
    private String sourceSystem;
    private String targetSystem;
    private String eventName;
    private String eventType;

    @Enumerated(EnumType.STRING)
    private UseStatCd useStatCd;

    private String crtUserId;
    private Timestamp crtDt;
    private String mdfyUserId;
    private Timestamp mdfyDt;


    @Enumerated(EnumType.STRING)
    private UseYn convertWmYn;



    @Builder
    public WnScenarioEventRel(String objId, String scenarioType, Long flowSeq, String sourceSystem, String targetSystem, String eventName, String eventType, UseStatCd useStatCd, String crtUserId, Timestamp crtDt, String mdfyUserId, Timestamp mdfyDt, UseYn convertWmYn) {
        this.objId = objId;
        this.scenarioType = scenarioType;
        this.flowSeq = flowSeq;
        this.sourceSystem = sourceSystem;
        this.targetSystem = targetSystem;
        this.eventName = eventName;
        this.eventType = eventType;
        this.useStatCd = useStatCd;
        this.crtUserId = crtUserId;
        this.crtDt = crtDt;
        this.mdfyUserId = mdfyUserId;
        this.mdfyDt = mdfyDt;
        this.convertWmYn = convertWmYn;
    }
}
