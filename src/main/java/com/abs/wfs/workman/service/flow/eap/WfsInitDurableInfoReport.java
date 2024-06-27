package com.abs.wfs.workman.service.flow.eap;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsInitDurableInfoReportIvo;
import com.abs.wfs.workman.spec.in.eap.WfsPrecedeLotResumeIvo;

public interface WfsInitDurableInfoReport extends WfsMessageService {


    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsInitDurableInfoReportIvo wfsInitDurableInfoReportIvo) throws Exception;
}
