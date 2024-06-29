package com.abs.wfs.workman.dao.query.dao;

import com.abs.wfs.workman.dao.query.mapper.TransferJobMyMapper;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abs.wfs.workman.dao.query.model.WnTransferJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class TransferJobDAO {


	@Autowired
	TransferJobMyMapper transferJobMapper;
	
	private static TransferJobDAO instance;
	private static final Logger logger = LoggerFactory.getLogger(TransferJobDAO.class);
	
	public static TransferJobDAO getInstance() {
		if(instance == null) instance = new TransferJobDAO();
		return instance;
	}
	
	private TransferJobDAO() {}
	
	
	/**
	 * TransferJob Create
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
	 */
	public int createTransferJob(String siteId, String cid, String tid, String userId, String jobId, String carrId, 
			String crntEqpId, String crntPortId, String srcEqpId, String srcPortId, String destEqpId, String destPortId, String prio) throws Exception{
		
		
		
		int resultVal = -1;
		
		try {
			WnTransferJob param = new WnTransferJob();
			// SET
			param.setSiteId(siteId);
			param.setEvntNm(cid);
			param.setTid(tid);
			param.setCrtUserId(userId);
			param.setMdfyUserId(userId);
			param.setJobId(jobId);
			param.setCarrId(carrId);
			param.setCrntEqpId(crntEqpId);
			param.setCrntPortId(crntPortId);
			param.setSrcEqpId(srcEqpId);
			param.setSrcPortId(srcPortId);
			param.setDestEqpId(destEqpId);
			param.setDestPortId(destPortId);
			param.setUseStatCd(UseStatCd.Usable.name());
			param.setMoveStatCd("Created");
			param.setPrirtNo(prio);
			resultVal = transferJobMapper.createWnTransferJob(param);
						
			if(resultVal > 0) {
				// CREATE History
				transferJobMapper.createWhTransferJob(param.getObjId());
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return resultVal;
	}
	
	public int updateTransferJob(String siteId, String cid, String tid, String mdfyUserId, String jobId, String moveStatCd) throws Exception{
		
		
		
		int resultVal = -1;
		
		try {
			WnTransferJob param = new WnTransferJob();
			// SET
			param.setMdfyUserId(mdfyUserId);
			param.setEvntNm(cid);
			param.setTid(tid);
			param.setMoveStatCd(moveStatCd);
			
			// WHERE
			param.setpSiteId(siteId);
			param.setpJobId(jobId);
			
			resultVal = transferJobMapper.updateWnTransferJob(param);
			
			if(resultVal > 0) {
				// CREATE History
				transferJobMapper.createWhTransferJob(param.getObjId());
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return resultVal;
	}
	
	public int updateTransferJobEventNm(String siteId, String cid, String tid, String mdfyUserId, String jobId) throws Exception{
		
		
		
		int resultVal = -1;
		
		try {
			WnTransferJob param = new WnTransferJob();
			// SET
			param.setMdfyUserId(mdfyUserId);
			param.setEvntNm(cid);
			param.setTid(tid);
			
			// WHERE
			param.setpSiteId(siteId);
			param.setpJobId(jobId);
			
			resultVal = transferJobMapper.updateWnTransferJob(param);
			
			if(resultVal > 0) {
				// CREATE History
				transferJobMapper.createWhTransferJob(param.getObjId());
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return resultVal;
	}
	
	public int deleteTransferJobByJobId(String siteId, String jobId) throws Exception{
		
		
		
		int resultVal = -1;
		
		try {
			WnTransferJob param = new WnTransferJob();
			param.setpSiteId(siteId);
			param.setpJobId(jobId);
			resultVal = transferJobMapper.deleteTransferJob(param);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return resultVal;
	}

}
