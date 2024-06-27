package com.abs.wfs.workman.service.flow.eap.impl;


import com.abs.wfs.workman.config.ApPropertyObject;
import com.abs.wfs.workman.dao.domain.posEqpGrpRel.model.CnPosEqpGrpRel;
import com.abs.wfs.workman.dao.domain.posEqpGrpRel.service.CnPosEqpGrpRelServiceImpl;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.eap.WfsEqpStateReport;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsEqpStateReportIvo;
import com.abs.wfs.workman.spec.out.brs.BrsEqpControlModeChangeIvo;
import com.abs.wfs.workman.spec.out.brs.BrsEqpStateChangeIvo;
import com.abs.wfs.workman.util.code.ApEqpGrpCodeConstant;
import com.abs.wfs.workman.util.code.UseStatCd;
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


    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType) {

        ApFlowProcessVo apFlowProcessVo = ApFlowProcessVo.builder()
                .eventName(cid)
                .trackingKey(trackingKey)
                .scenarioType(scenarioType)
                .executeStartTime(System.currentTimeMillis())
                .build();
        return apFlowProcessVo;
    }


    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsEqpStateReportIvo wfsEqpStateReportIvo) throws Exception {


        BrsEqpStateChangeIvo.BrsEqpStateChangeBody modeChangeBody = new BrsEqpStateChangeIvo.BrsEqpStateChangeBody();
        modeChangeBody.setSiteId(wfsEqpStateReportIvo.getBody().getSiteId());
        modeChangeBody.setEqpId(wfsEqpStateReportIvo.getBody().getEqpId());
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

            // TODO CALL EFEM  MODE
        }

        apFlowProcessVo.setExecuteEndTime(System.currentTimeMillis());

        return apFlowProcessVo;

    }
}
