package com.abs.wfs.workman.service.flow.eap;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsLotTrackInCancelRepIvo;
import com.abs.wfs.workman.spec.in.eap.WfsPrecedeLotResumeIvo;

public interface WfsLotTrackInCancelRep extends WfsMessageService {


    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsLotTrackInCancelRepIvo wfsLotTrackInCancelRepIvo) throws Exception;
}
