package com.abs.wfs.workman.dao.domain.pmsProcNode.repository;

import com.abs.wfs.workman.dao.domain.pmsProcNode.model.TnPmsProcNode;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TnPmsProcNodeRepository extends JpaRepository<TnPmsProcNode, String> {

    Optional<TnPmsProcNode> findBySiteIdAndUseStatCdAndProcNodeId(String siteId, UseStatCd useStatCd, String procNodeId);
}
