package com.abs.wfs.workman.service.flow.oi.impl;

import com.abs.wfs.workman.service.flow.oi.WfsDspInfoCreateOi;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.oia.WfsDspInfoCreateOiIvo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WfsDspInfoCreateOiServiceImpl implements WfsDspInfoCreateOi {
    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return null;
    }

    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsDspInfoCreateOiIvo wfsDspInfoCreateOiIvo) throws Exception {
        return null;
    }
}
