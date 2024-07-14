package com.abs.wfs.workman.service.flow.eap.impl;

import com.abs.wfs.workman.dao.domain.wipStat.model.WnWipStat;
import com.abs.wfs.workman.dao.domain.wipStat.service.WipStatServiceImpl;
import com.abs.wfs.workman.dao.query.model.QueryPortVO;
import com.abs.wfs.workman.dao.query.service.WfsCommonQueryService;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.common.staterule.StateRuleManager;
import com.abs.wfs.workman.service.flow.eap.WfsLoadReq;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.eap.WfsLoadReqIvo;
import com.abs.wfs.workman.spec.out.rtd.RtdDspWorkReqIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.ApStringConstant;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import com.abs.wfs.workman.util.code.RtdDspTyp;
import com.abs.wfs.workman.util.code.StateRuleList;
import com.abs.wfs.workman.util.exception.ApExceptionCode;
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




    @Autowired
    StateRuleManager stateRuleManager;

    @Autowired
    WipStatServiceImpl wipStatService;

    @Autowired
    MessageSendService messageSendService;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;


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


        Optional<List<WnWipStat>> resvWipQuery = this.wipStatService.findByResvEqpIdAndResvPortIdAndUseStatCd(siteId, eqpId, portId);
        if(resvWipQuery.isPresent()) {
            for(WnWipStat wnWipStat : resvWipQuery.get()) {
                if(!wnWipStat.getLotId().equals("-") && !wnWipStat.getCarrId().equals("-")) {
                    log.error("Other carr: {} is reserved with eqp ({}) and port ({}).", wnWipStat.getCarrId(), wnWipStat.getResvEqpId(), wnWipStat.getResvPortId());
                    throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_LOT_RESV_ALREADY_REGISTER, wfsLoadReqIvo.getHead().getLang(),
                                                new String[] {eqpId, portId, wnWipStat.getLotId(), wnWipStat.getCarrId()});
                }
            }

        }

        RtdDspWorkReqIvo.RtdDspWorkReqBody rtdDspWorkReqBody = new RtdDspWorkReqIvo.RtdDspWorkReqBody();
        rtdDspWorkReqBody.setSiteId(siteId); rtdDspWorkReqBody.setEqpId(eqpId); rtdDspWorkReqBody.setPortId(portId);


        // Message send based on Port Type.
        switch (queryPortVO.getPortTyp()){
            case ApStringConstant.IP:
            case ApStringConstant.BP:
                rtdDspWorkReqBody.setDspType(RtdDspTyp.LOT.name());
                break;

            case ApStringConstant.OP:
                rtdDspWorkReqBody.setDspType(RtdDspTyp.CARR.name());
                break;

            default:
                throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_PORT_TYP_UNMATCHED, wfsLoadReqIvo.getHead().getLang(),
                                            new String[] {queryPortVO.getPortTyp()});

        }

        log.info("Port Type({}), Request dispatch type : {}", queryPortVO.getPortTyp(), rtdDspWorkReqBody.getDspType());
        this.messageSendService.sendMessageSend(RtdDspWorkReqIvo.system, RtdDspWorkReqIvo.cid,
                this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), rtdDspWorkReqBody));


        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);



    }


    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return  WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }
}
