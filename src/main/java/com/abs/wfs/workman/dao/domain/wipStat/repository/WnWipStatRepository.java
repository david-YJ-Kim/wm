package com.abs.wfs.workman.dao.domain.wipStat.repository;

import com.abs.wfs.workman.dao.domain.wipStat.model.WnWipStat;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WnWipStatRepository extends JpaRepository<WnWipStat, String> {


        Optional<WnWipStat> findByLotIdAndSiteIdAndUseStatCd(String lotId, String siteId, UseStatCd useStatCd);

        Optional<WnWipStat> findByLotIdAndSiteIdAndUseStatCdAndResvEqpIdAndResvPortId(String lotId, String siteId, UseStatCd useStatCd, String resvEqpId, String resvPortId);

        Optional<WnWipStat> findByCarrIdAndLotIdAndSiteIdAndUseStatCd(String carrId, String lotId,String siteId, UseStatCd useStatCd);

}
