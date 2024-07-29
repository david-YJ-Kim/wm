package com.abs.wfs.workman.service.flow.eap.impl;//package com.abs.wfs.workman.service.flow.eap.impl;


import com.abs.wfs.workman.dao.domain.tnProducedMaterial.model.TnProducedMaterial;
import com.abs.wfs.workman.dao.domain.tnProducedMaterial.service.TnProducedMaterialServiceImpl;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.eap.WfsTrayUnloadReq;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.eap.WfsTrayUnloadReqIvo;
import com.abs.wfs.workman.spec.out.brs.BrsLotCarrAssign;
import com.abs.wfs.workman.spec.out.brs.BrsLotDeassignCarr;
import com.abs.wfs.workman.spec.out.brs.common.Slots;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class WfsTrayUnloadReqServiceImpl implements WfsTrayUnloadReq {

    @Autowired
    TnProducedMaterialServiceImpl tnProducedMaterialService;

    @Autowired
    MessageSendService messageSendService;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;


    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsTrayUnloadReqIvo wfsTrayUnloadReqIvo) throws Exception {

        WfsTrayUnloadReqIvo.Body body = wfsTrayUnloadReqIvo.getBody();
        apFlowProcessVo.setApMsgBody(body);


        TnProducedMaterial materialInf = tnProducedMaterialService.findByProdMtrlId(body.getSiteId(), body.getProdMtrlId());

        if(materialInf == null) {
            throw new Exception("MTRL not found");
        }

        log.debug("{} print prodMtrl query info: {}", apFlowProcessVo.printLog(), materialInf.toString());


        List<Slots> slots = new ArrayList<>();
        slots.add(Slots.builder().slotNo("1").prodMtrlId(body.getProdMtrlId()).build());

        // 1. de-assign. 보고된 tray와 carrId가 다르다면,
        if(!materialInf.getCarrId().equals(body.getCarrId())){
            log.info("{} Panel is not de-assigned. De-assign from {} and trying to assign : {}", apFlowProcessVo.printLog(), materialInf.getCarrId(), body.getCarrId());

            BrsLotDeassignCarr.Body lotDgnVo = new BrsLotDeassignCarr.Body();

            lotDgnVo.setSiteId(body.getSiteId()) ; lotDgnVo.setEqpId(body.getEqpId()); lotDgnVo.setCarrId(materialInf.getCarrId());
            lotDgnVo.setLotId(materialInf.getLotId()); lotDgnVo.setDeasgnQty("1"); lotDgnVo.setSlots(slots);

            this.messageSendService.sendMessageSend(BrsLotDeassignCarr.system, BrsLotDeassignCarr.cid,
                    this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), lotDgnVo));
        }

        // De-Assign 된 ProdMtrl
        if(materialInf.getSlotNo() == null){
            log.info("{} Panel has been de-assigned. Now prodMtrlId: {}, trayId: {}", apFlowProcessVo.printLog(), body.getProdMtrlId(), body.getCarrId());
            // TODO Assign

            BrsLotCarrAssign.Body lotAgnVo = new BrsLotCarrAssign.Body();
            lotAgnVo.setLotId(body.getLotId()); lotAgnVo.setCarrId(body.getCarrId());
            lotAgnVo.setMdfyUserId(ApSystemCodeConstant.WFS);lotAgnVo.setSlots(slots);
            lotAgnVo.setAsgnQty("1") ;


            this.messageSendService.sendMessageSend(BrsLotCarrAssign.system, BrsLotDeassignCarr.cid,
                    this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), lotAgnVo));

        }


        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
    }

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }
}
