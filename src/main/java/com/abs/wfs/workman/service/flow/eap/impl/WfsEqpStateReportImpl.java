package com.abs.wfs.workman.service.flow.eap.impl;


import com.abs.wfs.workman.config.ApPropertyObject;
import com.abs.wfs.workman.dao.domain.posEqpGrpRel.model.CnPosEqpGrpRel;
import com.abs.wfs.workman.dao.domain.posEqpGrpRel.service.CnPosEqpGrpRelServiceImpl;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.eap.WfsEqpStateReport;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
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
        apFlowProcessVo.setApMsgBody(wfsEqpStateReportIvo.getBody());

        if(body.getEqpStateCd().isEmpty()){
            log.warn("State is empty. No need to report state.");
        }

        BrsEqpStateChangeIvo.BrsEqpStateChangeBody modeChangeBody = new BrsEqpStateChangeIvo.BrsEqpStateChangeBody();
        modeChangeBody.setSiteId(body.getSiteId());
        modeChangeBody.setEqpId(body.getEqpId());
        modeChangeBody.setStatCd(wfsEqpStateReportIvo.getBody().getEqpStateCd());
        modeChangeBody.setUserId(wfsEqpStateReportIvo.getBody().getUserId());


        this.messageSendService.sendMessageSend(BrsEqpControlModeChangeIvo.system,
                BrsEqpControlModeChangeIvo.cid,
                this.apPayloadGenerateService.generateBody(wfsEqpStateReportIvo.getHead().getTid(), modeChangeBody) );



        CnPosEqpGrpRel cnPosEqpGrpRel =  this.cnPosEqpGrpRelService.findBySiteIdAndUseStatCdAndEqpGrpIdAndEqpId(
                ApPropertyObject.getInstance().getSiteName(), UseStatCd.Usable,
                ApEqpGrpCodeConstant.EfemSpecial,
                body.getEqpId());

        if(cnPosEqpGrpRel != null){
            log.info("EfemSpecial eqp: {}", cnPosEqpGrpRel);
            this.callEfemStateReport(apFlowProcessVo, body);
        }

        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);

    }

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return  WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }


    private ApFlowProcessVo callEfemStateReport(ApFlowProcessVo apFlowProcessVo, WfsEqpStateReportIvo.Body body) throws Exception {



        log.info("EFEM Special tool. use same as efem control mode.");

        // Generate Efem Control State Report.
        WfsEfemControlStateReportIvo.Body efemBody = new WfsEfemControlStateReportIvo().getBody();
        efemBody.setSiteId(body.getSiteId()); efemBody.setEqpId(body.getEqpId());
        efemBody.setUserId(body.getUserId()); efemBody.setEqpCommStateCd(body.getEqpStateCd());
        efemBody.setPortType(WorkManCommonUtil.extractPortTypWithPortId(body.getPortId()));

        WfsEfemControlStateReportIvo reportIvo = this.apPayloadGenerateService.generateMessageObject(apFlowProcessVo.getTid(), efemBody);
        try{
            ApFlowProcessVo efemControlVo = this.wfsEfemControlStateReport.execute(

                    WorkManCommonUtil.initializeProcessVo(
                            WorkManMessageList.WFS_EFEM_STATE_REPORT, apFlowProcessVo.getTrackingKey(),
                            apFlowProcessVo.getScenarioType(), reportIvo.getHead()
                    ),
                    reportIvo);

            apFlowProcessVo.addSubFlowProcess(efemControlVo);
            log.info("EFEM State report process has been added in eqp State Report. {}", apFlowProcessVo);

            WorkManCommonUtil.completeFlowProcessVo(efemControlVo);

        } catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }

        return apFlowProcessVo;

    }
}
