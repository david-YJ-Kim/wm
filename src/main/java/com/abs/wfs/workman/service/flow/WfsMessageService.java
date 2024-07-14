package com.abs.wfs.workman.service.flow;


import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;

public interface WfsMessageService {


    /**
     * ApFlowProcessVo: 이벤트를
     */


    /**
     *
     * @param cid
     * @return
     */
    ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead);


}