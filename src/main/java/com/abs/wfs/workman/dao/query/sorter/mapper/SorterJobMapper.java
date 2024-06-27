package com.abs.wfs.workman.dao.query.sorter.mapper;

import com.abs.wfs.workman.dao.query.sorter.vo.WnSorterJobExec;
import com.abs.wfs.workman.dao.query.sorter.vo.WnSorterJobResv;
import com.abs.wfs.workman.dao.query.sorter.vo.WnSorterJobSlotInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface SorterJobMapper {


    // WN_SORTER_JOB_RESV ===========================================
    // select WN_SORTER_JOB_RESV Table
    List<WnSorterJobResv> selectWnSorterJobResv(WnSorterJobResv sorterJobResv);

    // select WN_SORTER_JOB_RESV by Priority
    List<WnSorterJobResv> selectSorterJobPriorityList(WnSorterJobResv serterJobResv);

    // delete WN_SORTER_JOB_RESV
    int deleteSorterJobResv(WnSorterJobResv sorterJobExec);

    // update WN_SORTER_JOB_RESV table
    int updateSorterJobResv(WnSorterJobResv sorterJobResv);

    // update WN_SORTER_JOB_RESV by Priority
    int updateSorterJobResvPriority(WnSorterJobResv sorterJobResv);


    // WN_SORTER_JOB_EXEC ===========================================
    // select WN_SORTER_JOB_EXEC Table
    List<WnSorterJobExec> selectWnSorterJobExec(WnSorterJobExec sorterJobExec);

    // insert WH_SORTER_JOB_EXEC table
    int createWhSorterJobResv(String objId);

    // update WN_SORTER_JOB_EXEC table
    int updateSorterJobExec(WnSorterJobExec sorterJobExec);

    // insert WN_SORTER_JOB_EXEC table
    int createWnSorterJobExec(WnSorterJobExec sorterJobExec);

    // insert WH_SORTER_JOB_EXEC table
    int createWhSorterJobExec(String objId);



    // WN_SORTER_JOB_SLOT_INFO ===========================================
    // create WN_SORTER_JOB_SLOT_INFO table
    int createWnSorterJobSlotInfo(WnSorterJobSlotInfo sorterJobSlotInfo);

    // update WN_SORTER_JOB_SLOT_INFO table
    int updateSorterJobSlotInfo(WnSorterJobSlotInfo sorterJobSlotInfo);

    // create WH_SORTER_JOB_SLOT_INFO table
    int createWhSorterJobSlotInfo(String objId);

}
