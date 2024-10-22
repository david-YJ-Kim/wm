package com.abs.wfs.workman.interfaces.rest.flow;


import com.abs.wfs.workman.service.flow.far.impl.WfsArRequestServiceImpl;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.fix.WfsArRequestIvo;
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
@RequestMapping("/flow/far/")
@RequiredArgsConstructor
public class ArFlowController {



    /**
     * WFS_TOOL_VER
     */
    @Autowired
    WfsArRequestServiceImpl wfsArRequestService;

    @PostMapping(WorkManMessageList.WFS_AR_REQUEST)
    public ResponseEntity<ApResponseIvo> execute(@RequestBody WfsArRequestIvo ivo,
                                                 @RequestParam(value = "key") String trackingKey,
                                                 @RequestParam(value = "scenario") String scenarioType) throws Exception {

        log.info(ivo.toString());

        return processRequest(() -> wfsArRequestService.execute(wfsArRequestService.initialize(
                        WorkManMessageList.WFS_AR_REQUEST,
                        trackingKey,
                        scenarioType,
                        ivo.getHead()), ivo),
                ivo.getBody());

    }



    private ResponseEntity<ApResponseIvo> processRequest(ProcessExecutor executor, ApMsgBody body) {
        try {
            ApFlowProcessVo apFlowProcessVo = executor.execute();
            return new ResponseEntity<>(
                    ApResponseIvo.builder()
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