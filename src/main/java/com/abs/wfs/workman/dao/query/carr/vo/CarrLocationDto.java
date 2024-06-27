package com.abs.wfs.workman.dao.query.carr.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarrLocationDto {
    private String crntEqpId;
    private String crntPortId;
    private String lotId ;
    private String holdYn;
    private String procStatCd;
    private String sgmtStatCd;
    private String workStatCd;
    private String resvEqpId;
    private String resvPortId;
    private String resvGrpId;
    private String ecoId;
    private String prntLotId;

    private String pCarrId;
    private String pLotId;
}
