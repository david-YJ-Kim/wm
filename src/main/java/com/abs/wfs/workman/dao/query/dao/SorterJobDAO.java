package com.abs.wfs.workman.dao.query.dao;

import java.util.List;

import com.abs.wfs.workman.dao.query.mapper.SorterJobMapper;
import com.abs.wfs.workman.util.code.SorterJobStatCdExec;
import com.abs.wfs.workman.util.code.SorterJobStatCdResv;
import com.abs.wfs.workman.util.code.StatCd;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abs.wfs.workman.dao.query.model.WnSorterJobExec;
import com.abs.wfs.workman.dao.query.model.WnSorterJobResv;
import com.abs.wfs.workman.dao.query.model.WnSorterJobSlotInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class SorterJobDAO {

	@Autowired
	SorterJobMapper sorterJobMapper;



	private static SorterJobDAO instance;
	private static final Logger logger = LoggerFactory.getLogger(SorterJobDAO.class);
	
	public static SorterJobDAO getInstance() {
		if(instance == null) instance = new SorterJobDAO();
		return instance;
	}
	
	private SorterJobDAO() {}
	
	public WnSorterJobExec selectSorterJobExecByJobId(String siteId, String jobId, String jobStatCd) throws Exception{
		
		
		
		WnSorterJobExec sorterJobExec = null;
		
		try {
			WnSorterJobExec param = new WnSorterJobExec();
			param.setpSiteId(siteId);
			param.setpJobId(jobId);
			param.setpJobStatCd(jobStatCd);
			
			List<WnSorterJobExec>sorterJobExecList = sorterJobMapper.selectWnSorterJobExec(param);
			
			if(sorterJobExecList.size() > 0) 
				sorterJobExec = sorterJobExecList.get(0);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		
		return sorterJobExec;
	}
	
	public int createSorterJobExec(String siteId, String cid, String tid, String userId, String jobId) throws Exception{
		
		
		
		int resultVal = -1;
		
		try {
			WnSorterJobResv resvParam = new WnSorterJobResv();
			resvParam.setpSiteId(siteId);
			resvParam.setpJobId(jobId);
			resvParam.setpUseStatCd(UseStatCd.Usable.name());
			
			//SELECT SORTER_JOB_RESV
			List<WnSorterJobResv> sorterJobResvList = sorterJobMapper.selectWnSorterJobResv(resvParam);
						
			String [] srcSlotNo = sorterJobResvList.get(0).getSrcSlotNo().split(",");
			String [] prodMtrlId = sorterJobResvList.get(0).getSrcProdMtrlId().split(",");
			
			//split,merge와 같이 target이 있는 경우에만 사용
			String [] tgtSlotNo = null;			
			if(sorterJobResvList.get(0).getTgtSlotNo() != null) 
				tgtSlotNo = sorterJobResvList.get(0).getTgtSlotNo().split(",");
			
			WnSorterJobExec param = new WnSorterJobExec();
			
			// SET
			param.setEvntNm(cid);
			param.setMdfyUserId(userId);
			param.setCrtUserId(userId);
			
			// WHERE
			param.setpSiteId(siteId);
			param.setpJobId(jobId);
			param.setpUseStatCd(UseStatCd.Usable.name());
			
			resultVal = sorterJobMapper.createWnSorterJobExec(param);
						
			if(resultVal > 0) {
				// CREATE History
				sorterJobMapper.createWhSorterJobExec(param.getObjId());
				
				//Create SorterJobSlotInfo
				for(int i = 0; i < srcSlotNo.length; i++) {
					WnSorterJobSlotInfo slotParam = new WnSorterJobSlotInfo();
					slotParam.setSiteId(siteId);
					slotParam.setJobId(jobId);
					slotParam.setEqpId(sorterJobResvList.get(0).getEqpId());
					slotParam.setSrcLotId(sorterJobResvList.get(0).getSrcLotId());
					slotParam.setSrcSlotNo(srcSlotNo[i]);
					slotParam.setTgtLotId(sorterJobResvList.get(0).getTgtLotId());
					if(tgtSlotNo != null)
						slotParam.setTgtSlotNo(tgtSlotNo[i]);
					slotParam.setProdMtrlId(prodMtrlId[i]);
					slotParam.setJobStatCd(StatCd.Active.name());
					slotParam.setEvntNm(cid);
					slotParam.setTid(tid);
					slotParam.setUseStatCd(UseStatCd.Usable.name());
					slotParam.setCrtUserId(userId);
					slotParam.setMdfyUserId(userId);
					
					int slotQueryRslt = sorterJobMapper.createWnSorterJobSlotInfo(slotParam);
					if(slotQueryRslt > 0)
						sorterJobMapper.createWhSorterJobSlotInfo(slotParam.getObjId());
				}
				
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		
		return resultVal;
	}
	
	/**
	 * UPDATE WN_SORTER_JOB_RESV 
	 * JOB_STAT_CD
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param jobId
	 * @param jobStatCd
	 * @param eqpId
	 * @param priority
	 * @return
	 */
	public int updateSorterJobResv(String siteId, String cid, String tid, String userId, String jobId, String jobStatCd, String eqpId, String priority) throws Exception{
		
		
		
		int resultVal = -1;
		
		try {
			WnSorterJobResv param = new WnSorterJobResv();
			//SET
			if(!"".equals(jobStatCd)) param.setJobStatCd(jobStatCd);
			if(!"".equals(eqpId)) param.setEqpId(eqpId);
			if(!"".equals(priority)) param.setPrirtNo(priority);
			param.setMdfyUserId(userId);
			param.setEvntNm(cid);
			param.setTid(tid);
			
			//WHERE
			param.setpSiteId(siteId);
			param.setpJobId(jobId);
			param.setpUseStatCd(UseStatCd.Usable.name());
//			param.setpJobStatCd(SorterJobStatCdResv.RESERVED.name());	//RESERVED
			
			resultVal = sorterJobMapper.updateSorterJobResv(param);
			
			if(resultVal > 0) {
				//CREATE History
				sorterJobMapper.createWhSorterJobResv(param.getObjId());
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return resultVal;
	}
	
	
	/**
	 * UPDATE WN_SORTER_JOB_Exec
	 * JOB_STAT_CD
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param jobId
	 * @param jobStatCd
	 * @return
	 */
	public int updateSorterJobExec(String siteId, String cid, String tid, String userId, String jobId, String jobStatCd) throws Exception{
		
		
		
		int resultVal = -1;
		
		try {
			WnSorterJobExec param = new WnSorterJobExec();
			//SET
			if(!"".equals(jobStatCd)) param.setJobStatCd(jobStatCd);
			param.setMdfyUserId(userId);
			param.setEvntNm(cid);
			param.setTid(tid);
			
			//WHERE
			param.setpSiteId(siteId);
			param.setpJobId(jobId);
			param.setpUseStatCd(UseStatCd.Usable.name());
			
			resultVal = sorterJobMapper.updateSorterJobExec(param);
			
			if(resultVal > 0) {
				//CREATE History
				sorterJobMapper.createWhSorterJobExec(param.getObjId());
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		
		return resultVal;
	}
	
	
	/**
	 * 
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param eqpId
	 * @param prirtNo
	 * @return
	 */
	public int updateSorterResvPriority(String siteId, String cid, String tid, String userId, String eqpId, String prirtNo) throws Exception{
		
		
		
		int resultVal = -1;
		
		try {
			WnSorterJobResv param = new WnSorterJobResv();
			param.setpSiteId(siteId);
			param.setpEqpId(eqpId);
			param.setpPrirtNo(prirtNo);
			param.setpUseStatCd(UseStatCd.Usable.name());
						
			List<WnSorterJobResv> priorityList = sorterJobMapper.selectSorterJobPriorityList(param);
			for(WnSorterJobResv r : priorityList) {
				// SET
				r.setTid(tid);
				r.setMdfyUserId(userId);
				r.setEvntNm(cid);
				
				resultVal = sorterJobMapper.updateSorterJobResvPriority(r);
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		
		return resultVal;
	}
	
	/**
	 * UPDATE WN_SORTER_JOB_RESV 
	 * JOB_STAT_CD, TGT_CARR_ID
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param jobId
	 * @param jobStatCd
	 * @param tgtCarrId
	 * @return
	 */
	public int updateSorterJobResv(String siteId, String cid, String tid, String userId, String jobId, String jobStatCd, String tgtCarrId) throws Exception{
		
		
		
		int resultVal = -1;
		
		try {
			WnSorterJobResv param = new WnSorterJobResv();
			//SET
			param.setJobStatCd(jobStatCd);
			param.setTgtCarrId(tgtCarrId);
			param.setMdfyUserId(userId);
			param.setEvntNm(cid);
			param.setTid(tid);
			
			//WHERE
			param.setpSiteId(siteId);
			param.setpJobId(jobId);
			param.setpUseStatCd(UseStatCd.Usable.name());
//			param.setpJobStatCd(SorterJobStatCdResv.RESERVED.name());	//RESERVED
			
			resultVal = sorterJobMapper.updateSorterJobResv(param);
			
			if(resultVal > 0) {
				//CREATE History
				sorterJobMapper.createWhSorterJobExec(param.getObjId());
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		
		return resultVal;
	}
	
	/**
	 * UPDATE WN_SORTER_JOB_EXEC 
	 * JOB_STAT_CD : COMPLETED
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param mdfyUserId
	 * @param jobId
	 * @return
	 */
	public int updateSorterJobExecForFinish(String siteId, String cid, String tid, String mdfyUserId, String jobId ) throws Exception{
		
		
		
		int resultVal = -1;
		
		try {
			WnSorterJobExec param = new WnSorterJobExec();
			//SET
			param.setJobStatCd(SorterJobStatCdExec.Completed.name());
			param.setMdfyUserId(mdfyUserId);
			param.setEvntNm(cid);
			param.setTid(tid);
			
			//WHERE
			param.setpSiteId(siteId);
			param.setpJobId(jobId);
			param.setpJobStatCd(SorterJobStatCdExec.Processing.name());
			
			resultVal = sorterJobMapper.updateSorterJobExec(param);
			
			if(resultVal > 0) {
				//CREATE History
				sorterJobMapper.createWhSorterJobExec(param.getObjId());
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return resultVal;
	}
	
	/**
	 * UPDATE WN_SORTER_JOB_RESV 
	 * JOB_STAT_CD : COMPLETED
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param mdfyUserId
	 * @param jobId
	 * @return
	 * @throws Exception
	 */
	public int updateSorterJobResvForFinish(String siteId, String cid, String tid, String mdfyUserId, String jobId ) throws Exception{
		
		
		
		int resultVal = -1;
		
		try {
			WnSorterJobResv param = new WnSorterJobResv();
			//SET
			param.setJobStatCd(SorterJobStatCdResv.Completed.name());
			param.setMdfyUserId(mdfyUserId);
			param.setEvntNm(cid);
			param.setTid(tid);
			
			//WHERE
			param.setpSiteId(siteId);
			param.setpJobId(jobId);
			param.setpJobStatCd(SorterJobStatCdResv.Processing.name());
			
			resultVal = sorterJobMapper.updateSorterJobResv(param);
			
			if(resultVal > 0) {
				//CREATE History
				sorterJobMapper.createWhSorterJobResv(param.getObjId());
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		
		return resultVal;
	}
	
	public int deleteSorterJobResvByJobId(String siteId, String jobId) throws Exception{
		
		
		
		int resultVal = -1;
		
		try {
			WnSorterJobResv param = new WnSorterJobResv();
			//WHERE
			param.setpSiteId(siteId);
			param.setpJobId(jobId);
			
			resultVal = sorterJobMapper.deleteSorterJobResv(param);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return resultVal;
	}
	
	public int finishCheckCarrSlot(String siteId, String cid, String tid, String userId, String jobId,String trnsCm) throws Exception{
		
		
		
		int resultVal = -1;
		
		try {
			WnSorterJobResv param = new WnSorterJobResv();
			//SET
			param.setJobStatCd("Finished");

			param.setMdfyUserId(userId);
			param.setEvntNm(cid);
			param.setTid(tid);
			param.setTrnsCm(trnsCm);
			param.setpUseStatCd(UseStatCd.Unusable.name());
			
			//WHERE
			param.setpSiteId(siteId);
			param.setpJobId(jobId);
			param.setpUseStatCd(UseStatCd.Usable.name());
//			param.setpJobStatCd(SorterJobStatCdResv.RESERVED.name());	//RESERVED
			
			resultVal = sorterJobMapper.updateSorterJobResv(param);
			
			if(resultVal > 0) {
				//CREATE History
				sorterJobMapper.createWhSorterJobResv(param.getObjId());
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		
		return resultVal;
	}
	
	
	/**
	 * update WN_SORTER_JOB_SLOT_INFO by OBJ_ID
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param objId
	 * @return
	 */
	/**
	 * 
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
	 */
	public int updateSorterJobSlotInfo(String siteId, String cid, String tid, String userId, String objId, 
										String sorterJobTyp, String scanSlotNo, String scanProdMtrlId, boolean isStart) throws Exception{
		
		
		
		int resultVal = -1;
		
		try {
			WnSorterJobSlotInfo param = new WnSorterJobSlotInfo();
			//SET
			param.setEvntNm(cid);
			param.setTid(tid);
			param.setMdfyUserId(userId);
						
			if(isStart)
				param.setProdMtrlStrtTm("START");
			else 
				param.setProdMtrlEndTm("END");
			
			//Glass ID Scan Case!
			if("GlassIdScan".equals(sorterJobTyp)) {
				param.setScanSlotNo(scanSlotNo);
				param.setScanProdMtrlId(scanProdMtrlId);
			}
			
			//WHERE
			param.setpObjId(objId);
			
			resultVal = sorterJobMapper.updateSorterJobSlotInfo(param);
			
			if(resultVal > 0) {
				// Create History
				sorterJobMapper.createWhSorterJobSlotInfo(param.getObjId());
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		
		return resultVal;
	}
	
	public int createSorterJobSlotInfo(String siteId, String cid, String tid, String userId, String objId, String jobId, String eqpId, String lotId, String sorterJobTyp, String scanSlotNo, String scanProdMtrlId) throws Exception{
		
		
		
		int resultVal = -1;
		
		try {
			WnSorterJobSlotInfo param = new WnSorterJobSlotInfo();
			
			param.setEvntNm(cid);
			param.setTid(tid);
			param.setMdfyUserId(userId);
			param.setCrtUserId(userId);
			
			param.setSiteId(siteId);
			param.setJobId(jobId);
//			param.setSrcLotId(lotId);
			param.setScanSlotNo(scanSlotNo);
			param.setScanProdMtrlId(scanProdMtrlId);
			
			resultVal = sorterJobMapper.createWnSorterJobSlotInfo(param);
			
			if(resultVal > 0) {
				sorterJobMapper.createWhSorterJobSlotInfo(param.getObjId());
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		
		return resultVal;
	}
}
