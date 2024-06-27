package com.abs.wfs.workman.dao.domain.stateRuleInfo.repository;

import com.abs.wfs.workman.dao.domain.stateRuleInfo.model.WnStateRuleInfo;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WnStateRuleInfoRepository extends JpaRepository<WnStateRuleInfo, String> {

    List<WnStateRuleInfo> findBySiteIdAndUseStatCd(String siteId, UseStatCd useStatCd);
}
