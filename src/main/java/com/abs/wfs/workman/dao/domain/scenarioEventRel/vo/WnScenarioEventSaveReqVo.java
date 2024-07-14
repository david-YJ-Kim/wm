package com.abs.wfs.workman.dao.domain.scenarioEventRel.vo;


import lombok.Data;

@Data
public class WnScenarioEventSaveReqVo {

    private String scenarioType;
    private Long flowSeq;
    private String sourceSystem;
    private String targetSystem;
    private String eventName;
    private String eventType; // 이벤트 타입 (플로우성, 보고성)
}
