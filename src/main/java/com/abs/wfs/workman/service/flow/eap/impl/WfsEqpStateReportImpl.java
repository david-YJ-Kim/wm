package com.abs.wfs.workman.service.flow.eap.impl;


import com.abs.wfs.workman.config.ApPropertyObject;
import com.abs.wfs.workman.dao.domain.posEqpGrpRel.model.CnPosEqpGrpRel;
import com.abs.wfs.workman.dao.domain.posEqpGrpRel.service.CnPosEqpGrpRelServiceImpl;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.eap.WfsEqpStateReport;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsEfemControlStateReportIvo;
import com.abs.wfs.workman.spec.in.eap.WfsEqpStateReportIvo;
import com.abs.wfs.workman.spec.out.brs.BrsEqpControlModeChangeIvo;
import com.abs.wfs.workman.spec.out.brs.BrsEqpStateChangeIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApEqpGrpCodeConstant;
import com.abs.wfs.workman.util.code.UseStatCd;
import com.abs.wfs.workman.util.exception.ScenarioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class WfsEqpStateReportImpl implements WfsEqpStateReport {




    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, String tid) {

        return ApFlowProcessVo.builder()
                .eventName(cid)
                .trackingKey(trackingKey)
                .scenarioType(scenarioType)
                .executeStartTime(System.currentTimeMillis())
                .build();
    }

    @Autowired
    MessageSendService messageSendService;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;

    @Autowired
    CnPosEqpGrpRelServiceImpl cnPosEqpGrpRelService;

    @Autowired
    WfsEfemControlStateReportImpl wfsEfemControlStateReport;



    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsEqpStateReportIvo wfsEqpStateReportIvo) throws Exception {

        WfsEqpStateReportIvo.Body body = wfsEqpStateReportIvo.getBody();
        String siteId = body.getSiteId(); String eqpId = body.getEqpId(); String portId = body.getPortId();

        apFlowProcessVo.setApMsgBody(wfsEqpStateReportIvo.getBody());


        BrsEqpStateChangeIvo.BrsEqpStateChangeBody modeChangeBody = new BrsEqpStateChangeIvo.BrsEqpStateChangeBody();
        modeChangeBody.setSiteId(siteId);
        modeChangeBody.setEqpId(eqpId);
        modeChangeBody.setStatCd(wfsEqpStateReportIvo.getBody().getEqpStateCd());
        modeChangeBody.setUserId(wfsEqpStateReportIvo.getBody().getUserId());


        this.messageSendService.sendMessageSend(BrsEqpControlModeChangeIvo.system,
                BrsEqpControlModeChangeIvo.cid,
                this.apPayloadGenerateService.generateBody(wfsEqpStateReportIvo.getHead().getTid(), modeChangeBody) );


        Optional<CnPosEqpGrpRel> cnPosEqpGrpRel =  this.cnPosEqpGrpRelService.findBySiteIdAndUseStatCdAndEqpGrpIdAndEqpId(
                ApPropertyObject.getInstance().getSiteName(), UseStatCd.Usable,
                ApEqpGrpCodeConstant.EfemSpecial,
                wfsEqpStateReportIvo.getBody().getEqpId());


        if(cnPosEqpGrpRel.isPresent()){
            log.info("EFEM Special tool. use same as efem control mode.");


            WfsEfemControlStateReportIvo.Body efemBody = new WfsEfemControlStateReportIvo().getBody();
            efemBody.setSiteId(siteId); efemBody.setEqpId(eqpId); efemBody.setUserId(body.getUserId());
            efemBody.setEqpCommStateCd(body.getEqpStateCd()); efemBody.setPortType(WorkManCommonUtil.extractPortTypWithPortId(portId));

            try{
                ApFlowProcessVo efemControlVo = this.wfsEfemControlStateReport.execute(this.wfsEfemControlStateReport.initialize(WorkManMessageList.WFS_EFEM_STATE_REPORT, apFlowProcessVo.getTrackingKey(), apFlowProcessVo.getScenarioType(), apFlowProcessVo.getTid()),
                                                                                        this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), efemBody, true));
                WorkManCommonUtil.completeFlowProcessVo(efemControlVo);

            } catch (Exception e){
                throw e;
            }

        }


        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);

    }
}
