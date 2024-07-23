package com.abs.wfs.workman.interfaces.rest.flow;


import com.abs.wfs.workman.service.common.work.WorkManageService;
import com.abs.wfs.workman.service.flow.brs.impl.WfsManualWorkStartImpl;
import com.abs.wfs.workman.service.flow.oi.impl.WfsOiCarrMoveCrtServiceImpl;
import com.abs.wfs.workman.service.flow.oi.impl.WfsOiPortUnloadReqServiceImpl;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.brs.WfsManualWorkStartIvo;
import com.abs.wfs.workman.spec.in.eap.WfsTrayLoadCompIvo;
import com.abs.wfs.workman.spec.in.oia.WfsOiCarrDestChgReqIvo;
import com.abs.wfs.workman.spec.in.oia.WfsOiCarrMoveCrtIvo;
import com.abs.wfs.workman.spec.in.oia.WfsOiGenerateWorkReqIvo;
import com.abs.wfs.workman.spec.in.oia.WfsOiPortUnloadReqIvo;
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
                                                 @RequestParam(value = "key") String trackingKey,
                                                 @RequestParam(value = "scenario") String scenarioType) throws Exception {


        return processRequest(() -> workManageService.generateMeasurementRoomWork(WorkManCommonUtil.initializeProcessVo(
                        WorkManMessageList.WFS_OI_GENERATE_WORK_REQ,
                        trackingKey,
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
