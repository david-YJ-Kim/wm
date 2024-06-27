package com.abs.wfs.workman.dao.domain.tnProducedMaterial.service;

import com.abs.wfs.workman.dao.domain.tnProducedMaterial.model.TnProducedMaterial;
import com.abs.wfs.workman.dao.domain.tnProducedMaterial.repository.TnProducedMaterialRepository;
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
}
