package com.abs.wfs.workman.service.flow.mcs;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.mcs.WfsCarrDataRepIvo;

public interface WfsCarrDataRep extends WfsMessageService {
    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsCarrDataRepIvo wfsCarrDataRepIvo) throws Exception;

}
