package com.abs.wfs.workman.dao.domain.posManlResv.repository;

import com.abs.wfs.workman.dao.domain.posManlResv.model.CnPosManlResv;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CnPosManlResvRepository extends JpaRepository<CnPosManlResv, String> {
    /**
     * AUTO Track In Resv
     * @param siteId
     * @param eqpId
     * @param lotId
     * @param procSgmtId
     * @param useStatCd
     * @return
     */
    Optional<CnPosManlResv> findBySiteIdAndEqpIdAndLotIdAndProcSgmtIdAndUseStatCd(String siteId, String eqpId, String lotId, String procSgmtId, UseStatCd useStatCd);
}
