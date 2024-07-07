package com.abs.wfs.workman.dao.query.service.vo;


import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class SearchProdStartedPanelReqVo {

    String siteId;
    String useStatCd;
    String workId;
    String lotId;
    String JobSeqId;
    String slotNo;
    Timestamp prodMtrlStrtTm;
    Timestamp prodMtrlEndTm;

}
