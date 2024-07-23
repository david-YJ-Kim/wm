package com.abs.wfs.workman.service.common;


import com.abs.wfs.workman.dao.domain.tnLot.model.TnPosLot;
import com.abs.wfs.workman.dao.domain.tnLot.service.TnPosLotServiceImpl;
import com.abs.wfs.workman.dao.domain.tnPort.model.TnRdsPort;
import com.abs.wfs.workman.dao.domain.tnPort.service.TnRdsPortServiceImpl;
import com.abs.wfs.workman.dao.query.dao.WorkDAO;
import com.abs.wfs.workman.dao.query.service.vo.SearchProdStartedPanelReqVo;
import com.abs.wfs.workman.dao.query.service.vo.WorkInfoQueryRequestVo;
import com.abs.wfs.workman.dao.query.wipLot.service.WipLotQueryServiceImpl;
import com.abs.wfs.workman.dao.query.wipLot.vo.WipLotDto;
import com.abs.wfs.workman.dao.query.wipLotProdMat.service.WipLotProdMatServiceImpl;
import com.abs.wfs.workman.dao.query.wipLotProdMat.vo.WipLotProdMatDto;
import com.abs.wfs.workman.service.common.vo.MeasureOutInfo;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
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

    
    /**
     * Measurement Room, Panel 배출 CST 포트 탐색
     * 1. 현재 In Port (Trau)에 링크된 기준 포트 획득
     * 2. 패널이 이전에 담겨있던 CST가 있는 포트 조회
     * 3. 기준 정보 포트와 조회한 포트 비교
     * 4. 다르면, 조회한 포트로 Work 생성
     * 5. 이전 CST에 담긴 Slot no 확인
     *
     *
     * TODO MR룸에 반전되어 들어온다면..? 반전해서 배출해야하는데...
     *
     * @param cntInPortId
     * @param trayCarrId
     * @return  Panel 배출 CST 포트
     */

    @Autowired
    TnRdsPortServiceImpl tnRdsPortService;

    @Autowired
    WipLotProdMatServiceImpl wipLotProdMatService;

    public MeasureOutInfo getMeasureOutCstPort(ApFlowProcessVo apFlowProcessVo, String siteId, String cntInPortId, String trayId){

        String linkedPortId = "";
        Optional<TnRdsPort> findBySiteIdAndPortId = this.tnRdsPortService.findBySiteIdAndPortIdAndUseStatCd(siteId, cntInPortId);
        if(!findBySiteIdAndPortId.isPresent() || !WorkManCommonUtil.nullPointCheck(findBySiteIdAndPortId.get().getLnkPortId())){
            linkedPortId = findBySiteIdAndPortId.get().getLnkPortId();

        }else{
            log.error("{} Tray port ({}) is not found Linked port.", apFlowProcessVo.printLog(), cntInPortId);
            throw  new ScenarioException(apFlowProcessVo, apFlowProcessVo.getApMsgBody(), ApExceptionCode.WFS_ERR_PORT_INF_NOTFOUND, apFlowProcessVo.getLang()
                    , new String[] {WorkManCommonUtil.extractEqpIdWithPortId(cntInPortId),cntInPortId});
        }


        
        // 이전 패널 조회
        WipLotProdMatDto wipLotProdMatDto = WipLotProdMatDto.builder()
                                                            .siteId(siteId).useStatCd(UseStatCd.Usable).carrId(trayId)
                                                            .build();
        log.info("{} Request previous panel and slot info with tray id ({}). Request vo: {}", apFlowProcessVo.printLog(), trayId, wipLotProdMatDto.toString());

        String prevSlotNo = "";
        String prevCarrId = "";
        Optional<WipLotProdMatDto> prevSlotCarrInfo = this.wipLotProdMatService.queryLotIdWithCarr(wipLotProdMatDto);
        if(!prevSlotCarrInfo.isPresent() || !WorkManCommonUtil.nullPointCheck(prevSlotCarrInfo.get().getPrevSlotNo())
                || !WorkManCommonUtil.nullPointCheck(prevSlotCarrInfo.get().getPrevCarrId())){

            prevSlotNo = prevSlotCarrInfo.get().getPrevSlotNo();
            prevCarrId = prevSlotCarrInfo.get().getPrevCarrId();

            log.info("{} get previous slot no : {} and previous carr id : {}", apFlowProcessVo.printLog(), prevSlotNo, prevCarrId);

        }else{
            log.error("{} Tray port ({}) is not found Linked port.", apFlowProcessVo.printLog(), cntInPortId);
            throw  new ScenarioException(apFlowProcessVo, apFlowProcessVo.getApMsgBody(), ApExceptionCode.WFS_ERR_PREV_SLOT_INF_NOTFOUND, apFlowProcessVo.getLang()
                    , new String[] {trayId,cntInPortId});
        }

        return MeasureOutInfo.builder().linkedPortId(linkedPortId).prevSlotNo(prevSlotNo).prevCarrId(prevCarrId).build();

    }
}
