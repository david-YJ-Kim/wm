package com.abs.wfs.workman.service.common.work;


import com.abs.wfs.workman.dao.domain.tnProducedMaterial.model.TnProducedMaterial;
import com.abs.wfs.workman.dao.domain.tnProducedMaterial.service.TnProducedMaterialServiceImpl;
import com.abs.wfs.workman.dao.query.service.WfsCommonQueryService;
import com.abs.wfs.workman.dao.query.service.WorkQueryService;
import com.abs.wfs.workman.dao.query.util.CreateWorkRequestVo;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.UtilCommonService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.common.vo.MeasureOutInfo;
import com.abs.wfs.workman.service.common.vo.MeasureOutPortCarrInfoReqVo;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.oia.WfsOiGenerateWorkReqIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.UseYn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WorkManageService {


    @Autowired
    WorkQueryService workQueryService;
    @Autowired
    MessageSendService messageSendService;
    @Autowired
    WfsCommonQueryService wfsCommonQueryService;
    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;

    @Autowired
    UtilCommonService utilCommonService;

    @Autowired
    TnProducedMaterialServiceImpl tnProducedMaterialService;

    /**
     * Measurement Room, 패널 이동 Work 생성
     *
     * @return
     */
    public ApFlowProcessVo generateMeasurementRoomWork(ApFlowProcessVo apFlowProcessVo, WfsOiGenerateWorkReqIvo wfsOiGenerateWorkReqIvo) throws Exception {


        WfsOiGenerateWorkReqIvo.Body body = wfsOiGenerateWorkReqIvo.getBody();
        String workId = this.wfsCommonQueryService.getID("WORK");
        apFlowProcessVo.setApMsgBody(body);
        apFlowProcessVo.setWorkId(workId);

        String siteId = body.getSiteId();
        String eqpId = body.getEqpId();
        String lotId = body.getLotId();
        String slotNo = body.getSlotNo(); // 현재 작업 시작할 패널의 Slot 위치

        if(slotNo == null || slotNo.isEmpty()){

            TnProducedMaterial mtrlInfo = this.tnProducedMaterialService.findByLotIdAndProdMtrlId(siteId, lotId, body.getProdMtrlId());
            if(mtrlInfo == null || mtrlInfo.getSlotNo() == null){
                // TODO throw Exception.
                throw new Exception("Slot No is not defined");
            }
            slotNo = String.valueOf(mtrlInfo.getSlotNo());
        }

        boolean isInputWork = body.getPanelInputYn().equals(UseYn.Y.name());
        log.info("{} Start to generate measurement panel moving work. isInputWork? :{}" +
                "payload : {}", apFlowProcessVo.printLog(), wfsOiGenerateWorkReqIvo.toString(), isInputWork);


        MeasureOutPortCarrInfoReqVo measureReqVo = MeasureOutPortCarrInfoReqVo.builder()
                                                                .siteId(siteId.isEmpty() ? "SVM" : siteId)
                                                                .lotId(lotId)
                                                                .eqpId(body.getEqpId())
                                                                .portId(body.getPortId())
                                                                .carrId(body.getCarrId())
                                                                .prodMtrlId(body.getProdMtrlId())
                                                                .panelInputYn(body.getPanelInputYn().equals(UseYn.Y.name()))
                                                                .build();
        log.info("{} Ready to get measure out carr info with request vo : {}.", apFlowProcessVo.printLog(), measureReqVo.toString());

        MeasureOutInfo measureOutCstPort = this.utilCommonService.getMeasureOutPortCarrInfo(apFlowProcessVo, measureReqVo);
        log.info("{} Ready to make panel move work. from port: {}, measureOutInfo: {}, measureOutTargetCarr: {}",
                apFlowProcessVo.printLog(), body.getPortId(), measureOutCstPort.toString(), measureOutCstPort.getTargetCarrId());



        CreateWorkRequestVo vo = new CreateWorkRequestVo();
        vo.setSiteId(siteId);
        vo.setCid(WfsOiGenerateWorkReqIvo.cid);
        vo.setTid(siteId);
        vo.setUserId(body.getUserId());
        vo.setEqpId(eqpId);
        vo.setWorkId(workId);
        vo.setLotId(lotId);
        vo.setLotQty("1");

        /** 투입 부 정리 **/
        vo.setCarrId(body.getCarrId());
        vo.setInCarrId(body.getCarrId());
        vo.setInCarrTyp("");
        vo.setInPortId(body.getPortId());

        /** 배출 부 정리 **/
        vo.setOutCarrId(measureOutCstPort.getTargetCarrId());
        vo.setOutCarrTyp("");
        vo.setOutPortId(measureOutCstPort.getLinkedPortId());

        /** 공정 정보 **/
        vo.setProdDefId(body.getProdDefId());
        vo.setProcDefId(body.getProcDefId());
        vo.setProcSgmtId(body.getProcSgmtId());

        vo.setSelfInspYn("N");
        vo.setSelfInspCnt("");
        vo.setBatchYn("");
        vo.setInlineYn("");
        vo.setEqpInlineId("");
        vo.setInlineStatCd("");
        vo.setDspWorkId("");
        vo.setBatchYn("N");
        vo.setBatchId("");
        log.info("{} Ready to generate work. Req vo: {}", apFlowProcessVo.printLog(), vo.toString());

        WorkManCommonUtil.setAdditionalData(apFlowProcessVo, "CreateWorkRequestVo", vo.toString());


        try{

            this.workQueryService.createWorkMeasureTrayLoader(vo, "N", "", "TTT",
                    "Top", "Y", body.getProdMtrlId(), slotNo);
        }catch (Exception e){
            e.printStackTrace();
            log.error("Error : {}", e);
        }

        try{
            this.messageSendService.generateWorkMessageSend(apFlowProcessVo);
        }catch (Exception e){
            throw e;
        }

        return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);

    }
}
