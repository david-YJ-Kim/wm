package com.abs.wfs.workman.util.exception;


public enum ApExceptionCode {

    /**
     * INVALID: 부적합하다
     */
    WFS_ERR_TOOL_PORT_INF_INVALID("WFS_ERR_TOOL_INF_INVALID", "설비와 포트 정보가 부적합 합니다."),


    /**
     * UNMATCHED: 원하는 개댓값과 다르다.
     */
    WFS_ERR_TRAN_JOB_STAT_UNMATCHED("WFS_ERR_TRAN_JOB_STAT_UNMATCHED", "해당 CST (%s)로 생성된 반송 잡 (%s) 상태가 기댓값과 다릅니다. 현재 상태 %s"),
    WFS_ERR_CARR_SRC_LOC_UNMATCHED("WFS_ERR_CARR_SRC_LOC_UNMATCHED", "해당 CST (%s)의 요청 받은 위치와 현재 위치가 다릅니다. 요청 위치 (%s),  현재 위치 (%s)"),
    WFS_ERR_PORT_CARR_INF_UNMATCHED("WFS_ERR_PORT_CARR_INF_UNMATCHED", "해당 포트 (%s)에 요청한 CST(%s)가 아닌 다른 CST(%s)가 등록되어 있습니다."),
    WFS_ERR_PORT_TYP_UNMATCHED("WFS_ERR_PORT_TYP_UNMATCHED", "현재 요청된 포트 타입(%s)이 존재하지 않습니다."),  // 기존 UnmatchedPortTyp

    WFS_ERR_TRAY_NOT_EMPTY_UNMATCHED("WFS_ERR_TRAY_NOT_EMPTY_UNMATCHED", "해당 TRAY (%S)의 상태가 EMPTY가 아닙니다."),

    /**
     * UNREGISTER: 등록되어 있지 않다.
     */
    WFS_ERR_CARR_INF_UNREGISTER("WFS_ERR_CARR_INF_UNREGISTER", "해당 CST (%s)로 등록된 재공(wip)이 없습니다."),
    WFS_ERR_PORT_INF_UNREGISTER("WFS_ERR_PORT_INF_UNREGISTER", "해당 포트 (%s)로 전산에서 조회 되지 않았습니다."),
    WFS_ERR_PORT_CARR_INF_UNREGISTER("WFS_ERR_PORT_CARR_INF_UNREGISTER", "해당 포트 (%s)에 CST(%s)가 등록되지 않았습니다."),
    WFS_ERR_TRAN_JOB_UNREGISTER("WFS_ERR_TRAN_JOB_UNREGISTER", "해당 CST(%s)로 생성된 반송 작업이 없습니다."),


    /**
     * REGISTER: 등록되어 있다.
     */
    WFS_ERR_CARR_RESV_INF_REGISTER("WFS_ERR_CARR_RESV_INF_REGISTER", "해당 CST (%s)로 등록된 예약 정보가 습니다. 예약 정보: %s"),
    WFS_ERR_LOT_RESV_ALREADY_REGISTER("WFS_ERR_LOT_RESV_ALREADY_REGISTER", "설비 (%s) 포트 (%s)에 예약된 다른 재공 [Lot: (%s), Carr: (%s)]이 존재합니다. "),  // 기존: ReservedLotAlreadyExist


    /**
     * NOTFOUND: 찾지 못하다.
     */
    WFS_ERR_INF_NOTFOUND("WFS_ERR_INF_NOTFOUND", "주어진 정보로 조회를 할 수 없습니다. 조회 파라미터: %s"),
    WFS_ERR_LOT_INF_NOTFOUND("WFS_ERR_LOT_INF_NOTFOUND", "주어진 정보로 Lot을 찾을 수 없습니다. 주어진 정보: %s"),
    WFS_ERR_PORT_INF_NOTFOUND("WFS_ERR_PORT_INF_NOTFOUND", "주어진 정보로 포트을 찾을 수 없습니다. [설비: (%s), 포트: (%s)]"),
    WFS_ERR_PREV_SLOT_INF_NOTFOUND("WFS_ERR_PREV_SLOT_INF_NOTFOUND", "주어진 정보로 이전에 할당된 Slot 위치를 찾을 수 없습니다. [현재 CST Id: (%s), 포트: (%s)]"),
    WFS_ERR_JOB_SLOT_INF_NOTFOUND("WFS_ERR_JOB_SLOT_INF_NOTFOUND", "주어진 Work (%s)로 생성된 Job Slot 정보를 찾을 수 없습니다. [현재 Lot Id: (%s), 설비: (%s)]"),
    WFS_ERR_WORK_JOB_INF_NOTFOUND("WFS_ERR_WORK_JOB_INF_NOTFOUND", "주어진 Work (%s)로 생성된 Job 정보를 찾을 수 없습니다. [현재 Lot Id: (%s), 설비: (%s)]"),


    /**
     * AR ERROR (Auto Recovery 진행 중 에러)
     */

    WFS_AR_ERR_EXCEED_RETRIAL("WFS_AR_ERR_EXCEED_RETRIAL", "AR 재시도 횟수 초과로 실패 [기준 횟수: %s, 현재 횟수: %s ]"),





    WFS_ERR_SAMPLE("WFS_ERR_SAMPLE", "WFS 에러 샘플.");

    private final String code;
    private final String format;

    ApExceptionCode(String code, String format) {
        this.code = code;
        this.format = format;
    }


    public String getCode() {
        return code;
    }

    public String getFormat() {
        return format;
    }
}
