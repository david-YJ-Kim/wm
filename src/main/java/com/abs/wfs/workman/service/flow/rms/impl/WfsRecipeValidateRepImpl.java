//package com.abs.wfs.workman.service.flow.rms.impl;
//
//import com.abs.wfs.workman.service.flow.rms.WfsRecipeValidateRep;
//import com.abs.wfs.workman.spec.common.ApMessageResultVo;
//import com.abs.wfs.workman.spec.in.rms.WfsRecipeValidateRepVo;
//import com.abs.wfs.workman.dao.query.lot.service.LotQueryServiceImpl;
//import com.abs.wfs.workman.dao.query.lot.vo.QueryLotVo;
//import com.abs.wfs.workman.dao.query.tool.service.ToolQueryServiceImpl;
//import com.abs.wfs.workman.dao.query.tool.vo.QueryEqpVo;
//import com.abs.wfs.workman.util.code.UseStatCd;
//import com.abs.wfs.workman.util.code.WorkStatCd;
//import com.abs.wfs.workman.util.service.StateRuleManager;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class WfsRecipeValidateRepImpl implements WfsRecipeValidateRep {
//
//    @Autowired
//    ToolQueryServiceImpl toolQueryService;
//
//    @Autowired
//    LotQueryServiceImpl lotQueryService;
//
//    @Autowired
//    StateRuleManager stateRuleManager;
//
//    private String cid;
//
//    private WfsRecipeValidateRepVo.WfsDspWorkRepBody wfsDspWorkRepBody;
//
//    private long executeStartTime;
//
//    private QueryEqpVo queryEqpVo;
//
//    private QueryLotVo queryLotVo;
//
//
//
//    @Override
//    public void init(String cid, Object messageObj) {
//
//        this.cid = cid;
//        this.wfsDspWorkRepBody = ((WfsRecipeValidateRepVo) messageObj ).getBody();
//        this.executeStartTime = System.currentTimeMillis();
//
//    }
//
//    @Override
//    public ApMessageResultVo execute(String messageId) throws Exception {
//
//        // query
//        this.queryEqpVo = this.toolQueryService.queryEqpCondition(
//                QueryEqpVo.builder()
//                        .siteId(wfsDspWorkRepBody.getSiteId())
//                        .useStatCd(UseStatCd.Usable.name())
//                        .eqpId(wfsDspWorkRepBody.getEqpId())
//                        .build()
//        );
//
//        this.queryLotVo = this.lotQueryService.queryLotCondition(
//                                QueryLotVo.builder()
//                                        .siteId(wfsDspWorkRepBody.getSiteId())
//                                        .useStatCd(UseStatCd.Usable.name())
//                                        .lotId(wfsDspWorkRepBody.getLotId())
//                                        .build()
//        );
//
//        // result code validate
//        if(!(this.wfsDspWorkRepBody.getResultCode().trim()).equals("0")){
//            throw new Exception("Result Code is not success");
//        }
//
//        // TODO update recipe check flag.
//        // query siteId/use stat/lot id and update mdfyUserId / eventNm/tid/rcpChkYn
//
//        // TODO Condition Query
//        /**
//         * SELECT WN_WIP_STAT.RCP_CHK_YN, WN_WIP_STAT.TRACK_IN_CNFM_YN, WN_WIP_STAT.EQP_CHK_YN
//         *   FROM WN_WIP_STAT ,TN_POS_LOT
//         *  WHERE WN_WIP_STAT.SITE_ID = TN_POS_LOT.SITE_ID
//         *    AND WN_WIP_STAT.LOT_ID = TN_POS_LOT.LOT_ID
//         *    AND TN_POS_LOT.USE_STAT_CD = ?
//         *    AND WN_WIP_STAT.LOT_ID = ?
//         */
//
//        // TODO Work Status Query
//        /**
//         * SELECT WN_WIP_STAT.WORK_STAT_CD , WN_WIP_STAT.DTL_WORK_STAT_CD,WN_WIP_STAT.CRNT_EQP_ID,  WN_WIP_STAT.CRNT_PORT_ID,  WN_WIP_STAT.RESV_EQP_ID, WN_WIP_STAT.RESV_GRP_ID,   WN_WIP_STAT.RESV_PORT_ID, WN_WIP_STAT.CARR_ID, WN_WIP_STAT.SELF_INSP_YN, WN_WIP_STAT.SELF_INSP_INFO_OBJ_ID, WN_WIP_STAT.SELF_INSP_PANEL_CNT
//         *    FROM WN_WIP_STAT ,TN_POS_LOT
//         *   WHERE WN_WIP_STAT.SITE_ID = TN_POS_LOT.SITE_ID
//         *     AND WN_WIP_STAT.LOT_ID = TN_POS_LOT.LOT_ID
//         *     AND TN_POS_LOT.USE_STAT_CD = ?
//         *     AND WN_WIP_STAT.LOT_ID = ?
//         */
//
//        String workStatCd = "Get WorkStat cd from above query. or Top Layer";
//
//        switch (WorkStatCd.valueOf(workStatCd)){
//            case Ready:
//
//                // TODO WorkStatQuery
//                /**
//                 * SELECT WN_WORK_STAT.EQP_ID
//                 *    ,WN_WORK_JOB.IN_PORT_ID
//                 *    ,WN_WORK_JOB.IN_CARR_ID
//                 *    ,WN_WORK_STAT.WORK_ID
//                 *    ,WN_WORK_JOB.LOT_ID
//                 *    ,WN_WORK_JOB.CRNT_CARR_ID
//                 * FROM WN_WORK_STAT , WN_WORK_JOB
//                 * WHERE 1=1
//                 *   AND WN_WORK_STAT.SITE_ID = WN_WORK_JOB.SITE_ID
//                 *   AND WN_WORK_STAT.WORK_ID = WN_WORK_JOB.WORK_ID
//                 *   AND WN_WORK_STAT.WORK_STAT_CD = ?
//                 *   AND WN_WORK_JOB.JOB_STAT_CD = ?
//                 *   AND WN_WORK_STAT.USE_STAT_CD = ?
//                 *   AND WN_WORK_JOB.USE_STAT_CD = ?
//                 *   AND WN_WORK_STAT.SITE_ID = ?
//                 *   AND WN_WORK_JOB.LOT_ID = ?
//                 */
//
//                // TODO Work ID Gen Query
//                /**
//                 * SELECT 'WORK_' ||  to_char(SYSTIMESTAMP , 'YYYYMMDDHH24MISSFF3') AS WORK_ID FROM dual
//                 */
//
//
//                // TODO Get Resv Carr
//                /**
//                 * SELECT CARR_ID, CRNT_EQP_ID, CRNT_PORT_ID  FROM WN_WIP_STAT
//                 * WHERE SITE_ID = ?
//                 *  AND RESV_GRP_ID = ?
//                 *  AND LOT_ID = ?
//                 */
//
//                // if(count($getResvCarr/Record) = 2)
//                // TODO get Out Carr Ready
//                /**
//                 * SELECT RESV_EQP_ID, RESV_PORT_ID, CARR_ID, RESV_OUT_PORT_ID, RESV_OUT_CARR_ID
//                 *   FROM WN_WIP_STAT
//                 *  WHERE SITE_ID = ?
//                 *    AND RESV_GRP_ID = ?
//                 *    AND LOT_ID =?
//                 *    AND LENGTH(RESV_OUT_PORT_ID) >= 1
//                 */
//
//                // TODO create work in out
//
//                // TODO generate work order  payload
//
//
//                // else
//                // TODO create work & generate work order payload
//
//
//                break;
//            case Standby:
//
//                // TODO create job for thread-1
//                // TODO [thread-1] getTransferId
//                // TODO [thread-1] createTransfer
//                // TODO [thread-1] generate Carr move req payload
//
//                // TODO create job for thread-2
//                // TODO [thread-2] query getOutCarr
//                /**
//                 * SELECT CARR_ID, CRNT_EQP_ID, CRNT_PORT_ID, CRNT_EQP_ID || CRNT_PORT_ID AS CRNT,
//                 *   RESV_EQP_ID, RESV_PORT_ID, RESV_EQP_ID || RESV_PORT_ID AS RESV,
//                 *   RESV_GRP_ID
//                 *   FROM WN_WIP_STAT
//                 *   WHERE LOT_ID = '-' AND CARR_ID <> ? AND RESV_GRP_ID = ?
//                 */
//
//                // if(query get out carr) is exist
//                // TODO [thread-2] getTransferID_OUT
//                // TODO [thread-2] createTransferJob_OUT
//                // TODO [thread-2] generate Carr move req payload for out
//
//                break;
//            default:
//                break;
//
//
//        }
//
//
//        // TODO send cmr to MCS
//
//        return null;
//    }
//}
