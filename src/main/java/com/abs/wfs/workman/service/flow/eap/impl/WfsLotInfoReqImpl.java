package com.abs.wfs.workman.service.flow.eap.impl;


import com.abs.wfs.workman.dao.query.work.service.WorkService;
import com.abs.wfs.workman.dao.query.work.vo.WorkJobLotQueryDto;
import com.abs.wfs.workman.service.flow.eap.WfsLotInfoReq;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsLotInfoReqIvo;
import com.abs.wfs.workman.util.code.ApStringConstant;
import com.abs.wfs.workman.util.exception.ScenarioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class WfsLotInfoReqImpl implements WfsLotInfoReq {

    @Autowired
    WorkService workService;

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
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsLotInfoReqIvo wfsLotInfoReqIvo) throws Exception {

        WfsLotInfoReqIvo.WfsLotInfoReqBody body = wfsLotInfoReqIvo.getBody();


        Optional<WorkJobLotQueryDto> lotQueryResult = this.workService.searchWorkJobLotQueryDto(
                                                WorkJobLotQueryDto.builder()
                                                        .siteId(body.getSiteId())
                                                        .jobStatCd(ApStringConstant.Active)
                                                        .workId(body.getWorkId())
                                                        .inCarrId(body.getCarrId())
                                                        .build());

        if(!lotQueryResult.isPresent()) { // TODO  NoQueryResult Abnormal Case
        }




        return null;
    }

}
