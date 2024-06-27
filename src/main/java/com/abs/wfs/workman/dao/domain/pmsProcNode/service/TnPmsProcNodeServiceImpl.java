package com.abs.wfs.workman.dao.domain.pmsProcNode.service;


import com.abs.wfs.workman.dao.domain.pmsProcNode.model.TnPmsProcNode;
import com.abs.wfs.workman.dao.domain.pmsProcNode.repository.TnPmsProcNodeRepository;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TnPmsProcNodeServiceImpl {

    @Autowired
    TnPmsProcNodeRepository tnPmsProcNodeRepository;

    public Optional<TnPmsProcNode> findBySiteIdAndUseStatCdAndProcNodeId(String siteId, UseStatCd useStatCd, String procNodeId){
        return this.tnPmsProcNodeRepository.findBySiteIdAndUseStatCdAndProcNodeId(siteId, useStatCd, procNodeId);
    }


}
