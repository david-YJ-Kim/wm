package com.abs.wfs.workman.spec.common;


import com.abs.wfs.workman.spec.in.MsgReasonVo;
import com.abs.wfs.workman.util.code.SuccessYn;
import com.abs.wfs.workman.util.code.WorkStatCd;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Ap, 진행하면서 필요한 데이터를 저장 수집하는 데이터 구조체
 */
@Builder
@Data
public class ApFlowProcessVo {

    private String trackingKey; // 하나의 메시지를 처리하는 데 관통하는 키
    private String tid; // 시스템간 Transaction key
    private String eventName; // 발행된 이벤트 이름

    private String lang; // 요청한 언어


    private ApMsgBody apMsgBody;

    private WorkStatCd workStatCd;
    
    private String workId; // BW Scenario Exception 의 work Id를 위해서 존재

    private String scenarioType;
    private ApDefaultQueryVo apDefaultQueryVo; // 기본 쿼리 수행 결과 저장 구조체

    private long executeStartTime; // 성능 측정을 위해 이벤트 처리 시작 시간
    private long executeEndTime; // 성능 측정을 위해 이벤트 처리 종료 시간

    private long eventElapsedTimeMs;

    private String inPayload; // 전체 메시지 객체

    private SuccessYn successYn = SuccessYn.Y;


    private HashMap<String, String> additionData;

    private ArrayList<ApFlowProcessVo> subFlowCalledProcess; // Process 진행 중 Sub Process를 호출 하는 경우 저장. EQP STATE REPORT 하위 EFEM State Report


    /**
     * Sub Flow Process 를 저장함
     * @param vo
     * @return
     */
    public ArrayList<ApFlowProcessVo> addSubFlowProcess(ApFlowProcessVo vo){
        if(subFlowCalledProcess == null){
            subFlowCalledProcess = new ArrayList<>();
        }
        subFlowCalledProcess.add(vo);
        return subFlowCalledProcess;
    }




    public long setExecuteEndTime(long executeEndTime){
        this.executeEndTime = executeEndTime;
        this.eventElapsedTimeMs = executeEndTime - executeStartTime;
        return executeEndTime;
    }

    public String printLog(){
        return this.trackingKey;
    }
}