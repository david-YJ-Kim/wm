package com.abs.wfs.workman.dao.query.model;

import lombok.Builder;
import lombok.Data;

@Data
public class IamMultiLangErrorCodeIVo {

    String mtlgGrpIndex;
    String cpnntTypCd;
    String solCd;
    String mtlgKey;
    String mtlgTypCd;
    String mtlgVal;

    @Builder
    public IamMultiLangErrorCodeIVo(String mtlgGrpIndex, String cpnntTypCd, String solCd, String mtlgKey, String mtlgTypCd, String mtlgVal) {
        this.mtlgGrpIndex = mtlgGrpIndex;
        this.cpnntTypCd = cpnntTypCd;
        this.solCd = solCd;
        this.mtlgKey = mtlgKey;
        this.mtlgTypCd = mtlgTypCd;
        this.mtlgVal = mtlgVal;
    }
}
