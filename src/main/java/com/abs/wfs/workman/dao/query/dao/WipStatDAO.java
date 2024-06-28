package com.abs.wfs.workman.dao.query.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abs.wfs.workman.dao.query.mapper.WipStatMapper;
import com.abs.wfs.workman.dao.query.model.WnWipStat;

public class WipStatDAO {
	
	private static WipStatDAO instance;
	private static final Logger logger = LoggerFactory.getLogger(WipStatDAO.class);
	
	public static WipStatDAO getInstance() {
		if(instance == null) instance = new WipStatDAO();
		return instance;
	}
	
	private WipStatDAO() {}
	
	/**
	 * WN_WIP_STAT 조회 by Lot ID
	 * @param siteId
	 * @param lotId
	 * @return
	 */
	public List<WnWipStat> selectByLotId(String siteId, String lotId) throws Exception{
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper mapper = session.getMapper(WipStatMapper.class);
		
		List<WnWipStat> wipStatList = null;
		try {
			
			WnWipStat param = new WnWipStat();
			param.setSiteId(siteId);
			param.setLotId(lotId);
			
			wipStatList = mapper.selectWnWipStat(param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return wipStatList;
	}
	
	
	/**
	 * WN_WIP_STAT 
	 * WORK_STAT_CD Update by CarrId Base
	 * @param siteId
	 * @param carrId
	 * @param mdfyUserId
	 * @param workStatCd
	 * @return
	 */
	public int updateWorkStatusByCarrId(String siteId, String cid, String tid, String carrId, String mdfyUserId, String workStatCd, boolean dspInfoClearFlag) throws Exception{
		
		int updateCnt = 0;
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		try {
			WnWipStat param = new WnWipStat();
			
			// WHERE
			param.setpSiteId(siteId);
			param.setpCarrId(carrId);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStat(param);
			
			for(WnWipStat w : wipStatList) {
				
				WnWipStat setParam = new WnWipStat();
				// SET
				if(!"".equals(workStatCd)) {
					setParam.setWorkStatCd(workStatCd);
				}
				setParam.setMdfyUserId(mdfyUserId);
				setParam.setEvntNm(cid);
				setParam.setTid(tid);
				
				//Clear reserve Info & checkFlag WORK_START
				if(dspInfoClearFlag) {
					setParam.setResvEqpId("");
					setParam.setResvPortId("");
					setParam.setResvGrpId("");
					setParam.setResvOutCarrId("");
					setParam.setResvOutPortId("");
					setParam.setEqpChkYn("");
					setParam.setTrackInCnfmYn("");
					setParam.setRcpChkYn("");
					setParam.setSmplLotYn("");
					setParam.setSmplQty(0);
					setParam.setSmplWorkTyp("");
					setParam.setSelfInspInfoObjId("");
					setParam.setSelfInspPanelCnt(0);
					setParam.setSelfInspYn("");
					setParam.setBatchId("");
					setParam.setBatchSeq("");
				}
				
				// WHERE
				setParam.setpSiteId(w.getSiteId());
				setParam.setpCarrId(w.getCarrId());
				setParam.setpLotId(w.getLotId());
				setParam.setpUseStatCd(IsUsable.Usable.name());
				
				//Update & Insert History 
				if(wipStatMapper.updateWnWipStat(setParam) > 0) {
					// CREATE HISTORY
					wipStatMapper.createWhWipStat(w.getObjId());
				}
			}
			
			session.commit();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return updateCnt;
	}
	
	/**
	 * WN_WIP_STAT 
	 * WORK_STAT_CD Update by CarrId Base
	 * BATCH_ID, BATCH_SEQ Clear
	 * @param siteId
	 * @param carrId
	 * @param mdfyUserId
	 * @param workStatCd
	 * @return
	 */
	public int updateWorkStatusByCarrIdForBatchEnd(String siteId, String cid, String tid, String carrId, String mdfyUserId, String workStatCd, boolean dspInfoClearFlag) throws Exception{
		
		int updateCnt = 0;
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		try {
			WnWipStat param = new WnWipStat();
			// SET
			if(!"".equals(workStatCd)) {
				param.setWorkStatCd(workStatCd);
			}
			param.setBatchId("");
			param.setBatchSeq("");
			param.setMdfyUserId(mdfyUserId);
			param.setEvntNm(cid);
			param.setTid(tid);
			
			//Clear reserve Info & checkFlag WORK_START
			if(dspInfoClearFlag) {
				param.setResvEqpId("");
				param.setResvPortId("");
				param.setResvGrpId("");
				param.setResvOutCarrId("");
				param.setResvOutPortId("");
				param.setEqpChkYn("");
				param.setTrackInCnfmYn("");
				param.setRcpChkYn("");
				param.setSmplLotYn("");
				param.setSmplQty(0);
				param.setSmplWorkTyp("");
				param.setSelfInspInfoObjId("");
				param.setSelfInspPanelCnt(0);
				param.setSelfInspYn("");
				param.setBatchId("");
				param.setBatchSeq("");
			}
			
			// WHERE
			param.setpSiteId(siteId);
			param.setpCarrId(carrId);
			param.setpLotId("-");
			param.setpUseStatCd(IsUsable.Usable.name());
			
			//Update & Insert History 
			updateCnt = wipStatMapper.updateWnWipStat(param);
			if(updateCnt > 0) {
				// CREATE HISTORY
				wipStatMapper.createWhWipStat(param.getObjId());
			}
			
			session.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return updateCnt;
	}
	
	/**
	 * WN_WIP_STAT 
	 * WORK_STAT_CD Update by LotId Base
	 * @param cid
	 * @param siteId
	 * @param lotId
	 * @param mdfyUserId
	 * @param workStatCd
	 * @param crntEqpId
	 * @param crntPortId
	 * @param dspInfoClearFlag
	 * @return
	 */
	public int updateWorkStatusByLotId(String siteId, String cid, String tid, String lotId, String mdfyUserId, 
										String workStatCd, String crntEqpId, String crntPortId, boolean dspInfoClearFlag) throws Exception{
		
		int updateCnt = 0;
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		try {
			WnWipStat param = new WnWipStat();
			
			// SET
			if(!"".equals(workStatCd)) {
				param.setWorkStatCd(workStatCd);
			}
			param.setMdfyUserId(mdfyUserId);
			param.setEvntNm(cid);
			param.setTid(tid);
			if(crntEqpId != null) param.setCrntEqpId(crntEqpId);
			if(crntPortId != null) param.setCrntPortId(crntPortId);
			
			//Clear reserve Info & checkFlag WORK_START
			if(dspInfoClearFlag) {
				param.setResvEqpId("");
				param.setResvPortId("");
				param.setResvGrpId("");
				param.setResvOutCarrId("");
				param.setResvOutPortId("");
				param.setEqpChkYn("");
				param.setTrackInCnfmYn("");
				param.setRcpChkYn("");
				param.setSmplLotYn("");
				param.setSmplQty(0);
				param.setSmplWorkTyp("");
				param.setSelfInspInfoObjId("");
				param.setSelfInspPanelCnt(0);
				param.setSelfInspYn("");
				param.setBatchId("");
				param.setBatchSeq("");
			}
			
			// WHERE
			param.setpSiteId(siteId);
			param.setpLotId(lotId);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			
			// UPDATE
			updateCnt = wipStatMapper.updateWnWipStat(param);
			
			if(updateCnt > 0 ) {
				
				// CREATE HISTORY for Lot
				wipStatMapper.createWhWipStat(param.getObjId());
				
				// Assigned CARR Exist
				if(!"-".equals(param.getCarrId())) {
					// Update Carr Info
					WnWipStat carrParam = new WnWipStat();
					
					// SET
					if(!"".equals(workStatCd)) {
						carrParam.setWorkStatCd(workStatCd);
					}
					carrParam.setMdfyUserId(mdfyUserId);
					carrParam.setEvntNm(cid);
					carrParam.setTid(tid);
					
					//Clear reserve Info & checkFlag WORK_START
					if(dspInfoClearFlag) {
						carrParam.setResvEqpId("");
						carrParam.setResvPortId("");
						carrParam.setResvGrpId("");
						carrParam.setResvOutCarrId("");
						carrParam.setResvOutPortId("");
						carrParam.setEqpChkYn("");
						carrParam.setTrackInCnfmYn("");
						carrParam.setRcpChkYn("");
						carrParam.setSmplLotYn("");
						carrParam.setSmplQty(0);
						carrParam.setSmplWorkTyp("");
						carrParam.setSelfInspInfoObjId("");
						carrParam.setSelfInspPanelCnt(0);
						carrParam.setSelfInspYn("");
						carrParam.setBatchId("");
						carrParam.setBatchSeq("");
					}
					
					// WHERE
					carrParam.setpSiteId(siteId);
					carrParam.setpLotId("-");
					carrParam.setpCarrId(param.getCarrId());
					carrParam.setpUseStatCd(IsUsable.Usable.name());
					
					if(wipStatMapper.updateWnWipStat(carrParam) > 0) {
						// CREATE HISTORY for Carr
						wipStatMapper.createWhWipStat(carrParam.getObjId());
					}
				}
			}
			
			session.commit();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return updateCnt;
	}
	
	
	
	/**
	 * WN_WIP_STAT 
	 * WORK_STAT_CD Update by LotId Base
	 * @param cid
	 * @param siteId
	 * @param lotId
	 * @param mdfyUserId
	 * @param workStatCd
	 * @return
	 */
	public int updateWorkStatusForBatchEndByLotId(String siteId, String cid, String tid, String lotId, String mdfyUserId, String workStatCd, boolean dspInfoClearFlag) throws Exception{
		
		int updateCnt = 0;
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		try {
			WnWipStat param = new WnWipStat();
			
			// SET
			if(!"".equals(workStatCd)) {
				param.setWorkStatCd(workStatCd);
			}
			param.setBatchId("");
			param.setBatchSeq("");
			param.setMdfyUserId(mdfyUserId);
			param.setEvntNm(cid);
			param.setTid(tid);
			
			//Clear reserve Info & checkFlag WORK_START
			if(dspInfoClearFlag) {
				param.setResvEqpId("");
				param.setResvPortId("");
				param.setResvGrpId("");
				param.setResvOutCarrId("");
				param.setResvOutPortId("");
				param.setEqpChkYn("");
				param.setTrackInCnfmYn("");
				param.setRcpChkYn("");
				param.setSmplLotYn("");
				param.setSmplQty(0);
				param.setSmplWorkTyp("");
				param.setSelfInspInfoObjId("");
				param.setSelfInspPanelCnt(0);
				param.setSelfInspYn("");
				param.setBatchId("");
				param.setBatchSeq("");
			}
			
			// WHERE
			param.setpSiteId(siteId);
			param.setpLotId(lotId);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			
			// UPDATE
			updateCnt = wipStatMapper.updateWnWipStat(param);
			
			if(updateCnt > 0) {
				// CREATE HISTORY for Lot
				wipStatMapper.createWhWipStat(param.getObjId());
			}
			
			session.commit();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return updateCnt;
	}
	
	public int updateWorkStatusOnlyLotId(String siteId, String cid, String tid, String lotId, String mdfyUserId,
			String workStatCd, String crntEqpId, String crntPortId, boolean dspInfoClearFlag) throws Exception {

		int updateCnt = 0;

		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);

		try {
			WnWipStat param = new WnWipStat();

			// SET
			if (!"".equals(workStatCd)) {
				param.setWorkStatCd(workStatCd);
			}
			param.setMdfyUserId(mdfyUserId);
			param.setEvntNm(cid);
			param.setTid(tid);
			if (crntEqpId != null)
				param.setCrntEqpId(crntEqpId);
			if (crntPortId != null)
				param.setCrntPortId(crntPortId);

			// Clear reserve Info & checkFlag WORK_START
			if (dspInfoClearFlag) {
				param.setResvEqpId("");
				param.setResvPortId("");
				param.setResvGrpId("");
				param.setResvOutCarrId("");
				param.setResvOutPortId("");
				param.setEqpChkYn("");
				param.setTrackInCnfmYn("");
				param.setRcpChkYn("");
				param.setSmplLotYn("");
				param.setSmplQty(0);
				param.setSmplWorkTyp("");
				param.setSelfInspInfoObjId("");
				param.setSelfInspPanelCnt(0);
				param.setSelfInspYn("");
				param.setBatchId("");
				param.setBatchSeq("");
			}

			// WHERE
			param.setpSiteId(siteId);
			param.setpLotId(lotId);
			param.setpUseStatCd(IsUsable.Usable.name());

			// UPDATE
			updateCnt = wipStatMapper.updateWnWipStat(param);

			if (updateCnt > 0) {

				// CREATE HISTORY for Lot
				wipStatMapper.createWhWipStat(param.getObjId());
			}

			session.commit();

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}

		return updateCnt;
	}

	
	/**
	 * WN_WIP_STAT 
	 * EVENT_NM Update by CarrId Base
	 * @param siteId
	 * @param carrId
	 * @param mdfyUserId
	 * @param workStatCd
	 * @return
	 */
	public int updateEventNmByCarrId(String siteId, String cid, String tid, String carrId, String mdfyUserId) throws Exception{
		
		int updateCnt = 0;
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		try {
			WnWipStat param = new WnWipStat();
			// SET
			param.setMdfyUserId(mdfyUserId);
			param.setEvntNm(cid);
			param.setTid(tid);
			
			
			// WHERE
			param.setpSiteId(siteId);
			param.setpCarrId(carrId);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			
			List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStat(param);
			
			
			logger.info("WN_WIP_STAT SELECT_CNT >> " + wipStatList.size());
			
			for(WnWipStat w : wipStatList) {
				
				WnWipStat setParam = new WnWipStat();
				// SET
				setParam.setMdfyUserId(mdfyUserId);
				setParam.setEvntNm(cid);
				setParam.setTid(tid);
				
				// WHERE
				setParam.setpSiteId(w.getSiteId());
				setParam.setpCarrId(w.getCarrId());
				setParam.setpLotId(w.getLotId());
				setParam.setpUseStatCd(IsUsable.Usable.name());
				
				//Update & Insert History 
				if(wipStatMapper.updateWnWipStat(setParam) > 0) {
					logger.info("WN_WIP_STAT Updated!!!");
					// CREATE HISTORY
					wipStatMapper.createWhWipStat(w.getObjId());
				}
			}
			
			session.commit();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return updateCnt;
	}
	
	/**
	 * WN_WIP_STAT 
	 * EVENT_NM Update by LotId Base
	 * @param cid
	 * @param siteId
	 * @param lotId
	 * @param mdfyUserId
	 * @param workStatCd
	 * @return
	 */
	public int updateEventNmByLotId(String siteId, String cid, String tid, String lotId, String mdfyUserId) throws Exception{
		
		int updateCnt = 0;
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		try {
			WnWipStat param = new WnWipStat();
			
			// SET
			param.setMdfyUserId(mdfyUserId);
			param.setEvntNm(cid);
			param.setTid(tid);
			
			// WHERE
			param.setpSiteId(siteId);
			param.setpLotId(lotId);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			
			// UPDATE
			updateCnt = wipStatMapper.updateWnWipStat(param);
			
			if(updateCnt > 0) {
				
				// CREATE HISTORY for Lot
				wipStatMapper.createWhWipStat(param.getObjId());
				
				
				// Update Carr Info
				WnWipStat carrParam = new WnWipStat();
				
				// SET
				carrParam.setMdfyUserId(mdfyUserId);
				carrParam.setEvntNm(cid);
				carrParam.setTid(tid);
				
				// WHERE
				carrParam.setpSiteId(siteId);
				carrParam.setpLotId("-");
				carrParam.setpCarrId(param.getCarrId());
				carrParam.setpUseStatCd(IsUsable.Usable.name());
				
				if(!"-".equals(param.getCarrId())) {
					if(wipStatMapper.updateWnWipStat(carrParam) > 0) {
						// CREATE HISTORY for Carr
						wipStatMapper.createWhWipStat(carrParam.getObjId());
					}
				}
			}
			
			session.commit();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return updateCnt;
	}
	
	public int updateEventNmByLotCarrId(String siteId, String cid, String tid, String lotId, String carrId, String mdfyUserId) throws Exception{
		int updateCnt = 0;
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		try {
			WnWipStat carrParam = new WnWipStat();
			// SET
			carrParam.setMdfyUserId(mdfyUserId);
			carrParam.setEvntNm(cid);
			carrParam.setTid(tid);

			// WHERE
			carrParam.setpSiteId(siteId);
			carrParam.setpCarrId(carrId);
			carrParam.setpUseStatCd(IsUsable.Usable.name());
			carrParam.setpLotId("-");
			
			int updatedRows = wipStatMapper.updateWnWipStat(carrParam);
			//Update & Insert History CARR
			if(updatedRows > 0) {
				logger.info("WN_WIP_STAT Updated!!! by carrId");
				// CREATE HISTORY
				wipStatMapper.createWhWipStat(carrParam.getObjId());
			}
			
			
			WnWipStat lotParam = new WnWipStat();
			// SET
			lotParam.setMdfyUserId(mdfyUserId);
			lotParam.setEvntNm(cid);
			lotParam.setTid(tid);

			// WHERE
			lotParam.setpSiteId(siteId);
			lotParam.setpLotId(lotId);
			lotParam.setpUseStatCd(IsUsable.Usable.name());
			//Update & Insert History LOT
			if(wipStatMapper.updateWnWipStat(lotParam) > 0) {
				logger.info("WN_WIP_STAT Updated!!! by lotId ");
				// CREATE HISTORY
				wipStatMapper.createWhWipStat(lotParam.getObjId());
			}
			
			session.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		return updateCnt;
	}
	
	/**
	 * WN_WIP_STAT 
	 * EVENT_NM Update by LotId Base
	 * @param cid
	 * @param siteId
	 * @param lotId
	 * @param mdfyUserId
	 * @param workStatCd
	 * @return
	 */
	public int updateClearResvInfoForEcoLOT(String siteId, String cid, String tid, String lotId, String mdfyUserId) throws Exception{
		
		int updateCnt = 0;
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		try {
			WnWipStat param = new WnWipStat();
			
			// SET
			param.setMdfyUserId(mdfyUserId);
			param.setEvntNm(cid);
			param.setTid(tid);
			
			param.setResvEqpId("");
			param.setResvPortId("");
			param.setResvGrpId("");
			param.setResvOutCarrId("");
			param.setResvOutPortId("");
			param.setEqpChkYn("");
			param.setTrackInCnfmYn("");
			param.setRcpChkYn("");
			param.setSmplLotYn("");
			param.setSmplQty(0);
			param.setSmplWorkTyp("");
			param.setSelfInspInfoObjId("");
			param.setSelfInspPanelCnt(0);
			param.setSelfInspYn("");
			param.setBatchId("");
			param.setBatchSeq("");
			
			// WHERE
			param.setpSiteId(siteId);
			param.setpLotId(lotId);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			
			// UPDATE
			updateCnt = wipStatMapper.updateWnWipStat(param);
			
			if(updateCnt > 0) {
				
				// CREATE HISTORY for Lot
				wipStatMapper.createWhWipStat(param.getObjId());
			}
			
			session.commit();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return updateCnt;
	}
	
	public int updateSlotMapCheckYn(String siteId, String cid, String tid, String userId, String carrId, String slotMapChkYn)  throws Exception{
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		int resultCnt = -1;
		
		try {
			WnWipStat param = new WnWipStat();
			
			//SET
			param.setMdfyUserId(userId);
			param.setEvntNm(cid);
			param.setTid(tid);
//			param.setTrackInCnfmYn(trackInCnfmYn);
			
			//WHERE
			param.setpSiteId(siteId);
			param.setpLotId("-");
			param.setpCarrId(carrId);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			resultCnt = wipStatMapper.updateWnWipStat(param);
			logger.info("result CNT >>  " + resultCnt);
			logger.info("################");
			
			if(resultCnt > 0) {
				wipStatMapper.createWhWipStat(param.getObjId());
			}
			
			session.commit();
			logger.info("Commit");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		
		return resultCnt;
	}
	
	/**
	 * UPDATE WN_WIP_STAT
	 * TRACK_IN_CNFM_YN Update
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param lotId
	 * @param trackInCnfmYn
	 * @return
	 */
	public int updateTrackInCnfmYn(String siteId, String cid, String tid, String userId, String lotId, String trackInCnfmYn) throws Exception{
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		int resultCnt = -1;
		
		try {
			WnWipStat param = new WnWipStat();
			
			//SET
			param.setMdfyUserId(userId);
			param.setEvntNm(cid);
			param.setTid(tid);
			param.setTrackInCnfmYn(trackInCnfmYn);
			
			//WHERE
			param.setpSiteId(siteId);
			param.setpLotId(lotId);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			logger.info("################");
			logger.info("LOT ID : " + param.getpLotId());
			logger.info("SITE ID : " + param.getpSiteId());
			
			
			
			resultCnt = wipStatMapper.updateWnWipStat(param);
			logger.info("result CNT >>  " + resultCnt);
			logger.info("################");
			
			if(resultCnt > 0) {
				wipStatMapper.createWhWipStat(param.getObjId());
			}
			
			session.commit();
			logger.info("Commit");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		
		return resultCnt;
	}
	
	/**
	 * UPDATE WN_WIP_STAT
	 * RCP_CHK_YN Update
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param lotId
	 * @param rcpChkYn
	 * @return
	 */
	public int updateRcpChkYn(String siteId, String cid, String tid, String userId, String lotId, String rcpChkYn) throws Exception{
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		int resultCnt = -1;
		
		try {
			WnWipStat param = new WnWipStat();
			
			//SET
			param.setMdfyUserId(userId);
			param.setEvntNm(cid);
			param.setTid(tid);
			param.setRcpChkYn(rcpChkYn);
			
			
			//WHERE
			param.setpSiteId(siteId);
			param.setpLotId(lotId);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			resultCnt = wipStatMapper.updateWnWipStat(param);
			
			if(resultCnt > 0) {
				wipStatMapper.createWhWipStat(param.getObjId());
			}
			
			session.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		
		return resultCnt;
	}
	
	
	public int clearAllChkFlag(String siteId, String cid, String tid, String userId, String lotId) throws Exception{
		int updateCnt = 0;
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		try {
			WnWipStat param = new WnWipStat();
			
			// SET
			param.setMdfyUserId(userId);
			param.setEvntNm(cid);
			param.setTid(tid);
			
			param.setRcpChkYn("");
			param.setTrackInCnfmYn("");
			param.setEqpChkYn("");
			
			// WHERE
			param.setpSiteId(siteId);
			param.setpLotId(lotId);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			
			// UPDATE
			updateCnt = wipStatMapper.updateWnWipStat(param);
			
			if(updateCnt > 0) {
				
				// CREATE HISTORY for Lot
				wipStatMapper.createWhWipStat(param.getObjId());
				
				
				// Update Carr Info
				WnWipStat carrParam = new WnWipStat();
				
				// SET
				carrParam.setMdfyUserId(userId);
				carrParam.setEvntNm(cid);
				carrParam.setTid(tid);
				
				carrParam.setRcpChkYn("");
				carrParam.setTrackInCnfmYn("");
				carrParam.setEqpChkYn("");
				
				// WHERE
				carrParam.setpSiteId(siteId);
				carrParam.setpLotId("-");
				carrParam.setpCarrId(param.getCarrId());
				carrParam.setpUseStatCd(IsUsable.Usable.name());
				
				
				if(wipStatMapper.updateWnWipStat(carrParam) > 0) {
					// CREATE HISTORY for Carr
					wipStatMapper.createWhWipStat(carrParam.getObjId());
				}
			}
			
			session.commit();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return updateCnt;
		
	}
	
	/**
	 * UPDATE WN_WIP_STAT For Crrier Move Completed by CarrierID
	 * CRNT_EQP_ID, CRNT_PORT_ID
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param carrId
	 * @param userId
	 * @param crntEqpId
	 * @param crntPortId
	 * @return
	 */
	public int updateWipStatForMoveCompleteByCarrId(String siteId,String cid, String tid, String carrId, String userId, String crntEqpId, String crntPortId, String workStatCd) throws Exception{
		
		int updateCnt = 0;
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		try {
			WnWipStat param = new WnWipStat();
			
			// WHERE
			param.setpSiteId(siteId);
			param.setpCarrId(carrId);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStat(param);
			
			for(WnWipStat w : wipStatList) {
				WnWipStat setParam = new WnWipStat();
				// SET
				setParam.setCrntEqpId(crntEqpId);
				
				String portId = crntPortId;
				
				//STOCKER 또는 BUFFER인 경우
				if(crntEqpId.indexOf("ASTK") > -1 || crntEqpId.indexOf("BUF") > -1) {
					try {
						Integer.parseInt(portId);
						portId = crntEqpId + "_STORAGE";
					} catch(NumberFormatException ex) {
						// nothing
					}
				}
				
				setParam.setCrntPortId(portId);
				setParam.setWorkStatCd(workStatCd);

				setParam.setTrackInCnfmYn(""); // clear
				setParam.setRcpChkYn(""); // clear
				setParam.setEqpChkYn(""); // clear
				
				setParam.setMdfyUserId(userId);
				setParam.setEvntNm(cid);
				setParam.setTid(tid);
				
				// WHERE
				setParam.setpObjId(w.getObjId());
				
				//Update & Insert History 
				if(wipStatMapper.updateWnWipStat(setParam) > 0) {
					// CREATE HISTORY
					wipStatMapper.createWhWipStat(w.getObjId());
				}
			}
			
			session.commit();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return updateCnt;
	}
	
	/**
	 * updateWipStatForMoveCancelByCarrId
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
	public int updateWipStatForMoveCancelByCarrId(String siteId,String cid, String tid, String carrId, String userId, String crntEqpId, String crntPortId, String workStatCd) throws Exception{
		
		int updateCnt = 0;
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		try {
			WnWipStat param = new WnWipStat();
			
			// WHERE
			param.setpSiteId(siteId);
			param.setpCarrId(carrId);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStat(param);
			
			for(WnWipStat w : wipStatList) {
				WnWipStat setParam = new WnWipStat();
				// SET
				setParam.setCrntEqpId(crntEqpId);
				
				String portId = crntPortId;
				if(crntEqpId.indexOf("ASTK") > -1 || crntEqpId.indexOf("BUF") > -1) {
					try {
						Integer.parseInt(portId);
						portId = crntEqpId + "_STORAGE";
					} catch(NumberFormatException ex) {
						// nothing
					}
				}
				setParam.setCrntPortId(portId);
				
				setParam.setWorkStatCd(workStatCd);

				setParam.setResvEqpId("");
				setParam.setResvPortId("");
				setParam.setResvGrpId("");
				setParam.setResvOutCarrId("");
				setParam.setResvOutPortId("");
				
				
				setParam.setMdfyUserId(userId);
				setParam.setEvntNm(cid);
				setParam.setTid(tid);
				
				// WHERE
				setParam.setpObjId(w.getObjId());
				
				//Update & Insert History 
				if(wipStatMapper.updateWnWipStat(setParam) > 0) {
					// CREATE HISTORY
					wipStatMapper.createWhWipStat(w.getObjId());
				}
			}
			
			session.commit();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return updateCnt;
	}
	
	/**
	 * UPDATE WN_WIP_STAT For Crrier Location by CarrierID
	 * CRNT_EQP_ID, CRNT_PORT_ID
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param carrId
	 * @param userId
	 * @param crntEqpId
	 * @param crntPortId
	 * @return
	 */
	public int updateCurrentEqpPortByCarrId(String siteId,String cid, String tid, String carrId, String userId, String crntEqpId, String crntPortId) throws Exception{
		logger.info("updateCurrentEqpPortByCarrId CALL");
		int updateCnt = 0;
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		try {
			WnWipStat param = new WnWipStat();
			
			// WHERE
			param.setpSiteId(siteId);
			param.setpCarrId(carrId);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStat(param);
			
			for(WnWipStat w : wipStatList) {
				WnWipStat setParam = new WnWipStat();
				// SET
				setParam.setCrntEqpId(crntEqpId);
				
				String portId = crntPortId;
				//STOCKER 또는 BUFFER인 경우
				if(crntEqpId.indexOf("ASTK") > -1 || crntEqpId.indexOf("BUF") > -1) {
					try {
						Integer.parseInt(portId);
						portId = crntEqpId + "_STORAGE";
					} catch(NumberFormatException ex) {
						// nothing
					}
				}
				
				setParam.setCrntPortId(portId);
				
				setParam.setMdfyUserId(userId);
				setParam.setEvntNm(cid);
				setParam.setTid(tid);
				
				// WHERE
				setParam.setpObjId(w.getObjId());
				
				//Update & Insert History 
				if(wipStatMapper.updateWnWipStat(setParam) > 0) {
					// CREATE HISTORY
					wipStatMapper.createWhWipStat(w.getObjId());
				}	
			}
			
			session.commit();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return updateCnt;
	}
	
	
	public int updateCurrentEqpPortForCarrLogChg(String siteId,String cid, String tid, String carrId, String userId, String crntEqpId, String crntPortId, String moveStatCd) throws Exception{
		logger.info("updateCurrentEqpPortByCarrId CALL");
		int updateCnt = 0;
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		try {
			WnWipStat param = new WnWipStat();
			
			// WHERE
			param.setpSiteId(siteId);
			param.setpCarrId(carrId);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStat(param);
			
			for(WnWipStat w : wipStatList) {
				WnWipStat setParam = new WnWipStat();
				// SET
				setParam.setCrntEqpId(crntEqpId);
				
				String portId = crntPortId;
				//STOCKER 또는 BUFFER인 경우
				if(crntEqpId.indexOf("ASTK") > -1 || crntEqpId.indexOf("BUF") > -1) {
					try {
						Integer.parseInt(portId);
						portId = crntEqpId + "_STORAGE";
					} catch(NumberFormatException ex) {
						// nothing
					}
				}
				
				setParam.setCrntPortId(portId);
				setParam.setMoveStatCd(moveStatCd);
				
				setParam.setMdfyUserId(userId);
				setParam.setEvntNm(cid);
				setParam.setTid(tid);
				
				// WHERE
				setParam.setpObjId(w.getObjId());
				
				//Update & Insert History 
				if(wipStatMapper.updateWnWipStat(setParam) > 0) {
					// CREATE HISTORY
					wipStatMapper.createWhWipStat(w.getObjId());
				}	
			}
			
			session.commit();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return updateCnt;
	}
	
	/**
	 * UPDATE WN_WIP_STAT
	 * RTD Dspatching Response -> Batch
	 * Reserve Eqp, Port, Reserve Group ID
	 * Batch ID, Batch Sequence Update
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
	 */
	public int updateDspWorkRepBatch(String siteId,String cid, String tid, String carrId, String userId, String batchId, 
										String batchSeq, String resvEqpId, String resvPortId, String resvGrpId) throws Exception{
		logger.info("updateDspWorkRepBatch");
		int updateCnt = 0;
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		try {
			WnWipStat param = new WnWipStat();
			
			// WHERE
			param.setpSiteId(siteId);
			param.setpCarrId(carrId);
			param.setpUseStatCd(IsUsable.Usable.name());
			logger.info("carrId >> "+param.getpCarrId());
			List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStat(param);
			
			for(WnWipStat w : wipStatList) {
				WnWipStat setParam = new WnWipStat();
				// SET
				setParam.setBatchId(batchId);
				setParam.setBatchSeq(batchSeq);
				setParam.setResvEqpId(resvEqpId);
				setParam.setResvPortId(resvPortId);
				setParam.setResvGrpId(resvGrpId);
				
				setParam.setMdfyUserId(userId);
				setParam.setEvntNm(cid);
				setParam.setTid(tid);
				
				// WHERE
				setParam.setpObjId(w.getObjId());
				
				//Update & Insert History 
				if(wipStatMapper.updateWnWipStat(setParam) > 0) {
					// CREATE HISTORY
					wipStatMapper.createWhWipStat(w.getObjId());
				}	
			}
			
			session.commit();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return updateCnt;
	}
	
	/**
	 * UPDATE WN_WIP_STAT
	 * RTD Dspatching Response -> BothPort, InOut
	 * Reserve Eqp, Port, Reserve Group ID
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param carrId
	 * @param lotId
	 * @param userId
	 * @param batchId
	 * @param batchSeq
	 * @param resvEqpId
	 * @param resvPortId
	 * @param resvGrpId
	 * @return
	 */
	public int updateDspWorkRepNormal(String siteId,String cid, String tid, String carrId, String lotId, String userId, 
										String resvEqpId, String resvPortId, String resvGrpId, String resvOutCarrId, String resvOutPortId) throws Exception{
		int updateCnt = 0;
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		try {
			WnWipStat param = new WnWipStat();
			
			// WHERE
			param.setpSiteId(siteId);
			param.setpCarrId(carrId);
			param.setpLotId(lotId);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStatByLot(param);
			
			for(WnWipStat w : wipStatList) {
				WnWipStat setParam = new WnWipStat();
				// SET
				setParam.setResvEqpId(resvEqpId);
				setParam.setResvPortId(resvPortId);
				setParam.setResvGrpId(resvGrpId);
				setParam.setResvOutCarrId(resvOutCarrId);
				setParam.setResvOutPortId(resvOutPortId);
				
				setParam.setMdfyUserId(userId);
				setParam.setEvntNm(cid);
				setParam.setTid(tid);
				
				// WHERE
				setParam.setpObjId(w.getObjId());
				
				logger.info("##### UPDATE BEFORE ");
				//Update & Insert History 
				if(wipStatMapper.updateWnWipStat(setParam) > 0) {
					logger.info("##### UPDATE AFTER ");
					// CREATE HISTORY
					wipStatMapper.createWhWipStat(w.getObjId());
				}	
			}
			
			session.commit();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return updateCnt;
	}
	
	/**
	 * updateDspWorkRepECO
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param carrId
	 * @param userId
	 * @param resvEqpId
	 * @param resvPortId
	 * @param resvGrpId
	 * @param ecoId
	 * @return
	 * @throws Exception
	 */
	public int updateDspWorkRepECO(String siteId,String cid, String tid, String lotId, String carrId, String userId, 
			String resvEqpId, String resvPortId, String resvGrpId, String ecoId) throws Exception{
		
		int updateCnt = 0;
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		try {

			WnWipStat setParam = new WnWipStat();
			
			// SET
			setParam.setResvEqpId(resvEqpId);
			setParam.setResvPortId(resvPortId);
			setParam.setResvGrpId(resvGrpId);
			
			
			setParam.setMdfyUserId(userId);
			setParam.setEvntNm(cid);
			setParam.setTid(tid);
			
			// WHERE
			setParam.setpSiteId(siteId);
			setParam.setpCarrId(carrId);
			setParam.setpLotId(lotId);
			setParam.setpUseStatCd(IsUsable.Usable.name());
			
			//Update & Insert History 
			if(wipStatMapper.updateWnWipStat(setParam) > 0) {
				logger.info("WN_WIP_STAT Updated!!!");
				// CREATE HISTORY
				wipStatMapper.createWhWipStat(setParam.getObjId());
			}
			
			session.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return updateCnt;
	}
	
	/**
	 * WN_WIP_STAT
	 * SELF_INSP_YN, SELF_INSP_PANEL_CNT, SELF_INSP_INFO_OBJ_ID
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param lotId
	 * @param selfInspYn
	 * @param selfInspPanelCnt
	 * @param selfInspObjId
	 * @return
	 */
	public int updateSelfInspInfo(String siteId,String cid, String tid, String userId, String lotId, String selfInspYn, String selfInspPanelCnt, String selfInspObjId) throws Exception{
		int updateCnt = 0;
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		try {
			logger.info("WipStatDAO.updateSelfInspInfo");
			WnWipStat param = new WnWipStat();
			
			// SET
			param.setMdfyUserId(userId);
			param.setEvntNm(cid);
			param.setTid(tid);
			param.setSelfInspYn(selfInspYn);
			param.setSelfInspInfoObjId(selfInspObjId);
			param.setSelfInspPanelCnt(Integer.valueOf(selfInspPanelCnt));
			
			logger.info("########### SELF INSP VALUE ############");
//			logger.info("########### SelfInspYn > " + param.getSelfInspYn());
//			logger.info("########### SelfInspInfoObjId > " + param.getSelfInspInfoObjId());
//			logger.info("########### SelfInspPanelCnt > " + param.getSelfInspPanelCnt());
			
			// WHERE
			param.setpSiteId(siteId);
			param.setpLotId(lotId);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			// UPDATE
			updateCnt = wipStatMapper.updateWnWipStat(param);
			logger.info("updateCnt >> " + updateCnt);
			
			if(updateCnt > 0 ) {
				
				// CREATE HISTORY for Lot
				wipStatMapper.createWhWipStat(param.getObjId());
				
				// Assigned CARR Exist
				if(!"-".equals(param.getCarrId())) {
					// Update Carr Info
					WnWipStat carrParam = new WnWipStat();
					
					// SET
					carrParam.setEvntNm(cid);
					carrParam.setTid(tid);
					carrParam.setSelfInspYn(selfInspYn);
					carrParam.setSelfInspInfoObjId(selfInspObjId);
					carrParam.setSelfInspPanelCnt(Integer.valueOf(selfInspPanelCnt));
					
					// WHERE
					carrParam.setpSiteId(siteId);
					carrParam.setpLotId("-");
					carrParam.setpCarrId(param.getCarrId());
					carrParam.setpUseStatCd(IsUsable.Usable.name());
					
					if(wipStatMapper.updateWnWipStat(carrParam) > 0) {
						// CREATE HISTORY for Carr
						wipStatMapper.createWhWipStat(carrParam.getObjId());
					}
				}
			}
			
			session.commit();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return updateCnt;
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
	 */
	public int updateSampleInfo(String siteId,String cid, String tid, String userId, String lotId, String smplLotYn, String smplWorkTyp, String smplQty) throws Exception{
		int updateCnt = 0;
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		try {
			logger.info("WipStatDAO.updateSampleInfo");
			WnWipStat param = new WnWipStat();
			
			// SET
			param.setMdfyUserId(userId);
			param.setEvntNm(cid);
			param.setTid(tid);
			param.setSmplLotYn(smplLotYn);
			param.setSmplWorkTyp(smplWorkTyp);
			param.setSmplQty(Integer.parseInt(smplQty));
			
			// WHERE
			param.setpSiteId(siteId);
			param.setpLotId(lotId);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			// UPDATE
			updateCnt = wipStatMapper.updateWnWipStat(param);
			logger.info("updateCnt >> " + updateCnt);
			
			if(updateCnt > 0 ) {
				// CREATE HISTORY for Lot
				wipStatMapper.createWhWipStat(param.getObjId());
				
				// Assigned CARR Exist
				if(!"-".equals(param.getCarrId())) {
					// Update Carr Info
					WnWipStat carrParam = new WnWipStat();
					
					// SET
					carrParam.setEvntNm(cid);
					carrParam.setTid(tid);
					carrParam.setSmplLotYn(smplLotYn);
					carrParam.setSmplWorkTyp(smplWorkTyp);
					carrParam.setSmplQty(Integer.parseInt(smplQty));
					
					// WHERE
					carrParam.setpSiteId(siteId);
					carrParam.setpLotId("-");
					carrParam.setpCarrId(param.getCarrId());
					carrParam.setpUseStatCd(IsUsable.Usable.name());
					
					if(wipStatMapper.updateWnWipStat(carrParam) > 0) {
						// CREATE HISTORY for Carr
						wipStatMapper.createWhWipStat(carrParam.getObjId());
					}
				}
			}
			session.commit();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return updateCnt;
	}
	
	
	/**
	 * Init WIP_STAT
	 * @param siteId
	 * @param cid
	 * @param carrId
	 * @return
	 */
	public int updateInitWipStat(String siteId, String cid, String carrId) throws Exception{
		int updateCnt = 0;

		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		try {

			WnWipStat param = new WnWipStat();
			
			// WHERE
			param.setpSiteId(siteId);
			param.setpCarrId(carrId);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStat(param);
			
			for(WnWipStat w : wipStatList) {
				
				WnWipStat setParam = new WnWipStat();		
			
			
				// SET
				setParam.setWorkStatCd("Standby");
				setParam.setDtlWorkStatCd("");
				setParam.setResvEqpId("");
				setParam.setResvPortId("");
				setParam.setResvGrpId("");
				setParam.setResvOutCarrId("");
				setParam.setResvOutPortId("");
				setParam.setEqpChkYn("");
				setParam.setRcpChkYn("");
				setParam.setTrackInCnfmYn("");
				setParam.setSelfInspInfoObjId("");
				setParam.setSelfInspPanelCnt(0);
				setParam.setSelfInspYn("");
				setParam.setSmplLotYn("");
				setParam.setSmplQty(0);
				setParam.setSmplWorkTyp("");
				setParam.setBatchId("");
				setParam.setBatchSeq("");
				setParam.setMdfyUserId("WFS");
				setParam.setEvntNm(cid);
				
				// WHERE
				setParam.setpSiteId(w.getSiteId());
				setParam.setpCarrId(w.getCarrId());
				setParam.setpLotId(w.getLotId());
				setParam.setpUseStatCd(IsUsable.Usable.name());
				
	
				if( wipStatMapper.updateWnWipStat(setParam) > 0) {
					
					wipStatMapper.createWhWipStat(w.getObjId());
				}
			}
			
			session.commit();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return updateCnt;
	}
	
	public int updateInitWipStatByCarrIdLotId(String siteId, String cid, String carrId, String lotId) throws Exception{
		int updateCnt = 0;

		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		try {
			if(!carrId.equals("-")) {			
				WnWipStat carrParam = new WnWipStat();
				
				// WHERE
				carrParam.setpSiteId(siteId);
				carrParam.setpCarrId(carrId);
				carrParam.setpUseStatCd(IsUsable.Usable.name());
				
				List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStat(carrParam);
				
				for(WnWipStat w : wipStatList) {
					logger.info("-------CarrID------------"+lotId +" / " + carrId);
					WnWipStat setParam = new WnWipStat();		
				
					// SET
					setParam.setWorkStatCd("Standby");
					setParam.setDtlWorkStatCd("");
					setParam.setResvEqpId("");
					setParam.setResvPortId("");
					setParam.setResvGrpId("");
					setParam.setResvOutCarrId("");
					setParam.setResvOutPortId("");
					setParam.setEqpChkYn("");
					setParam.setRcpChkYn("");
					setParam.setTrackInCnfmYn("");
					setParam.setSelfInspInfoObjId("");
					setParam.setSelfInspPanelCnt(0);
					setParam.setSelfInspYn("");
					setParam.setSmplLotYn("");
					setParam.setSmplQty(0);
					setParam.setSmplWorkTyp("");
					setParam.setBatchId("");
					setParam.setBatchSeq("");
					setParam.setMdfyUserId("WFS");
					setParam.setEvntNm(cid);
					
					// WHERE
					setParam.setpSiteId(w.getSiteId());
					setParam.setpCarrId(w.getCarrId());
					setParam.setpLotId(w.getLotId());
					setParam.setpUseStatCd(IsUsable.Usable.name());
					
					//int updatedRows = wipStatMapper.updateWnWipStat(carrParam);
	
					if( wipStatMapper.updateWnWipStat(setParam) > 0) {
						logger.info("WN_WIP_STAT Updated!!! by carrId ");
						wipStatMapper.createWhWipStat(setParam.getObjId());
					}
				}
			}
			
			if(!lotId.equals("-") && carrId.equals("-")) {
				logger.info("-------LotID------------"+lotId +" / " + carrId);
				
				WnWipStat lotParam = new WnWipStat(); //For deassigned Lot
	
				// WHERE			
				lotParam.setpSiteId(siteId);
				lotParam.setpLotId(lotId);
				lotParam.setpCarrId("-");
				lotParam.setpUseStatCd(IsUsable.Usable.name());
				
							
				lotParam.setWorkStatCd("Standby");
				lotParam.setDtlWorkStatCd("");
				lotParam.setResvEqpId("");
				lotParam.setResvPortId("");
				lotParam.setResvGrpId("");
				lotParam.setResvOutCarrId("");
				lotParam.setResvOutPortId("");
				lotParam.setEqpChkYn("");
				lotParam.setRcpChkYn("");
				lotParam.setTrackInCnfmYn("");
				lotParam.setSelfInspInfoObjId("");
				lotParam.setSelfInspPanelCnt(0);
				lotParam.setSelfInspYn("");
				lotParam.setSmplLotYn("");
				lotParam.setSmplQty(0);
				lotParam.setSmplWorkTyp("");
				lotParam.setBatchId("");
				lotParam.setBatchSeq("");
				lotParam.setMdfyUserId("WFS");
				lotParam.setEvntNm(cid);
				
				//Update & Insert History LOT
				if(wipStatMapper.updateWnWipStat(lotParam) > 0) {
					logger.info("WN_WIP_STAT Updated!!! by lotId ");
					// CREATE HISTORY
					wipStatMapper.createWhWipStat(lotParam.getObjId());
				}
			}
			session.commit();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return updateCnt;
	}
	
	/**
	 * Manual Loading 여부 
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param carrId
	 * @param manlLdngYn
	 * @return
	 * @throws Exception
	 */
	public int updateManlLdngYnInfo(String siteId,String cid, String tid, String userId, String carrId, String manlLdngYn) throws Exception{
		int updateCnt = 0;
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WipStatMapper wipStatMapper = session.getMapper(WipStatMapper.class);
		
		try {
			logger.info("WipStatDAO.updateManlLdngYnInfo");
			WnWipStat param = new WnWipStat();
			
			// SET
			param.setMdfyUserId(userId);
			param.setEvntNm(cid);
			param.setTid(tid);
			param.setManlLdngYn(manlLdngYn);			
			// WHERE
			param.setpSiteId(siteId);
			param.setpLotId("-");
			param.setpCarrId(carrId);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			// UPDATE
			updateCnt = wipStatMapper.updateWnWipStat(param);
			logger.info("updateCnt >> " + updateCnt);
			
			if(updateCnt > 0 ) {
				
				// CREATE HISTORY for Lot
				wipStatMapper.createWhWipStat(param.getObjId());
			}
			
			session.commit();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return updateCnt;
	}
}
