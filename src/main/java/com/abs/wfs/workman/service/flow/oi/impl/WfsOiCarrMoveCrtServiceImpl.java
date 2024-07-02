package com.abs.wfs.workman.service.flow.oi.impl;


import com.abs.wfs.workman.dao.domain.tnPort.model.TnPosPort;
import com.abs.wfs.workman.dao.domain.tnPort.service.TnPosPortServiceImpl;
import com.abs.wfs.workman.dao.domain.transferJob.model.WnTransferJob;
import com.abs.wfs.workman.dao.domain.transferJob.service.WnTransferJobService;
import com.abs.wfs.workman.dao.domain.transferJob.service.WnTransferJobServiceImpl;
import com.abs.wfs.workman.dao.domain.wipStat.model.WnWipStat;
import com.abs.wfs.workman.dao.domain.wipStat.service.WipStatServiceImpl;
import com.abs.wfs.workman.service.flow.oi.WfsOiCarrMoveCrt;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.oia.WfsOiCarrMoveCrtIvo;
import com.abs.wfs.workman.util.code.MoveStatCd;
import com.abs.wfs.workman.util.code.UseStatCd;
import com.abs.wfs.workman.util.exception.ScenarioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WfsOiCarrMoveCrtServiceImpl implements WfsOiCarrMoveCrt {


    @Autowired
    WnTransferJobServiceImpl wnTransferJobService;

    @Autowired
    WipStatServiceImpl wipStatService;


    @Autowired
    TnPosPortServiceImpl tnPosPortService;


    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType) {
        ApFlowProcessVo apFlowProcessVo = ApFlowProcessVo.builder()
                .eventName(cid)
                .trackingKey(trackingKey)
                .scenarioType(scenarioType)
                .executeStartTime(System.currentTimeMillis())
                .build();
        return apFlowProcessVo;
    }

    
    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsOiCarrMoveCrtIvo wfsOiCarrMoveCrtIvo) throws Exception {

        WfsOiCarrMoveCrtIvo.WfsOiCarrMoveCrtBody body = wfsOiCarrMoveCrtIvo.getBody();

        String carrId = body.getCarrId();

        // 진행 중인 Transfer 잡이 있는 지 확인
        Optional<List<WnTransferJob>> transferJobList = this.wnTransferJobService.findByCarrIdAndUseStatCd(body.getSiteId(), carrId, UseStatCd.Usable);
        List<MoveStatCd> deniedMoveStatCds = Arrays.asList(MoveStatCd.Created, MoveStatCd.Queued, MoveStatCd.Started);
        transferJobList.ifPresent(jobs -> {
            jobs.stream()
                    .filter(job -> deniedMoveStatCds.contains(job.getMoveStatCd()))
                    .forEach(job -> {
                        
                        // TODO Exception 생성
                        throw new IllegalStateException("Job with ID: job.getId()  has a move status of " + job.getMoveStatCd());
                    });
        });

        log.info("Transfer job is empty. check location.");



        // 위치 정보가 Validation 

        // Carr 가 다른 설비에 예약 되어 있음

        // EQP와 포트의 네이밍이 안맞음 → Fail

        // CMR의 현재 위치와 WIP의 현재 위치가 안맞음

        // CMR의 현재 위치와 PORT의 현재 위치가 안맞음

        String sourceEqp = body.getSrcEqpId();  String sourcePort = body.getSrcPortId();
        if(!sourcePort.trim().startsWith(sourceEqp.trim())){
            
            // TODO Exception. EQP & Port 네이밍 룰이 다름
            throw new IllegalStateException("Source EQP id is not equal to Source port");
        }


        Optional<WnWipStat> wipStatCarrInfo = this.wipStatService.findByOnlyCarrIdAndSiteIdAndUseStatCd(carrId, body.getSiteId());
        if(!wipStatCarrInfo.isPresent()){ throw new IllegalStateException("Un registered carr.");} // TODO Wip에 Carr 가 없는 경우
        if(!sourceEqp.equals(wipStatCarrInfo.get().getCrntEqpId())) {throw new IllegalStateException("요청 Source 정보와 현재 정보가 다름 (설비)");} // TODO 현재 위치 정보가 다른 경우
        if(!sourcePort.equals(wipStatCarrInfo.get().getCrntPortId())) {throw new IllegalStateException("요청 Source 정보와 현재 정보가 다름 (포트)");} // TODO 현재 위치 정보가 다른 경우

        if(!wipStatCarrInfo.get().getResvGrpId().isEmpty()) { throw new IllegalStateException("wip has reserve group Id.");} // TODO Carr에 리저브 그룹  정보 존재
        if(!wipStatCarrInfo.get().getResvEqpId().isEmpty()) { throw new IllegalStateException("wip has reserve eqp Id.");} // TODO Carr에 리저브 설비  정보 존재
        if(!wipStatCarrInfo.get().getResvPortId().isEmpty()) { throw new IllegalStateException("wip has reserve port Id.");} // TODO Carr에 리저브 포트  정보 존재
        
        Optional<TnPosPort> portInto = this.tnPosPortService.findByPortIdAndSiteIdAndUseStatCd(sourcePort, body.getSiteId());
        if(!portInto.isPresent()){ throw new IllegalStateException("Un registered port.");} // TODO 포트  정보가 없는 경우
        if(portInto.get().getCarrId().isEmpty()) {throw new IllegalStateException("Carr id is not in Port info");} // TODO 포트에 Carr Id 가 없는 경우
        if(!portInto.get().getCarrId().equals(carrId)) {throw  new IllegalStateException("요청과 다른  Carr 가 포트 위에 존재");} // TODO 포트에 다른 Carr 가 존재 하는 경우
        



        
        // 다 맞으면 CMR 발송
        // Util.반송 생성 호출 (Transfer Job 생성 & 메시지 발송)
        
        return null;
    }

}
