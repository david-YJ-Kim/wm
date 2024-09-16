package com.abs.wfs.workman.service.flow.oi;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.oia.WfsSorterModeChgOiIvo;

public interface WfsSorterModeChgOi extends WfsMessageService {
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsSorterModeChgOiIvo wfsSorterModeChgOiIvo) throws Exception;
}
