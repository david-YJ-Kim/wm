package com.abs.wfs.workman.dao.query.tool.vo;

import lombok.*;

@NoArgsConstructor
@Data
public class QueryEqpVo {

    private String siteId;
    private String useStatCd;
    private String eqpId;
    private String eqpTyp;
    private String modelTyp;
    private String batchYn;
    private String inlineYn;
    private String eqpInlineSeq;
    private String eqpInlineId;
    private String statCd;
    private String procStatCd;
    private String ctrlModeCd;
    private String oprtnModeCd;
    private String sorterModeYn;
    private String autoDspYn;
    private String intlkYn;
    private String sorterModeAvailYn;


    @Builder
    public QueryEqpVo(String siteId, String useStatCd, String eqpId, String eqpTyp, String modelTyp, String batchYn, String inlineYn, String eqpInlineSeq, String eqpInlineId, String statCd, String procStatCd, String ctrlModeCd, String oprtnModeCd, String sorterModeYn, String autoDspYn, String intlkYn, String sorterModeAvailYn) {
        this.siteId = siteId;
        this.useStatCd = useStatCd;
        this.eqpId = eqpId;
        this.eqpTyp = eqpTyp;
        this.modelTyp = modelTyp;
        this.batchYn = batchYn;
        this.inlineYn = inlineYn;
        this.eqpInlineSeq = eqpInlineSeq;
        this.eqpInlineId = eqpInlineId;
        this.statCd = statCd;
        this.procStatCd = procStatCd;
        this.ctrlModeCd = ctrlModeCd;
        this.oprtnModeCd = oprtnModeCd;
        this.sorterModeYn = sorterModeYn;
        this.autoDspYn = autoDspYn;
        this.intlkYn = intlkYn;
        this.sorterModeAvailYn = sorterModeAvailYn;
    }


    @Override
    public String toString() {
        return "QueryEqpVo{" +
                "siteId='" + siteId + '\'' +
                ", useStatCd='" + useStatCd + '\'' +
                ", eqpId='" + eqpId + '\'' +
                ", eqpTyp='" + eqpTyp + '\'' +
                ", modelTyp='" + modelTyp + '\'' +
                ", batchYn='" + batchYn + '\'' +
                ", inlineYn='" + inlineYn + '\'' +
                ", eqpInlineSeq='" + eqpInlineSeq + '\'' +
                ", eqpInlineId='" + eqpInlineId + '\'' +
                ", statCd='" + statCd + '\'' +
                ", procStatCd='" + procStatCd + '\'' +
                ", ctrlModeCd='" + ctrlModeCd + '\'' +
                ", oprtnModeCd='" + oprtnModeCd + '\'' +
                ", sorterModeYn='" + sorterModeYn + '\'' +
                ", autoDspYn='" + autoDspYn + '\'' +
                ", intlkYn='" + intlkYn + '\'' +
                ", sorterModeAvailYn='" + sorterModeAvailYn + '\'' +
                '}';
    }
}
