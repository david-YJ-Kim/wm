package com.abs.wfs.workman.dao.query.service;

import com.abs.wfs.workman.dao.query.dao.CommonDAO;
import com.abs.wfs.workman.dao.query.model.QueryEqpVO;
import com.abs.wfs.workman.dao.query.model.QueryLotVO;
import com.abs.wfs.workman.dao.query.model.QueryPortVO;
import com.abs.wfs.workman.dao.query.service.vo.UpdatePortCarrierRequestVo;
import com.abs.wfs.workman.dao.query.service.vo.UpdatePortStatAndCarrierReqVo;
import com.abs.wfs.workman.dao.query.service.vo.UpdatePortStatCdRequestVo;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class WfsCommonQueryService {

	
	@Autowired
	CommonDAO commonDAO;
	
	/**
	 * get Transaction ID
	 * @return
	 * @throws Exception 
	 */
	public String getID(String name) throws Exception {
		try {
			return this.commonDAO.getID(name);
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
			return this.commonDAO.updateUnloadComplete(siteId, cid, tid, userId, statCd, trsfStatCd, eqpId, portId);
		} catch (Exception e) {
			throw e;
		}
	}


	public int updatePortStatAndCarrier(UpdatePortStatAndCarrierReqVo vo) throws Exception {
		try {
			return this.commonDAO.updatePortStatAndCarrier(vo.getSiteId(), vo.getCid(), vo.getTid(), vo.getUserId(), vo.getEfemCommStateCd(),
					vo.getEfemStateCd(), vo.getEfemToolStateCd(), vo.getStatCd(), vo.getTrsfStatCd(), vo.getAcesModeCd(),
					vo.getCtrlModeCd(), vo.getCarrId(), vo.getEqpId(), vo.getPortId());
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * UPDATE TN_POS_PORT
	 * CARR_ID
	 * @return
	 * @throws Exception
	 */
	public int updatePortCarrier(UpdatePortCarrierRequestVo vo) throws Exception {
		try {
			return this.commonDAO.updatePortCarrier(vo.getSiteId(), vo.getCid(), vo.getTid(), vo.getUserId(),
													vo.getCarrierId(), vo.getEqpId(), vo.getPortId());
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * UPDATE TN_POS_PORT
	 * STAT_CD, TRSF_STAT_CD
	 * @return
	 * @throws Exception
	 */
	public int updatePortStatCd(UpdatePortStatCdRequestVo vo) throws Exception {
		try {
			return this.commonDAO.updatePortStatCd(vo.getSiteId(), vo.getCid(), vo.getTid(), vo.getUserId(), vo.getSiteId(),
													vo.getTrsfStatCd(), vo.getAcesModeCd(), vo.getCtrlModeCd(),
													vo.getEqpId(), vo.getPortId());
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
			return this.commonDAO.updateCarrierMoveStatCd(siteId, cid, tid, userId, carrId, moveStatCd);
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
			return this.commonDAO.getQueryEqp(siteId, eqpId);
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
			return this.commonDAO.getQueryPort(siteId, eqpId, portId);
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
			return this.commonDAO.getQueryLot(siteId, lotId);
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
			return this.commonDAO.insertWhErrorInfo(siteId, msgId, msgCtnsCm, workStatCd, lotId, carrId, eqpId, portId, errCd, errCm, cid, userId, tid);
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
			return this.commonDAO.insertWnMtrlUsageInfo(siteId, workId, eqpId, subEqpId, lotId, specId, specTyp, specUseCnt, specLimitCnt, cid, userId, tid);
		} catch (Exception e) {
			throw e;
		}
	}

	public int insertWnMtrlUsageInfoDicing(String siteId, String workId, String eqpId, String subEqpId, String lotId, String specId, String specTyp, String specUseCnt, String subSpecUseCnt, String specLimitCnt, String cid, String userId, String tid) throws Exception {
		try {
			return this.commonDAO.insertWnMtrlUsageInfoDicing(siteId, workId, eqpId, subEqpId, lotId, specId, specTyp, specUseCnt, subSpecUseCnt, specLimitCnt, cid, userId, tid);
		} catch (Exception e) {
			throw e;
		}
	}
	
}
