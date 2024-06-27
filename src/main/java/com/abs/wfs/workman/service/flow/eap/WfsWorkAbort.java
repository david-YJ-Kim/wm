package com.abs.wfs.workman.service.flow.eap;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsLoadCompIvo;
import com.abs.wfs.workman.spec.in.eap.WfsWorkAbortIvo;

public interface WfsWorkAbort extends WfsMessageService {


    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsWorkAbortIvo wfsWorkAbortIvo) throws Exception;
}
