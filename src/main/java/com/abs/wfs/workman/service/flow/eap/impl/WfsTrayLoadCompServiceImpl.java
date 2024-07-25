package com.abs.wfs.workman.service.flow.eap.impl;

import com.abs.wfs.workman.dao.domain.tnPort.service.TnPosPortServiceImpl;
import com.abs.wfs.workman.dao.query.model.QueryPortVO;
import com.abs.wfs.workman.dao.query.service.WfsCommonQueryService;
import com.abs.wfs.workman.dao.query.service.WfsQueryService;
import com.abs.wfs.workman.dao.query.service.WorkQueryService;
import com.abs.wfs.workman.dao.query.service.vo.UpdateCurrentEqpPortByCarrIdReqVo;
import com.abs.wfs.workman.dao.query.service.vo.UpdatePortStatAndCarrierReqVo;
import com.abs.wfs.workman.dao.query.wipLotProdMat.service.WipLotProdMatServiceImpl;
import com.abs.wfs.workman.dao.query.wipLotProdMat.vo.WipLotProdMatDto;
import com.abs.wfs.workman.service.common.ApMsgObjectGenerateService;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.UtilCommonService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.common.message.vo.WorkMessageSendReqVo;
import com.abs.wfs.workman.service.common.staterule.StateRuleManager;
import com.abs.wfs.workman.service.common.vo.MeasureOutInfo;
import com.abs.wfs.workman.service.common.vo.MeasureOutPortCarrInfoReqVo;
import com.abs.wfs.workman.service.common.work.WorkManageService;
import com.abs.wfs.workman.service.flow.eap.WfsTrayLoadComp;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.eap.WfsTrayLoadCompIvo;
import com.abs.wfs.workman.spec.in.oia.WfsOiGenerateWorkReqIvo;
import com.abs.wfs.workman.spec.out.brs.BrsLotCarrAssign;
import com.abs.wfs.workman.spec.out.brs.BrsLotDeassignCarr;
import com.abs.wfs.workman.spec.out.brs.common.Slots;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.*;
import com.abs.wfs.workman.util.exception.ApExceptionCode;
import com.abs.wfs.workman.util.exception.ScenarioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WfsTrayLoadCompServiceImpl implements WfsTrayLoadComp {
    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }


    @Autowired
    TnPosPortServiceImpl tnPosPortService;

    @Autowired
    WfsCommonQueryService wfsCommonQueryService;

    @Autowired
    WfsQueryService wfsQueryService;

    @Autowired
    StateRuleManager stateRuleManager;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;

    @Autowired
    MessageSendService messageSendService;

    @Autowired
    WipLotProdMatServiceImpl wipLotProdMatService;

    @Autowired
    UtilCommonService utilCommonService;

    @Autowired
    WorkQueryService workQueryService;

    @Autowired
    WorkManageService workManageService;

    @Autowired
    ApMsgObjectGenerateService apMsgObjectGenerateService;

    /**
     * Tray Load Comp
     * : Tray Port에 carr가 도착 시점에 보고 되는 메시지
     *
     * SVM 라인 內 Tray Port
     *  1. AP-SR-06-TR01 (SR 인라인 포트), 포트명 확인 필요
     *  2. AM-RE-02-BP06 (SR 선진행 패널 투입 포트)
     *  3. AM-RE-00-01-BP (MR룸 Tray 로딩)
     *
     * 시나리오 케이스
     * 1. SR 선진행 케이스 (AP-SR-06-TR01 ↔ AM-RE-02-BP06)
     * 2. MR룸 Tray 케이스 (AM-RE-00-01 수취 ↔ 배출)
     *
     */


    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsTrayLoadCompIvo wfsTrayLoadCompIvo) throws Exception {

        WfsTrayLoadCompIvo.Body body = wfsTrayLoadCompIvo.getBody();
        apFlowProcessVo.setApMsgBody(body);
        log.info("{} Set up message body: {}", apFlowProcessVo.printLog(), wfsTrayLoadCompIvo.toString());

        String siteId = body.getSiteId(); String eqpId = body.getEqpId(); String carrId = body.getCarrId();
        String portId = body.getPortId(); String prodMtrlId = body.getProdMtrlId();

        boolean isEmptyTray = prodMtrlId == null || prodMtrlId.isEmpty();
        log.info("{} tray is loaded. is it empty: {}?", apFlowProcessVo.printLog(), isEmptyTray);


        /*
        Validate port status
         */
        QueryPortVO queryPortVO = this.wfsCommonQueryService.getQueryPortVO(siteId, eqpId, portId);
        if(queryPortVO == null){
            log.error("{} Port query is not found.", apFlowProcessVo.printLog());
            throw  new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_PORT_INF_NOTFOUND, apFlowProcessVo.getLang()
                    , new String[] {eqpId, portId});
        }
        log.info("{} Port query result: {}", apFlowProcessVo.printLog(), queryPortVO.toString());

        // this.stateRuleManager.validatePortStateRule(siteId, eqpId, portId, StateRuleList.ValidPort, queryPortVO);


        /*
        Update port status
         */
        UpdatePortStatAndCarrierReqVo updatePortVo = UpdatePortStatAndCarrierReqVo.builder()
                                            .siteId(siteId).cid(WfsTrayLoadCompIvo.cid).tid(apFlowProcessVo.getTid()).userId(ApSystemCodeConstant.WFS)
                                            .efemCommStateCd("").efemStateCd("").efemToolStateCd("").statCd(PortStatCd.Occupied.name()).trsfStatCd(TrsfStatCd.LoadCompleted.name())
                                            .acesModeCd("").ctrlModeCd("").carrId(carrId).eqpId(eqpId).portId(portId)
                                            .build();
        log.info("{} Port will be update with this data : {}.", apFlowProcessVo.printLog(), updatePortVo.toString());

        int updatePortStatCdCnt = this.wfsCommonQueryService.updatePortStatAndCarrier(updatePortVo);
        log.info("{} Port data has been update {} rows.", apFlowProcessVo.printLog(),updatePortStatCdCnt);



        UpdateCurrentEqpPortByCarrIdReqVo updateCntEqpPortReqVo = UpdateCurrentEqpPortByCarrIdReqVo.builder()
                                            .siteId(siteId).cid(WfsTrayLoadCompIvo.cid).tid(apFlowProcessVo.getTid()).carrId(carrId)
                                            .userId(ApSystemCodeConstant.WFS).crntEqpId(eqpId).crntPortId(portId)
                                            .build();
        log.info("{} Port will be update with this data : {}.", apFlowProcessVo.printLog(), updateCntEqpPortReqVo.toString());

        int updateCurrentEqpPortByCarrIdCnt = this.wfsQueryService.updateCurrentEqpPortByCarrId(updateCntEqpPortReqVo);
        log.info("{} Port data has been update {} rows.", apFlowProcessVo.printLog(),updateCurrentEqpPortByCarrIdCnt);



        if(isEmptyTray){
            log.info("{} Empty tray ({}) is loaded port ({}) with null panel id: {}", apFlowProcessVo.printLog(), carrId, portId, prodMtrlId);

        }else {

            WipLotProdMatDto wipLotProdMatDto = WipLotProdMatDto.builder().siteId(siteId).prodMtrlId(prodMtrlId).useStatCd(UseStatCd.Usable).build();
            log.info("{} Query lot and pro d material info with {}", apFlowProcessVo.printLog(), wipLotProdMatDto.toString());

            WipLotProdMatDto queryLotIdWithCarr = this.wipLotProdMatService.queryPanelLotIdWithCarr(wipLotProdMatDto);

            if(queryLotIdWithCarr == null || queryLotIdWithCarr.getLotId().isEmpty()){
                log.error("{} Lot is not found with carr ({}).", apFlowProcessVo.printLog(), carrId);
                throw  new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_LOT_INF_NOTFOUND, apFlowProcessVo.getLang()
                        , new String[] {carrId});
            }

            String lotId = queryLotIdWithCarr.getLotId();
            apFlowProcessVo.getApMsgBody().setLotId(lotId);




            /*
            실 Tray 도착 시,
            1. Tray 패널 분리 (De-Assign)
            2. 반출 Work 생성 (RE-00-01)
             */


            BrsLotDeassignCarr.Body lotDgnVo = new BrsLotDeassignCarr.Body();
            List<Slots> slots = new ArrayList<>();
            slots.add(Slots.builder().slotNo("1").prodMtrlId(prodMtrlId).build());

            lotDgnVo.setSiteId(body.getSiteId()) ; lotDgnVo.setEqpId(eqpId); lotDgnVo.setPortId(portId); lotDgnVo.setCarrId(carrId);
            lotDgnVo.setLotId(lotId); lotDgnVo.setDeasgnQty("1"); lotDgnVo.setSlots(slots);

            this.messageSendService.sendMessageSend(BrsLotDeassignCarr.system, BrsLotDeassignCarr.cid,
                    this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), lotDgnVo));
            
            
            /*
            RE-00-01, Measurement Room Tray loader 시나리오
                - 배출하는 CST 포트 탐색
                - 패널 이동 Work 생성
             */
            if(eqpId.equals("AM-RE-00-01")){
                log.info("{} Measurement room tray loader. eqpId: {}, portId: {}", apFlowProcessVo.printLog(), eqpId, portId);


                MeasureOutPortCarrInfoReqVo measureReqVo = MeasureOutPortCarrInfoReqVo.builder()
                                                    .siteId(siteId.isEmpty() ? "SVM" : siteId)
                                                    .lotId(lotId)
                                                    .portId(body.getPortId())
                                                    .carrId(body.getCarrId())
                                                    .prodMtrlId(body.getProdMtrlId())
                                                    .build();

                MeasureOutInfo measureOutCstPort = this.utilCommonService.getMeasureOutPortCarrInfo(apFlowProcessVo, measureReqVo);
                log.info("{} Ready to make panel move work. from port: {}, target port: {}, panel Id: {}, target slot Not: {}",
                        apFlowProcessVo.printLog(), portId, measureOutCstPort.getLinkedPortId(), prodMtrlId, measureOutCstPort.getTargetSlotNo());


                String workId = this.wfsCommonQueryService.getID("WORK");

                /*
                Work 생성 발송
                 */
                WfsOiGenerateWorkReqIvo.Body workReqBody = new WfsOiGenerateWorkReqIvo.Body();
                workReqBody.setSiteId(siteId); workReqBody.setLotId(lotId); workReqBody.setPortId(portId); workReqBody.setCarrId(carrId);
                workReqBody.setSlotNo("1"); workReqBody.setProdMtrlId(prodMtrlId); workReqBody.setPanelInputYn(UseYn.N.name());
                workReqBody.setEqpId(eqpId);

                this.workManageService.generateMeasurementRoomWork(apFlowProcessVo,
                        this.apMsgObjectGenerateService.messageObject(apFlowProcessVo.getTid(), workReqBody)
                );

//                this.workQueryService.createWorkMeasureTrayLoader(siteId, WfsTrayLoadCompIvo.cid, apFlowProcessVo.getTid(), ApSystemCodeConstant.WFS,
//                                                    eqpId, workId, "N", "N", "","","",
//                                                    lotId, portId, carrId, portId, carrId, "", "1", measureOutCstPort.getLinkedPortId(),
//                                                    measureOutCstPort.getTargetCarrId(), "BP", "", "" ,"" ,"N",
//                                                    "", "", "TTT", "Top", "N", "N");

                
                

            }
            


        }




        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
    }




}
