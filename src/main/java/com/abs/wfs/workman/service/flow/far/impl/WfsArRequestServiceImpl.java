package com.abs.wfs.workman.service.flow.far.impl;

import com.abs.wfs.workman.service.flow.far.WfsArRequest;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.fix.WfsArRequestIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.WorkStatCd;
import com.abs.wfs.workman.util.exception.ScenarioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 요청이 들어온 항목에 대해서만 재시도 로직 수행
 */
@Service
@Slf4j
public class WfsArRequestServiceImpl implements WfsArRequest {


    @Autowired
    StandbyArServiceImpl standbyArService;


    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsArRequestIvo ivo) throws Exception {

        WfsArRequestIvo.Body body = ivo.getBody();
        apFlowProcessVo.setApMsgBody(body);

        WorkStatCd workStat = body.getWorkStatCd();
        apFlowProcessVo.setWorkStatCd(workStat);

        switch (workStat){
            case Standby:
                apFlowProcessVo = this.standbyArService.execute(apFlowProcessVo, ivo);
                break;
            case Transfer:
                break;
            case Ready:
                break;
            case Process:
                break;
            case None:
                break;
            default:
                throw new ScenarioException();
        }

        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);

    }

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return  WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }
}
