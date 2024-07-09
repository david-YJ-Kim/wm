package com.abs.wfs.workman.service.flow.rtd;

import com.abs.wfs.workman.service.flow.WfsMessageService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.rtd.WfsDspWorkRepIvo;

public interface WfsDspWorkRep extends WfsMessageService {


    /**
     *
     * @param apFlowProcessVo
     * @return
     * @throws Exception
     */
    ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsDspWorkRepIvo wfsDspWorkRepIvo) throws Exception;

}
