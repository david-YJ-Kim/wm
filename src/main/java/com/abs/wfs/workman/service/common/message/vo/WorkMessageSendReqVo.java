package com.abs.wfs.workman.service.common.message.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkMessageSendReqVo {

    String workId;
    String eqpId;
    String prodDefId;
    String procDefId;
    String procSgmtId;
}
