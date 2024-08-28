package com.abs.wfs.workman.service.flow.eap;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsAlarmReportIvo;
import com.abs.wfs.workman.spec.in.eap.WfsToolVerReportIvo;

public interface WfsToolVerReport extends WfsMessageService {

    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsToolVerReportIvo wfsToolVerReportIvo) throws Exception;
}
