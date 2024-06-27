package com.abs.wfs.workman.service.flow.brs;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.brs.WfsManualWorkStartIvo;

public interface WfsManualWorkStart extends WfsMessageService {

    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsManualWorkStartIvo wfsManualWorkStartIvo) throws Exception;
}
