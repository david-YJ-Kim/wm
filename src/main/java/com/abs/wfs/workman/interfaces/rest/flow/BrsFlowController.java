package com.abs.wfs.workman.interfaces.rest.flow;


import com.abs.wfs.workman.service.flow.brs.impl.WfsManualWorkStartImpl;
import com.abs.wfs.workman.service.flow.brs.impl.WfsNpgProcReqImpl;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.brs.WfsManualWorkStartIvo;
import com.abs.wfs.workman.spec.in.brs.WfsNpgProcReqIvo;
import com.abs.wfs.workman.util.WorkManMessageList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/flow/brs/")
@RequiredArgsConstructor
public class BrsFlowController {

    @Autowired
    WfsManualWorkStartImpl wfsManualWorkStart;

    @Autowired
    WfsNpgProcReqImpl wfsNpgProcReq;

    @PostMapping(WorkManMessageList.WFS_MANUAL_WORK_START)
    public ApFlowProcessVo executeWfsAlarmReport(@RequestBody WfsManualWorkStartIvo wfsManualWorkStartIvo,
                                                 @RequestParam(value = "key") String trackingKey,
                                                 @RequestParam(value = "scenario") String scenarioType) throws Exception {


        String cid = WorkManMessageList.WFS_MANUAL_WORK_START;
        ApFlowProcessVo apFlowProcessVo = this.wfsManualWorkStart.initialize(cid, trackingKey, scenarioType, wfsManualWorkStartIvo.getHead());

        return this.wfsManualWorkStart.execute(apFlowProcessVo, wfsManualWorkStartIvo);

    }

    @PostMapping(WorkManMessageList.WFS_NPG_PROC_REQ)
    public ApFlowProcessVo executeWfsNpgProcReq(@RequestBody WfsNpgProcReqIvo wfsNpgProcReqIvo,
                                                @RequestParam(value = "key") String trackingKey,
                                                @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_NPG_PROC_REQ;
        ApFlowProcessVo apFlowProcessVo = this.wfsNpgProcReq.initialize(cid, trackingKey, scenarioType, wfsNpgProcReqIvo.getHead());

        return this.wfsNpgProcReq.execute(apFlowProcessVo, wfsNpgProcReqIvo);
    }


}
