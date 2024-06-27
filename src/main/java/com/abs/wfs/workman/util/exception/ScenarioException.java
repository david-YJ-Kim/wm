package com.abs.wfs.workman.util.exception;

import com.abs.wfs.workman.util.vo.ScenarioExceptionVo;
import lombok.Data;

@Data
public class ScenarioException  extends RuntimeException{

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

    public ScenarioException( String messageKey,  String scenarioType,String eventName,String siteId,
                              String workStateCode,String workId,String lotId,String carrId,String eqpId,String portId,
                              String errorCode,String errorComment) {
        super();
    }



    public ScenarioException(ScenarioExceptionVo vo) {

        super();
        this.messageKey = vo.getMessageKey();
        this.scenarioType = vo.getScenarioType();
        this.eventName = vo.getEventName();
        this.siteId = vo.getSiteId();
        this.workStateCode = vo.getWorkStateCode();
        this.workId = vo.getWorkId();
        this.lotId = vo.getLotId();
        this.carrId = vo.getCarrId();
        this.eqpId = vo.getEqpId();
        this.portId = vo.getPortId();
        this.errorCode = vo.getErrorCode();
        this.errorComment = vo.getErrorComment();
    }
}
