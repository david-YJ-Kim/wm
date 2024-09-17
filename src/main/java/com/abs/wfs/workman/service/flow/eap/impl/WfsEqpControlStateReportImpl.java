package com.abs.wfs.workman.service.flow.eap.impl;


import com.abs.wfs.workman.config.ApPropertyObject;
import com.abs.wfs.workman.dao.domain.posEqpGrpRel.model.CnPosEqpGrpRel;
import com.abs.wfs.workman.dao.domain.posEqpGrpRel.service.CnPosEqpGrpRelServiceImpl;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.eap.WfsEqpControlStateReport;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.eap.WfsEqpControlStateReportIvo;
import com.abs.wfs.workman.spec.out.brs.BrsEqpControlModeChangeIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WfsEqpControlStateReportImpl implements WfsEqpControlStateReport {


    @Autowired
    MessageSendService messageSendService;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;

    @Autowired
    CnPosEqpGrpRelServiceImpl cnPosEqpGrpRelService;




    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsEqpControlStateReportIvo wfsEqpControlStateReportIvo) throws Exception {

        BrsEqpControlModeChangeIvo.BrsEqpControlModeChangeBody modeChangeBody = new BrsEqpControlModeChangeIvo.BrsEqpControlModeChangeBody();
        modeChangeBody.setSiteId(wfsEqpControlStateReportIvo.getBody().getSiteId());
        modeChangeBody.setEqpId(wfsEqpControlStateReportIvo.getBody().getEqpId());
        modeChangeBody.setCtrlModeCd(wfsEqpControlStateReportIvo.getBody().getEqpCommStateCd());
        modeChangeBody.setUserId(wfsEqpControlStateReportIvo.getBody().getUserId());

        this.messageSendService.sendMessageSend(BrsEqpControlModeChangeIvo.system,
                BrsEqpControlModeChangeIvo.cid,
                this.apPayloadGenerateService.generateBrsEqpControlModeChangeIvo(modeChangeBody) );


        CnPosEqpGrpRel cnPosEqpGrpRel =  this.cnPosEqpGrpRelService.findBySiteIdAndUseStatCdAndEqpGrpIdAndEqpId(
                ApPropertyObject.getInstance().getSiteName(), UseStatCd.Usable,
                ApEqpGrpCodeConstant.EfemSpecial,
                wfsEqpControlStateReportIvo.getBody().getEqpId());

        if(cnPosEqpGrpRel != null){
            log.info("EFEM Special tool. use same as efem control mode.");

            // TODO CALL EFEM CONTROL MODE
        }

        apFlowProcessVo.setExecuteEndTime(System.currentTimeMillis());

        return apFlowProcessVo;

    }

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return  WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }
}
