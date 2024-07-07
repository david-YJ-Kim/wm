package com.abs.wfs.workman.service.flow.eap;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsVmsProcEndedIvo;
import com.abs.wfs.workman.spec.in.eap.WfsVmsProcStartedIvo;

public interface WfsVmsProcStarted extends WfsMessageService {


    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsVmsProcStartedIvo wfsVmsProcStartedIvo) throws Exception;

}
