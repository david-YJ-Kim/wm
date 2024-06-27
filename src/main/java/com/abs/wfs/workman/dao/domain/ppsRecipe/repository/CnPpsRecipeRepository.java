package com.abs.wfs.workman.dao.domain.ppsRecipe.repository;

import com.abs.wfs.workman.dao.domain.ppsRecipe.model.CnPpsRecipe;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CnPpsRecipeRepository extends JpaRepository<CnPpsRecipe, String> {


    Optional<List<CnPpsRecipe>> findBySiteIdAndUseStatCdAndEqpId(String siteId, UseStatCd useStatCd, String eppId);

    Optional<CnPpsRecipe> findBySiteIdAndUseStatCdAndEqpIdAndProdDefIdAndProcDefIdAndProcSgmtId(String siteId, UseStatCd useStatCd, String eqpId, String prodDefId, String procDefId, String procSgmtId);
}
