package com.abs.wfs.workman.interfaces.rest.flow;


import com.abs.wfs.workman.dao.domain.transferJob.service.WnTransferJobServiceImpl;
import com.abs.wfs.workman.dao.domain.transferJob.vo.CancelTransferJobResultVo;
import com.abs.wfs.workman.service.common.work.WorkManageService;
import com.abs.wfs.workman.service.flow.oi.impl.*;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.oia.*;
import com.abs.wfs.workman.util.WorkManCommonUtil;
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
@RequestMapping("/flow/oi/")
@RequiredArgsConstructor
public class OiFlowController {


    @Autowired
    WnTransferJobServiceImpl transferJobService;

    @PostMapping(WorkManMessageList.WFS_OI_CARR_MOVE_CANCEL_REQ)
    public ResponseEntity<CancelTransferJobResultVo> execute(
            @RequestParam(value = "siteId") String siteId,
            @RequestParam(value = "portId") String portId) throws Exception {

        CancelTransferJobResultVo resultVo = this.transferJobService.cancelTransferJob(siteId, portId);
        log.info(resultVo.toString());

        return new ResponseEntity<>(resultVo, HttpStatus.OK);
    }


    @Autowired
    WfsOiPortUnloadReqServiceImpl wfsOiPortUnloadReqService;
    @PostMapping(WorkManMessageList.WFS_OI_PORT_UNLOAD_REQ)
    public ResponseEntity<ApResponseIvo> execute(@RequestBody WfsOiPortUnloadReqIvo wfsOiPortUnloadReqIvo,
                                                 @RequestParam(value = "key") String trackingKey,
                                                 @RequestParam(value = "scenario") String scenarioType) throws Exception {


        return processRequest(() -> wfsOiPortUnloadReqService.execute(wfsOiPortUnloadReqService.initialize(
                        WorkManMessageList.WFS_OI_PORT_UNLOAD_REQ,
                        trackingKey,
                        scenarioType,
                        wfsOiPortUnloadReqIvo.getHead()), wfsOiPortUnloadReqIvo),
                wfsOiPortUnloadReqIvo.getBody());

    }


    /**
     * WFS_OI_WORK
     */
    @Autowired
    WorkManageService workManageService;

    @PostMapping(WorkManMessageList.WFS_OI_GENERATE_WORK_REQ)
    public ResponseEntity<ApResponseIvo> execute(@RequestBody WfsOiGenerateWorkReqIvo wfsOiGenerateWorkReqIvo,
                                                 @RequestParam(value = "key", required = false) String trackingKey,
                                                 @RequestParam(value = "scenario") String scenarioType) throws Exception {



        return processRequest(() -> workManageService.generateMeasurementRoomWork(WorkManCommonUtil.initializeProcessVo(
                        WorkManMessageList.WFS_OI_GENERATE_WORK_REQ,
                        (trackingKey == null || trackingKey.isEmpty()) ? WorkManCommonUtil.generateRandomKey()  : trackingKey,
                        scenarioType,
                        wfsOiGenerateWorkReqIvo.getHead()), wfsOiGenerateWorkReqIvo),
                wfsOiGenerateWorkReqIvo.getBody());

    }




    @Autowired
    WfsOiCarrMoveCrtServiceImpl wfsOiCarrMoveCrtService;

    @PostMapping(WorkManMessageList.WFS_OI_CARR_MOVE_CRT)
    public ResponseEntity<ApResponseIvo> control(@RequestBody WfsOiCarrMoveCrtIvo wfsOiCarrMoveCrtIvo,
                                                               @RequestParam(value = "key") String trackingKey,
                                                               @RequestParam(value = "scenario") String scenarioType) throws Exception {


        String cid = WorkManMessageList.WFS_OI_CARR_MOVE_CRT;
        ApFlowProcessVo apFlowProcessVo = this.wfsOiCarrMoveCrtService.initialize(cid, trackingKey, scenarioType, wfsOiCarrMoveCrtIvo.getHead());

        try{
            ApFlowProcessVo resultVo = this.wfsOiCarrMoveCrtService.execute(apFlowProcessVo, wfsOiCarrMoveCrtIvo);

            return new ResponseEntity<>(
                    ApResponseIvo.builder().msgBody(wfsOiCarrMoveCrtIvo.getBody()).processInfo(resultVo)
                                            .build(),

                                            HttpStatus.OK);

        }catch (ScenarioException se){
            return new ResponseEntity<>(
                    ApResponseIvo.builder().scenarioException(se).build(),
                    HttpStatus.OK

            );

        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PostMapping(WorkManMessageList.WFS_OI_CARR_DEST_CHG_REQ)
    public ResponseEntity<ApResponseIvo> control(@RequestBody WfsOiCarrDestChgReqIvo wfsOiCarrDestChgReqIvo,
                                                               @RequestParam(value = "key") String trackingKey,
                                                               @RequestParam(value = "scenario") String scenarioType) throws Exception {



        return new ResponseEntity<>(null,HttpStatus.OK);
    }


    @Autowired
    WfsWorkProgressOiServiceImpl wfsWorkProgressOiService;
    @PostMapping(WorkManMessageList.WFS_WORK_PROGRESS_OI)
    public ResponseEntity<ApResponseIvo> execute(@RequestBody WfsWorkProgressOiIvo wfsWorkProgressOiIvo,
                                                 @RequestParam(value = "key") String trackingKey,
                                                 @RequestParam(value = "scenario") String scenarioType) throws Exception {
        return processRequest(() -> wfsWorkProgressOiService.execute(wfsWorkProgressOiService.initialize(
                        WorkManMessageList.WFS_WORK_PROGRESS_OI,
                        trackingKey,
                        scenarioType,
                        wfsWorkProgressOiIvo.getHead()), wfsWorkProgressOiIvo),
                wfsWorkProgressOiIvo.getBody());

    }

    @Autowired
    WfsDspInfoCreateOiServiceImpl wfsDspInfoCreateOiService;
    @PostMapping(WorkManMessageList.WFS_DSP_INFO_CREATE_OI)
    public ResponseEntity<ApResponseIvo> execute(@RequestBody WfsDspInfoCreateOiIvo wfsDspInfoCreateOiIvo,
                                                 @RequestParam(value = "key") String trackingKey,
                                                 @RequestParam(value = "scenario") String scenarioType) throws Exception {
        return processRequest(() -> wfsDspInfoCreateOiService.execute(wfsDspInfoCreateOiService.initialize(
                        WorkManMessageList.WFS_DSP_INFO_CREATE_OI,
                        trackingKey,
                        scenarioType,
                        wfsDspInfoCreateOiIvo.getHead()), wfsDspInfoCreateOiIvo),
                wfsDspInfoCreateOiIvo.getBody());
    }

    @Autowired
    WfsInitEqpStateReqOiServiceImpl wfsInitEqpStateReqOiService;
    @PostMapping(WorkManMessageList.WFS_INIT_EQP_STATE_REQ_OI)
    public ResponseEntity<ApResponseIvo> execute(@RequestBody WfsInitEqpStateReqOiIvo wfsInitEqpStateReqOiIvo,
                                                 @RequestParam(value = "key") String trackingKey,
                                                 @RequestParam(value = "scenario") String scenarioType) throws Exception {
        return processRequest(() -> wfsInitEqpStateReqOiService.execute(wfsInitEqpStateReqOiService.initialize(
                        WorkManMessageList.WFS_INIT_EQP_STATE_REQ_OI,
                        trackingKey,
                        scenarioType,
                        wfsInitEqpStateReqOiIvo.getHead()), wfsInitEqpStateReqOiIvo),
                wfsInitEqpStateReqOiIvo.getBody());
    }

    @Autowired
    WfsJobAbortOiServiceImpl wfsJobAbortOiService;
    @PostMapping(WorkManMessageList.WFS_JOB_ABORT_OI)
    public ResponseEntity<ApResponseIvo> execute(@RequestBody WfsJobAbortOiIvo wfsJobAbortOiIvo,
                                                 @RequestParam(value = "key") String trackingKey,
                                                 @RequestParam(value = "scenario") String scenarioType) throws Exception {
        return processRequest(() -> wfsJobAbortOiService.execute(wfsJobAbortOiService.initialize(
                        WorkManMessageList.WFS_JOB_ABORT_OI,
                        trackingKey,
                        scenarioType,
                        wfsJobAbortOiIvo.getHead()), wfsJobAbortOiIvo),
                wfsJobAbortOiIvo.getBody());
    }

    @Autowired
    WfsManualTrackOutOiServiceImpl wfsManualTrackOutOiService;
    @PostMapping(WorkManMessageList.WFS_MANUAL_TRACK_OUT_OI)
    public ResponseEntity<ApResponseIvo> execute(@RequestBody WfsManualTrackOutOiIvo wfsManualTrackOutOiIvo,
                                                 @RequestParam(value = "key") String trackingKey,
                                                 @RequestParam(value = "scenario") String scenarioType) throws Exception {
        return processRequest(() -> wfsManualTrackOutOiService.execute(wfsManualTrackOutOiService.initialize(
                        WorkManMessageList.WFS_MANUAL_TRACK_OUT_OI,
                        trackingKey,
                        scenarioType,
                        wfsManualTrackOutOiIvo.getHead()), wfsManualTrackOutOiIvo),
                wfsManualTrackOutOiIvo.getBody());
    }

    @Autowired
    WfsRechuckOiServiceImpl wfsRechuckOiService;
    @PostMapping(WorkManMessageList.WFS_RECHUCK_OI)
    public ResponseEntity<ApResponseIvo> execute(@RequestBody WfsRechuckOiIvo wfsRechuckOiIvo,
                                                 @RequestParam(value = "key") String trackingKey,
                                                 @RequestParam(value = "scenario") String scenarioType) throws Exception {
        return processRequest(() -> wfsRechuckOiService.execute(wfsRechuckOiService.initialize(
                        WorkManMessageList.WFS_RECHUCK_OI,
                        trackingKey,
                        scenarioType,
                        wfsRechuckOiIvo.getHead()), wfsRechuckOiIvo),
                wfsRechuckOiIvo.getBody());
    }

    @Autowired
    WfsSorterModeChgOiServiceImpl wfsSorterModeChgOiService;
    @PostMapping(WorkManMessageList.WFS_SORTER_MODE_CHG_OI)
    public ResponseEntity<ApResponseIvo> execute(@RequestBody WfsSorterModeChgOiIvo wfsSorterModeChgOiIvo,
                                                 @RequestParam(value = "key") String trackingKey,
                                                 @RequestParam(value = "scenario") String scenarioType) throws Exception {
        return processRequest(() -> wfsSorterModeChgOiService.execute(wfsSorterModeChgOiService.initialize(
                        WorkManMessageList.WFS_SORTER_MODE_CHG_OI,
                        trackingKey,
                        scenarioType,
                        wfsSorterModeChgOiIvo.getHead()), wfsSorterModeChgOiIvo),
                wfsSorterModeChgOiIvo.getBody());
    }

    @Autowired
    WfsWorkCancelServiceImpl wfsWorkCancelService;
    @PostMapping(WorkManMessageList.WFS_WORK_CANCEL)
    public ResponseEntity<ApResponseIvo> execute(@RequestBody WfsWorkCancelIvo wfsWorkCancelIvo,
                                                 @RequestParam(value = "key") String trackingKey,
                                                 @RequestParam(value = "scenario") String scenarioType) throws Exception {
        return processRequest(() -> wfsWorkCancelService.execute(wfsWorkCancelService.initialize(
                        WorkManMessageList.WFS_WORK_CANCEL,
                        trackingKey,
                        scenarioType,
                        wfsWorkCancelIvo.getHead()), wfsWorkCancelIvo),
                wfsWorkCancelIvo.getBody());
    }

    @Autowired
    WfsWorkInitializeServiceImpl wfsWorkInitializeService;
    @PostMapping(WorkManMessageList.WFS_WORK_INITIALIZE)
    public ResponseEntity<ApResponseIvo> execute(@RequestBody WfsWorkInitializeIvo wfsWorkInitializeIvo,
                                                 @RequestParam(value = "key") String trackingKey,
                                                 @RequestParam(value = "scenario") String scenarioType) throws Exception {
        return processRequest(() -> wfsWorkInitializeService.execute(wfsWorkInitializeService.initialize(
                        WorkManMessageList.WFS_WORK_INITIALIZE,
                        trackingKey,
                        scenarioType,
                        wfsWorkInitializeIvo.getHead()), wfsWorkInitializeIvo),
                wfsWorkInitializeIvo.getBody());
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
}
