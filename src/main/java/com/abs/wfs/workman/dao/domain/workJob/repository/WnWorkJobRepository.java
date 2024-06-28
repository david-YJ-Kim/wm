package com.abs.wfs.workman.dao.domain.workJob.repository;

import com.abs.wfs.workman.dao.domain.workJob.model.WnWorkJob;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WnWorkJobRepository extends JpaRepository<WnWorkJob, String> {

    Optional<WnWorkJob> findByWorkIdAndSiteIdAndJobStatCdAndUseStatCd(String workId, String siteId, String jobStatCd, UseStatCd useStatCd);
}
