package com.abs.wfs.workman.service.flow.brs;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.brs.WfsManualWorkStartIvo;
import com.abs.wfs.workman.spec.in.brs.WfsNpgProcReqIvo;

public interface WfsNpgProcReq  extends WfsMessageService {

    /**
     *
     * @param apFlowProcessVo
     * @param wfsNpgProcReqIvo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsNpgProcReqIvo wfsNpgProcReqIvo) throws Exception;
}
