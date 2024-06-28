package com.abs.wfs.workman.dao.domain.workJob.service;

import com.abs.wfs.workman.dao.domain.workJob.model.WnWorkJob;
import com.abs.wfs.workman.dao.domain.workJob.repository.WnWorkJobRepository;
import com.abs.wfs.workman.dao.domain.workStat.repository.WnWorkStatRepository;
import com.abs.wfs.workman.util.code.ApStringConstant;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class WnWorkJobServiceImpl {


    @Autowired
    WnWorkJobRepository workJobRepository;

   public Optional<WnWorkJob> findByWorkIdAndSiteIdAndJobStatCdAndUseStatCd(String workId, String siteId){
       return this.workJobRepository.findByWorkIdAndSiteIdAndJobStatCdAndUseStatCd(workId, siteId, ApStringConstant.Active, UseStatCd.Usable);
   }

}
