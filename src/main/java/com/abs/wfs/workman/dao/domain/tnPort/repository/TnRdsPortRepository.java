package com.abs.wfs.workman.dao.domain.tnPort.repository;

import com.abs.wfs.workman.dao.domain.tnPort.model.TnRdsPort;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TnRdsPortRepository extends JpaRepository<TnRdsPort, String> {

    /**
     * 포트 기준 정보 조회
     * @param siteId
     * @param portId
     * @param useStatCd
     * @return
     */
    Optional<TnRdsPort> findBySiteIdAndPortIdAndUseStatCd(String siteId, String portId, UseStatCd useStatCd);
}
