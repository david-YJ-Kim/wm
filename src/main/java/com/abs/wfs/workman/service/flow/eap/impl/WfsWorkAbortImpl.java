package com.abs.wfs.workman.service.flow.eap.impl;


import com.abs.wfs.workman.dao.domain.wipStat.model.WnWipStat;
import com.abs.wfs.workman.dao.domain.wipStat.service.WipStatServiceImpl;
import com.abs.wfs.workman.dao.domain.workJob.model.WnWorkJob;
import com.abs.wfs.workman.dao.domain.workJob.service.WnWorkJobServiceImpl;
import com.abs.wfs.workman.dao.domain.workJobSlotInfo.model.WnWorkJobSlotInfo;
import com.abs.wfs.workman.dao.domain.workJobSlotInfo.service.WnWorkJobSlotInfoServiceImpl;
import com.abs.wfs.workman.dao.domain.workStat.model.WnWorkStat;
import com.abs.wfs.workman.dao.domain.workStat.service.WnWorkStatServiceImpl;
import com.abs.wfs.workman.service.flow.eap.WfsWorkAbort;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.eap.WfsWorkAbortIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.SelfInspectionCd;
import com.abs.wfs.workman.util.code.UseYn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WfsWorkAbortImpl implements WfsWorkAbort {


    @Autowired
    WnWorkStatServiceImpl wnWorkStatService;

    @Autowired
    WnWorkJobSlotInfoServiceImpl wnWorkJobSlotInfoService;

    @Autowired
    WnWorkJobServiceImpl wnWorkJobService;

    @Autowired
    WipStatServiceImpl wipStatService;


    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsWorkAbortIvo wfsWorkAbortIvo) throws Exception {

        WfsWorkAbortIvo.WfsWorkAbortBody body = wfsWorkAbortIvo.getBody();

        String siteId = body.getSiteId();  String workId = body.getWorkId();

        Optional<WnWorkStat> SelectWorkStat =  this.wnWorkStatService.findByActiveAndUsableWorkWithWorkId(siteId, workId);

        if(!SelectWorkStat.isPresent()) { throw new Exception("");} // TODO $GetAbnormalCd/NoWorkActive


        Optional<WnWorkJob> SelectWorkJobQuery = this.wnWorkJobService.findByWorkIdAndSiteIdAndJobStatCdAndUseStatCd(workId, siteId);

        Optional<WnWipStat> SelfInspYn = this.wipStatService.findByLotIdAndSiteIdAndUseStatCd(body.getLotId(), siteId);

        Optional<List<WnWorkJobSlotInfo>> ProdStartQuery =
                    this.wnWorkJobSlotInfoService.findByWorkIdAndSiteIdAndUseStatCdAndProdMtrlStrtTmIsNotNull(workId, siteId);

        Optional<List<WnWorkJobSlotInfo>> selectSelfInspSlot =
                this.wnWorkJobSlotInfoService.findByWorkIdAndSiteIdAndUseStatCdAndSelfInspYn(workId, siteId);


        if(SelfInspYn.isPresent()) {

            if(SelfInspYn.get().getSelfInspYn().equals(UseYn.Y) && SelectWorkStat.get().getRsnCd().equals(SelfInspectionCd.ONE_MORE)){

                log.info("Self Inspection one  more log.");

                return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);
            }


        }
        // TODO 임시
        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);

    }

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return  WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }
}
