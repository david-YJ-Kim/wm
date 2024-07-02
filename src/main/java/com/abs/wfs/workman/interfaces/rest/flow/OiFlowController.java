package com.abs.wfs.workman.interfaces.rest.flow;


import com.abs.wfs.workman.service.flow.brs.impl.WfsManualWorkStartImpl;
import com.abs.wfs.workman.service.flow.oi.impl.WfsOiCarrMoveCrtServiceImpl;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.brs.WfsManualWorkStartIvo;
import com.abs.wfs.workman.spec.in.oia.WfsOiCarrMoveCrtIvo;
import com.abs.wfs.workman.util.WorkManMessageList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ApFlowProcessVo executeWfsAlarmReport(@RequestBody WfsOiCarrMoveCrtIvo wfsOiCarrMoveCrtIvo,
                                                 @RequestParam(value = "key") String trackingKey,
                                                 @RequestParam(value = "scenario") String scenarioType) throws Exception {


        String cid = WorkManMessageList.WFS_OI_CARR_MOVE_CRT;
        ApFlowProcessVo apFlowProcessVo = this.wfsOiCarrMoveCrtService.initialize(cid, trackingKey, scenarioType);

        return this.wfsOiCarrMoveCrtService.execute(apFlowProcessVo, wfsOiCarrMoveCrtIvo);

    }
}
