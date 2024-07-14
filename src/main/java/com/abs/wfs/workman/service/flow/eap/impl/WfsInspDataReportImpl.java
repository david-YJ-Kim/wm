package com.abs.wfs.workman.service.flow.eap.impl;


import com.abs.wfs.workman.dao.domain.ppsRecipe.model.CnPpsRecipe;
import com.abs.wfs.workman.dao.domain.ppsRecipe.service.CnPpsRecipeServiceImpl;
import com.abs.wfs.workman.dao.domain.ppsRecipe.vo.CnPpsRecipeProcEqpRequestDto;
import com.abs.wfs.workman.dao.query.wipLot.service.WipLotQueryServiceImpl;
import com.abs.wfs.workman.dao.query.wipLot.vo.WipLotDto;
import com.abs.wfs.workman.dao.query.work.service.WorkService;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.eap.WfsInspDataReport;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.eap.WfsInspDataReportIvo;
import com.abs.wfs.workman.spec.out.brs.BrsLotSelfInspDataIvo;
import com.abs.wfs.workman.spec.out.eap.EapJobAbortReqIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.ApStringConstant;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import com.abs.wfs.workman.util.code.RecipeTypeCode;
import com.abs.wfs.workman.util.code.UseStatCd;
import com.abs.wfs.workman.util.exception.ScenarioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class WfsInspDataReportImpl implements WfsInspDataReport {

    @Autowired
    WipLotQueryServiceImpl wipLotQueryService;

    @Autowired
    CnPpsRecipeServiceImpl cnPpsRecipeService;

    @Autowired
    MessageSendService messageSendService;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;

    @Autowired
    WorkService workService;



    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsInspDataReportIvo wfsInspDataReportIvo) throws Exception {

        WfsInspDataReportIvo.WfsInspDataReportBody body = wfsInspDataReportIvo.getBody();
        apFlowProcessVo.setApMsgBody(body);

        Optional<WipLotDto> resulWipLot =  this.wipLotQueryService.selectWipLotInfo(
                                                                       WipLotDto.builder()
                                                                                .pLotId(body.getLotId())
                                                                                .pUseStatCd(UseStatCd.Usable.name())
                                                                                .pSiteId(body.getSiteId())
                                                                                .pSelfInspYn(ApStringConstant.Y)
                                                                                .build()
        );

        if(!resulWipLot.isPresent()) {
            log.error("Lot is not set for self inspection. check the lot : {} and query result : {}",
                    body.getLotId(),
                    this.wipLotQueryService.selectWipLotInfo(
                                        WipLotDto.builder().pLotId(body.getLotId()).build()
                    )
            );
            throw new ScenarioException(apFlowProcessVo, body);

        }else{

            Optional<CnPpsRecipe> resultRecipe = this.cnPpsRecipeService.findEqpProcessingRecipe(CnPpsRecipeProcEqpRequestDto.builder()
                                                        .siteId(body.getSiteId())
                                                        .eqpId(body.getEqpId())
                                                        .prodDefId(resulWipLot.get().getProdDefId())
                                                        .procDefId(resulWipLot.get().getProcDefId())
                                                        .procSgmtId(resulWipLot.get().getProcSgmtId())
                                                        .build()
            );

            // TODO  Abnormal 테스트 케이스: 레시피 등록 안된 경우
            if(!resultRecipe.isPresent()) {
                log.error("Recipe is not set.");
                throw new ScenarioException(apFlowProcessVo, body);
            }else{

                RecipeTypeCode recipeType = RecipeTypeCode.valueOf(resultRecipe.get().getMtrlFaceCd());
                // TODO  Abnormal 테스트 케이스: 레시피 타입과 상이한 경우


                ApMsgBody apMsgBody = new ApMsgBody();
                apMsgBody.setSiteId(body.getSiteId());
                apMsgBody.setLotId((body.getLotId()));
                apMsgBody.setCarrId((body.getCarrId()));
                apMsgBody.setEqpId((body.getEqpId()));


                boolean onMoreInpsection = body.getResultCode().equals("2");
                if(recipeType.equals(RecipeTypeCode.Both_Flip) && onMoreInpsection) {
                    log.info("Both flip and self inspection one-more.");

                    // Update one-more work
                    this.workService.updateWorkRsnCd(body.getSiteId(), apFlowProcessVo.getEventName(),
                                                        wfsInspDataReportIvo.getHead().getTid(), body.getWorkId(),
                                                        ApSystemCodeConstant.WFS, WorkManCommonUtil.convertEqpInspectionCodeIntoMesCode(body.getResultCode()));


                    // send abort messages
                    EapJobAbortReqIvo.EapJobAbortReqBody abortReqPayload = new EapJobAbortReqIvo.EapJobAbortReqBody();
                                                                                    abortReqPayload.setSiteId(apMsgBody.getSiteId());
                                                                                    abortReqPayload.setEqpId(apMsgBody.getEqpId());
                                                                                    abortReqPayload.setUserId(ApSystemCodeConstant.WFS);
                                                                                    abortReqPayload.setWorkId(body.getWorkId());

                    this.messageSendService.sendMessageSend(EapJobAbortReqIvo.system, EapJobAbortReqIvo.cid,
                            this.apPayloadGenerateService.generateBody(wfsInspDataReportIvo.getHead().getTid(), abortReqPayload));

                    log.info("EapJobAbortReqIvo abort request processed.");
                }

                BrsLotSelfInspDataIvo.BrsLotSelfInspDataBody payload = this.generatePayload(apMsgBody, body,
                                                resulWipLot.get().getProdDefId(), resulWipLot.get().getProcDefId(),
                                                resulWipLot.get().getProcSgmtId(),
                                                resulWipLot.get().getSelfInspInfoObjId());

                this.messageSendService.sendMessageSend(BrsLotSelfInspDataIvo.system, BrsLotSelfInspDataIvo.cid,
                        this.apPayloadGenerateService.generateBody(wfsInspDataReportIvo.getHead().getTid(), payload));


                return WorkManCommonUtil.completeFlowProcessVo(apFlowProcessVo);

            }

        }

    }




    private BrsLotSelfInspDataIvo.BrsLotSelfInspDataBody generatePayload(ApMsgBody basicBody, WfsInspDataReportIvo.WfsInspDataReportBody body,
                                   String prodDefId, String procDefId, String procSgmtId, String selfInspOjbId){

        BrsLotSelfInspDataIvo.BrsLotSelfInspDataBody payload = new BrsLotSelfInspDataIvo.BrsLotSelfInspDataBody();
        payload.setSiteId(basicBody.getSiteId()); payload.setLotId(basicBody.getLotId()); payload.setCarrId(basicBody.getCarrId()); payload.setEqpId(basicBody.getEqpId());
        payload.setUserId(basicBody.getUserId());

        payload.setProdMtrlId(body.getProdMtrlId());
        payload.setSlotNo(body.getSlotNo());
        payload.setMtrlFaceCd(WorkManCommonUtil.convertEqpWorkFaceIntoMesWorkFace(body.getMtrlFace()));

        payload.setProdDefId(prodDefId); payload.setProcDefId(procDefId); payload.setProcSgmtId(procSgmtId);

        payload.setWorkId(body.getWorkId());
        payload.setSelfInspRsltCd(WorkManCommonUtil.convertEqpInspectionCodeIntoMesCode(body.getResultCode()));
        payload.setSelfInspObjId(selfInspOjbId);
        payload.setUserId(ApSystemCodeConstant.WFS);


        return payload;

    }

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return  WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }
}
