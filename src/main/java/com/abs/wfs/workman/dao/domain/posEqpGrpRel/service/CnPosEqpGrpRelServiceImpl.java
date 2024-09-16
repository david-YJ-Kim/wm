package com.abs.wfs.workman.dao.domain.posEqpGrpRel.service;

import com.abs.wfs.workman.dao.domain.posEqpGrpRel.model.CnPosEqpGrpRel;
import com.abs.wfs.workman.dao.domain.posEqpGrpRel.repository.CnPosEqpGrpRelRepository;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CnPosEqpGrpRelServiceImpl {

    @Autowired
    CnPosEqpGrpRelRepository cnPosEqpGrpRelRepository;

    public List<CnPosEqpGrpRel> findBySiteIdAndUseStatCd(String site, UseStatCd useStatCd){
        return this.cnPosEqpGrpRelRepository.findBySiteIdAndUseStatCd(site, useStatCd);
    }

    public List<CnPosEqpGrpRel> findBySiteIdAndUseStatCdAndEqpGrpId(String site, UseStatCd useStatCd, String eqpGrpId){
        return this.cnPosEqpGrpRelRepository.findBySiteIdAndUseStatCdAndEqpGrpId(site, useStatCd, eqpGrpId);
    }

    public CnPosEqpGrpRel findBySiteIdAndUseStatCdAndEqpGrpIdAndEqpId(String site, UseStatCd useStatCd, String eqpGrpId, String eqpId){
        return this.cnPosEqpGrpRelRepository.findBySiteIdAndUseStatCdAndEqpGrpIdAndEqpId(site, useStatCd, eqpGrpId, eqpId);
    }
}
