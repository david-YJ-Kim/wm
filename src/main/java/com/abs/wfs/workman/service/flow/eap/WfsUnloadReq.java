package com.abs.wfs.workman.service.flow.eap;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsProdInspJdgmIvo;
import com.abs.wfs.workman.spec.in.eap.WfsUnloadReqIvo;

public interface WfsUnloadReq extends WfsMessageService {


    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsUnloadReqIvo wfsUnloadReqIvo) throws Exception;
}
