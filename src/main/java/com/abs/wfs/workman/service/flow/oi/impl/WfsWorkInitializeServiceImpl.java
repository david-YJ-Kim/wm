package com.abs.wfs.workman.service.flow.oi.impl;

import com.abs.wfs.workman.service.flow.oi.WfsWorkInitialize;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.oia.WfsWorkInitializeIvo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WfsWorkInitializeServiceImpl implements WfsWorkInitialize {
    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsWorkInitializeIvo wfsWorkInitializeIvo) throws Exception {
        return null;
    }

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return null;
    }
}
