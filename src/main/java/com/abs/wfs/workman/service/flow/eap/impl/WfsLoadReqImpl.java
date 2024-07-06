package com.abs.wfs.workman.service.flow.eap.impl;

import com.abs.wfs.workman.dao.domain.wipStat.model.WnWipStat;
import com.abs.wfs.workman.dao.domain.wipStat.service.WipStatServiceImpl;
import com.abs.wfs.workman.dao.query.model.QueryPortVO;
import com.abs.wfs.workman.dao.query.service.WfsCommonQueryService;
import com.abs.wfs.workman.service.common.staterule.StateRuleManager;
import com.abs.wfs.workman.service.flow.eap.WfsLoadReq;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsLoadReqIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.ApStringConstant;
import com.abs.wfs.workman.util.code.StateRuleList;
import com.abs.wfs.workman.util.code.WorkManScenarioList;
import com.abs.wfs.workman.util.exception.ScenarioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class WfsLoadReqImpl implements WfsLoadReq {

    @Autowired
    WfsCommonQueryService wfsCommonQueryService;

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, String tid) {
        ApFlowProcessVo apFlowProcessVo = ApFlowProcessVo.builder()
                .eventName(cid)
                .trackingKey(trackingKey)
                .scenarioType(scenarioType)
                .executeStartTime(System.currentTimeMillis())
                .tid(tid)
                .build();


        return apFlowProcessVo;
    }



    @Autowired
    StateRuleManager stateRuleManager;

    @Autowired
    WipStatServiceImpl wipStatService;


    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsLoadReqIvo wfsLoadReqIvo) throws Exception {

        WfsLoadReqIvo.WfsLoadReqBody body = wfsLoadReqIvo.getBody();

        String siteId = body.getSiteId(); String eqpId = body.getEqpId(); String portId = body.getPortId();

        QueryPortVO queryPortVO = this.wfsCommonQueryService.getQueryPortVO(siteId, eqpId, portId);

        List<Runnable> validatePortTasks = Arrays.asList(
                () -> this.stateRuleManager.validatePortStateRule(siteId, eqpId, portId, StateRuleList.ValidPort, queryPortVO),
                () -> this.stateRuleManager.validatePortStateRule(siteId, eqpId, portId, StateRuleList.FullAutoPort, queryPortVO)
        );


        WorkManCommonUtil.executeAsyncTasks(validatePortTasks);


        Optional<List<WnWipStat>> resvWipQuery = this.wipStatService.findByResvEqpIdAndResvPortIdAndUseStatCd(body.getSiteId(), body.getEqpId(), body.getPortId());
        if(resvWipQuery.isPresent()) {
            for(WnWipStat wnWipStat : resvWipQuery.get()) {
                if(!wnWipStat.getLotId().equals("-") && !wnWipStat.getCarrId().equals("-")) {
                    log.error("Other carr: {} is reserved with eqp ({}) and port ({}).", wnWipStat.getCarrId(), wnWipStat.getResvEqpId(), wnWipStat.getResvPortId());
                    throw new ScenarioException();  // TODO "ReservedLotAlreadyExist"
                }
            }

        }


        // Message send based on Port Type.
        switch (queryPortVO.getPortTyp()){
            case ApStringConstant.BP:
                // TODO SEND DSP REQ with  LOT
                break;

            case ApStringConstant.OP:
                // TODO SEND DSP REQ with  CARR
                break;

            case ApStringConstant.IP:
                // TODO SEND DSP REQ with  LOT
                break;

            default:
                throw new ScenarioException(); // TODO $GetAbnormalCd/UnmatchedPortTyp

        }



        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);



    }




}
