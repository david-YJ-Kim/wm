package com.abs.wfs.workman.service.flow.mcs.impl;

import com.abs.wfs.workman.service.flow.mcs.WfsEqpStatChg;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.mcs.WfsEqpStatChgIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WfsEqpStatChgServiceImpl implements WfsEqpStatChg {

    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsEqpStatChgIvo wfsEqpStatChgIvo) throws Exception {
        return null;
    }

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return  WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }
}
