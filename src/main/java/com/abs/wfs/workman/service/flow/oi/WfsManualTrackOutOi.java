package com.abs.wfs.workman.service.flow.oi;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.oia.WfsDspInfoCreateOiIvo;
import com.abs.wfs.workman.spec.in.oia.WfsManualTrackOutOiIvo;

public interface WfsManualTrackOutOi extends WfsMessageService {
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsManualTrackOutOiIvo wfsManualTrackOutOiIvo) throws Exception;
}
