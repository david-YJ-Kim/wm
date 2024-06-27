package com.abs.wfs.workman.dao.domain.posEqpGrpRel.repository;

import com.abs.wfs.workman.dao.domain.efemAlarm.model.CnEfemAlarm;
import com.abs.wfs.workman.dao.domain.posEqpGrpRel.model.CnPosEqpGrpRel;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CnPosEqpGrpRelRepository extends JpaRepository<CnPosEqpGrpRel, String> {

    /**
     * 사이트와 사용한 로우 조회
     * @param siteId
     * @param useStatCd
     * @return
     */
    List<CnPosEqpGrpRel> findBySiteIdAndUseStatCd(String siteId, UseStatCd useStatCd);


    /**
     * 설비 그룹별 사용 가능한 로우 조회
     * @param siteId
     * @param useStatCd
     * @param eqpGrpId
     * @return
     */
    List<CnPosEqpGrpRel> findBySiteIdAndUseStatCdAndEqpGrpId(String siteId, UseStatCd useStatCd, String eqpGrpId);


    /**
     * 설비 그룹별 설비 조회
     * @param siteId
     * @param useStatCd
     * @param eqpGrpId
     * @param eqpId
     * @return
     */
    Optional<CnPosEqpGrpRel> findBySiteIdAndUseStatCdAndEqpGrpIdAndEqpId(String siteId, UseStatCd useStatCd, String eqpGrpId, String eqpId);
}
