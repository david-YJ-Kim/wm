package com.abs.wfs.workman.dao.query.tool.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class QueryPortVo {

    private String siteId;
    private String useStatCd;
    private String portId;
    private String eqpId;
    private String carrTyp;
    private String portTyp;
    private String acesModeCd;
    private String ctrlModeCd;
    private String statCd;
    private String trsfStatCd;
    private String carrId;

    @Builder
    public QueryPortVo(String siteId, String useStatCd, String portId, String eqpId, String carrTyp, String portTyp, String acesModeCd, String ctrlModeCd, String statCd, String trsfStatCd, String carrId) {
        this.siteId = siteId;
        this.useStatCd = useStatCd;
        this.portId = portId;
        this.eqpId = eqpId;
        this.carrTyp = carrTyp;
        this.portTyp = portTyp;
        this.acesModeCd = acesModeCd;
        this.ctrlModeCd = ctrlModeCd;
        this.statCd = statCd;
        this.trsfStatCd = trsfStatCd;
        this.carrId = carrId;
    }


    @Override
    public String toString() {
        return "QueryPortVo{" +
                "siteId='" + siteId + '\'' +
                ", useStatCd='" + useStatCd + '\'' +
                ", portId='" + portId + '\'' +
                ", eqpId='" + eqpId + '\'' +
                ", carrTyp='" + carrTyp + '\'' +
                ", portTyp='" + portTyp + '\'' +
                ", acesModeCd='" + acesModeCd + '\'' +
                ", ctrlModeCd='" + ctrlModeCd + '\'' +
                ", statCd='" + statCd + '\'' +
                ", trsfStatCd='" + trsfStatCd + '\'' +
                ", carrId='" + carrId + '\'' +
                '}';
    }
}
