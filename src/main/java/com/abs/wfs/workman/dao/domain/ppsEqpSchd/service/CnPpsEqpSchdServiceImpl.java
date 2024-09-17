package com.abs.wfs.workman.dao.domain.ppsEqpSchd.service;

import com.abs.wfs.workman.dao.domain.ppsEqpSchd.model.CnPpsEqpSchd;
import com.abs.wfs.workman.dao.domain.ppsEqpSchd.repository.CnPpsEqpSchdRepository;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CnPpsEqpSchdServiceImpl {
    @Autowired
    CnPpsEqpSchdRepository cnPpsEqpSchdRepository;

    public Optional<CnPpsEqpSchd> findByAutoRelease(String siteId, String eqpId, String lotId, String prodDefId, String procDefId, String procSgmtId, UseStatCd useStatCd) {
        return cnPpsEqpSchdRepository.findBySiteIdAndEqpIdAndLotIdAndProdDefIdAndProcDefIdAndProcSgmtIdAndUseStatCd(siteId,eqpId, lotId, prodDefId, procDefId, procSgmtId, useStatCd);
    }
}
