package com.abs.wfs.workman.dao.domain.toolVer.repository;

import com.abs.wfs.workman.dao.domain.toolVer.model.CnPosToolVer;
import com.abs.wfs.workman.util.code.AlarmEqpType;
import com.abs.wfs.workman.util.code.PortType;
import com.abs.wfs.workman.util.code.UseStatCd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CnPosToolVerRepository extends JpaRepository<CnPosToolVer, String> {

    /**
     * EQP · EFEM 버전 조회
     * @param siteId
     * @param eqpId
     * @param toolTyp
     * @param useStatCd
     * @return
     */
    CnPosToolVer findBySiteIdAndEqpIdAndToolTypAndUseStatCd(String siteId, String eqpId, AlarmEqpType toolTyp, UseStatCd useStatCd);


    /**
     * 포트 타입 별 EFEM 버전 조회
     * @param siteId
     * @param eqpId
     * @param toolType
     * @param portTyp
     * @param useStatCd
     * @return
     */
    CnPosToolVer findBySiteIdAndEqpIdAndToolTypAndPortTypAndUseStatCd(String siteId, String eqpId, AlarmEqpType toolType, PortType portTyp, UseStatCd useStatCd);
}
