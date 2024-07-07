package com.abs.wfs.workman.service.flow.eap;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsProcEndedIvo;
import com.abs.wfs.workman.spec.in.eap.WfsProcStartedIvo;

public interface WfsProcStarted extends WfsMessageService {


    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsProcStartedIvo wfsProcStartedIvo) throws Exception;

}
