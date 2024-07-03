package com.abs.wfs.workman.util.exception;


public enum ApExceptionCode {

    /**
     * INVALID: 부적합하다
     */
    WFS_ERR_TOOL_PORT_INF_INVALID("WFS_ERR_TOOL_INF_INVALID", "설비와 포트 정보가 부적합 합니다."),


    /**
     * UNMATCHED: 원하는 개댓값과 다르다.
     */
    WFS_ERR_TRAN_JOB_STAT_UNMATCHED("WFS_TRAN_JOB_STAT_UNMATCHED", "해당 CST (%s)로 생성된 반송 잡 (%s) 상태가 기댓값과 다릅니다. 현재 상태 %s"),
    WFS_ERR_CARR_SRC_LOC_UNMATCHED("WFS_ERR_CARR_SRC_LOC_UNMATCHED", "해당 CST (%s)의 요청 받은 위치와 현재 위치가 다릅니다. 요청 위치 (%s),  현재 위치 (%s)"),
    WFS_ERR_PORT_CARR_INF_UNMATCHED("WFS_ERR_PORT_CARR_INF_UNMATCHED", "해당 포트 (%s)에 요청 CST(%s)가 아닌 CST(%s)가 다른 등록되지 있습니다."),

    /**
     * UNREGISTER: 등록되어 있지 않다.
     */
    WFS_ERR_CARR_INF_UNREGISTER("WFS_ERR_CARR_INF_UNREGISTER", "해당 CST (%s)로 등록된 재공(wip)이 없습니다."),
    WFS_ERR_PORT_INF_UNREGISTER("WFS_ERR_PORT_INF_UNREGISTER", "해당 포트 (%s)로 전산에서 조회 되지 않았습니다."),
    WFS_ERR_PORT_CARR_INF_UNREGISTER("WFS_ERR_PORT_INF_UNREGISTER", "해당 포트 (%s)에 CST(%s)가 등록되지 않았습니다."),


    /**
     * REGISTER: 등록되어 있다.
     */
    WFS_ERR_CARR_RESV_INF_REGISTER("WFS_ERR_CARR_RESV_INF_REGISTER", "해당 CST (%s)로 등록된 예약 정보가 습니다. 예약 정보: %s"),



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
