package com.abs.wfs.workman.dao.query.service.vo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkInfoQueryRequestVo {


    String workId;
    String workStatCd;
    String eqpId;
    String rsnCd;
    String outPortId;
    String inPortId;
    String inCarrId;
    String lotId;
    String jobSeqId;
    String objId;
    String siteId;
    String useStatCd;

}
