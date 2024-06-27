package com.abs.wfs.workman.dao.query.work.mapper;

import com.abs.wfs.workman.dao.query.work.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper

public interface WorkMapper {

    //--- WN_WORK_STAT --------------------------
    // select WN_WORK_STAT Table
    List<WnWorkStat> selectWnWorkStat(WnWorkStat wnWorkStat);

    // update WN_WORK_STAT Table
    int updateWnWorkStat(WnWorkStat wnWorkStat);

    // insert WN_WORK_STAT Table
    int createWnWorkStat(WnWorkStat wnWorkStat);

    // insert WH_WORK_STAT Table
    int createWhWorkStat(String objId);

    // delete WN_WORK_STAT
    int deleteWnWorkStat(String objId);

    int deleteWnWorkStatByWorkId(String workId);


    //--- WN_WORK_JOB --------------------------
    // select WN_WORK_JOB
    List<WnWorkJob> selectWnWorkJob(WnWorkJob wnWorkJob);

    // update WN_WORK_JOB Table
    int updateWnWorkJob(WnWorkJob wnWorkJob);

    // insert WN_WORK_JOB Table
    int createWnWorkJob(WnWorkJob wnWorkJob);

    // insert WH_WORK_JOB Table
    int createWhWorkJob(String objId);

    // delete WN_WORK_JOB Table
    int deleteWnWorkJob(String objId);

    int deleteWnWorkJobByWorkId(String workId);


    //--- WN_WORK_JOB_SLOT_INFO -----------------
    // select WN_WORK_JOB_SLOT_INFO
    List<WnWorkJobSlotInfo> selectWnWorkJobSlotInfo(WnWorkJobSlotInfo wnWorkJobSlotInfo);

    // update WN_WORK_JOB_SLOT_INFO Table
    int updateWnWorkJobSlotInfo(WnWorkJobSlotInfo wnWorkJobSlotInfo);

    // create WN_WORK_JOB_SLOT_INFO Table
    int createWnWorkJobSlotInfo(WnWorkJobSlotInfo wnWorkJobSlotInfo);

    // create WH_WORK_JOB_SLOT_INFO Table
    int createWhWorkJobSlotInfo(String objId);

    // create WH_WORK_JOB_SLOT_INFO FOR ALL SLOT
    int createWhWorkJobSlotInfoAllSlot(WnWorkJobSlotInfo wnWorkJobSlotInfo);

    // delete WN_WORK_JOB_SLOT_INFO Table
    int deleteWnWorkJobSlotInfoALLSlot(WnWorkJobSlotInfo wnWorkJobSlotInfo);

    int deleteWnWorkJobSlotInfoByWorkId(String workId);


    //--- WN_WORK_JOB_CELL_INFO -----------------
    // create WN_WORK_JOB_CELL_INFO Table
    int createWnWorkJobCellInfo(WnWorkJobCellInfo wnWorkJobCellInfo);

    // create WH_WORK_JOB_CELL_INFO Table
    int createWhWorkJobCellInfo(String objId);

    int deleteWnWorkJobCellInfoByWorkId(String workId);


    //-- WORK LOT 조인 쿼리 (LOT INFO)
    Optional<WorkJobLotQueryDto> selectWorkJobLotQueryDto(WorkJobLotQueryDto dto);

    Optional<List<WorkSlotListInfoQueryDto>> selectWorkSlotListInfoQueryDto(WorkSlotListInfoQueryDto dto);

}