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
import com.abs.wfs.workman.spec.common.ApMsgHead;
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
import java.util.HashMap;
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
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsOiCarrMoveCrtIvo wfsOiCarrMoveCrtIvo) throws Exception {

        String lang = wfsOiCarrMoveCrtIvo.getHead().getLang();
        WfsOiCarrMoveCrtIvo.WfsOiCarrMoveCrtBody body = wfsOiCarrMoveCrtIvo.getBody();
        apFlowProcessVo.setApMsgBody(body);

        log.info(apFlowProcessVo.toString());

        String carrId = body.getCarrId();

        // 진행 중인 Transfer 잡이 있는 지 확인
        Optional<List<WnTransferJob>> transferJobList = this.wnTransferJobService.findByCarrIdAndUseStatCd(body.getSiteId(), carrId, UseStatCd.Usable);
        List<MoveStatCd> deniedMoveStatCds = Arrays.asList(MoveStatCd.Created, MoveStatCd.Queued, MoveStatCd.Started);
        transferJobList.ifPresent(jobs -> {
            jobs.stream()
                    .filter(job -> deniedMoveStatCds.contains(job.getMoveStatCd()))
                    .forEach(job -> {

                        String transJobId = job.getJobId();
                        WorkManCommonUtil.setAdditionalData(apFlowProcessVo, "commId", transJobId);

                        throw new ScenarioException(apFlowProcessVo, body,
                                                    ApExceptionCode.WFS_ERR_TRAN_JOB_STAT_UNMATCHED, lang,
                                                    new String[] {job.getCarrId(), job.getJobId(), job.getMoveStatCd().name()}
                                                    );
                    });
        });

        log.info("Transfer job is empty. check location.");



        String sourceEqp = body.getSrcEqpId();  String sourcePort = body.getSrcPortId();
        if(!sourcePort.trim().startsWith(sourceEqp.trim())){

            throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_TOOL_PORT_INF_INVALID,  lang,null);
        }

        Optional<WnWipStat> wipStatCarrInfo = this.wipStatService.findByOnlyCarrIdAndSiteIdAndUseStatCd(carrId, body.getSiteId());
        if(wipStatCarrInfo.isPresent()){
            WnWipStat wipStat = wipStatCarrInfo.get();

            if(wipStat.getCrntEqpId() != null && !sourceEqp.equals(wipStat.getCrntEqpId())) {
                throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_CARR_SRC_LOC_UNMATCHED, lang, new String[] {carrId, sourceEqp, wipStat.getCrntEqpId()});
            }
            if(wipStat.getCrntPortId() != null && !sourcePort.equals(wipStat.getCrntPortId())) {
                throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_CARR_SRC_LOC_UNMATCHED, lang, new String[] {carrId, sourcePort, wipStat.getCrntPortId()});}

            if(wipStat.getResvGrpId() != null && !wipStat.getResvGrpId().isEmpty()) {
                throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_CARR_RESV_INF_REGISTER, lang, new String[] {carrId, wipStat.getResvGrpId()});
            }

            if(wipStat.getResvEqpId() != null && !wipStat.getResvEqpId().isEmpty()) {
                throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_CARR_RESV_INF_REGISTER, lang, new String[] {carrId, wipStat.getResvEqpId()});}

            if(wipStat.getResvPortId() != null && !wipStat.getResvPortId().isEmpty()) {
                throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_CARR_RESV_INF_REGISTER, lang, new String[] {carrId, wipStat.getResvPortId()});}


        }else{
            throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_CARR_INF_UNREGISTER, lang, new String[] {carrId});
        }

        if(sourcePort.startsWith("ASTK") || sourcePort.startsWith("ABUF")){

                log.info("Carr now at logistic eqp {}", sourcePort);
        }else{

            TnPosPort portInto = this.tnPosPortService.findByPortIdAndSiteIdAndUseStatCd(sourcePort, body.getSiteId());
            if(portInto == null){
                throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_PORT_INF_UNREGISTER, lang, new String[] {sourcePort});
            }
            if(!portInto.getCarrId().equals(carrId)) {
                throw  new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_PORT_CARR_INF_UNMATCHED, lang, new String[] {sourcePort, carrId, portInto.getCarrId()});
            }



        }

        TransferJobReqVo transferJobReqVo = TransferJobReqVo.builder()
                                            .siteId(body.getSiteId())
                                            .userId(body.getUserId())
                                            .carrId(carrId)
                                            .srcEqpId(sourceEqp)
                                            .srcPortId(sourcePort)
                                            .destEqpId(body.getDestEqpId())
                                            .destPortId(body.getDestPortId())
                                            .comment(body.getComment())
                                            .prio(body.getPrio())
                                            .build();


        this.transferJobService.generateTransferJob(transferJobReqVo, apFlowProcessVo);

        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);

    }

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return  WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }
}
