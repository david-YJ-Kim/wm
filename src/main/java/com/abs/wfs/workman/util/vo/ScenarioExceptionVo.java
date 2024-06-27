package com.abs.wfs.workman.util.vo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScenarioExceptionVo {

    String messageKey;
    String scenarioType;
    String eventName;
    String siteId;
    String workStateCode;
    String workId;
    String lotId;
    String carrId;
    String eqpId;
    String portId;
    String errorCode;
    String errorComment;
}
