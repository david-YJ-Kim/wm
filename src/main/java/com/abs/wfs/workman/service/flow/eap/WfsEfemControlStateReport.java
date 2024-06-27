package com.abs.wfs.workman.service.flow.eap;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsEfemControlStateReportIvo;
import com.abs.wfs.workman.spec.in.eap.WfsInitDurableInfoReportIvo;

public interface WfsEfemControlStateReport extends WfsMessageService {


    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsEfemControlStateReportIvo wfsEfemControlStateReportIvo) throws Exception;
}
