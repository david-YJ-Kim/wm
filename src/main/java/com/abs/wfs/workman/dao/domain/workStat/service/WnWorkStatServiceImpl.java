package com.abs.wfs.workman.dao.domain.workStat.service;


import com.abs.wfs.workman.config.ApPropertyObject;
import com.abs.wfs.workman.dao.domain.workStat.model.WnWorkStat;
import com.abs.wfs.workman.dao.domain.workStat.repository.WnWorkStatRepository;
import com.abs.wfs.workman.util.code.ApStringConstant;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class WnWorkStatServiceImpl {

    @Autowired
    WnWorkStatRepository wnWorkStatRepository;


    public Optional<WnWorkStat> findByActiveAndUsableWorkWithWorkId(String siteId, String workId){
        return this.wnWorkStatRepository.findByWorkIdAndWorkStatCdAndUseStatCdAndSiteId(workId, ApStringConstant.Active, UseStatCd.Usable, siteId);
    }

}
