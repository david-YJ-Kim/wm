package com.abs.wfs.workman.service.flow.rms.impl;

import com.abs.wfs.workman.service.flow.rms.WfsRecipeValidateRep;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.rms.WfsRecipeValidateRepIvo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WfsRecipeValidateRepServiceImpl implements WfsRecipeValidateRep {
    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, String tid) {
        return null;
    }

    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsRecipeValidateRepIvo wfsRecipeValidateRepIvo) throws Exception {
        return null;
    }
}
