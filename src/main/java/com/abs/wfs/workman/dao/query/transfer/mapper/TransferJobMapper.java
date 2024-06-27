package com.abs.wfs.workman.dao.query.transfer.mapper;

import com.abs.wfs.workman.dao.query.transfer.vo.WnTransferJob;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TransferJobMapper {

    // select WN_TRANSFER_JOB Table
    List<WnTransferJob> selectTransferJob(WnTransferJob wnTransferJob);

    List<WnTransferJob> selectTransferJobWithinTime(WnTransferJob wnTransferJob);

    // update WN_TRANSFER_JOB Table
    int updateWnTransferJob(WnTransferJob wnTransferJob);

    // insert WN_TRANSFER_JOB Table
    int createWnTransferJob(WnTransferJob wnTransferJob);

    // insert WH_TRANSFER_JOB Table
    int createWhTransferJob(String objId);

    // delete WN_TRANSFER_JOB Table
    int deleteTransferJob(WnTransferJob wnTransferJob);

}
