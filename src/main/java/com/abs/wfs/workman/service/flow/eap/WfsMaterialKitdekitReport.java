package com.abs.wfs.workman.service.flow.eap;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsMaterialChangeReqIvo;
import com.abs.wfs.workman.spec.in.eap.WfsMaterialKitdekitReportIvo;

public interface WfsMaterialKitdekitReport extends WfsMessageService {


    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsMaterialKitdekitReportIvo wfsMaterialKitdekitReportIvo) throws Exception;
}
