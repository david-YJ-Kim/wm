package com.abs.wfs.workman.dao.domain.workJobSlotInfo.repository;

import com.abs.wfs.workman.dao.domain.workJobSlotInfo.model.WnWorkJobSlotInfo;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WnWorkJobSlotInfoRepository extends JpaRepository<WnWorkJobSlotInfo, String> {


    /**
     * 작업 보고 받은 패널에 대한 조회
     * @param siteId
     * @param workId
     * @param prodMtrlId
     * @param slotNo
     * @param mtrlFaceCd
     * @param useStatCd
     * @return
     */
    Optional<WnWorkJobSlotInfo> findBySiteIdAndWorkIdAndProdMtrlIdAndSlotNoAndMtrlFaceCdAndUseStatCd(String siteId, String workId, String prodMtrlId, String slotNo, String mtrlFaceCd, UseStatCd useStatCd);


    /**
     * 기본 Work Slot 정보 조회
     * @param workId
     * @param siteId
     * @param useStatCd
     * @return
     */
    Optional<List<WnWorkJobSlotInfo>> findByWorkIdAndSiteIdAndUseStatCd(String workId, String siteId, UseStatCd useStatCd);


    /**
     * 작업 시작 이전 Slot 정보 조회
     * @param workId
     * @param siteId
     * @param useStatCd
     * @return
     */
    Optional<List<WnWorkJobSlotInfo>> findByWorkIdAndSiteIdAndUseStatCdAndProdMtrlStrtTmIsNotNull(String workId, String siteId, UseStatCd useStatCd);


    /**
     * 자주검사 Work Slot 정보 조회
     * @param workId
     * @param siteId
     * @param useStatCd
     * @return
     */
    Optional<List<WnWorkJobSlotInfo>> findByWorkIdAndSiteIdAndUseStatCdAndSelfInspYnOrderBySlotNo(String workId, String siteId, UseStatCd useStatCd, String selfInspYn);

}
