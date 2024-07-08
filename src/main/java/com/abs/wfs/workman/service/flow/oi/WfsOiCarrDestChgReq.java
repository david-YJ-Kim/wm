package com.abs.wfs.workman.service.flow.oi;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.oia.WfsOiCarrDestChgReqIvo;
import com.abs.wfs.workman.spec.in.oia.WfsOiCarrMoveCrtIvo;

public interface WfsOiCarrDestChgReq extends WfsMessageService {

    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsOiCarrDestChgReqIvo wfsOiCarrDestChgReqIvo) throws Exception;
}
