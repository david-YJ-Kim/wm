package com.abs.wfs.workman.dao.domain.workJobSlotInfo.service;


import com.abs.wfs.workman.dao.domain.workJobSlotInfo.model.WnWorkJobSlotInfo;
import com.abs.wfs.workman.dao.domain.workJobSlotInfo.repository.WnWorkJobSlotInfoRepository;
import com.abs.wfs.workman.util.code.UseStatCd;
import com.abs.wfs.workman.util.code.UseYn;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WnWorkJobSlotInfoServiceImpl {


    @Autowired
    WnWorkJobSlotInfoRepository wnWorkJobSlotInfoRepository;


    /**
     * 작업 보고 받은 패널에 대한 조회
     * @param siteId
     * @param workId
     * @param prodMtrlId
     * @param slotNo
     * @param mtrlFaceCd
     * @return
     */
    public Optional<WnWorkJobSlotInfo> findPanelWithReportInfo(String siteId, String workId, String prodMtrlId, String slotNo, String mtrlFaceCd){
        return this.wnWorkJobSlotInfoRepository.findBySiteIdAndWorkIdAndProdMtrlIdAndSlotNoAndMtrlFaceCdAndUseStatCd(siteId, workId, prodMtrlId, slotNo, mtrlFaceCd, UseStatCd.Usable);
    }




    /**
     * 기본 Work Slot 정보 조회
     * @param workId
     * @param siteId
     * @return
     */
    public Optional<List<WnWorkJobSlotInfo>> findByWorkIdAndSiteIdAndUseStatCd(String workId, String siteId){

        return this.wnWorkJobSlotInfoRepository.findByWorkIdAndSiteIdAndUseStatCd(workId, siteId, UseStatCd.Usable);
    }


    /**
     * 작업 시작 이전 Slot 정보 조회
     * @param workId
     * @param siteId
     * @return
     */
    public Optional<List<WnWorkJobSlotInfo>> findByWorkIdAndSiteIdAndUseStatCdAndProdMtrlStrtTmIsNotNull(String workId, String siteId){

        return this.wnWorkJobSlotInfoRepository.findByWorkIdAndSiteIdAndUseStatCdAndProdMtrlStrtTmIsNotNull(workId, siteId, UseStatCd.Usable);
    }



    /**
     * 자주검사 Work Slot 정보 조회
     * @param workId
     * @param siteId
     * @return
     */
    public Optional<List<WnWorkJobSlotInfo>> findByWorkIdAndSiteIdAndUseStatCdAndSelfInspYn(String workId, String siteId){

        return this.wnWorkJobSlotInfoRepository.findByWorkIdAndSiteIdAndUseStatCdAndSelfInspYnOrderBySlotNo(workId, siteId, UseStatCd.Usable, UseYn.Y.name());
    }


}
