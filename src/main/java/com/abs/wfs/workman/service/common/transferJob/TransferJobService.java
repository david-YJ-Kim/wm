package com.abs.wfs.workman.service.common.transferJob;

import com.abs.wfs.workman.dao.query.service.WfsCommonQueryService;
import com.abs.wfs.workman.dao.query.service.WfsQueryService;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.common.transferJob.vo.TransferJobReqVo;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.out.mcs.McsCarrMoveReqIvo;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransferJobService {

    @Autowired
    WfsCommonQueryService wfsCommonQueryService;


    @Autowired
    WfsQueryService wfsQueryService;


    @Autowired
    MessageSendService messageSendService;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;



    public void generateTransferJob(TransferJobReqVo vo, ApFlowProcessVo apFlowProcessVo) throws Exception {

        String transferId = wfsCommonQueryService.getID("TRANS");


        this.wfsQueryService.createTransferJob(vo.getSiteId(), apFlowProcessVo.getEventName(),
                                                    apFlowProcessVo.getTid(), vo.getUserId(), transferId,
                                                    vo.getCarrId(), vo.getSrcEqpId(), vo.getSrcPortId(), vo.getSrcEqpId(), vo.getSrcPortId(),
                                                    vo.getDestEqpId(), vo.getDestPortId(), vo.getPrio());


        McsCarrMoveReqIvo.McsCarrMoveReqBody carrMoveReqBody = new McsCarrMoveReqIvo.McsCarrMoveReqBody();
        carrMoveReqBody.setSiteId(vo.getSiteId());
        carrMoveReqBody.setEventUserId(ApSystemCodeConstant.WFS);
        carrMoveReqBody.setEventComment(vo.getComment());
        carrMoveReqBody.setCarrId(vo.getCarrId());
        carrMoveReqBody.setCommId(transferId);
        carrMoveReqBody.setSrcEqpId(vo.getSrcEqpId());
        carrMoveReqBody.setSrcPortId(vo.getSrcPortId());
        carrMoveReqBody.setDestEqpId(vo.getDestEqpId());
        carrMoveReqBody.setDestPortId(vo.getDestPortId());
        carrMoveReqBody.setPrio(vo.getPrio());


        this.messageSendService.sendMessageSend(McsCarrMoveReqIvo.system, McsCarrMoveReqIvo.cid,
                this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), carrMoveReqBody));



    }
}
