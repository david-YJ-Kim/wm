package com.abs.wfs.workman.dao.domain.ppsProdDef.repository;

import com.abs.wfs.workman.dao.domain.ppsProdDef.model.TnPpsProdDef;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TnPpsProdDefRepository extends JpaRepository<TnPpsProdDef, String> {

    Optional<TnPpsProdDef> findBySiteIdAndUseStatCdAndProdDefIdAndShipUnitTypIsNotNull(String siteId, UseStatCd useStatCd, String prodDefId);
}
