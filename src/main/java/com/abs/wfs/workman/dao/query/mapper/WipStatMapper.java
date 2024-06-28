package com.abs.wfs.workman.dao.query.mapper;


import com.abs.wfs.workman.dao.query.model.WnWipStat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface WipStatMapper {
	
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
