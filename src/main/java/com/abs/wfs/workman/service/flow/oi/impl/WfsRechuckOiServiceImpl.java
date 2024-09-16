package com.abs.wfs.workman.service.flow.oi.impl;

import com.abs.wfs.workman.service.flow.oi.WfsRechuckOi;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.oia.WfsDspInfoCreateOiIvo;
import com.abs.wfs.workman.spec.in.oia.WfsRechuckOiIvo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WfsRechuckOiServiceImpl implements WfsRechuckOi {
    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return null;
    }

    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsRechuckOiIvo wfsRechuckOiIvo) throws Exception {
        return null;
    }
}
