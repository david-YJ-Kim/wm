package com.abs.wfs.workman.dao.query.transfer.service;

import com.abs.wfs.workman.dao.query.mapper.TransferJobMapper;
import com.abs.wfs.workman.dao.query.transfer.mapper.TransferJobMyMapper;
import com.abs.wfs.workman.dao.query.transfer.vo.WnTransferJob;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class TransferJobServiceImpl implements TransferJobService{

    @Autowired
    TransferJobMyMapper mapper;


    /**
     *
     * @param wnTransferJob
     * @return
     */
    public List<WnTransferJob> selectTransferJobWithinTime(WnTransferJob wnTransferJob){

        return mapper.selectTransferJobWithinTime(wnTransferJob);

    }

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
            resultVal = mapper.createWnTransferJob(param);

            if(resultVal > 0) {
                // CREATE History
                mapper.createWhTransferJob(param.getObjId());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
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
            param.setPSiteId(siteId);
            param.setPJobId(jobId);

            resultVal = mapper.updateWnTransferJob(param);

            if(resultVal > 0) {
                // CREATE History
                mapper.createWhTransferJob(param.getObjId());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {

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
            param.setPSiteId(siteId);
            param.setPJobId(jobId);

            resultVal = mapper.updateWnTransferJob(param);

            if(resultVal > 0) {
                // CREATE History
                mapper.createWhTransferJob(param.getObjId());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {

        }

        return resultVal;
    }

    public int deleteTransferJobByJobId(String siteId, String jobId) throws Exception{

        int resultVal = -1;

        try {
            WnTransferJob param = new WnTransferJob();
            param.setPSiteId(siteId);
            param.setPJobId(jobId);
            resultVal = mapper.deleteTransferJob(param);

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
        }

        return resultVal;
    }

}
