package com.abs.wfs.workman.dao.query.mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.abs.wfs.workman.dao.query.model.WnDspWorkInfo;
import com.abs.wfs.workman.dao.query.model.WnWorkJob;
import com.abs.wfs.workman.dao.query.model.WnWorkJobCellInfo;
import com.abs.wfs.workman.dao.query.model.WnWorkJobSlotInfo;
import com.abs.wfs.workman.dao.query.model.WnWorkStat;
import com.abs.wfs.workman.dao.query.service.vo.SearchProdStartedPanelReqVo;
import com.abs.wfs.workman.dao.query.service.vo.SelectWorkJobPortVo;
import com.abs.wfs.workman.dao.query.service.vo.WorkInfoQueryRequestVo;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface WorkMapper {


	/**
	 * Work Job과 Port 조인해서 조회
	 * @param selectWorkJobPortVo
	 * @return
	 */
	List<SelectWorkJobPortVo> selectWorkJobPort(SelectWorkJobPortVo selectWorkJobPortVo);


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

	/**
	 * Work Stat과 Job을 조인해서 Lot, Eqp, Port로 생성된 Work을 조회
	 * @return
	 */
	Optional<List<WorkInfoQueryRequestVo>> selectActiveWorkInfoQuery(WorkInfoQueryRequestVo workInfoQueryRequestVo);
	

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


	/**
	 * 현재 진행하는 Lot의 Prod 진행 개수 조회
	 * @param vo
	 * @return
	 */
	Optional<List<SearchProdStartedPanelReqVo>> searchProdStartedPanel(SearchProdStartedPanelReqVo vo);

	
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
	
	int deleteSelfInspSlot(WnWorkJobSlotInfo wnWorkJobSlotInfo);
	
	int updateUnUsableSelfInspSlot(WnWorkJobSlotInfo wnWorkJobSlotInfo);
	
	int update1MoreSlotInfo(WnWorkJobSlotInfo wnWorkJobSlotInfo);
	
	
	//--- WN_WORK_JOB_CELL_INFO -----------------
	// create WN_WORK_JOB_CELL_INFO Table
	int createWnWorkJobCellInfo(WnWorkJobCellInfo wnWorkJobCellInfo);
	
	// create WH_WORK_JOB_CELL_INFO Table
	int createWhWorkJobCellInfo(String objId);
	
	int deleteWnWorkJobCellInfoByWorkId(String workId);
	
	//--- WN_DSP_WORK_INFO -----------------
	
	List<WnDspWorkInfo> selectWnDspWorkInfo(WnDspWorkInfo wnDspWorkInfo);
	
	int updateWnDspWorkInfo(WnDspWorkInfo wnDspWorkInfo);
	
	int createWnDspWorkInfo(WnDspWorkInfo wnDspWorkInfo);
	
	int createWhDspWorkInfo(String objId);
	
	int deleteWnDspWorkInfo(String objId);

	List<Map<String,String>> selectWorkExist(Map<String, String> map);
}
