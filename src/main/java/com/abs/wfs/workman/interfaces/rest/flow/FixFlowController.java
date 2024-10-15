package com.abs.wfs.workman.interfaces.rest.flow;


import com.abs.wfs.workman.service.flow.eap.impl.*;
import com.abs.wfs.workman.service.flow.fix.WfsFixEventReqServiceImpl;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.eap.*;
import com.abs.wfs.workman.spec.in.fix.WfsFixEventReqIvo;
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
@RequestMapping("/flow/fix/")
@RequiredArgsConstructor
public class FixFlowController {



    /**
     * WFS_TOOL_VER
     */
    @Autowired
    WfsFixEventReqServiceImpl fixEventReqService;

    @PostMapping(WorkManMessageList.WFS_FIX_EVENT_REQ)
    public ResponseEntity<ApResponseIvo> execute(@RequestBody WfsFixEventReqIvo fixEventReqIvo,
                                                 @RequestParam(value = "key") String trackingKey,
                                                 @RequestParam(value = "scenario") String scenarioType) throws Exception {

        log.info(fixEventReqIvo.toString());

        return processRequest(() -> fixEventReqService.execute(fixEventReqService.initialize(
                        WorkManMessageList.WFS_FIX_EVENT_REQ,
                        trackingKey,
                        scenarioType,
                        fixEventReqIvo.getHead()), fixEventReqIvo),
                fixEventReqIvo.getBody());

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