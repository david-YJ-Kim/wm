package com.abs.wfs.workman.service.flow.rms;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.rms.WfsRecipeValidateRepIvo;

public interface WfsRecipeValidateRep extends WfsMessageService {

    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsRecipeValidateRepIvo wfsRecipeValidateRepIvo) throws Exception;

}
