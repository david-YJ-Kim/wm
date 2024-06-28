package com.abs.wfs.workman.dao.query.service;

import com.abs.wfs.workman.dao.query.dao.CommonDAO;
import com.abs.wfs.workman.dao.query.model.QueryEqpVO;
import com.abs.wfs.workman.dao.query.model.QueryLotVO;
import com.abs.wfs.workman.dao.query.model.QueryPortVO;

public class WfsCommonQueryService {
	public WfsCommonQueryService() {
		
	}
	
	/**
	 * get Transaction ID
	 * @return
	 * @throws Exception 
	 */
	public String getID(String name) throws Exception {
		try {
			return CommonDAO.getInstance().getID(name);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * UNLOAD_COMPLETED
	 * UPDATE TN_POS_PORT
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param statCd
	 * @param trsfStatCd
	 * @param eqpId
	 * @param portId
	 * @return
	 * @throws Exception
	 */
	public int updatePortUnloadCompleted(String siteId, String cid, String tid, String userId, String statCd, String trsfStatCd, String eqpId, String portId) throws Exception {
		try {
			return CommonDAO.getInstance().updateUnloadComplete(siteId, cid, tid, userId, statCd, trsfStatCd, eqpId, portId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public int updatePortStatAndCarrier(String siteId, String cid, String tid, String userId, String efemCommStateCd, String efemStateCd, String efemToolStateCd, String statCd, String trsfStatCd, String acesModeCd, String ctrlModeCd, String carrId, String eqpId, String portId) throws Exception {
		try {
			return CommonDAO.getInstance().updatePortStatAndCarrier(siteId, cid, tid, userId, efemCommStateCd, efemStateCd, efemToolStateCd, statCd, trsfStatCd, acesModeCd, ctrlModeCd, carrId, eqpId, portId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * UPDATE TN_POS_PORT
	 * CARR_ID
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param carrierId
	 * @param eqpId
	 * @param portId
	 * @return
	 * @throws Exception 
	 */
	public int updatePortCarrier(String siteId, String cid, String tid, String userId, String carrierId, String eqpId, String portId) throws Exception {
		try {
			return CommonDAO.getInstance().updatePortCarrier(siteId, cid, tid, userId, carrierId, eqpId, portId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * UPDATE TN_POS_PORT
	 * STAT_CD, TRSF_STAT_CD
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param statCd
	 * @param trsfStatCd
	 * @param acesModeCd
	 * @param eqpId
	 * @param portId
	 * @return
	 * @throws Exception 
	 */
	public int updatePortStatCd(String siteId, String cid, String tid, String userId, String statCd, String trsfStatCd, String acesModeCd, String ctrlModeCd, String eqpId, String portId) throws Exception {
		try {
			return CommonDAO.getInstance().updatePortStatCd(siteId, cid, tid, userId, statCd, trsfStatCd, acesModeCd, ctrlModeCd, eqpId, portId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * Update TN_POS_CARRIER.MOVE_STAT_CD
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param carrId
	 * @param moveStatCd
	 * @return
	 * @throws Exception 
	 */
	public int updateCarrierMoveSatCd(String siteId, String cid, String tid, String userId, String carrId, String moveStatCd) throws Exception {
		try {
			return CommonDAO.getInstance().updateCarrierMoveStatCd(siteId, cid, tid, userId, carrId, moveStatCd);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * getQueryEqpVO
	 * @param siteId
	 * @param eqpId
	 * @return
	 * @throws Exception 
	 */
	public QueryEqpVO getQueryEqpVO(String siteId, String eqpId) throws Exception {
		try {
			return CommonDAO.getInstance().getQueryEqp(siteId, eqpId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * getQueryPortVO
	 * @param siteId
	 * @param eqpId
	 * @param portId
	 * @return
	 * @throws Exception 
	 */
	public QueryPortVO getQueryPortVO(String siteId, String eqpId, String portId) throws Exception {
		try {
			return CommonDAO.getInstance().getQueryPort(siteId, eqpId, portId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * getQueryLotVO
	 * @param siteId
	 * @param lotId
	 * @return
	 * @throws Exception 
	 */
	public QueryLotVO getQueryLotVO(String siteId, String lotId) throws Exception {
		try {
			return CommonDAO.getInstance().getQueryLot(siteId, lotId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * insert WH_ERROR_INFO
	 * @param siteId
	 * @param msgId
	 * @param msgCtnsCm
	 * @param workStatCd
	 * @param lotId
	 * @param carrId
	 * @param eqpId
	 * @param portId
	 * @param errCd
	 * @param errCm
	 * @param cid
	 * @param userId
	 * @param tid
	 * @return
	 * @throws Exception 
	 */
	public int insertWhErrorInfo(String siteId, String msgId, String msgCtnsCm, String workStatCd, String lotId, String carrId, String eqpId, String portId, String errCd, String errCm, String cid, String userId, String tid) throws Exception {
		try {
			return CommonDAO.getInstance().insertWhErrorInfo(siteId, msgId, msgCtnsCm, workStatCd, lotId, carrId, eqpId, portId, errCd, errCm, cid, userId, tid);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * insert WN_MTRL_USAGE_INFO
	 * @param siteId
	 * @param workId
	 * @param eqpId
	 * @param subEqpId
	 * @param lotId
	 * @param specId
	 * @param specTyp
	 * @param specUseCnt
	 * @param specLimitCnt
	 * @param cid
	 * @param userId
	 * @param tid
	 * @return
	 * @throws Exception 
	 */
	public int insertWnMtrlUsageInfo(String siteId, String workId, String eqpId, String subEqpId, String lotId, String specId, String specTyp, String specUseCnt, String specLimitCnt, String cid, String userId, String tid) throws Exception {
		try {
			return CommonDAO.getInstance().insertWnMtrlUsageInfo(siteId, workId, eqpId, subEqpId, lotId, specId, specTyp, specUseCnt, specLimitCnt, cid, userId, tid);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public int insertWnMtrlUsageInfoDicing(String siteId, String workId, String eqpId, String subEqpId, String lotId, String specId, String specTyp, String specUseCnt, String subSpecUseCnt, String specLimitCnt, String cid, String userId, String tid) throws Exception {
		try {
			return CommonDAO.getInstance().insertWnMtrlUsageInfoDicing(siteId, workId, eqpId, subEqpId, lotId, specId, specTyp, specUseCnt, subSpecUseCnt, specLimitCnt, cid, userId, tid);
		} catch (Exception e) {
			throw e;
		}
	}
	
}
