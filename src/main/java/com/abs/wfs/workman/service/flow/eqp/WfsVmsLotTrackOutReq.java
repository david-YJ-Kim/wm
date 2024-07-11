package com.abs.wfs.workman.service.flow.eqp;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eqp.WfsVmsLotTrackOutReqIvo;

public interface WfsVmsLotTrackOutReq extends WfsMessageService {

    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsVmsLotTrackOutReqIvo wfsVmsLotTrackOutReqIvo) throws Exception;
}
