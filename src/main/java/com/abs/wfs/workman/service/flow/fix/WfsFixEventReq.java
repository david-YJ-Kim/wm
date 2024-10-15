package com.abs.wfs.workman.service.flow.fix;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.fix.WfsFixEventReqIvo;

public interface WfsFixEventReq extends WfsMessageService {


    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsFixEventReqIvo fixEventReqIvo) throws Exception;

}
