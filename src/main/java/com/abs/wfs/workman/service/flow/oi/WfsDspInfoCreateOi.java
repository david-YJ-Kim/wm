package com.abs.wfs.workman.service.flow.oi;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.oia.WfsDspInfoCreateOiIvo;

public interface WfsDspInfoCreateOi extends WfsMessageService {
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsDspInfoCreateOiIvo wfsDspInfoCreateOiIvo) throws Exception;
}
