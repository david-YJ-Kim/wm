package com.abs.wfs.workman.dao.domain.tnPort.service;

import com.abs.wfs.workman.dao.domain.tnPort.model.TnPosPort;
import com.abs.wfs.workman.dao.domain.tnPort.repository.TnPosPortRepository;
import com.abs.wfs.workman.dao.domain.tnPort.vo.TnPosPortUpdateRequestDto;
import com.abs.wfs.workman.util.code.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TnPosPortServiceImpl {

    @Autowired
    TnPosPortRepository tnPosPortRepository;


    /**
     * 사용 가능한 Port 상태 조회
     * @param portId
     * @param siteId
     * @return
     */
    public TnPosPort findByPortIdAndSiteIdAndUseStatCd(String portId, String siteId){
        return this.tnPosPortRepository.findByPortIdAndSiteIdAndUseStatCd(portId, siteId, UseStatCd.Usable);
    }

    /**
     * 사용 가능한 설비의 포트 상태 조회
     * @param eqpId
     * @param siteId
     * @return
     */
    public Optional<List<TnPosPort>> findByEqpIdAndSiteIdAndUseStatCd(String eqpId, String siteId){
        return this.tnPosPortRepository.findByEqpIdAndSiteIdAndUseStatCd(eqpId, siteId, UseStatCd.Usable);
    }

    /**
     * Carr 기준 Port ID 조회
     * @param siteId
     * @param eqpId
     * @param carrId
     * @return
     */
    public TnPosPort findBySiteIdAndEqpIdAndCarrIdAndUseStatCd(String siteId, String eqpId, String carrId) {
        return this.tnPosPortRepository.findBySiteIdAndEqpIdAndCarrIdAndUseStatCd(siteId, eqpId, carrId, UseStatCd.Usable);
    }

    /**
     * Port 내 진행 가능 Port List Return
     * @param siteId
     * @param eqpId
     * @return
     */
    public Optional<List<TnPosPort>> findByAvailPortList(String siteId, String eqpId) {
        return this.tnPosPortRepository.findBySiteIdAndEqpIdAndStatCdAndCarrIdAndTrsfStatCdAndAcesModeCdAndCtrlModeCdAndUseStatCd(siteId,eqpId, PortStatCd.Empty, null, TrsfStatCd.ReadyToLoad, AcesModeCd.Auto, PortCtrlModeCd.InService, UseStatCd.Usable);
    }

}
