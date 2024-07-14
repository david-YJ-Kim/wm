package com.abs.wfs.workman.dao.query.service.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateWipStatEventNmByCarrIdVo {
    String siteId;
    String cid;
    String tid;
    String carrId;
    String mdfyUserId;
}
