package com.abs.wfs.workman.service.flow.eap;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsProdDcollReportIvo;
import com.abs.wfs.workman.spec.in.eap.WfsScrapReportIvo;

public interface WfsScrapReport extends WfsMessageService {


    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsScrapReportIvo wfsScrapReportIvo) throws Exception;
}
