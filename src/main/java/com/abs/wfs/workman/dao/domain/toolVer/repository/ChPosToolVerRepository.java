package com.abs.wfs.workman.dao.domain.toolVer.repository;

import com.abs.wfs.workman.dao.domain.toolVer.model.ChPosToolVer;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChPosToolVerRepository extends JpaRepository<ChPosToolVer, String> {


    List<ChPosToolVer> findBySiteIdAndEqpIdAndUseStatCd(String siteId, String eqpId, UseStatCd useStatCd);
}
