package com.abs.wfs.workman.dao.query.work.vo;


import lombok.Builder;
import lombok.Data;

@Data
public class WorkJobLotQueryDto {

    String lotId;
    String prntLotId;
    String prodDefId;
    String procDefId;
    String procSgmtId;
    String procNodeId;
    String prevEqpId;

    String siteId;
    String jobStatCd;
    String workId;
    String inCarrId;


    @Builder
    public WorkJobLotQueryDto(String siteId, String jobStatCd, String workId, String inCarrId) {
        this.siteId = siteId;
        this.jobStatCd = jobStatCd;
        this.workId = workId;
        this.inCarrId = inCarrId;
    }
}

/* selectWipLotInfo
{
  "siteId": "SVM",
  "jobStatCd": "Active",
  "workId": "WORK_20240502",
  "inCarrId": "CAA0250"
}
 */