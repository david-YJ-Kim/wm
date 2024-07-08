package com.abs.wfs.workman.service.flow.eap.impl;


import com.abs.wfs.workman.dao.query.carr.service.CarrLocationServiceImpl;
import com.abs.wfs.workman.dao.query.carr.vo.CarrMcsLocationDto;
import com.abs.wfs.workman.dao.query.eqp.service.EqpServiceImpl;
import com.abs.wfs.workman.dao.query.eqp.vo.UpdatePortStatAndCarrierDto;
import com.abs.wfs.workman.service.flow.eap.WfsInitPortStateReport;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsInitPortStateReportIvo;
import com.abs.wfs.workman.spec.in.eap.common.PortInfoVo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.ApStringConstant;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WfsInitPortStateReportImpl implements WfsInitPortStateReport {

    @Autowired
    CarrLocationServiceImpl carrLocationService;

    @Autowired
    EqpServiceImpl eqpService;

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, String tid) {
        ApFlowProcessVo apFlowProcessVo = ApFlowProcessVo.builder()
                .eventName(cid)
                .trackingKey(trackingKey)
                .scenarioType(scenarioType)
                .executeStartTime(System.currentTimeMillis())
                .build();

        log.info("Ready to process flow. ProcessVo: {}", apFlowProcessVo);
        return apFlowProcessVo;

    }

    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsInitPortStateReportIvo wfsInitPortStateReportIvo) throws Exception {

        List<PortInfoVo> portStataList = wfsInitPortStateReportIvo.getBody().getPortInfo();

        WfsInitPortStateReportIvo.WfsInitPortStateReportBody  body = wfsInitPortStateReportIvo.getBody();
        apFlowProcessVo.setApMsgBody(body);


        if(portStataList != null && !portStataList.isEmpty()) {
            for(PortInfoVo portInfoVo : portStataList) {

                CarrMcsLocationDto resultCarrMcsLocationDto = this.carrLocationService.carrMcsLocationQuery(
                        CarrMcsLocationDto.builder()
                                .pMachinename(body.getEqpId())
                                .pName(portInfoVo.getPortId())
                                .pUnitkind(ApStringConstant.PORT)
                                .build()
                );

                String carrId = (resultCarrMcsLocationDto != null) ? resultCarrMcsLocationDto.getCarrId() : null;

                log.info("data query carrId: {}, result: {}", carrId, resultCarrMcsLocationDto);

                this.eqpService.updatePortStatAndCarrier(UpdatePortStatAndCarrierDto.builder()
                        .siteId(body.getSiteId())
                        .cid(apFlowProcessVo.getEventName())
                        .tid(wfsInitPortStateReportIvo.getHead().getTid())
                        .userId(body.getUserId())
                        .efemCommStateCd(body.getEqpCommStateCd())
                        .efemStateCd(body.getEqpStateCd())
                        .efemToolStateCd(body.getEfemToolStateCd())
                        .statCd(portInfoVo.getPortStateCd())
                        .trsfStatCd(portInfoVo.getTrsfStatCd())
                        .acesModeCd(portInfoVo.getPortAutoModeFlag())
                        .ctrlModeCd(carrId)
                        .eqpId(body.getEqpId())
                        .portId(portInfoVo.getPortId())
                        .build());
            }
        }



        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
    }

}
