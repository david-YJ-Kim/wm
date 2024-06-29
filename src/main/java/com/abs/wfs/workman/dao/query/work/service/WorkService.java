package com.abs.wfs.workman.dao.query.work.service;

import com.abs.wfs.workman.dao.query.wip.mapper.WipStatMyMapper;
import com.abs.wfs.workman.dao.query.work.mapper.WorkMyMapper;
import com.abs.wfs.workman.dao.query.work.vo.WnWorkStat;
import com.abs.wfs.workman.dao.query.work.vo.WorkJobLotQueryDto;
import com.abs.wfs.workman.dao.query.work.vo.WorkSlotListInfoQueryDto;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WorkService {

    @Autowired
    WorkMyMapper workMapper;

    @Autowired
    WipStatMyMapper wipStatMapper;



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


        int resultCnt = -1;

        try {
            WnWorkStat param = new WnWorkStat();
            //SET
            param.setEvntNm(cid);
            param.setMdfyUserId(userId);
            param.setTid(tid);
            if(!"".equals(inlineStatCd)) param.setInlineStatCd(inlineStatCd);


            //WHERE
            param.setUseStatCd(UseStatCd.Usable.name());
            param.setSiteId(siteId);
            param.setWorkId(workId);


            //update WN_WORK_STAT
            resultCnt = this.workMapper.updateWnWorkStat(param);

            if(resultCnt > 0) {
                // insert WH_WORK_STAT
                this.workMapper.createWhWorkStat(param.getObjId());
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {

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

        int resultCnt = -1;

        try {
            WnWorkStat param = new WnWorkStat();
            //SET
            param.setEvntNm(cid);
            param.setMdfyUserId(userId);
            param.setTid(tid);
            param.setRsnCd(rsnCd); //1MORE


            //WHERE
            param.setPUseStatCd(UseStatCd.Usable.name());
            param.setPSiteId(siteId);
            param.setPWorkId(workId);


            //update WN_WORK_STAT
            resultCnt = workMapper.updateWnWorkStat(param);

            if(resultCnt > 0) {
                // insert WH_WORK_STAT
                workMapper.createWhWorkStat(param.getObjId());
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
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


        List<WnWorkStat> wnWorkStatList = null;

        try {
            WnWorkStat param = new WnWorkStat();
            param.setUseStatCd(UseStatCd.Usable.name());
            param.setSiteId(siteId);
            param.setWorkId(workId);

            wnWorkStatList = this.workMapper.selectWnWorkStat(param);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return wnWorkStatList;
    }


    /**
     * CARR ID로 LOT 조회
     * @param dto
     * @return
     * @throws Exception
     */
    public Optional<WorkJobLotQueryDto> searchWorkJobLotQueryDto(WorkJobLotQueryDto dto) throws Exception{
        return this.workMapper.selectWorkJobLotQueryDto(dto);
    }


    /**
     * Lot으로 현재 slot에 존재하는 패널의 양불 정보 획득
     * @param dto
     * @return
     * @throws Exception
     */
    public Optional<List<WorkSlotListInfoQueryDto>> searchWorkSlotListInfoQueryDto(WorkSlotListInfoQueryDto dto) throws Exception{
        return this.workMapper.selectWorkSlotListInfoQueryDto(dto);
    }
}
