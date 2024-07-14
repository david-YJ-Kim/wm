package com.abs.wfs.workman.service.flow.mcs.impl;

import com.abs.wfs.workman.service.flow.mcs.WfsCarrMoveRep;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.mcs.WfsCarrMoveRepIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WfsCarrMoveRepImpl implements WfsCarrMoveRep {




    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return  WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }

    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsCarrMoveRepIvo wfsCarrMoveRepIvo) throws Exception {

        // query
        // TODO CarrMoveReqQuery
        /**
         * SELECT OBJ_ID, JOB_ID, CARR_ID, CRNT_EQP_ID, CRNT_PORT_ID, DEST_EQP_ID,
         *   DEST_PORT_ID
         *   FROM WN_TRANSFER_JOB
         *   WHERE SITE_ID = ? AND MOVE_STAT_CD = ? AND JOB_ID = ?
         */

        // if(CarrMoveReqQuery is not exist)
        // then throw Exception


        return null;
    }
}
