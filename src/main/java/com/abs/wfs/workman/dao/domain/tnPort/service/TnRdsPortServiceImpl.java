package com.abs.wfs.workman.dao.domain.tnPort.service;

import com.abs.wfs.workman.dao.domain.tnPort.model.TnRdsPort;
import com.abs.wfs.workman.dao.domain.tnPort.repository.TnRdsPortRepository;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TnRdsPortServiceImpl {

    @Autowired
    TnRdsPortRepository tnRdsPortRepository;


    /**
     * 포트 기준 정보 조회
     * @param siteId
     * @param portId
     * @return
     */
    public Optional<TnRdsPort> findBySiteIdAndPortIdAndUseStatCd(String siteId, String portId){
        return this.tnRdsPortRepository.findBySiteIdAndPortIdAndUseStatCd(siteId, portId, UseStatCd.Usable);
    }

}
