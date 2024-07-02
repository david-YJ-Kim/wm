package com.abs.wfs.workman.service.flow.mcs;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.mcs.WfsInvtCarrDataIvo;

public interface WfsInvtCarrData extends WfsMessageService {

    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsInvtCarrDataIvo wfsInvtCarrDataIvo) throws Exception;

}
