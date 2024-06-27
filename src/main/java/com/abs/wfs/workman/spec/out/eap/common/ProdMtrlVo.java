package com.abs.wfs.workman.spec.out.eap.common;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdMtrlVo {

    String slotNo;
    String prodMtrlId;
    String subMtrlGrdCd;
}
