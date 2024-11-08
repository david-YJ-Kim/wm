package com.abs.wfs.workman.dao.domain.tnLot.service;

import com.abs.wfs.workman.dao.domain.tnLot.model.TnPosLot;
import com.abs.wfs.workman.dao.domain.tnLot.repository.TnPosLotRepository;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TnPosLotServiceImpl {


    @Autowired
    private TnPosLotRepository tnPosLotRepository;


    /**
     * EQP로 Port 정보 조회
     * @param siteId
     * @param eqpId
     * @return
     */
    public Optional<List<TnPosLot>> findBySiteIdAndEqpIdAndUseStatCd(String siteId, String eqpId){
        return this.tnPosLotRepository.findBySiteIdAndEqpIdAndUseStatCd(siteId,eqpId, UseStatCd.Usable);
    }



    public Optional<TnPosLot> findBySiteIdAndLotIdAndUseStatCd(String siteId, String lotId, UseStatCd useStatCd) {
        return this.tnPosLotRepository.findBySiteIdAndLotIdAndUseStatCd(siteId, lotId, useStatCd);
    }

    public Optional<TnPosLot> findBySiteIdAndCarrIdAndUseStatCd(String siteId, String carrId, UseStatCd useStatCd) {
        return this.tnPosLotRepository.findBySiteIdAndCarrIdAndUseStatCd(siteId, carrId, useStatCd);
    }



}
