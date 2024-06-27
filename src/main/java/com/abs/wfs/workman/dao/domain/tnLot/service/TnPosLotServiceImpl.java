package com.abs.wfs.workman.dao.domain.tnLot.service;

import com.abs.wfs.workman.dao.domain.tnLot.model.TnPosLot;
import com.abs.wfs.workman.dao.domain.tnLot.repository.TnPosLotRepository;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TnPosLotServiceImpl {


    @Autowired
    private TnPosLotRepository tnPosLotRepository;


    public Optional<TnPosLot> findBySiteIdAndLotIdAndUseStatCd(String siteId, String lotId, UseStatCd useStatCd) {
        return this.tnPosLotRepository.findBySiteIdAndLotIdAndUseStatCd(siteId, lotId, useStatCd);
    }



}
