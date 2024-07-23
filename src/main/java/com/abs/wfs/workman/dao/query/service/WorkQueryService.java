package com.abs.wfs.workman.dao.query.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import com.abs.wfs.workman.dao.query.dao.WipStatDAO;
import com.abs.wfs.workman.dao.query.dao.WorkDAO;
import com.abs.wfs.workman.dao.query.service.vo.UpdateWnWorkJobEventRequestVo;
import com.abs.wfs.workman.dao.query.service.vo.UpdateWnWorkJobSlotInfoForEndTmReqVo;
import com.abs.wfs.workman.dao.query.service.vo.UpdateWnWorkJobSlotInfoForStartTmReqVo;
import com.abs.wfs.workman.dao.query.util.CreateWorkRequestVo;
import com.abs.wfs.workman.dao.query.util.XMLManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;




@Service
@Slf4j
public class WorkQueryService {

	@Autowired
	WorkDAO workDAO;


	@Autowired
	WipStatDAO wipStatDAO;

	/**
	 * CREATE WORK
	 * WN_WORK_STAT
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param workId
	 * @param batchYn
	 * @param inlineYn
	 * @param eqpInlineId
	 * @param inlineStatCd
	 * @param dspWorkId
	 * @param lotId
	 * @param batchId
	 * @param carrId
	 * @param inPortId
	 * @param inCarrId
	 * @param inCarrTyp
	 * @param lotQty
	 * @param outCarrId
	 * @param outCarrTyp
	 * @param prodDefId
	 * @param procDefId
	 * @param procSgmtId
	 * @param selfInspYn
	 * @param selfInspCnt
	 * @param recipeListXML
	 * @return
	 * @throws Exception 
	 */
	public int createWork(String siteId, String cid, String tid, String userId, String eqpId, String workId, String batchYn, String inlineYn, String eqpInlineId, String inlineStatCd, String dspWorkId,
			String lotId, String batchId, String carrId, String inPortId, String inCarrId, String inCarrTyp, String lotQty,
			String outPortId, String outCarrId, String outCarrTyp, String prodDefId, String procDefId, String procSgmtId,
			String selfInspYn, String selfInspCnt, String recipeListXML) throws Exception {
		try {	
//			return this.workDAO.createWork(siteId, cid, tid, userId, eqpId, workId, batchYn, inlineYn, eqpInlineId, inlineStatCd, dspWorkId,
//					lotId, batchId, carrId, inPortId, inCarrId, inCarrTyp, lotQty,
//					outPortId, outCarrId, outCarrTyp, prodDefId, procDefId, procSgmtId,
//					selfInspYn, selfInspCnt, recipeListXML);
			// Recipe Test
			return this.workDAO.createWorkNew(siteId, cid, tid, userId, eqpId, workId, batchYn, inlineYn, eqpInlineId, inlineStatCd, dspWorkId,
					lotId, batchId, carrId, inPortId, inCarrId, inCarrTyp, lotQty,
					outPortId, outCarrId, outCarrTyp, prodDefId, procDefId, procSgmtId,
					selfInspYn, selfInspCnt, recipeListXML);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * ValidateWork Face, 기존 createWork 메소드에서 테이블 적재 로직만 빼고 
	 * try catch 진행
	 * WN_WORK_STAT
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param workId
	 * @param lotId
	 * @param carrId
	 * @param recipeListXML
	 * @return
	 * @throws Exception 
	 */
	public String generateWorkFace(String siteId, String cid, String tid, String userId, String eqpId, String workId, String lotId, String carrId, 
									String recipeListXML, String currenntMtrlLoadingSide, String nextWorkSide, String toolHasFlipperYn) throws Exception {
		
		
		try {
			

			CreateWorkRequestVo workRequestVo = new CreateWorkRequestVo();
			workRequestVo.setCurrenntMtrlLoadingSide(currenntMtrlLoadingSide);
			workRequestVo.setNextWorkSide(nextWorkSide);
			workRequestVo.setToolHasFlipper((toolHasFlipperYn.equals("Y") ? true : false));
			
			XMLManager xmlMamager = new XMLManager();
			List<Map<String,String>> recipeList = xmlMamager.getXMLtoListMap(recipeListXML, "recipeList");
			workRequestVo.setRecipeList(recipeList);
			
			boolean isToolHasFlipper = workRequestVo.isToolHasFlipper();
			int recipeLen = workRequestVo.getRecipeList().size();
			boolean isBothRecipe = (recipeLen == 1 && "BOTH".equals(workRequestVo.getRecipeList().get(0).getWorkFace().toUpperCase())) ? true : false;
			boolean isBothFlipRecipe = (recipeLen > 1) ? true : false;
			
			
			if(isBothFlipRecipe) {
				
				// 단면
				String faceCd = this.workDAO.generateMtrlFace(currenntMtrlLoadingSide, workRequestVo.getRecipeList().get(0).getWorkFace().toUpperCase(), 
						workRequestVo.getNextWorkSide(), isToolHasFlipper, isBothRecipe, isBothFlipRecipe)[0];
				
				return faceCd;
				
			}else {
				
				// 양면
				String[] faceCdList = this.workDAO.generateMtrlFace(workRequestVo.getCurrenntMtrlLoadingSide(), "F", workRequestVo.getNextWorkSide(),
						isToolHasFlipper, isBothRecipe, isBothFlipRecipe);
				
				return String.join(",", faceCdList);
				
			}
			
			
			
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public static void main(String[] args) {
		
		String[] a = new String[2];
	}


	/**
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int createWork(CreateWorkRequestVo vo, String mtrlFaceFlag , String recipeListXML, String currenntMtrlLoadingSide, String nextWorkSide, String toolHasFlipperYn) throws Exception {
		try {


			vo.setCurrenntMtrlLoadingSide(currenntMtrlLoadingSide);
			vo.setNextWorkSide(nextWorkSide);
			vo.setToolHasFlipper((toolHasFlipperYn.equals("Y") ? true : false));

			if("Y".equals(mtrlFaceFlag)) {
				return this.workDAO.createWorkNew(vo, recipeListXML);
			}
			else {
				// 작업면 적용 전 Work 생성
				return this.workDAO.createWorkNew(vo.getSiteId(), vo.getCid(), vo.getTid(), vo.getUserId(), vo.getEqpId(),
						vo.getWorkId(), vo.getBatchYn(), vo.getInlineYn(), vo.getEqpInlineId(), vo.getInlineStatCd(), vo.getDspWorkId(),
						vo.getLotId(), vo.getBatchId(), vo.getCarrId(), vo.getInPortId(), vo.getInCarrId(), vo.getInCarrTyp(), vo.getLotQty(),
						vo.getOutPortId(), vo.getOutCarrId(), vo.getOutCarrTyp(), vo.getProdDefId(), vo.getProcDefId(), vo.getProcSgmtId(),
						vo.getSelfInspYn(), vo.getSelfInspCnt(), recipeListXML);
			}


		} catch (Exception e) {
			throw e;
		}
	}


	/**
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int createWorkMeasureTrayLoader(CreateWorkRequestVo vo, String mtrlFaceFlag , String recipeListXML, String currenntMtrlLoadingSide,
										   String nextWorkSide, String toolHasFlipperYn, String prodMtrlId) throws Exception {
		try {


			vo.setCurrenntMtrlLoadingSide(currenntMtrlLoadingSide);
			vo.setNextWorkSide(nextWorkSide);
			vo.setToolHasFlipper((toolHasFlipperYn.equals("Y") ? true : false));

			if("Y".equals(mtrlFaceFlag)) {
				return this.workDAO.createWork(vo, recipeListXML);
			}
			else {
				// 작업면 적용 전 Work 생성
				return this.workDAO.createWorkMeasTrayLoader(vo.getSiteId(), vo.getCid(), vo.getTid(), vo.getUserId(), vo.getEqpId(),
						vo.getWorkId(), vo.getBatchYn(), vo.getInlineYn(), vo.getEqpInlineId(), vo.getInlineStatCd(), vo.getDspWorkId(),
						vo.getLotId(), vo.getBatchId(), vo.getCarrId(), vo.getInPortId(), vo.getInCarrId(), vo.getInCarrTyp(), vo.getLotQty(),
						vo.getOutPortId(), vo.getOutCarrId(), vo.getOutCarrTyp(), vo.getProdDefId(), vo.getProcDefId(), vo.getProcSgmtId(),
						vo.getSelfInspYn(), vo.getSelfInspCnt(), recipeListXML,prodMtrlId);
			}


		} catch (Exception e) {
			throw e;
		}
	}

	
	/**
	 * CREATE WORK
	 * WN_WORK_STAT
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param workId
	 * @param batchYn
	 * @param inlineYn
	 * @param eqpInlineId
	 * @param inlineStatCd
	 * @param dspWorkId
	 * @param lotId
	 * @param batchId
	 * @param carrId
	 * @param inPortId
	 * @param inCarrId
	 * @param inCarrTyp
	 * @param lotQty
	 * @param outPortId
	 * @param outCarrId
	 * @param outCarrTyp
	 * @param prodDefId
	 * @param procDefId
	 * @param procSgmtId
	 * @param selfInspYn
	 * @param selfInspCnt
	 * @param recipeListXML
	 * @return
	 * @throws Exception 
	 */
	public int createWork(String siteId, String cid, String tid, String userId, String eqpId, String workId, String batchYn, String inlineYn, String eqpInlineId, String inlineStatCd, String dspWorkId,
			String lotId, String batchId, String carrId, String inPortId, String inCarrId, String inCarrTyp, String lotQty,
			String outPortId, String outCarrId, String outCarrTyp, String prodDefId, String procDefId, String procSgmtId,
			String selfInspYn, String selfInspCnt, String recipeListXML, String currenntMtrlLoadingSide, String nextWorkSide, String toolHasFlipperYn, String mtrlFaceFlag) throws Exception {
		try {	
			
			CreateWorkRequestVo workRequestVo = new CreateWorkRequestVo();
			workRequestVo.setSiteId(siteId);
			workRequestVo.setCid(cid);
			workRequestVo.setTid(tid);
			workRequestVo.setUserId(userId);
			workRequestVo.setEqpId(eqpId);
			workRequestVo.setWorkId(workId);
			workRequestVo.setBatchYn(batchYn);
			workRequestVo.setInlineYn(inlineYn);
			workRequestVo.setEqpInlineId(eqpInlineId);
			workRequestVo.setInlineStatCd(inlineStatCd);
			workRequestVo.setDspWorkId(dspWorkId);
			workRequestVo.setLotId(lotId);
			workRequestVo.setBatchId(batchId);
			workRequestVo.setCarrId(carrId);
			workRequestVo.setInPortId(inPortId);
			workRequestVo.setInCarrId(inCarrId);
			workRequestVo.setInCarrTyp(inCarrTyp);
			workRequestVo.setLotQty(lotQty);
			workRequestVo.setOutPortId(outPortId);
			workRequestVo.setOutCarrId(outCarrId);
			workRequestVo.setOutCarrTyp(outCarrTyp);
			workRequestVo.setProdDefId(prodDefId);
			workRequestVo.setProcDefId(procDefId);
			workRequestVo.setProcSgmtId(procSgmtId);
			workRequestVo.setSelfInspYn(selfInspYn);
			workRequestVo.setSelfInspCnt(selfInspCnt);
			
			workRequestVo.setCurrenntMtrlLoadingSide(currenntMtrlLoadingSide);
			workRequestVo.setNextWorkSide(nextWorkSide);
			workRequestVo.setToolHasFlipper((toolHasFlipperYn.equals("Y") ? true : false));
			
			if("Y".equals(mtrlFaceFlag)) {
				return this.workDAO.createWorkNew(workRequestVo, recipeListXML);
			}
			else {
				// 작업면 적용 전 Work 생성
				return this.workDAO.createWorkNew(siteId, cid, tid, userId, eqpId, workId, batchYn, inlineYn, eqpInlineId, inlineStatCd, dspWorkId,
						lotId, batchId, carrId, inPortId, inCarrId, inCarrTyp, lotQty,
						outPortId, outCarrId, outCarrTyp, prodDefId, procDefId, procSgmtId,
						selfInspYn, selfInspCnt, recipeListXML);
			}
			
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public int createWorkRepair(String siteId, String cid, String tid, String userId, String eqpId, String workId, String batchYn, String inlineYn, String eqpInlineId, String inlineStatCd, String dspWorkId,
			String lotId, String batchId, String carrId, String inPortId, String inCarrId, String inCarrTyp, String lotQty,
			String outPortId, String outCarrId, String outCarrTyp, String prodDefId, String procDefId, String procSgmtId,
			String selfInspYn, String selfInspCnt, String recipeListXML) throws Exception {
		try {	
			
			CreateWorkRequestVo workRequestVo = new CreateWorkRequestVo();
			workRequestVo.setSiteId(siteId);
			workRequestVo.setCid(cid);
			workRequestVo.setTid(tid);
			workRequestVo.setUserId(userId);
			workRequestVo.setEqpId(eqpId);
			workRequestVo.setWorkId(workId);
			workRequestVo.setBatchYn(batchYn);
			workRequestVo.setInlineYn(inlineYn);
			workRequestVo.setEqpInlineId(eqpInlineId);
			workRequestVo.setInlineStatCd(inlineStatCd);
			workRequestVo.setDspWorkId(dspWorkId);
			workRequestVo.setLotId(lotId);
			workRequestVo.setBatchId(batchId);
			workRequestVo.setCarrId(carrId);
			workRequestVo.setInPortId(inPortId);
			workRequestVo.setInCarrId(inCarrId);
			workRequestVo.setInCarrTyp(inCarrTyp);
			workRequestVo.setLotQty(lotQty);
			workRequestVo.setOutPortId(outPortId);
			workRequestVo.setOutCarrId(outCarrId);
			workRequestVo.setOutCarrTyp(outCarrTyp);
			workRequestVo.setProdDefId(prodDefId);
			workRequestVo.setProcDefId(procDefId);
			workRequestVo.setProcSgmtId(procSgmtId);
			workRequestVo.setSelfInspYn(selfInspYn);
			workRequestVo.setSelfInspCnt(selfInspCnt);
			
			return this.workDAO.createWorkRepair(workRequestVo, recipeListXML);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public int createWorkDicing(String siteId, String cid, String tid, String userId, String eqpId, String workId, String batchYn, String inlineYn, String eqpInlineId, String inlineStatCd, String dspWorkId,
			String lotId, String batchId, String carrId, String inPortId, String inCarrId, String inCarrTyp, String lotQty,
			String outPortId, String outCarrId, String outCarrTyp, String prodDefId, String procDefId, String procSgmtId,
			String selfInspYn, String selfInspCnt, String recipeListXML) throws Exception {
		try {	
			return this.workDAO.createWorkDicingNew(siteId, cid, tid, userId, eqpId, workId, batchYn, inlineYn, eqpInlineId, inlineStatCd, dspWorkId,
					lotId, batchId, carrId, inPortId, inCarrId, inCarrTyp, lotQty,
					outPortId, outCarrId, outCarrTyp, prodDefId, procDefId, procSgmtId,
					selfInspYn, selfInspCnt, recipeListXML);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public int createWorkForECO(String siteId, String cid, String tid, String userId, String eqpId, String workId, String batchYn, String inlineYn, String eqpInlineId, String inlineStatCd, String dspWorkId,
			String lotId, String batchId, String carrId, String inPortId, String inCarrId, String inCarrTyp, String lotQty,
			String outPortId, String outCarrId, String outCarrTyp, String prodDefId, String procDefId, String procSgmtId, String ecoId, String currenntMtrlLoadingSide, String nextWorkSide, String toolHasFlipperYn, String mtrlFaceFlag, String mtrlFaceCd) throws Exception {
		try {
			if("Y".equals(mtrlFaceFlag)) {
				return this.workDAO.createWorkForECO_WorkFace(siteId, cid, tid, userId, eqpId, workId, batchYn, inlineYn, eqpInlineId, inlineStatCd, dspWorkId,
						lotId, batchId, carrId, inPortId, inCarrId, inCarrTyp, lotQty,
						outPortId, outCarrId, outCarrTyp, prodDefId, procDefId, procSgmtId, ecoId, currenntMtrlLoadingSide, nextWorkSide, toolHasFlipperYn, mtrlFaceCd);
				
			}
			else {
				return this.workDAO.createWorkForECO(siteId, cid, tid, userId, eqpId, workId, batchYn, inlineYn, eqpInlineId, inlineStatCd, dspWorkId,
						lotId, batchId, carrId, inPortId, inCarrId, inCarrTyp, lotQty,
						outPortId, outCarrId, outCarrTyp, prodDefId, procDefId, procSgmtId, ecoId);
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	public int createWorkForBatch(String siteId, String cid, String tid, String userId, String eqpId, String workId, String batchYn, String inlineYn, String eqpInlineId, String inlineStatCd, String dspWorkId,
			String lotId, String batchId, String carrId, String inPortId, String inCarrId, String inCarrTyp, String lotQty,
			String outPortId, String outCarrId, String outCarrTyp, String prodDefId, String procDefId, String procSgmtId,
			String selfInspYn, String selfInspCnt, String recipeListXML, String currenntMtrlLoadingSide, String nextWorkSide, String toolHasFlipperYn, String mtrlFaceFlag) throws Exception {
		try {	
			
			CreateWorkRequestVo workRequestVo = new CreateWorkRequestVo();
			workRequestVo.setSiteId(siteId);
			workRequestVo.setCid(cid);
			workRequestVo.setTid(tid);
			workRequestVo.setUserId(userId);
			workRequestVo.setEqpId(eqpId);
			workRequestVo.setWorkId(workId);
			workRequestVo.setBatchYn(batchYn);
			workRequestVo.setInlineYn(inlineYn);
			workRequestVo.setEqpInlineId(eqpInlineId);
			workRequestVo.setInlineStatCd(inlineStatCd);
			workRequestVo.setDspWorkId(dspWorkId);
			workRequestVo.setLotId(lotId);
			workRequestVo.setBatchId(batchId);
			workRequestVo.setCarrId(carrId);
			workRequestVo.setInPortId(inPortId);
			workRequestVo.setInCarrId(inCarrId);
			workRequestVo.setInCarrTyp(inCarrTyp);
			workRequestVo.setLotQty(lotQty);
			workRequestVo.setOutPortId(outPortId);
			workRequestVo.setOutCarrId(outCarrId);
			workRequestVo.setOutCarrTyp(outCarrTyp);
			workRequestVo.setProdDefId(prodDefId);
			workRequestVo.setProcDefId(procDefId);
			workRequestVo.setProcSgmtId(procSgmtId);
			workRequestVo.setSelfInspYn(selfInspYn);
			workRequestVo.setSelfInspCnt(selfInspCnt);
			
			if("Y".equals(mtrlFaceFlag)) {
				workRequestVo.setCurrenntMtrlLoadingSide(currenntMtrlLoadingSide);
				workRequestVo.setNextWorkSide(nextWorkSide);
				workRequestVo.setToolHasFlipper((toolHasFlipperYn.equals("Y") ? true : false));
			}
			else {
				//Flag Off시 강제 시작/끝 면은 Top으로 고정
				workRequestVo.setCurrenntMtrlLoadingSide("Top");
				workRequestVo.setNextWorkSide("Top");
				workRequestVo.setToolHasFlipper((toolHasFlipperYn.equals("Y") ? true : false));
			}

			return this.workDAO.createWorkForBatch(workRequestVo, recipeListXML);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * UPDATE WN_WORK_STAT 
	 * Event Nm, mdfyTime
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param workId
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	public int updateWorkStatForEvent(String siteId, String cid, String tid, String workId, String userId) throws Exception {
		try {
			return this.workDAO.updateWorkStat(siteId, cid, tid, workId, userId, "");
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * UPDATE WN_WORK_STAT 
	 * RSN_CD : 1MORE
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param workId
	 * @param userId
	 * @param rsnCd
	 * @return
	 * @throws Exception
	 */
	public int updateWorkStatRsnCd(String siteId, String cid, String tid, String workId, String userId, String rsnCd) throws Exception {
		try {
			return this.workDAO.updateWorkRsnCd(siteId, cid, tid, workId, userId, rsnCd);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Start Work
	 * WN_WORK_STAT, WN_WORK_JOB, WN_WORK_SLOT_INFO update by WorkID
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param workId
	 * @return
	 * @throws Exception 
	 */
	public int startWork(String siteId, String cid, String tid, String userId, String workId) throws Exception {
		try {
			return this.workDAO.workStarted(siteId, cid, tid, userId, workId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Finish Work
	 * WN_WORK_STAT, WN_WORK_JOB, WN_WORK_SLOT_INFO update & delete BY WorkID
	 * WN_WIP_STAT.WORK_STAT_CD : Standby
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param workId
	 * @return
	 * @throws Exception 
	 */
	public int finishWork(String siteId, String cid, String tid, String userId, String workId) throws Exception {
		try {
			return this.workDAO.workEnded(siteId, cid, tid, userId, workId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * Abort Work
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param workId
	 * @return
	 * @throws Exception
	 */
	public int abortWork(String siteId, String cid, String tid, String userId, String workId) throws Exception {
		try {
			return this.workDAO.workEnded(siteId, cid, tid, userId, workId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * UPDATE WN_WORK_JOB
	 * EVNT_NM, TID, MDFY_USER_ID
	 * @return
	 * @throws Exception 
	 */
	public int updateWnWorkJobEvent(UpdateWnWorkJobEventRequestVo vo) throws Exception {
		try {
			return this.workDAO.updateWnWorkJobEvent(vo.getSiteId(), vo.getCid(), vo.getTid(), vo.getUserId(), vo.getWorkId(), vo.getJobSeqId());
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * UPDATE WN_WORK_JOB_SLOT_INFO
	 * PROD_MTRL_END_TM
	 * @return
	 * @throws Exception 
	 */
	public int updateWnWorkJobSlotInfoForEndTm(UpdateWnWorkJobSlotInfoForEndTmReqVo vo ) throws Exception {
		try {
			return this.workDAO.updateWnWorkJobSlotInfoForEndTm(vo.getSiteId(), vo.getCid(), vo.getTid(), vo.getUserId(), vo.getWorkId(),
																vo.getJobSeqId(), vo.getProdMtrlId(), vo.getSlotNo());
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * UPDATE WN_WORK_JOB_SLOT_INFO
	 * PROD_MTRL_STRT_TM
	 * @return
	 * @throws Exception 
	 */
	public int updateWnWorkJobSlotInfoForStartTm(UpdateWnWorkJobSlotInfoForStartTmReqVo vo) throws Exception {
		try {
			return this.workDAO.updateWnWorkJobSlotInfoForStartTm(vo.getSiteId(), vo.getCid(), vo.getTid(),
													vo.getUserId(), vo.getWorkId(), vo.getJobSeqId(),
													vo.getProdMtrlId(), vo.getSlotNo());
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * UPDATE WN_WORK_JOB_SLOT_INFO
	 * PROD_MTRL_STRT_TM
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param workId
	 * @param jobSeqId
	 * @param prodMtrlId
	 * @param slotNo
	 * @return
	 * @throws Exception 
	 */
	public int updateWnWorkJobSlotInfoForScrap(String siteId, String cid, String tid, String userId, String workId, String jobSeqId, String prodMtrlId, String slotNo ) throws Exception {
		try {
			return this.workDAO.updateWnWorkJobSlotInfoForScrap(siteId, cid, tid, userId, workId, jobSeqId, prodMtrlId, slotNo);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * PANEL_ID_SCAN Update
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param workId
	 * @param jobSeqId
	 * @param scanProdMtrlId
	 * @param slotNo
	 * @return
	 * @throws Exception
	 */
	public int updatePanelIdScanReport(String siteId, String cid, String tid, String userId, String workId, String jobSeqId, String scanProdMtrlId, String slotNo ) throws Exception {
		try {
			return this.workDAO.updateWnWorkJobSlotInfoForPanelIdScan(siteId, cid, tid, userId, workId, jobSeqId, scanProdMtrlId, slotNo);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * CREATE WN_WORK_JOB_CELL_INFO
	 * Process, Dump, Scrap Cell List
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param workId
	 * @param jobSeqId
	 * @param lotId
	 * @param bodyXML
	 * @return
	 * @throws Exception 
	 */
	public int dicingProcEndCellInfo(String siteId, String cid, String tid, String userId, String workId, String jobSeqId, String lotId, String bodyXML) throws Exception {
		try {
			return this.workDAO.dicingProcEnded(siteId, cid, tid, userId, workId, jobSeqId, lotId, bodyXML);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Job 초기화
	 * Work Stat Cd : Standby로 Update
	 * RESV_EQP_ID, RESV_PORT_ID 초기화
	 * @param siteId
	 * @param cid
	 * @param carrId
	 * @return
	 * @throws Exception 
	 */
	public int initJob(String siteId, String cid, String carrId) throws Exception {
		try {
			return this.wipStatDAO.updateInitWipStat(siteId, cid, carrId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public int initJobByCarrIdLotId(String siteId, String cid, String carrId, String lotId) throws Exception {
		try {
			return WipStatDAO.getInstance().updateInitWipStatByCarrIdLotId(siteId, cid, carrId, lotId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * WORK 취소(삭제)
	 * @param siteId
	 * @param cid
	 * @param workId
	 * @return
	 * @throws Exception 
	 */
	public int deleteWork(String siteId, String cid, String workId) throws Exception {
		try {
			return this.workDAO.deleteWork(siteId, cid, workId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public String[] getWorkEndLotList(String bodyXml) throws SAXException, IOException, ParserConfigurationException {
		XMLManager xmlMamager = new XMLManager();
		
		
		Map<String, Object> parsingResult = xmlMamager.getWorkEndInfo(bodyXml);
		
		return (String[])parsingResult.get("lotIdList");
		
	}
	
	public String getWorkEndRecipeId(String bodyXml) throws SAXException, IOException, ParserConfigurationException {
		XMLManager xmlMamager = new XMLManager();
		Map<String, Object> parsingResult = xmlMamager.getWorkEndInfo(bodyXml);
		
		return String.join(",", (String[])parsingResult.get("ppIdList"));
		
	}
	
	//WN_DSP_WORK_INFO
	
	public int deleteDspWorkInfo(String siteId, String cid, String dspWorkId) throws Exception{
		try {
			return this.workDAO.deleteDspWorkInfo(siteId, cid, dspWorkId);
		}catch(Exception e) {
			throw e;
		}
	}
	
	public int updateSelfInsp1MoreWork(String siteId, String workId) throws Exception{
		try {
			return this.workDAO.updateSelfInsp1MoreWork(siteId, workId);
		}catch(Exception e) {
			throw e;
		}
	}
	

}
