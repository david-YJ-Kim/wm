package com.abs.wfs.workman.service.flow.eap;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsLotInfoReqIvo;
import com.abs.wfs.workman.spec.in.eap.WfsScrapReportIvo;

public interface WfsLotInfoReq extends WfsMessageService {


    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsLotInfoReqIvo wfsLotInfoReqIvo) throws Exception;
}
