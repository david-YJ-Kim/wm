package com.abs.wfs.workman.service.flow.brs.impl;

import com.abs.wfs.workman.dao.domain.pmsProcNode.model.TnPmsProcNode;
import com.abs.wfs.workman.dao.domain.pmsProcNode.service.TnPmsProcNodeServiceImpl;
import com.abs.wfs.workman.dao.domain.ppsProdDef.model.TnPpsProdDef;
import com.abs.wfs.workman.dao.domain.ppsProdDef.service.TnPpsProdDefServiceImpl;
import com.abs.wfs.workman.dao.domain.tnLot.model.TnPosLot;
import com.abs.wfs.workman.dao.domain.tnLot.service.TnPosLotServiceImpl;
import com.abs.wfs.workman.dao.domain.tnProducedMaterial.model.TnProducedMaterial;
import com.abs.wfs.workman.dao.domain.tnProducedMaterial.service.TnProducedMaterialServiceImpl;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.brs.WfsManualWorkStart;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.brs.WfsManualWorkStartIvo;
import com.abs.wfs.workman.spec.out.eap.EapLotInfoRepIvo;
import com.abs.wfs.workman.spec.out.eap.common.ProdMtrlVo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.ShipType;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class WfsManualWorkStartImpl implements WfsManualWorkStart {

    @Autowired
    TnPosLotServiceImpl tnPosLotService;

    @Autowired
    TnProducedMaterialServiceImpl tnProducedMaterialService;


    @Autowired
    TnPpsProdDefServiceImpl tnPpsProdDefService;

    @Autowired
    TnPmsProcNodeServiceImpl tnPmsProcNodeService;


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
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsManualWorkStartIvo wfsManualWorkStartIvo) throws Exception {


        WfsManualWorkStartIvo.WfsManualWorkStartBody body = wfsManualWorkStartIvo.getBody();
        String siteId = body.getSiteId();
        String lotId = body.getLotId();


        Optional<TnPosLot> posLotInfo = this.tnPosLotService.findBySiteIdAndLotIdAndUseStatCd(siteId, lotId, UseStatCd.Usable);

        if(!posLotInfo.isPresent()) {

            // TODO LOT ERROR EXCEPTION
            log.error(String.valueOf(posLotInfo.isPresent()));
            throw new Exception("");
        }


        Optional<List<TnProducedMaterial>> slotInfoList = this.tnProducedMaterialService.findBySiteIdAndLotIdAndUseStatCdOrderBySlotNo(siteId, lotId, UseStatCd.Usable);
        if(!slotInfoList.isPresent()) {

            // TODO LOT ERROR EXCEPTION
            log.error(String.valueOf(slotInfoList.isPresent()));
            throw new Exception("");
        }



        Optional<TnPpsProdDef> productTypeInfo = this.tnPpsProdDefService.findBySiteIdAndUseStatCdAndProdDefIdAndShipUnitTypIsNotNull(siteId, UseStatCd.Usable, body.getProdDefId());
        if(!productTypeInfo.isPresent()) {

            // TODO LOT ERROR EXCEPTION
            log.error(String.valueOf(posLotInfo.isPresent()));
            throw new Exception("Lot Info is null");
        }

        Optional<TnPmsProcNode> mtrlFaceCdInfo = this.tnPmsProcNodeService.findBySiteIdAndUseStatCdAndProcNodeId(siteId, UseStatCd.Usable, posLotInfo.get().getProcNodeId());

        EapLotInfoRepIvo.EapLotInfoRepBody eapLotInfoRepBody = new EapLotInfoRepIvo.EapLotInfoRepBody();

        eapLotInfoRepBody.setSiteId(siteId);
        eapLotInfoRepBody.setLotId(lotId);
        eapLotInfoRepBody.setEqpId(body.getEqpId());
        eapLotInfoRepBody.setPrntLotId(posLotInfo.get().getPrntLotId());
        eapLotInfoRepBody.setProdDefId(body.getProdDefId());
        eapLotInfoRepBody.setProcDefId(body.getProcDefId());
        eapLotInfoRepBody.setProcSgmtId(body.getProcSgmtId());
        eapLotInfoRepBody.setPrevEqpId(posLotInfo.get().getPrevEqpId());

        mtrlFaceCdInfo.ifPresent(tnPmsProcNode -> eapLotInfoRepBody.setMtrlFaceCd(tnPmsProcNode.getMtrlFaceCd()));

        List<ProdMtrlVo> prodMtrlList = new ArrayList<>();
        for(TnProducedMaterial material : slotInfoList.get()){

            prodMtrlList.add(ProdMtrlVo.builder()
                            .slotNo(String.valueOf(material.getSlotNo()))
                            .prodMtrlId(material.getProdMtrlId())
                            .subMtrlGrdCd(material.getSubMtrlGrdCd())
                            .build()
            );
        }
        eapLotInfoRepBody.setProdMtrlList(prodMtrlList);
        eapLotInfoRepBody.setShipType(productTypeInfo.get().getShipUnitTyp());
        eapLotInfoRepBody.setDimensionX(String.valueOf(productTypeInfo.get().getStripXSpecVal()));
        eapLotInfoRepBody.setDimensionY(String.valueOf(productTypeInfo.get().getStripYSpecVal()));


        // TODO Default 값 하드코딩...
        eapLotInfoRepBody.setRowCountLimitInspection("10000");
        eapLotInfoRepBody.setRowCountLimitInspection("10000");


        this.messageSendService.sendMessageSend(EapLotInfoRepIvo.system, EapLotInfoRepIvo.cid,
                this.apPayloadGenerateService.generateBody(wfsManualWorkStartIvo.getHead().getTid(), eapLotInfoRepBody));

        WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
        return apFlowProcessVo;
    }

}
