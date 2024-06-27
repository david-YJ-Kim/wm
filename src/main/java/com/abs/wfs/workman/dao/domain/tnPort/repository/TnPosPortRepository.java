package com.abs.wfs.workman.dao.domain.tnPort.repository;

import com.abs.wfs.workman.dao.domain.tnPort.model.TnPosPort;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TnPosPortRepository extends JpaRepository<TnPosPort, String> {


//    List<TnPosPort> searchTnPosPortBySiteIdAndEqpIdAndPortIdAndUseStatCd(String siteId, String eqpId, String portId, UseStatCd useStatCd);

}
