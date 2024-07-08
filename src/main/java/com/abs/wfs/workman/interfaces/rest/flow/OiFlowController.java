package com.abs.wfs.workman.interfaces.rest.flow;


import com.abs.wfs.workman.service.flow.brs.impl.WfsManualWorkStartImpl;
import com.abs.wfs.workman.service.flow.oi.impl.WfsOiCarrMoveCrtServiceImpl;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.brs.WfsManualWorkStartIvo;
import com.abs.wfs.workman.spec.in.oia.WfsOiCarrDestChgReqIvo;
import com.abs.wfs.workman.spec.in.oia.WfsOiCarrMoveCrtIvo;
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
    WfsOiCarrMoveCrtServiceImpl wfsOiCarrMoveCrtService;

    @PostMapping(WorkManMessageList.WFS_OI_CARR_MOVE_CRT)
    public ResponseEntity<ApResponseIvo> executeWfsAlarmReport(@RequestBody WfsOiCarrMoveCrtIvo wfsOiCarrMoveCrtIvo,
                                                               @RequestParam(value = "key") String trackingKey,
                                                               @RequestParam(value = "scenario") String scenarioType) throws Exception {


        String cid = WorkManMessageList.WFS_OI_CARR_MOVE_CRT;
        ApFlowProcessVo apFlowProcessVo = this.wfsOiCarrMoveCrtService.initialize(cid, trackingKey, scenarioType, wfsOiCarrMoveCrtIvo.getHead().getTid());

        try{
            ApFlowProcessVo resultVo = this.wfsOiCarrMoveCrtService.execute(apFlowProcessVo, wfsOiCarrMoveCrtIvo);

            return new ResponseEntity<>(ApResponseIvo.builder()
                                            .msgBody(wfsOiCarrMoveCrtIvo.getBody()).processInfo(resultVo)
                                            .build(),
                                            HttpStatus.OK);

        }catch (ScenarioException se){

            return new ResponseEntity<>(ApResponseIvo.builder().scenarioException(se).build(),
                    HttpStatus.OK);

        }catch (Exception e){
            throw e;
        }

    }


    @PostMapping(WorkManMessageList.WFS_OI_CARR_DEST_CHG_REQ)
    public ResponseEntity<ApResponseIvo> executeWfsAlarmReport(@RequestBody WfsOiCarrDestChgReqIvo wfsOiCarrDestChgReqIvo,
                                                               @RequestParam(value = "key") String trackingKey,
                                                               @RequestParam(value = "scenario") String scenarioType) throws Exception {



        return new ResponseEntity<>(null,HttpStatus.OK);
    }
}
