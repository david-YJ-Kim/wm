package com.abs.wfs.workman.dao.domain.ppsProdDef.service;


import com.abs.wfs.workman.dao.domain.ppsProdDef.model.TnPpsProdDef;
import com.abs.wfs.workman.dao.domain.ppsProdDef.repository.TnPpsProdDefRepository;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TnPpsProdDefServiceImpl {

    @Autowired
    TnPpsProdDefRepository tnPpsProdDefRepository;

    public Optional<TnPpsProdDef> findBySiteIdAndUseStatCdAndProdDefIdAndShipUnitTypIsNotNull(String siteId, UseStatCd useStatCd, String prodDefId){

        return this.tnPpsProdDefRepository.findBySiteIdAndUseStatCdAndProdDefIdAndShipUnitTypIsNotNull(siteId, useStatCd, prodDefId);
    }



}
