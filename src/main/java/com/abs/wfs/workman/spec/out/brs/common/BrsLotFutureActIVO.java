package com.abs.wfs.workman.spec.out.brs.common;

import lombok.Data;

@Data
public class BrsLotFutureActIVO {
    String actTyp; // (Enum) LotFutureActionType
    String actProdDefId;
    String actProcDefId;
    String actProcSgmtId;
    String actRsnCd;

    String alrmSysId;
}
