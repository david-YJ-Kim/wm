package com.abs.wfs.workman.dao.query.dao;

import java.util.HashMap;
import java.util.Map;

import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abs.wfs.workman.dao.query.mapper.EqpMapper;
import com.abs.wfs.workman.dao.query.mapper.WfsMapper;
import com.abs.wfs.workman.dao.query.model.QueryEqpVO;
import com.abs.wfs.workman.dao.query.model.QueryLotVO;
import com.abs.wfs.workman.dao.query.model.QueryPortVO;
import com.abs.wfs.workman.dao.query.model.TnPosCarrier;
import com.abs.wfs.workman.dao.query.model.TnPosPort;
import com.abs.wfs.workman.dao.query.model.WhErrorInfo;
import com.abs.wfs.workman.dao.query.model.WnMtrlUsageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CommonDAO {
	
	@Autowired
	WfsMapper wfsMapper;


	@Autowired
	EqpMapper eqpMapper;

	private static CommonDAO instance;
	private static final Logger logger = LoggerFactory.getLogger(CommonDAO.class);
	
	public static CommonDAO getInstance() {
		if(instance == null) instance = new CommonDAO();
		return instance;
	}
	
	private CommonDAO() {
		
	}
	
	/**
	 * get Generate ID
	 * @param name
	 * @return
	 */
	public String getID(String name) throws Exception{
		String returnVal = null;
		
		
		try {
			returnVal = wfsMapper.getID(name);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return returnVal;
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
	public int updateUnloadComplete(String siteId, String cid, String tid, String userId, String statCd, String trsfStatCd, String eqpId, String portId) throws Exception{
		
		int resultVal = -1;
		

		try {
			TnPosPort param = new TnPosPort();
			
			//SET
			param.setMdfyUserId(userId);
			param.setTid(tid);
			param.setEvntNm(cid);
			
			param.setCarrId(""); //UNLOAD_COMP clear
			
			if(!"".equals(statCd))param.setStatCd(statCd);
			if(!"".equals(trsfStatCd)) param.setTrsfStatCd(trsfStatCd);
			
			//WHERE
			param.setpUseStatCd(UseStatCd.Usable.name());
			param.setpSiteId(siteId);
			param.setpEqpId(eqpId);
			param.setpPortId(portId);
			
			resultVal = eqpMapper.updateTnPosPort(param);
			
			if(resultVal > 0) {
				eqpMapper.createThPosPort(param.getObjId());
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return resultVal;
		
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
	 */
	public int updatePortCarrier(String siteId, String cid, String tid, String userId, String carrierId, String eqpId, String portId) throws Exception{
		
		int resultVal = -1;
		
		
		try {
			TnPosPort param = new TnPosPort();
			
			//SET
			param.setMdfyUserId(userId);
			param.setTid(tid);
			param.setEvntNm(cid);
			param.setCarrId(carrierId);
			
			//WHERE
			param.setpUseStatCd(UseStatCd.Usable.name());
			param.setpSiteId(siteId);
			param.setpEqpId(eqpId);
			param.setpPortId(portId);
			
			resultVal = eqpMapper.updateTnPosPort(param);
			
			if(resultVal > 0) {
				eqpMapper.createThPosPort(param.getObjId());
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		
		return resultVal;
		
	}
	
	/**
	 * UPDATE TN_POS_PORT
	 * STAT_CD, CARR_ID
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param statCd
	 * @param trsfStatCd
	 * @param acesModeCd
	 * @param ctrlModeCd
	 * @param carrId
	 * @param eqpId
	 * @param portId
	 * @return
	 * @throws Exception
	 */
	public int updatePortStatAndCarrier(String siteId, String cid, String tid, String userId, String efemCommStateCd, String efemStateCd, String efemToolStateCd, String statCd, String trsfStatCd, 
			String acesModeCd, String ctrlModeCd, String carrId, String eqpId, String portId) throws Exception{

		int resultVal = -1;
		
		
		try {
		TnPosPort param = new TnPosPort();
		
		//SET
		param.setMdfyUserId(userId);
		param.setTid(tid);
		param.setEvntNm(cid);
				
		if(!"".equals(efemCommStateCd)) param.setEfemCtrlModeCd(efemCommStateCd);
		if(!"".equals(efemStateCd)) param.setEfemStatCd(efemStateCd);
		
		if("Empty".equals(statCd)) {
			param.setTrsfStatCd("ReadyToLoad"); //ReadyToLoad
			param.setCarrId(""); //clear
			
		} else {
			if(!"".equals(trsfStatCd)) param.setTrsfStatCd(trsfStatCd);
			if(!"".equals(carrId)) param.setCarrId(carrId);
		}
		
		if(!"".equals(statCd))param.setStatCd(statCd);
		if(!"".equals(acesModeCd)) param.setAcesModeCd(acesModeCd);
		if(!"".equals(ctrlModeCd)) param.setCtrlModeCd(ctrlModeCd);
		
		//WHERE
		param.setpUseStatCd(UseStatCd.Usable.name());
		param.setpSiteId(siteId);
		param.setpEqpId(eqpId);
		param.setpPortId(portId);
		
		resultVal = eqpMapper.updateTnPosPort(param);
		
		if(resultVal > 0) {
			eqpMapper.createThPosPort(param.getObjId());
		}
		
		
		} catch (Exception e) {
		logger.error(e.getMessage());
		e.printStackTrace();
		throw e;
		} 
		
		return resultVal;
		
	}
	
	/**
	 * UPDATE TN_POS_PORT
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param statCd
	 * @param trsfStatCd
	 * @param acesModeCd
	 * @param ctrlModeCd
	 * @param eqpId
	 * @param portId
	 * @return
	 */
	public int updatePortStatCd(String siteId, String cid, String tid, String userId, String statCd, String trsfStatCd, 
									String acesModeCd, String ctrlModeCd, String eqpId, String portId) throws Exception{
		
		int resultVal = -1;
		
		
		try {
			TnPosPort param = new TnPosPort();
			
			//SET
			param.setMdfyUserId(userId);
			param.setTid(tid);
			param.setEvntNm(cid);
			
			if("Empty".equals(statCd)) {
				param.setTrsfStatCd("ReadyToLoad"); //ReadyToLoad
				param.setCarrId(""); //clear
			} else {
				if(!"".equals(trsfStatCd)) param.setTrsfStatCd(trsfStatCd);
			}
			if(!"".equals(statCd))param.setStatCd(statCd);
			if(!"".equals(acesModeCd)) param.setAcesModeCd(acesModeCd);
			if(!"".equals(acesModeCd)) param.setAcesModeCd(acesModeCd);
			if(!"".equals(ctrlModeCd)) param.setCtrlModeCd(ctrlModeCd);
			
			//WHERE
			param.setpUseStatCd(UseStatCd.Usable.name());
			param.setpSiteId(siteId);
			param.setpEqpId(eqpId);
			param.setpPortId(portId);
			
			resultVal = eqpMapper.updateTnPosPort(param);
			
			if(resultVal > 0) {
				eqpMapper.createThPosPort(param.getObjId());
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		
		return resultVal;
		
	}
	
//	public int updatePortStatCdForInit(String siteId, String cid, String tid, String userId, String statCd, String trsfStatCd, 
//			String acesModeCd, String ctrlModeCd, String eqpId, String portId) throws Exception{
//
//		int resultVal = -1;
//		
//		EqpMapper mapper = session.getMapper(EqpMapper.class);
//		
//		try {
//		TnPosPort param = new TnPosPort();
//		
//		//SET
//		param.setMdfyUserId(userId);
//		param.setTid(tid);
//		param.setEvntNm(cid);
//		
//		
//		
//		
//		if("Empty".equals(statCd)) {
//			if(!"".equals(statCd))param.setStatCd(statCd);
//			param.setTid("ReadyToLoad"); //ReadyToLoad
//			param.setCarrId(""); //clear
//		} else {
//			if(!"".equals(statCd))param.setStatCd(statCd);
//			if(!"".equals(trsfStatCd)) param.setTrsfStatCd(trsfStatCd);
//			if(!"".equals(acesModeCd)) param.setAcesModeCd(acesModeCd);
//			if(!"".equals(ctrlModeCd)) param.setCtrlModeCd(ctrlModeCd);
//		}
//
//		
//		//WHERE
//		param.setpUseStatCd(UseStatCd.Usable.name());
//		param.setpSiteId(siteId);
//		param.setpEqpId(eqpId);
//		param.setpPortId(portId);
//		
//		resultVal = mapper.updateTnPosPort(param);
//		
//		if(resultVal > 0) {
//		mapper.createThPosPort(param.getObjId());
//		}
//		
//		
//		} catch (Exception e) {
//		logger.error(e.getMessage());
//		e.printStackTrace();
//		throw e;
//		} finally {
//		
//		session.close();
//		}
//		
//		return resultVal;
//		
//	}
	
	public int updateEqpStatCd(String siteId, String cid, String tid, String userId) throws Exception{
		int resultVal = -1;
		
//		EqpMapper mapper = session.getMapper(EqpMapper.class);
		
		try {
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return resultVal;
	}
	
	/**
	 * TN_POS_CARRIER MOVE_STAT_CD Update
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param carrId
	 * @param moveStatCd
	 * @return
	 */
	public int updateCarrierMoveStatCd(String siteId, String cid, String tid, String userId, String carrId, String moveStatCd) throws Exception{
		int resultVal = -1;
		
		
		
		
		try {
			TnPosCarrier param = new TnPosCarrier();
			
			// SET
			param.setMdfyUserId(userId);
			param.setMoveStatCd(moveStatCd);
			param.setEvntNm(cid);
			param.setTid(tid);
			
			param.setSiteId(siteId);
			param.setUseStatCd(UseStatCd.Usable.name());
			param.setCarrId(carrId);
			
			resultVal = wfsMapper.updateTnPosCarrierMoveStatCd(param);
			
			if(resultVal > 0) {
				wfsMapper.insertThPosCarrier(param.getObjId());
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return resultVal;
	}
	
	
	public Map<String, String> getEqp(String siteId, String eqpId) throws Exception{
		Map<String, String> returnVal = null;
		
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("eqpId", eqpId);
			param.put("siteId", siteId);
			param.put("useStatCd", UseStatCd.Usable.name());
			
			returnVal = eqpMapper.selectEqp(param);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return returnVal;
	}
	
	public QueryEqpVO getQueryEqp(String siteId, String eqpId) throws Exception{
		
		
		QueryEqpVO result = null;
		
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("eqpId", eqpId);
			param.put("siteId", siteId);
			param.put("useStatCd", UseStatCd.Usable.name());
			result = eqpMapper.selectQueryEqpVO(param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return result;
	}
	
	public Map<String, String> getPort(String siteId, String eqpId, String portId) throws Exception{
		Map<String, String> returnVal = null;
		
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("eqpId", eqpId);
			param.put("portId", portId);
			param.put("siteId", siteId);
			param.put("useStatCd", UseStatCd.Usable.name());
			
			returnVal = eqpMapper.selectPort(param);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return returnVal;
	}
	
	public QueryPortVO getQueryPort(String siteId, String eqpId, String portId) throws Exception{
		
		
		QueryPortVO result = null;
		
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("eqpId", eqpId);
			param.put("portId", portId);
			param.put("siteId", siteId);
			param.put("useStatCd", UseStatCd.Usable.name());
			
			result = eqpMapper.selectQueryPortVO(param);
			
			logger.info("QueryPort Result: {}", result.toString());
			
		} catch (Exception e) {
			logger.error("Exception: {}", e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	public Map<String, String> getLotInfo(String siteId, String lotId) throws Exception{
		Map<String, String> returnVal = null;
		
		
		
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("lotId", lotId);
			param.put("siteId", siteId);
			param.put("useStatCd", UseStatCd.Usable.name());
			
			returnVal = wfsMapper.selectLot(param);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return returnVal;
	}
	
	public QueryLotVO getQueryLot(String siteId, String lotId) throws Exception{
		
		
		
		QueryLotVO result = null;
		
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("lotId", lotId);
			param.put("siteId", siteId);
			param.put("useStatCd", UseStatCd.Usable.name());
			
			result = wfsMapper.selectQueryLotVO(param);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	public Map<String, String> getLotInfoByCarr(String siteId, String carrId) throws Exception{
		Map<String, String> returnVal = null;
		
		
		
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("carrId", carrId);
			param.put("siteId", siteId);
			param.put("useStatCd", UseStatCd.Usable.name());
			
			returnVal = wfsMapper.selectLot(param);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return returnVal;
	}
	
	public Map<String, String> getCarrierInfo(String siteId, String carrId) throws Exception{
		Map<String, String> returnVal = null;
		
		
		
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("carrId", carrId);
			param.put("siteId", siteId);
			param.put("useStatCd", UseStatCd.Usable.name());
			
			returnVal = wfsMapper.selectCarrier(param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return returnVal;
		
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
	 */
	public int insertWnMtrlUsageInfo(String siteId, String workId, String eqpId, String subEqpId, String lotId, String specId, String specTyp, 
										String specUseCnt, String specLimitCnt, String cid, String userId, String tid) throws Exception{
		int resultVal = -1;
		
		
		
		
		try {
			WnMtrlUsageInfo param = new WnMtrlUsageInfo();
			param.setSiteId(siteId);
			param.setWorkId(workId);
			param.setEqpId(eqpId);
			param.setSubEqpId(subEqpId);
			param.setLotId(lotId);
			param.setSpecId(specId);
			param.setSpecTyp(specTyp);
			param.setSpecUseCnt(Double.valueOf(specUseCnt));
			param.setSpecLimitCnt(specLimitCnt);
			param.setEvntNm(cid);
			param.setPrevEvntNm("");
			param.setCstmEvntNm("");
			param.setPrevCstmEvntNm("");
			param.setUseStatCd("Usable");
			param.setRsnCd("");
			param.setTrnsCm("");
			param.setCrtUserId(userId);
			param.setMdfyUserId(userId);
			param.setUseStatCd("Usable");
			param.setTid(tid);
			
			resultVal = wfsMapper.insertWnMtrlUsageInfo(param);
			
			if(resultVal > 0) {
				wfsMapper.insertWhMtrlUsageInfo(param.getObjId());
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return resultVal;
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
	public int insertWnMtrlUsageInfoDicing(String siteId, String workId, String eqpId, String subEqpId, String lotId, String specId, String specTyp, 
		String specUseCnt, String subSpecUseCnt, String specLimitCnt, String cid, String userId, String tid) throws Exception{
		int resultVal = -1;
		
		
		
		
		try {
			String[] useCntList = specUseCnt.split(",");
			String[] subSpecUseCntList = subSpecUseCnt.split(",");
			int posnId = 1;
			for(int i = 0 ; i < useCntList.length; i++) {
				WnMtrlUsageInfo param = new WnMtrlUsageInfo();
				param.setSiteId(siteId);
				param.setWorkId(workId);
				param.setEqpId(eqpId);
				param.setSubEqpId(subEqpId);
				param.setLotId(lotId);
				param.setSpecId(specId + "-" + String.valueOf(posnId));
				param.setSpecTyp(specTyp);
				param.setPosnId(String.valueOf(posnId));
				param.setSpecUseCnt(Double.valueOf(useCntList[i]));
				param.setSubSpecUseCnt(Double.valueOf(subSpecUseCntList[i]));
				param.setSpecLimitCnt(specLimitCnt);
				param.setEvntNm(cid);
				param.setPrevEvntNm("");
				param.setCstmEvntNm("");
				param.setPrevCstmEvntNm("");
				param.setUseStatCd("Usable");
				param.setRsnCd("");
				param.setTrnsCm("");
				param.setCrtUserId(userId);
				param.setMdfyUserId(userId);
				param.setUseStatCd("Usable");
				param.setTid(tid);
				
				resultVal = wfsMapper.insertWnMtrlUsageInfo(param);
				if(resultVal > 0) {
					wfsMapper.insertWhMtrlUsageInfo(param.getObjId());
				}
				
				posnId++;
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return resultVal;
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
	 */
	public int insertWhErrorInfo(String siteId, String msgId, String msgCtnsCm, String workStatCd, String lotId, String carrId, 
								String eqpId, String portId, String errCd, String errCm, String cid, String userId, String tid) throws Exception{
		int resultVal = -1;
		
		
		
		
		try {
			WhErrorInfo whErrorInfo = new WhErrorInfo();
			
			whErrorInfo.setSiteId(siteId);
			whErrorInfo.setMsgId(msgId);
			whErrorInfo.setMsgCtntsCm(msgCtnsCm);
			whErrorInfo.setWorkStatCd(workStatCd);
			whErrorInfo.setLotId(lotId);
			whErrorInfo.setCarrId(carrId);
			whErrorInfo.setEqpId(eqpId);
			whErrorInfo.setPortId(portId);
			whErrorInfo.setErrCd(errCd);
			whErrorInfo.setErrCm(errCm);
			whErrorInfo.setCrtUserId(userId);
			whErrorInfo.setMdfyUserId(userId);
			whErrorInfo.setEvntNm(cid);
			whErrorInfo.setTid(tid);
			
			resultVal = wfsMapper.insertWhErrorInfo(whErrorInfo);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return resultVal;
	}
	
	

}
