package com.abs.wfs.workman.dao.domain.transferJob.vo;


import com.abs.wfs.workman.dao.domain.transferJob.model.WnTransferJob;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CancelTransferJobResultVo {

    String siteId;
    String portId;

    List<WnTransferJob> canceledSrcTransferJobs;
    List<WnTransferJob> canceledDstTransferJobs;

    int totalCanceledCount;
}
