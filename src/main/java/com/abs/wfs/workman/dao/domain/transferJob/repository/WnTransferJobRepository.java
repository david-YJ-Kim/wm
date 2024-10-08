package com.abs.wfs.workman.dao.domain.transferJob.repository;

import com.abs.wfs.workman.dao.domain.transferJob.model.WnTransferJob;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WnTransferJobRepository extends JpaRepository<WnTransferJob, String> {

    /**
     * Object Id로 조회
     * @param objId
     * @param useStatCd
     * @return
     */
    WnTransferJob findByObjIdAndUseStatCdAndSiteId(String objId, UseStatCd useStatCd, String siteId);


    /**
     * Transfer 잡 아이디로 조회 & 사용가능 여부 확인
     * @param jobId
     * @param useStatCd
     * @return
     */
    List<WnTransferJob> findByJobIdAndUseStatCdAndSiteId(String jobId, UseStatCd useStatCd, String siteId);


    /**
     * Carr 아이디로 조회 되는 정보 조회 & 사용 가능 여부 확인
     *
     *
     * @param carrId
     * @param useStatCd
     * @return
     */
    List<WnTransferJob> findByCarrIdAndUseStatCdAndSiteId(String carrId, UseStatCd useStatCd, String siteId);


    /**
     * Source 포트 기준으로 생성된 반송 잡 조회
     * @param srcPortId
     * @param useStatCd
     * @param siteId
     * @return
     */
    List<WnTransferJob> findBySrcPortIdAndUseStatCdAndSiteId(String srcPortId, UseStatCd useStatCd, String siteId);


    /**
     * 목적지 포트 기준으로 생성된 반송 잡 조회
     * @param destPortId
     * @param useStatCd
     * @param siteId
     * @return
     */
    List<WnTransferJob> findByDestPortIdAndUseStatCdAndSiteId(String destPortId, UseStatCd useStatCd, String siteId);
}
