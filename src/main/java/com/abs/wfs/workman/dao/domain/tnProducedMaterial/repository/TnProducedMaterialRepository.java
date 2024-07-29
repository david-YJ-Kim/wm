package com.abs.wfs.workman.dao.domain.tnProducedMaterial.repository;

import com.abs.wfs.workman.dao.domain.tnProducedMaterial.model.TnProducedMaterial;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TnProducedMaterialRepository extends JpaRepository<TnProducedMaterial, String> {

    Optional<List<TnProducedMaterial>> findBySiteIdAndLotIdAndUseStatCdOrderBySlotNo(String siteId, String lotId, UseStatCd useStatCd);


    /**
     * Lot Id와 ProdMtrl Id로 패널 정보 조회
     * @param siteId
     * @param lotId
     * @param prodMtrlId
     * @param useStatCd
     * @return
     */
    TnProducedMaterial findBySiteIdAndLotIdAndProdMtrlIdAndUseStatCd(String siteId, String lotId, String prodMtrlId, UseStatCd useStatCd);;


    /**
     * 패널정보로 탐색하기
     * @param siteId
     * @param prodMtrlId
     * @param useStatCd
     * @return
     */
    TnProducedMaterial findBySiteIdAndProdMtrlIdAndUseStatCd(String siteId, String prodMtrlId, UseStatCd useStatCd);
}
