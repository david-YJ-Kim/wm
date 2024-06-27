package com.abs.wfs.workman.service.flow.eap;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsDurableInfoRepIvo;
import com.abs.wfs.workman.spec.in.eap.WfsPortStateReportIvo;

public interface WfsDurableInfoRep extends WfsMessageService {


    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsDurableInfoRepIvo wfsDurableInfoRepIvo) throws Exception;
}
