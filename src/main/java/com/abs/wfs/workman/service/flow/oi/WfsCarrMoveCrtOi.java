package com.abs.wfs.workman.service.flow.oi;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.oia.WfsCarrMoveCrtOiIvo;

public interface WfsCarrMoveCrtOi extends WfsMessageService {
    /**
     *
     * @param apFlowProcessVo
     * @param wfsCarrMoveCrtOiIvo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsCarrMoveCrtOiIvo wfsCarrMoveCrtOiIvo) throws Exception;
}
