package com.abs.wfs.workman.service.flow.oi.impl;


import com.abs.wfs.workman.dao.domain.tnPort.model.TnPosPort;
import com.abs.wfs.workman.dao.domain.tnPort.service.TnPosPortServiceImpl;
import com.abs.wfs.workman.dao.domain.transferJob.model.WnTransferJob;
import com.abs.wfs.workman.dao.domain.transferJob.service.WnTransferJobServiceImpl;
import com.abs.wfs.workman.dao.domain.wipStat.model.WnWipStat;
import com.abs.wfs.workman.dao.domain.wipStat.service.WipStatServiceImpl;
import com.abs.wfs.workman.dao.query.service.WfsCommonQueryService;
import com.abs.wfs.workman.dao.query.service.WfsQueryService;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.oi.WfsOiCarrMoveCrt;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.oia.WfsOiCarrMoveCrtIvo;
import com.abs.wfs.workman.spec.out.mcs.McsCarrMoveReqIvo;
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
    WfsQueryService wfsQueryService;

    @Autowired
    WfsCommonQueryService wfsCommonQueryService;


    @Autowired
    MessageSendService messageSendService;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;



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
                        throw new ScenarioException(apFlowProcessVo, body,
                                                    ApExceptionCode.WFS_ERR_TRAN_JOB_STAT_UNMATCHED,
                                                    new String[] {job.getCarrId(), job.getJobId(), job.getMoveStatCd().name()}
                                                    );
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
            throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_TOOL_PORT_INF_INVALID, null);
        }

        Optional<WnWipStat> wipStatCarrInfo = this.wipStatService.findByOnlyCarrIdAndSiteIdAndUseStatCd(carrId, body.getSiteId());
        if(!wipStatCarrInfo.isPresent()){ throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_CARR_INF_UNREGISTER, new String[] {carrId});}
        if(!sourceEqp.equals(wipStatCarrInfo.get().getCrntEqpId())) {throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_CARR_SRC_LOC_UNMATCHED, new String[] {carrId, sourceEqp, wipStatCarrInfo.get().getCrntEqpId()});}
        if(!sourcePort.equals(wipStatCarrInfo.get().getCrntPortId())) {throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_CARR_SRC_LOC_UNMATCHED, new String[] {carrId, sourcePort, wipStatCarrInfo.get().getCrntPortId()});}

        if(!wipStatCarrInfo.get().getResvGrpId().isEmpty()) { throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_CARR_RESV_INF_REGISTER, new String[] {carrId, wipStatCarrInfo.get().getResvGrpId()});}
        if(!wipStatCarrInfo.get().getResvEqpId().isEmpty()) { throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_CARR_RESV_INF_REGISTER, new String[] {carrId, wipStatCarrInfo.get().getResvEqpId()});}
        if(!wipStatCarrInfo.get().getResvPortId().isEmpty()) { throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_CARR_RESV_INF_REGISTER, new String[] {carrId, wipStatCarrInfo.get().getResvPortId()});}

        Optional<TnPosPort> portInto = this.tnPosPortService.findByPortIdAndSiteIdAndUseStatCd(sourcePort, body.getSiteId());
        if(!portInto.isPresent()){ throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_PORT_INF_UNREGISTER, new String[] {sourcePort}); }
        if(portInto.get().getCarrId().isEmpty()) {throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_PORT_INF_UNREGISTER, new String[] {sourcePort, carrId}); }
        if(!portInto.get().getCarrId().equals(carrId)) {throw  new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_PORT_CARR_INF_UNMATCHED, new String[] {sourcePort, carrId, portInto.get().getCarrId()});  }




        // 다 맞으면 CMR 발송
        // Util.반송 생성 호출 (Transfer Job 생성 & 메시지 발송)

        String transferId = wfsCommonQueryService.getID("TRANS");
        this.wfsQueryService.createTransferJob(wfsOiCarrMoveCrtIvo.getBody().getSiteId(), apFlowProcessVo.getEventName(), apFlowProcessVo.getTid(), body.getUserId(), transferId, carrId, sourceEqp, sourcePort, sourceEqp, sourcePort, body.getDestEqpId(), body.getPortId(), "50");

        McsCarrMoveReqIvo.McsCarrMoveReqBody carrMoveReqBody = new McsCarrMoveReqIvo.McsCarrMoveReqBody();
        carrMoveReqBody.setSiteId(body.getSiteId());
        carrMoveReqBody.setEventUserId(ApSystemCodeConstant.WFS);
        carrMoveReqBody.setCarrId(carrId);
        carrMoveReqBody.setCommId(transferId);
        carrMoveReqBody.setSrcEqpId(sourceEqp);
        carrMoveReqBody.setSrcPortId(sourcePort);
        carrMoveReqBody.setDestEqpId(body.getDestEqpId());
        carrMoveReqBody.setDestPortId(body.getDestPortId());
        carrMoveReqBody.setPrio("50");

        this.messageSendService.sendMessageSend(McsCarrMoveReqIvo.system, McsCarrMoveReqIvo.cid,
                this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), carrMoveReqBody));


        return null;
    }

}
