package com.abs.wfs.workman.dao.domain.tnLot.repository;

import com.abs.wfs.workman.dao.domain.tnLot.model.TnPosLot;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TnPosLotRepository extends JpaRepository<TnPosLot, String> {


    Optional<TnPosLot> findBySiteIdAndLotIdAndUseStatCd(String siteId, String lotId, UseStatCd useStatus);


    /**
     * EQP로 Port 정보 조회
     * @param siteId
     * @param eqpId
     * @param useStatus
     * @return
     */
    Optional<List<TnPosLot>> findBySiteIdAndEqpIdAndUseStatCd(String siteId, String eqpId, UseStatCd useStatus);

    Optional<TnPosLot> findBySiteIdAndCarrIdAndUseStatCd(String siteId, String carrId, UseStatCd useStatus);

}
