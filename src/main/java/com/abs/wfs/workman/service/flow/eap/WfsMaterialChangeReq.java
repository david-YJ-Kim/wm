package com.abs.wfs.workman.service.flow.eap;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsEqpControlStateReportIvo;
import com.abs.wfs.workman.spec.in.eap.WfsMaterialChangeReqIvo;

public interface WfsMaterialChangeReq extends WfsMessageService {

    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsMaterialChangeReqIvo wfsMaterialChangeReqIvo) throws Exception;

}
