package com.abs.wfs.workman.dao.query.service;

import com.abs.wfs.workman.dao.query.dao.SorterJobDAO;
import com.abs.wfs.workman.dao.query.dao.TransferJobDAO;
import com.abs.wfs.workman.dao.query.dao.WipStatDAO;
import com.abs.wfs.workman.dao.query.dao.WorkDAO;
import com.abs.wfs.workman.dao.query.model.WnSorterJobExec;
import com.abs.wfs.workman.dao.query.service.vo.UpdateEventNmByLotCarrIdRequestVo;
import com.abs.wfs.workman.dao.query.service.vo.UpdateWorkStatusByLotIdForWorkStartReqVo;
import com.abs.wfs.workman.util.code.SorterJobStatCdExec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class WfsQueryService {

	@Autowired
	WipStatDAO wipStatDAO;
	
	@Autowired
	SorterJobDAO sorterJobDAO;
	
	@Autowired
	TransferJobDAO transferJobDAO;
	
	
	@Autowired
	WorkDAO workDAO;
	
	/**
	 * update WN_WIP_STAT.WORK_STAT_CD Column
	 * by Carrier ID
	 * @param cid
	 * @param siteId
	 * @param carrId
	 * @param mdfyUserId
	 * @param workStatCd
	 * @param dspInfoCleanFlag
	 * @return
	 * @throws Exception 
	 */
	public int updateWorkStatusByCarrId(String siteId, String cid, String tid, String carrId, String mdfyUserId, String workStatCd, boolean dspInfoCleanFlag) throws Exception {
		try {
			return this.wipStatDAO.updateWorkStatusByCarrId(siteId, cid, tid, carrId, mdfyUserId, workStatCd, dspInfoCleanFlag);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 삭제대상 상위 method와 통합 - 23.07.07
	 * update WN_WIP_STAT.WORK_STAT_CD Column	
	 * by Carrier ID
	 * @param cid
	 * @param siteId
	 * @param carrId
	 * @param mdfyUserId
	 * @param workStatCd
	 * @return
	 * @throws Exception 
	 */
	public int updateWorkStatusByCarrIdForWorkStart(String siteId, String cid, String tid, String carrId, String mdfyUserId, String workStatCd) throws Exception {
		try {
			return this.wipStatDAO.updateWorkStatusByCarrId(siteId, cid, tid, carrId, mdfyUserId, workStatCd, false);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * update WN_WIP_STAT.WORK_STAT_CD, BATCH_SEQ, BATCH_ID Column 
	 * by Carrier ID
	 * @param cid
	 * @param siteId
	 * @param carrId
	 * @param mdfyUserId
	 * @param workStatCd
	 * @return
	 * @throws Exception 
	 */
	public int updateWorkStatusByCarrIdForBatchEnd(String siteId, String cid, String tid, String carrId, String mdfyUserId, String workStatCd) throws Exception {
		try {
			return this.wipStatDAO.updateWorkStatusByCarrIdForBatchEnd(siteId, cid, tid, carrId, mdfyUserId, workStatCd, true);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * update WN_WIP_STAT.WORK_STAT_CD Column
	 * by LOT ID
	 * @param cid
	 * @param siteId
	 * @param lotId
	 * @param mdfyUserId
	 * @param workStatCd
	 * @param dspInfoCleanFlag
	 * @return
	 * @throws Exception 
	 */
	public int updateWorkStatusByLotId(String siteId, String cid, String tid, String lotId, String mdfyUserId, String workStatCd, boolean dspInfoCleanFlag) throws Exception {
		try {
			return this.wipStatDAO.updateWorkStatusByLotId(siteId, cid, tid, lotId, mdfyUserId, workStatCd, null, null, dspInfoCleanFlag );
		} catch (Exception e) {
			throw e;
		}
	}
	
	public int updateWorkStatusOnlyId(String siteId, String cid, String tid, String lotId, String mdfyUserId, String workStatCd, boolean dspInfoCleanFlag) throws Exception {
		try {
			return this.wipStatDAO.updateWorkStatusOnlyLotId(siteId, cid, tid, lotId, mdfyUserId, workStatCd, null, null, dspInfoCleanFlag );
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 통합대상 - 23.07.07
	 * update WN_WIP_STAT.WORK_STAT_CD Column FOR WORK START
	 * by LOT ID
	 * @return
	 * @throws Exception 
	 */
	public int updateWorkStatusByLotIdForWorkStart(UpdateWorkStatusByLotIdForWorkStartReqVo vo) throws Exception {
		try {
			//Dispatch(Reserved) Eqp, Port and check Flag Clear
			return this.wipStatDAO.updateWorkStatusByLotId(vo.getSiteId(), vo.getCid(), vo.getTid(), vo.getLotId(),
					vo.getMdfyUserId(), vo.getWorkStatCd(), null, null, false );
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * update WN_WIP_STAT for WORK_END
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param lotId
	 * @param mdfyUserId
	 * @param workStatCd
	 * @param crntEqpId
	 * @param crntPortId
	 * @return
	 * @throws Exception 
	 */
	public int updateWorkStatusByLotIdForWorkEnd(String siteId, String cid, String tid, String lotId, String mdfyUserId, 
												String workStatCd, boolean dspInfoCleanFlag, String crntEqpId, String crntPortId) throws Exception {
		try {
			//Dispatch(Reserved) Eqp, Port and check Flag Clear
			return this.wipStatDAO.updateWorkStatusByLotId(siteId, cid, tid, lotId, mdfyUserId, workStatCd, crntEqpId, crntPortId, dspInfoCleanFlag );
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * update WN_WIP_STAT.WORK_STAT_CD, BATCH_SEQ, BATCH_ID Column
	 * by LOT ID
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param lotId
	 * @param mdfyUserId
	 * @param workStatCd
	 * @return
	 * @throws Exception 
	 */
	public int updateWorkStatusForBatchEndByLotId(String siteId, String cid, String tid, String lotId, String mdfyUserId, String workStatCd) throws Exception {
		try {
			return this.wipStatDAO.updateWorkStatusForBatchEndByLotId(siteId, cid, tid, lotId, mdfyUserId, workStatCd, true);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * UPDATE WN_WIP_STAT.TRACK_IN_CNFM_YN
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param lotId
	 * @param trackInCnfmYn
	 * @return
	 * @throws Exception 
	 */
	public int updateWnWipStatForTrackCnfmYn(String siteId, String cid, String tid, String userId, String lotId, String trackInCnfmYn ) throws Exception {
		try {			
			return this.wipStatDAO.updateTrackInCnfmYn(siteId, cid, tid, userId, lotId, trackInCnfmYn);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * UPDATE WN_WIP_STAT.RCP_CHK_YN
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param lotId
	 * @param rcpChkYn
	 * @return
	 * @throws Exception 
	 */
	public int updateWnWipStatForRcpChkYn(String siteId, String cid, String tid, String userId, String lotId, String rcpChkYn) throws Exception {
		try {
			return this.wipStatDAO.updateRcpChkYn(siteId, cid, tid, userId, lotId, rcpChkYn);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * UPDATE WN_WIP_STAT.MANL_LDNG_YN
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param carrId
	 * @param manlLdngYn
	 * @return
	 * @throws Exception
	 */
	public int updateManlLdngYnInfo(String siteId,String cid, String tid, String userId, String carrId, String manlLdngYn) throws Exception {
		try {
			return this.wipStatDAO.updateManlLdngYnInfo(siteId, cid, tid, userId, carrId, manlLdngYn);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * UPDATE WN_WIP_STAT
	 * TRACK_IN_CNFM_YN, EQP_CHK_YN, RCP_CHK_YN Clear
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param lotId
	 * @return
	 * @throws Exception 
	 */
	public int clearAllChkFlag(String siteId, String cid, String tid, String userId, String lotId) throws Exception {
		try {
			return this.wipStatDAO.clearAllChkFlag(siteId, cid, tid, userId, lotId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * UPDATE WN_WIP_STAT.EVNT_NM by carr
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param carrId
	 * @param mdfyUserId
	 * @return
	 * @throws Exception 
	 */
	public int updateWipStatEventNmByCarrId(String siteId, String cid, String tid, String carrId, String mdfyUserId) throws Exception {
		try {
			return this.wipStatDAO.updateEventNmByCarrId(siteId, cid, tid, carrId, mdfyUserId);
		}catch (Exception e) {
			throw e;
		}
		
	}
	
	/**
	 * UPDATE WN_WIP_STAT.EVNT_NM by lot
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param lotId
	 * @param mdfyUserId
	 * @return
	 * @throws Exception 
	 */
	public int updateWipStatEventNmByLotId(String siteId, String cid, String tid, String lotId, String mdfyUserId) throws Exception {
		try {
			return this.wipStatDAO.updateEventNmByLotId(siteId, cid, tid, lotId, mdfyUserId);
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public int updateEventNmByLotCarrId(UpdateEventNmByLotCarrIdRequestVo vo) throws Exception {
		try {
			return this.wipStatDAO.updateEventNmByLotCarrId(vo.getSiteId(), vo.getCid(), vo.getTid(), vo.getLotId(),
														vo.getCarrId(), vo.getMdfyUserId());
		}catch (Exception e) {
			throw e;
		}
		
	}
	
	
	/**
	 * For ECO
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param lotId
	 * @param mdfyUserId
	 * @return
	 * @throws Exception
	 */
	public int updateClearResvInfoForEcoLOT(String siteId, String cid, String tid, String lotId, String mdfyUserId) throws Exception {
		try {
			return this.wipStatDAO.updateClearResvInfoForEcoLOT(siteId, cid, tid, lotId, mdfyUserId);
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	/**
	 * UPDATE WN_WIP_STAT For MOVE COMPLETE
	 * Current EqpId, PortId
	 * WORK_STAT_CD : Transfer -> Ready
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param carrId
	 * @param userId
	 * @param crntEqpId
	 * @param crntPortId
	 * @param workStatCd
	 * @return
	 * @throws Exception 
	 */
	public int updateWipStatForMoveComplete(String siteId, String cid, String tid, String carrId, String userId, String crntEqpId, String crntPortId, String workStatCd) throws Exception {
		try {
			return this.wipStatDAO.updateWipStatForMoveCompleteByCarrId(siteId, cid, tid, carrId, userId, crntEqpId, crntPortId, workStatCd);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param carrId
	 * @param userId
	 * @param crntEqpId
	 * @param crntPortId
	 * @param workStatCd
	 * @return
	 * @throws Exception
	 */
	public int updateWipStatForMoveCancelComplete(String siteId, String cid, String tid, String carrId, String userId, String crntEqpId, String crntPortId, String workStatCd) throws Exception {
		try {
			return this.wipStatDAO.updateWipStatForMoveCancelByCarrId(siteId, cid, tid, carrId, userId, crntEqpId, crntPortId, workStatCd);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	
	/**
	 * UPDATE WN_WIP_STAT
	 * Current EqpId, PortId
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param carrId
	 * @param userId
	 * @param crntEqpId
	 * @param crntPortId
	 * @return
	 * @throws Exception 
	 */
	public int updateCurrentEqpPortByCarrId(String siteId, String cid, String tid, String carrId, String userId, String crntEqpId, String crntPortId) throws Exception {
		try {
			return this.wipStatDAO.updateCurrentEqpPortByCarrId(siteId, cid, tid, carrId, userId, crntEqpId, crntPortId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public int updateCurrentEqpPortForCarrLogChg(String siteId, String cid, String tid, String carrId, String userId, String crntEqpId, String crntPortId, String moveStatCd) throws Exception {
		try {
			return this.wipStatDAO.updateCurrentEqpPortForCarrLogChg(siteId, cid, tid, carrId, userId, crntEqpId, crntPortId, moveStatCd);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * UPDATE WN_WIP_STAT
	 * RTD_DSP_WORK_REP Update For Batch
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param carrId
	 * @param userId
	 * @param batchId
	 * @param batchSeq
	 * @param resvEqpId
	 * @param resvPortId
	 * @param resvGrpId
	 * @return
	 * @throws Exception 
	 */
	public int updateWipStatForDispatchigBatch(String siteId, String cid, String tid, String carrId, String userId, String batchId, String batchSeq, String resvEqpId, String resvPortId, String resvGrpId) throws Exception {
		try {
			return this.wipStatDAO.updateDspWorkRepBatch(siteId, cid, tid, carrId, userId, batchId, batchSeq, resvEqpId, resvPortId, resvGrpId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * UPDATE WN_WIP_STAT
	 * RTD_DSP_WORK_REP Update For BP, INOUT
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param carrId
	 * @param lotId
	 * @param userId
	 * @param resvEqpId
	 * @param resvPortId
	 * @param resvGrpId
	 * @param resvOutCarrId
	 * @param resvOutPortId
	 * @return
	 * @throws Exception 
	 */
	public int updateWipStatForDispatchigNormal(String siteId, String cid, String tid, String carrId, String lotId, String userId, String resvEqpId, String resvPortId, String resvGrpId, String resvOutCarrId, String resvOutPortId) throws Exception {
		try {
			return this.wipStatDAO.updateDspWorkRepNormal(siteId, cid, tid, carrId, lotId, userId, resvEqpId, resvPortId, resvGrpId, resvOutCarrId, resvOutPortId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * UPDATE WN_WIP_STAT
	 * 1 CARR Multi Lot ECO RESV Info Update
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param lotId
	 * @param carrId
	 * @param userId
	 * @param resvEqpId
	 * @param resvPortId
	 * @param resvGrpId
	 * @param ecoId
	 * @return
	 * @throws Exception
	 */
	public int updateWipStatForDispatchingECO(String siteId, String cid, String tid, String lotId, String carrId, String userId, String resvEqpId, String resvPortId, String resvGrpId, String ecoId) throws Exception {
		try {
			return this.wipStatDAO.updateDspWorkRepECO(siteId, cid, tid, lotId, carrId, userId, resvEqpId, resvPortId, resvGrpId, ecoId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * UPDATE WN_WORK_STAT For InlineStatCd
	 * Event Nm, mdfyTime
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param workId
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	public int updateWorkStatForInline(String siteId, String cid, String tid, String workId, String userId, String inlineStatCd) throws Exception {
		try {
			return this.workDAO.updateWorkStat(siteId, cid, tid, workId, userId, inlineStatCd);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * UPDATE WN_SORTER_JOB_SLOT_INFO 
	 * START_TM, END_TM, EVENT_NM Update
	 * GlassID Scan 인 경우 > SCAN_SLOT_NO, SCAN_PROD_MTRL_ID update
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param objId
	 * @param sorterJobTyp
	 * @param scanSlotNo
	 * @param scanProdMtrlId
	 * @param isStart
	 * @return
	 * @throws Exception 
	 */
	public int updateSorterJobSlotInfo(String siteId, String cid, String tid, String userId, String objId, String sorterJobTyp, String scanSlotNo, String scanProdMtrlId, boolean isStart) throws Exception {
		try {
			return this.sorterJobDAO.updateSorterJobSlotInfo(siteId, cid, tid, userId, objId, sorterJobTyp, scanSlotNo, scanProdMtrlId, isStart);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Scan 정보 Insert
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param objId
	 * @param jobId
	 * @param eqpId
	 * @param lotId
	 * @param sorterJobTyp
	 * @param scanSlotNo
	 * @param scanProdMtrlId
	 * @return
	 * @throws Exception
	 */
	public int insertSorterJobScanSlotInfo(String siteId, String cid, String tid, String userId, String objId, String jobId, String eqpId, String lotId, String sorterJobTyp, String scanSlotNo, String scanProdMtrlId) throws Exception {
		try {
			return this.sorterJobDAO.createSorterJobSlotInfo(siteId, cid, tid, userId, objId, sorterJobTyp, cid, tid, sorterJobTyp, scanSlotNo, scanProdMtrlId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * UPDATE WN_WIP_STAT
	 * SELF_INSP_YN, SELF_INSP_PANEL_CNT, SELF_INSP_INFO_OBJ_ID
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param lotId
	 * @param selfInspPanelCnt
	 * @param selfInspObjId
	 * @return
	 * @throws Exception 
	 */
	public int updateSelfInspInfo(String siteId, String cid, String tid, String userId, String lotId, String selfInspYn, String selfInspPanelCnt, String selfInspObjId) throws Exception {
		try {
			return this.wipStatDAO.updateSelfInspInfo(siteId, cid, tid, userId, lotId, selfInspYn, selfInspPanelCnt, selfInspObjId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * WN_WIP_STAT
	 * SMPL_LOT_YN, SPML_WORK_TYP, SMPL_QTY
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param lotId
	 * @param smplLotYn
	 * @param smplWorkTyp
	 * @param smplQty
	 * @return
	 * @throws Exception 
	 */
	public int updateSampleInfo(String siteId,String cid, String tid, String userId, String lotId, String smplLotYn, String smplWorkTyp, String smplQty) throws Exception {
		try {
			return this.wipStatDAO.updateSampleInfo(siteId, cid, tid, userId, lotId, smplLotYn, smplWorkTyp, smplQty);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	
	// ========= SORTER ==========
	
	/**
	 * update WN_SORTER_JOB_RESV
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param jobId
	 * @param jobStatCd
	 * @return
	 * @throws Exception 
	 */
	public int updateSorterJobResv(String siteId, String cid, String tid, String userId, String jobId, String jobStatCd, String eqpId, String priority) throws Exception {
		try {
			return this.sorterJobDAO.updateSorterJobResv(siteId, cid, tid, userId, jobId, jobStatCd, eqpId, priority);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * update WN_SORTER_JOB_RESV with TargetCarr Assign
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param jobId
	 * @param jobStatCd
	 * @param tgtCarrId
	 * @return
	 * @throws Exception 
	 */
	public int updateSorterJobResv(String siteId, String cid, String tid, String userId, String jobId, String jobStatCd, String tgtCarrId) throws Exception {
		try {
			return this.sorterJobDAO.updateSorterJobResv(siteId, cid, tid, userId, jobId, jobStatCd, tgtCarrId);
		} catch (Exception e) {
			throw e;
		}
	}
		
	/**
	 * update WN_SORTER_JOB_EXEC
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param jobId
	 * @param jobStatCd
	 * @return
	 * @throws Exception 
	 */
	public int updateSorterJobExec(String siteId, String cid, String tid, String userId, String jobId, String jobStatCd) throws Exception {
		try {
			return this.sorterJobDAO.updateSorterJobExec(siteId, cid, tid, userId, jobId, jobStatCd);
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	/**
	 * Sorter JOB 우선순위 반영
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param eqpId
	 * @param prirtNo
	 * @return
	 * @throws Exception 
	 */
	public int updateSorterJobPriority(String siteId, String cid, String tid, String userId, String eqpId, String prirtNo) throws Exception {
		try {
			return this.sorterJobDAO.updateSorterResvPriority(siteId, cid, tid, userId, eqpId, prirtNo);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Sorter Job 실행 Table 생성
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param jobId
	 * @return
	 * @throws Exception 
	 */
	public int createSorterJobExec(String siteId, String cid, String tid, String userId, String jobId) throws Exception {
		try {
			return this.sorterJobDAO.createSorterJobExec(siteId, cid, tid, userId, jobId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
		
	/**
	 * Finish SorterJob
	 * SorterJob Resv 테이블 삭제, Exec 테이블 JOB_STAT_CD Complete로 update
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param jobId
	 * @param mdfyUserId
	 * @return
	 * @throws Exception 
	 */
	public int finishSorterJob(String siteId, String cid, String tid, String jobId, String mdfyUserId) throws Exception {
		try {
			int resultVal = -1;
			// 1. getSorterJob
			WnSorterJobExec selectSorterJobExec = this.sorterJobDAO.selectSorterJobExecByJobId(siteId, jobId, SorterJobStatCdExec.Processing.name());
			
			// 2. Check Job Exist
			if(selectSorterJobExec == null) return resultVal;
			
			// 23.02.22 - RESV 테이블도 BRS에서 삭제처리. 
			// 3. delete SorterJob Resv
//			if(this.sorterJobDAO.deleteSorterJobResvByJobId(siteId, jobId) > 0) {
//			}
			
			 //this.sorterJobDAO.updateSorterJobResvForFinish(siteId, cid, tid, mdfyUserId, jobId);
			
			// 4. update SorterJob Exec State
			resultVal = this.sorterJobDAO.updateSorterJobExecForFinish(siteId, cid, tid, mdfyUserId, jobId);
			
			return resultVal;
		} catch (Exception e) {
				throw e;
		}
	}
	
	public int finishSorterJobResv(String siteId, String cid, String tid, String jobId, String mdfyUserId) throws Exception {
		try {
			int resultVal = -1;
			
			// delete SorterJob Resv
			if(this.sorterJobDAO.deleteSorterJobResvByJobId(siteId, jobId) > 0) {
				resultVal = 1;
			}

			return resultVal;
		} catch (Exception e) {
				throw e;
		}
	}
	
	public int finishCheckCarrSlot(String siteId, String cid, String tid, String jobId, String mdfyUserId, String trnsCm ) throws Exception {
		try {
			return this.sorterJobDAO.finishCheckCarrSlot(siteId, cid, tid, mdfyUserId, jobId, trnsCm);
		} catch (Exception e) {
				throw e;
		}
	}
	
	
	//========== TRANSFER JOB =============

	/**
	 * CREATE TransferJob
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param jobId
	 * @param carrId
	 * @param crntEqpId
	 * @param crntPortId
	 * @param srcEqpId
	 * @param srcPortId
	 * @param destEqpId
	 * @param destPortId
	 * @param prio
	 * @return
	 * @throws Exception 
	 */
	public int createTransferJob(String siteId, String cid, String tid, String userId, String jobId, String carrId, String crntEqpId, String crntPortId, String srcEqpId, String srcPortId, String destEqpId, String destPortId, String prio) throws Exception {
		try {
			int resultVal = this.transferJobDAO.createTransferJob(siteId, cid, tid, userId, jobId, carrId, crntEqpId, crntPortId, srcEqpId, srcPortId, destEqpId, destPortId, prio);
			
			return resultVal;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * TransferJob Complete & Delete Job
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param jobId
	 * @param moveStatCd
	 * @return
	 * @throws Exception 
	 */
	public int finishTransferJob(String siteId, String cid, String tid, String userId, String jobId, String moveStatCd) throws Exception {
		try {
			int resultVal = -1;
		
			resultVal = this.transferJobDAO.updateTransferJob(siteId, cid, tid, userId, jobId, moveStatCd);
			
			if(resultVal > 0) {
				this.transferJobDAO.deleteTransferJobByJobId(siteId, jobId);
			}
			
			return resultVal;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * update TransferJob MoveStatCd
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param jobId
	 * @param moveStatCd
	 * @return
	 * @throws Exception 
	 */
	public int updateTransferJob(String siteId, String cid, String tid, String userId, String jobId, String moveStatCd) throws Exception {
		try {
			return this.transferJobDAO.updateTransferJob(siteId, cid, tid, userId, jobId, moveStatCd);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public int updateTransferJobEvent(String siteId, String cid, String tid, String userId, String jobId) throws Exception {
		try {
			return this.transferJobDAO.updateTransferJobEventNm(siteId, cid, tid, userId, jobId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * update TransferJob MoveStatCd & Delete TransferJob
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param jobId
	 * @param moveStatCd
	 * @param deleteFlag
	 * @return
	 * @throws Exception 
	 */
	public int updateTransferJob(String siteId, String cid, String tid, String userId, String jobId, String moveStatCd, boolean deleteFlag) throws Exception {
		try {
			int resultVal = -1;
			resultVal = this.transferJobDAO.updateTransferJob(siteId, cid, tid, userId, jobId, moveStatCd);
			
			if(resultVal > 0 && deleteFlag) {
				this.transferJobDAO.deleteTransferJobByJobId(siteId, jobId);
			}
			return resultVal;
			
		} catch (Exception e) {
			throw e;
		}
	}
	
}
