package com.abs.wfs.workman.service.flow.eap;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsPanelMoveInIvo;

public interface WfsPanelMoveIn extends WfsMessageService {

    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsPanelMoveInIvo wfsPanelMoveInIvo) throws Exception;
}
