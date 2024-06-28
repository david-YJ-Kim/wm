package com.abs.wfs.workman.dao.domain.workStat.repository;

import com.abs.wfs.workman.dao.domain.workStat.model.WnWorkStat;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WnWorkStatRepository extends JpaRepository<WnWorkStat, String> {

    Optional<WnWorkStat> findByWorkIdAndWorkStatCdAndUseStatCdAndSiteId(String workId, String workStatCd, UseStatCd useStatCd, String siteId);
}
