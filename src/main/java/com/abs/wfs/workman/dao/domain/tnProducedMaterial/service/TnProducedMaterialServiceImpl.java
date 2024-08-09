package com.abs.wfs.workman.dao.domain.tnProducedMaterial.service;

import com.abs.wfs.workman.dao.domain.tnProducedMaterial.model.TnProducedMaterial;
import com.abs.wfs.workman.dao.domain.tnProducedMaterial.repository.TnProducedMaterialRepository;
import com.abs.wfs.workman.dao.query.model.TnPosProducedMaterial;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TnProducedMaterialServiceImpl {

    @Autowired
    TnProducedMaterialRepository tnProducedMaterialRepository;

    public Optional<List<TnProducedMaterial>> findBySiteIdAndLotIdAndUseStatCdOrderBySlotNo(String siteId, String lotId, UseStatCd useStatCd){
        return this.tnProducedMaterialRepository.findBySiteIdAndLotIdAndUseStatCdOrderBySlotNo(siteId, lotId, useStatCd);
    }

    /**
     * Lot Id와 ProdMtrl Id로 패널 정보 조회
     * @param siteId
     * @param lotId
     * @param prodMtrlId
     * @return
     */
    public TnProducedMaterial findByLotIdAndProdMtrlId(String siteId, String lotId, String prodMtrlId){
        return this.tnProducedMaterialRepository.findBySiteIdAndLotIdAndProdMtrlIdAndUseStatCd(siteId, lotId, prodMtrlId,UseStatCd.Usable);
    }


    /**
     * 패널정보로 탐색하기
     * @param siteId
     * @param prodMtrlId
     * @return
     */
    public TnProducedMaterial findByProdMtrlId(String siteId, String prodMtrlId){
        return this.tnProducedMaterialRepository.findBySiteIdAndProdMtrlIdAndUseStatCd(siteId, prodMtrlId,UseStatCd.Usable);
    }

    /**
     * Tray에 Assign된 패널정보 탐색
     * @param siteId
     * @param carrId
     * @return
     */
    public TnProducedMaterial findByCarrId(String siteId, String carrId) {
        return this.tnProducedMaterialRepository.findBySiteIdAndCarrIdAndUseStatCd(siteId, carrId, UseStatCd.Usable);
    }


}
