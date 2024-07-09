package com.abs.wfs.workman.dao.query.wipLot.vo;

import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SelectCarrLocQueryReqVo {

    String crntEqpId;
    String crntPortId;
    String lotId;
    String holdYn;
    String procStatCd;
    String sgmtStatCd;
    String workStatCd;
    String resvEqpId;
    String resvPortId;
    String resvGrpId;
    String ecoId;

    String siteId;
    UseStatCd useStatCd;
    String carrId;

}
