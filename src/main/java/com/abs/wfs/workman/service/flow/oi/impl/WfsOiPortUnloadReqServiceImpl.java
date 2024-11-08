package com.abs.wfs.workman.service.flow.oi.impl;

import com.abs.wfs.workman.dao.domain.tnLot.model.TnPosLot;
import com.abs.wfs.workman.dao.domain.tnLot.service.TnPosLotServiceImpl;
import com.abs.wfs.workman.dao.query.dao.CommonDAO;
import com.abs.wfs.workman.dao.query.lot.service.LotQueryServiceImpl;
import com.abs.wfs.workman.dao.query.service.WfsCommonQueryService;
import com.abs.wfs.workman.dao.query.service.WfsQueryService;
import com.abs.wfs.workman.dao.query.service.vo.UpdatePortAutoUnloadYnReqVo;
import com.abs.wfs.workman.dao.query.service.vo.UpdateWipStatEventNmByCarrIdVo;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.oi.WfsOiPortUnloadReq;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.eap.WfsUnloadReqIvo;
import com.abs.wfs.workman.spec.in.oia.WfsOiPortUnloadReqIvo;
import com.abs.wfs.workman.spec.out.eap.EapCarrCancelReq;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import com.abs.wfs.workman.util.code.UseStatCd;
import com.abs.wfs.workman.util.code.UseYn;
import com.abs.wfs.workman.util.code.WorkStatCd;
import com.abs.wfs.workman.util.exception.ApExceptionCode;
import com.abs.wfs.workman.util.exception.ScenarioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class WfsOiPortUnloadReqServiceImpl implements WfsOiPortUnloadReq {
    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return  WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }

    @Autowired
    WfsCommonQueryService wfsCommonQueryService;

    @Autowired
    MessageSendService messageSendService;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;

    @Autowired
    WfsQueryService wfsQueryService;

    @Autowired
    TnPosLotServiceImpl tnPosLotService;

    @Autowired
    CommonDAO commonDAO;


    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsOiPortUnloadReqIvo wfsOiPortUnloadReqIvo) throws Exception {

        WfsOiPortUnloadReqIvo.Body body = wfsOiPortUnloadReqIvo.getBody();
        apFlowProcessVo.setApMsgBody(body);

        String siteId = body.getSiteId(); String portId = body.getPortId(); String eqpId = body.getEqpId();
        String userId = body.getUserId();
        String carrId = body.getCarrId();


        UpdatePortAutoUnloadYnReqVo updateVo = UpdatePortAutoUnloadYnReqVo.builder()
                .siteId(siteId)
                .cid(apFlowProcessVo.getEventName())
                .tid(apFlowProcessVo.getTid())
                .userId(body.getUserId())
                .autoUnloadYn(UseYn.Y.name())
                .eqpId(eqpId)
                .portId(portId)
                .build();
        // TN POS 테이블 N으로 변경
        this.wfsCommonQueryService.updatePortAutoUnloadYn(updateVo);
        log.info("{} update port unload able.", apFlowProcessVo.printLog());

        // WIp Status Standby로 변경
        this.wfsQueryService.updateWorkStatusByCarrId(siteId, apFlowProcessVo.getEventName(), apFlowProcessVo.getTid(),
                                                body.getCarrId(), ApSystemCodeConstant.WFS, WorkStatCd.Standby.name(), false);

        log.info("{} update wip stat to standby.", apFlowProcessVo.printLog());

        if(eqpId.equals("AM-RE-00-01")){
            log.info("OI_PORT_UNLOAD_REQ >> AM-RE-00-01");

            Optional<TnPosLot> tnPosLot = tnPosLotService.findBySiteIdAndCarrIdAndUseStatCd(body.getSiteId(), body.getCarrId(), UseStatCd.Usable);
            if(!tnPosLot.isPresent() || tnPosLot.get().getLotId() == null){
                throw  new ScenarioException(apFlowProcessVo, apFlowProcessVo.getApMsgBody(), ApExceptionCode.WFS_ERR_LOT_INF_NOTFOUND, apFlowProcessVo.getLang()
                        , new String[] {"carrId : " + body.getCarrId()});

            }

            List<Map<String,String>> lotQtyInfo = commonDAO.selectLotQtyInfo(body.getSiteId(), body.getCarrId());

            if(lotQtyInfo != null) {
                int qty = Integer.parseInt(lotQtyInfo.get(0).get("QTY"));
                int cnt = Integer.parseInt(lotQtyInfo.get(0).get("CNT"));

                if(qty != cnt) {
                    log.info("{} carr QTY is not Valid Lot Qty[{}], CARR Exist Qty[{}]", body.getCarrId(), qty, cnt);
                    throw  new ScenarioException(apFlowProcessVo, apFlowProcessVo.getApMsgBody(), ApExceptionCode.WFS_ERR_CST_PANEL_QTY_UNMATCHED, apFlowProcessVo.getLang()
                            , new String[] {"carrId : " + body.getCarrId()});
                }
            }


            //EAP로 CARR_CANCEL_REQ 발송
            EapCarrCancelReq.Body carrCancelReqIvo = new EapCarrCancelReq.Body();

            carrCancelReqIvo.setSiteId(siteId);
            carrCancelReqIvo.setEqpId(eqpId);
            carrCancelReqIvo.setPortId(portId);
            carrCancelReqIvo.setPortType(WorkManCommonUtil.extractPortTypWithPortId(portId));
            carrCancelReqIvo.setCarrId(body.getCarrId());
            carrCancelReqIvo.setUserId(body.getUserId());

            this.messageSendService.sendMessageSend(EapCarrCancelReq.system, EapCarrCancelReq.cid,
                    this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), carrCancelReqIvo));

        } else {
            // WFS로 UNLOAD REQ 발송
            WfsUnloadReqIvo.Body unloadReqIvo = new WfsUnloadReqIvo.Body();
            unloadReqIvo.setSiteId(siteId); unloadReqIvo.setEqpId(eqpId); unloadReqIvo.setPortId(portId);
            unloadReqIvo.setUserId(body.getUserId()); unloadReqIvo.setCarrId(body.getCarrId());
            unloadReqIvo.setPortType(WorkManCommonUtil.extractPortTypWithPortId(portId));

            this.messageSendService.sendMessageSend(WfsUnloadReqIvo.system, WfsUnloadReqIvo.cid,
                    this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), unloadReqIvo));

            //WN_WIP_STAT Event Update

            this.wfsQueryService.updateWipStatEventNmByCarrId(UpdateWipStatEventNmByCarrIdVo.builder()
                    .siteId(siteId)
                    .cid(apFlowProcessVo.getEventName())
                    .tid(apFlowProcessVo.getTid())
                    .carrId(carrId)
                    .mdfyUserId(userId)
                    .build());
        }








        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
    }
}
