package com.abs.wfs.workman.dao.domain.tnLot.repository;

import com.abs.wfs.workman.dao.domain.tnLot.model.TnPosLot;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TnPosLotRepository extends JpaRepository<TnPosLot, String> {


    Optional<TnPosLot> findBySiteIdAndLotIdAndUseStatCd(String siteId, String lotId, UseStatCd useStatus);

}
