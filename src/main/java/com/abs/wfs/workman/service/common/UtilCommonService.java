package com.abs.wfs.workman.service.common;


import com.abs.wfs.workman.dao.domain.tnLot.model.TnPosLot;
import com.abs.wfs.workman.dao.domain.tnLot.service.TnPosLotServiceImpl;
import com.abs.wfs.workman.dao.query.dao.WorkDAO;
import com.abs.wfs.workman.dao.query.service.vo.SearchProdStartedPanelReqVo;
import com.abs.wfs.workman.dao.query.service.vo.WorkInfoQueryRequestVo;
import com.abs.wfs.workman.dao.query.wipLot.service.WipLotQueryServiceImpl;
import com.abs.wfs.workman.dao.query.wipLot.vo.WipLotDto;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.UseStatCd;
import com.abs.wfs.workman.util.exception.ApExceptionCode;
import com.abs.wfs.workman.util.exception.ScenarioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UtilCommonService {

    public void threadSleep(String sleepMs){
        Long startTm = System.currentTimeMillis();
        log.info("Thread is now sleep for {} ms", sleepMs);
        try {
            Thread.sleep(Long.valueOf(sleepMs));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Thread is now finish sleep. Elapsed sleep Ms : {}.", System.currentTimeMillis() - startTm);
    }



    @Autowired
    TnPosLotServiceImpl tnPosLotService;

    @Autowired
    WipLotQueryServiceImpl wipLotQueryService;


    @Autowired
    WorkDAO workDAO;


    /**
     *
     * @param apFlowProcessVo
     * @param slotNo
     * @param proMtrlId
     * @param mtrlFace
     * @return true: trigger start process for other side port (tool_cond_req)
     */
    public boolean JudgeContinuousInput(ApFlowProcessVo apFlowProcessVo, String slotNo, String proMtrlId, String mtrlFace){

        String siteId = apFlowProcessVo.getApMsgBody().getSiteId();
        String eqpId = apFlowProcessVo.getApMsgBody().getEqpId();
        String portId = apFlowProcessVo.getApMsgBody().getPortId();

        Optional<List<TnPosLot>> queryPortInfo = this.tnPosLotService.findBySiteIdAndEqpIdAndUseStatCd(siteId, eqpId);

        String otherPortId = null;
        if(queryPortInfo.isPresent()){
            for(TnPosLot tnPosLot: queryPortInfo.get()){
                if(!tnPosLot.getPortId().equals(portId)){otherPortId = tnPosLot.getPortId();}
            }
        }else{
            throw new ScenarioException(apFlowProcessVo, apFlowProcessVo.getApMsgBody(), ApExceptionCode.WFS_ERR_PORT_INF_UNREGISTER, null, new String[] {eqpId});
        }


        Optional<WipLotDto> resulWipLot =  this.wipLotQueryService.selectWipLotInfo(
                                            WipLotDto.builder()
                                                    .pSiteId(siteId)
                                                    .pUseStatCd(UseStatCd.Usable.name())
                                                    .pCrntEqpId(eqpId)
                                                    .pCrntPortId(proMtrlId)
                                                    .build()
        );

        if(!resulWipLot.isPresent()){
            log.info("No Lot found at other side port.");
            return true;
        }

        if(apFlowProcessVo.getEventName().equals(WorkManMessageList.WFS_WORK_ENDED)){
            Optional<List<WorkInfoQueryRequestVo>> queryIsWOrkCreated = this.workDAO.selectActiveWorkInfoQuery(WorkInfoQueryRequestVo.builder()
                            .siteId(siteId)
                            .useStatCd(UseStatCd.Usable.name())
                            .eqpId(eqpId)
                            .inPortId(otherPortId)
                            .lotId(resulWipLot.get().getLotId())
                            .build());
            if(queryIsWOrkCreated.isPresent()) {
                log.info("Work has been  created.");
                return false;

            }else{

                log.info("Work is not start at other side port.");
                return true;
            }

        }


        Optional<List<SearchProdStartedPanelReqVo>> queryProdStart = this.workDAO.searchProdStartedPanel(
                                                    SearchProdStartedPanelReqVo.builder()
                                                            .siteId(siteId)
                                                            .useStatCd(UseStatCd.Usable.name())
                                                            .lotId(apFlowProcessVo.getApMsgBody().getLotId())
                                                            .build()
        );

        if(queryProdStart.isPresent() && !queryProdStart.get().isEmpty()){
            log.info("Some panel need to be started.");
            return false;
        }else{
            log.info("All panel is started.");
            return true;
        }


    }
}
