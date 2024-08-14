package com.abs.wfs.workman.dao.domain.tnPort.repository;

import com.abs.wfs.workman.dao.domain.tnPort.model.TnPosPort;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TnPosPortRepository extends JpaRepository<TnPosPort, String> {



    /**
     * 사용 가능한 설비의 포트 상태 조회
     * @param eqpId
     * @param siteId
     * @param useStatCd
     * @return
     */
    Optional<List<TnPosPort>> findByEqpIdAndSiteIdAndUseStatCd(String eqpId, String siteId, UseStatCd useStatCd);

    /**
     * 사용 가능한 Port 상태 조회
     * @param portId
     * @param siteId
     * @param useStatCd
     * @return
     */
    TnPosPort findByPortIdAndSiteIdAndUseStatCd(String portId, String siteId, UseStatCd useStatCd);

    /**
     *
     * @param siteId
     * @param eqpId
     * @param useStatCd
     * @return
     */
    TnPosPort findBySiteIdAndEqpIdAndCarrIdAndUseStatCd(String siteId, String eqpId, String carrId, UseStatCd useStatCd);
}
