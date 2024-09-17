package com.abs.wfs.workman.dao.domain.ppsEqpSchd.repository;

import com.abs.wfs.workman.dao.domain.posManlResv.model.CnPosManlResv;
import com.abs.wfs.workman.dao.domain.ppsEqpSchd.model.CnPpsEqpSchd;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CnPpsEqpSchdRepository extends JpaRepository<CnPpsEqpSchd, String> {
//    Optional<CnPpsEqpSchd> findBySiteIdAAndEqpIdAndLotId(String siteId, String eqpId, String lotId);
    Optional<CnPpsEqpSchd> findBySiteIdAndEqpIdAndLotIdAndProdDefIdAndProcDefIdAndProcSgmtIdAndUseStatCd(String siteId, String eqpId, String lotId, String prodDefId, String procDefId, String procSgmtId, UseStatCd useStatCd);
}
