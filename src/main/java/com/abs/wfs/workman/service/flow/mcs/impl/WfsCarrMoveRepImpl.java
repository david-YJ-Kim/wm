//package com.abs.wfs.workman.service.flow.mcs.impl;
//
//import com.abs.wfs.workman.service.flow.mcs.WfsCarrMoveRep;
//import com.abs.wfs.workman.spec.common.ApMessageResultVo;
//import com.abs.wfs.workman.spec.in.mcs.WfsCarrMoveRepVo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class WfsCarrMoveRepImpl implements WfsCarrMoveRep {
//
//    private String cid;
//
//    private WfsCarrMoveRepVo.WfsCarrMoveRepBody wfsCarrMoveRepBody;
//    private long executeStartTime;
//
//
//    @Override
//    public void init(String cid, Object messageObj) {
//        this.cid = cid;
//        this.wfsCarrMoveRepBody = ((WfsCarrMoveRepVo) messageObj).getBody();
//        this.executeStartTime = System.currentTimeMillis();
//
//    }
//
//    @Override
//    public ApMessageResultVo execute(String messageId) throws Exception {
//
//        // query
//        // TODO CarrMoveReqQuery
//        /**
//         * SELECT OBJ_ID, JOB_ID, CARR_ID, CRNT_EQP_ID, CRNT_PORT_ID, DEST_EQP_ID,
//         *   DEST_PORT_ID
//         *   FROM WN_TRANSFER_JOB
//         *   WHERE SITE_ID = ? AND MOVE_STAT_CD = ? AND JOB_ID = ?
//         */
//
//        // if(CarrMoveReqQuery is not exist)
//        // then throw Exception
//
//        if(!(this.wfsCarrMoveRepBody.getResultCode().trim()).equals("0")){
//            // TODO updateMoveStatFailed  "Failed"
//            // TODO updateWipStatStandby "Standby"
//        }else{
//
//            // TODO updateMoveStatCd "Queued"
//            // TODO updateWipStat "Transfer"
//        }
//
//
//
//        return null;
//    }
//
//}
