package com.abs.wfs.workman.dao.query.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.abs.wfs.workman.dao.query.mapper.WfsMapper;
import com.abs.wfs.workman.dao.query.mapper.WorkMapper;
import com.abs.wfs.workman.dao.query.model.TnPosProducedMaterial;
import com.abs.wfs.workman.dao.query.model.WnDspWorkInfo;
import com.abs.wfs.workman.dao.query.model.WnWorkJob;
import com.abs.wfs.workman.dao.query.model.WnWorkJobCellInfo;
import com.abs.wfs.workman.dao.query.model.WnWorkJobSlotInfo;
import com.abs.wfs.workman.dao.query.model.WnWorkStat;

public class WorkDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageSendManager.class);
	private static WorkDAO instance;
	
	
	public static WorkDAO getInstance() {
		if(instance == null) instance = new WorkDAO();
		return instance;
	}
	
	private WorkDAO() {}
	
	
	
	
	/**
	 * Create WORK
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param eqpId
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
			String selfInspYn, String selfInspCnt, String recipeListXML) throws Exception {
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		XMLManager xmlMamager = new XMLManager();
		
		WfsMapper wfsMapper = session.getMapper(WfsMapper.class);
		
		List<Map<String,String>> recipeList = xmlMamager.getXMLtoListMap(recipeListXML, "recipeList");
		logger.info("RECIPE LIST >> " + recipeList.size());
		
		int recipeLen = recipeList.size();
		
		int resultCnt = -1;
		
		try {
			//select Panel List 
			List<TnPosProducedMaterial> prodMtrlList = wfsMapper.selectTnPosProducedMaterialByLotId(lotId);
			logger.info("###### 1> PANEL LIST >> " + prodMtrlList.size());
						
			//insert WN_WORK_STAT
			createWorkStat(session, siteId, cid, tid, userId, workId, eqpId, batchYn, inlineYn, eqpInlineId, inlineStatCd, dspWorkId);
			logger.info("###### 2> CREATE WN_WORK_STAT Completed");
			
			
			//insert WN_WORK_JOB
			// 단면 작업
			if(recipeLen == 1) {
				String recipeId = recipeList.get(0).get("recipeId");
				logger.info("###### 3> RECIPE_ID : " + recipeId);
				String mtrlFace = "";
				if("Top".equals(recipeList.get(0).get("workFace")) || "Both".equals(recipeList.get(0).get("workFace"))) {
					mtrlFace = "TTT";					
				} else if("Bottom".equals(recipeList.get(0).get("workFace"))) {
					mtrlFace = "TBT";
				}
					
				logger.info("###### 4> WORK_FACE : " + mtrlFace);
				String jobSeqId = "1";
				
				createWorkJob(session, siteId, cid, tid, workId, jobSeqId, batchId, lotId, lotQty, inPortId, inCarrId, inCarrTyp, outPortId, outCarrId, outCarrTyp, 
						prodDefId, procDefId, procSgmtId, recipeId, "Y", mtrlFace, userId);
				
				logger.info("###### 5> CREATE WN_WORK_JOB Completed");
				logger.info("###### 6> PROD_MTRL_SIZE >> " +  prodMtrlList.size());
				for(int i = 0; i < prodMtrlList.size(); i++) {
					String slotNo = prodMtrlList.get(i).getSlotNo();
					String prodMtrlId = prodMtrlList.get(i).getProdMtrlId();
					
					//자주검사
					if(selfInspCnt == null || "".equals(selfInspCnt)) 
						selfInspCnt = "0";
					int selfInspCntNum = Integer.parseInt(selfInspCnt);
					
					// slot별 Flag 찍을 지 여부
					String inspYnProdMtrl = i < selfInspCntNum ? "Y":"N";
					
					createWorkJobSlotInfo(session, siteId, cid, tid, workId, jobSeqId, lotId, slotNo, prodMtrlId, inspYnProdMtrl, mtrlFace, userId);
					
				}
			}
			// 양면 작업
			else {
				// TO-DO Test 필요
				
				//자주검사
				if("Y".equals(selfInspYn)) {
					
					int jobSeq = 1;
					//자주검사 recipe 양면
					for(Map<String, String> m : recipeList) {
						// jobSeq 1,2
						String mtrlFace = "";
						if("Top".equals(m.get("workFace")) || "Both".equals(m.get("workFace"))) {
							mtrlFace = "TTT";					
						} else if("Bottom".equals(m.get("workFace"))) {
							mtrlFace = "TBT";
						}
						logger.info("### >> " + mtrlFace);
						createWorkJob(session, siteId, cid, tid, workId, String.valueOf(jobSeq), batchId, lotId, lotQty, inPortId, inCarrId, inCarrTyp, outPortId, outCarrId, outCarrTyp, 
								prodDefId, procDefId, procSgmtId, m.get("recipeId"), "Y", mtrlFace, userId);
						jobSeq++;
					}
					
					//자주검사 외 Slot recipe 양면 
					for(Map<String, String> m : recipeList) {
						// jobSeq 3,4
						String mtrlFace = "";
						if("Top".equals(m.get("workFace")) || "Both".equals(m.get("workFace"))) {
							mtrlFace = "TTT";					
						} else if("Bottom".equals(m.get("workFace"))) {
							mtrlFace = "TBT";
						}
						createWorkJob(session, siteId, cid, tid, workId, String.valueOf(jobSeq), batchId, lotId, lotQty, inPortId, inCarrId, inCarrTyp, outPortId, outCarrId, outCarrTyp, 
								prodDefId, procDefId, procSgmtId, m.get("recipeId"), "Y", mtrlFace, userId);
						jobSeq++;
					}
					logger.info("###### 5> CREATE WN_WORK_JOB Completed");
					logger.info("###### 6> PROD_MTRL_SIZE >> " +  prodMtrlList.size());
					
					//자주검사 job Seq recipe 양면
					jobSeq = 1;
					//자주검사 recipe 양면 createWorkJobSlotInfo
					for(Map<String, String> m : recipeList) {
						String mtrlFace = "";
						if("Top".equals(m.get("workFace")) || "Both".equals(m.get("workFace"))) {
							mtrlFace = "TTT";					
						} else if("Bottom".equals(m.get("workFace"))) {
							mtrlFace = "TBT";
						} else {
							logger.info("###### UNMATCHED ####");
							logger.info(mtrlFace + "|" + m.get("workFace"));
						}
						for(int i = 0; i < Integer.parseInt(selfInspCnt); i++) {
							String slotNo = prodMtrlList.get(i).getSlotNo();
							String prodMtrlId = prodMtrlList.get(i).getProdMtrlId();
							// jobSeq 1,2
							String manualInsp = "Y";
//							String manualInsp = "N";
//							if(jobSeq == 2)
//								manualInsp = "Y";
							
							createWorkJobSlotInfo(session, siteId, cid, tid, workId, String.valueOf(jobSeq), lotId, slotNo, prodMtrlId, manualInsp, mtrlFace, userId);
						}
						jobSeq++;
					}
					
					//자주검사 외 Slot recipe 양면 createWorkJobSlotInfo
					
					for(Map<String, String> m : recipeList) {
						//자주검사 제외 slot부터 생성
						String mtrlFace = "";
						if("Top".equals(m.get("workFace")) || "Both".equals(m.get("workFace"))) {
							mtrlFace = "TTT";					
						} else if("Bottom".equals(m.get("workFace"))) {
							mtrlFace = "TBT";
						} else {
							logger.info("###### UNMATCHED ####");
							logger.info(mtrlFace + "|" + m.get("workFace"));
						}
						for(int i = Integer.parseInt(selfInspCnt); i < prodMtrlList.size(); i++) {
							String slotNo = prodMtrlList.get(i).getSlotNo();
							String prodMtrlId = prodMtrlList.get(i).getProdMtrlId();
							
							// jobSeq 3,4
							createWorkJobSlotInfo(session, siteId, cid, tid, workId, String.valueOf(jobSeq), lotId, slotNo, prodMtrlId, "N", mtrlFace, userId);
						}
						jobSeq++;
					}

				} else {
					logger.info("1 >> Top/Bottom");
					//recipe 양면
					int jobSeq = 1;
					for(Map<String, String> m : recipeList) {
						logger.info("2");
						logger.info("workFace>> "+m.get("workFace"));
						String mtrlFace = "";
						if("Top".equals(m.get("workFace")) || "Both".equals(m.get("workFace"))) {
							mtrlFace = "TTT";					
						} else if("Bottom".equals(m.get("workFace"))) {
							mtrlFace = "TBT";
						}
						else {
							logger.info("###### UNMATCHED ####");
							logger.info(mtrlFace + "|" + m.get("workFace"));
						}
						logger.info("mtrlFace>> "+mtrlFace);
						createWorkJob(session, siteId, cid, tid, workId, String.valueOf(jobSeq), batchId, lotId, lotQty, inPortId, inCarrId, inCarrTyp, outPortId, outCarrId, outCarrTyp, 
								prodDefId, procDefId, procSgmtId, m.get("recipeId"), "Y", mtrlFace, userId);
						
						logger.info("3");
						for(int i = 0; i < prodMtrlList.size(); i++) {
							String slotNo = prodMtrlList.get(i).getSlotNo();
							String prodMtrlId = prodMtrlList.get(i).getProdMtrlId();
							logger.info("4");
							createWorkJobSlotInfo(session, siteId, cid, tid, workId, String.valueOf(jobSeq), lotId, slotNo, prodMtrlId, "N", mtrlFace, userId);
						}	
						jobSeq++;
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
		
		return resultCnt;
	}
	
	/**
	 * Create WORK New : 작업면 적용
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param eqpId
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
	public int createWorkNew(String siteId, String cid, String tid, String userId, String eqpId, String workId, String batchYn, String inlineYn, String eqpInlineId, String inlineStatCd, String dspWorkId,
			String lotId, String batchId, String carrId, String inPortId, String inCarrId, String inCarrTyp, String lotQty,
			String outPortId, String outCarrId, String outCarrTyp, String prodDefId, String procDefId, String procSgmtId,
			String selfInspYn, String selfInspCnt, String recipeListXML) throws Exception {
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		XMLManager xmlMamager = new XMLManager();
		
		WfsMapper wfsMapper = session.getMapper(WfsMapper.class);
		
		// Recipe 변경 작업 적용
		Map<String,String> recipeMap = xmlMamager.getXMLtoListMap(recipeListXML).get(0);
		String mtrlFaceCd = recipeMap.get("MTRL_FACE_CD");
		String topRcpId = recipeMap.get("TOP_RCP_ID");
		String bottomRcpId = recipeMap.get("BOTTOM_RCP_ID");
		
		int resultCnt = -1;
		
		try {
			//select Panel List 
			List<TnPosProducedMaterial> prodMtrlList = wfsMapper.selectTnPosProducedMaterialByLotId(lotId);
			logger.info("###### 1> PANEL LIST >> " + prodMtrlList.size());
						
			//insert WN_WORK_STAT
			createWorkStat(session, siteId, cid, tid, userId, workId, eqpId, batchYn, inlineYn, eqpInlineId, inlineStatCd, dspWorkId);
			logger.info("###### 2> CREATE WN_WORK_STAT Completed");
			
			
			//insert WN_WORK_JOB
			// 단면 작업
			if("Top".equals(mtrlFaceCd) || "Bottom".equals(mtrlFaceCd) || "Both".equals(mtrlFaceCd)) {
				
				
				String recipeId = "";
				String mtrlFace = "";
				
				if("Top".equals(mtrlFaceCd) || "Both".equals(mtrlFaceCd)) {
					recipeId = topRcpId;
					mtrlFace = "TTT";
				} else {
					recipeId = bottomRcpId;
					mtrlFace = "TBT";
				}
				
				logger.info("###### 3> RECIPE_ID : " + recipeId);	
				logger.info("###### 4> WORK_FACE : " + mtrlFace);
				String jobSeqId = "1";
				
				createWorkJob(session, siteId, cid, tid, workId, jobSeqId, batchId, lotId, lotQty, inPortId, inCarrId, inCarrTyp, outPortId, outCarrId, outCarrTyp, 
						prodDefId, procDefId, procSgmtId, recipeId, "Y", mtrlFace, userId);
				
				logger.info("###### 5> CREATE WN_WORK_JOB Completed");
				logger.info("###### 6> PROD_MTRL_SIZE >> " +  prodMtrlList.size());
				for(int i = 0; i < prodMtrlList.size(); i++) {
					String slotNo = prodMtrlList.get(i).getSlotNo();
					String prodMtrlId = prodMtrlList.get(i).getProdMtrlId();
					
					//자주검사
					if(selfInspCnt == null || "".equals(selfInspCnt)) 
						selfInspCnt = "0";
					int selfInspCntNum = Integer.parseInt(selfInspCnt);
					
					// slot별 Flag 찍을 지 여부
					String inspYnProdMtrl = i < selfInspCntNum ? "Y":"N";
					
					createWorkJobSlotInfo(session, siteId, cid, tid, workId, jobSeqId, lotId, slotNo, prodMtrlId, inspYnProdMtrl, mtrlFace, userId);
					
				}
			}
			// 양면 작업
			else if("Both_Flip".equals(mtrlFaceCd)) {
				
				List<Map<String,String>> recipeList = new ArrayList<Map<String,String>>();
				
				Map<String,String> topMap = new HashMap<String,String>();
				Map<String,String> bottomMap = new HashMap<String,String>();
				
				topMap.put("workFace", "Top");
				topMap.put("recipeId", topRcpId);
				
				bottomMap.put("workFace", "Bottom");
				bottomMap.put("recipeId", bottomRcpId);
				
				recipeList.add(topMap);
				recipeList.add(bottomMap);
				
				
				//자주검사
				if("Y".equals(selfInspYn)) {
					
					int jobSeq = 1;
					//자주검사 recipe 양면
					for(Map<String, String> m : recipeList) {
						// jobSeq 1,2
						String mtrlFace = "";
						if("Top".equals(m.get("workFace")) || "Both".equals(m.get("workFace"))) {
							mtrlFace = "TTT";					
						} else if("Bottom".equals(m.get("workFace"))) {
							mtrlFace = "TBT";
						}
						logger.info("### >> " + mtrlFace);
						createWorkJob(session, siteId, cid, tid, workId, String.valueOf(jobSeq), batchId, lotId, lotQty, inPortId, inCarrId, inCarrTyp, outPortId, outCarrId, outCarrTyp, 
								prodDefId, procDefId, procSgmtId, m.get("recipeId"), "Y", mtrlFace, userId);
						jobSeq++;
					}
					
					//자주검사 외 Slot recipe 양면 
					for(Map<String, String> m : recipeList) {
						// jobSeq 3,4
						String mtrlFace = "";
						if("Top".equals(m.get("workFace")) || "Both".equals(m.get("workFace"))) {
							mtrlFace = "TTT";					
						} else if("Bottom".equals(m.get("workFace"))) {
							mtrlFace = "TBT";
						}
						createWorkJob(session, siteId, cid, tid, workId, String.valueOf(jobSeq), batchId, lotId, lotQty, inPortId, inCarrId, inCarrTyp, outPortId, outCarrId, outCarrTyp, 
								prodDefId, procDefId, procSgmtId, m.get("recipeId"), "Y", mtrlFace, userId);
						jobSeq++;
					}
					logger.info("###### 5> CREATE WN_WORK_JOB Completed");
					logger.info("###### 6> PROD_MTRL_SIZE >> " +  prodMtrlList.size());
					
					//자주검사 job Seq recipe 양면
					jobSeq = 1;
					//자주검사 recipe 양면 createWorkJobSlotInfo
					for(Map<String, String> m : recipeList) {
						String mtrlFace = "";
						if("Top".equals(m.get("workFace")) || "Both".equals(m.get("workFace"))) {
							mtrlFace = "TTT";					
						} else if("Bottom".equals(m.get("workFace"))) {
							mtrlFace = "TBT";
						} else {
							logger.info("###### UNMATCHED ####");
							logger.info(mtrlFace + "|" + m.get("workFace"));
						}
						for(int i = 0; i < Integer.parseInt(selfInspCnt); i++) {
							String slotNo = prodMtrlList.get(i).getSlotNo();
							String prodMtrlId = prodMtrlList.get(i).getProdMtrlId();
							// jobSeq 1,2
							String manualInsp = "Y";
//							String manualInsp = "N";
//							if(jobSeq == 2)
//								manualInsp = "Y";
							
							createWorkJobSlotInfo(session, siteId, cid, tid, workId, String.valueOf(jobSeq), lotId, slotNo, prodMtrlId, manualInsp, mtrlFace, userId);
						}
						jobSeq++;
					}
					
					//자주검사 외 Slot recipe 양면 createWorkJobSlotInfo
					
					for(Map<String, String> m : recipeList) {
						//자주검사 제외 slot부터 생성
						String mtrlFace = "";
						if("Top".equals(m.get("workFace")) || "Both".equals(m.get("workFace"))) {
							mtrlFace = "TTT";					
						} else if("Bottom".equals(m.get("workFace"))) {
							mtrlFace = "TBT";
						} else {
							logger.info("###### UNMATCHED ####");
							logger.info(mtrlFace + "|" + m.get("workFace"));
						}
						for(int i = Integer.parseInt(selfInspCnt); i < prodMtrlList.size(); i++) {
							String slotNo = prodMtrlList.get(i).getSlotNo();
							String prodMtrlId = prodMtrlList.get(i).getProdMtrlId();
							
							// jobSeq 3,4
							createWorkJobSlotInfo(session, siteId, cid, tid, workId, String.valueOf(jobSeq), lotId, slotNo, prodMtrlId, "N", mtrlFace, userId);
						}
						jobSeq++;
					}

				} else {
					logger.info("1 >> Top/Bottom");
					//recipe 양면
					int jobSeq = 1;
					for(Map<String, String> m : recipeList) {
						logger.info("2");
						logger.info("workFace>> "+m.get("workFace"));
						String mtrlFace = "";
						if("Top".equals(m.get("workFace")) || "Both".equals(m.get("workFace"))) {
							mtrlFace = "TTT";					
						} else if("Bottom".equals(m.get("workFace"))) {
							mtrlFace = "TBT";
						}
						else {
							logger.info("###### UNMATCHED ####");
							logger.info(mtrlFace + "|" + m.get("workFace"));
						}
						logger.info("mtrlFace>> "+mtrlFace);
						createWorkJob(session, siteId, cid, tid, workId, String.valueOf(jobSeq), batchId, lotId, lotQty, inPortId, inCarrId, inCarrTyp, outPortId, outCarrId, outCarrTyp, 
								prodDefId, procDefId, procSgmtId, m.get("recipeId"), "Y", mtrlFace, userId);
						
						logger.info("3");
						for(int i = 0; i < prodMtrlList.size(); i++) {
							String slotNo = prodMtrlList.get(i).getSlotNo();
							String prodMtrlId = prodMtrlList.get(i).getProdMtrlId();
							logger.info("4");
							createWorkJobSlotInfo(session, siteId, cid, tid, workId, String.valueOf(jobSeq), lotId, slotNo, prodMtrlId, "N", mtrlFace, userId);
						}	
						jobSeq++;
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
		
		return resultCnt;
	}

	
	
	/**
	 * Create WORK
	 * 
	 * workFace : 현 공정에서 작업할 면
	 * mtrlFace : Work으로 내려야하는 전체적인 Work 면 (TTT / TBT 등)
	 * 
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param eqpId
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
	public int createWork(CreateWorkRequestVo workRequestVo, String recipeListXML) throws Exception {
		
		String siteId = workRequestVo.getSiteId();
		String cid = workRequestVo.getCid();
		String tid = workRequestVo.getTid();
		String workId = workRequestVo.getWorkId();
		String userId = workRequestVo.getUserId();
		String lotId = workRequestVo.getLotId();
		String batchId = workRequestVo.getBatchId();
		String selfInspCnt = workRequestVo.getSelfInspCnt();
		String eqpId = workRequestVo.getEqpId();
		String batchYn = workRequestVo.getBatchYn();
		String inlineYn = workRequestVo.getInlineYn();
		String eqpInlineId = workRequestVo.getEqpInlineId();
		String dspWorkId = workRequestVo.getDspWorkId();
		String inlineStatCd = workRequestVo.getInlineStatCd();
		
		
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		XMLManager xmlMamager = new XMLManager();
		
		WfsMapper wfsMapper = session.getMapper(WfsMapper.class);
		// recipeId:RCP.1002 / workFace: Top || Bottom
		List<Map<String,String>> recipeList = xmlMamager.getXMLtoListMap(recipeListXML, "recipeList");

		
		// Resources
		workRequestVo.setRecipeList(recipeList);
		workRequestVo.setSession(session);
		workRequestVo.setXmlManager(xmlMamager);
		workRequestVo.setWfsMapper(wfsMapper);
		
		
		
		int recipeLen = workRequestVo.getRecipeList().size();
		logger.info("RECIPE LIST >> " + recipeLen);

		String currentMtrlSide = workRequestVo.getCurrenntMtrlLoadingSide();
		String nextWorkSide = workRequestVo.getNextWorkSide();
		
		boolean isBothRecipe = (recipeLen == 1 && "BOTH".equals(workRequestVo.getRecipeList().get(0).getWorkFace().toUpperCase())) ? true : false;
		boolean isBothFlipRecipe = (recipeLen > 1) ? true : false;
		boolean isToolHasFlipper = workRequestVo.isToolHasFlipper();
		
		int resultCnt = -1;
		
		List<TnPosProducedMaterial> prodMtrlList = null;
		try {
			//select Panel List 
			prodMtrlList = wfsMapper.selectTnPosProducedMaterialByLotId(lotId);
		}catch (Exception e) {
			logger.error("Error : {}", e);
			throw e;
		}
		if(prodMtrlList == null) throw new NullPointerException("Produced material is not found by Lot Id : . " + lotId);
		
		
		try {
			//insert WN_WORK_STAT
			this.createWorkStat(session, siteId, cid, tid, userId, workId, eqpId, batchYn, inlineYn, eqpInlineId, inlineStatCd, dspWorkId);
			logger.info("###### 2> CREATE WN_WORK_STAT Completed");
			
		}catch (Exception e) {
			logger.error("Error : {}", e);
			throw e;
		}
		

		
		try {
			logger.info("###### 1> PANEL LIST >> " + prodMtrlList.size());
						
			int jobSeq = 1;
			
			//insert WN_WORK_JOB
			// 단면 작업 & BOTH 작업
			if(recipeLen == 1 && !isBothFlipRecipe) {
				RecipeVo recipeVo = workRequestVo.getRecipeList().get(0);
				String recipeId = recipeVo.getRecipeId();

				logger.info("###### 3> RECIPE_ID : " + recipeId);
				String mtrlFace = this.generateMtrlFace(currentMtrlSide, recipeVo.getWorkFace().toUpperCase(), nextWorkSide, 
													isToolHasFlipper, isBothRecipe, isBothFlipRecipe)[0];
					
				logger.info("###### 4> WORK_FACE : " + mtrlFace);
				
				this.createWorkJob(session, siteId, cid, tid, workId, String.valueOf(jobSeq), batchId, lotId, workRequestVo, recipeId, "Y", mtrlFace, userId);
				
				logger.info("###### 5> CREATE WN_WORK_JOB Completed");
				logger.info("###### 6> PROD_MTRL_SIZE >> " +  prodMtrlList.size());
				this.insertWorkSlotPerProdMtrl(workRequestVo, String.valueOf(jobSeq), lotId, mtrlFace, prodMtrlList);
				
			}
			// 양면 작업
			else {
				
				String[] mtrlFaceArray = this.generateMtrlFace(currentMtrlSide, "F", nextWorkSide, isToolHasFlipper, isBothRecipe, isBothFlipRecipe);
				//recipe 양면
				
				
				/*
				 * 양면 - 일반 검사 
				 */
				if(!"Y".equals(workRequestVo.getSelfInspYn())) {
					
					logger.info("1 >> Top/Bottom");
					this.createBothFlipWork(jobSeq, mtrlFaceArray, workRequestVo, prodMtrlList, true);
					
				}
				
				/*
				 * 양면 - 자주 검사
				 */
				else {
					
					/*
					 * 자주검사 recipe 양면
					 * Seq 1 : 첫번째 장 TTT 자주검사 → 투입면 + 투입면 + 반대면
					 * Seq 2 : 첫번째 장 TBT 자주검사 → 반대면 + 반대면 + 투입면
					 */
					logger.info("###### 5> CREATE WN_WORK_JOB Completed");
					this.createBothFlipWork(jobSeq, mtrlFaceArray, workRequestVo, prodMtrlList, false);
					this.createBothFlipWork(jobSeq + 2, mtrlFaceArray, workRequestVo, prodMtrlList, false);
					
					logger.info("###### 6> PROD_MTRL_SIZE >> " +  prodMtrlList.size());
					
					
					
					
					//자주검사 job Seq recipe 양면
					jobSeq = 1; // job seq 초기화
					
					//자주검사 recipe 양면 createWorkJobSlotInfo
					for(RecipeVo vo : workRequestVo.getRecipeList()) {
						
						String workFace = vo.getWorkFace(); // 작업면
						String mtrlFace = "";
						
						if("Top".equals(workFace) || "Both".equals(workFace)) {
							mtrlFace = "TTT";					
						} else if("Bottom".equals(workFace)) {
							mtrlFace = "TBT";
						} else {
							logger.info("###### UNMATCHED ####");
							logger.info(mtrlFace + "|" + workFace);
						}
						for(int i = 0; i < Integer.parseInt(selfInspCnt); i++) {
							String slotNo = prodMtrlList.get(i).getSlotNo();
							String prodMtrlId = prodMtrlList.get(i).getProdMtrlId();
							// jobSeq 1,2
							String manualInsp = "Y";
							
							createWorkJobSlotInfo(session, siteId, cid, tid, workId, String.valueOf(jobSeq), lotId, slotNo, prodMtrlId, manualInsp, mtrlFace, userId);
						}
						jobSeq++;
					}

					
					
					//자주검사 외 Slot recipe 양면 createWorkJobSlotInfo
					for(RecipeVo vo : workRequestVo.getRecipeList()) {
						
						//자주검사 제외 slot부터 생성
						String workFace = vo.getWorkFace(); // 작업면
						String mtrlFace = "";
						
						if("Top".equals(workFace) || "Both".equals(workFace)) {
							mtrlFace = "TTT";					
						} else if("Bottom".equals(workFace)) {
							mtrlFace = "TBT";
						} else {
							logger.info("###### UNMATCHED ####");
							logger.info(mtrlFace + "|" + workFace);
						}
						for(int i = Integer.parseInt(selfInspCnt); i < prodMtrlList.size(); i++) {
							String slotNo = prodMtrlList.get(i).getSlotNo();
							String prodMtrlId = prodMtrlList.get(i).getProdMtrlId();
							
							// jobSeq 3,4
							createWorkJobSlotInfo(session, siteId, cid, tid, workId, String.valueOf(jobSeq), lotId, slotNo, prodMtrlId, "N", mtrlFace, userId);
						}
						jobSeq++;
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
		
		return resultCnt;
	}
	
	/**
	 * WORK 생성 - 작업면, Recipe 적용
	 * @param workRequestVo
	 * @param recipeListXML
	 * @return
	 * @throws Exception
	 */
	public int createWorkNew(CreateWorkRequestVo workRequestVo, String recipeListXML) throws Exception {
		
		String siteId = workRequestVo.getSiteId();
		String cid = workRequestVo.getCid();
		String tid = workRequestVo.getTid();
		String workId = workRequestVo.getWorkId();
		String userId = workRequestVo.getUserId();
		String lotId = workRequestVo.getLotId();
		String batchId = workRequestVo.getBatchId();
		String selfInspCnt = workRequestVo.getSelfInspCnt();
		String eqpId = workRequestVo.getEqpId();
		String batchYn = workRequestVo.getBatchYn();
		String inlineYn = workRequestVo.getInlineYn();
		String eqpInlineId = workRequestVo.getEqpInlineId();
		String dspWorkId = workRequestVo.getDspWorkId();
		String inlineStatCd = workRequestVo.getInlineStatCd();
		
		
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		XMLManager xmlMamager = new XMLManager();
		
		WfsMapper wfsMapper = session.getMapper(WfsMapper.class);
		
		
		// Recipe 변경 작업 적용
		Map<String,String> recipeMap = xmlMamager.getXMLtoListMap(recipeListXML).get(0);
		String mtrlFaceCd = recipeMap.get("MTRL_FACE_CD");
		String topRcpId = recipeMap.get("TOP_RCP_ID");
		String bottomRcpId = recipeMap.get("BOTTOM_RCP_ID");
		
		
		
				
		// Resources
//		workRequestVo.setRecipeList(recipeList);
		workRequestVo.setSession(session);
		workRequestVo.setXmlManager(xmlMamager);
		workRequestVo.setWfsMapper(wfsMapper);
		
		String currentMtrlSide = workRequestVo.getCurrenntMtrlLoadingSide();
		String nextWorkSide = workRequestVo.getNextWorkSide();
		
		boolean isToolHasFlipper = workRequestVo.isToolHasFlipper();
		
		//MaterialFaceList
		String[] mtrlFace = this.generateMtrlFace(currentMtrlSide, mtrlFaceCd.toUpperCase(), nextWorkSide, isToolHasFlipper, mtrlFaceCd);
		logger.info("###### 0> Material Face >> " + mtrlFace);
		
		int resultCnt = -1;
		
		List<TnPosProducedMaterial> prodMtrlList = null;
		try {
			//select Panel List 
			prodMtrlList = wfsMapper.selectTnPosProducedMaterialByLotId(lotId);
		}catch (Exception e) {
			logger.error("Error : {}", e);
			throw e;
		}
		if(prodMtrlList == null) throw new NullPointerException("Produced material is not found by Lot Id : . " + lotId);
		
		
		try {
			//insert WN_WORK_STAT
			this.createWorkStat(session, siteId, cid, tid, userId, workId, eqpId, batchYn, inlineYn, eqpInlineId, inlineStatCd, dspWorkId);
			logger.info("###### 2> CREATE WN_WORK_STAT Completed");
			
		}catch (Exception e) {
			logger.error("Error : {}", e);
			throw e;
		}
		

		
		try {
			logger.info("###### 1> PANEL LIST >> " + prodMtrlList.size());
						
			int jobSeq = 1;
			
			//insert WN_WORK_JOB
			// 단면 작업
			if("Top".equals(mtrlFaceCd) || "Bottom".equals(mtrlFaceCd) || "Both".equals(mtrlFaceCd)) {
				
				String recipeId = "";
				if("Top".equals(mtrlFaceCd) || "Both".equals(mtrlFaceCd)) {
					recipeId = topRcpId;
				}
				else {
					recipeId = bottomRcpId;
				}
				

				logger.info("###### 3> RECIPE_ID : " + recipeId);
				
					
				logger.info("###### 4> WORK_FACE : " + mtrlFace);
				
				this.createWorkJob(session, siteId, cid, tid, workId, String.valueOf(jobSeq), batchId, lotId, workRequestVo, recipeId, "Y", mtrlFace[0], userId);
				this.insertWorkSlotPerProdMtrl(workRequestVo, String.valueOf(jobSeq), lotId, mtrlFace[0], prodMtrlList);
				logger.info("###### 5> CREATE WN_WORK_JOB Completed");
				logger.info("###### 6> PROD_MTRL_SIZE >> " +  prodMtrlList.size());
				
			}
			// 양면 작업 Both_Flip
			else {
				
				List<Map<String,String>> recipeList = new ArrayList<Map<String,String>>();
				
				Map<String,String> topMap = new HashMap<String,String>();
				Map<String,String> bottomMap = new HashMap<String,String>();
				
				topMap.put("workFace", "Top");
				topMap.put("recipeId", topRcpId);
				
				bottomMap.put("workFace", "Bottom");
				bottomMap.put("recipeId", bottomRcpId);
				
				recipeList.add(topMap);
				recipeList.add(bottomMap);
				
				workRequestVo.setRecipeList(recipeList);
				
				/*
				 * 양면 - 일반 검사 
				 */
				if(!"Y".equals(workRequestVo.getSelfInspYn())) {
					
					logger.info("1 >> Top/Bottom");
					this.createBothFlipWork(jobSeq, mtrlFace, workRequestVo, prodMtrlList, true);
					
				}
				
				/*
				 * 양면 - 자주 검사
				 */
				else {
					
					/*
					 * 자주검사 recipe 양면
					 * Seq 1 : 첫번째 장 TTT 자주검사 → 투입면 + 투입면 + 반대면
					 * Seq 2 : 첫번째 장 TBT 자주검사 → 반대면 + 반대면 + 투입면
					 */
					logger.info("###### 5> CREATE WN_WORK_JOB Completed");
					this.createBothFlipWork(jobSeq, mtrlFace, workRequestVo, prodMtrlList, false);
					this.createBothFlipWork(jobSeq + 2, mtrlFace, workRequestVo, prodMtrlList, false);
					
					logger.info("###### 6> PROD_MTRL_SIZE >> " +  prodMtrlList.size());
										
					
					//자주검사 job Seq recipe 양면
					jobSeq = 1; // job seq 초기화
					
					//자주검사 recipe 양면 createWorkJobSlotInfo
					for(String face : mtrlFace) {
						for(int i = 0; i < Integer.parseInt(selfInspCnt); i++) {
							String slotNo = prodMtrlList.get(i).getSlotNo();
							String prodMtrlId = prodMtrlList.get(i).getProdMtrlId();
							// jobSeq 1,2
							String manualInsp = "Y";
							
							createWorkJobSlotInfo(session, siteId, cid, tid, workId, String.valueOf(jobSeq), lotId, slotNo, prodMtrlId, manualInsp, face, userId);
						}
						jobSeq++;
					}
										
					
					//자주검사 외 Slot recipe 양면 createWorkJobSlotInfo
					for(String face : mtrlFace) {
						
						//자주검사 제외 slot부터 생성

						for(int i = Integer.parseInt(selfInspCnt); i < prodMtrlList.size(); i++) {
							String slotNo = prodMtrlList.get(i).getSlotNo();
							String prodMtrlId = prodMtrlList.get(i).getProdMtrlId();
							
							String manualInsp = "N";
							
							// jobSeq 3,4
							createWorkJobSlotInfo(session, siteId, cid, tid, workId, String.valueOf(jobSeq), lotId, slotNo, prodMtrlId, manualInsp, face, userId);
						}
						jobSeq++;
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
		
		return resultCnt;
	}
	
	
	@SuppressWarnings("unused")
	public int createWorkRepair(CreateWorkRequestVo workRequestVo, String recipeListXML) throws Exception {
		
		String siteId = workRequestVo.getSiteId();
		String cid = workRequestVo.getCid();
		String tid = workRequestVo.getTid();
		String workId = workRequestVo.getWorkId();
		String userId = workRequestVo.getUserId();
		String lotId = workRequestVo.getLotId();
		String batchId = workRequestVo.getBatchId();
		String eqpId = workRequestVo.getEqpId();
		String batchYn = workRequestVo.getBatchYn();
		String inlineYn = workRequestVo.getInlineYn();
		String eqpInlineId = workRequestVo.getEqpInlineId();
		String dspWorkId = workRequestVo.getDspWorkId();
		String inlineStatCd = workRequestVo.getInlineStatCd();
		
		
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		XMLManager xmlMamager = new XMLManager();
		
		WfsMapper wfsMapper = session.getMapper(WfsMapper.class);
		
		
		// Recipe 변경 작업 적용
		Map<String,String> recipeMap = xmlMamager.getXMLtoListMap(recipeListXML).get(0);
		String mtrlFaceCd = recipeMap.get("MTRL_FACE_CD");
		String topRcpId = recipeMap.get("TOP_RCP_ID");
		String bottomRcpId = recipeMap.get("BOTTOM_RCP_ID");
		
				
		// Resources
//		workRequestVo.setRecipeList(recipeList);
		workRequestVo.setSession(session);
		workRequestVo.setXmlManager(xmlMamager);
		workRequestVo.setWfsMapper(wfsMapper);
		
		
		
		
		//MaterialFaceList
//		String[] mtrlFace = this.generateMtrlFace(currentMtrlSide, mtrlFaceCd.toUpperCase(), nextWorkSide, isToolHasFlipper, mtrlFaceCd);
//		logger.info("###### 0> Material Face >> " + mtrlFace);
		
		int resultCnt = -1;
		
		List<Map<String,String>> prodMtrlList = null;
		
		try {
			//insert WN_WORK_STAT
			this.createWorkStat(session, siteId, cid, tid, userId, workId, eqpId, batchYn, inlineYn, eqpInlineId, inlineStatCd, dspWorkId);
			logger.info("###### 2> CREATE WN_WORK_STAT Completed");
			
		}catch (Exception e) {
			logger.error("Error : {}", e);
			throw e;
		}
		

		
		try {
			
			
			Map<String,String> param = new HashMap<String,String>();
			param.put("statCd", "Active");
			param.put("siteId", siteId);
			param.put("useStatCd", "Usable");
			param.put("lotId", lotId);
			param.put("rprYn", "Y");
			
			try {
				//select Panel List (Top/Bottom ALL) 
				prodMtrlList = wfsMapper.selectTargetGlassList(param);
				logger.info("###### PANEL LIST TOP/BOTTOM ALL  >> " + prodMtrlList.size());
				
			}catch (Exception e) {
				logger.error("Error : {}", e);
				throw e;
			}
			
			if(prodMtrlList == null || prodMtrlList.size() == 0) throw new NullPointerException("Produced material is not found by Lot Id : . " + lotId);
			
						
			int jobSeq = 1;
			
			if("Top".equals(mtrlFaceCd) || "Bottom".equals(mtrlFaceCd)) {
				
				try {
					param.put("mtrlFaceCd", mtrlFaceCd);
					//select Panel List 
					prodMtrlList = wfsMapper.selectTargetGlassList(param);
					logger.info("###### 1> PANEL LIST >> " + prodMtrlList.size());
					
				}catch (Exception e) {
					logger.error("Error : {}", e);
					throw e;
				}				
				
				if(prodMtrlList.size() > 0) {
					String recipeId =  topRcpId;
					String mtrlFace = "";
					if("Top".equals(mtrlFaceCd)) {
						mtrlFace ="TTT";
					} else {
						mtrlFace ="TBT";
					}
					
					logger.info("###### 4> WORK_FACE : " + mtrlFace);
					
					this.createWorkJob(session, siteId, cid, tid, workId, String.valueOf(jobSeq), batchId, lotId, workRequestVo, recipeId, "Y", mtrlFace, userId);
					
					logger.info("###### 5> CREATE WN_WORK_JOB Completed");
					logger.info("###### 6> PROD_MTRL_SIZE >> " +  prodMtrlList.size());
					this.insertWorkSlotPerProdMtrlRepair(workRequestVo, String.valueOf(jobSeq), lotId, mtrlFace, prodMtrlList);
				}
				
				
			} else if("Both".equals(mtrlFaceCd) || "Both_Flip".equals(mtrlFaceCd)) {
				String mtrlFace = "";;
				
				logger.info("###### 3> RECIPE_ID : " + topRcpId + "," + bottomRcpId);
				
				
				logger.info("###### 4> WORK_FACE : " + mtrlFace);
				
				//TOP
				mtrlFace = "TTT";
				try {
					param.put("mtrlFaceCd", "Top");
					//select Panel List 
					prodMtrlList = wfsMapper.selectTargetGlassList(param);
					logger.info("###### 4> PROD_MTRL_SIZE >> " +  prodMtrlList.size());
					
				}catch (Exception e) {
					logger.error("Error : {}", e);
					throw e;
				}
							
				if(prodMtrlList.size() > 0) {
					this.createWorkJob(session, siteId, cid, tid, workId, String.valueOf(jobSeq), batchId, lotId, workRequestVo, topRcpId, "Y", mtrlFace, userId);
					this.insertWorkSlotPerProdMtrlRepair(workRequestVo, String.valueOf(jobSeq), lotId, mtrlFace, prodMtrlList);
					jobSeq++;
				}
				
				
				//BOTTOM
				mtrlFace = "TBT";
				try {
					param.put("mtrlFaceCd", "Bottom");
					//select Panel List 
					prodMtrlList = wfsMapper.selectTargetGlassList(param);
					logger.info("###### 4> PROD_MTRL_SIZE >> " +  prodMtrlList.size());
				}catch (Exception e) {
					logger.error("Error : {}", e);
					throw e;
				}
				if(prodMtrlList.size() > 0) {
					this.createWorkJob(session, siteId, cid, tid, workId, String.valueOf(jobSeq), batchId, lotId, workRequestVo, bottomRcpId, "Y", mtrlFace, userId);
					this.insertWorkSlotPerProdMtrlRepair(workRequestVo, String.valueOf(jobSeq), lotId, mtrlFace, prodMtrlList);
				}
				
			}
			
			logger.info("###### 5> CREATE WN_WORK_JOB Completed");
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
	
	
	
	
	public int createWorkDicing(String siteId, String cid, String tid, String userId, String eqpId, String workId, String batchYn, String inlineYn, String eqpInlineId, String inlineStatCd, String dspWorkId,
			String lotId, String batchId, String carrId, String inPortId, String inCarrId, String inCarrTyp, String lotQty,
			String outPortId, String outCarrId, String outCarrTyp, String prodDefId, String procDefId, String procSgmtId,
			String selfInspYn, String selfInspCnt, String recipeIdParam) throws Exception {
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		XMLManager xmlMamager = new XMLManager();
		
		WfsMapper wfsMapper = session.getMapper(WfsMapper.class);
		
		
		int recipeLen = 1;
		
		int resultCnt = -1;
		
		try {
			//select Panel List 
			List<TnPosProducedMaterial> prodMtrlList = wfsMapper.selectTnPosProducedMaterialByLotId(lotId);
			logger.info("###### 1> PANEL LIST >> " + prodMtrlList.size());
						
			//insert WN_WORK_STAT
			createWorkStat(session, siteId, cid, tid, userId, workId, eqpId, batchYn, inlineYn, eqpInlineId, inlineStatCd, dspWorkId);
			logger.info("###### 2> CREATE WN_WORK_STAT Completed");
			
			
			//insert WN_WORK_JOB
			// 단면 작업
			if(recipeLen == 1) {
				String recipeId = recipeIdParam;
				logger.info("###### 3> RECIPE_ID : " + recipeId);
				String mtrlFace = "";
//				if("Top".equals(recipeList.get(0).get("workFace")) || "Both".equals(recipeList.get(0).get("workFace"))) {
//					mtrlFace = "TTT";					
//				} else if("Bottom".equals(recipeList.get(0).get("workFace"))) {
//					mtrlFace = "TBT";
//				}
				mtrlFace = "TTT";
					
				logger.info("###### 4> WORK_FACE : " + mtrlFace);
				String jobSeqId = "1";
				
				createWorkJob(session, siteId, cid, tid, workId, jobSeqId, batchId, lotId, lotQty, inPortId, inCarrId, inCarrTyp, outPortId, outCarrId, outCarrTyp, 
						prodDefId, procDefId, procSgmtId, recipeId, "Y", mtrlFace, userId);
				
				logger.info("###### 5> CREATE WN_WORK_JOB Completed");
				logger.info("###### 6> PROD_MTRL_SIZE >> " +  prodMtrlList.size());
				for(int i = 0; i < prodMtrlList.size(); i++) {
					String slotNo = prodMtrlList.get(i).getSlotNo();
					String prodMtrlId = prodMtrlList.get(i).getProdMtrlId();
					
					//자주검사
					if(selfInspCnt == null || "".equals(selfInspCnt)) 
						selfInspCnt = "0";
					int selfInspCntNum = Integer.parseInt(selfInspCnt);
					
					// slot별 Flag 찍을 지 여부
					String inspYnProdMtrl = i < selfInspCntNum ? "Y":"N";
					
					createWorkJobSlotInfo(session, siteId, cid, tid, workId, jobSeqId, lotId, slotNo, prodMtrlId, inspYnProdMtrl, mtrlFace, userId);
					
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
		
		return resultCnt;
	}
	
	public int createWorkDicingNew(String siteId, String cid, String tid, String userId, String eqpId, String workId, String batchYn, String inlineYn, String eqpInlineId, String inlineStatCd, String dspWorkId,
			String lotId, String batchId, String carrId, String inPortId, String inCarrId, String inCarrTyp, String lotQty,
			String outPortId, String outCarrId, String outCarrTyp, String prodDefId, String procDefId, String procSgmtId,
			String selfInspYn, String selfInspCnt, String recipeListXML) throws Exception {
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		XMLManager xmlMamager = new XMLManager();
		
		// Recipe 변경 작업 적용
		Map<String,String> recipeMap = xmlMamager.getXMLtoListMap(recipeListXML).get(0);
		String mtrlFaceCd = recipeMap.get("MTRL_FACE_CD");
		String topRcpId = recipeMap.get("TOP_RCP_ID");
		String bottomRcpId = recipeMap.get("BOTTOM_RCP_ID");
		
		WfsMapper wfsMapper = session.getMapper(WfsMapper.class);
		
		
		int recipeLen = 1;
		
		int resultCnt = -1;
		
		try {
			//select Panel List 
			List<TnPosProducedMaterial> prodMtrlList = wfsMapper.selectTnPosProducedMaterialByLotId(lotId);
			logger.info("###### 1> PANEL LIST >> " + prodMtrlList.size());
						
			//insert WN_WORK_STAT
			createWorkStat(session, siteId, cid, tid, userId, workId, eqpId, batchYn, inlineYn, eqpInlineId, inlineStatCd, dspWorkId);
			logger.info("###### 2> CREATE WN_WORK_STAT Completed");
			
			
			//insert WN_WORK_JOB
			// 단면 작업
			// 단면 작업
			if("Top".equals(mtrlFaceCd) || "Bottom".equals(mtrlFaceCd) || "Both".equals(mtrlFaceCd)) {
				String recipeId = "";
				String mtrlFace = "";
				
				if("Top".equals(mtrlFaceCd) || "Both".equals(mtrlFaceCd)) {
					recipeId = topRcpId;
					mtrlFace = "TTT";
				} else {
					recipeId = bottomRcpId;
					mtrlFace = "TBT";
				}
				
				logger.info("###### 3> RECIPE_ID : " + recipeId);
//				if("Top".equals(recipeList.get(0).get("workFace")) || "Both".equals(recipeList.get(0).get("workFace"))) {
//					mtrlFace = "TTT";					
//				} else if("Bottom".equals(recipeList.get(0).get("workFace"))) {
//					mtrlFace = "TBT";
//				}
				mtrlFace = "TTT";
					
				logger.info("###### 4> WORK_FACE : " + mtrlFace);
				String jobSeqId = "1";
				
				createWorkJob(session, siteId, cid, tid, workId, jobSeqId, batchId, lotId, lotQty, inPortId, inCarrId, inCarrTyp, outPortId, outCarrId, outCarrTyp, 
						prodDefId, procDefId, procSgmtId, recipeId, "Y", mtrlFace, userId);
				
				logger.info("###### 5> CREATE WN_WORK_JOB Completed");
				logger.info("###### 6> PROD_MTRL_SIZE >> " +  prodMtrlList.size());
				for(int i = 0; i < prodMtrlList.size(); i++) {
					String slotNo = prodMtrlList.get(i).getSlotNo();
					String prodMtrlId = prodMtrlList.get(i).getProdMtrlId();
					
					//자주검사
					if(selfInspCnt == null || "".equals(selfInspCnt)) 
						selfInspCnt = "0";
					int selfInspCntNum = Integer.parseInt(selfInspCnt);
					
					// slot별 Flag 찍을 지 여부
					String inspYnProdMtrl = i < selfInspCntNum ? "Y":"N";
					
					createWorkJobSlotInfo(session, siteId, cid, tid, workId, jobSeqId, lotId, slotNo, prodMtrlId, inspYnProdMtrl, mtrlFace, userId);
					
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
		
		return resultCnt;
	}
	
	public int createWorkForECO(String siteId, String cid, String tid, String userId, String eqpId, String workId, String batchYn, String inlineYn, String eqpInlineId, String inlineStatCd, String dspWorkId,
			String lotId, String batchId, String carrId, String inPortId, String inCarrId, String inCarrTyp, String lotQty,
			String outPortId, String outCarrId, String outCarrTyp, String prodDefId, String procDefId, String procSgmtId, String ecoId) throws Exception {
		SqlSession session = MybatisSession.getSqlSessionInstance();
	
		WfsMapper wfsMapper = session.getMapper(WfsMapper.class);
		
		
		
		
		
//		logger.info("RECIPE LIST >> " + recipeList.size());
//		int recipeLen = recipeList.size();
		
		int resultCnt = -1;
		
		try {
			
			Map<String,String> ecoParam = new HashMap<String,String>();
			ecoParam.put("siteId", siteId);
			ecoParam.put("ecoId", ecoId);
			ecoParam.put("prodDefId", prodDefId);
			ecoParam.put("procDefId", procDefId);
			ecoParam.put("procSgmtId", procSgmtId);
			ecoParam.put("lotId", lotId);
			ecoParam.put("eqpId", eqpId);
			
			Map<String,String> ecoInfo = wfsMapper.selectEcoLotInfo(ecoParam);
			
			//insert WN_WORK_STAT
			createWorkStat(session, siteId, cid, tid, userId, workId, eqpId, batchYn, inlineYn, eqpInlineId, inlineStatCd, dspWorkId);
			
			String topRcpId = ecoInfo.get("TOP_RCP_ID");
			String bottomRcpId =  ecoInfo.get("BOTTOM_RCP_ID");
			
			int jobSeqId = 1;
			
			// Top Recipe 존재
			if(topRcpId != null) {
				String mtrlFace = "TTT";
				createWorkJob(session, siteId, cid, tid, workId, String.valueOf(jobSeqId), batchId, lotId, lotQty, inPortId, inCarrId, inCarrTyp, outPortId, outCarrId, outCarrTyp, 
						prodDefId, procDefId, procSgmtId, topRcpId, "Y", mtrlFace, userId);
				
				Map<String,String> slotInfoParam =  new HashMap<String, String>();
				slotInfoParam.put("siteId", siteId);
				slotInfoParam.put("useStatCd", IsUsable.Usable.name());
				slotInfoParam.put("lotId", lotId);

				
				List<Map<String,String>> prodMtrlList = wfsMapper.selectProdMtrlList(slotInfoParam);
				for(Map<String,String> m : prodMtrlList) {
					String slotNo = m.get("SLOT_NO");
					String prodMtrlId = m.get("PROD_MTRL_ID");
					
					createWorkJobSlotInfo(session, siteId, cid, tid, workId, String.valueOf(jobSeqId), lotId, slotNo, prodMtrlId, "N", mtrlFace, userId);
				}
				
				jobSeqId++;
			}
			
			// Bottom Recipe 존재
			if(bottomRcpId != null) {
				String mtrlFace = "TBT";
				createWorkJob(session, siteId, cid, tid, workId, String.valueOf(jobSeqId), batchId, lotId, lotQty, inPortId, inCarrId, inCarrTyp, outPortId, outCarrId, outCarrTyp, 
						prodDefId, procDefId, procSgmtId, bottomRcpId, "Y", mtrlFace, userId);
				
				Map<String,String> slotInfoParam =  new HashMap<String, String>();
				slotInfoParam.put("siteId", siteId);
				slotInfoParam.put("useStatCd", IsUsable.Usable.name());
				slotInfoParam.put("lotId", lotId);
				
				List<Map<String,String>> prodMtrlList = wfsMapper.selectProdMtrlList(slotInfoParam);
				for(Map<String,String> m : prodMtrlList) {
					String slotNo = m.get("SLOT_NO");
					String prodMtrlId = m.get("PROD_MTRL_ID");
					
					createWorkJobSlotInfo(session, siteId, cid, tid, workId, String.valueOf(jobSeqId), lotId, slotNo, prodMtrlId, "N", mtrlFace, userId);
				}
			}
			
			resultCnt = 1;
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
	
	public int createWorkForECO_WorkFace(String siteId, String cid, String tid, String userId, String eqpId, String workId, String batchYn, String inlineYn, String eqpInlineId, String inlineStatCd, String dspWorkId,
			String lotId, String batchId, String carrId, String inPortId, String inCarrId, String inCarrTyp, String lotQty,
			String outPortId, String outCarrId, String outCarrTyp, String prodDefId, String procDefId, String procSgmtId, String ecoId, String currenntMtrlLoadingSide, String nextWorkSide, String toolHasFlipperYn, String mtrlFaceCd) throws Exception {
		SqlSession session = MybatisSession.getSqlSessionInstance();
	
		WfsMapper wfsMapper = session.getMapper(WfsMapper.class);
		
		int resultCnt = -1;
		
		try {
			
			//MaterialFaceList
			String[] mtrlFace = this.generateMtrlFace(currenntMtrlLoadingSide, mtrlFaceCd.toUpperCase(), nextWorkSide, (toolHasFlipperYn.equals("Y") ? true : false), mtrlFaceCd);
			logger.info("###### 0> Material Face >> " + mtrlFace[0]);
			
			
			Map<String,String> ecoParam = new HashMap<String,String>();
			ecoParam.put("siteId", siteId);
			ecoParam.put("ecoId", ecoId);
			ecoParam.put("prodDefId", prodDefId);
			ecoParam.put("procDefId", procDefId);
			ecoParam.put("procSgmtId", procSgmtId);
			ecoParam.put("lotId", lotId);
			ecoParam.put("eqpId", eqpId);
			
			logger.info("###" + ecoParam.toString());
			Map<String,String> ecoInfo = wfsMapper.selectEcoLotInfo(ecoParam);
			logger.info(ecoInfo.toString());
			
			//insert WN_WORK_STAT
			createWorkStat(session, siteId, cid, tid, userId, workId, eqpId, batchYn, inlineYn, eqpInlineId, inlineStatCd, dspWorkId);
			
			String topRcpId = ecoInfo.get("TOP_RCP_ID");
			String bottomRcpId =  ecoInfo.get("BOTTOM_RCP_ID");
			
			int jobSeqId = 1;
			
			// 단면 작업
			if("Top".equals(mtrlFaceCd) || "Both".equals(mtrlFaceCd)) {
				// Top Recipe 존재
				if(topRcpId != null) {
					String topMtrlFace = mtrlFace[0];
					createWorkJob(session, siteId, cid, tid, workId, String.valueOf(jobSeqId), batchId, lotId, lotQty, inPortId, inCarrId, inCarrTyp, outPortId, outCarrId, outCarrTyp, 
							prodDefId, procDefId, procSgmtId, topRcpId, "Y", topMtrlFace, userId);
					
					Map<String,String> slotInfoParam =  new HashMap<String, String>();
					slotInfoParam.put("siteId", siteId);
					slotInfoParam.put("useStatCd", IsUsable.Usable.name());
					slotInfoParam.put("lotId", lotId);

					
					List<Map<String,String>> prodMtrlList = wfsMapper.selectProdMtrlList(slotInfoParam);
					for(Map<String,String> m : prodMtrlList) {
						String slotNo = m.get("SLOT_NO");
						String prodMtrlId = m.get("PROD_MTRL_ID");
						
						createWorkJobSlotInfo(session, siteId, cid, tid, workId, String.valueOf(jobSeqId), lotId, slotNo, prodMtrlId, "N", topMtrlFace, userId);
					}
				}
			} else if ( "Bottom".equals(mtrlFaceCd)) {
				// Bottom Recipe 존재
				if(bottomRcpId != null) {
					String bottomMtrlFace = mtrlFace[0];
					createWorkJob(session, siteId, cid, tid, workId, String.valueOf(jobSeqId), batchId, lotId, lotQty, inPortId, inCarrId, inCarrTyp, outPortId, outCarrId, outCarrTyp, 
							prodDefId, procDefId, procSgmtId, bottomRcpId, "Y", bottomMtrlFace, userId);
					
					Map<String,String> slotInfoParam =  new HashMap<String, String>();
					slotInfoParam.put("siteId", siteId);
					slotInfoParam.put("useStatCd", IsUsable.Usable.name());
					slotInfoParam.put("lotId", lotId);
					
					List<Map<String,String>> prodMtrlList = wfsMapper.selectProdMtrlList(slotInfoParam);
					for(Map<String,String> m : prodMtrlList) {
						String slotNo = m.get("SLOT_NO");
						String prodMtrlId = m.get("PROD_MTRL_ID");
						
						createWorkJobSlotInfo(session, siteId, cid, tid, workId, String.valueOf(jobSeqId), lotId, slotNo, prodMtrlId, "N", bottomMtrlFace, userId);
					}
				}
			} else {
				
				// Top Recipe 존재
				if(topRcpId != null) {
					String topMtrlFace = mtrlFace[0];
					createWorkJob(session, siteId, cid, tid, workId, String.valueOf(jobSeqId), batchId, lotId, lotQty, inPortId, inCarrId, inCarrTyp, outPortId, outCarrId, outCarrTyp, 
							prodDefId, procDefId, procSgmtId, topRcpId, "Y", topMtrlFace, userId);
					
					Map<String,String> slotInfoParam =  new HashMap<String, String>();
					slotInfoParam.put("siteId", siteId);
					slotInfoParam.put("useStatCd", IsUsable.Usable.name());
					slotInfoParam.put("lotId", lotId);

					
					List<Map<String,String>> prodMtrlList = wfsMapper.selectProdMtrlList(slotInfoParam);
					for(Map<String,String> m : prodMtrlList) {
						String slotNo = m.get("SLOT_NO");
						String prodMtrlId = m.get("PROD_MTRL_ID");
						
						createWorkJobSlotInfo(session, siteId, cid, tid, workId, String.valueOf(jobSeqId), lotId, slotNo, prodMtrlId, "N", topMtrlFace, userId);
					}
					
					jobSeqId++;
				}
				
				// Bottom Recipe 존재
				if(bottomRcpId != null) {
					String bottomMtrlFace = mtrlFace[1];
					createWorkJob(session, siteId, cid, tid, workId, String.valueOf(jobSeqId), batchId, lotId, lotQty, inPortId, inCarrId, inCarrTyp, outPortId, outCarrId, outCarrTyp, 
							prodDefId, procDefId, procSgmtId, bottomRcpId, "Y", bottomMtrlFace, userId);
					
					Map<String,String> slotInfoParam =  new HashMap<String, String>();
					slotInfoParam.put("siteId", siteId);
					slotInfoParam.put("useStatCd", IsUsable.Usable.name());
					slotInfoParam.put("lotId", lotId);
					
					List<Map<String,String>> prodMtrlList = wfsMapper.selectProdMtrlList(slotInfoParam);
					for(Map<String,String> m : prodMtrlList) {
						String slotNo = m.get("SLOT_NO");
						String prodMtrlId = m.get("PROD_MTRL_ID");
						
						createWorkJobSlotInfo(session, siteId, cid, tid, workId, String.valueOf(jobSeqId), lotId, slotNo, prodMtrlId, "N", bottomMtrlFace, userId);
					}
				}
				
				
			}
			
			resultCnt = 1;
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
	

	/**
	 * CreateWork For Batch
	 * @param workRequestVo
	 * @param recipeListXML
	 * @return
	 * @throws Exception
	 */
	public int createWorkForBatch(CreateWorkRequestVo workRequestVo, String recipeListXML) throws Exception {
		
		String siteId = workRequestVo.getSiteId();
		String cid = workRequestVo.getCid();
		String tid = workRequestVo.getTid();
		String workId = workRequestVo.getWorkId();
		String userId = workRequestVo.getUserId();
		String lotId = workRequestVo.getLotId();
		String batchId = workRequestVo.getBatchId();
		String selfInspCnt = workRequestVo.getSelfInspCnt();
		String eqpId = workRequestVo.getEqpId();
		String batchYn = workRequestVo.getBatchYn();
		String inlineYn = workRequestVo.getInlineYn();
		String eqpInlineId = workRequestVo.getEqpInlineId();
		String dspWorkId = workRequestVo.getDspWorkId();
		String inlineStatCd = workRequestVo.getInlineStatCd();
		
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		XMLManager xmlMamager = new XMLManager();
		
		WfsMapper wfsMapper = session.getMapper(WfsMapper.class);
		
		workRequestVo.setSession(session);
		workRequestVo.setXmlManager(xmlMamager);
		workRequestVo.setWfsMapper(wfsMapper);
				
		// Recipe 변경 작업 적용
		Map<String,String> recipeMap = xmlMamager.getXMLtoListMap(recipeListXML).get(0);
		String mtrlFaceCd = recipeMap.get("MTRL_FACE_CD");
		String topRcpId = recipeMap.get("TOP_RCP_ID");
		String bottomRcpId = recipeMap.get("BOTTOM_RCP_ID");
		
		String currentMtrlSide = workRequestVo.getCurrenntMtrlLoadingSide();
		String nextWorkSide = workRequestVo.getNextWorkSide();
		
		boolean isToolHasFlipper = workRequestVo.isToolHasFlipper();
		
		//MaterialFaceList
		logger.info("###### 0> Current Loading Side >> " + currentMtrlSide);
		logger.info("###### 0> Current Loading Side >> " + mtrlFaceCd);
		logger.info("###### 0> Current Loading Side >> " + nextWorkSide);
		String[] mtrlFace = this.generateMtrlFace(currentMtrlSide, mtrlFaceCd.toUpperCase(), nextWorkSide, isToolHasFlipper, mtrlFaceCd);
		logger.info("###### 0> Material Face >> " + mtrlFace[0]);
		
		int resultCnt = -1;
		
		try {
			Map<String, String> batchLotParam = new HashMap<String, String>();
			batchLotParam.put("siteId", siteId);
			batchLotParam.put("useStatCd", "Usable");
			batchLotParam.put("statCd", "Active");
			batchLotParam.put("batchId", batchId);
			
			List<Map<String,String>> batchLotList = wfsMapper.selectBatchLotList(batchLotParam);
			logger.info("###### 1> batchLotList Size >> " + batchLotList.size());
			
			//insert WN_WORK_STAT
			createWorkStat(session, siteId, cid, tid, userId, workId, eqpId, batchYn, inlineYn, eqpInlineId, inlineStatCd, dspWorkId);
			logger.info("###### 1> CREATE WN_WORK_STAT Completed");
			
			int jobSeq = 1;
			for(Map<String,String> lot : batchLotList) {
				//select Panel List 
				List<TnPosProducedMaterial> prodMtrlList = wfsMapper.selectTnPosProducedMaterialByLotId(lot.get("LOT_ID"));
				logger.info("###### 2> PANEL LIST >> " + prodMtrlList.size());
				
				//insert WN_WORK_JOB
				// 단면 작업
				if("Top".equals(mtrlFaceCd) || "Bottom".equals(mtrlFaceCd) || "Both".equals(mtrlFaceCd)) {
					
					String recipeId = "";
					if("Top".equals(mtrlFaceCd) || "Both".equals(mtrlFaceCd)) {
						recipeId = topRcpId;
					}
					else {
						recipeId = bottomRcpId;
					}
					
					logger.info("###### 3> RECIPE_ID : " + recipeId);
						
					logger.info("###### 4> WORK_FACE : " + mtrlFace[0]);
					
					workRequestVo.setCarrId(lot.get("CARR_ID"));
					workRequestVo.setInCarrId(lot.get("CARR_ID"));
					workRequestVo.setOutCarrId(lot.get("CARR_ID"));
					workRequestVo.setInPortId(lot.get("RESV_PORT_ID"));
					workRequestVo.setOutPortId(lot.get("RESV_PORT_ID"));
					workRequestVo.setLotQty(String.valueOf(lot.get("QTY")));
					
					this.createWorkJob(session, siteId, cid, tid, workId, lot.get("BATCH_SEQ"), batchId, lot.get("LOT_ID"), workRequestVo, recipeId, "Y", mtrlFace[0], userId);
					
					
					logger.info("###### 5> CREATE WN_WORK_JOB Completed");
					logger.info("###### 6> PROD_MTRL_SIZE >> " +  prodMtrlList.size());
//					createWorkJobSlotInfo(session, siteId, cid, tid, workId, jobSeqId, lot.get("lotId"), slotNo, prodMtrlId, inspYnProdMtrl, mtrlFace, userId);
					this.insertWorkSlotPerProdMtrl(workRequestVo, lot.get("BATCH_SEQ"), lot.get("LOT_ID"), mtrlFace[0], prodMtrlList);
					
				} 
				// 양면 작업 Both_Flip
				else {
//					this.createBothFlipWork(jobSeq, mtrlFace, workRequestVo, prodMtrlList, true);
				}
				jobSeq++;
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
	
	
	private void createBothFlipWork(int startJobSeq, String[] mtrlFaceArray, CreateWorkRequestVo workRequestVo,
									List<TnPosProducedMaterial> prodMtrlList, boolean doInsertSlotInfo) throws Exception {
		
		String siteId = workRequestVo.getSiteId();
		String cid = workRequestVo.getCid();
		String tid = workRequestVo.getTid();
		String workId = workRequestVo.getWorkId();
		String userId = workRequestVo.getUserId();
		String lotId = workRequestVo.getLotId();
		String batchId = workRequestVo.getBatchId();
		
		
		int jobSeq = startJobSeq;
		for(String mtrlFace : mtrlFaceArray) {
			
			String recipeId = this.getRecipeIdWithMtrlFaceCd(mtrlFace, workRequestVo.getRecipeList());
			
			logger.info("mtrlFace>> " + mtrlFace);
			this.createWorkJob(workRequestVo.getSession(), siteId, cid, tid, workId, String.valueOf(jobSeq), batchId, lotId, 
							workRequestVo,  recipeId, "Y", mtrlFace, userId);
			
			if(doInsertSlotInfo) {
				logger.info("3");
				this.insertWorkSlotPerProdMtrl(workRequestVo, String.valueOf(jobSeq), lotId, mtrlFace, prodMtrlList);
				
			}
			
			jobSeq++;
			
		}
	}
	
	/**
	 * INSERT WN_WORK_STAT
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param workId
	 * @param eqpId
	 * @param batchYn
	 * @param inlineYn
	 * @param eqpInlineId
	 * @param inlineStatCd
	 * @param dspWorkId
	 * @return
	 */
	private int createWorkStat(SqlSession session, String siteId, String cid, String tid, String userId, String workId, 
								String eqpId, String batchYn, String inlineYn, String eqpInlineId, String inlineStatCd, String dspWorkId) throws Exception{
		WorkMapper mapper = session.getMapper(WorkMapper.class);
		
		int resultCnt = -1;
		
		try {
			WnWorkStat wnWorkStat = new WnWorkStat();
			wnWorkStat.setSiteId(siteId);
			wnWorkStat.setEvntNm(cid);
			wnWorkStat.setCrtUserId(userId);
			wnWorkStat.setMdfyUserId(userId);
			wnWorkStat.setTid(tid);
			wnWorkStat.setWorkId(workId);
			wnWorkStat.setEqpId(eqpId);
			wnWorkStat.setWorkStatCd(StatCd.Active.name());
			wnWorkStat.setUseStatCd(IsUsable.Usable.name());
			wnWorkStat.setBatchYn(batchYn);
			wnWorkStat.setInlineYn(inlineYn);
			wnWorkStat.setEqpInlineId(eqpInlineId);
			wnWorkStat.setInlineStatCd(inlineStatCd);
			wnWorkStat.setDspWorkId(dspWorkId);
			
			resultCnt = mapper.createWnWorkStat(wnWorkStat);
			
			if(resultCnt > 0) {
				// insert WH_WORK_STAT
				mapper.createWhWorkStat(wnWorkStat.getObjId());
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}		
		return resultCnt;
	}
	
	
	/**
	 * INSERT WN_WORK_JOB
	 * @param session
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param workId
	 * @param jobSeqId
	 * @param batchId
	 * @param lotId
	 * @param qty
	 * @param inPortId
	 * @param inCarrId
	 * @param inCarrTyp
	 * @param outPortId
	 * @param outCarrId
	 * @param outCarrTyp
	 * @param prodDefId
	 * @param procDefId
	 * @param procSgmtId
	 * @param rcpDefId
	 * @param rcpReadyYn
	 * @param userId
	 * @return
	 */
	private int createWorkJob(SqlSession session, String siteId, String cid, String tid, String workId, 
													String jobSeqId, String batchId, String lotId, CreateWorkRequestVo vo, 
													String rcpDefId, String rcpReadyYn, String workFaceCd, 
													String userId) throws Exception{
		
		WorkMapper mapper = session.getMapper(WorkMapper.class);
		
		int resultCnt = -1;
		
		try {
			WnWorkJob param = new WnWorkJob();
			param.setSiteId(siteId);
			param.setEvntNm(cid);
			param.setTid(tid);
			param.setMdfyUserId(userId);
			param.setCrtUserId(userId);
			param.setUseStatCd(IsUsable.Usable.name());
			param.setWorkId(workId);
			param.setJobSeqId(jobSeqId);
			param.setBatchId(batchId);
			param.setLotId(lotId);
			
			param.setJobQty(vo.getLotQty());
			param.setInPortId(vo.getInPortId());
			param.setInCarrId(vo.getInCarrId());
			param.setInCarrTyp(vo.getInCarrTyp());
			param.setOutPortId(vo.getOutPortId());
			param.setOutCarrId(vo.getOutCarrId());
			param.setOutCarrTyp(vo.getOutCarrTyp());
			param.setProdDefId(vo.getProdDefId());
			param.setProcDefId(vo.getProcDefId());
			param.setProcSgmtId(vo.getProcSgmtId());
			
			
			param.setRcpDefId(rcpDefId);
			param.setRcpReadyYn(rcpReadyYn);
			param.setMdfyUserId(userId);
			param.setCrtUserId(userId);
			param.setJobStatCd(StatCd.Active.name());
			param.setWorkFaceCd(workFaceCd);
			
			
//			logger.info("WN_WORK_JOB @@@@@@@@@@@@@@@@@@@@@");
//			logger.info(param.getSiteId());
//			logger.info(param.getWorkId());
//			logger.info(param.getJobSeqId());
			resultCnt = mapper.createWnWorkJob(param);
			if(resultCnt > 0) {
				mapper.createWhWorkJob(param.getObjId());
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return resultCnt;
	}
	
	
	
	
	/**
	 * INSERT WN_WORK_JOB
	 * @param session
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param workId
	 * @param jobSeqId
	 * @param batchId
	 * @param lotId
	 * @param qty
	 * @param inPortId
	 * @param inCarrId
	 * @param inCarrTyp
	 * @param outPortId
	 * @param outCarrId
	 * @param outCarrTyp
	 * @param prodDefId
	 * @param procDefId
	 * @param procSgmtId
	 * @param rcpDefId
	 * @param rcpReadyYn
	 * @param userId
	 * @return
	 */
	private int createWorkJob(SqlSession session, String siteId, String cid, String tid, String workId, String jobSeqId, String batchId, String lotId, String qty, 
			String inPortId, String inCarrId, String inCarrTyp, String outPortId, String outCarrId, String outCarrTyp, 
			String prodDefId, String procDefId, String procSgmtId, String rcpDefId, String rcpReadyYn, String workFaceCd, String userId) throws Exception{
		
		WorkMapper mapper = session.getMapper(WorkMapper.class);
		
		int resultCnt = -1;
		
		try {
			WnWorkJob param = new WnWorkJob();
			param.setSiteId(siteId);
			param.setEvntNm(cid);
			param.setTid(tid);
			param.setMdfyUserId(userId);
			param.setCrtUserId(userId);
			param.setUseStatCd(IsUsable.Usable.name());
			param.setWorkId(workId);
			param.setJobSeqId(jobSeqId);
			param.setBatchId(batchId);
			param.setLotId(lotId);
			param.setInPortId(inPortId);
			param.setInCarrId(inCarrId);
			param.setInCarrTyp(inCarrTyp);
			param.setOutPortId(outPortId);
			param.setOutCarrId(outCarrId);
			param.setOutCarrTyp(outCarrTyp);
			param.setJobQty(qty);
			param.setProdDefId(prodDefId);
			param.setProcDefId(procDefId);
			param.setProcSgmtId(procSgmtId);
			param.setRcpDefId(rcpDefId);
			param.setRcpReadyYn(rcpReadyYn);
			param.setMdfyUserId(userId);
			param.setCrtUserId(userId);
			param.setJobStatCd(StatCd.Active.name());
			param.setWorkFaceCd(workFaceCd);
			
			
//			logger.info("WN_WORK_JOB @@@@@@@@@@@@@@@@@@@@@");
//			logger.info(param.getSiteId());
//			logger.info(param.getWorkId());
//			logger.info(param.getJobSeqId());
			resultCnt = mapper.createWnWorkJob(param);
			if(resultCnt > 0) {
				mapper.createWhWorkJob(param.getObjId());
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return resultCnt;
	}
	
	
	/**
	 * Insert WorkSlot while iterate Prod Mtrl List.
	 * @param requestVo
	 * @param jobSeqId
	 * @param lotId
	 * @param mtrlFace
	 * @param prodMtrlList
	 * @throws Exception
	 */
	private void insertWorkSlotPerProdMtrl (CreateWorkRequestVo requestVo, String jobSeqId, String lotId, 
											String mtrlFace, List<TnPosProducedMaterial> prodMtrlList) 
													throws Exception {
		
		String selfInspCnt = requestVo.getSelfInspCnt();
		
		for(int i = 0; i < prodMtrlList.size(); i++) {
			String slotNo = prodMtrlList.get(i).getSlotNo();
			String prodMtrlId = prodMtrlList.get(i).getProdMtrlId();
			
			//자주검사
			if(selfInspCnt == null || "".equals(selfInspCnt)) 
				selfInspCnt = "0";
			int selfInspCntNum = Integer.parseInt(selfInspCnt);
			
			// slot별 Flag 찍을 지 여부
			String inspYnProdMtrl = i < selfInspCntNum ? "Y":"N";
			
			this.createWorkJobSlotInfo(requestVo.getSession(), requestVo.getSiteId(), requestVo.getCid(), 
					requestVo.getTid(), requestVo.getWorkId(), jobSeqId, lotId, slotNo, prodMtrlId, 
					inspYnProdMtrl, mtrlFace, requestVo.getUserId());
			
		}
	}
	
	private void insertWorkSlotPerProdMtrlRepair (CreateWorkRequestVo requestVo, String jobSeqId, String lotId, 
			String mtrlFace, List<Map<String,String>> prodMtrlList) 
					throws Exception {


		for(int i = 0; i < prodMtrlList.size(); i++) {
			String slotNo = String.valueOf(prodMtrlList.get(i).get("SLOT_NO"));
			String prodMtrlId = prodMtrlList.get(i).get("PROD_MTRL_ID");
			

			this.createWorkJobSlotInfo(requestVo.getSession(), requestVo.getSiteId(), requestVo.getCid(), 
			requestVo.getTid(), requestVo.getWorkId(), jobSeqId, lotId, slotNo, prodMtrlId, 
			"N", mtrlFace, requestVo.getUserId());
		}
	}
		
	
	
	/**
	 * INSERT WN_WORK_JOB_SLOT_INFO
	 * @param session
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param workId
	 * @param jobSeqId
	 * @param lotId
	 * @param slotNo
	 * @param prodMtrlId
	 * @param selfInspYn
	 * @param mtrlFaceCd
	 * @param userId
	 * @return
	 */
	private int createWorkJobSlotInfo(SqlSession session, String siteId, String cid, String tid, String workId, 
									String jobSeqId, String lotId, String slotNo, String prodMtrlId, String selfInspYn, String mtrlFaceCd, String userId) throws Exception{
		
		WorkMapper mapper = session.getMapper(WorkMapper.class);
		
		int resultCnt = -1;
		
		try {
			WnWorkJobSlotInfo param = new WnWorkJobSlotInfo();
			// SET
			param.setSiteId(siteId);
			param.setEvntNm(cid);
			param.setTid(tid);
			param.setCrtUserId(userId);
			param.setMdfyUserId(userId);
			param.setUseStatCd(IsUsable.Usable.name());
			param.setWorkId(workId);
			param.setJobSeqId(jobSeqId);
			param.setLotId(lotId);
			param.setSlotNo(slotNo);
			param.setProdMtrlId(prodMtrlId);
			param.setSelfInspYn(selfInspYn);
			param.setMtrlFaceCd(mtrlFaceCd);
			
			
			
//			logger.info("WN_WORK_JOB_SLOT_INFO @@@@@@@@@@@@@@@@@@@@@");
//			logger.info(param.getSiteId());
//			logger.info(param.getWorkId());
//			logger.info(param.getJobSeqId());
//			logger.info(param.getSlotNo());
			
			
						
			resultCnt = mapper.createWnWorkJobSlotInfo(param);
			if(resultCnt > 0) {
				mapper.createWhWorkJobSlotInfo(param.getObjId());
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return resultCnt;
	}
	
		
	/**
	 * UPDATE WN_WORK_STAT
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param workId
	 * @param userId
	 * @param inlineStatCd
	 * @return
	 */
	public int updateWorkStat(String siteId, String cid, String tid, String workId, String userId, String inlineStatCd) throws Exception{
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WorkMapper mapper = session.getMapper(WorkMapper.class);
		
		int resultCnt = -1;
		
		try {
			WnWorkStat param = new WnWorkStat();
			//SET
			param.setEvntNm(cid);
			param.setMdfyUserId(userId);
			param.setTid(tid);
			if(!"".equals(inlineStatCd)) param.setInlineStatCd(inlineStatCd);
			
			
			//WHERE
			param.setpUseStatCd(IsUsable.Usable.name());
			param.setpSiteId(siteId);
			param.setpWorkId(workId);
			
			
			//update WN_WORK_STAT
			resultCnt = mapper.updateWnWorkStat(param);
			
			if(resultCnt > 0) {
				// insert WH_WORK_STAT
				mapper.createWhWorkStat(param.getObjId());
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
	
	/**
	 * 자주검사 1MORE RSN_CD update
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param workId
	 * @param userId
	 * @param rsnCd
	 * @return
	 * @throws Exception
	 */
	public int updateWorkRsnCd(String siteId, String cid, String tid, String workId, String userId, String rsnCd) throws Exception{
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WorkMapper mapper = session.getMapper(WorkMapper.class);
		
		int resultCnt = -1;
		
		try {
			WnWorkStat param = new WnWorkStat();
			//SET
			param.setEvntNm(cid);
			param.setMdfyUserId(userId);
			param.setTid(tid);
			param.setRsnCd(rsnCd); //1MORE
			
			
			//WHERE
			param.setpUseStatCd(IsUsable.Usable.name());
			param.setpSiteId(siteId);
			param.setpWorkId(workId);
			
			
			//update WN_WORK_STAT
			resultCnt = mapper.updateWnWorkStat(param);
			
			if(resultCnt > 0) {
				// insert WH_WORK_STAT
				mapper.createWhWorkStat(param.getObjId());
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
	
	
	/**
	 * SELECT WN_WORK_STAT By LOT_ID
	 * @param siteId
	 * @param workId
	 * @return
	 */
	public List<WnWorkStat> getWorkStatByWorkId(String siteId, String workId) throws Exception{
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WorkMapper mapper = session.getMapper(WorkMapper.class);
		
		List<WnWorkStat> wnWorkStatList = null;
		
		try {
			WnWorkStat param = new WnWorkStat();
			param.setpUseStatCd(IsUsable.Usable.name());
			param.setpSiteId(siteId);
			param.setpWorkId(workId);
			
			wnWorkStatList = mapper.selectWnWorkStat(param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return wnWorkStatList;
	}
	
	public int updateWnWorkJobEvent(String siteId, String cid, String tid, String userId, String workId, String jobSeqId) throws Exception{
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WorkMapper mapper = session.getMapper(WorkMapper.class);
		
		int resultCnt = -1;
		
		try {
			WnWorkJob param = new WnWorkJob();
			
			//SET
			param.setEvntNm(cid);
			param.setTid(tid);
			param.setMdfyUserId(userId);
			
			//WHERE
			param.setpSiteId(siteId);
			param.setpWorkId(workId);
			param.setpJobSeqId(jobSeqId);
			param.setpUseStatCd(IsUsable.Usable.name());
			param.setpJobStatCd(StatCd.Active.name());
			
			resultCnt = mapper.updateWnWorkJob(param);
			
			if(resultCnt > 0) {
				mapper.createWhWorkJob(param.getObjId());
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
	
	public int updateWnWorkJobSlotInfoForEndTm(String siteId, String cid, String tid, String userId, 
												String workId, String jobSeqId, String prodMtrlId, String slotNo ) throws Exception{
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WorkMapper mapper = session.getMapper(WorkMapper.class);
		
		int resultCnt = -1;
		
		try {
			WnWorkJobSlotInfo param = new WnWorkJobSlotInfo();
			
			//SET
			param.setEvntNm(cid);
			param.setTid(tid);
			param.setMdfyUserId(userId);
			param.setProdMtrlEndTm("END"); //SYADATE 
			
			//WHERE
			param.setpSiteId(siteId);
			param.setpWorkId(workId);
			param.setpJobSeqId(jobSeqId);
			param.setpProdMtrlId(prodMtrlId);
			param.setpSlotNo(slotNo);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			resultCnt = mapper.updateWnWorkJobSlotInfo(param);
			
			if(resultCnt > 0) {
				mapper.createWhWorkJobSlotInfo(param.getObjId());
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
	
	public int updateWnWorkJobSlotInfoForStartTm(String siteId, String cid, String tid, String userId, String workId, String jobSeqId, String prodMtrlId, String slotNo ) throws Exception{
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WorkMapper mapper = session.getMapper(WorkMapper.class);
		
		int resultCnt = -1;
		
		try {
			WnWorkJobSlotInfo param = new WnWorkJobSlotInfo();
			
			//SET
			param.setEvntNm(cid);
			param.setTid(tid);
			param.setMdfyUserId(userId);
			param.setProdMtrlStrtTm("STRT"); //SYADATE 
			
			//WHERE
			param.setpSiteId(siteId);
			param.setpWorkId(workId);
			param.setpJobSeqId(jobSeqId);
			param.setpProdMtrlId(prodMtrlId);
			param.setpSlotNo(slotNo);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			resultCnt = mapper.updateWnWorkJobSlotInfo(param);
			
			if(resultCnt > 0) {
				mapper.createWhWorkJobSlotInfo(param.getObjId());
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
	
	public int updateWnWorkJobSlotInfoForScrap(String siteId, String cid, String tid, String userId, String workId, String jobSeqId, String prodMtrlId, String slotNo ) throws Exception{
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WorkMapper mapper = session.getMapper(WorkMapper.class);
		
		int resultCnt = -1;
		
		try {
			WnWorkJobSlotInfo param = new WnWorkJobSlotInfo();
			
			//SET
			param.setEvntNm(cid);
			param.setTid(tid);
			param.setMdfyUserId(userId);
			//param.setProdMtrlStrtTm("STRT"); //SYADATE 
			
			//WHERE
			param.setpSiteId(siteId);
			param.setpWorkId(workId);
			param.setpJobSeqId(jobSeqId);
			param.setpProdMtrlId(prodMtrlId);
			param.setpSlotNo(slotNo);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			resultCnt = mapper.updateWnWorkJobSlotInfo(param);
			
			if(resultCnt > 0) {
				mapper.createWhWorkJobSlotInfo(param.getObjId());
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
	
	public int updateWnWorkJobSlotInfoForPanelIdScan(String siteId, String cid, String tid, String userId, String workId, String jobSeqId, String scanProdMtrlId, String slotNo ) throws Exception{
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WorkMapper mapper = session.getMapper(WorkMapper.class);
		
		int resultCnt = -1;
		
		try {
			WnWorkJobSlotInfo param = new WnWorkJobSlotInfo();
			
			//SET
			param.setEvntNm(cid);
			param.setTid(tid);
			param.setMdfyUserId(userId);
			param.setScanProdMtrlId(scanProdMtrlId); 
			
			//WHERE
			param.setpSiteId(siteId);
			param.setpWorkId(workId);
			param.setpJobSeqId(jobSeqId);
//			param.setpProdMtrlId(prodMtrlId);
			param.setpSlotNo(slotNo);
			param.setpUseStatCd(IsUsable.Usable.name());
			
			resultCnt = mapper.updateWnWorkJobSlotInfo(param);
			
			if(resultCnt > 0) {
				mapper.createWhWorkJobSlotInfo(param.getObjId());
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
	
	/**
	 * Finish Work By WORK_ID
	 * WORK_ENDED
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param workId
	 * @return
	 */
	public int workEnded(String siteId, String cid, String tid, String userId, String workId) throws Exception{
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WorkMapper mapper = session.getMapper(WorkMapper.class);
		
		int resultCnt = -1;
		
		try {
			
			WnWorkStat workStatParam = new WnWorkStat();
			//SET
			workStatParam.setEvntNm(cid);
			workStatParam.setTid(tid);
			workStatParam.setMdfyUserId(userId);
			workStatParam.setWorkStatCd(StatCd.Finished.name());
			workStatParam.setUseStatCd(IsUsable.UnUsable.name());
			
			//WHERE
			workStatParam.setpSiteId(siteId);
			workStatParam.setpWorkId(workId);
			workStatParam.setpWorkStatCd(StatCd.Active.name());
			workStatParam.setpUseStatCd(IsUsable.Usable.name());
			
			// UPDATE WN_WORK_STAT
			resultCnt = mapper.updateWnWorkStat(workStatParam);
			
			if(resultCnt > 0) {
				// Create History WH_WORK_STAT
				mapper.createWhWorkStat(workStatParam.getObjId());
				
				// DELETE WN_WORK_STAT
				mapper.deleteWnWorkStat(workStatParam.getObjId());
				
				// Select WorkJob List
				WnWorkJob workJobParam = new WnWorkJob();
				workJobParam.setpSiteId(siteId);
				workJobParam.setpWorkId(workId);
				workJobParam.setpJobStatCd(StatCd.Active.name());
				workJobParam.setpUseStatCd(IsUsable.Usable.name());
				
				
				
				List<WnWorkJob> workJobList = mapper.selectWnWorkJob(workJobParam);
				
				for(WnWorkJob j: workJobList) {
					
					WnWorkJob workJobUpdateParam = new WnWorkJob();
					// UPDATE WN_WORK_JOB	
					//SET
					workJobUpdateParam.setEvntNm(cid);
					workJobUpdateParam.setTid(tid);
					workJobUpdateParam.setMdfyUserId(userId);
					workJobUpdateParam.setJobStatCd(StatCd.Finished.name());
					workJobUpdateParam.setUseStatCd(IsUsable.UnUsable.name());
					
					//WHERE
					workJobUpdateParam.setpObjId(j.getObjId());
					
					// UPDATE WN_WORK_JOB
					int updateWorkJobCnt = mapper.updateWnWorkJob(workJobUpdateParam);
					
					if(updateWorkJobCnt > 0) {
						
						// WORK_JOB ===================
						
						// Create History WH_WORK_JOB
						mapper.createWhWorkJob(j.getObjId());
						
						// DELETE WN_WORK_JOB
						mapper.deleteWnWorkJob(j.getObjId());
						
						//SLOT_INFO ===================
						WnWorkJobSlotInfo slotInfoParam = new WnWorkJobSlotInfo();
						
						//SET 
						slotInfoParam.setEvntNm(cid);
						slotInfoParam.setTid(tid);
						slotInfoParam.setMdfyUserId(userId);
						slotInfoParam.setUseStatCd(IsUsable.UnUsable.name());
						slotInfoParam.setJobStatCd(StatCd.Finished.name());
						
						//WHERE
						slotInfoParam.setpSiteId(siteId);
						slotInfoParam.setpWorkId(workId);
						slotInfoParam.setpJobSeqId(j.getJobSeqId());
						
						//UPDATE WN_WORK_SLOT_INFO
						if(mapper.updateWnWorkJobSlotInfo(slotInfoParam) > 0) {
							//Create History WH_WORK_SLOT_INFO
							mapper.createWhWorkJobSlotInfoAllSlot(slotInfoParam);
							
							//DELETE WN_WORK_SLOT_INFO
							mapper.deleteWnWorkJobSlotInfoALLSlot(slotInfoParam);
						}
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
		return resultCnt;
	}
	
	/**
	 * WORK_ABORTED
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param workId
	 * @return
	 * @throws Exception
	 */
	public int workAborted(String siteId, String cid, String tid, String userId, String workId) throws Exception{
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WorkMapper mapper = session.getMapper(WorkMapper.class);
		
		int resultCnt = -1;
		
		try {
			
			WnWorkStat workStatParam = new WnWorkStat();
			//SET
			workStatParam.setEvntNm(cid);
			workStatParam.setTid(tid);
			workStatParam.setMdfyUserId(userId);
			workStatParam.setWorkStatCd(StatCd.Aborted.name());
			workStatParam.setUseStatCd(IsUsable.UnUsable.name());
			
			//WHERE
			workStatParam.setpSiteId(siteId);
			workStatParam.setpWorkId(workId);
			workStatParam.setpWorkStatCd(StatCd.Active.name());
			workStatParam.setpUseStatCd(IsUsable.Usable.name());
			
			// UPDATE WN_WORK_STAT
			resultCnt = mapper.updateWnWorkStat(workStatParam);
			
			if(resultCnt > 0) {
				// Create History WH_WORK_STAT
				mapper.createWhWorkStat(workStatParam.getObjId());
				
				// DELETE WN_WORK_STAT
				// deleteWnWorkStat(workStatParam.getObjId());
				
				// Select WorkJob List
				WnWorkJob workJobParam = new WnWorkJob();
				workJobParam.setpSiteId(siteId);
				workJobParam.setpWorkId(workId);
				workJobParam.setpJobStatCd(StatCd.Active.name());
				workJobParam.setpUseStatCd(IsUsable.Usable.name());
				
				
				
				List<WnWorkJob> workJobList = mapper.selectWnWorkJob(workJobParam);
				
				for(WnWorkJob j: workJobList) {
					
					WnWorkJob workJobUpdateParam = new WnWorkJob();
					// UPDATE WN_WORK_JOB	
					//SET
					workJobUpdateParam.setEvntNm(cid);
					workJobUpdateParam.setTid(tid);
					workJobUpdateParam.setMdfyUserId(userId);
					workJobUpdateParam.setJobStatCd(StatCd.Aborted.name());
					workJobUpdateParam.setUseStatCd(IsUsable.UnUsable.name());
					
					//WHERE
					workJobUpdateParam.setpObjId(j.getObjId());
					
					// UPDATE WN_WORK_JOB
					int updateWorkJobCnt = mapper.updateWnWorkJob(workJobUpdateParam);
					
					if(updateWorkJobCnt > 0) {
						
						// WORK_JOB ===================
						
						// Create History WH_WORK_JOB
						mapper.createWhWorkJob(j.getObjId());
						
						// DELETE WN_WORK_JOB
						mapper.deleteWnWorkJob(j.getObjId());
						
						//SLOT_INFO ===================
						WnWorkJobSlotInfo slotInfoParam = new WnWorkJobSlotInfo();
						
						//SET 
						slotInfoParam.setEvntNm(cid);
						slotInfoParam.setTid(tid);
						slotInfoParam.setMdfyUserId(userId);
						slotInfoParam.setUseStatCd(IsUsable.UnUsable.name());
						slotInfoParam.setJobStatCd(StatCd.Aborted.name());
						
						//WHERE
						slotInfoParam.setpSiteId(siteId);
						slotInfoParam.setpWorkId(workId);
						slotInfoParam.setpJobSeqId(j.getJobSeqId());
						
						//UPDATE WN_WORK_SLOT_INFO
						if(mapper.updateWnWorkJobSlotInfo(slotInfoParam) > 0) {
							//Create History WH_WORK_SLOT_INFO
							mapper.createWhWorkJobSlotInfoAllSlot(slotInfoParam);
							
							//DELETE WN_WORK_SLOT_INFO
							//mapper.deleteWnWorkJobSlotInfoALLSlot(slotInfoParam);
						}
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
		return resultCnt;
	}
	
	/**
	 * WORK STARTED
	 * @param siteId
	 * @param cid
	 * @param tid
	 * @param userId
	 * @param workId
	 * @return
	 */
	public int workStarted(String siteId, String cid, String tid, String userId, String workId) throws Exception{
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WorkMapper mapper = session.getMapper(WorkMapper.class);
		
		int resultCnt = -1;
		
		try {
			WnWorkStat workStatParam = new WnWorkStat();
			//SET
			workStatParam.setEvntNm(cid);
			workStatParam.setTid(tid);
			workStatParam.setMdfyUserId(userId);
			workStatParam.setWorkStartTm("START");	// SYSDATE
			
			
			//WHERE
			workStatParam.setpSiteId(siteId);
			workStatParam.setpWorkId(workId);
			workStatParam.setpUseStatCd(IsUsable.Usable.name());
			
			// UPDATE WN_WORK_STAT
			resultCnt = mapper.updateWnWorkStat(workStatParam);
			
			if(resultCnt > 0) {
				// Create History WH_WORK_STAT
				mapper.createWhWorkStat(workStatParam.getObjId());
				
				// Select WorkJob List
				WnWorkJob workJobParam = new WnWorkJob();
				workJobParam.setpSiteId(siteId);
				workJobParam.setpWorkId(workId);
				workJobParam.setpJobStatCd(StatCd.Active.name());
				workJobParam.setpUseStatCd(IsUsable.Usable.name());
				
				
				List<WnWorkJob> workJobList = mapper.selectWnWorkJob(workJobParam);
				
				for(WnWorkJob j: workJobList) {
					WnWorkJob workJobUpdateParam = new WnWorkJob();
					// UPDATE WN_WORK_JOB	
					//SET
					workJobUpdateParam.setEvntNm(cid);
					workJobUpdateParam.setTid(tid);
					workJobUpdateParam.setMdfyUserId(userId);
					
					//WHERE
					workJobUpdateParam.setpObjId(j.getObjId());
					
					// UPDATE WN_WORK_JOB
					int updateWorkJobCnt = mapper.updateWnWorkJob(workJobUpdateParam);
					
					if(updateWorkJobCnt > 0) {
						
						// WORK_JOB ===================
						// Create History WH_WORK_JOB
						mapper.createWhWorkJob(j.getObjId());
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
		return resultCnt;
	}
	
	public int dicingProcEnded(String siteId, String cid, String tid, String userId, String workId, String jobSeqId, String lotId, String bodyXML) throws Exception{
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WorkMapper mapper = session.getMapper(WorkMapper.class);
		XMLManager xmlMamager = new XMLManager();
		int resultCnt = -1;
		try {
			//TODO
			// 추후 Bulk Insert 방식으로 전환 필요
//			logger.info("######################## process XML #######################");
//			logger.info(bodyXML);
			
			
			List<Map<String,String>> processList = xmlMamager.getXMLtoListMap(bodyXML, "process");
			List<Map<String,String>> dumpList = xmlMamager.getXMLtoListMap(bodyXML, "dump");
			List<Map<String,String>> scrapList = xmlMamager.getXMLtoListMap(bodyXML, "scrap");
			
//			logger.info("######################## process #######################");
//			logger.info(">> "+ processList);
//			logger.info("Length >> "+ processList.size());
//			logger.info("first item >> " + processList.get(0).get("#text"));
			
			if(processList.get(0).get("#text") != null) {
				String [] processStringList = processList.get(0).get("#text").split(",");
//				logger.info("@@@@@@@@@@@@@@@@@@@ >> "+processStringList.length);
				for(String process : processStringList) {
					WnWorkJobCellInfo param = new WnWorkJobCellInfo();
					param.setSiteId(siteId);
					param.setWorkId(workId);
					param.setJobSeqId(jobSeqId);
					param.setLotId(lotId);
					param.setEvntNm(cid);
					param.setTid(tid);
					param.setMdfyUserId(userId);
					param.setCrtUserId(userId);
					param.setSubProdMtrlId(process);
					param.setCellGrdCd("Process");

					if(mapper.createWnWorkJobCellInfo(param) > 0) {
						mapper.createWhWorkJob(param.getObjId());
					}
				}
			}
			
			if(dumpList.get(0).get("#text") != null) {
				String [] dumpStringList = dumpList.get(0).get("#text").split(",");
				for(String dump : dumpStringList) {
					WnWorkJobCellInfo param = new WnWorkJobCellInfo();
					param.setSiteId(siteId);
					param.setWorkId(workId);
					param.setJobSeqId(jobSeqId);
					param.setLotId(lotId);
					param.setEvntNm(cid);
					param.setTid(tid);
					param.setMdfyUserId(userId);
					param.setCrtUserId(userId);
					param.setSubProdMtrlId(dump);
					param.setCellGrdCd("Dump");
					
					if(mapper.createWnWorkJobCellInfo(param) > 0) {
						mapper.createWhWorkJob(param.getObjId());
					}
				}
			}
			
			if(scrapList.get(0).get("#text") != null) {
				String [] scrapStringList = scrapList.get(0).get("#text").split(",");
				for(String scrap : scrapStringList) {
					WnWorkJobCellInfo param = new WnWorkJobCellInfo();
					param.setSiteId(siteId);
					param.setWorkId(workId);
					param.setJobSeqId(jobSeqId);
					param.setLotId(lotId);
					param.setEvntNm(cid);
					param.setTid(tid);
					param.setMdfyUserId(userId);
					param.setCrtUserId(userId);
					param.setSubProdMtrlId(scrap);
					param.setCellGrdCd("Scrap");
					
					if(mapper.createWnWorkJobCellInfo(param) > 0) {
						mapper.createWhWorkJob(param.getObjId());
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
		return resultCnt;
	}
	
	/**
	 * WORK 삭제
	 * @param siteId
	 * @param cid
	 * @param workId
	 * @return
	 */
	public int deleteWork(String siteId, String cid, String workId) throws Exception{
		
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WorkMapper mapper = session.getMapper(WorkMapper.class);
		
		int resultCnt = -1;
		try {
			WnWorkJob wParam = new WnWorkJob();
			
			wParam.setpWorkId(workId);
			List<WnWorkJob> selectWorkJobList = mapper.selectWnWorkJob(wParam);
			
			for(WnWorkJob j : selectWorkJobList) {
				WipStatDAO.getInstance().updateInitWipStat(siteId, j.getInCarrId(), cid);
			}
			
			//WN_WORK_STAT
			mapper.deleteWnWorkStatByWorkId(workId);
			
			//WN_WORK_JOB
			mapper.deleteWnWorkJobByWorkId(workId);
			
			//WN_WORK_JOB_SLOT_INFO
			mapper.deleteWnWorkJobSlotInfoByWorkId(workId);
			
			//WN_WORK_JOB_CELL_INFO
			mapper.deleteWnWorkJobCellInfoByWorkId(workId);
			
			
			
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
	
	// SR INLINE / INLINE DICING
	public int createDspWorkInfo(String siteId, String cid, String tid, String userId, String dspWorkId, String eqpId, String portId, String lotId, String dspStatCd) throws Exception {
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WorkMapper mapper = session.getMapper(WorkMapper.class);
		
		int resultCnt = -1;
		try {
			
			WnDspWorkInfo param = new WnDspWorkInfo();
			
			param.setSiteId(siteId);
			param.setDspWorkId(dspWorkId);
			param.setEqpId(eqpId);
			param.setPortId(portId);
			param.setLotId(lotId);
			param.setDspStatCd(dspStatCd);
			param.setRcpId(eqpId);
			param.setEvntNm(cid);
			param.setTid(tid);
			param.setCrtUserId(userId);
			param.setMdfyUserId(userId);
			param.setUseStatCd(IsUsable.Usable.name());
			
			
			if(mapper.createWnDspWorkInfo(param) > 0)
				mapper.createWhDspWorkInfo(param.getObjId());
			
			
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
	
	public int updateDspWorkInfo(String siteId, String cid, String tid, String userId, String dspWorkId, String dspStatCd, String rcpChkYn, String rcpId, String trackInCnfmYn, String eqpChkYn) throws Exception {
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WorkMapper mapper = session.getMapper(WorkMapper.class);
		
		int resultCnt = -1;
		try {
			
			WnDspWorkInfo param = new WnDspWorkInfo();
			
			
			param.setRcpChkYn(dspStatCd);
			param.setRcpId(rcpId);
			
			
			param.setEvntNm(cid);
			param.setMdfyUserId(userId);
			
			
			param.setpSiteId(siteId);
			param.setpDspWorkId(dspWorkId);
			
			
			if(mapper.createWnDspWorkInfo(param) > 0)
				mapper.createWhDspWorkInfo(param.getObjId());
			
			
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
	
	
	public int deleteDspWorkInfo(String siteId, String cid, String dspWorkId) throws Exception {
		SqlSession session = MybatisSession.getSqlSessionInstance();
		WorkMapper mapper = session.getMapper(WorkMapper.class);
		
		
		int resultCnt = -1;
		try {
			
			WnDspWorkInfo param = new WnDspWorkInfo();
			//WHERE
			param.setpSiteId(siteId);
			param.setpDspWorkId(dspWorkId);
			
			List<WnDspWorkInfo> wnDspWorkInfo = mapper.selectWnDspWorkInfo(param);
			
			for(WnDspWorkInfo w : wnDspWorkInfo) {
				
				WnDspWorkInfo setParam = new WnDspWorkInfo();
				//SET
				setParam.setEvntNm(cid);
				
				//WHERE
				setParam.setpSiteId(siteId);
				setParam.setpObjId(w.getObjId());
				setParam.setpDspWorkId(dspWorkId);
				
				//Update & Insert History 
				if( mapper.updateWnDspWorkInfo(setParam) > 0) {
					logger.info(setParam.getObjId());
					resultCnt ++;
					mapper.deleteWnDspWorkInfo(setParam.getObjId());
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
		return resultCnt;
	}
	
	
	
	/**
    *
    * @param crntLoadingFace: 	    CST 적재된 패널면 (현 설비 투입면)
    * @param crntWorkFace: 	    현 공정 · 설비 작업면 (T: 상면 / B: 하면 / F: 양면)
    * @param nextRecipeType: 	    다음 공정 작업면 레시피 (Top: 상면 / Bottom: 하면 / Both: Both 레시피 / Both_Flip: 양면)
    * @param isToolHasFilpper:		현 설비 Flipper 존재 여부 (true: 존재 / false: 미 존재)
    * @param isBothRecipe:		    BOTH 레시피 여부         (true: Both 레시피 / false: 양면 or 단면)
    * @param isBothFlipRecipe:	    BOTH FLIP 레시피 여부    (true: 양면 / false: 단면)
    * @return
    * @throws Exception
    */
   public String[] generateMtrlFace(String crntLoadingFace, String crntWorkFace, String nextRecipeType,
                                    boolean isToolHasFilpper, boolean isBothRecipe, boolean isBothFlipRecipe)
           throws Exception {
       String nextRecipeTypeUpper = nextRecipeType.toUpperCase();
       /*
           Top         →       T
           Bottom      →       B
           Both        →       작업면
           BothFlip    →       배출면
        */


       String startFace = crntLoadingFace.startsWith("T") || crntLoadingFace.toUpperCase().startsWith("TOP") ? "T" : "B";
       String workFace = crntWorkFace.startsWith("T") || crntWorkFace.toUpperCase().startsWith("TOP") ? "T" : "B";
       String nextWorkFace = nextRecipeTypeUpper.startsWith("T") || nextRecipeTypeUpper.toUpperCase().startsWith("TOP") ?
               "T" : (nextRecipeTypeUpper.toUpperCase().startsWith("BOTTOM") ? "B" : null);
       String endFace = null;
       String mtrlFacePattern = "%s%s%s";

       ArrayList<String> mtrlFaceList = new ArrayList<>();


       // 양면 (Both) 작업인 경우
       if(isBothRecipe) {
    	   logger.info("Both recipe Yn : {}", isBothRecipe);
           // Both 레시피 작업 → 작업면과 상관없이 투입면으로 작업 진행
           workFace = startFace;

           // Both 레시피 작업
           // Flipper 있다면, 다음공정 투입면으로 배출
           // 투입면과 다음 공정 작업면 비교 → 같으면: 시작면으로 배출 (Both/Both_Flip 포함) / 다르면: (hasFlipper) ? 반전 배출 : 시작면 배출

           // 다음 공정: Both || Both_Flip → 투입면으로 배출 (조건: 투입면으로 작업 startWork ≒ workFace)
           if(nextWorkFace == null){

               endFace = startFace;

               // 다음 공정: T(Top) or B(Bottom)
           }else{
               // 투입면과 다음 공정 작업면이 같은 경우
               if(startFace.equals(nextWorkFace)){
                   endFace = startFace;

                   // 투입면과 다음 공정 작업면이 다른 경우
               }else {
                   // Flipper 有 → 시작면으로 배출 (조건: 투입면으로 작업 startWork ≒ workFace)
                   // Flipper 無 → 반전하여 배출
                   endFace = (isToolHasFilpper) ? nextWorkFace : startFace;
               }
           }

//           if(isToolHasFilpper) {
//               // 다음 공정, Both || Both_Flip → nextWorkFace is null → 작업면 배출 (Flip 최소)
//               endFace = (nextWorkFace == null) ? workFace : nextWorkFace;
//           }
//           // Flipper 없다면, 작업면으로 배출
//           else {
//               endFace = startFace;
//           }
           addMtrlFaceAtArray(mtrlFaceList, mtrlFacePattern, startFace, workFace,endFace);


           // 단면 작업 인 경우
       }else if(!isBothFlipRecipe){
    	   logger.info("No both recipe Yn : {} and One side recipe Yn: {}", isBothRecipe, isBothFlipRecipe);

           // Filpper 부재, 투입면과 작업면이 다른 경우 → 작업 진행 불가 → Abnormal 케이스
           if(!isToolHasFilpper && !startFace.equals(workFace)){
        	   logger.error("Start face and work face is not matched. No permit to start ");
               // TODO Error Code 식별 필요
               throw new Exception("No Flipper and Work Face is not matched");
           }

           if(!(nextWorkFace == null)){
               // Flipper 존재 → 다음 공정면 배출
               endFace = (isToolHasFilpper) ? nextWorkFace : workFace;
           }
           // 다음 공정, Both || Both_Flip → 작업면 배출
           else{
               endFace = startFace;
           }
           addMtrlFaceAtArray(mtrlFaceList, mtrlFacePattern, startFace, workFace,endFace);


           // 양면 작업 인 경우 → Seq1에서 최대한 반전하여, Seq2에서 반전 없이 작업 진행
       }else if(isBothFlipRecipe){

           // 다음 공정 Both || Both_Flip → 투입면, 배출
           if(nextWorkFace == null) nextWorkFace = crntLoadingFace;

           // 투입면과 배출면이 같으면  → seq1 다른면 작업 후 배출면으로 담는다.
           // 투입면과 배출면이 다르면  → seq1 투입면으로 작업 후 반전 진행 / seq2 배출면 작업
           // ** 첫번째 작업 후 배출면으로 담는다.
           for(int i=1; i < 3; i++){

               // 반전하여 작업 → 반전하여 배출면 맞춤
               if(i == 1){
                   // 투입면과 배출면이 같으면 → 반전 작업 먼저 진행 (배출면 기준 반대면)
                   if(crntLoadingFace.equals(nextWorkFace)){
                       workFace = (nextWorkFace.equals("T")) ? "B" : "T";
                   }
                   // 투입면과 배출면이 다르면 → 투입면 먼저 작업 진행
                   else {
                       workFace = startFace;
                   }
                   endFace = nextWorkFace;
                   addMtrlFaceAtArray(mtrlFaceList, mtrlFacePattern, startFace, workFace,endFace);
               }

               // (seq 1에서 맞춤) 배출면 작업 후 배출, 반전 작업 없음
               if(i == 2){
                   startFace = nextWorkFace;
                   workFace = nextWorkFace;
                   endFace = nextWorkFace;
                   addMtrlFaceAtArray(mtrlFaceList, mtrlFacePattern, startFace, workFace,endFace);
               }

           }
       }else{

           logger.error("Recipe Type is not defined");
           throw new Exception("Recipe type is not defined. Current type: Both / Both Flip / Side");
       }



       return mtrlFaceList.toArray(new String[mtrlFaceList.size()]);

   }
   
   public String[] generateMtrlFace(String crntLoadingFace, String crntWorkFace, String nextRecipeType, boolean isToolHasFilpper, String mtrlFaceCd)	throws Exception {
		String nextRecipeTypeUpper = nextRecipeType.toUpperCase();
		/*
		Top         →       T
		Bottom      →       B
		Both        →       작업면
		BothFlip    →       배출면
		*/
		
		String startFace = crntLoadingFace.startsWith("T") || crntLoadingFace.toUpperCase().startsWith("TOP") ? "T" : "B";
		String workFace = crntWorkFace.startsWith("T") || crntWorkFace.toUpperCase().startsWith("TOP") ? "T" : "B";
		String nextWorkFace = nextRecipeTypeUpper.startsWith("T") || nextRecipeTypeUpper.toUpperCase().startsWith("TOP") ?
		"T" : (nextRecipeTypeUpper.toUpperCase().startsWith("BOTTOM") ? "B" : null);
		String endFace = null;
		String mtrlFacePattern = "%s%s%s";

		ArrayList<String> mtrlFaceList = new ArrayList<>();
		
		// 양면 (Both) 작업인 경우
		if("Both".equals(mtrlFaceCd)) {
			logger.info("Both recipe Yn : {}", mtrlFaceCd);
			// Both 레시피 작업 → 작업면과 상관없이 투입면으로 작업 진행
			workFace = startFace;
			
			// Both 레시피 작업
			// Flipper 있다면, 다음공정 투입면으로 배출
			// 투입면과 다음 공정 작업면 비교 → 같으면: 시작면으로 배출 (Both/Both_Flip 포함) / 다르면: (hasFlipper) ? 반전 배출 : 시작면 배출
			
			// 다음 공정: Both || Both_Flip → 투입면으로 배출 (조건: 투입면으로 작업 startWork ≒ workFace)
			if(nextWorkFace == null){
			
				endFace = startFace;
		
			// 다음 공정: T(Top) or B(Bottom)
			}else{
				// 투입면과 다음 공정 작업면이 같은 경우
				if(startFace.equals(nextWorkFace)){
				endFace = startFace;
			
				// 투입면과 다음 공정 작업면이 다른 경우
				}else {
				// Flipper 有 → 시작면으로 배출 (조건: 투입면으로 작업 startWork ≒ workFace)
				// Flipper 無 → 반전하여 배출
				endFace = (isToolHasFilpper) ? nextWorkFace : startFace;
				}
			}
			
			//if(isToolHasFilpper) {
			//// 다음 공정, Both || Both_Flip → nextWorkFace is null → 작업면 배출 (Flip 최소)
			//endFace = (nextWorkFace == null) ? workFace : nextWorkFace;
			//}
			//// Flipper 없다면, 작업면으로 배출
			//else {
			//endFace = startFace;
			//}
			addMtrlFaceAtArray(mtrlFaceList, mtrlFacePattern, startFace, workFace,endFace);
			
			
		// 단면 작업 인 경우
		} 
		else if("Top".equals(mtrlFaceCd) || "Bottom".equals(mtrlFaceCd)){
			logger.info("One side recipe Yn: {}", mtrlFaceCd);
			
			// Filpper 부재, 투입면과 작업면이 다른 경우 → 작업 진행 불가 → Abnormal 케이스
			if(!isToolHasFilpper && !startFace.equals(workFace)){
				logger.error("Start face and work face is not matched. No permit to start ");
				// TODO Error Code 식별 필요
			throw new Exception("No Flipper and Work Face is not matched");
			}
		
			if(!(nextWorkFace == null)){
				// Flipper 존재 → 다음 공정면 배출
				endFace = (isToolHasFilpper) ? nextWorkFace : workFace;
			}
			// 다음 공정, Both || Both_Flip → 작업면 배출
			else {
				endFace = startFace;
			}
			addMtrlFaceAtArray(mtrlFaceList, mtrlFacePattern, startFace, workFace,endFace);
		
		
		// 양면 작업 인 경우 → Seq1에서 최대한 반전하여, Seq2에서 반전 없이 작업 진행
		} 
		else if("Both_Flip".equals(mtrlFaceCd)){
			
			// 다음 공정 Both || Both_Flip → 투입면, 배출
			if(nextWorkFace == null) nextWorkFace = crntLoadingFace;
			
			// 투입면과 배출면이 같으면  → seq1 다른면 작업 후 배출면으로 담는다.
			// 투입면과 배출면이 다르면  → seq1 투입면으로 작업 후 반전 진행 / seq2 배출면 작업
			// ** 첫번째 작업 후 배출면으로 담는다.
			for(int i=1; i < 3; i++){
				
				// 반전하여 작업 → 반전하여 배출면 맞춤
				if(i == 1){
					// 투입면과 배출면이 같으면 → 반전 작업 먼저 진행 (배출면 기준 반대면)
					if(crntLoadingFace.equals(nextWorkFace)){
						workFace = (nextWorkFace.equals("T")) ? "B" : "T";
					}
					// 투입면과 배출면이 다르면 → 투입면 먼저 작업 진행
					else {
						workFace = startFace;
					}
					endFace = nextWorkFace;
					addMtrlFaceAtArray(mtrlFaceList, mtrlFacePattern, startFace, workFace,endFace);
				}
				
				// (seq 1에서 맞춤) 배출면 작업 후 배출, 반전 작업 없음
				if(i == 2){
					startFace = nextWorkFace;
					workFace = nextWorkFace;
					endFace = nextWorkFace;
					addMtrlFaceAtArray(mtrlFaceList, mtrlFacePattern, startFace, workFace,endFace);
				}
			}
		} 
		else {
			logger.error("Recipe Type is not defined");
			throw new Exception("Recipe type is not defined. Current type: Both / Both Flip / Side");
		}
		
		
		
		return mtrlFaceList.toArray(new String[mtrlFaceList.size()]);
		
	}



   private void addMtrlFaceAtArray(ArrayList<String> list, String mtrlFacePattern, String startFace, String workFace, String endFace){
       list.add(String.format(
               mtrlFacePattern, startFace, workFace, endFace
       ));
   }
   
   /**
    * Iterate recipe list and get recipe id with mtrl face cd.
    * @param mtrlFace TTT/TBT
    * @param recipeList
    * @return
 * @throws Exception 
    */
   private String getRecipeIdWithMtrlFaceCd(String mtrlFace, List<RecipeVo> recipeList) throws Exception {
	   
	   String recipeId = null;
	   
	   if(!(mtrlFace.length() == 3)) throw new Exception("Materical Face Code must be 3 digit. It't not 3. MtrlCd " + mtrlFace);
	   char secondChar = mtrlFace.charAt(1);
	   String workFace =  secondChar == 'B' ? "Bottom" : (secondChar == 'T' ? "Top" : null);
	   if(workFace == null) throw new NullPointerException("Mtrl Cd is not consist of T of B. MtrlCd : " + mtrlFace);
	   
	   for(RecipeVo vo : recipeList) {
		   if(workFace.equals(vo.getWorkFace())) {
			   recipeId = vo.getRecipeId();
			   return recipeId;
		   }
	   }
	   
	   
	   throw new Exception(String.format("No Recipe with WorkFace. workFace: %s. recipeList: %s", workFace, recipeList.toString()));
	   
   }
   
   /**
    * 양면 자주검사 job Slot 재생성
    * @param siteId
    * @param workId
    * @return
    */
   public int updateSelfInsp1MoreWork(String siteId, String workId) {
	   
	   int resultCnt = -1;
	   SqlSession session = MybatisSession.getSqlSessionInstance();
	   		
	   WorkMapper mapper = session.getMapper(WorkMapper.class);
	   
	   try {
		   
		   WnWorkJobSlotInfo param = new WnWorkJobSlotInfo();
		   
		   param.setSiteId(siteId);
		   param.setWorkId(workId);
		
//		   mapper.deleteSelfInspSlot(param);
		   mapper.updateUnUsableSelfInspSlot(param);
		   
		   // Top
		   param.setJobSeqId("1");
		   param.setpJobSeqId("3");
		   mapper.update1MoreSlotInfo(param);
		   
		   // Bottom
		   param.setJobSeqId("2");
		   param.setpJobSeqId("4");
		   mapper.update1MoreSlotInfo(param);
		   
		   session.commit();
		   resultCnt = 1;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		
		return resultCnt;
   }
   
   

}
