package com.abs.wfs.workman.service.flow.oi.impl;

import com.abs.wfs.workman.service.flow.oi.WfsSorterModeChgOi;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.oia.WfsSorterModeChgOiIvo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WfsSorterModeChgOiServiceImpl implements WfsSorterModeChgOi {
    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return null;
    }

    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsSorterModeChgOiIvo wfsSorterModeChgOiIvo) throws Exception {
        return null;
    }
}
