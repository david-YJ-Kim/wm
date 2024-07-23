package com.abs.wfs.workman.dao.query.service.vo;

import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SelectWorkJobPortVo {

    // COMMON
    String siteId;
    UseStatCd useStatCd;


    // JOB
    String workId;
    String jobSeqId;
    String lotId;
    String inCarrId;
    String inPortId;
    String outPortId;
    String outCarrId;
    String rcpDefId;

    // PORT
    String portTyp;


}
