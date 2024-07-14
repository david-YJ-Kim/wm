package com.abs.wfs.workman.service.flow.eap.impl;

import com.abs.wfs.workman.dao.domain.wipStat.service.WipStatServiceImpl;
import com.abs.wfs.workman.dao.domain.wipStat.vo.UpdateDspWorkVo;
import com.abs.wfs.workman.dao.domain.wipStat.vo.UpdateWipStatForMoveCompleteByCarrIdReqVo;
import com.abs.wfs.workman.dao.query.carr.service.CarrLocationServiceImpl;
import com.abs.wfs.workman.dao.query.carr.vo.CarrLocationDto;
import com.abs.wfs.workman.dao.query.common.service.WfsCommonServiceImpl;
import com.abs.wfs.workman.dao.query.eqp.service.EqpServiceImpl;
import com.abs.wfs.workman.dao.query.eqp.vo.UpdatePortCarrierDto;
import com.abs.wfs.workman.dao.query.lot.service.LotQueryServiceImpl;
import com.abs.wfs.workman.dao.query.lot.vo.QueryLotVo;
import com.abs.wfs.workman.dao.query.service.WfsCommonQueryService;
import com.abs.wfs.workman.dao.query.service.vo.UpdatePortCarrierRequestVo;
import com.abs.wfs.workman.dao.query.tool.service.ToolQueryServiceImpl;
import com.abs.wfs.workman.dao.query.tool.vo.QueryPortVo;
import com.abs.wfs.workman.dao.query.transfer.service.TransferJobServiceImpl;
import com.abs.wfs.workman.dao.query.transfer.vo.WnTransferJob;
import com.abs.wfs.workman.dao.query.wip.vo.WnWipStat;
import com.abs.wfs.workman.dao.query.wipLot.service.WipLotQueryServiceImpl;
import com.abs.wfs.workman.dao.query.wipLot.vo.SelectCarrLocQueryReqVo;
import com.abs.wfs.workman.service.flow.eap.WfsCarrIdRead;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsCarrIdReadIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.*;
import com.abs.wfs.workman.util.exception.ApExceptionCode;
import com.abs.wfs.workman.util.exception.ScenarioException;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WfsCarrIdReadImpl implements WfsCarrIdRead {



    /**
     * State Rule
     * ============== EQP
     * ValidEqp: Y
     * FullAutoEqp: N
     * AutoModeEqp: N
     * ============== PORT
     * ValidPort: N
     * FullAutoPort: N
     * IsLoadedPort: Y
     * AutoPort: N
     * ============= LOT
     * validLot
     */

    /**
     * CID를 설정, Message Object를 설정
     * Scenario Type을 넘겨 받아야 함, 인터페이스에서 공통으로 eqp를 보고 시나리오를 구분함
     * @param cid
     * @return
     */
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


    @Autowired
    private CarrLocationServiceImpl carrLocationService;

    @Autowired
    private ToolQueryServiceImpl toolQueryService;

    @Autowired
    private LotQueryServiceImpl lotQueryService;

    @Autowired
    private EqpServiceImpl eqpService;

    @Autowired
    private TransferJobServiceImpl transferJobService;

    @Autowired
    private WipStatServiceImpl wipStatService;

    @Autowired
    private MessageSendService messageSendService;

    @Autowired
    private WfsCommonServiceImpl wfsCommonService;

    @Autowired
    WfsCommonQueryService wfsCommonQueryService;

    @Autowired
    WipLotQueryServiceImpl wipLotQueryService;

    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsCarrIdReadIvo wfsCarrIdReadIvo) throws Exception {

        WfsCarrIdReadIvo.WfsCarrIdReadBody body = wfsCarrIdReadIvo.getBody();
        apFlowProcessVo.setApMsgBody(body);
        String siteId = body.getSiteId(); String carrId = body.getCarrId(); String eqpId = body.getEqpId(); String portId = body.getPortId();

        throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_SAMPLE,  null,null);


//        this.wfsCommonQueryService.updatePortCarrier(UpdatePortCarrierRequestVo.builder()
//                                                                    .siteId(siteId)
//                                                                    .carrierId(carrId)
//                                                                    .portId(portId)
//                                                                    .eqpId(eqpId)
//                                                                    .tid(apFlowProcessVo.getTid())
//                                                                    .userId(body.getUserId())
//                                                                    .build()
//        );
//
//        // carrLocQuery
//        Optional<SelectCarrLocQueryReqVo> carrLocQuery =  this.wipLotQueryService.selectCarrLocQuery(SelectCarrLocQueryReqVo.builder()
//                                                                                .siteId(siteId)
//                                                                                .carrId(carrId)
//                                                                                .lotId("-")
//                                                                                .useStatCd(UseStatCd.Usable)
//                                                                                .build());


        // GetPortResvCarr
//
//        Optional<com.abs.wfs.workman.dao.domain.wipStat.model.WnWipStat> getPortResvCarr = this.wipStatService.findResvEqpIdAndResvPortIdAndLotId(siteId, eqpId, portId, "-");
//
//        if(!carrLocQuery.isPresent()) {
//            throw new ScenarioException(); // TODO CARR 조회 결과 없읍
//        }
//
//        if(carrLocQuery.get().getResvGrpId().isEmpty()){
//            // TODO CARR ID READ 질의 후 정리
//        }
//
//
//
//        QueryPortVo queryPortVo = (apFlowProcessVo.getApDefaultQueryVo().getQueryPortVo() != null)
//                ? apFlowProcessVo.getApDefaultQueryVo().getQueryPortVo()
//                : this.toolQueryService.queryPortCondition(
//                QueryPortVo.builder().portId(body.getPortId()).build()
//        );
//
//
//        if(!queryPortVo.getCarrTyp().equals(ApEnumConstant.CA.name())){
//            // TODO Warning it is not CST type.
//            // Do not stop the processing above reason.
//        }
//
//        int updatedRowCount = this.eqpService.updatePortCarrier(
//
//                UpdatePortCarrierDto.builder()
//                        .siteId(body.getSiteId())
//                        .cid(apFlowProcessVo.getEventName())
//                        .tid(wfsCarrIdReadIvo.getHead().getTid())
//                        .userId(ApSystemCodeConstant.WFS)
//                        .carrierId(body.getCarrId())
//                        .eqpId(body.getEqpId())
//                        .portId(body.getPortId())
//                        .build()
//        );
//        log.info("{} rows has been updated", updatedRowCount);
//
//        return this.dispatchScenario(apFlowProcessVo, body, queryPortVo);

    }


    private ApFlowProcessVo dispatchScenario(ApFlowProcessVo apFlowProcessVo, WfsCarrIdReadIvo.WfsCarrIdReadBody body, QueryPortVo queryPortVo)
            throws Exception {

        CarrLocationDto carrLocationDto = this.carrLocationService.carrLocationQuery(
                CarrLocationDto.builder()
                        .pCarrId(body.getCarrId())
                        .pLotId("-")
                        .build()
        );




        switch (apFlowProcessVo.getScenarioType()){
            case WorkManScenarioList.INOUT_SINGLE:
                this.dispatchInOutSingle(apFlowProcessVo, body, carrLocationDto, queryPortVo);
                log.info("Complete execute Scenario, {}.", WorkManScenarioList.INOUT_SINGLE);
                break;

            default: // BOTH PORT is default Scenario.\

                this.dispatchBpSingle(apFlowProcessVo, body, carrLocationDto);
                log.info("Complete execute Scenario.{}.", WorkManScenarioList.BP_SINGLE);
                // to query lot info


                break;
        }


        return apFlowProcessVo;

    }

    private void dispatchInOutSingle(ApFlowProcessVo apFlowProcessVo, WfsCarrIdReadIvo.WfsCarrIdReadBody body,
                                     CarrLocationDto CarrLocationDto, QueryPortVo queryPortVo) throws Exception {

        switch (queryPortVo.getPortTyp()){
            case ApStringConstant.IP:
            case ApStringConstant.IN:
                this.dispatchInSingle(apFlowProcessVo, body, CarrLocationDto, queryPortVo);

                break;


            case ApStringConstant.OP:
            case ApStringConstant.OUT:
                this.dispatchOutSingle(apFlowProcessVo, body, CarrLocationDto, queryPortVo);
                break;


            default:

                throw new ScenarioException("messageKey", "scenarioType","eventName","siteId",
                        "workStateCode", "workId", "lotId", "carrId", "eqpId", "portId",
                        ExceptionCode.UnmatchedPortTyp, "errorComment");

        }

    }


    private void dispatchInSingle(ApFlowProcessVo apFlowProcessVo, WfsCarrIdReadIvo.WfsCarrIdReadBody body,
                                  CarrLocationDto CarrLocationDto, QueryPortVo queryPortVo){


        // Check Location
        if(!this.validateCarrLocation(CarrLocationDto.getCrntEqpId(), body.getEqpId(), CarrLocationDto.getCrntPortId(), body.getPortId())){
            log.warn("Location is not matched");

            this.matchWithTransferData(apFlowProcessVo, body);

        }


        // Check ECO
        if(!CarrLocationDto.getEcoId().isEmpty()){

            // Check Lot validation.
            if(!CarrLocationDto.getHoldYn().equals(ApStringConstant.N) && CarrLocationDto.getSgmtStatCd().equals(ApStringConstant.Wait)){

                // TODO StateRule - ValidLot
                log.error("Lot status is not valid.");
                throw new ScenarioException("messageKey", "scenarioType","eventName","siteId",
                        "workStateCode", "workId", "lotId", "carrId", "eqpId", "portId",
                        ExceptionCode.ValidLot, "errorComment");

            }
        }

    }

    private void dispatchOutSingle(ApFlowProcessVo apFlowProcessVo, WfsCarrIdReadIvo.WfsCarrIdReadBody body,
                                   CarrLocationDto CarrLocationDto, QueryPortVo queryPortVo){

    }


    private void dispatchBpSingle(ApFlowProcessVo apFlowProcessVo, WfsCarrIdReadIvo.WfsCarrIdReadBody body,
                                  CarrLocationDto CarrLocationDto) throws Exception {


        log.info("Validate carr location info.");
        if(!this.validateCarrLocation(CarrLocationDto.getCrntEqpId(), body.getEqpId(), CarrLocationDto.getCrntPortId(), body.getPortId())){

            log.info("Carr location is not matched.");
            // query transfer job, search transfer job within  1 hour.

            this.matchWithTransferData(apFlowProcessVo, body);

            // complete validate carr location.
        }else{

            log.info("Location validate complete.");

            // 공카가 아닌 경우 - 실카 - Lot 확인
            if(!CarrLocationDto.getLotId().isEmpty()){

                log.info("Check lot status.");
                // Lot 체크
                QueryLotVo lotVo = this.lotQueryService.getQueryLot(body.getSiteId(), CarrLocationDto.getLotId());

                //TODO StateRule - ValidateLot with ValidLot





                // Lot이 있지만, 리저브 정보 존재
                ////    리저브 설비와 포트가 요청과 다르다면?
                ////    리저비 그룹 아이디가 없다면?
                if(existReserveInfo(lotVo)){

                    if(!this.validateCarrLocation(lotVo.getResvEqpId(), body.getEqpId(), lotVo.getResvPortId(), body.getPortId())){
                        log.error("Other lot and carr has been reserved with current Eqp and Port. Fail to update current location");
                        throw new ScenarioException("messageKey", "scenarioType","eventName","siteId",
                                "workStateCode", "workId", "lotId", "carrId", "eqpId", "portId",
                                ExceptionCode.AlreadyReservedLot, "errorComment");

                    }


                    // Lot이 있지만, 리저브 정보 미 존재
                    ////    다른 설비로 예약된 라잇이라면?
                }else {
                    // Query with reserve eqp and port without reserve group id.
                    List<WnWipStat> wnWipStatList = this.wipStatService.selectWnWipStatWithReserveEqpPort(WnWipStat.builder()
                            .pSiteId(body.getSiteId())
                            .pReserveEqpId(body.getEqpId())
                            .pReservePortId(body.getPortId())
                            .pUseStatCd(UseStatCd.Usable.name())
                            .build());

                    if(!wnWipStatList.isEmpty()){
                        log.error("Other lot and carr has been reserved with current Eqp and Port. Fail to update current location");
                        throw new ScenarioException("messageKey", "scenarioType","eventName","siteId",
                                "workStateCode", "workId", "lotId", "carrId", "eqpId", "portId",
                                ExceptionCode.AlreadyReservedLot, "errorComment");

                    }else{
                        log.info("No other lot is reserved with eqp and port. update reserve data.");
                        String dspGroupId = this.wfsCommonService.getID(ApStringConstant.DSP);

                        int wipUpdateRowCnt = this.wipStatService.updateDspWorkRepNormal(UpdateDspWorkVo.builder()
                                .siteId(body.getSiteId())
                                .tid(apFlowProcessVo.getTrackingKey())
                                .cid(apFlowProcessVo.getEventName())
                                .carrId(body.getCarrId())
                                .lotId(CarrLocationDto.getLotId())
                                .userId(ApSystemCodeConstant.WFS)
                                .resvEqpId(body.getEqpId())
                                .resvPortId(body.getPortId())
                                .resvGrpId(dspGroupId)
                                .resvOutCarrId(body.getCarrId())
                                .resvOutPortId(body.getPortId())
                                .build());
                        log.info("WipStat dsp info has been updated. row count: {}", wipUpdateRowCnt);

                    }

                }

            }

        }

        // TODO Generate payload & message send.
        this.messageSendService.sendMessageSend("","",""); // CarrIdReadRep
        // to query lot info


        // Check WorkStat Whether is Ready or not.
        if(!CarrLocationDto.getWorkStatCd().equals(ApStringConstant.Ready)){
            // Update WipStat and Move Complete.
            int updatedCnt = this.wipStatService.updateWipStatForMoveCompleteByCarrId(UpdateWipStatForMoveCompleteByCarrIdReqVo.builder()
                    .siteId(body.getSiteId())
                    .tid(apFlowProcessVo.getTrackingKey())
                    .cid(apFlowProcessVo.getEventName())
                    .carrId(body.getCarrId())
                    .userId(body.getUserId())
                    .crntEqpId(body.getEqpId())
                    .crntPortId(body.getPortId())
                    .workStatCd(ApStringConstant.Ready)
                    .build());
            log.info("Update wip stat for move complete by carr id. row count: {}", updatedCnt);

            int wipUpdatedCnt = this.wipStatService.updateEventNmByCarrId(body.getSiteId(), apFlowProcessVo.getEventName(), apFlowProcessVo.getTrackingKey(),
                    body.getCarrId(), ApSystemCodeConstant.WFS);

            log.info("update Event name by carr id. row count: {}", wipUpdatedCnt);
        }

    }



    /**
     * Validate Reserve information is validate.
     * current validate level = null check
     * @param lotVo
     * @return
     */
    private boolean existReserveInfo(QueryLotVo lotVo){
        if(lotVo.getResvEqpId().isEmpty()) return false;
        if(lotVo.getResvPortId().isEmpty()) return false;
        if(lotVo.getResvGrpId().isEmpty()) return false;

        return true;
    }

    // TODO String 비교하는 Utill 함수 개발 필요
    private boolean validateCarrLocation(String crntEqpId, String eqpId, String crntPortId, String portId) {

        if(!crntEqpId.trim().equalsIgnoreCase(eqpId.trim())) {
            return false;
        }


        if(!crntPortId.trim().equalsIgnoreCase(portId.trim())) {
            return false;
        }


        return true;
    }



    private List<WnTransferJob> doSelectTransferJobWithinTime(WfsCarrIdReadIvo.WfsCarrIdReadBody body){
        return this.transferJobService.selectTransferJobWithinTime(WnTransferJob.builder()
                .pSiteId(body.getSiteId())
                .pCarrId(body.getCarrId())
                .useStatCd(UseStatCd.Unusable.name())
                .build());
    }


    /**
     *
     * @param apFlowProcessVo
     * @param body
     */
    private void updateEqpPortWithTransferInfo(ApFlowProcessVo apFlowProcessVo, WfsCarrIdReadIvo.WfsCarrIdReadBody body){

        try{
            int updateRows = this.wipStatService.updateCurrentEqpPortByCarrId(
                    body.getSiteId(),
                    apFlowProcessVo.getEventName(),
                    apFlowProcessVo.getTrackingKey(),
                    body.getCarrId(),
                    body.getUserId(),
                    body.getEqpId(),
                    body.getPortId()
            );
            log.info("Update Current Eqp Port By carr id. row count: {}", updateRows);


        }catch (Exception e){
            log.error("Exception: {}", e);
            throw new ScenarioException("messageKey", "scenarioType","eventName","siteId",
                    "workStateCode", "workId", "lotId", "carrId", "eqpId", "portId",
                    ExceptionCode.UnmatchedCarrLoc, "errorComment");
        }
    }

    /**
     * Query Transfer data and validate it.
     * @param apFlowProcessVo
     * @param body
     */
    private void matchWithTransferData(ApFlowProcessVo apFlowProcessVo, WfsCarrIdReadIvo.WfsCarrIdReadBody body){

        List<WnTransferJob> wnTransferJobList = this.doSelectTransferJobWithinTime(body);
        if(wnTransferJobList.isEmpty()){

            // TODO 변경 필요한 부분, Transfer Job이 없어도 진행  가능하게해야...
            log.error("Carrier is arrived with no transfer job.");
            throw new ScenarioException("messageKey", "scenarioType","eventName","siteId",
                    "workStateCode", "workId", "lotId", "carrId", "eqpId", "portId",
                    ExceptionCode.UnmatchedCarrLoc, "errorComment");
        }else{

            WnTransferJob wnTransferJob = wnTransferJobList.get(0);
            if(!this.validateCarrLocation(wnTransferJob.getDestEqpId(), body.getEqpId(),
                    wnTransferJob.getDestPortId(), body.getPortId())){
                log.error("Mismatch Eqp and Port info.");
                throw new ScenarioException("messageKey", "scenarioType","eventName","siteId",
                        "workStateCode", "workId", "lotId", "carrId", "eqpId", "portId",
                        ExceptionCode.UnmatchedCarrLoc, "errorComment");
            }else{

                this.updateEqpPortWithTransferInfo(apFlowProcessVo, body);

            }
        }
    }
}