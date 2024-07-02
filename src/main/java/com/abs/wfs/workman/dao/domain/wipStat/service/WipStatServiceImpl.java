package com.abs.wfs.workman.dao.domain.wipStat.service;

import com.abs.wfs.workman.dao.domain.wipStat.repository.WnWipStatRepository;
import com.abs.wfs.workman.dao.domain.wipStat.vo.UpdateDspWorkVo;
import com.abs.wfs.workman.dao.domain.wipStat.vo.UpdateWipStatForMoveCompleteByCarrIdReqVo;
import com.abs.wfs.workman.dao.domain.workStat.repository.WnWorkStatRepository;
import com.abs.wfs.workman.dao.query.wip.mapper.WipStatMyMapper;
import com.abs.wfs.workman.dao.query.wip.vo.WnWipStat;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WipStatServiceImpl implements WipStatService{

    @Autowired
    WipStatMyMapper wipStatMapper;


    @Autowired
    WnWipStatRepository wnWipStatRepository;


    /**
     * 재공에 Carr id 로우만 조회 (lotId = '-')
     * @param carrId
     * @param siteId
     * @return
     */
    public Optional<com.abs.wfs.workman.dao.domain.wipStat.model.WnWipStat> findByOnlyCarrIdAndSiteIdAndUseStatCd(String carrId, String siteId){

        return this.wnWipStatRepository.findByCarrIdAndLotIdAndSiteIdAndUseStatCd(carrId, "-", siteId, UseStatCd.Usable);
    }

    /**
     * 재공, Lot Carr Id로 조회
     * @param carrId
     * @param lotId
     * @param siteId
     * @return
     */
    public Optional<com.abs.wfs.workman.dao.domain.wipStat.model.WnWipStat> findByCarrIdAndLotIdAndSiteIdAndUseStatCd(String carrId, String lotId,String siteId){

        return this.wnWipStatRepository.findByCarrIdAndLotIdAndSiteIdAndUseStatCd(carrId, lotId,siteId, UseStatCd.Usable);
    }


    public Optional<com.abs.wfs.workman.dao.domain.wipStat.model.WnWipStat> findByLotIdAndSiteIdAndUseStatCd(String lotId, String siteId){

        return this.wnWipStatRepository.findByLotIdAndSiteIdAndUseStatCd(lotId, siteId, UseStatCd.Usable);
    }

    public Optional<com.abs.wfs.workman.dao.domain.wipStat.model.WnWipStat> findByLotIdAndSiteIdAndUseStatCdAndResvEqpIdAndResvPortIdAndUseStatCd(String lotId, String siteId, String resvEqpId, String resvPortId){
        return this.wnWipStatRepository.findByLotIdAndSiteIdAndUseStatCdAndResvEqpIdAndResvPortId(lotId, siteId, UseStatCd.Usable, resvEqpId, resvPortId);
    }









    /**
     * @return
     */
    public List<WnWipStat> selectWnWipStatWithReserveEqpPort(WnWipStat wnWipStat) throws Exception{

        return wipStatMapper.selectWnWipStatWithReserveEqpPort(wnWipStat);
    }





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
     * @param siteId
     * @param carrId
     * @param mdfyUserId
     * @param workStatCd
     * @return
     */
    public int updateWorkStatusByCarrId(String siteId, String cid, String tid, String carrId,
                                        String mdfyUserId, String workStatCd, boolean dspInfoClearFlag)
            throws Exception{

        int updateCnt = 0;


        try {
            WnWipStat wnWipStat = new WnWipStat();

            wnWipStat.setSiteId(siteId);
            // WHERE
            wnWipStat.setSiteId(siteId);
            wnWipStat.setCarrId(carrId);
            wnWipStat.setUseStatCd(UseStatCd.Usable.name());

            List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStat(wnWipStat);

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
                setParam.setSiteId(w.getSiteId());
                setParam.setCarrId(w.getCarrId());
                setParam.setLotId(w.getLotId());
                setParam.setUseStatCd(UseStatCd.Usable.name());

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
            param.setSiteId(siteId);
            param.setCarrId(carrId);
            param.setLotId("-");
            param.setUseStatCd(UseStatCd.Usable.name());

            //Update & Insert History
            updateCnt = wipStatMapper.updateWnWipStat(param);
            if(updateCnt > 0) {
                // CREATE HISTORY
                wipStatMapper.createWhWipStat(param.getObjId());
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
            param.setSiteId(siteId);
            param.setLotId(lotId);
            param.setUseStatCd(UseStatCd.Usable.name());


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
                    carrParam.setSiteId(siteId);
                    carrParam.setLotId("-");
                    carrParam.setCarrId(param.getCarrId());
                    carrParam.setUseStatCd(UseStatCd.Usable.name());

                    if(wipStatMapper.updateWnWipStat(carrParam) > 0) {
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
            param.setSiteId(siteId);
            param.setLotId(lotId);
            param.setUseStatCd(UseStatCd.Usable.name());


            // UPDATE
            updateCnt = wipStatMapper.updateWnWipStat(param);

            if(updateCnt > 0) {
                // CREATE HISTORY for Lot
                wipStatMapper.createWhWipStat(param.getObjId());
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
     * EVENT_NM Update by CarrId Base
     * @param siteId
     * @param carrId
     * @param mdfyUserId
     * @return
     */
    public int updateEventNmByCarrId(String siteId, String cid, String tid, String carrId, String mdfyUserId) throws Exception{

        int updateCnt = 0;




        try {
            WnWipStat param = new WnWipStat();
            // SET
            param.setMdfyUserId(mdfyUserId);
            param.setEvntNm(cid);
            param.setTid(tid);


            // WHERE
            param.setSiteId(siteId);
            param.setCarrId(carrId);
            param.setUseStatCd(UseStatCd.Usable.name());


            List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStat(param);


            log.info("WN_WIP_STAT SELECT_CNT >> " + wipStatList.size());

            for(WnWipStat w : wipStatList) {

                WnWipStat setParam = new WnWipStat();
                // SET
                setParam.setMdfyUserId(mdfyUserId);
                setParam.setEvntNm(cid);
                setParam.setTid(tid);

                // WHERE
                setParam.setSiteId(w.getSiteId());
                setParam.setCarrId(w.getCarrId());
                setParam.setLotId(w.getLotId());
                setParam.setUseStatCd(UseStatCd.Usable.name());

                //Update & Insert History
                if(wipStatMapper.updateWnWipStat(setParam) > 0) {
                    log.info("WN_WIP_STAT Updated!!!");
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
     * WN_WIP_STAT
     * EVENT_NM Update by LotId Base
     * @param cid
     * @param siteId
     * @param lotId
     * @param mdfyUserId
     * @return
     */
    public int updateEventNmByLotId(String siteId, String cid, String tid, String lotId, String mdfyUserId) throws Exception{

        int updateCnt = 0;




        try {
            WnWipStat param = new WnWipStat();

            // SET
            param.setMdfyUserId(mdfyUserId);
            param.setEvntNm(cid);
            param.setTid(tid);

            // WHERE
            param.setSiteId(siteId);
            param.setLotId(lotId);
            param.setUseStatCd(UseStatCd.Usable.name());


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
                carrParam.setSiteId(siteId);
                carrParam.setLotId("-");
                carrParam.setCarrId(param.getCarrId());
                carrParam.setUseStatCd(UseStatCd.Usable.name());

                if(!"-".equals(param.getCarrId())) {
                    if(wipStatMapper.updateWnWipStat(carrParam) > 0) {
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
     * @param siteId
     * @param cid
     * @param tid
     * @param userId
     * @param lotId
     * @param rcpChkYn
     * @return
     */
    public int updateRcpChkYn(String siteId, String cid, String tid, String userId, String lotId, String rcpChkYn) throws Exception{



        int resultCnt = -1;

        try {
            WnWipStat param = new WnWipStat();

            //SET
            param.setMdfyUserId(userId);
            param.setEvntNm(cid);
            param.setTid(tid);
            param.setRcpChkYn(rcpChkYn);


            //WHERE
            param.setSiteId(siteId);
            param.setLotId(lotId);
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


    public int clearAllChkFlag(String siteId, String cid, String tid, String userId, String lotId) throws Exception{
        int updateCnt = 0;




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
            param.setSiteId(siteId);
            param.setLotId(lotId);
            param.setUseStatCd(UseStatCd.Usable.name());


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
                carrParam.setSiteId(siteId);
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
    public int updateWipStatForMoveCompleteByCarrId(UpdateWipStatForMoveCompleteByCarrIdReqVo reqVo) throws Exception{

        int updateCnt = 0;




        try {
            WnWipStat param = new WnWipStat();

            // WHERE
            param.setSiteId(reqVo.getSiteId());
            param.setCarrId(reqVo.getCarrId());
            param.setUseStatCd(UseStatCd.Usable.name());

            List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStat(param);

            for(WnWipStat w : wipStatList) {
                WnWipStat setParam = new WnWipStat();
                // SET
                setParam.setCrntEqpId(reqVo.getCrntEqpId());

                String portId = reqVo.getCrntPortId();

                //STOCKER 또는 BUFFER인 경우
                if(reqVo.getCrntEqpId().indexOf("ASTK") > -1 || reqVo.getCrntPortId().indexOf("BUF") > -1) {
                    try {
                        Integer.parseInt(portId);
                        portId = reqVo.getCrntEqpId() + "_STORAGE";
                    } catch(NumberFormatException ex) {
                        // nothing
                    }
                }

                setParam.setCrntPortId(reqVo.getCrntPortId());
                setParam.setWorkStatCd(reqVo.getWorkStatCd());

                setParam.setTrackInCnfmYn(""); // clear
                setParam.setRcpChkYn(""); // clear
                setParam.setEqpChkYn(""); // clear

                setParam.setMdfyUserId(reqVo.getUserId());
                setParam.setEvntNm(reqVo.getCid());
                setParam.setTid(reqVo.getTid());

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




        try {
            WnWipStat param = new WnWipStat();

            // WHERE
            param.setSiteId(siteId);
            param.setCarrId(carrId);
            param.setUseStatCd(UseStatCd.Usable.name());

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

                setParam.setCrntPortId(crntPortId);
                setParam.setWorkStatCd(workStatCd);

                setParam.setTrackInCnfmYn(""); // clear
                setParam.setRcpChkYn(""); // clear
                setParam.setEqpChkYn(""); // clear

                setParam.setMdfyUserId(userId);
                setParam.setEvntNm(cid);
                setParam.setTid(tid);

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




        try {
            WnWipStat param = new WnWipStat();

            // WHERE
            param.setSiteId(siteId);
            param.setCarrId(carrId);
            param.setUseStatCd(UseStatCd.Usable.name());

            List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStat(param);

            for(WnWipStat w : wipStatList) {
                WnWipStat setParam = new WnWipStat();
                // SET
                setParam.setCrntEqpId(crntEqpId);

                String portId = crntPortId;
                if(crntPortId.indexOf("ASTK") > -1 || crntPortId.indexOf("BUF") > -1) {
                    portId = crntEqpId + "_STORAGE";
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
        log.info("updateCurrentEqpPortByCarrId CALL");
        int updateCnt = 0;




        try {
            WnWipStat param = new WnWipStat();

            // WHERE
            param.setSiteId(siteId);
            param.setCarrId(carrId);
            param.setUseStatCd(UseStatCd.Usable.name());

            List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStat(param);

            for(WnWipStat w : wipStatList) {
                WnWipStat setParam = new WnWipStat();
                // SET
                setParam.setCrntEqpId(crntEqpId);
                setParam.setCrntPortId(crntPortId);

                setParam.setMdfyUserId(userId);
                setParam.setEvntNm(cid);
                setParam.setTid(tid);

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
        log.info("updateDspWorkRepBatch");
        int updateCnt = 0;




        try {
            WnWipStat param = new WnWipStat();

            // WHERE
            param.setSiteId(siteId);
            param.setCarrId(carrId);
            param.setUseStatCd(UseStatCd.Usable.name());
            log.info("carrId >> "+param.getCarrId());
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
     * UPDATE WN_WIP_STAT
     * RTD Dspatching Response -> BothPort, InOut
     * Reserve Eqp, Port, Reserve Group ID
     * @return
     */
    public int updateDspWorkRepNormal(UpdateDspWorkVo updateDspWorkVo) throws Exception{
        int updateCnt = 0;
        try {
            WnWipStat param = new WnWipStat();

            // WHERE
            param.setSiteId(updateDspWorkVo.getSiteId());
            param.setCarrId(updateDspWorkVo.getCarrId());
            param.setLotId(updateDspWorkVo.getLotId());
            param.setUseStatCd(UseStatCd.Usable.name());

            List<WnWipStat> wipStatList = wipStatMapper.selectWnWipStatByLot(param);

            for(WnWipStat w : wipStatList) {
                WnWipStat setParam = new WnWipStat();
                // SET
                setParam.setResvEqpId(updateDspWorkVo.getResvEqpId());
                setParam.setResvPortId(updateDspWorkVo.getResvPortId());
                setParam.setResvGrpId(updateDspWorkVo.getResvGrpId());
                setParam.setResvOutCarrId(updateDspWorkVo.getResvOutCarrId());
                setParam.setResvOutPortId(updateDspWorkVo.getResvOutPortId());

                setParam.setMdfyUserId(updateDspWorkVo.getUserId());
                setParam.setEvntNm(updateDspWorkVo.getCid());
                setParam.setTid(updateDspWorkVo.getTid());

                // WHERE
                setParam.setObjId(w.getObjId());

                log.info("##### UPDATE BEFORE ");
                //Update & Insert History
                if(wipStatMapper.updateWnWipStat(setParam) > 0) {
                    log.info("##### UPDATE AFTER ");
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
     * UPDATE WN_WIP_STAT
     * RTD Dspatching Response -> BothPort, InOut
     * Reserve Eqp, Port, Reserve Group ID
     * @param siteId
     * @param cid
     * @param tid
     * @param carrId
     * @param lotId
     * @param userId
     * @param resvEqpId
     * @param resvPortId
     * @param resvGrpId
     * @return
     */
    public int updateDspWorkRepNormal(String siteId,String cid, String tid, String carrId, String lotId, String userId,
                                      String resvEqpId, String resvPortId, String resvGrpId, String resvOutCarrId, String resvOutPortId) throws Exception{
        int updateCnt = 0;




        try {
            WnWipStat param = new WnWipStat();

            // WHERE
            param.setSiteId(siteId);
            param.setCarrId(carrId);
            param.setLotId(lotId);
            param.setUseStatCd(UseStatCd.Usable.name());

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
                setParam.setObjId(w.getObjId());

                log.info("##### UPDATE BEFORE ");
                //Update & Insert History
                if(wipStatMapper.updateWnWipStat(setParam) > 0) {
                    log.info("##### UPDATE AFTER ");
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




        try {
            log.info("WipStatDAO.updateSelfInspInfo");
            WnWipStat param = new WnWipStat();

            // SET
            param.setMdfyUserId(userId);
            param.setEvntNm(cid);
            param.setTid(tid);
            param.setSelfInspYn(selfInspYn);
            param.setSelfInspInfoObjId(selfInspObjId);
            param.setSelfInspPanelCnt(Integer.valueOf(selfInspPanelCnt));

            log.info("########### SELF INSP VALUE ############");
//			log.info("########### SelfInspYn > " + param.getSelfInspYn());
//			log.info("########### SelfInspInfoObjId > " + param.getSelfInspInfoObjId());
//			log.info("########### SelfInspPanelCnt > " + param.getSelfInspPanelCnt());

            // WHERE
            param.setSiteId(siteId);
            param.setLotId(lotId);
            param.setUseStatCd(UseStatCd.Usable.name());

            // UPDATE
            updateCnt = wipStatMapper.updateWnWipStat(param);
            log.info("updateCnt >> " + updateCnt);

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
                    carrParam.setSiteId(siteId);
                    carrParam.setLotId("-");
                    carrParam.setCarrId(param.getCarrId());
                    carrParam.setUseStatCd(UseStatCd.Usable.name());

                    if(wipStatMapper.updateWnWipStat(carrParam) > 0) {
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




        try {
            log.info("WipStatDAO.updateSampleInfo");
            WnWipStat param = new WnWipStat();

            // SET
            param.setMdfyUserId(userId);
            param.setEvntNm(cid);
            param.setTid(tid);
            param.setSmplLotYn(smplLotYn);
            param.setSmplWorkTyp(smplWorkTyp);
            param.setSmplQty(Integer.parseInt(smplQty));

            // WHERE
            param.setSiteId(siteId);
            param.setLotId(lotId);
            param.setUseStatCd(UseStatCd.Usable.name());

            // UPDATE
            updateCnt = wipStatMapper.updateWnWipStat(param);
            log.info("updateCnt >> " + updateCnt);

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
                    carrParam.setSiteId(siteId);
                    carrParam.setLotId("-");
                    carrParam.setCarrId(param.getCarrId());
                    carrParam.setUseStatCd(UseStatCd.Usable.name());

                    if(wipStatMapper.updateWnWipStat(carrParam) > 0) {
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
