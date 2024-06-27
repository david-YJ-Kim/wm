package com.abs.wfs.workman.dao.domain.ppsRecipe.vo;


import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CnPpsRecipeProcEqpRequestDto {

    String siteId;
    String eqpId;
    String prodDefId;
    String procDefId;
    String procSgmtId;
}
/*
{
    "siteId": "SVM",
    "eqpId": "AP-LA-03-01",
    "prodDefId": "NP24-04-001",
    "procDefId": "R00003",
    "procSgmtId": "P30201"
}
 */