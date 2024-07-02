package com.abs.wfs.workman.util.vo;


import com.abs.wfs.workman.util.code.WorkStatCd;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScenarioExceptionVo {

    String messageKey;
    String scenarioType;
    String eventName;
    String siteId;
    WorkStatCd workStateCode;
    String workId;
    String lotId;
    String carrId;
    String eqpId;
    String portId;
    String errorCode;
    String errorComment;
}
