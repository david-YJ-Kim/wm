package com.abs.wfs.workman.service.flow.eap;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsPortStateReportIvo;
import com.abs.wfs.workman.spec.in.eap.WfsProdDcollReportIvo;

public interface WfsPortStateReport extends WfsMessageService {


    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsPortStateReportIvo wfsPortStateReportIvo) throws Exception;
}
