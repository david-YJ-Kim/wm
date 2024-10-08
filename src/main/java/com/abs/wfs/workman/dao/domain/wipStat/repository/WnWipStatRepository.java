package com.abs.wfs.workman.dao.domain.wipStat.repository;

import com.abs.wfs.workman.dao.domain.wipStat.model.WnWipStat;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WnWipStatRepository extends JpaRepository<WnWipStat, String> {


        /**
         * Wip, 예약 설비 · 포트, Lot으로 조회
         * @param siteId
         * @param resvEqpId
         * @param resvPortId
         * @param lotId
         * @param useStatCd
         * @return
         */
        WnWipStat findBySiteIdAndResvEqpIdAndResvPortIdAndLotIdAndUseStatCd(String siteId, String resvEqpId, String resvPortId, String lotId, UseStatCd useStatCd);


        /**
         * Wip에 예약 설비, 포트로 조회
         * @param siteId
         * @param resvEqpId
         * @param resvPortId
         * @param useStatCd
         * @return
         */
        List<WnWipStat> findBySiteIdAndResvEqpIdAndResvPortIdAndUseStatCd(String siteId, String resvEqpId, String resvPortId, UseStatCd useStatCd);

        WnWipStat findByLotIdAndSiteIdAndUseStatCd(String lotId, String siteId, UseStatCd useStatCd);

        WnWipStat findByLotIdAndSiteIdAndUseStatCdAndResvEqpIdAndResvPortId(String lotId, String siteId, UseStatCd useStatCd, String resvEqpId, String resvPortId);

        WnWipStat findByCarrIdAndLotIdAndSiteIdAndUseStatCd(String carrId, String lotId,String siteId, UseStatCd useStatCd);

}
