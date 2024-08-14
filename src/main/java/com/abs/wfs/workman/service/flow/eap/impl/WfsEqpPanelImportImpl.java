package com.abs.wfs.workman.service.flow.eap.impl;

import com.abs.wfs.workman.dao.domain.posProdMtrlMove.model.CnPosProdMtrlMove;
import com.abs.wfs.workman.dao.domain.posProdMtrlMove.service.CnPosProdMtrlMoveServiceImpl;
import com.abs.wfs.workman.dao.domain.tnLot.model.TnPosLot;
import com.abs.wfs.workman.dao.domain.tnLot.service.TnPosLotServiceImpl;
import com.abs.wfs.workman.dao.domain.tnProducedMaterial.model.TnProducedMaterial;
import com.abs.wfs.workman.dao.domain.tnProducedMaterial.service.TnProducedMaterialServiceImpl;
import com.abs.wfs.workman.service.flow.eap.WfsEqpPanelImport;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.eap.WfsEqpPanelExportIvo;
import com.abs.wfs.workman.spec.in.eap.WfsEqpPanelImportIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.UseStatCd;
import com.abs.wfs.workman.util.exception.ApExceptionCode;
import com.abs.wfs.workman.util.exception.ScenarioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class WfsEqpPanelImportImpl implements WfsEqpPanelImport {

    @Autowired
    CnPosProdMtrlMoveServiceImpl cnPosProdMtrlMoveService;

    @Autowired
    TnProducedMaterialServiceImpl tnProducedMaterialService;

    @Autowired
    TnPosLotServiceImpl tnPosLotService;
    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return null;
    }

    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsEqpPanelImportIvo wfsEqpPanelImportIvo) throws Exception {
        WfsEqpPanelImportIvo.Body body = wfsEqpPanelImportIvo.getBody();
        apFlowProcessVo.setApMsgBody(body);

        Long slotNo;
        String procSgmtId = "";
        String rcpId = "";

        log.info("{} Set up message body: {}", apFlowProcessVo.printLog(), wfsEqpPanelImportIvo.toString());

        Optional<TnProducedMaterial> optProdMtrl = tnProducedMaterialService.findByProdMtrlId(body.getSiteId(), body.getProdMtrlId());

        if(optProdMtrl.isPresent()) {
            TnProducedMaterial tnProducedMaterial = optProdMtrl.get();
            Optional<TnPosLot> optLot = tnPosLotService.findBySiteIdAndLotIdAndUseStatCd(body.getSiteId(), tnProducedMaterial.getLotId(), UseStatCd.Usable);

            if(tnProducedMaterial.getSlotNo() != null) {
                slotNo = tnProducedMaterial.getSlotNo();
            } else {
                slotNo = tnProducedMaterial.getPrevSlotNo();
            }

            if(optLot.isPresent()) {
                TnPosLot lot = optLot.get();
                procSgmtId = lot.getProcSgmtId();
                rcpId = lot.getRcpDefId();

            } else {
                log.error("Lot Not Exist : {}", body.getLotId());
                throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_LOT_INF_NOTFOUND, apFlowProcessVo.getLang(), new String[] {body.getLotId()});
            }

        } else {
            log.error("prod Mtrl Not Exist : {}", body.toString());
            throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_LOT_INF_NOTFOUND, apFlowProcessVo.getLang(), new String[] {body.toString()});
        }

        Optional<CnPosProdMtrlMove> optProdMtrlMove = cnPosProdMtrlMoveService.findByProdMtrlId(body.getProdMtrlId());


        TnProducedMaterial tnPosProducedMaterial;
        CnPosProdMtrlMove cnPosProdMtrlMove;
        if(!optProdMtrlMove.isPresent()) {
            cnPosProdMtrlMove = new CnPosProdMtrlMove();
            log.info("CN_POS_PROD_MTRL_MOVE Create");

        } else {
            cnPosProdMtrlMove = optProdMtrlMove.get();
            cnPosProdMtrlMove.setMdfyDt(null);
            cnPosProdMtrlMove.setFnlEvntDt(null);
        }

        cnPosProdMtrlMove.setSiteId(body.getSiteId());
        cnPosProdMtrlMove.setEqpId(body.getEqpId());
        cnPosProdMtrlMove.setPortId(body.getPortId());
        cnPosProdMtrlMove.setLotId(body.getLotId());
        cnPosProdMtrlMove.setProdMtrlId(body.getProdMtrlId());
        cnPosProdMtrlMove.setCarrId(body.getCarrId());
        cnPosProdMtrlMove.setSlotNo(slotNo);
        cnPosProdMtrlMove.setProcSgmtId(procSgmtId);
        cnPosProdMtrlMove.setMtrlWorkFaceCd(body.getMtrlFace());
        cnPosProdMtrlMove.setRcpId(body.getPpId());
        cnPosProdMtrlMove.setSubEqpId(body.getSubEqpId());

        cnPosProdMtrlMove.setCarrOutDt(cnPosProdMtrlMove.getCarrOutDt());
        cnPosProdMtrlMove.setEqpImportDt(cnPosProdMtrlMove.getEqpImportDt());
        cnPosProdMtrlMove.setEqpExportDt(cnPosProdMtrlMove.getEqpExportDt());
        cnPosProdMtrlMove.setCarrInDt(cnPosProdMtrlMove.getCarrInDt());

        cnPosProdMtrlMove.setMdfyUserId(body.getUserId());
        cnPosProdMtrlMove.setCrtUserId(cnPosProdMtrlMove.getCrtUserId());
        cnPosProdMtrlMove.setPrevEvntNm(cnPosProdMtrlMove.getEvntNm());
        cnPosProdMtrlMove.setEvntNm(wfsEqpPanelImportIvo.getHead().getCid());
        cnPosProdMtrlMove.setTid(wfsEqpPanelImportIvo.getHead().getTid());

        cnPosProdMtrlMoveService.saveCnPosProdMtrlMoveRepository(cnPosProdMtrlMove);

        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
    }
}
