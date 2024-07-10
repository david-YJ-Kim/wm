package com.abs.wfs.workman.service.flow.oi.impl;


import com.abs.wfs.workman.dao.domain.transferJob.model.WnTransferJob;
import com.abs.wfs.workman.dao.domain.transferJob.service.WnTransferJobServiceImpl;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.oi.WfsOiCarrDestChgReq;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.oia.WfsOiCarrDestChgReqIvo;
import com.abs.wfs.workman.spec.out.mcs.McsCarrDestChgReq;
import com.abs.wfs.workman.util.WorkManCommonUtil;
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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class WfsOiCarrDestChgReqServiceImpl implements WfsOiCarrDestChgReq {



    @Autowired
    MessageSendService messageSendService;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;

    @Autowired
    WnTransferJobServiceImpl wnTransferJobService;


    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsOiCarrDestChgReqIvo wfsOiCarrDestChgReqIvo) throws Exception {

        String lang = wfsOiCarrDestChgReqIvo.getHead().getLang();
        WfsOiCarrDestChgReqIvo.Body body = wfsOiCarrDestChgReqIvo.getBody();
        apFlowProcessVo.setApMsgBody(body);

        String siteId = body.getSiteId(); String eqpId = body.getDestEqpId(); String portId = body.getDestPortId(); String carrId = body.getCarrId();
        AtomicReference<String> commId = new AtomicReference<>(body.getCommId());

        if(commId.get() == null || commId.get().isEmpty()){

            // 진행 중인 Transfer 잡이 있는 지 확인
            AtomicBoolean isRunningTransferJobExist = new AtomicBoolean(false);
            Optional<List<WnTransferJob>> transferJobList = this.wnTransferJobService.findByCarrIdAndUseStatCd(body.getSiteId(), carrId, UseStatCd.Usable);
            List<MoveStatCd> deniedMoveStatCds = Arrays.asList(MoveStatCd.Created, MoveStatCd.Queued, MoveStatCd.Started);
            transferJobList.ifPresent(jobs -> {
                jobs.stream()
                        .filter(job -> deniedMoveStatCds.contains(job.getMoveStatCd()))
                        .forEach(job -> {
                            isRunningTransferJobExist.set(true);
                            commId.set(job.getJobId());
                        });
            });


            if(isRunningTransferJobExist.get()) {
                throw new ScenarioException(apFlowProcessVo, body,
                        ApExceptionCode.WFS_ERR_TRAN_JOB_UNREGISTER, lang,
                        new String[] {carrId}
                );
            }

        }

        McsCarrDestChgReq.Body destChgReqBody = new McsCarrDestChgReq.Body();
        destChgReqBody.setSiteId(siteId); destChgReqBody.setCarrId(carrId); destChgReqBody.setDestEqpId(eqpId); destChgReqBody.setDestEqpId(portId);
        destChgReqBody.setEventUserId(body.getUserId()); destChgReqBody.setEventComment(body.getComment());
        destChgReqBody.setCommId(commId.get());


        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
    }

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, String tid) {

        return ApFlowProcessVo.builder()
                .eventName(cid)
                .trackingKey(trackingKey)
                .scenarioType(scenarioType)
                .executeStartTime(System.currentTimeMillis())
                .tid(tid)
                .build();
    }
}
