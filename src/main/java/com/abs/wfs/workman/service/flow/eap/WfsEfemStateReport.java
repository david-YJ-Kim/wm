package com.abs.wfs.workman.service.flow.eap;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsEfemStateReportIvo;
import com.abs.wfs.workman.spec.in.eap.WfsInitDurableInfoReportIvo;

public interface WfsEfemStateReport extends WfsMessageService {


    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsEfemStateReportIvo wfsEfemStateReportIvo) throws Exception;
}
