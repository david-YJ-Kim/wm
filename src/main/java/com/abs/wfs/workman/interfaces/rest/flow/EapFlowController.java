package com.abs.wfs.workman.interfaces.rest.flow;


import com.abs.wfs.workman.service.flow.eap.impl.*;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.eap.*;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.exception.ScenarioException;
import com.abs.wfs.workman.util.vo.ApResponseIvo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/flow/eap/")
@RequiredArgsConstructor
public class EapFlowController {

    @Autowired
    WfsInspDataReportImpl wfsInspDataReportImpl;

    // TODO endpoint 일원화
//
//    public ResponseEntity<ApResponseIvo> execute(@RequestBody String payload,
//                                                 @RequestParam(value = "key") String trackingKey,
//                                                 @RequestParam(value = "scenario") String scenarioType) throws Exception {
//
//
//
//        String cid = WorkManMessageList.WFS_OI_CARR_MOVE_CRT;
//        ApFlowProcessVo apFlowProcessVo = this.wfsOiCarrMoveCrtService.initialize(cid, trackingKey, scenarioType, wfsOiCarrMoveCrtIvo.getHead());
//
//        try{
//            ApFlowProcessVo resultVo = this.wfsOiCarrMoveCrtService.execute(apFlowProcessVo, wfsOiCarrMoveCrtIvo);
//
//            return new ResponseEntity<>(
//                    ApResponseIvo.builder().msgBody(wfsOiCarrMoveCrtIvo.getBody()).processInfo(resultVo)
//                            .build(),
//
//                    HttpStatus.OK);
//
//        }catch (ScenarioException se){
//            return new ResponseEntity<>(
//                    ApResponseIvo.builder().scenarioException(se).build(),
//                    HttpStatus.OK
//
//            );
//
//        }catch (Exception e){
//            log.error(e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }




    @PostMapping(WorkManMessageList.WFS_CARR_SLOTMAP_REPORT_REQ)
    public ResponseEntity<ApResponseIvo> execute(@RequestBody WfsCarrSlotmapReportReqVo wfsCarrSlotmapReportReqVo,
                                                 @RequestParam(value = "key") String trackingKey,
                                                 @RequestParam(value = "scenario") String scenarioType) throws Exception {

        return new ResponseEntity<>(
                        ApResponseIvo.builder().build(),
                HttpStatus.NON_AUTHORITATIVE_INFORMATION);

    }


    /**
     * WFS_TRAY
     */
    @Autowired
    WfsTrayLoadCompServiceImpl wfsTrayLoadCompService;

    @PostMapping(WorkManMessageList.WFS_TRAY_LOAD_COMP)
    public ResponseEntity<ApResponseIvo> execute(@RequestBody WfsTrayLoadCompIvo wfsTrayLoadCompIvo,
                                                               @RequestParam(value = "key") String trackingKey,
                                                               @RequestParam(value = "scenario") String scenarioType) throws Exception {


        return processRequest(() -> wfsTrayLoadCompService.execute(wfsTrayLoadCompService.initialize(
                WorkManMessageList.WFS_TRAY_LOAD_COMP,
                trackingKey,
                scenarioType,
                wfsTrayLoadCompIvo.getHead()), wfsTrayLoadCompIvo),
                wfsTrayLoadCompIvo.getBody());

    }


    /**
     * WFS_ALARM
     */


    @Autowired
    WfsAlarmReportImpl wfsAlarmReport;

    @PostMapping(WorkManMessageList.WFS_ALARM_REPORT)
    public ApFlowProcessVo execute(@RequestBody WfsAlarmReportIvo wfsAlarmReportIvo,
                                                 @RequestParam(value = "key") String trackingKey,
                                                 @RequestParam(value = "scenario") String scenarioType) throws Exception {


        String cid = WorkManMessageList.WFS_ALARM_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsAlarmReport.initialize(cid, trackingKey, scenarioType, wfsAlarmReportIvo.getHead());

        return this.wfsAlarmReport.execute(apFlowProcessVo, wfsAlarmReportIvo);

    }


    private ResponseEntity<ApResponseIvo> processRequest(ProcessExecutor executor, ApMsgBody body) {
        try {
            ApFlowProcessVo apFlowProcessVo = executor.execute();
            return new ResponseEntity<>(
                    ApResponseIvo.builder()
                            .msgBody(body)
                            .processInfo(apFlowProcessVo)
                            .build(),
                    HttpStatus.OK);
        } catch (ScenarioException se) {
            return new ResponseEntity<>(
                    ApResponseIvo.builder().scenarioException(se).build(),
                    HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @FunctionalInterface
    private interface ProcessExecutor {
        ApFlowProcessVo execute() throws Exception;
    }



    /**
     * WFS_EQP
     */

//    @Autowired
//    WfsCarrIdReadImpl wfsCarrIdRead;
//
//    @PostMapping(WorkManMessageList.WFS_CARR_ID_READ)
//    public ResponseEntity<ApResponseIvo> executeEvent(@RequestBody WfsCarrIdReadIvo wfsCarrIdReadIvo,
//                                        @RequestParam(value = "key") String trackingKey,
//                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {
//
//        return processRequest(() -> wfsCarrIdRead.execute(wfsCarrIdRead.initialize(
//                        WorkManMessageList.WFS_CARR_ID_READ,
//                        trackingKey,
//                        scenarioType,
//                        wfsCarrIdReadIvo.getHead()), wfsCarrIdReadIvo),
//                wfsCarrIdReadIvo.getBody());
//
//
//    }


    @Autowired
    WfsEqpStateReportImpl wfsEqpStateReportImpl;

    @PostMapping(WorkManMessageList.WFS_EQP_CONTROL_STATE_REPORT)
    public ResponseEntity<ApResponseIvo> executeEvent(@RequestBody WfsEqpStateReportIvo wfsEqpStateReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {


        String cid = WorkManMessageList.WFS_EQP_CONTROL_STATE_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsEqpStateReportImpl.initialize(cid, trackingKey, scenarioType, wfsEqpStateReportIvo.getHead());


        try{
            return new ResponseEntity<>(

                    ApResponseIvo.builder()
                            .msgBody(wfsEqpStateReportIvo.getBody())
                            .processInfo(this.wfsEqpStateReportImpl.execute(apFlowProcessVo, wfsEqpStateReportIvo))
                    .build(),

                    HttpStatus.NON_AUTHORITATIVE_INFORMATION);


        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @Autowired
    WfsEqpControlStateReportImpl wfsEqpControlStateReportImpl;

    @PostMapping(WorkManMessageList.WFS_EQP_STATE_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsEqpControlStateReportIvo wfsEqpControlStateReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {


        String cid = WorkManMessageList.WFS_EQP_STATE_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsEqpControlStateReportImpl.initialize(cid, trackingKey, scenarioType, wfsEqpControlStateReportIvo.getHead());

        return this.wfsEqpControlStateReportImpl.execute(apFlowProcessVo, wfsEqpControlStateReportIvo);

    }

    @Autowired
    WfsInitPortStateReportImpl wfsInitPortStateReportImpl;

    @PostMapping(WorkManMessageList.WFS_INIT_PORT_STATE_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsInitPortStateReportIvo wfsEqpControlStateReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_INIT_PORT_STATE_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsInitPortStateReportImpl.initialize(cid, trackingKey, scenarioType, wfsEqpControlStateReportIvo.getHead());

        return this.wfsInitPortStateReportImpl.execute(apFlowProcessVo, wfsEqpControlStateReportIvo);

    }


    @PostMapping(WorkManMessageList.WFS_INSP_DATA_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsInspDataReportIvo wfsInspDataReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_INSP_DATA_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsInspDataReportImpl.initialize(cid, trackingKey, scenarioType, wfsInspDataReportIvo.getHead());

        return this.wfsInspDataReportImpl.execute(apFlowProcessVo, wfsInspDataReportIvo);

    }

    @Autowired
    WfsInspReportImpl wfsInspReportImpl;


    @PostMapping(WorkManMessageList.WFS_INSP_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsInspReportIvo wfsInspReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_INSP_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsInspReportImpl.initialize(cid, trackingKey, scenarioType, wfsInspReportIvo.getHead());

        return this.wfsInspReportImpl.execute(apFlowProcessVo, wfsInspReportIvo);

    }


    @Autowired
    WfsLotInfoReqImpl wfsLotInfoReqImpl;

    @PostMapping(WorkManMessageList.WFS_LOT_INFO_REQ)
    public ApFlowProcessVo executeEvent(@RequestBody WfsLotInfoReqIvo wfsLotInfoReqIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_LOT_INFO_REQ;
        ApFlowProcessVo apFlowProcessVo = this.wfsLotInfoReqImpl.initialize(cid, trackingKey, scenarioType, wfsLotInfoReqIvo.getHead());

        return this.wfsLotInfoReqImpl.execute(apFlowProcessVo, wfsLotInfoReqIvo);

    }


    @Autowired
    WfsMaterialChangeReqImpl wfsMaterialChangeReq;

    @PostMapping(WorkManMessageList.WFS_MATERIAL_CHANGE_REQ)
    public ApFlowProcessVo executeEvent(@RequestBody WfsMaterialChangeReqIvo wfsMaterialChangeReqIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_MATERIAL_CHANGE_REQ;
        ApFlowProcessVo apFlowProcessVo = this.wfsMaterialChangeReq.initialize(cid, trackingKey, scenarioType, wfsMaterialChangeReqIvo.getHead());

        return this.wfsMaterialChangeReq.execute(apFlowProcessVo, wfsMaterialChangeReqIvo);

    }


    @Autowired
    WfsMaterialKitdekitReportImpl wfsMaterialKitdekitReport;

    @PostMapping(WorkManMessageList.WFS_MATERIAL_KITDEKIT_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsMaterialKitdekitReportIvo wfsMaterialKitdekitReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        log.info(wfsMaterialKitdekitReportIvo.toString());
        String cid = WorkManMessageList.WFS_MATERIAL_KITDEKIT_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsMaterialKitdekitReport.initialize(cid, trackingKey, scenarioType, wfsMaterialKitdekitReportIvo.getHead());

        return this.wfsMaterialKitdekitReport.execute(apFlowProcessVo, wfsMaterialKitdekitReportIvo);

    }


    @Autowired
    WfsMaterialUsageReportImpl wfsMaterialUsageReport;

    @PostMapping(WorkManMessageList.WFS_MATERIAL_USAGE_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsMaterialUsageReportIvo wfsMaterialUsageReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_MATERIAL_USAGE_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsMaterialUsageReport.initialize(cid, trackingKey, scenarioType, wfsMaterialUsageReportIvo.getHead());

        return this.wfsMaterialUsageReport.execute(apFlowProcessVo, wfsMaterialUsageReportIvo);

    }


    @Autowired
    WfsProdDcollReportImpl wfsProdDcollReport;

    @PostMapping(WorkManMessageList.WFS_PROD_DCOLL_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsProdDcollReportIvo wfsProdDcollReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_PROD_DCOLL_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsProdDcollReport.initialize(cid, trackingKey, scenarioType, wfsProdDcollReportIvo.getHead());

        return this.wfsProdDcollReport.execute(apFlowProcessVo, wfsProdDcollReportIvo);

    }


    @Autowired
    WfsScrapReportImpl wfsScrapReport;

    @PostMapping(WorkManMessageList.WFS_SCRAP_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsScrapReportIvo wfsScrapReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_SCRAP_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsScrapReport.initialize(cid, trackingKey, scenarioType, wfsScrapReportIvo.getHead());

        return this.wfsScrapReport.execute(apFlowProcessVo, wfsScrapReportIvo);

    }


    @Autowired
    WfsProdInspJdgmImpl wfsProdInspJdgm;

    @PostMapping(WorkManMessageList.WFS_PROD_INSP_JDGM)
    public ApFlowProcessVo executeEvent(@RequestBody WfsProdInspJdgmIvo wfsProdInspJdgmIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_PROD_INSP_JDGM;
        ApFlowProcessVo apFlowProcessVo = this.wfsProdInspJdgm.initialize(cid, trackingKey, scenarioType, wfsProdInspJdgmIvo.getHead());

        return this.wfsProdInspJdgm.execute(apFlowProcessVo, wfsProdInspJdgmIvo);

    }

    @Autowired
    WfsPortStateReportImpl wfsPortStateReport;

    @PostMapping(WorkManMessageList.WFS_PORT_STATE_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsPortStateReportIvo wfsPortStateReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_PORT_STATE_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsPortStateReport.initialize(cid, trackingKey, scenarioType, wfsPortStateReportIvo.getHead());

        return this.wfsPortStateReport.execute(apFlowProcessVo, wfsPortStateReportIvo);

    }


    /**
     * WFS_SORTER
     */


    @Autowired
    WfsSorterModeChangeRepImpl wfsSorterModeChangeRep;

    @PostMapping(WorkManMessageList.WFS_SORTER_MODE_CHANGE_REP)
    public ApFlowProcessVo executeEvent(@RequestBody WfsSorterModeChangeRepIvo wfsSorterModeChangeRepIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_SORTER_MODE_CHANGE_REP;
        ApFlowProcessVo apFlowProcessVo = this.wfsSorterModeChangeRep.initialize(cid, trackingKey, scenarioType, wfsSorterModeChangeRepIvo.getHead());

        return this.wfsSorterModeChangeRep.execute(apFlowProcessVo, wfsSorterModeChangeRepIvo);

    }

    /**
     * WFS_UNLOAD
     */


    @Autowired
    WfsUnloadReqImpl wfsUnloadReq;

    @PostMapping(WorkManMessageList.WFS_UNLOAD_REQ)
    public ApFlowProcessVo executeEvent(@RequestBody WfsUnloadReqIvo wfsUnloadReqIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_UNLOAD_REQ;
        ApFlowProcessVo apFlowProcessVo = this.wfsUnloadReq.initialize(cid, trackingKey, scenarioType, wfsUnloadReqIvo.getHead());

        return this.wfsUnloadReq.execute(apFlowProcessVo, wfsUnloadReqIvo);

    }


    @Autowired
    WfsUnloadCompImpl wfsUnloadComp;

    @PostMapping(WorkManMessageList.WFS_UNLOAD_COMP)
    public ApFlowProcessVo executeEvent(@RequestBody WfsUnloadCompIvo wfsUnloadCompIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_UNLOAD_COMP;
        ApFlowProcessVo apFlowProcessVo = this.wfsUnloadComp.initialize(cid, trackingKey, scenarioType, wfsUnloadCompIvo.getHead());

        return this.wfsUnloadComp.execute(apFlowProcessVo, wfsUnloadCompIvo);

    }

    /**
     * WFS_LOAD
     */


    @Autowired
    WfsLoadCompImpl wfsLoadComp;

    @PostMapping(WorkManMessageList.WFS_LOAD_COMP)
    public ApFlowProcessVo executeEvent(@RequestBody WfsLoadCompIvo wfsLoadCompIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_LOAD_COMP;
        ApFlowProcessVo apFlowProcessVo = this.wfsLoadComp.initialize(cid, trackingKey, scenarioType, wfsLoadCompIvo.getHead());

        return this.wfsLoadComp.execute(apFlowProcessVo, wfsLoadCompIvo);

    }


    /**
     * WFS_WORK
     */

    @Autowired
    WfsWorkOrderRepImpl wfsWorkOrderRep;

    @PostMapping(WorkManMessageList.WFS_WORK_ORDER_REP)
    public ApFlowProcessVo executeEvent(@RequestBody WfsWorkOrderRepIvo wfsWorkOrderRepIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_WORK_ORDER_REP;
        ApFlowProcessVo apFlowProcessVo = this.wfsWorkOrderRep.initialize(cid, trackingKey, scenarioType, wfsWorkOrderRepIvo.getHead());

        return this.wfsWorkOrderRep.execute(apFlowProcessVo, wfsWorkOrderRepIvo);

    }


    @Autowired
    WfsWorkStartRepImpl wfsWorkStartRep;

    @PostMapping(WorkManMessageList.WFS_WORK_START_REP)
    public ApFlowProcessVo executeEvent(@RequestBody WfsWorkStartRepIvo wfsWorkStartRepIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_WORK_START_REP;
        ApFlowProcessVo apFlowProcessVo = this.wfsWorkStartRep.initialize(cid, trackingKey, scenarioType, wfsWorkStartRepIvo.getHead());

        return this.wfsWorkStartRep.execute(apFlowProcessVo, wfsWorkStartRepIvo);

    }


    @Autowired
    WfsDurableInfoRepImpl wfsDurableInfoRep;

    @PostMapping(WorkManMessageList.WFS_DURABLE_INFO_REP)
    public ApFlowProcessVo executeEvent(@RequestBody WfsDurableInfoRepIvo wfsDurableInfoRepIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_DURABLE_INFO_REP;
        ApFlowProcessVo apFlowProcessVo = this.wfsDurableInfoRep.initialize(cid, trackingKey, scenarioType, wfsDurableInfoRepIvo.getHead());

        return this.wfsDurableInfoRep.execute(apFlowProcessVo, wfsDurableInfoRepIvo);

    }



    @Autowired
    WfsWorkAbortImpl wfsWorkAbort;

    @PostMapping(WorkManMessageList.WFS_WORK_ABORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsWorkAbortIvo wfsWorkAbortIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_WORK_ABORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsWorkAbort.initialize(cid, trackingKey, scenarioType, wfsWorkAbortIvo.getHead());

        return this.wfsWorkAbort.execute(apFlowProcessVo, wfsWorkAbortIvo);

    }


    @Autowired
    WfsPrecedeLotResumeImpl wfsPrecedeLotResume;

    @PostMapping(WorkManMessageList.WFS_PRECEDE_LOT_RESUME)
    public ApFlowProcessVo executeEvent(@RequestBody WfsPrecedeLotResumeIvo wfsPrecedeLotResumeIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_PRECEDE_LOT_RESUME;
        ApFlowProcessVo apFlowProcessVo = this.wfsPrecedeLotResume.initialize(cid, trackingKey, scenarioType, wfsPrecedeLotResumeIvo.getHead());

        return this.wfsPrecedeLotResume.execute(apFlowProcessVo, wfsPrecedeLotResumeIvo);

    }


    @Autowired
    WfsLotTrackInCancelRepImpl wfsLotTrackInCancelRep;

    @PostMapping(WorkManMessageList.WFS_LOT_TRACK_IN_CANCEL_REP)
    public ApFlowProcessVo executeEvent(@RequestBody WfsLotTrackInCancelRepIvo wfsLotTrackInCancelRepIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_LOT_TRACK_IN_CANCEL_REP;
        ApFlowProcessVo apFlowProcessVo = this.wfsLotTrackInCancelRep.initialize(cid, trackingKey, scenarioType, wfsLotTrackInCancelRepIvo.getHead());

        return this.wfsLotTrackInCancelRep.execute(apFlowProcessVo, wfsLotTrackInCancelRepIvo);

    }



    @Autowired
    WfsInitDurableInfoReportImpl wfsInitDurableInfoReport;

    @PostMapping(WorkManMessageList.WFS_INIT_DURABLE_INFO_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsInitDurableInfoReportIvo wfsInitDurableInfoReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_INIT_DURABLE_INFO_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsInitDurableInfoReport.initialize(cid, trackingKey, scenarioType, wfsInitDurableInfoReportIvo.getHead());

        return this.wfsInitDurableInfoReport.execute(apFlowProcessVo, wfsInitDurableInfoReportIvo);

    }


    /**
     * WFS_EFEM
     */

    @Autowired
    WfsEfemStateReportImpl wfsEfemStateReport;

    @PostMapping(WorkManMessageList.WFS_EFEM_STATE_REPORT )
    public ApFlowProcessVo executeEvent(@RequestBody WfsEfemStateReportIvo wfsEfemStateReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_EFEM_STATE_REPORT ;
        ApFlowProcessVo apFlowProcessVo = this.wfsEfemStateReport.initialize(cid, trackingKey, scenarioType, wfsEfemStateReportIvo.getHead());

        return this.wfsEfemStateReport.execute(apFlowProcessVo, wfsEfemStateReportIvo);

    }


    @Autowired
    WfsEfemControlStateReportImpl wfsEfemControlStateReport;

    @PostMapping(WorkManMessageList.WFS_EFEM_CONTROL_STATE_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsEfemControlStateReportIvo wfsEfemControlStateReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_EFEM_CONTROL_STATE_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsEfemControlStateReport.initialize(cid, trackingKey, scenarioType, wfsEfemControlStateReportIvo.getHead());

        return this.wfsEfemControlStateReport.execute(apFlowProcessVo, wfsEfemControlStateReportIvo);

    }


}