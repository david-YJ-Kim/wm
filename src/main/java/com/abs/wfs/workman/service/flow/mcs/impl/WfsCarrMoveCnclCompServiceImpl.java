package com.abs.wfs.workman.service.flow.mcs.impl;

import com.abs.wfs.workman.service.flow.mcs.WfsCarrMoveCnclComp;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.mcs.WfsCarrMoveCnclCompIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class WfsCarrMoveCnclCompServiceImpl implements WfsCarrMoveCnclComp {

    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsCarrMoveCnclCompIvo wfsCarrMoveCnclCompIvo) throws Exception {
        return null;
    }

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return  WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }
}
