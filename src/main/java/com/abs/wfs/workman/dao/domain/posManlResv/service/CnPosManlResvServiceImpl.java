package com.abs.wfs.workman.dao.domain.posManlResv.service;

import com.abs.wfs.workman.dao.domain.posManlResv.model.CnPosManlResv;
import com.abs.wfs.workman.dao.domain.posManlResv.repository.CnPosManlResvRepository;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CnPosManlResvServiceImpl {
    @Autowired
    CnPosManlResvRepository cnPosManlResvRepository;

    public Optional<CnPosManlResv> findBySiteIdAndEqpIdAndLotIdAndProcSgmtIdAndUseStatCd(String siteId, String eqpId, String lotId, String procSgmtId, UseStatCd useStatCd) {
        return this.cnPosManlResvRepository.findBySiteIdAndEqpIdAndLotIdAndProcSgmtIdAndUseStatCd(siteId, eqpId, lotId, procSgmtId, useStatCd);
    }
}
