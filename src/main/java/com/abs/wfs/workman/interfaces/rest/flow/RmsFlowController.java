package com.abs.wfs.workman.interfaces.rest.flow;

import com.abs.wfs.workman.service.flow.rms.impl.WfsRecipeValidateRepServiceImpl;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.rms.WfsRecipeValidateRepIvo;
import com.abs.wfs.workman.util.WorkManMessageList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/flow/rms/")
@RequiredArgsConstructor
public class RmsFlowController {
    @Autowired
    WfsRecipeValidateRepServiceImpl wfsRecipeValidateRepService;

    @PostMapping(WorkManMessageList.WFS_RECIPE_VALIDATE_REP)
    public ApFlowProcessVo execute(@RequestBody WfsRecipeValidateRepIvo wfsRecipeValidateRepIvo,
                                   @RequestParam(value = "key") String trackingKey,
                                   @RequestParam(value = "scenario") String scenarioType) throws Exception{

        String cid = WorkManMessageList.WFS_RECIPE_VALIDATE_REP;
        ApFlowProcessVo apFlowProcessVo = this.wfsRecipeValidateRepService.initialize(cid, trackingKey, scenarioType, wfsRecipeValidateRepIvo.getHead());

        return this.wfsRecipeValidateRepService.execute(apFlowProcessVo,wfsRecipeValidateRepIvo);

    }

}
