package com.abs.wfs.workman.service.flow.oi.impl;

import com.abs.wfs.workman.service.flow.oi.WfsJobAbortOi;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.oia.WfsDspInfoCreateOiIvo;
import com.abs.wfs.workman.spec.in.oia.WfsJobAbortOiIvo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WfsJobAbortOiServiceImpl implements WfsJobAbortOi {
    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return null;
    }

    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsJobAbortOiIvo wfsJobAbortOiIvo) throws Exception {
        return null;
    }
}
