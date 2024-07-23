package com.abs.wfs.workman.dao.query.wipLotProdMat.vo;

import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WipLotProdMatDto {

    // COMMON
    String siteId;
    UseStatCd useStatCd;

    // WIP
    String lotId;
    String carrId;

    // PROD MAT
    String prodMtrlId;
    String slotNo;
    String prevSlotNo;
    String prevCarrId;

    // POS
    String holdYn;
}
