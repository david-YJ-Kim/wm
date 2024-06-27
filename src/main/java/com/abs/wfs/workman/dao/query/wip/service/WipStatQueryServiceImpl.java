package com.abs.wfs.workman.dao.query.wip.service;

import com.abs.wfs.workman.dao.query.wip.mapper.WipStatMapper;
import com.abs.wfs.workman.dao.query.wip.vo.*;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class WipStatQueryServiceImpl implements WipStatQueryService{

    @Autowired
    WipStatMapper wipStatMapper;


    /**
     * WN_WIP_STAT 조회 by Lot ID
     * @param siteId
     * @param lotId
     * @return
     */
    public List<WnWipStat> selectByLotId(String siteId, String lotId) throws Exception{


        List<WnWipStat> wipStatList = null;
        try {

            WnWipStat param = new WnWipStat();
            param.setSiteId(siteId);
            param.setLotId(lotId);

            wipStatList = wipStatMapper.selectWnWipStat(param);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return wipStatList;
    }


    /**
     * WN_WIP_STAT
     * WORK_STAT_CD Update by CarrId Base
     * @return
     */
    
    @Transactional(rollbackFor = Exception.class)
    public int updateWorkStatusByCarrId(UpdateWorkStatusByCarrIdDto dto) throws Exception {
        int updateCnt = 0;

        try {
            WnWipStat wnWipStat = new WnWipStat();
            wnWipStat.setSiteId(dto.getSiteId());
            wnWipStat.setCarrId(dto.getCarrId());
            wnWipStat.setUseStatCd(UseStatCd.Usable.name());

            List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStat(wnWipStat);

            for (WnWipStat w : wipStatList) {
                WnWipStat setParam = new WnWipStat();

                if (!"".equals(dto.getWorkStatCd())) {
                    setParam.setWorkStatCd(dto.getWorkStatCd());
                }
                setParam.setMdfyUserId(dto.getMdfyUserId());
                setParam.setEvntNm(dto.getCid());
                setParam.setTid(dto.getTid());

                if (dto.isDspInfoClearFlag()) {
                    // Clear reserved information and check flags
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

                setParam.setSiteId(w.getSiteId());
                setParam.setCarrId(w.getCarrId());
                setParam.setLotId(w.getLotId());
                setParam.setUseStatCd(UseStatCd.Usable.name());

                if (wipStatMapper.updateWnWipStat(setParam) > 0) {
                    wipStatMapper.createWhWipStat(w.getObjId()); // Create history
                    updateCnt++;
                }
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return updateCnt;
    }

    
    @Transactional(rollbackFor = Exception.class)
    public int updateWorkStatusByCarrIdForBatchEnd(UpdateWorkStatusByCarrIdForBatchEndDto dto) throws Exception {
        int updateCnt = 0;

        try {
            WnWipStat param = new WnWipStat();

            if (!"".equals(dto.getWorkStatCd())) {
                param.setWorkStatCd(dto.getWorkStatCd());
            }
            param.setBatchId("");
            param.setBatchSeq("");
            param.setMdfyUserId(dto.getMdfyUserId());
            param.setEvntNm(dto.getCid());
            param.setTid(dto.getTid());

            if (dto.isDspInfoClearFlag()) {
                // Clear reserved information and check flags
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

            param.setSiteId(dto.getSiteId());
            param.setCarrId(dto.getCarrId());
            param.setLotId("-");
            param.setUseStatCd(UseStatCd.Usable.name());

            updateCnt = wipStatMapper.updateWnWipStat(param);
            if (updateCnt > 0) {
                wipStatMapper.createWhWipStat(param.getObjId()); // Create history
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return updateCnt;
    }

    
    @Transactional(rollbackFor = Exception.class)
    public int updateWorkStatusByLotId(UpdateWorkStatusByLotIdDto dto) throws Exception {
        int updateCnt = 0;

        try {
            WnWipStat param = new WnWipStat();

            // SET
            if (!"".equals(dto.getWorkStatCd())) {
                param.setWorkStatCd(dto.getWorkStatCd());
            }
            param.setMdfyUserId(dto.getMdfyUserId());
            param.setEvntNm(dto.getCid());
            param.setTid(dto.getTid());
            if (dto.getCrntEqpId() != null) param.setCrntEqpId(dto.getCrntEqpId());
            if (dto.getCrntPortId() != null) param.setCrntPortId(dto.getCrntPortId());

            // Clear reserve Info & checkFlag WORK_START
            if (dto.isDspInfoClearFlag()) {
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
            param.setSiteId(dto.getSiteId());
            param.setLotId(dto.getLotId());
            param.setUseStatCd(UseStatCd.Usable.name());

            // UPDATE
            updateCnt = wipStatMapper.updateWnWipStat(param);

            if (updateCnt > 0) {
                // CREATE HISTORY for Lot
                wipStatMapper.createWhWipStat(param.getObjId());

                // Assigned CARR Exist
                if (!"-".equals(param.getCarrId())) {
                    // Update Carr Info
                    WnWipStat carrParam = new WnWipStat();

                    // SET
                    if (!"".equals(dto.getWorkStatCd())) {
                        carrParam.setWorkStatCd(dto.getWorkStatCd());
                    }
                    carrParam.setMdfyUserId(dto.getMdfyUserId());
                    carrParam.setEvntNm(dto.getCid());
                    carrParam.setTid(dto.getTid());

                    // Clear reserve Info & checkFlag WORK_START
                    if (dto.isDspInfoClearFlag()) {
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
                    carrParam.setSiteId(dto.getSiteId());
                    carrParam.setLotId("-");
                    carrParam.setCarrId(param.getCarrId());
                    carrParam.setUseStatCd(UseStatCd.Usable.name());

                    if (wipStatMapper.updateWnWipStat(carrParam) > 0) {
                        // CREATE HISTORY for Carr
                        wipStatMapper.createWhWipStat(carrParam.getObjId());
                    }
                }
            }

        } catch (Exception e) {
            throw new Exception("Failed to update work status by LotId", e);
        }

        return updateCnt;
    }



    
    @Transactional(rollbackFor = Exception.class)
    public int updateWorkStatusForBatchEndByLotId(UpdateWorkStatusForBatchEndByLotIdDto dto) throws Exception {
        int updateCnt = 0;

        try {
            WnWipStat param = new WnWipStat();

            // SET
            if (!"".equals(dto.getWorkStatCd())) {
                param.setWorkStatCd(dto.getWorkStatCd());
            }
            param.setBatchId("");
            param.setBatchSeq("");
            param.setMdfyUserId(dto.getMdfyUserId());
            param.setEvntNm(dto.getCid());
            param.setTid(dto.getTid());

            // Clear reserve Info & checkFlag WORK_START
            if (dto.isDspInfoClearFlag()) {
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
            param.setSiteId(dto.getSiteId());
            param.setLotId(dto.getLotId());
            param.setUseStatCd(UseStatCd.Usable.name());

            // UPDATE
            updateCnt = wipStatMapper.updateWnWipStat(param);

            if (updateCnt > 0) {
                // CREATE HISTORY for Lot
                wipStatMapper.createWhWipStat(param.getObjId());
            }

        } catch (Exception e) {
            throw new Exception("Failed to update work status for batch end by LotId", e);
        }

        return updateCnt;
    }

    
    @Transactional(rollbackFor = Exception.class)
    public int updateEventNmByCarrId(UpdateEventNmByCarrIdDto dto) throws Exception {
        int updateCnt = 0;

        try {
            WnWipStat param = new WnWipStat();
            // SET
            param.setMdfyUserId(dto.getMdfyUserId());
            param.setEvntNm(dto.getCid());
            param.setTid(dto.getTid());

            // WHERE
            param.setSiteId(dto.getSiteId());
            param.setCarrId(dto.getCarrId());
            param.setUseStatCd(UseStatCd.Usable.name());

            List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStat(param);

            for (WnWipStat w : wipStatList) {
                WnWipStat setParam = new WnWipStat();
                // SET
                setParam.setMdfyUserId(dto.getMdfyUserId());
                setParam.setEvntNm(dto.getCid());
                setParam.setTid(dto.getTid());

                // WHERE
                setParam.setSiteId(w.getSiteId());
                setParam.setCarrId(w.getCarrId());
                setParam.setLotId(w.getLotId());
                setParam.setUseStatCd(UseStatCd.Usable.name());

                // Update & Insert History
                if (wipStatMapper.updateWnWipStat(setParam) > 0) {
                    // CREATE HISTORY
                    wipStatMapper.createWhWipStat(w.getObjId());
                    updateCnt++;
                }
            }

        } catch (Exception e) {
            throw new Exception("Failed to update event name by CarrId", e);
        }

        return updateCnt;
    }


    
    @Transactional(rollbackFor = Exception.class)
    public int updateEventNmByLotId(UpdateEventNmByLotIdDto dto) throws Exception {
        int updateCnt = 0;

        try {
            WnWipStat param = new WnWipStat();

            // SET
            param.setMdfyUserId(dto.getMdfyUserId());
            param.setEvntNm(dto.getCid());
            param.setTid(dto.getTid());

            // WHERE
            param.setSiteId(dto.getSiteId());
            param.setLotId(dto.getLotId());
            param.setUseStatCd(UseStatCd.Usable.name());

            // UPDATE
            updateCnt = wipStatMapper.updateWnWipStat(param);

            if (updateCnt > 0) {
                // CREATE HISTORY for Lot
                wipStatMapper.createWhWipStat(param.getObjId());

                // Update Carr Info if CarrId is not "-"
                if (!"-".equals(param.getCarrId())) {
                    WnWipStat carrParam = new WnWipStat();

                    // SET
                    carrParam.setMdfyUserId(dto.getMdfyUserId());
                    carrParam.setEvntNm(dto.getCid());
                    carrParam.setTid(dto.getTid());

                    // WHERE
                    carrParam.setSiteId(dto.getSiteId());
                    carrParam.setLotId("-");
                    carrParam.setCarrId(param.getCarrId());
                    carrParam.setUseStatCd(UseStatCd.Usable.name());

                    if (wipStatMapper.updateWnWipStat(carrParam) > 0) {
                        // CREATE HISTORY for Carr
                        wipStatMapper.createWhWipStat(carrParam.getObjId());
                    }
                }
            }

        } catch (Exception e) {
            throw new Exception("Failed to update event name by LotId", e);
        }

        return updateCnt;
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



        int resultCnt = -1;

        try {
            WnWipStat param = new WnWipStat();

            //SET
            param.setMdfyUserId(userId);
            param.setEvntNm(cid);
            param.setTid(tid);
            param.setTrackInCnfmYn(trackInCnfmYn);

            //WHERE
            param.setSiteId(siteId);
            param.setLotId(lotId);
            param.setUseStatCd(UseStatCd.Usable.name());

            log.info("################");
            log.info("LOT ID : " + param.getLotId());
            log.info("SITE ID : " + param.getSiteId());



            resultCnt = wipStatMapper.updateWnWipStat(param);
            log.info("result CNT >>  " + resultCnt);
            log.info("################");

            if(resultCnt > 0) {
                wipStatMapper.createWhWipStat(param.getObjId());
            }


            log.info("Commit");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {

        }


        return resultCnt;
    }

    /**
     * UPDATE WN_WIP_STAT
     * RCP_CHK_YN Update
     * @return
     */
    public int updateRcpChkYn(WipStatServiceDto dto) throws Exception{



        int resultCnt = -1;

        try {
            WnWipStat param = new WnWipStat();

            //SET
            param.setMdfyUserId(dto.getUserId());
            param.setEvntNm(dto.getCid());
            param.setTid(dto.getTid());
            param.setRcpChkYn(dto.getRcpChkYn());


            //WHERE
            param.setSiteId(dto.getSiteId());
            param.setLotId(dto.getLotId());
            param.setUseStatCd(UseStatCd.Usable.name());

            resultCnt = wipStatMapper.updateWnWipStat(param);

            if(resultCnt > 0) {
                wipStatMapper.createWhWipStat(param.getObjId());
            }


        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }


        return resultCnt;
    }


    public int clearAllChkFlag(WipStatServiceDto dto) throws Exception{
        int updateCnt = 0;




        try {
            WnWipStat param = new WnWipStat();

            // SET
            param.setMdfyUserId(dto.getUserId());
            param.setEvntNm(dto.getCid());
            param.setTid(param.getTid());

            param.setRcpChkYn("");
            param.setTrackInCnfmYn("");
            param.setEqpChkYn("");

            // WHERE
            param.setSiteId(param.getSiteId());
            param.setLotId(param.getLotId());
            param.setUseStatCd(UseStatCd.Usable.name());


            // UPDATE
            updateCnt = wipStatMapper.updateWnWipStat(param);

            if(updateCnt > 0) {

                // CREATE HISTORY for Lot
                wipStatMapper.createWhWipStat(param.getObjId());


                // Update Carr Info
                WnWipStat carrParam = new WnWipStat();

                // SET
                carrParam.setMdfyUserId(dto.getUserId());
                carrParam.setEvntNm(dto.getCid());
                carrParam.setTid(param.getTid());

                carrParam.setRcpChkYn("");
                carrParam.setTrackInCnfmYn("");
                carrParam.setEqpChkYn("");

                // WHERE
                carrParam.setSiteId(dto.getSiteId());
                carrParam.setLotId("-");
                carrParam.setCarrId(param.getCarrId());
                carrParam.setUseStatCd(UseStatCd.Usable.name());


                if(wipStatMapper.updateWnWipStat(carrParam) > 0) {
                    // CREATE HISTORY for Carr
                    wipStatMapper.createWhWipStat(carrParam.getObjId());
                }
            }



        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return updateCnt;

    }

    /**
     * UPDATE WN_WIP_STAT For Crrier Move Completed by CarrierID
     * CRNT_EQP_ID, CRNT_PORT_ID
     * @return
     */
    public int updateWipStatForMoveCompleteByCarrId(updateWipStatForMoveCompleteByCarrIdDto dto) throws Exception{

        int updateCnt = 0;




        try {
            WnWipStat param = new WnWipStat();

            // WHERE
            param.setSiteId(dto.getSiteId());
            param.setCarrId(dto.getCarrId());
            param.setUseStatCd(UseStatCd.Usable.name());

            List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStat(param);

            for(WnWipStat w : wipStatList) {
                WnWipStat setParam = new WnWipStat();
                // SET
                setParam.setCrntEqpId(dto.getCrntEqpId());

                String portId = dto.getCrntPortId();

                //STOCKER 또는 BUFFER인 경우
                if(dto.getCrntEqpId().indexOf("ASTK") > -1 || dto.getCrntEqpId().indexOf("BUF") > -1) {
                    try {
                        Integer.parseInt(portId);
                        portId = dto.getCrntEqpId() + "_STORAGE";
                    } catch(NumberFormatException ex) {
                        // nothing
                    }
                }

                setParam.setCrntPortId(dto.getCrntPortId());
                setParam.setWorkStatCd(dto.getWorkStatCd());

                setParam.setTrackInCnfmYn(""); // clear
                setParam.setRcpChkYn(""); // clear
                setParam.setEqpChkYn(""); // clear

                setParam.setMdfyUserId(dto.getUserId());
                setParam.setEvntNm(dto.getCid());
                setParam.setTid(dto.getTid());

                // WHERE
                setParam.setObjId(w.getObjId());

                //Update & Insert History
                if(wipStatMapper.updateWnWipStat(setParam) > 0) {
                    // CREATE HISTORY
                    wipStatMapper.createWhWipStat(w.getObjId());
                }
            }



        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return updateCnt;
    }

    /**
     * updateWipStatForMoveCancelByCarrId
     * @return
     * @throws Exception
     */
    public int updateWipStatForMoveCancelByCarrId(updateWipStatForMoveCancelByCarrIdDto dto) throws Exception{

        int updateCnt = 0;




        try {
            WnWipStat param = new WnWipStat();

            // WHERE
            param.setSiteId(dto.getSiteId());
            param.setCarrId(dto.getCarrId());
            param.setUseStatCd(UseStatCd.Usable.name());

            List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStat(param);

            for(WnWipStat w : wipStatList) {
                WnWipStat setParam = new WnWipStat();
                // SET
                setParam.setCrntEqpId(dto.getCrntEqpId());

                String portId = dto.getCrntPortId();
                if(dto.getCrntPortId().indexOf("ASTK") > -1 || dto.getCrntPortId().indexOf("BUF") > -1) {
                    portId = dto.getCrntEqpId() + "_STORAGE";
                }
                setParam.setCrntPortId(portId);

                setParam.setWorkStatCd(dto.getWorkStatCd());

                setParam.setResvEqpId("");
                setParam.setResvPortId("");
                setParam.setResvGrpId("");
                setParam.setResvOutCarrId("");
                setParam.setResvOutPortId("");


                setParam.setMdfyUserId(dto.getUserId());
                setParam.setEvntNm(dto.getCid());
                setParam.setTid(dto.getTid());

                // WHERE
                setParam.setObjId(w.getObjId());

                //Update & Insert History
                if(wipStatMapper.updateWnWipStat(setParam) > 0) {
                    // CREATE HISTORY
                    wipStatMapper.createWhWipStat(w.getObjId());
                }
            }



        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return updateCnt;
    }



    /**
     * UPDATE WN_WIP_STAT For Crrier Location by CarrierID
     * CRNT_EQP_ID, CRNT_PORT_ID
     * @return
     */
    public int updateCurrentEqpPortByCarrId(UpdateCurrentEqpPortByCarrIdDto dto) throws Exception {
        int updateCnt = 0;

        try {
            log.info("WipStatDAO.updateCurrentEqpPortByCarrId");

            WnWipStat param = new WnWipStat();

            // WHERE conditions
            param.setSiteId(dto.getSiteId());
            param.setCarrId(dto.getCarrId());
            param.setUseStatCd(UseStatCd.Usable.name());

            List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStat(param);

            for (WnWipStat w : wipStatList) {
                WnWipStat setParam = new WnWipStat();

                // SET values for update
                setParam.setCrntEqpId(dto.getCrntEqpId());
                setParam.setCrntPortId(dto.getCrntPortId());
                setParam.setMdfyUserId(dto.getUserId());
                setParam.setEvntNm(dto.getCid());
                setParam.setTid(dto.getTid());

                // WHERE condition for update
                setParam.setObjId(w.getObjId());

                // Update & Insert History
                if (wipStatMapper.updateWnWipStat(setParam) > 0) {
                    // CREATE HISTORY
                    wipStatMapper.createWhWipStat(w.getObjId());
                    updateCnt++; // Increment update count
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return updateCnt;
    }



    /**
     * UPDATE WN_WIP_STAT
     * RTD Dspatching Response -> Batch
     * Reserve Eqp, Port, Reserve Group ID
     * Batch ID, Batch Sequence Update
     * @return
     */
    public int updateDspWorkRepBatch(UpdateDspWorkRepBatchDto dto) throws Exception {
        int updateCnt = 0;

        try {
            log.info("WipStatDAO.updateDspWorkRepBatch");

            WnWipStat param = new WnWipStat();

            // WHERE conditions
            param.setSiteId(dto.getSiteId());
            param.setCarrId(dto.getCarrId());
            param.setUseStatCd(UseStatCd.Usable.name());

            List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStat(param);

            for (WnWipStat w : wipStatList) {
                WnWipStat setParam = new WnWipStat();

                // SET values for update
                setParam.setBatchId(dto.getBatchId());
                setParam.setBatchSeq(dto.getBatchSeq());
                setParam.setResvEqpId(dto.getResvEqpId());
                setParam.setResvPortId(dto.getResvPortId());
                setParam.setResvGrpId(dto.getResvGrpId());
                setParam.setMdfyUserId(dto.getUserId());
                setParam.setEvntNm(dto.getCid());
                setParam.setTid(dto.getTid());

                // WHERE condition for update
                setParam.setObjId(w.getObjId());

                // Update & Insert History
                if (wipStatMapper.updateWnWipStat(setParam) > 0) {
                    // CREATE HISTORY
                    wipStatMapper.createWhWipStat(w.getObjId());
                    updateCnt++; // Increment update count
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return updateCnt;
    }



    /**
     * UPDATE WN_WIP_STAT
     * RTD Dspatching Response -> BothPort, InOut
     * Reserve Eqp, Port, Reserve Group ID
     * @return
     */
    public int updateDspWorkRepNormal(UpdateDspWorkRepNormalDto dto) throws Exception {
        int updateCnt = 0;

        try {
            log.info("WipStatDAO.updateDspWorkRepNormal");

            WnWipStat param = new WnWipStat();

            // WHERE conditions
            param.setSiteId(dto.getSiteId());
            param.setCarrId(dto.getCarrId());
            param.setLotId(dto.getLotId());
            param.setUseStatCd(UseStatCd.Usable.name());

            // Retrieve list of WnWipStat objects based on WHERE conditions
            List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStatByLot(param);

            for (WnWipStat w : wipStatList) {
                WnWipStat setParam = new WnWipStat();

                // SET values for update
                setParam.setResvEqpId(dto.getResvEqpId());
                setParam.setResvPortId(dto.getResvPortId());
                setParam.setResvGrpId(dto.getResvGrpId());
                setParam.setResvOutCarrId(dto.getResvOutCarrId());
                setParam.setResvOutPortId(dto.getResvOutPortId());
                setParam.setMdfyUserId(dto.getUserId());
                setParam.setEvntNm(dto.getCid());
                setParam.setTid(dto.getTid());

                // WHERE condition for update
                setParam.setObjId(w.getObjId());

                log.info("##### UPDATE BEFORE ");
                // Update & Insert History
                if (wipStatMapper.updateWnWipStat(setParam) > 0) {
                    log.info("##### UPDATE AFTER ");
                    // CREATE HISTORY
                    wipStatMapper.createWhWipStat(w.getObjId());
                    updateCnt++; // Increment update count
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return updateCnt;
    }



    /**
     * WN_WIP_STAT
     * SELF_INSP_YN, SELF_INSP_PANEL_CNT, SELF_INSP_INFO_OBJ_ID
     * @return
     */
    public int updateSelfInspInfo(UpdateSelfInspInfoDto dto) throws Exception {
        int updateCnt = 0;

        try {
            log.info("WipStatDAO.updateSelfInspInfo");

            WnWipStat param = new WnWipStat();

            // SET
            param.setMdfyUserId(dto.getUserId());
            param.setEvntNm(dto.getCid());
            param.setTid(dto.getTid());
            param.setSelfInspYn(dto.getSelfInspYn());
            param.setSelfInspInfoObjId(dto.getSelfInspObjId());
            param.setSelfInspPanelCnt(Integer.valueOf(dto.getSelfInspPanelCnt()));

            // WHERE
            param.setSiteId(dto.getSiteId());
            param.setLotId(dto.getLotId());
            param.setUseStatCd(UseStatCd.Usable.name());

            // UPDATE
            updateCnt = wipStatMapper.updateWnWipStat(param);
            log.info("updateCnt >> " + updateCnt);

            if (updateCnt > 0) {
                // CREATE HISTORY for Lot
                wipStatMapper.createWhWipStat(param.getObjId());

                // Assigned CARR Exist
                if (!"-".equals(param.getCarrId())) {
                    // Update Carr Info
                    WnWipStat carrParam = new WnWipStat();

                    // SET
                    carrParam.setEvntNm(dto.getCid());
                    carrParam.setTid(dto.getTid());
                    carrParam.setSelfInspYn(dto.getSelfInspYn());
                    carrParam.setSelfInspInfoObjId(dto.getSelfInspObjId());
                    carrParam.setSelfInspPanelCnt(Integer.valueOf(dto.getSelfInspPanelCnt()));

                    // WHERE
                    carrParam.setSiteId(dto.getSiteId());
                    carrParam.setLotId("-");
                    carrParam.setCarrId(param.getCarrId());
                    carrParam.setUseStatCd(UseStatCd.Usable.name());

                    if (wipStatMapper.updateWnWipStat(carrParam) > 0) {
                        // CREATE HISTORY for Carr
                        wipStatMapper.createWhWipStat(carrParam.getObjId());
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return updateCnt;
    }


    public int updateSampleInfo(UpdateSampleInfoDto dto) throws Exception {
        int updateCnt = 0;

        try {
            log.info("WipStatDAO.updateSampleInfo");

            WnWipStat param = new WnWipStat();

            // SET
            param.setMdfyUserId(dto.getUserId());
            param.setEvntNm(dto.getCid());
            param.setTid(dto.getTid());
            param.setSmplLotYn(dto.getSmplLotYn());
            param.setSmplWorkTyp(dto.getSmplWorkTyp());
            param.setSmplQty(Integer.parseInt(dto.getSmplQty()));

            // WHERE
            param.setSiteId(dto.getSiteId());
            param.setLotId(dto.getLotId());
            param.setUseStatCd(UseStatCd.Usable.name());

            // UPDATE
            updateCnt = wipStatMapper.updateWnWipStat(param);
            log.info("updateCnt >> " + updateCnt);

            if (updateCnt > 0) {
                // CREATE HISTORY for Lot
                wipStatMapper.createWhWipStat(param.getObjId());

                // Assigned CARR Exist
                if (!"-".equals(param.getCarrId())) {
                    // Update Carr Info
                    WnWipStat carrParam = new WnWipStat();

                    // SET
                    carrParam.setEvntNm(dto.getCid());
                    carrParam.setTid(dto.getTid());
                    carrParam.setSmplLotYn(dto.getSmplLotYn());
                    carrParam.setSmplWorkTyp(dto.getSmplWorkTyp());
                    carrParam.setSmplQty(Integer.parseInt(dto.getSmplQty()));

                    // WHERE
                    carrParam.setSiteId(dto.getSiteId());
                    carrParam.setLotId("-");
                    carrParam.setCarrId(param.getCarrId());
                    carrParam.setUseStatCd(UseStatCd.Usable.name());

                    if (wipStatMapper.updateWnWipStat(carrParam) > 0) {
                        // CREATE HISTORY for Carr
                        wipStatMapper.createWhWipStat(carrParam.getObjId());
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
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




        try {

            WnWipStat param = new WnWipStat();

            param.setWorkStatCd("Standby");
            param.setDtlWorkStatCd("");
            param.setResvEqpId("");
            param.setResvPortId("");
            param.setResvGrpId("");
            param.setResvOutCarrId("");
            param.setResvOutPortId("");
            param.setEqpChkYn("");
            param.setRcpChkYn("");
            param.setTrackInCnfmYn("");
            param.setSelfInspInfoObjId("");
            param.setSelfInspPanelCnt(0);
            param.setSelfInspYn("");
            param.setSmplLotYn("");
            param.setSmplQty(0);
            param.setSmplWorkTyp("");
            param.setBatchId("");
            param.setBatchSeq("");
            param.setMdfyUserId("WFS");
            param.setEvntNm(cid);

            param.setSiteId(siteId);
            param.setCarrId(carrId);

            if(wipStatMapper.updateWnWipStat(param) > 0) {
                wipStatMapper.createWhWipStat(param.getObjId());
            }




        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return updateCnt;
    }
}
