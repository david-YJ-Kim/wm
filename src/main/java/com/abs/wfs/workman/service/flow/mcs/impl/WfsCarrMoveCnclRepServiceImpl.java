package com.abs.wfs.workman.service.flow.mcs.impl;

import com.abs.wfs.workman.service.flow.mcs.WfsCarrMoveCnclRep;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.mcs.WfsCarrMoveCnclRepIvo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class WfsCarrMoveCnclRepServiceImpl implements WfsCarrMoveCnclRep {

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, String tid) {
        return null;
    }

    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsCarrMoveCnclRepIvo wfsCarrMoveCnclRepIvo) throws Exception {
        return null;
    }
}
