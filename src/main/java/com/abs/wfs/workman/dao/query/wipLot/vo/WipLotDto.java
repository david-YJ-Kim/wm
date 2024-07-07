package com.abs.wfs.workman.dao.query.wipLot.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WipLotDto {

    // WIP
    String lotId;
    String carrId;
    String workStatCd;
    String selfInspYn;
    String selfInspInfoObjId;
    String crntEqpId;
    String crntPortId;

    // LOT
    String prodDefId;
    String procDefId;
    String procSgmtId;


    // parameter
    String pSiteId;
    String pUseStatCd;
    String pLotId;
    String pSelfInspYn;
    String pCrntEqpId;
    String pCrntPortId;


}

/* selectWipLotInfo
{
  "plotId": "D100",
  "psiteId": "SVM",
  "puseStatCd": "Usable",
  "pselfInspYn": "Y"
}
 */
