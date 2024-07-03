package com.abs.wfs.workman.service.common.transferJob.vo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferJobReqVo {

    String siteId;

    String carrId;

    String userId;
    String comment;

    String srcEqpId;
    String srcPortId;

    String destEqpId;
    String destPortId;

    String prio;



}
