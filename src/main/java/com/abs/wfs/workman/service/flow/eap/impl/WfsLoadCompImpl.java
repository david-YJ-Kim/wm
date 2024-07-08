package com.abs.wfs.workman.service.flow.eap.impl;


import com.abs.wfs.workman.dao.query.service.WfsCommonQueryService;
import com.abs.wfs.workman.dao.query.service.vo.UpdatePortStatCdRequestVo;
import com.abs.wfs.workman.service.flow.eap.WfsLoadComp;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.WfsLoadCompIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import com.abs.wfs.workman.util.code.PortStatCd;
import com.abs.wfs.workman.util.code.TrsfStatCd;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WfsLoadCompImpl implements WfsLoadComp {


    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, String tid) {


        return ApFlowProcessVo.builder()
                .eventName(cid)
                .trackingKey(trackingKey)
                .scenarioType(scenarioType)
                .executeStartTime(System.currentTimeMillis())
                .tid(tid)
                .build();
    }



    @Autowired
    WfsCommonQueryService wfsCommonQueryService;

    /**
     * Load Comp 보고 되면, 포트 기준으로 상태 업데이트
     * @param apFlowProcessVo
     * @param wfsLoadCompIvo
     * @return
     * @throws Exception
     */
    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsLoadCompIvo wfsLoadCompIvo) throws Exception {

        WfsLoadCompIvo.WfsLoadCompBody body = wfsLoadCompIvo.getBody();

        int updatePortStatCdRowCount = this.wfsCommonQueryService.updatePortStatCd(UpdatePortStatCdRequestVo.builder()
                                            .siteId(body.getSiteId()).cid(apFlowProcessVo.getEventName()).tid(apFlowProcessVo.getTid()).userId(ApSystemCodeConstant.WFS)
                                            .statCd(PortStatCd.Occupied.name()).trsfStatCd(TrsfStatCd.LoadCompleted.name()).acesModeCd("").ctrlModeCd("")
                                            .portId(body.getPortId()).eqpId(body.getEqpId())
                                            .build());
        log.info("Complete Port stat. row count : {}", updatePortStatCdRowCount);


        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
    }

}
