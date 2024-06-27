package com.abs.wfs.workman.dao.domain.tnProducedMaterial.repository;

import com.abs.wfs.workman.dao.domain.tnProducedMaterial.model.TnProducedMaterial;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TnProducedMaterialRepository extends JpaRepository<TnProducedMaterial, String> {

    Optional<List<TnProducedMaterial>> findBySiteIdAndLotIdAndUseStatCdOrderBySlotNo(String siteId, String lotId, UseStatCd useStatCd);
}
