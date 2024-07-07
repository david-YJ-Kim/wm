package com.abs.wfs.workman.service.flow.oi.impl;


import com.abs.wfs.workman.dao.domain.tnPort.model.TnPosPort;
import com.abs.wfs.workman.dao.domain.tnPort.service.TnPosPortServiceImpl;
import com.abs.wfs.workman.dao.domain.transferJob.model.WnTransferJob;
import com.abs.wfs.workman.dao.domain.transferJob.service.WnTransferJobServiceImpl;
import com.abs.wfs.workman.dao.domain.wipStat.model.WnWipStat;
import com.abs.wfs.workman.dao.domain.wipStat.service.WipStatServiceImpl;
import com.abs.wfs.workman.service.common.transferJob.TransferJobService;
import com.abs.wfs.workman.service.common.transferJob.vo.TransferJobReqVo;
import com.abs.wfs.workman.service.flow.oi.WfsOiCarrMoveCrt;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.oia.WfsOiCarrMoveCrtIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import com.abs.wfs.workman.util.code.MoveStatCd;
import com.abs.wfs.workman.util.code.UseStatCd;
import com.abs.wfs.workman.util.exception.ApExceptionCode;
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


    @Autowired
    TransferJobService transferJobService;




    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, String tid) {
        ApFlowProcessVo apFlowProcessVo = ApFlowProcessVo.builder()
                .eventName(cid)
                .trackingKey(trackingKey)
                .scenarioType(scenarioType)
                .executeStartTime(System.currentTimeMillis())
                .tid(tid)
                .build();
        return apFlowProcessVo;
    }


    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsOiCarrMoveCrtIvo wfsOiCarrMoveCrtIvo) throws Exception {

        String lang = wfsOiCarrMoveCrtIvo.getHead().getLang();
        WfsOiCarrMoveCrtIvo.WfsOiCarrMoveCrtBody body = wfsOiCarrMoveCrtIvo.getBody();



        String carrId = body.getCarrId();

        // 진행 중인 Transfer 잡이 있는 지 확인
        Optional<List<WnTransferJob>> transferJobList = this.wnTransferJobService.findByCarrIdAndUseStatCd(body.getSiteId(), carrId, UseStatCd.Usable);
        List<MoveStatCd> deniedMoveStatCds = Arrays.asList(MoveStatCd.Created, MoveStatCd.Queued, MoveStatCd.Started);
        transferJobList.ifPresent(jobs -> {
            jobs.stream()
                    .filter(job -> deniedMoveStatCds.contains(job.getMoveStatCd()))
                    .forEach(job -> {

                        throw new ScenarioException(apFlowProcessVo, body,
                                                    ApExceptionCode.WFS_ERR_TRAN_JOB_STAT_UNMATCHED, lang,
                                                    new String[] {job.getCarrId(), job.getJobId(), job.getMoveStatCd().name()}
                                                    );
                    });
        });

        log.info("Transfer job is empty. check location.");



        String sourceEqp = body.getSrcEqpId();  String sourcePort = body.getSrcPortId();
        if(!sourcePort.trim().startsWith(sourceEqp.trim())){

            // TODO Exception. EQP & Port 네이밍 룰이 다름
            throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_TOOL_PORT_INF_INVALID,  lang,null);
        }

        Optional<WnWipStat> wipStatCarrInfo = this.wipStatService.findByOnlyCarrIdAndSiteIdAndUseStatCd(carrId, body.getSiteId());
        if(!wipStatCarrInfo.isPresent()){ throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_CARR_INF_UNREGISTER, lang, new String[] {carrId});}
        if(!sourceEqp.equals(wipStatCarrInfo.get().getCrntEqpId())) {throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_CARR_SRC_LOC_UNMATCHED, lang, new String[] {carrId, sourceEqp, wipStatCarrInfo.get().getCrntEqpId()});}
        if(!sourcePort.equals(wipStatCarrInfo.get().getCrntPortId())) {throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_CARR_SRC_LOC_UNMATCHED, lang, new String[] {carrId, sourcePort, wipStatCarrInfo.get().getCrntPortId()});}

        if(!wipStatCarrInfo.get().getResvGrpId().isEmpty()) { throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_CARR_RESV_INF_REGISTER, lang, new String[] {carrId, wipStatCarrInfo.get().getResvGrpId()});}
        if(!wipStatCarrInfo.get().getResvEqpId().isEmpty()) { throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_CARR_RESV_INF_REGISTER, lang, new String[] {carrId, wipStatCarrInfo.get().getResvEqpId()});}
        if(!wipStatCarrInfo.get().getResvPortId().isEmpty()) { throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_CARR_RESV_INF_REGISTER, lang, new String[] {carrId, wipStatCarrInfo.get().getResvPortId()});}

        Optional<TnPosPort> portInto = this.tnPosPortService.findByPortIdAndSiteIdAndUseStatCd(sourcePort, body.getSiteId());
        if(!portInto.isPresent()){ throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_PORT_INF_UNREGISTER, lang, new String[] {sourcePort}); }
        if(portInto.get().getCarrId().isEmpty()) {throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_PORT_INF_UNREGISTER, lang, new String[] {sourcePort, carrId}); }
        if(!portInto.get().getCarrId().equals(carrId)) {throw  new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_PORT_CARR_INF_UNMATCHED, lang, new String[] {sourcePort, carrId, portInto.get().getCarrId()});  }



        TransferJobReqVo transferJobReqVo = TransferJobReqVo.builder()
                                            .siteId(body.getSiteId())
                                            .userId(ApSystemCodeConstant.WFS)
                                            .carrId(carrId)
                                            .srcEqpId(sourceEqp)
                                            .srcPortId(sourcePort)
                                            .destEqpId(body.getDestEqpId())
                                            .destPortId(body.getDestPortId())
                                            .prio("50")
                                            .build();


        this.transferJobService.generateTransferJob(transferJobReqVo, apFlowProcessVo);

        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);

    }

}
