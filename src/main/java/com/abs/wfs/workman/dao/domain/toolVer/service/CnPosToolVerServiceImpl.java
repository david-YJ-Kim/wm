package com.abs.wfs.workman.dao.domain.toolVer.service;

import com.abs.wfs.workman.dao.domain.toolVer.model.ChPosToolVer;
import com.abs.wfs.workman.dao.domain.toolVer.model.CnPosToolVer;
import com.abs.wfs.workman.dao.domain.toolVer.repository.ChPosToolVerRepository;
import com.abs.wfs.workman.dao.domain.toolVer.repository.CnPosToolVerRepository;
import com.abs.wfs.workman.dao.domain.toolVer.vo.ChPosToolVerDto;
import com.abs.wfs.workman.dao.domain.toolVer.vo.CnPosToolVerDto;
import com.abs.wfs.workman.spec.in.eap.WfsToolVerReportIvo;
import com.abs.wfs.workman.util.code.AlarmEqpType;
import com.abs.wfs.workman.util.code.PortType;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;


@Service
@Slf4j
public class CnPosToolVerServiceImpl implements CnPosToolVerService{

    @Autowired
    CnPosToolVerRepository cnPosToolVerRepository;

    @Autowired
    ChPosToolVerRepository chPosToolVerRepository;

    public CnPosToolVer saveToolVer(WfsToolVerReportIvo ivo){

        WfsToolVerReportIvo.Body body = ivo.getBody();

        String siteId = body.getSiteId();
        String eqpId = body.getEqpId();
        AlarmEqpType eqpType = body.getEqpType();
        String portType = body.getPortType();


        // 기존 데이터 조회
        CnPosToolVer crntToolVer = null;
        if(eqpType.equals(AlarmEqpType.EFEM)){
            crntToolVer = this.cnPosToolVerRepository.findBySiteIdAndEqpIdAndToolTypAndPortTypAndUseStatCd(siteId, eqpId, eqpType, PortType.valueOf(portType), UseStatCd.Usable);

        }else{
            crntToolVer = this.cnPosToolVerRepository.findBySiteIdAndEqpIdAndToolTypAndUseStatCd(siteId, eqpId, eqpType, UseStatCd.Usable);
        }


        // 기존 정보가 없다면 Insert
        if(crntToolVer == null){
            CnPosToolVer newInsertedRecord = this.insertNewRecord(ivo);
            this.insertHistoryRecord(newInsertedRecord);
            return newInsertedRecord;

        }else{

            // 기존 정보 업데이트
            crntToolVer.setVersion(body.getVersion());
            crntToolVer.setPrevEvntNm(crntToolVer.getEvntNm());
            crntToolVer.setEvntNm(WfsToolVerReportIvo.cid);
            crntToolVer.setMdfyDt(Timestamp.from(Instant.now()));
            crntToolVer.setFnlEvntDt(Timestamp.from(Instant.now()));
            this.cnPosToolVerRepository.save(crntToolVer);

            this.insertHistoryRecord(crntToolVer);
            return crntToolVer;

        }
    }


    private CnPosToolVer insertNewRecord(WfsToolVerReportIvo ivo){

        CnPosToolVerDto cnPosToolVerDto = new CnPosToolVerDto(ivo);
        return this.cnPosToolVerRepository.save(cnPosToolVerDto.toEntity());

    }

    private ChPosToolVer insertHistoryRecord(CnPosToolVer toolVer){
        ChPosToolVerDto chPosToolVerDto = new ChPosToolVerDto(toolVer);

        ChPosToolVer chPosToolVer = this.chPosToolVerRepository.save(chPosToolVerDto.toEntity());
        log.info("inserted history record. {}", chPosToolVer);
        return chPosToolVer;
    }






}
