package com.abs.wfs.workman.dao.query.wip.mapper;

import com.abs.wfs.workman.dao.query.wip.vo.WnWipStat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WipStatMyMapper {

    // select Wip with Reserved Eqp and Port.
    List<WnWipStat> selectWnWipStatWithReserveEqpPort (WnWipStat wnWipStat);

    // select WN_WIP_STAT Table
    List<WnWipStat> selectWnWipStat(WnWipStat wnWipStat);

    // select WN_WIP_STAT Table byLotID
    List<WnWipStat> selectWnWipStatByLot(WnWipStat wnWipStat);

    // update WN_WIP_STAT Table
    int updateWnWipStat(WnWipStat wnWipStat);

    // insert WN_WIP_STAT Table
    int createWnWipStat(WnWipStat wnWipStat);

    // insert WH_WIP_STAT Table
    int createWhWipStat(String objId);
}
