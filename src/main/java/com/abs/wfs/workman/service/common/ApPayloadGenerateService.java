package com.abs.wfs.workman.service.common;


import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.eap.*;
import com.abs.wfs.workman.spec.out.brs.*;
import com.abs.wfs.workman.spec.out.eap.*;
import com.abs.wfs.workman.spec.out.fis.FisFileReportIvo;
import com.abs.wfs.workman.spec.out.mcs.McsCarrMoveCnclReqIvo;
import com.abs.wfs.workman.spec.out.mcs.McsCarrMoveReqIvo;
import com.abs.wfs.workman.spec.out.rtd.RtdDspWorkReqIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Service
@Slf4j
public class ApPayloadGenerateService {

    private static ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    /**
     * BRS
     */

    public String generateBrsEqpControlModeChangeIvo(BrsEqpControlModeChangeIvo.BrsEqpControlModeChangeBody body) throws JsonProcessingException {
        BrsEqpControlModeChangeIvo brsEqpControlModeChangeIvo = new BrsEqpControlModeChangeIvo();

        ApMsgHead apMsgHead = ApMsgHead.builder()
                .cid(BrsEqpControlModeChangeIvo.cid)
                .src(ApSystemCodeConstant.WFS)
                .tgt(ApSystemCodeConstant.BRS)
                .build();

        brsEqpControlModeChangeIvo.setHead(apMsgHead);
        brsEqpControlModeChangeIvo.setBody(body);

        return objectMapper.writeValueAsString(brsEqpControlModeChangeIvo);
    }


    public String generateBody(String tid,BrsEqpStateChangeIvo.BrsEqpStateChangeBody body) throws JsonProcessingException {
        BrsEqpStateChangeIvo brsEqpStateChangeIvo = new BrsEqpStateChangeIvo();

        brsEqpStateChangeIvo.setHead(WorkManCommonUtil.generateMessageHead(tid, BrsEqpStateChangeIvo.cid, ApSystemCodeConstant.BRS, null));
        brsEqpStateChangeIvo.setBody(body);

        return objectMapper.writeValueAsString(brsEqpStateChangeIvo);
    }





    public String generateBody(String tid, BrsLotSelfInspDataIvo.BrsLotSelfInspDataBody body) throws JsonProcessingException {


        BrsLotSelfInspDataIvo brsLotSelfInspDataIvo = new BrsLotSelfInspDataIvo();

        if(body.getUserId() == null || body.getUserId().isEmpty()) {body.setUserId(ApSystemCodeConstant.WFS);}
        brsLotSelfInspDataIvo.setHead(WorkManCommonUtil.generateMessageHead(tid, BrsLotSelfInspDataIvo.cid, BrsLotSelfInspDataIvo.system, null));
        brsLotSelfInspDataIvo.setBody(body);

        return objectMapper.writeValueAsString(brsLotSelfInspDataIvo);


    }


    /**
     * EAP
     */
    public String generateBody(String tid, EapCarrCancelReq.Body body) throws JsonProcessingException {

        EapCarrCancelReq eapCarrCancelReqIvo = new EapCarrCancelReq();

        if(body.getUserId() == null || body.getUserId().isEmpty()) {body.setUserId(ApSystemCodeConstant.WFS);}
        eapCarrCancelReqIvo.setHead(WorkManCommonUtil.generateMessageHead(tid, EapCarrCancelReq.cid, EapCarrCancelReq.system, body.getEqpId()));
        eapCarrCancelReqIvo.setBody(body);

        return objectMapper.writeValueAsString(eapCarrCancelReqIvo);
    }

    public String generateBody(String tid, EapJobAbortReqIvo.EapJobAbortReqBody body) throws JsonProcessingException {


        EapJobAbortReqIvo eapJobAbortReqIvo = new EapJobAbortReqIvo();

        if(body.getUserId() == null || body.getUserId().isEmpty()) {body.setUserId(ApSystemCodeConstant.WFS);}
        eapJobAbortReqIvo.setHead(WorkManCommonUtil.generateMessageHead(tid, EapJobAbortReqIvo.cid, EapJobAbortReqIvo.system, body.getEqpId()));
        eapJobAbortReqIvo.setBody(body);

        return objectMapper.writeValueAsString(eapJobAbortReqIvo);


    }


    public String generateBody(String tid, EapLotInfoRepIvo.EapLotInfoRepBody body) throws JsonProcessingException {


        EapLotInfoRepIvo eapLotInfoRepIvo = new EapLotInfoRepIvo();

        if(body.getUserId() == null || body.getUserId().isEmpty()) {body.setUserId(ApSystemCodeConstant.WFS);}
        eapLotInfoRepIvo.setHead(WorkManCommonUtil.generateMessageHead(tid, EapLotInfoRepIvo.cid, EapLotInfoRepIvo.system, body.getEqpId()));
        eapLotInfoRepIvo.setBody(body);

        return objectMapper.writeValueAsString(eapLotInfoRepIvo);


    }


    public String generateBody(String tid, EapToolCondReqIvo.Body body) throws JsonProcessingException {


        EapToolCondReqIvo ivo = new EapToolCondReqIvo();

        if (body.getUserId() == null || body.getUserId().isEmpty()) {
            body.setUserId(ApSystemCodeConstant.WFS);
        }
        ivo.setHead(WorkManCommonUtil.generateMessageHead(tid, EapToolCondReqIvo.cid, EapToolCondReqIvo.system, body.getEqpId()));
        ivo.setBody(body);

        return objectMapper.writeValueAsString(ivo);
    }


    public String generateBody(String tid, WfsEfemControlStateReportIvo.Body body) throws JsonProcessingException {


        WfsEfemControlStateReportIvo ivo = new WfsEfemControlStateReportIvo();

        if (body.getUserId() == null || body.getUserId().isEmpty()) {
            body.setUserId(ApSystemCodeConstant.WFS);
        }
        ivo.setHead(WorkManCommonUtil.generateMessageHead(tid, WfsEfemControlStateReportIvo.cid, WfsEfemControlStateReportIvo.system, body.getEqpId()));
        ivo.setBody(body);

        return objectMapper.writeValueAsString(ivo);
    }



    public String generateBody(String tid, EapDurableInfoReqIvo.Body body) throws JsonProcessingException {


        EapDurableInfoReqIvo ivo = new EapDurableInfoReqIvo();

        if (body.getUserId() == null || body.getUserId().isEmpty()) {
            body.setUserId(ApSystemCodeConstant.WFS);
        }
        ivo.setHead(WorkManCommonUtil.generateMessageHead(tid, EapDurableInfoReqIvo.cid, EapDurableInfoReqIvo.system, body.getEqpId()));
        ivo.setBody(body);

        return objectMapper.writeValueAsString(ivo);
    }

    public String generateBody(String tid, EapWorkOrderReq.Body body) throws JsonProcessingException {


        EapWorkOrderReq ivo = new EapWorkOrderReq();

        if (body.getUserId() == null || body.getUserId().isEmpty()) {
            body.setUserId(ApSystemCodeConstant.WFS);
        }
        ivo.setHead(WorkManCommonUtil.generateMessageHead(tid, EapWorkOrderReq.cid, EapWorkOrderReq.system, body.getEqpId()));
        ivo.setBody(body);

        return objectMapper.writeValueAsString(ivo);
    }



    public WfsEfemControlStateReportIvo generateMessageObject(String tid, WfsEfemControlStateReportIvo.Body body) throws JsonProcessingException {


        WfsEfemControlStateReportIvo ivo = new WfsEfemControlStateReportIvo();

        if (body.getUserId() == null || body.getUserId().isEmpty()) {
            body.setUserId(ApSystemCodeConstant.WFS);
        }
        ivo.setHead(WorkManCommonUtil.generateMessageHead(tid, WfsEfemControlStateReportIvo.cid, WfsEfemControlStateReportIvo.system, body.getEqpId()));
        ivo.setBody(body);

        return ivo;
    }




    /**
     * FIS
     */
    public String generateBody(String tid, FisFileReportIvo.FisFileReportBody body) throws JsonProcessingException {


        FisFileReportIvo fisFileReportIvo = new FisFileReportIvo();

        if(body.getUserId() == null || body.getUserId().isEmpty()) {body.setUserId(ApSystemCodeConstant.WFS);}
        fisFileReportIvo.setHead(WorkManCommonUtil.generateMessageHead(tid, FisFileReportIvo.cid, FisFileReportIvo.system, body.getEqpId()));
        fisFileReportIvo.setBody(body);

        return objectMapper.writeValueAsString(fisFileReportIvo);


    }
    /**
     * RTD
     */
    public String generateBody(String tid, RtdDspWorkReqIvo.RtdDspWorkReqBody body) throws JsonProcessingException {


        RtdDspWorkReqIvo rtdDspWorkReqIvo = new RtdDspWorkReqIvo();

        if(body.getUserId() == null || body.getUserId().isEmpty()) {body.setUserId(ApSystemCodeConstant.WFS);}
        rtdDspWorkReqIvo.setHead(WorkManCommonUtil.generateMessageHead(tid, RtdDspWorkReqIvo.cid, RtdDspWorkReqIvo.system, body.getEqpId()));
        rtdDspWorkReqIvo.setBody(body);

        return objectMapper.writeValueAsString(rtdDspWorkReqIvo);


    }


    /**
     * BRS
     */
    public String generateBody(String tid, BrsLotProcEndedIvo.BrsLotProcEndedBody body) throws JsonProcessingException {


        BrsLotProcEndedIvo ivo = new BrsLotProcEndedIvo();

        if(body.getUserId() == null || body.getUserId().isEmpty()) {body.setUserId(ApSystemCodeConstant.WFS);}
        ivo.setHead(WorkManCommonUtil.generateMessageHead(tid, BrsLotProcEndedIvo.cid, BrsLotProcEndedIvo.system, body.getEqpId()));
        ivo.setBody(body);

        return objectMapper.writeValueAsString(ivo);


    }


    public String generateBody(String tid, BrsLotProcStartedIvo.BrsLotProcStartedBody body) throws JsonProcessingException {


        BrsLotProcStartedIvo ivo = new BrsLotProcStartedIvo();

        if(body.getUserId() == null || body.getUserId().isEmpty()) {body.setUserId(ApSystemCodeConstant.WFS);}
        ivo.setHead(WorkManCommonUtil.generateMessageHead(tid, BrsLotProcStartedIvo.cid, BrsLotProcStartedIvo.system, body.getEqpId()));
        ivo.setBody(body);

        return objectMapper.writeValueAsString(ivo);


    }

    public String generateBody(String tid, BrsLotProdEndedIvo.Body body) throws JsonProcessingException {


        BrsLotProdEndedIvo ivo = new BrsLotProdEndedIvo();

        if(body.getUserId() == null || body.getUserId().isEmpty()) {body.setUserId(ApSystemCodeConstant.WFS);}
        ivo.setHead(WorkManCommonUtil.generateMessageHead(tid, BrsLotProdEndedIvo.cid, BrsLotProdEndedIvo.system, body.getEqpId()));
        ivo.setBody(body);

        return objectMapper.writeValueAsString(ivo);


    }

    public String generateBody(String tid, BrsLotProdStartedIvo.Body body) throws JsonProcessingException {


        BrsLotProdStartedIvo ivo = new BrsLotProdStartedIvo();

        if(body.getUserId() == null || body.getUserId().isEmpty()) {body.setUserId(ApSystemCodeConstant.WFS);}
        ivo.setHead(WorkManCommonUtil.generateMessageHead(tid, BrsLotProdStartedIvo.cid, BrsLotProdStartedIvo.system, body.getEqpId()));
        ivo.setBody(body);

        return objectMapper.writeValueAsString(ivo);

    }

    public String generateBody(String tid, BrsEqpDekitIvo.Body body) throws JsonProcessingException {


        BrsEqpDekitIvo ivo = new BrsEqpDekitIvo();

        if(body.getUserId() == null || body.getUserId().isEmpty()) {body.setUserId(ApSystemCodeConstant.WFS);}
        ivo.setHead(WorkManCommonUtil.generateMessageHead(tid, BrsEqpDekitIvo.cid, BrsEqpDekitIvo.system, body.getEqpId()));
        ivo.setBody(body);

        return objectMapper.writeValueAsString(ivo);


    }
    public String generateBody(String tid, BrsCarrHoldIvo.Body body) throws JsonProcessingException {


        BrsCarrHoldIvo ivo = new BrsCarrHoldIvo();

        if(body.getUserId() == null || body.getUserId().isEmpty()) {body.setUserId(ApSystemCodeConstant.WFS);}
        ivo.setHead(WorkManCommonUtil.generateMessageHead(tid, BrsCarrHoldIvo.cid, BrsCarrHoldIvo.system, body.getEqpId()));
        ivo.setBody(body);

        return objectMapper.writeValueAsString(ivo);

    }

    public String generateBody(String tid, BrsLotDeassignCarr.Body body) throws JsonProcessingException {


        BrsLotDeassignCarr ivo = new BrsLotDeassignCarr();

        if(body.getUserId() == null || body.getUserId().isEmpty()) {body.setUserId(ApSystemCodeConstant.WFS);}
        ivo.setHead(WorkManCommonUtil.generateMessageHead(tid, BrsLotDeassignCarr.cid, BrsLotDeassignCarr.system, body.getEqpId()));
        ivo.setBody(body);

        return objectMapper.writeValueAsString(ivo);

    }

    public String generateBody(String tid, BrsLotCarrAssign.Body body) throws JsonProcessingException {


        BrsLotCarrAssign ivo = new BrsLotCarrAssign();

        if(body.getUserId() == null || body.getUserId().isEmpty()) {body.setUserId(ApSystemCodeConstant.WFS);}
        ivo.setHead(WorkManCommonUtil.generateMessageHead(tid, BrsLotCarrAssign.cid, BrsLotCarrAssign.system, body.getEqpId()));
        ivo.setBody(body);

        return objectMapper.writeValueAsString(ivo);

    }

    /**
     * WFS
     */
    public String generateBody(String tid, WfsTrayLoadCompIvo.Body body) throws JsonProcessingException {


        WfsTrayLoadCompIvo ivo = new WfsTrayLoadCompIvo();

        if(body.getUserId() == null || body.getUserId().isEmpty()) {body.setUserId(ApSystemCodeConstant.WFS);}
        ivo.setHead(WorkManCommonUtil.generateMessageHead(tid, WfsTrayLoadCompIvo.cid, WfsTrayLoadCompIvo.system, body.getEqpId()));
        ivo.setBody(body);

        return objectMapper.writeValueAsString(ivo);


    }

    public String generateBody(String tid, WfsUnloadReqIvo.Body body) throws JsonProcessingException {


        WfsUnloadReqIvo ivo = new WfsUnloadReqIvo();

        if(body.getUserId() == null || body.getUserId().isEmpty()) {body.setUserId(ApSystemCodeConstant.WFS);}
        ivo.setHead(WorkManCommonUtil.generateMessageHead(tid, WfsUnloadReqIvo.cid, ApSystemCodeConstant.EAP, WfsUnloadReqIvo.system, body.getEqpId(), null, null));
        ivo.setBody(body);

        return objectMapper.writeValueAsString(ivo);


    }

    public String generateBody(String tid, WfsEqpStateReportIvo.Body body) throws JsonProcessingException {


        WfsEqpStateReportIvo ivo = new WfsEqpStateReportIvo();

        if(body.getUserId() == null || body.getUserId().isEmpty()) {body.setUserId(ApSystemCodeConstant.WFS);}
        ivo.setHead(WorkManCommonUtil.generateMessageHead(tid, WfsEqpStateReportIvo.cid, ApSystemCodeConstant.EAP, WfsUnloadReqIvo.system, body.getEqpId(), null, null));
        ivo.setBody(body);

        return objectMapper.writeValueAsString(ivo);


    }



    /**
     * MCS
     * mcs는 tgtEqp는 null
     * userId 는 null
     */
    public String generateBody(String tid, McsCarrMoveReqIvo.McsCarrMoveReqBody body) throws JsonProcessingException {


        McsCarrMoveReqIvo mcsCarrMoveReqIvo = new McsCarrMoveReqIvo();

        mcsCarrMoveReqIvo.setHead(WorkManCommonUtil.generateMessageHead(tid, McsCarrMoveReqIvo.cid, McsCarrMoveReqIvo.system, null));
        mcsCarrMoveReqIvo.setBody(body);

        return objectMapper.writeValueAsString(mcsCarrMoveReqIvo);


    }


    /**
     * MCS
     * mcs는 tgtEqp는 null
     * userId 는 null
     */
    public String generateBody(String tid, McsCarrMoveCnclReqIvo.Body body) throws JsonProcessingException {


        McsCarrMoveCnclReqIvo mcsCarrMoveCnclReqIvo = new McsCarrMoveCnclReqIvo();

        mcsCarrMoveCnclReqIvo.setHead(WorkManCommonUtil.generateMessageHead(tid, McsCarrMoveCnclReqIvo.cid, McsCarrMoveCnclReqIvo.system, null));
        mcsCarrMoveCnclReqIvo.setBody(body);

        return objectMapper.writeValueAsString(mcsCarrMoveCnclReqIvo);


    }



    public String generateBody(String tid, EapRechuckReqIvo.Body rechuckReqBody) throws JsonProcessingException {
        EapRechuckReqIvo eapRechuckReqIvo = new EapRechuckReqIvo();
        eapRechuckReqIvo.setHead(WorkManCommonUtil.generateMessageHead(tid, EapRechuckReqIvo.cid, EapRechuckReqIvo.system, rechuckReqBody.getEqpId()));

        return objectMapper.writeValueAsString(rechuckReqBody);
    }

//
//
//    public String getBRS_LOT_TRACK_IN_CNFM_CANCEL(String siteId, String tid, String eqpId, String lotId)
//            throws JsonProcessingException {
//
//        String cid = "BRS_LOT_TRACK_IN_CNFM_CANCEL";
//        Head head = new Head(cid, tid, "", "", "WFS", "BRS","" ,eqpId);
//
//
//
//
//        BrsLotTrackInCnfmCancelBody body = new BrsLotTrackInCnfmCancelBody();
//        body.setSiteId(siteId);
//        body.setUserId(WFS);
//        body.setLotId(lotId);
//        body.setEqpId(eqpId);
//        body.setTrnsCm("");
//
//        BrsLotTrackInCnfmCancel brsLotTrackInCnfmCancel = new BrsLotTrackInCnfmCancel();
//        brsLotTrackInCnfmCancel.setHead(head);
//        brsLotTrackInCnfmCancel.setBody(body);
//
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsLotTrackInCnfmCancel);
//    }
//
//
//
//    public String getEAP_TERMINAL_MESSAGE_REQ(String siteId, String tid, String eqpId,
//                                              String message, String errorCode, String dateString) throws JsonProcessingException {
//
//        String cid = "EAP_TERMINAL_MESSAGE_REQ";
//        Head head = new Head(cid, tid, "", "", "WFS", "EAP","" ,eqpId);
//
//        String messageFormat = "message: %s, requestSystem: %S, errorCode: %s, time: %S";
//
//
//        EapTerminalMessageReqBody body = new EapTerminalMessageReqBody();
//        body.setSiteId(siteId);
//        body.setUserId(WFS);
//        body.setEqpId(eqpId);
//        body.setMessage(String.format(messageFormat, message, WFS, errorCode, dateString));
//
//        EapTerminalMessageReq eapTerminalMessageReq = new EapTerminalMessageReq();
//        eapTerminalMessageReq.setHead(head);
//        eapTerminalMessageReq.setBody(body);
//
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(eapTerminalMessageReq);
//    }
//
//
//
//    public String getBRS_LOT_TRACK_IN_CNFM_CANCEL(String siteId, String tid, String eqpId,
//                                                  String lotId, String trnsCm) throws JsonProcessingException {
//
//        String cid = "BRS_LOT_TRACK_IN_CNFM_CANCEL";
//        Head head = new Head(cid, tid, "", "", "WFS", "BRS","" ,eqpId);
//
//        BrsLotTrackInCnfmCancelBody body = new BrsLotTrackInCnfmCancelBody();
//        body.setSiteId(siteId);
//        body.setUserId(WFS);
//        body.setLotId(lotId);
//        body.setEqpId(eqpId);
//        body.setTrnsCm(trnsCm);
//
//        BrsLotTrackInCnfmCancel brsLotTrackInCnfmCancel = new BrsLotTrackInCnfmCancel();
//        brsLotTrackInCnfmCancel.setHead(head);
//        brsLotTrackInCnfmCancel.setBody(body);
//
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsLotTrackInCnfmCancel);
//    }
//
//    public String getBRS_LOT_HOLD(String siteId, String tid, String eqpId, String lotId, String holdCd,
//                                  String futureHoldYn, String alrmSysId, String trnsCm) throws JsonProcessingException {
//
//        String cid = "BRS_LOT_HOLD";
//        Head head = new Head(cid, tid, "", "", "WFS", "BRS","" ,eqpId);
//
//        BrsLotHoldBody body = new BrsLotHoldBody();
//        body.setSiteId(siteId);
//        body.setUserId(WFS);
//        body.setMdfyUserId(WFS);
//        body.setLotId(lotId);
//        body.setHoldCd(holdCd);
//        body.setFutureHoldYn(futureHoldYn);
//        body.setAlrmSysId(alrmSysId);
//        body.setTrnsCm(trnsCm);
//
//        BrsLotHold brsLotHold = new BrsLotHold();
//        brsLotHold.setHead(head);
//        brsLotHold.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsLotHold);
//
//    }
//
//    public String getBRS_LOT_TRACK_OUT_MEASURE(String tid, String siteId, String eqpId,
//                                               String lotId, String carrId, String subEqpId,
//                                               String procSgmtId, String subProcSgmtId, String recipeListXML) throws SAXException, IOException, ParserConfigurationException {
//
//        XMLManager xmlMamager = new XMLManager();
//        // Recipe 변경 작업 적용
//        Map<String,String> recipeMap = xmlMamager.getXMLtoListMap(recipeListXML).get(0);
//        String mtrlFaceCd = recipeMap.get("MTRL_FACE_CD");
//        String topRcpId = recipeMap.get("TOP_RCP_ID");
//        String bottomRcpId = recipeMap.get("BOTTOM_RCP_ID");
//
//        String recipeId = "";
//        if("Top".equals(mtrlFaceCd)) {
//            recipeId = topRcpId;
//        } else if("Bottom".equals(mtrlFaceCd)) {
//            recipeId = bottomRcpId;
//        } else if("Both".equals(mtrlFaceCd) || "Both_Flip".equals(mtrlFaceCd)) {
//            recipeId = topRcpId + "," +bottomRcpId;
//        }
//
//        String cid = "BRS_LOT_TRACK_OUT_MEASURE";
//        Head head = new Head(cid, tid, "", "", "WFS", "BRS","" ,eqpId);
//
//        BrsLotTrackInMeasureBody body = new BrsLotTrackInMeasureBody();
//        body.setSiteId(siteId);
//        body.setUserId(WFS);
//
//        body.setLotId(lotId);
//        body.setEqpId(eqpId);
//        body.setCarrId(carrId);
//        body.setSubEqpId(subEqpId);
//        body.setProcSgmtId(procSgmtId);
//        body.setSubProcSgmtId(subProcSgmtId);
//        body.setRecipeId(recipeId);
//
//
//        BrsLotTrackInMeasure orderReq = new BrsLotTrackInMeasure();
//        orderReq.setHead(head);
//        orderReq.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(orderReq);
//    }
//
//
//    public String getBRS_LOT_CARR_ASSIGN(String tid, String siteId,String lotId, String eqpId,String carrId, String rsnCd,
//                                         String trnsCm, String mdfyUserId, boolean checkClnStat, String asgnQty, String prodMtrlId)
//            throws JsonProcessingException {
//
//        String cid = "BRS_LOT_CARR_ASSIGN";
//        Head head = new Head(cid, tid, "", "", "WFS", "EQP","" ,eqpId);
//
//        BrsLotCarrAssignBody body = new BrsLotCarrAssignBody();
//        body.setSiteId(siteId);
//        body.setUserId(WFS);
//
//        body.setLotId(lotId);
//        body.setCarrId(carrId);
//        body.setRsnCd(rsnCd);
//        body.setTrnsCm(trnsCm);
//        body.setMdfyUserId(mdfyUserId);
//        body.setCheckClnStat(checkClnStat);
//        body.setAsgnQty(asgnQty);
//
//        List<Slots> slotArray = new ArrayList<>();
//        Slots slots = new Slots();
//        slots.setSlotNo("1");
//        slots.setProdMtrlId(prodMtrlId);
//        slotArray.add(slots);
//
//        body.setSlots(slotArray);
//
//        BrsLotCarrAssign orderReq = new BrsLotCarrAssign();
//        orderReq.setHead(head);
//        orderReq.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(orderReq);
//    }
//
//
//
//    public String getBRS_LOT_TRACK_IN_MEASURE(String tid, String siteId, String eqpId,
//                                              String lotId, String carrId, String subEqpId,
//                                              String procSgmtId, String subProcSgmtId, String recipeId) throws JsonProcessingException {
//
//        String cid = "BRS_LOT_TRACK_IN_MEASURE";
//        Head head = new Head(cid, tid, "", "", "WFS", "EQP","" ,eqpId);
//
//        BrsLotTrackInMeasureBody body = new BrsLotTrackInMeasureBody();
//        body.setSiteId(siteId);
//        body.setUserId(WFS);
//
//        body.setLotId(lotId);
//        body.setEqpId(eqpId);
//        body.setCarrId(carrId);
//        body.setSubEqpId(subEqpId);
//        body.setProcSgmtId(procSgmtId);
//        body.setSubProcSgmtId(subProcSgmtId);
//        body.setRecipeId(recipeId);
//
//
//        BrsLotTrackInMeasure orderReq = new BrsLotTrackInMeasure();
//        orderReq.setHead(head);
//        orderReq.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(orderReq);
//    }
//
//
//
//
//
//    public String getVMS_WORK_ORDER_REQ(String tid, String siteId, String eqpId,
//                                        String prevEqpId, String lotId, String prodDefId,
//                                        String procDefId, String procSgmtId) throws JsonProcessingException {
//
//        String cid = "VMS_WORK_START_REQ";
//        Head head = new Head(cid, tid, "", "", "WFS", "EQP","" ,eqpId);
//
//        VmsWorkOrderReqBody body = new VmsWorkOrderReqBody();
//        body.setSiteId(siteId);
//        body.setUserId(WFS);
//        body.setEqpId(eqpId);
//        body.setPrevEqpId(prevEqpId);
//        body.setLotId(lotId);
//        body.setProdDefId(prodDefId);
//        body.setProcDefId(procDefId);
//        body.setProcSgmtId(procSgmtId);
//
//        VmsWorkOrderReq orderReq = new VmsWorkOrderReq();
//        orderReq.setHead(head);
//        orderReq.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(orderReq);
//    }
//
//
//    /**
//     *
//     * @param tid
//     * @param siteId
//     * @param eqpId
//     * @param specInfo
//     * @return
//     * @throws JsonProcessingException
//     */
//    public String getEAP_DURABLE_INFO_REQ(String tid, String siteId, String eqpId
//    ) throws JsonProcessingException {
//        String cid = "EAP_DURABLE_INFO_REQ";
//        Head head = new Head(cid,
//                tid,
//                "",
//                "",
//                "WFS",
//                "EAP");
//
//        EapDurableInfoReqBody body = new EapDurableInfoReqBody();
//        body.setSiteId(siteId);
//        body.setUserId(WFS);
//        body.setEqpId(eqpId);
//        body.setSpecInfo("ALL");
//
//        EapDurableInfoReq durableInfoReq = new EapDurableInfoReq();
//        durableInfoReq.setHead(head);
//        durableInfoReq.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(durableInfoReq);
//
//
//    }
//
//
//    /**
//     *
//     * @param siteId
//     * @param tid
//     * @param eqpId
//     * @param subEqpId
//     * @param specId
//     * @param specType
//     * @return
//     * @throws JsonProcessingException
//     */
//    public String getBRS_EQP_MTRL_CNFM_REQ(String siteId, String tid, String eqpId, String subEqpId,
//                                           String specId, String specType)
//            throws JsonProcessingException {
//
//        String cid = "BRS_EQP_MTRL_CNFM_REQ";
//        Head head = new Head(cid,
//                tid,
//                "",
//                "",
//                "WFS",
//                "BRS");
//        BrsEqpMtrlCnfmReqBody body = new BrsEqpMtrlCnfmReqBody();
//        body.setSiteId(siteId);
//        body.setUserId("WFS");
//        body.setEqpId(eqpId);
//        body.setSubEqpId(subEqpId);
//        body.setSpecId(specId);
//        body.setSpecType(specType);
//
//        BrsEqpMtrlCnfmReq brsEqpMtrlCnfmReq = new BrsEqpMtrlCnfmReq();
//        brsEqpMtrlCnfmReq.setHead(head);
//        brsEqpMtrlCnfmReq.setBody(body);
//
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsEqpMtrlCnfmReq);
//    }
//
//
//    public String getEAP_LOT_INFO_REP(String siteId, String tid, String eqpId, String lotId, String prntLotId,
//                                      String prodDefId, String procDefId, String procSgmtId, String prevEqpId, String mtrlFaceCd,
//                                      String prodMtrlListXml, String shipTypeCode, String dimensionX, String dimensionY,
//                                      String rowCountLimitInspection, String rowCountLimitMeasurement)
//            throws SAXException, IOException, ParserConfigurationException {
//
//        XMLManager xmlMamager = new XMLManager();
//
//
//
//
//        String cid = "EAP_LOT_INFO_REP";
//        Head head = new Head(cid,
//                tid,
//                "",
//                "",
//                "WFS",
//                "EAP");
//        EapLotInfoRepBody body = new EapLotInfoRepBody();
//
//        body.setSiteId(siteId);
//        body.setUserId("WFS");
//        body.setEqpId(eqpId);
//        body.setLotId(lotId);
//        body.setPrntLotId(prntLotId);
//        body.setProdDefId(prodDefId);
//        body.setProcDefId(procDefId);
//        body.setProcSgmtId(procSgmtId);
//        body.setMtrlFaceCd(mtrlFaceCd);
//        body.setPrevEqpId(prevEqpId);
//
//        body.setShipType(shipTypeCode);
//        body.setDimensionX(dimensionX);
//        body.setDimensionY(dimensionY);
//        body.setRowCountLimitInspection(rowCountLimitInspection);
//        body.setRowCountLimitMeasurement(rowCountLimitMeasurement);
//
//        List<ProdMtrInfo> prodMtrlInfoList = new ArrayList<ProdMtrInfo>();
//
//        List<Map<String,String>> prodMtrlList = xmlMamager.getXMLtoListMap(prodMtrlListXml);
//
//        if((eqpId.trim().toUpperCase()).startsWith("AP-OL-13")) {
//
//            for(Map<String,String> prodMtrlRow : prodMtrlList) {
//                ProdMtrInfo prodMtrlInfo = new ProdMtrInfo();
//
//                prodMtrlInfo.setProdMtrlId(prodMtrlRow.get("PROD_MTRL_ID"));
//                prodMtrlInfo.setSubMtrlGrdCd(prodMtrlRow.get("SUB_MTRL_GRD_CD").replaceAll("0", "O").replaceAll("1", "X"));
//                prodMtrlInfoList.add(prodMtrlInfo);
//                // 0101010 -> OXOXOXOX으로 변경 필요
//
//            }
//
//        }
//        else {
//            for(Map<String,String> prodMtrlRow : prodMtrlList) {
//                ProdMtrInfo prodMtrlInfo = new ProdMtrInfo();
//                prodMtrlInfo.setProdMtrlId(prodMtrlRow.get("PROD_MTRL_ID"));
//                prodMtrlInfo.setSubMtrlGrdCd(prodMtrlRow.get("SUB_MTRL_GRD_CD").replaceAll("0", "O").replaceAll("1", "X"));
//                prodMtrlInfoList.add(prodMtrlInfo);
//            }
//        }
//
//
//        // make list with prodMtrlListXml
//        body.setProdMtrlList(prodMtrlInfoList);
//
//        EapLotInfoRep eapLotInfoRep = new EapLotInfoRep();
//        eapLotInfoRep.setHead(head);
//        eapLotInfoRep.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(eapLotInfoRep);
//    }
//
//
//
//    public String getBRS_LOT_INSP_JDGM(String siteId, String tid, String lotId, String mtrlFaceCd,
//                                       String procSgmtId, String prodMtrlId, String judgeMap,
//                                       String rsnCd, String trnsCm, String mdfyUserId) throws Exception {
//        String cid = "BRS_LOT_INSP_JDGM";
//        Head head = new Head(cid,
//                tid,
//                "",
//                "",
//                "WFS",
//                "BRS");
//        BrsLotInspJdgmBody body = new BrsLotInspJdgmBody();
//
//        body.setSiteId(siteId);
//        body.setUserId("WFS");
//        body.setLotId(lotId);
//
//        if((mtrlFaceCd.trim().toUpperCase()).equals("TTT") || (mtrlFaceCd.trim().toUpperCase()).equals("TOP")){
//
//            body.setMtrlFaceCd("Top");
//        }else if ((mtrlFaceCd.trim().toUpperCase()).equals("TBT") || (mtrlFaceCd.trim().toUpperCase()).equals("BOTTOM")) {
//
//            body.setMtrlFaceCd("Bottom");
//
//        }else {
//            throw new Exception(
//                    String.format("Mtrl Face is not defined.(TTT or TBT). It's %s", mtrlFaceCd));
//
//        }
//
//        body.setProcSgmtId(procSgmtId);
//        body.setProdMtrlId(prodMtrlId);
//        body.setJudgeMap(judgeMap);
//        body.setRsnCd(rsnCd);
//        body.setTrnsCm(trnsCm);
//        body.setMdfyUserId("WFS");
//
//        BrsLotInspJdgm brsLotInspJdgm = new BrsLotInspJdgm();
//        brsLotInspJdgm.setHead(head);
//        brsLotInspJdgm.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsLotInspJdgm);
//    }
//
//
//
//    public String getFIS_FILE_REPORT(String siteId, String tid, String eqpId, String lotId, String prodMtrlId, String mtrlFace, String fileType, String fileName, String filePath) throws JsonProcessingException {
//        String cid = "FIS_FILE_REPORT";
//        Head head = new Head(cid,
//                tid,
//                "",
//                "",
//                "WFS",
//                "FIS");
//        FisFileReportBody body = new FisFileReportBody();
//
//        body.setSiteId(siteId);
//        body.setUserId("WFS");
//        body.setEqpId(eqpId);
//        body.setLotId(lotId);
//        body.setProdMtrlId(prodMtrlId);
//        body.setMtrlFace(mtrlFace);
//        body.setFileName(fileName);
//        body.setFilePath(filePath);
//        body.setFileType(fileType);
//
//        FisFileReport fileReport = new FisFileReport();
//        fileReport.setHead(head);
//        fileReport.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(fileReport);
//    }
//
//    public String getBRS_LOT_TRACK_IN_REQ(String tid, String siteId, String eqpId, String portId, String lotId, String carrId, String recipeId, String workId, String batchId, String selfInspMasterInfoObjId) throws JsonProcessingException {
//        String targetCID = "BRS_LOT_TRACK_IN_REQ";
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,"WFS"
//                ,"BRS");
//
//        BrsLotTrackInReqBody body = new BrsLotTrackInReqBody();
//
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setLotId(lotId);
//        body.setCarrId(carrId);
//        body.setRecipeId(recipeId);
//        body.setWorkId(workId);
//        if(!"".equals(batchId)) body.setSrcBtchId(batchId);
//        body.setUserId("WFS");
//
//        if(!"".equals(selfInspMasterInfoObjId)) body.setSelfInspMasterInfoObjId(selfInspMasterInfoObjId);
//
//        BrsLotTrackInReq brsLotTrackInReq = new BrsLotTrackInReq();
//        brsLotTrackInReq.setHead(head);
//        brsLotTrackInReq.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsLotTrackInReq);
//    }
//
//    //배출 작업면 적용 OLD 로직 폐기
////	public String getBRS_LOT_TRACK_OUT_REQ(String tid, String siteId, String eqpId, String portId, String lotId,
////										String carrId, String recipeId, String workId, String autoReposition, String slotXML)
////												throws SAXException, IOException, ParserConfigurationException {
////		String targetCID = "BRS_LOT_TRACK_OUT_REQ";
////
////		XMLManager xmlMamager = new XMLManager();
//////		List<Map<String,String>> slotList = xmlMamager.getXMLtoListMap(slotXML, "slotMap");
////
////		Map<String, Object> workInfo = xmlMamager.getWorkEndInfo(slotXML);
////
////		@SuppressWarnings("unchecked")
////		List<Map<String,String>> slotList = (List<Map<String,String>>)workInfo.get("slotList");
////
////		List<Slots> slotsArray = new ArrayList<Slots>();
////
////		for(Map<String,String> slotRow : slotList) {
////			Slots s = new Slots();
////
////			s.setSlotNo(slotRow.get("outCarrSlotNo"));
////			s.setProdMtrlId(slotRow.get("prodMtrlId"));
////			slotsArray.add(s);
////		}
////
////		Head head = new Head(targetCID
////				,tid
////				,""
////				,""
////				,"WFS"
////				,"BRS");
////
////		BrsLotTrackOutReqBody body = new BrsLotTrackOutReqBody();
////
////		List<CarrInfo> carrList = new ArrayList<CarrInfo>();
////		CarrInfo carrInfo = new CarrInfo();
////
////		carrInfo.setCarrId(carrId);
////		carrInfo.setSlots(slotsArray);
////		carrList.add(carrInfo);
////
////
////		body.setRecipeId(recipeId);
////		body.setSiteId(siteId);
////		body.setEqpId(eqpId);
////		body.setPortId(portId);
////		body.setLotId(lotId);
////		body.setCarrId(carrId);
////		body.setAutoReposition(autoReposition);
////		body.setAsgnQty(String.valueOf(slotsArray.size()));
////		body.setCarrList(carrList);
////		body.setUserId(WFS);
////		body.setWorkId(workId);
////
////		BrsLotTrackOutReq brsLotTrackOutReq = new BrsLotTrackOutReq();
////		brsLotTrackOutReq.setHead(head);
////		brsLotTrackOutReq.setBody(body);
////
////		mapper.setSerializationInclusion(Include.NON_NULL);
////		return mapper.writeValueAsString(brsLotTrackOutReq);
////	}
//
//    public String getBRS_LOT_TRACK_OUT_REQ(String tid, String siteId, String eqpId, String portId, String lotId,
//                                           String carrId, String recipeListXML, String workId, String autoReposition, String slotXML, String endWorkFace)
//            throws SAXException, IOException, ParserConfigurationException {
//        String targetCID = "BRS_LOT_TRACK_OUT_REQ";
//
//        XMLManager xmlMamager = new XMLManager();
//        //List<Map<String,String>> slotList = xmlMamager.getXMLtoListMap(slotXML, "slotMap");
//
//        // Recipe 변경 작업 적용
//        Map<String,String> recipeMap = xmlMamager.getXMLtoListMap(recipeListXML).get(0);
//        String mtrlFaceCd = recipeMap.get("MTRL_FACE_CD");
//        String topRcpId = recipeMap.get("TOP_RCP_ID");
//        String bottomRcpId = recipeMap.get("BOTTOM_RCP_ID");
//
//        String recipeId = "";
//        if("Top".equals(mtrlFaceCd)) {
//            recipeId = topRcpId;
//        } else if("Bottom".equals(mtrlFaceCd)) {
//            recipeId = bottomRcpId;
//        } else if("Both".equals(mtrlFaceCd) || "Both_Flip".equals(mtrlFaceCd)) {
//            recipeId = topRcpId + "," +bottomRcpId;
//        }
//
//
//        Map<String, Object> workInfo = xmlMamager.getWorkEndInfo(slotXML);
//
//
//
//
//
//        List<Map<String,String>> slotList = null;
//
//        if("AP-RD-03-01".equals(eqpId)) {
//            slotList = xmlMamager.getXMLtoListMap(slotXML, "slotMap");
//        } else {
//            slotList = (List<Map<String,String>>)workInfo.get("slotList");
//        }
//
//        List<Slots> slotsArray = new ArrayList<Slots>();
//
//        for(Map<String,String> slotRow : slotList) {
//            Slots s = new Slots();
//
//            s.setSlotNo(slotRow.get("outCarrSlotNo"));
//            s.setProdMtrlId(slotRow.get("prodMtrlId"));
//            slotsArray.add(s);
//        }
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,"WFS"
//                ,"BRS");
//
//        BrsLotTrackOutReqBody body = new BrsLotTrackOutReqBody();
//
//        List<CarrInfo> carrList = new ArrayList<CarrInfo>();
//        CarrInfo carrInfo = new CarrInfo();
//
//        carrInfo.setCarrId(carrId);
//        carrInfo.setSlots(slotsArray);
//        carrList.add(carrInfo);
//
//
//        body.setRecipeId(recipeId);
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setLotId(lotId);
//        body.setCarrId(carrId);
//        body.setAutoReposition(autoReposition);
//        body.setAsgnQty(String.valueOf(slotsArray.size()));
//        body.setCarrList(carrList);
//        body.setUserId(WFS);
//        body.setWorkId(workId);
//        body.setMtrlFaceCd(endWorkFace);
//
//        BrsLotTrackOutReq brsLotTrackOutReq = new BrsLotTrackOutReq();
//        brsLotTrackOutReq.setHead(head);
//        brsLotTrackOutReq.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsLotTrackOutReq);
//    }
//
//
//
//
//    public String getBRS_LOT_TRACK_OUT_REQ_VMS(String tid, String siteId, String eqpId, String portId, String lotId,
//                                               String carrId, String recipeId, String workId, String autoReposition)
//            throws SAXException, IOException, ParserConfigurationException {
//        String targetCID = "BRS_LOT_TRACK_OUT_REQ";
//
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,"WFS"
//                ,"BRS");
//
//        BrsLotTrackOutReqBody body = new BrsLotTrackOutReqBody();
//
//
//
//        body.setRecipeId(recipeId);
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setLotId(lotId);
//        body.setCarrId(carrId);
//
//        body.setAsgnQty(null);
//        body.setCarrList(null);
//        body.setUserId(WFS);
//        body.setWorkId(workId);
//
//        BrsLotTrackOutReq brsLotTrackOutReq = new BrsLotTrackOutReq();
//        brsLotTrackOutReq.setHead(head);
//        brsLotTrackOutReq.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsLotTrackOutReq);
//    }
//
//
//
//
//    public String getBRS_LOT_TRACK_OUT_REQ_Unpacking(String tid, String siteId, String eqpId, String portId, String lotId, String carrId, String recipeId, String workId, String autoReposition, String slotXML) throws SAXException, IOException, ParserConfigurationException {
//        String targetCID = "BRS_LOT_TRACK_OUT_REQ";
//
//        XMLManager xmlMamager = new XMLManager();
//
////		List<Map<String,String>> slotList = xmlMamager.getXMLtoListMap(slotXML);
////
////		System.out.println("######################################");
////		System.out.println("SLOT List SIZE >>> " + slotXML);
////		System.out.println("SLOT List >>> " + slotList);
////
////		List<Slots> slotsArray = new ArrayList<Slots>();
////
////		for(Map<String,String> slotRow : slotList) {
////			Slots s = new Slots();
////
////			s.setSlotNo(slotRow.get("SLOT_NO"));
////			s.setProdMtrlId(slotRow.get("SCAN_PROD_MTRL_ID"));
////			slotsArray.add(s);
////		}
//
//        Map<String, Object> workInfo = xmlMamager.getWorkEndInfoUnpacking(slotXML);
//
//        @SuppressWarnings("unchecked")
//        List<Map<String,String>> slotList = (List<Map<String,String>>)workInfo.get("slotList");
//
//        List<Slots> slotsArray = new ArrayList<Slots>();
//
//        for(Map<String,String> slotRow : slotList) {
//            Slots s = new Slots();
//
//            s.setSlotNo(slotRow.get("outCarrSlotNo"));
//            s.setProdMtrlId(slotRow.get("prodMtrlId"));
//            slotsArray.add(s);
//        }
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,"WFS"
//                ,"BRS");
//
//        BrsLotTrackOutReqBody body = new BrsLotTrackOutReqBody();
//
//        List<CarrInfo> carrList = new ArrayList<CarrInfo>();
//        CarrInfo carrInfo = new CarrInfo();
//
//        carrInfo.setCarrId(carrId);
//        carrInfo.setSlots(slotsArray);
//        carrList.add(carrInfo);
//
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setLotId(lotId);
//        body.setCarrId(carrId);
//        body.setAutoReposition(autoReposition);
//        body.setAsgnQty(String.valueOf(slotsArray.size()));
//        body.setCarrList(carrList);
//        body.setUserId(WFS);
//        body.setMtrlFaceCd("Top");
//
//        BrsLotTrackOutReq brsLotTrackOutReq = new BrsLotTrackOutReq();
//        brsLotTrackOutReq.setHead(head);
//        brsLotTrackOutReq.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsLotTrackOutReq);
//    }
//
//    public String getBRS_LOT_TRACK_OUT_REQ_Dicing(String tid, String siteId, String eqpId, String portId, String lotId, String carrId, String recipeId, String workId, String autoReposition, String dicingType, String cellListXML, String scrapListXML) throws SAXException, IOException, ParserConfigurationException {
//        String targetCID = "BRS_LOT_TRACK_OUT_REQ";
//        XMLManager xmlManager = new XMLManager();
//        List<Map<String,Object>> dicingList = xmlManager.getTrackOutDicingMap(cellListXML);
//        List<Map<String,String>> scrapList = null;
//
//        if(scrapListXML != null && scrapListXML.length() > 0)
//            scrapList = xmlManager.getXMLtoListMap(scrapListXML);
//
//        List<CarrInfo> carrList = new ArrayList<CarrInfo>();
//
//        List<ScrapUnit> scrapUnitList = new ArrayList<ScrapUnit>();
//        List<ScrapStrip> scrapStripList = new ArrayList<ScrapStrip>();
//
//        // Dicing Type : TN_PPS_PROD_DEF.SHIP_UNIT_TYP
//        //				  Quad/Strip/Unit
//        if("Unit".equals(dicingType)) {
//            for(Map<String,Object> cellRow : dicingList) {
//                CarrInfo carrInfo = new CarrInfo();
//
//                carrInfo.setCarrId(cellRow.get("carrId").toString());
//                carrInfo.setCellQty(cellRow.get("cellQty").toString());
//                carrList.add(carrInfo);
//            }
//
//            if(scrapList != null) {
//                for(Map<String,String> subProdMtrl : scrapList) {
//                    ScrapUnit su = new ScrapUnit();
//                    su.setUnitId(subProdMtrl.get("SUB_PROD_MTRL_ID"));
//                    scrapUnitList.add(su);
//                }
//            }
//        }
//        // Dicing Type : Strip / Quarter
//        else {
//            for(Map<String,Object> cellRow : dicingList) {
//
//                CarrInfo carrInfo = new CarrInfo();
//                carrInfo.setCarrId(cellRow.get("carrId").toString());
//
//                List<Slots> slotList = new ArrayList<Slots>();
//
//                @SuppressWarnings("unchecked")
//                List<Map<String,String>> unitParamList = (List<Map<String,String>>)cellRow.get("unitList");
//
//                for(Map<String, String> unitRow : unitParamList) {
//                    Slots s = new Slots();
//                    s.setSlotNo(unitRow.get("slotNo"));
//                    s.setProdMtrlId(unitRow.get("subProdMtrlId"));
//                    slotList.add(s);
//                }
//                carrInfo.setSlots(slotList);
//                carrList.add(carrInfo);
//            }
//
//            if(scrapList != null) {
//                for(Map<String,String> subProdMtrl : scrapList) {
//                    ScrapStrip ss = new ScrapStrip();
//                    ss.setStripId(subProdMtrl.get("SUB_PROD_MTRL_ID"));
//                    scrapStripList.add(ss);
//                }
//            }
//        }
//
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,"WFS"
//                ,"BRS");
//
//        BrsLotTrackOutReqBody body = new BrsLotTrackOutReqBody();
//
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setLotId(lotId);
//        body.setCarrId(carrId);
//        body.setAutoReposition(autoReposition);
//        body.setAsgnQty("0");
//        body.setMtrlFaceCd("Top");
//        body.setCarrList(carrList);
//        if("Unit".equals(dicingType)) {
//            body.setScrapUnitList(scrapUnitList);
//        } else {
//            body.setScrapStripList(scrapStripList);
//        }
//
//        body.setUserId(WFS);
//        body.setWorkId(workId);
//
//        BrsLotTrackOutReq brsLotTrackOutReq = new BrsLotTrackOutReq();
//        brsLotTrackOutReq.setHead(head);
//        brsLotTrackOutReq.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsLotTrackOutReq);
//    }
//
//    public String getBRS_LOT_TRACK_OUT_REQ_DicingNew(String tid, String siteId, String eqpId, String portId, String lotId, String carrId, String recipeListXML, String workId, String autoReposition, String dicingType, String cellListXML, String scrapListXML) throws SAXException, IOException, ParserConfigurationException {
//        String targetCID = "BRS_LOT_TRACK_OUT_REQ";
//        XMLManager xmlManager = new XMLManager();
//        List<Map<String,Object>> dicingList = xmlManager.getTrackOutDicingMap(cellListXML);
//        List<Map<String,String>> scrapList = null;
//
//        if(scrapListXML != null && scrapListXML.length() > 0)
//            scrapList = xmlManager.getXMLtoListMap(scrapListXML);
//
//        List<CarrInfo> carrList = new ArrayList<CarrInfo>();
//
//        List<ScrapUnit> scrapUnitList = new ArrayList<ScrapUnit>();
//        List<ScrapStrip> scrapStripList = new ArrayList<ScrapStrip>();
//
//        String endMtrlFaceCd = "Top"; // 배출면 Dicing Default TOP
//
//        // Recipe 변경 작업 적용
//        XMLManager xmlMamager = new XMLManager();
//        Map<String,String> recipeMap = xmlMamager.getXMLtoListMap(recipeListXML).get(0);
//        String mtrlFaceCd = recipeMap.get("MTRL_FACE_CD");
//        String topRcpId = recipeMap.get("TOP_RCP_ID");
//        String bottomRcpId = recipeMap.get("BOTTOM_RCP_ID");
//
//        String recipeId = "";
//        if("Top".equals(mtrlFaceCd)) {
//            recipeId = topRcpId;
//        } else if("Bottom".equals(mtrlFaceCd)) {
//            recipeId = bottomRcpId;
//        } else if("Both".equals(mtrlFaceCd) || "Both_Flip".equals(mtrlFaceCd)) {
//            recipeId = topRcpId + "," +bottomRcpId;
//        }
//
//        // Dicing Type : TN_PPS_PROD_DEF.SHIP_UNIT_TYP
//        //				  Quad/Strip/Unit
//        if("Unit".equals(dicingType)) {
//            for(Map<String,Object> cellRow : dicingList) {
//                CarrInfo carrInfo = new CarrInfo();
//
//                carrInfo.setCarrId(cellRow.get("carrId").toString());
//                carrInfo.setCellQty(cellRow.get("cellQty").toString());
//                carrList.add(carrInfo);
//            }
//
//            if(scrapList != null) {
//                for(Map<String,String> subProdMtrl : scrapList) {
//                    ScrapUnit su = new ScrapUnit();
//                    su.setUnitId(subProdMtrl.get("SUB_PROD_MTRL_ID"));
//                    scrapUnitList.add(su);
//                }
//            }
//        }
//        // Dicing Type : Strip / Quarter
//        else {
//            for(Map<String,Object> cellRow : dicingList) {
//
//                CarrInfo carrInfo = new CarrInfo();
//                carrInfo.setCarrId(cellRow.get("carrId").toString());
//
//                List<Slots> slotList = new ArrayList<Slots>();
//
//                @SuppressWarnings("unchecked")
//                List<Map<String,String>> unitParamList = (List<Map<String,String>>)cellRow.get("unitList");
//
//                for(Map<String, String> unitRow : unitParamList) {
//                    Slots s = new Slots();
//                    s.setSlotNo(unitRow.get("slotNo"));
//                    s.setProdMtrlId(unitRow.get("subProdMtrlId"));
//                    slotList.add(s);
//                }
//                carrInfo.setSlots(slotList);
//                carrList.add(carrInfo);
//            }
//
//            if(scrapList != null) {
//                for(Map<String,String> subProdMtrl : scrapList) {
//                    ScrapStrip ss = new ScrapStrip();
//                    ss.setStripId(subProdMtrl.get("SUB_PROD_MTRL_ID"));
//                    scrapStripList.add(ss);
//                }
//            }
//        }
//
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,"WFS"
//                ,"BRS");
//
//        BrsLotTrackOutReqBody body = new BrsLotTrackOutReqBody();
//
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setLotId(lotId);
//        body.setCarrId(carrId);
//        body.setAutoReposition(autoReposition);
//        body.setAsgnQty("0");
//        body.setMtrlFaceCd(endMtrlFaceCd);
//        body.setRecipeId(recipeId);
//        body.setCarrList(carrList);
//        if("Unit".equals(dicingType)) {
//            body.setScrapUnitList(scrapUnitList);
//        } else {
//            body.setScrapStripList(scrapStripList);
//        }
//
//        body.setUserId(WFS);
//        body.setWorkId(workId);
//
//        BrsLotTrackOutReq brsLotTrackOutReq = new BrsLotTrackOutReq();
//        brsLotTrackOutReq.setHead(head);
//        brsLotTrackOutReq.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsLotTrackOutReq);
//    }
//
//    public String getCARR_ID_READ_REP(String tid, String siteId, String eqpId, String portId, String portType, String carrId, String slotMap) throws JsonProcessingException {
//        String targetCID = "EAP_CARR_ID_READ_REP";
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,"WFS"
//                ,"EAP"
//                ,""
//                ,eqpId);	//tgtEqp
//
//        EapCarrIdReadRepBody body = new EapCarrIdReadRepBody();
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setPortType(portType);
//        body.setCarrId(carrId);
//        body.setSlotMap(slotMap);
//        body.setUserId(WFS);
//
//        Reason reason = new Reason();
//        reason.setReasonCode("0");
//        body.setReason(reason);
//
//        EapCarrIdReadRep eapCarrIdReadRep = new EapCarrIdReadRep();
//        eapCarrIdReadRep.setHead(head);
//        eapCarrIdReadRep.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(eapCarrIdReadRep);
//    }
//
//
//    public String getBRS_LOT_TRACK_IN_CNFM_REQ(String tid, String siteId, String eqpId, String portId, String lotId, String carrId ) throws JsonProcessingException {
//        String targetCID = "BRS_LOT_TRACK_IN_CNFM_REQ";
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,"WFS"
//                ,"BRS");
//
//        BrsLotTrackInCnfmReqBody body = new BrsLotTrackInCnfmReqBody();
//
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setLotId(lotId);
//        body.setCarrId(carrId);
//        body.setUserId("WFS");
//
//        BrsLotTrackInCnfmReq brsLotTrackInCnfmReq = new BrsLotTrackInCnfmReq();
//        brsLotTrackInCnfmReq.setHead(head);
//        brsLotTrackInCnfmReq.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsLotTrackInCnfmReq);
//    }
//
//    public String getBRS_LOT_PROD_END_REPORT(String tid, String siteId, String eqpId, String subEqpId, String ppId, String inPortId, String inCarrierId, String lotId, String slotNo, String prodMtrlId, String workFace) throws JsonProcessingException {
//        String targetCID = "BRS_LOT_PROD_END_REPORT";
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,"WFS"
//                ,"BRS");
//
//        BrsLotProdEndReportBody body = new BrsLotProdEndReportBody();
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setSubEqpId(subEqpId);
//        body.setPpId(ppId);
//        body.setInPortId(inPortId);
//        body.setInCarrierId(inCarrierId);
//        body.setLotId(lotId);
//        body.setSlotNo(slotNo);
//        body.setProdMtrlId(prodMtrlId);
//
//        //Mtrl Face Cd 추가
//        String mtrlFaceCd = "";
//        if("TTT".equals(workFace)) mtrlFaceCd = "Top";
//        else if("TBT".equals(workFace)) mtrlFaceCd = "Bottom";
//        else mtrlFaceCd = workFace;
//
//        body.setMtrlFaceCd(mtrlFaceCd);
//
//        body.setUserId(WFS);
//
//        BrsLotProdEndReport brsLotProdEndReport = new BrsLotProdEndReport();
//        brsLotProdEndReport.setHead(head);
//        brsLotProdEndReport.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsLotProdEndReport);
//    }
//
//    public String getBRS_LOT_PROD_START_REPORT(String tid, String siteId, String eqpId, String subEqpId, String ppId, String inPortId, String inCarrierId, String lotId, String slotNo, String prodMtrlId, String workFace) throws JsonProcessingException {
//        String targetCID = "BRS_LOT_PROD_START_REPORT";
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,"WFS"
//                ,"BRS");
//
//        BrsLotProdStartReportBody body = new BrsLotProdStartReportBody();
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setSubEqpId(subEqpId);
//        body.setPpId(ppId);
//        body.setInPortId(inPortId);
//        body.setInCarrierId(inCarrierId);
//        body.setLotId(lotId);
//        body.setSlotNo(slotNo);
//        body.setProdMtrlId(prodMtrlId);
//
//        //Mtrl Face Cd 추가
//        String mtrlFaceCd = "";
//        if("TTT".equals(workFace)) mtrlFaceCd = "Top";
//        else if("TBT".equals(workFace)) mtrlFaceCd = "Bottom";
//        else mtrlFaceCd = workFace;
//
//        body.setMtrlFaceCd(mtrlFaceCd);
//        body.setUserId(WFS);
//
//        BrsLotProdStartReport brsLotProdStartReport = new BrsLotProdStartReport();
//        brsLotProdStartReport.setHead(head);
//        brsLotProdStartReport.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsLotProdStartReport);
//    }
//
//    public String getBRS_LOT_PROC_STARTED(String tid, String siteId, String eqpId, String portId, String lotId, String carrId) throws JsonProcessingException {
//        String targetCID = "BRS_LOT_PROC_STARTED";
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,"WFS"
//                ,"BRS");
//        BrsLotProcStartedBody body = new BrsLotProcStartedBody();
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setLotId(lotId);
//        body.setCarrId(carrId);
//
//        BrsLotProcStarted brsLotProcStarted = new BrsLotProcStarted();
//        brsLotProcStarted.setHead(head);
//        brsLotProcStarted.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsLotProcStarted);
//    }
//
//    public String getBRS_LOT_PROC_ENDED(String tid, String siteId, String eqpId, String portId, String lotId, String carrId) throws JsonProcessingException {
//        String targetCID = "BRS_LOT_PROC_ENDED";
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,"WFS"
//                ,"BRS");
//        BrsLotProcEndedBody body = new BrsLotProcEndedBody();
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setLotId(lotId);
//        body.setCarrId(carrId);
//
//        BrsLotProcEnded brsLotProcEnded = new BrsLotProcEnded();
//        brsLotProcEnded.setHead(head);
//        brsLotProcEnded.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsLotProcEnded);
//    }
//
//    public String getBRS_LOT_DEASSIGN_CARR(String tid, String siteId, String eqpId, String portId, String lotId, String carrId, String qty,  String slotNo, String prodMtrlId ) throws SAXException, IOException, ParserConfigurationException {
//        XMLManager xmlMamager = new XMLManager();
//
//        String targetCID = "BRS_LOT_CARR_DEASSIGN_REQ";
//
//
//
//        List<Slots> slotsArray = new ArrayList<Slots>();
//
//        Slots slot = new Slots();
//        slot.setSlotNo(slotNo);
//        slot.setProdMtrlId(prodMtrlId);
//        slotsArray.add(slot);
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,"WFS"
//                ,"BRS");
//
//        BrsLotDeassignCarrBody body = new BrsLotDeassignCarrBody();
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setLotId(lotId);
//        body.setCarrId(carrId);
//        body.setUserId(WFS);
//        body.setDeasgnQty(qty);
//        body.setSlots(slotsArray);
//        body.setIsCheckClnStat("false");
//        body.setIsCheckSorterResv("false");
//        body.setMdfyUserId(WFS);
//        body.setFnlEvntDt("");
//        body.setRsnCd("");
//        body.setTrnsCm("");
//
//        BrsLotDeassignCarr brsLotDeassignCarr = new BrsLotDeassignCarr();
//        brsLotDeassignCarr.setHead(head);
//        brsLotDeassignCarr.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsLotDeassignCarr);
//    }
//
//
//    public String getBRS_LOT_DEASSIGN_CARR(String tid, String siteId, String eqpId, String portId, String lotId, String carrId, String qty,  String slotXML ) throws SAXException, IOException, ParserConfigurationException {
//        XMLManager xmlMamager = new XMLManager();
//
//        String targetCID = "BRS_LOT_CARR_DEASSIGN_REQ";
//
//        List<Map<String,String>> slotList = xmlMamager.getXMLtoListMap(slotXML);
//
//
//        List<Slots> slotsArray = new ArrayList<Slots>();
//
//        for(Map<String,String> slotRow : slotList) {
//            Slots s = new Slots();
//
//            s.setSlotNo(slotRow.get("SLOT_NO"));
//            s.setProdMtrlId(slotRow.get("PROD_MTRL_ID"));
//            slotsArray.add(s);
//        }
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,"WFS"
//                ,"BRS");
//
//        BrsLotDeassignCarrBody body = new BrsLotDeassignCarrBody();
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setLotId(lotId);
//        body.setCarrId(carrId);
//        body.setUserId(WFS);
//        body.setDeasgnQty(qty);
//        body.setSlots(slotsArray);
//        body.setIsCheckClnStat("false");
//        body.setIsCheckSorterResv("false");
//        body.setMdfyUserId(WFS);
//        body.setFnlEvntDt("");
//        body.setRsnCd("");
//        body.setTrnsCm("");
//
//        BrsLotDeassignCarr brsLotDeassignCarr = new BrsLotDeassignCarr();
//        brsLotDeassignCarr.setHead(head);
//        brsLotDeassignCarr.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsLotDeassignCarr);
//    }
//
//    public String getRTD_DSP_WORK_REQ(String tid, String siteId, String dspType, String eqpId, String portId,
//                                      String lotId, String carrId, String prodDefId, String procDefId, String evntCm, String userId) throws JsonProcessingException {
//
//        String targetCID = "RTD_DSP_WORK_REQ";
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,"WFS"
//                ,"RTD");
//
//        RtdDspWorkReqBody body = new RtdDspWorkReqBody();
//        body.setSiteId(siteId);
//        body.setDspType(dspType);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setLotId(lotId);
//        body.setCarrId(carrId);
//        body.setProdDefId(prodDefId);
//        body.setProcDefId(procDefId);
//        body.setEvntCm(evntCm);
//        body.setEvntUserId(WFS);
//
//        RtdDspWorkReq rtdDspWorkReq = new RtdDspWorkReq();
//        rtdDspWorkReq.setHead(head);
//        rtdDspWorkReq.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(rtdDspWorkReq);
//    }
//
//
//    public String getCARR_SLOTMAP_REPORT_REP_Message(String tid, String siteId, String eqpId, String portId, String portType, String carrId, String slotMap) throws JsonProcessingException {
//        String targetCID = "EAP_CARR_SLOTMAP_REPORT_REP";
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,"WFS"
//                ,"EAP"
//                ,""
//                ,eqpId);	//tgtEqp
//
//
//        EapCarrSlotMapReportRepBody body = new EapCarrSlotMapReportRepBody();
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setPortType(portType);
//        body.setCarrId(carrId);
//        body.setSlotMap(slotMap);
//        body.setUserId(WFS);
//
//        Reason reason = new Reason();
//
//        //메세지 정상여부에 따라 reason 설정
//        reason.setReasonCode("0");
//        reason.setReasonComment("");
//
//        body.setReason(reason);
//
//        EapCarrSlotMapReportRep eapCarrSlotMapReportRep = new EapCarrSlotMapReportRep();
//
//
//        eapCarrSlotMapReportRep.setHead(head);
//        eapCarrSlotMapReportRep.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(eapCarrSlotMapReportRep);
//    }
//
//    public String getTOOL_COND_REQ(String tid, String siteId, String eqpId, String inPortId, String outPortId) throws JsonProcessingException {
//        String targetCID = "EAP_TOOL_COND_REQ";
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,"WFS"
//                ,"EAP"
//                ,""
//                ,eqpId);	//tgtEqp
//        EapToolCondReqBody body = new EapToolCondReqBody();
//
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setInPortId(inPortId);
//        body.setOutPortId(outPortId);
//
//        EapToolCondReq eapToolCondReq = new EapToolCondReq();
//
//        eapToolCondReq.setHead(head);
//        eapToolCondReq.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(eapToolCondReq);
//
//    }
//
//    public String getBRS_LOT_SORTER_JOB_EXECUTE(String tid, String siteId, String jobId, String srcLotId, String srcCarrId, String tgtLotId, String tgtCarrId, String sorterJobTyp, String dtlSorterJobTyp, String srcSlotNo, String srcProdMtrlId ) throws JsonProcessingException {
//        String targetCID = "BRS_LOT_SORTER_JOB_EXECUTE";
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,"WFS"
//                ,"BRS");
//
//        BrsLotSorterJobExecuteBody body = new BrsLotSorterJobExecuteBody();
//
//        body.setSiteId(siteId);
//        body.setJobId(jobId);
//        body.setSrcLotId(srcLotId);
//        body.setSrcCarrId(srcCarrId);
//        body.setTgtLotId(tgtLotId);
//        body.setTgtCarrId(tgtCarrId);
//        body.setSorterJobTyp(sorterJobTyp);
//        body.setDtlSorterJobTyp(dtlSorterJobTyp);
//
//        List<Slots> slotsArray = new ArrayList<Slots>();
//
//        String[] slotNoList = srcSlotNo.split(",");
//        String[] prodMtrlList = srcProdMtrlId.split(",");
//
//        // slotNo와 ProdMtrl List의 길이가 같지 않다면 오류
//        if(slotNoList.length != prodMtrlList.length) {
//            return "";
//        }
//
//        for(int i = 0; i < slotNoList.length; i++) {
//            Slots slot = new Slots();
//
//            slot.setSlotNo(slotNoList[i]);
//            slot.setProdMtrlId(prodMtrlList[i]);
//            slotsArray.add(slot);
//        }
//
//        body.setSlots(slotsArray);
//
//        BrsLotSorterJobExecute brsLotSorterJobExecute = new BrsLotSorterJobExecute();
//
//        brsLotSorterJobExecute.setHead(head);
//        brsLotSorterJobExecute.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsLotSorterJobExecute);
//    }
//
//    public String getEAP_SORTER_START_REQ(String tid, String siteId, String eqpId, String inCarrId, String inPortId, String outCarrId, String outPortId, String sorterType, String srcSlotNo, String srcProdMtrlId, String tgtSlotNo, String sorterJobId) throws JsonProcessingException {
//        String targetCID = "EAP_SORTER_START_REQ";
//
//        String[] srcSlotNoArr = srcSlotNo.split(",");
//        String[] srcProdMtrlArr = srcProdMtrlId.split(",");
//        String[] tgtSlotNoArr = tgtSlotNo.split(",");
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,WFS
//                ,"EAP"
//                ,""
//                ,eqpId);	//tgtEqp
//
//        EapSorterStartReqBody body = new EapSorterStartReqBody();
//
//        SorterInfo sorterInfo =  new SorterInfo();
//
//        sorterInfo.setInCarrId(inCarrId);
//        sorterInfo.setInPortId(inPortId);
//        sorterInfo.setOutCarrId(outCarrId);
//        sorterInfo.setOutPortId(outPortId);
//        sorterInfo.setSorterType(sorterType);
//        sorterInfo.setScanYn("N"); // parameter로 받도록 처리
//
//
//        List<SlotMap> slotMapList = new ArrayList<SlotMap>();
//
//        for(int i = 0; i < srcSlotNoArr.length; i++) {
//            SlotMap slotmap = new SlotMap();
//            slotmap.setInCarrSlotNo(srcSlotNoArr[i]);
//            slotmap.setOutCarrSlotNo(tgtSlotNoArr[i]);
//            slotmap.setprodMtrlId(srcProdMtrlArr[i]);
//
//            slotMapList.add(slotmap);
//        }
//
//        sorterInfo.setSlotMap(slotMapList);
//
//        body.setSorterInfo(sorterInfo);
//        body.setSorterJobId(sorterJobId);
//        body.setEqpId(eqpId);
//        body.setSiteId(siteId);
//        body.setUserId(WFS);
//
//        EapSorterStartReq eapSorterStartReq = new EapSorterStartReq();
//
//        eapSorterStartReq.setHead(head);
//        eapSorterStartReq.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(eapSorterStartReq);
//    }
//
//    public String getEAP_PPID_START_REQ(String siteId, String eqpId, String workId, String userId, String tid) throws JsonProcessingException {
//
//        String targetCID = "EAP_PPID_START_REQ";
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,"WFS"
//                ,"EAP"
//                ,""
//                ,eqpId);	//tgtEqp
//
//        EapPpIdStartReqBody body = new EapPpIdStartReqBody();
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setWorkId(workId);
//        body.setUserId(userId);
//
//        EapPpIdStartReq eapPpIdStartReq = new EapPpIdStartReq();
//
//        eapPpIdStartReq.setHead(head);
//        eapPpIdStartReq.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(eapPpIdStartReq);
//    }
//
//    public String getPANEL_ID_SCAN_REPORT_REP(String tid, String siteId, String eqpId, String portId, String carrId, String slotNo, String prodMtrlId, String userId, String reasonCode) throws JsonProcessingException {
//        String targetCID = "EAP_PANEL_ID_SCAN_REPORT_REP";
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,"WFS"
//                ,"EAP"
//                ,""
//                ,eqpId);	//tgtEqp
//
//        EapPanelIdScanReportRepBody body = new EapPanelIdScanReportRepBody();
//
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setCarrId(carrId);
//        body.setSlotNo(slotNo);
//        body.setProdMtrlId(prodMtrlId);
//        body.setUserId(userId);
//
//        Reason reason = new Reason();
//
//        //메세지 정상여부에 따라 reason 설정
//        reason.setReasonCode(reasonCode);
//        reason.setReasonComment("");
//
//        body.setReason(reason);
//
//        EapPanelIdScanReportRep eapPanelIdScanReportRep = new EapPanelIdScanReportRep();
//
//        eapPanelIdScanReportRep.setHead(head);
//        eapPanelIdScanReportRep.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(eapPanelIdScanReportRep);
//    }
//
//
//    public String getEAP_CELL_MAP_REP(String siteId, String tid, String cid, String eqpId, String portId, String carrId, String lotId, String cuttingType, String slotXML) throws SAXException, IOException, ParserConfigurationException {
//
//        XMLManager xmlMamager = new XMLManager();
//
//        String targetCID = "EAP_CELL_MAP_REP";
//        List<Map<String,String>> cellMapList = xmlMamager.getXMLtoListMap(slotXML);
//
//
//        Head head = new Head(targetCID
//                ,tid
//                ,""
//                ,""
//                ,"WFS"
//                ,"EAP"
//                ,""
//                ,eqpId);	//tgtEqp
//
//        EapCellMapRepBody body = new EapCellMapRepBody();
//
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setCarrId(carrId);
//
//        Lot lot = new Lot();
//
//        lot.setLotId(lotId);
//
//        List<SlotMapCell> slotMapList = new ArrayList<SlotMapCell>();
//
//        //unit
//        if("1".equals(cuttingType)) {
//            for(Map<String,String> cellRow : cellMapList) {
//                SlotMapCell s = new SlotMapCell();
//                s.setSlotNo(cellRow.get("SLOT_NO"));
//                s.setProdMtrlId(cellRow.get("PROD_MTRL_ID"));
//
//                String subMtrlGrdCd = cellRow.get("MDFY_SUB_MTRL_GRD_CD");
//                String cellMap = "";
//                for(char c : subMtrlGrdCd.toCharArray()) {
//                    cellMap += c + ",";
//                }
//                cellMap = cellMap.substring(0, cellMap.length() - 1);
//
//                s.setCellMap(cellMap);
//
//                slotMapList.add(s);
//            }
//        }
//        //strip, quad
//        else {
//
//
//            for(Map<String,String> cellRow : cellMapList) {
//                SlotMapCell s = new SlotMapCell();
//                s.setSlotNo(cellRow.get("SLOT_NO"));
//                s.setProdMtrlId(cellRow.get("PROD_MTRL_ID"));
//                s.setCellMap(cellRow.get("CELL_MAP"));
//
//                slotMapList.add(s);
//            }
//        }
//
//
//
//        lot.setSlotMap(slotMapList);
//
//        List<Lot> lotList = new ArrayList<Lot>();
//        lotList.add(lot);
//        body.setLot(lotList);
//
//
//        EapCellMapRep eapCellMapRep = new EapCellMapRep();
//        eapCellMapRep.setHead(head);
//        eapCellMapRep.setBody(body);
//
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(eapCellMapRep);
//    }
//
//    // SIM 테스트 정보
//    public String getSIM_SET_TEST_INFO(String eqpId, String inCarrId, String inPortId, String lotId, String outCarrId, String outPortId, String rcpId, String slotCnt) throws JsonProcessingException {
//
//        String targetCID = "SIM_SET_TEST_INFO";
//
//        Head head = new Head(targetCID
//                ,""
//                ,""
//                ,""
//                ,WFS
//                ,"EAP"
//                ,""
//                ,eqpId);	//tgtEqp
//
//        TestInfo TestInfo = new TestInfo();
//
//        TestInfo.setEqpId(eqpId);
//        TestInfo.setInCarrId(inCarrId);
//        TestInfo.setInPortId(inPortId);
//        TestInfo.setLotId(lotId);
//        TestInfo.setOutCarrId(outCarrId);
//        TestInfo.setOutPortId(outPortId);
//        TestInfo.setRcpId(rcpId);
//        TestInfo.setSlotCount(slotCnt);
//
//        SimSetTestInfoBody simSetTestInfoBody = new SimSetTestInfoBody();
//        simSetTestInfoBody.setTestInfo(TestInfo);
//
//        SimSetTestInfo simSetTestInfo = new SimSetTestInfo();
//        simSetTestInfo.setHead(head);
//        simSetTestInfo.setBody(simSetTestInfoBody);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(simSetTestInfo);
//    }
//
//    // SIM Load Complete 요청
//    public String getSIM_LOAD_COMP(String eqpId, String portId) throws JsonProcessingException {
//        String targetCID = "SIM_LOAD_COMP";
//
//        Head head = new Head(targetCID
//                ,""
//                ,""
//                ,""
//                ,WFS
//                ,"EAP"
//                ,""
//                ,eqpId);	//tgtEqp
//
//        SimBody simBody = new SimBody();
//        simBody.setEqpId(eqpId);
//        simBody.setPortId(portId);
//
//        SimLoadComp simLoadComp = new SimLoadComp();
//        simLoadComp.setHead(head);
//        simLoadComp.setBody(simBody);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(simLoadComp);
//    }
//
//    // SIM UnLoad Complete 요청
//    public String getSIM_UNLOAD_COMP(String eqpId, String portId) throws JsonProcessingException {
//        String targetCID = "SIM_UNLOAD_COMP";
//
//        Head head = new Head(targetCID
//                ,""
//                ,""
//                ,""
//                ,WFS
//                ,"EAP"
//                ,""
//                ,eqpId);	//tgtEqp
//
//        SimBody simBody = new SimBody();
//        simBody.setEqpId(eqpId);
//        simBody.setPortId(portId);
//
//        SimUnloadComp simUnloadComp = new SimUnloadComp();
//        simUnloadComp.setHead(head);
//        simUnloadComp.setBody(simBody);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(simUnloadComp);
//    }
//
//
//    /**
//     * BRS_EQP_CONTROL_MODE_CHANGE
//     * @param siteId
//     * @param eqpId
//     * @param ctrlModeCd
//     * @param rsnCd
//     * @param trnsCm
//     * @param userId
//     * @return
//     * @throws JsonProcessingException
//     */
//    public String getEQP_CONTROL_MODE_CHANGE(String siteId, String eqpId, String ctrlModeCd, String rsnCd, String trnsCm, String userId) throws JsonProcessingException {
//        String targetCID = "BRS_EQP_CONTROL_MODE_CHANGE";
//
//        Head head = new Head(targetCID
//                ,""
//                ,""
//                ,""
//                ,WFS
//                ,"BRS"
//                ,""
//                ,"");	//tgtEqp
//
//        BrsEqpControlModeChangeBody body = new BrsEqpControlModeChangeBody();
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setCtrlModeCd(ctrlModeCd);
//        body.setMdfyUserId(userId);
//        body.setRsnCd(rsnCd);
//        body.setTrnsCm(trnsCm);
//
//        BrsEqpControlModeChange brsEqpControlModeChange = new BrsEqpControlModeChange();
//
//        brsEqpControlModeChange.setHead(head);
//        brsEqpControlModeChange.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsEqpControlModeChange);
//    }
//
//
//    /**
//     * EQP PROC STATE CHANGE
//     */
//    public String getEQP_PROC_STATE_CHANGE(String siteId, String eqpId, String procStatCd, String rsnCd, String trnsCm, String userId) throws JsonProcessingException {
//        String targetCID = "BRS_EQP_PROC_STATE_CHANGE";
//
//        Head head = new Head(targetCID
//                ,""
//                ,""
//                ,""
//                ,WFS
//                ,"BRS"
//                ,""
//                ,"");	//tgtEqp
//
//        BrsEqpProcStateChangeBody body = new BrsEqpProcStateChangeBody();
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        String procStatCdStr = "";
//        if("RUN".equals(procStatCd)) {
//            procStatCdStr = "Run";
//        } else if("IDLE".equals(procStatCd)) {
//            procStatCdStr = "Idle";
//        }
//        else {
//            procStatCdStr = procStatCd;
//        }
//        body.setProcStatCd(procStatCdStr);
//        body.setMdfyUserId(userId);
//        body.setRsnCd(rsnCd);
//        body.setTrnsCm(trnsCm);
//
//        BrsEqpProcStateChange brsEqpProcStateChange = new BrsEqpProcStateChange();
//        brsEqpProcStateChange.setHead(head);
//        brsEqpProcStateChange.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsEqpProcStateChange);
//    }
//
//
//    public String getPORT_STATE_CHANGE(String siteId, String eqpId, String portId, String statCd, String rsnCd, String trnsCm, String userId) throws JsonProcessingException {
//        String targetCID = "BRS_PORT_STATE_CHANGE";
//
//        Head head = new Head(targetCID
//                ,""
//                ,""
//                ,""
//                ,WFS
//                ,"BRS"
//                ,""
//                ,"");	//tgtEqp
//
//        BrsPortStateChangeBody body = new BrsPortStateChangeBody();
//
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setStatCd(statCd);
//        body.setMdfyUserId(userId);
//        body.setRsnCd(rsnCd);
//        body.setTrnsCm(trnsCm);
//
//        BrsPortStateChange brsPortStateChange = new BrsPortStateChange();
//        brsPortStateChange.setHead(head);
//        brsPortStateChange.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsPortStateChange);
//    }
//
//
//    public String getPORT_TRANSFER_STATE_CHANGE(String siteId, String eqpId, String portId, String trsfStatCd, String rsnCd, String trnsCm, String userId) throws JsonProcessingException {
//        String targetCID = "BRS_PORT_TRANSFER_STATE_CHANGE";
//
//        Head head = new Head(targetCID
//                ,""
//                ,""
//                ,""
//                ,WFS
//                ,"BRS"
//                ,""
//                ,"");	//tgtEqp
//
//        BrsPortTransferStateChangeBody body = new BrsPortTransferStateChangeBody();
//
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setTrsfStatCd(trsfStatCd);
//        body.setMdfyUserId(userId);
//        body.setRsnCd(rsnCd);
//        body.setTrnsCm(trnsCm);
//
//        BrsPortTransferStateChange brsPortTransferStateChange = new BrsPortTransferStateChange();
//        brsPortTransferStateChange.setHead(head);
//        brsPortTransferStateChange.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsPortTransferStateChange);
//    }
//
//    public String getBRS_EQP_KIT(String tid, String siteId, String eqpId, String xmlSpecInfoList
//    ) throws SAXException, IOException, ParserConfigurationException {
//        String cid = "BRS_EQP_KIT";
//
//        XMLManager xmlMamager = new XMLManager();
//
//        List<Map<String,String>> specInfoList = xmlMamager.getXMLtoListMap(xmlSpecInfoList, "specInfo");
//
//        Head head = new Head(cid
//                ,tid
//                ,""
//                ,""
//                ,WFS
//                ,"BRS"
//                ,""
//                ,"");
//
//        BrsEqpKitBody body = new BrsEqpKitBody();
//
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setMdfyUserId("WFS");
//
//        List<MaterialDurable> MaterialDurableList = new ArrayList<MaterialDurable>();
//        for(Map<String, String> specInfo : specInfoList) {
//
//            MaterialDurable materialDurable = new MaterialDurable();
//            materialDurable.setMaterialDurableType(specInfo.get("specType"));
//            materialDurable.setMtrlDrblId(specInfo.get("specId"));
//
//            materialDurable.setSubEqpId(specInfo.get("subEqpId"));
//            materialDurable.setDrblUseCnt(specInfo.get("specUseCnt"));
//            materialDurable.setDrblRateUseCnt(specInfo.get("specRateUseCnt"));
//
//            String positionCd = specInfo.get("specPosition");
//            if(positionCd.equals("Top") || positionCd.equals("Bottom")) {
//                materialDurable.setMtrlFaceCd(positionCd);
//            }else {
//                materialDurable.setPstnCd(positionCd);
//            }
//            MaterialDurableList.add(materialDurable);
//
//        }
//
//        body.setMaterialDurableList(MaterialDurableList);
//        BrsEqpKit brsEqpKit = new BrsEqpKit();
//
//        brsEqpKit.setHead(head);
//        brsEqpKit.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsEqpKit);
//    }
//
//    public String getEQP_DEKIT(String siteId, String eqpId, String mtrlDrblTyp, String mtrlDbrlId, String userId) throws JsonProcessingException {
//        String targetCID = "BRS_EQP_DEKIT";
//
//        Head head = new Head(targetCID
//                ,""
//                ,""
//                ,""
//                ,WFS
//                ,"BRS"
//                ,""
//                ,"");	//tgtEqp
//
//        BrsEqpDekitBody body = new BrsEqpDekitBody();
//
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setMtrlDrblTyp(mtrlDrblTyp);
//        body.setMdfyUserId(userId);
//
//        BrsEqpDekit brsEqpDekit = new BrsEqpDekit();
//
//        brsEqpDekit.setHead(head);
//        brsEqpDekit.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsEqpDekit);
//    }
//
//
//    public String getDRBL_USE(String siteId, String lotId, String rsnCd, String userId, String trnsCm, String drblId, String drblUseQty) throws JsonProcessingException {
//        String targetCID = "BRS_DRBL_USE";
//
//        Head head = new Head(targetCID
//                ,""
//                ,""
//                ,""
//                ,WFS
//                ,"BRS"
//                ,""
//                ,"");	//tgtEqp
//
//
//        BrsDrblUseBody body = new BrsDrblUseBody();
//        body.setSiteId(siteId);
//        body.setLotId(lotId);
//        body.setRsnCd(rsnCd);
//        body.setMdfyUserId(userId);
//        body.setTrnsCm(trnsCm);
//
//        List<Drbl> drblList = new ArrayList<Drbl>();
//
//        Drbl drbl = new Drbl();
//
//        drbl.setDrblId(drblId);
//        drbl.setDrblUseQty(drblUseQty);
//
//        drblList.add(drbl);
//
//        body.setDrblList(drblList);
//
//        BrsDrblUse brsDrblUse = new BrsDrblUse();
//
//        brsDrblUse.setHead(head);
//        brsDrblUse.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsDrblUse);
//    }
//
//    public String getLOT_CONSUME_MTRL(String siteId, String lotId, String mtrlId, String qty) throws JsonProcessingException {
//
//        String targetCID = "BRS_LOT_CONSUME_MTRL";
//
//        Head head = new Head(targetCID
//                ,""
//                ,""
//                ,""
//                ,WFS
//                ,"BRS"
//                ,""
//                ,"");	//tgtEqp
//
//
//        BrsLotConsumeMtrlBody body = new BrsLotConsumeMtrlBody();
//        body.setSiteId(siteId);
//        body.setLotId(lotId);
//
//        List<Mtrl> mtrlList = new ArrayList<Mtrl>();
//        Mtrl m = new Mtrl();
//        m.setMtrlLotId(mtrlId);
//        m.setQty(qty);
//
//        mtrlList.add(m);
//
//        body.setMtrlList(mtrlList);
//
//        BrsLotConsumeMtrl brsLotConsumeMtrl = new BrsLotConsumeMtrl();
//        brsLotConsumeMtrl.setHead(head);
//        brsLotConsumeMtrl.setBody(body);
//        return mapper.writeValueAsString(brsLotConsumeMtrl);
//
//    }
//
//    public String getLOT_DCOL_DATA_SAVE(String siteId, String lotId, String prodMtrlId, String prodDefId, String procDefId, String procSgmtId, String eqpId, String subEqpId, String mtrlFace, String userId, String dcolXml) throws SAXException, IOException, ParserConfigurationException {
//        String targetCID = "BRS_LOT_MEAS_DATA_SAVE";
//
//
//        XMLManager xmlMamager = new XMLManager();
//        List<Map<String,Object>> dcolDataList = xmlMamager.getDcolMap(dcolXml);
//
//        Head head = new Head(targetCID
//                ,""
//                ,""
//                ,""
//                ,WFS
//                ,"BRS"
//                ,""
//                ,"");	//tgtEqp
//
//        BrsLotDcolDataSaveBody body = new BrsLotDcolDataSaveBody();
//        body.setSiteId(siteId);
//        body.setLotId(lotId);
//        body.setProdDefId(prodDefId);
//        body.setProcDefId(procDefId);
//        body.setProcSgmtId(procSgmtId);
//        body.setMtrlId(prodMtrlId);
//        body.setMtrlTyp("ProdMtrl");
//        body.setEqpId(eqpId);
//        body.setSubEqpId(subEqpId);
//        body.setDcdataSgmtId(procSgmtId);
//        body.setUserId(userId);
//
//        //Mtrl Face Cd 추가
//        String mtrlFaceCd = "";
//        if("TTT".equals(mtrlFace)) mtrlFaceCd = "Top";
//        else if("TBT".equals(mtrlFace)) mtrlFaceCd = "Bottom";
//        else mtrlFaceCd = "Top";
//
//        body.setMtrlFaceCd(mtrlFaceCd);
//
//
//        //body.setEvntNm("BRS_LOT_MEAS_DATA_SAVE");
//
//        List<DcolData> dcolList = new ArrayList<DcolData>();
//
//        // to-do
//        // 테스트 임시 하드코딩
//        String xVal = "0";
//        String yVal = "0";
//        String zVal = "0";
//        int count = 1;
//        for(Map<String,Object> m : dcolDataList) {
//            @SuppressWarnings("unchecked")
//            List<Map<String,String>> paraNameList = (List<Map<String,String>>)m.get("paraNameList");
//
//            if(paraNameList == null) {
//                DcolData dcolData = new DcolData();
//
//                // to-do
//                // 작업면 eap로부터 받아오기
////				dcolData.setMtrlFaceCd("Top");
//                dcolData.setXval(xVal + count);
//                dcolData.setYval(yVal + count);
//                dcolData.setZval(zVal + count);
//
//                dcolData.setDcitemId(m.get("pName").toString());
//                dcolData.setRsltVal(m.get("pValue").toString());
//                dcolData.setMtrlFaceCd(mtrlFaceCd);
//                dcolList.add(dcolData);
//
//                count++;
//            } else {
//                for(Map<String, String> paraNameRow : paraNameList) {
//                    DcolData dcolData = new DcolData();
//                    dcolData.setDcitemId(m.get("pName").toString());
//                    dcolData.setDcsiteId(paraNameRow.get("site"));
//                    dcolData.setRsltVal(paraNameRow.get("value"));
//                    dcolData.setMtrlFaceCd(mtrlFaceCd);
//                    dcolList.add(dcolData);
//                }
//            }
//        }
//
//        body.setDcolDataList(dcolList);
//
//        BrsLotDcolDataSave brsLotDcolDataSave = new BrsLotDcolDataSave();
//        brsLotDcolDataSave.setHead(head);
//        brsLotDcolDataSave.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsLotDcolDataSave);
//    }
//
//    /**
//     * 삭제대상 임시
//     * @param siteId
//     * @param eqpId
//     * @param portId
//     * @param carrId
//     * @return
//     * @throws JsonProcessingException
//     */
//    public String getEAP_CARR_CANCEL_REQ(String siteId, String eqpId, String portId, String carrId) throws JsonProcessingException {
//        String targetCID = "EAP_CARR_CANCEL_REQ";
//
//        Head head = new Head(targetCID
//                ,""
//                ,""
//                ,""
//                ,WFS
//                ,"EAP"
//                ,""
//                ,eqpId);	//tgtEqp
//
//        EapCarrCancelReqBody body = new EapCarrCancelReqBody();
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setPortType("BP");
//        body.setCarrId(carrId);
//
//        EapCarrCancelReq eapCarrCancelReq = new EapCarrCancelReq();
//        eapCarrCancelReq.setHead(head);
//        eapCarrCancelReq.setBody(body);
//
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//
//        return mapper.writeValueAsString(eapCarrCancelReq);
//    }
//
//    public String getEAP_CARR_CANCEL_REQ(String siteId, String eqpId, String portId, String portTyp, String carrId) throws JsonProcessingException {
//        String targetCID = "EAP_CARR_CANCEL_REQ";
//
//        Head head = new Head(targetCID
//                ,""
//                ,""
//                ,""
//                ,WFS
//                ,"EAP"
//                ,""
//                ,eqpId);	//tgtEqp
//
//        EapCarrCancelReqBody body = new EapCarrCancelReqBody();
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setPortType(portTyp);
//        body.setCarrId(carrId);
//
//        EapCarrCancelReq eapCarrCancelReq = new EapCarrCancelReq();
//        eapCarrCancelReq.setHead(head);
//        eapCarrCancelReq.setBody(body);
//
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//
//        return mapper.writeValueAsString(eapCarrCancelReq);
//    }
//
//    public String getEAP_CARR_RECHUCK_REQ(String siteId, String eqpId, String portId, String portType, String carrId, String userId) throws JsonProcessingException {
//        String targetCID = "EAP_CARR_RECHUCK_REQ";
//
//        Head head = new Head(targetCID
//                ,""
//                ,""
//                ,""
//                ,WFS
//                ,"EAP"
//                ,""
//                ,eqpId);	//tgtEqp
//
//        EapCarrRechuckReqBody body = new EapCarrRechuckReqBody();
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setPortType(portType);
//        body.setCarrId(carrId);
//        body.setUserId(userId);
//
//        EapCarrRechuckReq eapCarrRechuckReq = new EapCarrRechuckReq();
//        eapCarrRechuckReq.setHead(head);
//        eapCarrRechuckReq.setBody(body);
//
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//
//        return mapper.writeValueAsString(eapCarrRechuckReq);
//    }
//
//    public String getEAP_WORK_CANCEL_REQ(String siteId, String eqpId, String portId, String portType, String worKId, String userId) throws JsonProcessingException {
//        String targetCID = "EAP_WORK_CANCEL_REQ";
//
//        Head head = new Head(targetCID
//                ,""
//                ,""
//                ,""
//                ,WFS
//                ,"EAP"
//                ,""
//                ,eqpId);	//tgtEqp
//
//        EapWorkCancelReqBody body = new EapWorkCancelReqBody();
//        body.setSiteId(siteId);
//        body.setEqpId(eqpId);
//        body.setPortId(portId);
//        body.setPortType(portType);
//        body.setWorkId(worKId);
//        body.setUserId(userId);
//
//        EapWorkCancelReq eapWorkCancelReq = new EapWorkCancelReq();
//        eapWorkCancelReq.setHead(head);
//        eapWorkCancelReq.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//
//        return mapper.writeValueAsString(eapWorkCancelReq);
//    }
//
//    public String getBRS_LOT_SCRAP(String siteId, String lotId, String userId, String slotNo, String prodMtrlId, String rsnCd)  throws JsonProcessingException {
//        String targetCID = "BRS_LOT_SCRAP_REPORT";
//
//        Head head = new Head(targetCID
//                ,""
//                ,""
//                ,""
//                ,WFS
//                ,"BRS"
//                ,""
//                ,"");	//tgtEqp
//
//        List<Slots> slotsArray = new ArrayList<Slots>();
//        Slots slot = new Slots();
//        slot.setSlotNo(slotNo);
//        slot.setProdMtrlId(prodMtrlId);
//        slotsArray.add(slot);
//
//        BrsLotScrapReportBody body = new BrsLotScrapReportBody();
//
//        body.setSiteId(siteId);
//        body.setLotId(lotId);
//        body.setSlots(slotsArray);
//        body.setMdfyUserId(userId);
//
//        // BRS확인
//        body.setRsnCd("Defect");
//        body.setTrnsCm("Scrap");
//
//        BrsLotScrapReport brsLotScrap = new BrsLotScrapReport();
//
//        brsLotScrap.setHead(head);
//        brsLotScrap.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//
//        return mapper.writeValueAsString(brsLotScrap);
//    }
//
//    public String getBRS_EQP_SORTER_MODE_CHAGE(String siteId, String userId, String eqpId, String modeType) throws JsonProcessingException {
//        String targetCID = "BRS_EQP_SORTER_MODE_CHANGE";
//
//        Head head = new Head(targetCID
//                ,""
//                ,""
//                ,""
//                ,WFS
//                ,"BRS"
//                ,""
//                ,"");	//tgtEqp
//
//        BrsEqpSorterModeChangeBody body = new BrsEqpSorterModeChangeBody();
//
//        body.setSiteId(siteId);
//        body.setMdfyUserId(userId);
//        body.setEqpId(eqpId);
//
//        String sorterModeYn = "N";
//        if("Sorter".equals(modeType))
//            sorterModeYn = "Y";
//        body.setSorterModeYn(sorterModeYn);
//
//        BrsEqpSorterModeChange brsEqpSorterModeChange = new BrsEqpSorterModeChange();
//
//        brsEqpSorterModeChange.setHead(head);
//        brsEqpSorterModeChange.setBody(body);
//
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsEqpSorterModeChange);
//
//    }
//
//    /**
//     * 자주검사 NG 시 BRS 전송 Message
//     * @param siteId
//     * @param lotId
//     * @param eqpId
//     * @param recipeId
//     * @param carrId
//     * @param slotXML
//     * @param userId
//     * @param workId
//     * @return
//     * @throws ParserConfigurationException
//     * @throws IOException
//     * @throws SAXException
//     */
//    public String getBRS_LOT_SELF_INSP_NG(String siteId, String lotId, String eqpId, String recipeId, String carrId, String slotXML, String userId, String workId) throws SAXException, IOException, ParserConfigurationException {
//        String targetCID = "BRS_LOT_SELF_INSP_NG";
//
//        Head head = new Head(targetCID
//                ,""
//                ,""
//                ,""
//                ,WFS
//                ,"BRS"
//                ,""
//                ,"");	//tgtEqp
//
//        BrsLotSelfInspNgBody body = new BrsLotSelfInspNgBody();
//
//        body.setSiteId(siteId);
//        body.setLotId(lotId);
//        body.setEqpId(eqpId);
//        body.setCarrId(carrId);
//        body.setRecipeId(recipeId);
//        body.setUserId(userId);
//        body.setWorkId(workId);
//
//        XMLManager xmlMamager = new XMLManager();
//
//        @SuppressWarnings("unchecked")
//        List<Map<String,String>> slotList = xmlMamager.getXMLtoListMap(slotXML, "processSlotMap");
//
//        List<Slots> slots  = new ArrayList<Slots>();
//
//        for(Map<String,String> slotRow : slotList) {
//            Slots s = new Slots();
//
//            s.setSlotNo(slotRow.get("slotNo"));
//            s.setProdMtrlId(slotRow.get("prodMtrlId"));
//            slots.add(s);
//        }
//
//
//        body.setSlots(slots);
//
//
//        BrsLotSelfInspNg brsLotSelfInspNg = new BrsLotSelfInspNg();
//
//        brsLotSelfInspNg.setHead(head);
//        brsLotSelfInspNg.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(brsLotSelfInspNg);
//    }
//
//    public String EAP_BUZZER_REQ(String siteId, String tid, String eqpId, String signal, String userId) throws JsonProcessingException {
//
//        String targetCID = "EAP_BUZZER_REQ";
//
//        Head head = new Head(targetCID
//                ,""
//                ,""
//                ,""
//                ,WFS
//                ,"EAP"
//                ,""
//                ,"");	//tgtEqp
//
//        EapBuzzerReqBody body = new EapBuzzerReqBody();
//
//        body.setSiteId(siteId);
//        body.setSignal(signal);
//        body.setEqpId(eqpId);
//        body.setUserId(userId);
//
//        EapBuzzerReq eapBuzzerReq = new EapBuzzerReq();
//        eapBuzzerReq.setHead(head);
//        eapBuzzerReq.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(eapBuzzerReq);
//    }
//
//    public String EAP_TERMINAL_MESSAGE_REQ(String siteId, String tid, String eqpId, String message, String userId) throws JsonProcessingException {
//
//        String targetCID = "EAP_TERMINAL_MESSAGE_REQ";
//
//        Head head = new Head(targetCID
//                ,""
//                ,""
//                ,""
//                ,WFS
//                ,"EAP"
//                ,""
//                ,"");	//tgtEqp
//
//        EapTerminalMessageReqBody body = new EapTerminalMessageReqBody();
//
//        body.setSiteId(siteId);
//        body.setMessage(message);
//        body.setEqpId(eqpId);
//        body.setUserId(userId);
//
//        EapTerminalMessageReq eapTerminalMessageReq = new EapTerminalMessageReq();
//        eapTerminalMessageReq.setHead(head);
//        eapTerminalMessageReq.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(eapTerminalMessageReq);
//    }
//
//
//    public String getEAP_GROOVE_CHANGE_REP(String tid, String siteId, String eqpId, String subEqpId, String specId, String result, String comment, String grooveType, String rgLimit, String fgLimit, String k, String f, String d, String useDist, String rateUseDist, String userId) throws JsonProcessingException {
//        String cid = "EAP_GROOVE_CHANGE_REP";
//        Head head = new Head(cid,
//                tid,
//                "",
//                "",
//                "WFS",
//                "EAP");
//
//        EapGrooveChangeRepBody body = new EapGrooveChangeRepBody();
//        body.setSiteId(siteId);
//        body.setSpecId(specId);
//        body.setEqpId(eqpId);
//        body.setSubEqpId(eqpId);
//        body.setResult(result);
//        body.setComment(comment);
//        body.setGrooveType(grooveType);
//        body.setRgLimit(rgLimit);
//        body.setFgLimit(fgLimit);
//
//        ToolInfo toolInfo = new ToolInfo();
//        toolInfo.setK(k);
//        toolInfo.setF(f);
//        toolInfo.setD(d);
//        toolInfo.setUseDist(useDist);
//        toolInfo.setRateUseDist(rateUseDist);
//
//        body.setToolInfo(toolInfo);
//
//
//        EapGrooveChangeRep eapGrooveChangeRep = new EapGrooveChangeRep();
//        eapGrooveChangeRep.setHead(head);
//        eapGrooveChangeRep.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(eapGrooveChangeRep);
//    }
//
//    public String getEAP_INIT_EQP_STATE_REQ(String tid, String sitedId, String eqpId) throws JsonProcessingException{
//        String cid = "EAP_INIT_EQP_STATE_REQ";
//        Head head = new Head(cid, tid, "", "", "WFS", "EAP");
//
//        EapInitEqpStateReqBody body = new EapInitEqpStateReqBody();
//        body.setSiteId(sitedId);
//        body.setEqpId(eqpId);
//
//        EapInitEqpStateReq eapInitEqpStateReq = new EapInitEqpStateReq();
//        eapInitEqpStateReq.setHead(head);
//        eapInitEqpStateReq.setBody(body);
//
//        mapper.setSerializationInclusion(Include.NON_NULL);
//        return mapper.writeValueAsString(eapInitEqpStateReq);
//    }

    /**
     * EAP
     */
}
