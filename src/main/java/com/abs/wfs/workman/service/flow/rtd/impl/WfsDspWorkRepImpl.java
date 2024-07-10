package com.abs.wfs.workman.service.flow.rtd.impl;

import com.abs.wfs.workman.config.ApPropertyObject;
import com.abs.wfs.workman.service.flow.rtd.WfsDspWorkRep;
import com.abs.wfs.workman.spec.common.ApMessageResultVo;
import com.abs.wfs.workman.spec.in.rtd.WfsDspWorkRepVo;
import com.abs.wfs.workman.dao.query.lot.service.LotQueryServiceImpl;
import com.abs.wfs.workman.dao.query.lot.vo.QueryLotVo;
import com.abs.wfs.workman.dao.query.tool.service.ToolQueryServiceImpl;
import com.abs.wfs.workman.dao.query.tool.vo.QueryEqpVo;
import com.abs.wfs.workman.dao.query.tool.vo.QueryPortVo;
import com.abs.wfs.workman.util.code.*;
import com.abs.wfs.workman.util.service.StateRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class WfsDspWorkRepImpl implements WfsDspWorkRep {

    @Autowired
    ToolQueryServiceImpl toolQueryService;

    @Autowired
    LotQueryServiceImpl lotQueryService;

    @Autowired
    StateRuleManager stateRuleManager;

    private static final String siteId = ApPropertyObject.getInstance().getSiteName();
    private String cid;

    private long executeStartTime;

    private WfsDspWorkRepVo.WfsDspWorkRepBody wfsDspWorkRepBody;

    private QueryPortVo queryPortVo;
    private QueryEqpVo queryEqpVo;
    private QueryLotVo queryLotVo;

    private ExecutorService executorService;

    @Override
    public void init(String cid, Object messageObj) {
        this.cid = cid;
        this.wfsDspWorkRepBody = ((WfsDspWorkRepVo) messageObj ).getBody();
        this.executeStartTime = System.currentTimeMillis();

    }

    @Override
    public ApMessageResultVo execute(String messageId) throws Exception {

        // Query
        this.executeQueryTasks();

        // validate
        if(!(this.wfsDspWorkRepBody.getDspType().trim()).equals(DspTypeList.EQP)){
            this.stateRuleManager.fullAutoEqp(siteId, wfsDspWorkRepBody.getEqpId(), queryEqpVo);
        }
        this.scenarioDispatch(messageId);


        // generated tool cond req payload via scenario dispatch
        // send payload.




        ApMessageResultVo apMessageResultVo = ApMessageResultVo.builder()
                .cid(cid)
                .messageKey(messageId)
                .elapsedMilliSecond(System.currentTimeMillis() - executeStartTime)
                .executeSuccessYn(UseYn.Y)
                .build();
        return apMessageResultVo;
    }

    public void scenarioDispatch(String messageId) throws Exception {

        // DSP 타입에 따른 분기
        switch (this.wfsDspWorkRepBody.getDspType()){
            case DspTypeList.EQP:

                // TODO STK 임시 반송
                // 1. TRANSIDGENQuery (SELECT 'TRANS_' ||  to_char(SYSTIMESTAMP , 'YYYYMMDDHH24MISSFF3') AS TRANS_ID FROM dual)
                // 2. OBJIDQuery (SELECT to_char(SYSTIMESTAMP , 'YYYYMMDDHH24MISSFF3') || '-' || SYS_GUID() AS OBJ_ID FROM dual)
                // 3. getCrntQuery
                /**
                 * SELECT CRNT_EQP_ID, CRNT_PORT_ID, CARR_ID  FROM WN_WIP_STAT
                 * WHERE SITE_ID = ?
                 *  AND USE_STAT_CD = ?
                 *  AND (LOT_ID  = ? OR (LOT_ID = '-' AND CARR_ID = ?))
                 */
                // 4. create transfer job
                // 5. generate CMR payload
                // 6. send CMR

                break;

            case DspTypeList.SORTER:

                this.doSorterScenario(messageId);

                break;


            case DspTypeList.LOT:

                if(this.queryEqpVo.getBatchYn().equals(UseYn.Y.name())){
                    log.info("Batch Eqp");
                    // TODO Define DSP Lot for Batch
                }

                switch (this.queryEqpVo.getModelTyp()){
                    case WorkManScenarioList.BP_SINGLE:
                        this.doBpSingleScenario(messageId);
                        break;

                    case WorkManScenarioList.INOUT_SINGLE:
                        this.doInoutSingleScenario(messageId);
                        break;
                }


                break;

            case DspTypeList.CARR:
                break;

            default:
                break;
        }

    }

    private void doBpSingleScenario(String messageId) throws Exception {

        // validate - port
        if(!(this.queryPortVo.getAcesModeCd().trim()).equals(ApStringConstant.AUTO)){
            throw new Exception("LOT HOLD");

        }

        if(!(this.queryPortVo.getStatCd().trim()).equals(ApStringConstant.EMPTY)){
            throw new Exception("LOT HOLD");
        }

        if(!(this.queryPortVo.getTrsfStatCd().trim()).equals(ApStringConstant.READYTOLOAD)){

            throw new Exception("LOT HOLD");
        }

        if(!(this.queryPortVo.getCarrTyp().trim()).equals(ApStringConstant.CST)){
            throw new Exception("LOT HOLD");

        }

        // validate - lot
        if((this.queryLotVo.getHoldYn().trim()).equals(UseYn.Y.name())){
            throw new Exception("LOT HOLD");
        }


        // 1. WorkState Query
        /**
         * SELECT WN_WORK_STAT.EQP_ID
         *    ,WN_WORK_JOB.IN_PORT_ID
         *    ,WN_WORK_JOB.IN_CARR_ID
         *    ,WN_WORK_STAT.WORK_ID
         *    ,WN_WORK_JOB.LOT_ID
         *    ,WN_WORK_JOB.CRNT_CARR_ID
         * FROM WN_WORK_STAT , WN_WORK_JOB
         * WHERE 1=1
         *   AND WN_WORK_STAT.SITE_ID = WN_WORK_JOB.SITE_ID
         *   AND WN_WORK_STAT.WORK_ID = WN_WORK_JOB.WORK_ID
         *   AND WN_WORK_STAT.WORK_STAT_CD = ?
         *   AND WN_WORK_JOB.JOB_STAT_CD = ?
         *   AND WN_WORK_STAT.USE_STAT_CD = ?
         *   AND WN_WORK_JOB.USE_STAT_CD = ?
         *   AND WN_WORK_STAT.SITE_ID = ?
         *   AND WN_WORK_JOB.LOT_ID = ?
         */

        // 2. DSP ID Generate
        /**
         * SELECT 'DSP_' || TO_CHAR(SYSTIMESTAMP, 'YYYYMMDDHH24MISSFF3') AS DSP_ID
         *   FROM DUAL
         */

        // 3. Select Wip Stat
        /**
         * SELECT OBJ_ID
         *   FROM WN_WIP_STAT
         *   WHERE LOT_ID = ?
         */

        // 4. update Wip Stat
        /**
         * UPDATE WN_WIP_STAT
         *   SET RESV_EQP_ID = ?, RESV_PORT_ID = ?, RESV_GRP_ID = ?,MDFY_USER_ID = ?, MDFY_DT = SYSDATE,
         *       EVNT_NM = ?, TID = ?
         *   WHERE SITE_ID = ? AND LOT_ID = ?
         */

        // 5. create wip stat hist
        /**
         * INSERT INTO WH_WIP_STAT (OBJ_ID, REF_OBJ_ID, SITE_ID, LOT_ID,
         *   CARR_ID, CARR_LCTN_NM, WORK_STAT_CD, DTL_WORK_STAT_CD, MDFY_USER_ID,
         *   MDFY_DT, CRT_USER_ID, CRT_DT, CSTM_EVNT_NM, EVNT_NM, FNL_EVNT_DT,
         *   PREV_CSTM_EVNT_NM, PREV_EVNT_NM, RSN_CD, TID, TRNS_CM, USE_STAT_CD,
         *   CRNT_EQP_ID, CRNT_PORT_ID, BATCH_ID, RESV_EQP_ID, RESV_PORT_ID,
         *   EQP_CHK_YN, RCP_CHK_YN, TRACK_IN_CNFM_YN)
         *   SELECT (SELECT TO_CHAR(SYSTIMESTAMP, 'YYYYMMDDHH24MISSFF3') || '-' || SYS_GUID()
         *           FROM DUAL) AS OBJ_ID, OBJ_ID AS REF_OBJ_ID, SITE_ID,
         *   LOT_ID, CARR_ID, CARR_LCTN_NM, WORK_STAT_CD, DTL_WORK_STAT_CD,
         *   MDFY_USER_ID, MDFY_DT, CRT_USER_ID, CRT_DT, CSTM_EVNT_NM, EVNT_NM,
         *   FNL_EVNT_DT, PREV_CSTM_EVNT_NM, PREV_EVNT_NM, RSN_CD, TID, TRNS_CM,
         *   USE_STAT_CD, CRNT_EQP_ID, CRNT_PORT_ID, BATCH_ID, RESV_EQP_ID,
         *   RESV_PORT_ID, EQP_CHK_YN, RCP_CHK_YN, TRACK_IN_CNFM_YN
         *     FROM WN_WIP_STAT
         *     WHERE OBJ_ID = ?
         */

        // 6. select wip stat carr
        /**
         * SELECT OBJ_ID
         *   FROM WN_WIP_STAT
         *   WHERE LOT_ID = ? AND CARR_ID = ?
         */

        // 7. update wip stat carr
        /**
         * UPDATE WN_WIP_STAT
         *   SET RESV_EQP_ID = ?, RESV_PORT_ID = ?, RESV_GRP_ID = ?, MDFY_USER_ID = ?, MDFY_DT = SYSDATE,
         *       EVNT_NM = ?, TID = ?
         *   WHERE SITE_ID = ? AND CARR_ID = ? AND LOT_ID = ?
         */

        // 8. create wip stat his carr
        /**
         * INSERT INTO WH_WIP_STAT (OBJ_ID, REF_OBJ_ID, SITE_ID, LOT_ID,
         *   CARR_ID, CARR_LCTN_NM, WORK_STAT_CD, DTL_WORK_STAT_CD, MDFY_USER_ID,
         *   MDFY_DT, CRT_USER_ID, CRT_DT, CSTM_EVNT_NM, EVNT_NM, FNL_EVNT_DT,
         *   PREV_CSTM_EVNT_NM, PREV_EVNT_NM, RSN_CD, TID, TRNS_CM, USE_STAT_CD,
         *   CRNT_EQP_ID, CRNT_PORT_ID, BATCH_ID, RESV_EQP_ID, RESV_PORT_ID,
         *   EQP_CHK_YN, RCP_CHK_YN, TRACK_IN_CNFM_YN)
         *   SELECT (SELECT TO_CHAR(SYSTIMESTAMP, 'YYYYMMDDHH24MISSFF3') || '-' || SYS_GUID()
         *           FROM DUAL) AS OBJ_ID, OBJ_ID AS REF_OBJ_ID, SITE_ID,
         *   LOT_ID, CARR_ID, CARR_LCTN_NM, WORK_STAT_CD, DTL_WORK_STAT_CD,
         *   MDFY_USER_ID, MDFY_DT, CRT_USER_ID, CRT_DT, CSTM_EVNT_NM, EVNT_NM,
         *   FNL_EVNT_DT, PREV_CSTM_EVNT_NM, PREV_EVNT_NM, RSN_CD, TID, TRNS_CM,
         *   USE_STAT_CD, CRNT_EQP_ID, CRNT_PORT_ID, BATCH_ID, RESV_EQP_ID,
         *   RESV_PORT_ID, EQP_CHK_YN, RCP_CHK_YN, TRACK_IN_CNFM_YN
         *     FROM WN_WIP_STAT
         *     WHERE OBJ_ID = ?
         */

        // 9. generate payload TOOL COND REQ

    }
    private void doInoutSingleScenario(String messageId){

        String[] portList = this.wfsDspWorkRepBody.getPortId().split(",");
        String[] carrList = this.wfsDspWorkRepBody.getCarrId().split(",");

        // 1. port query in port
        /**
         * SELECT TN_RDS_PORT.PORT_ID
         * 	  ,TN_RDS_PORT.CARR_TYP
         *   	  ,TN_RDS_PORT.PORT_TYP
         *   	  ,TN_POS_PORT.ACES_MODE_CD
         *   	  ,TN_POS_PORT.CTRL_MODE_CD
         *   	  ,TN_POS_PORT.STAT_CD
         *   	  ,TN_POS_PORT.TRSF_STAT_CD
         *   	  ,TN_POS_PORT.CARR_ID
         *   FROM TN_RDS_PORT
         *   	  ,TN_POS_PORT
         *  WHERE TN_RDS_PORT.SITE_ID = TN_POS_PORT.SITE_ID
         *    AND TN_RDS_PORT.EQP_ID = TN_POS_PORT.EQP_ID
         *    AND TN_RDS_PORT.PORT_ID = TN_POS_PORT.PORT_ID
         *    AND TN_RDS_PORT.USE_STAT_CD = 'Usable'
         *    AND TN_RDS_PORT.PORT_ID = ?
         */

        // 2. port query out
        /**
         * SELECT TN_RDS_PORT.PORT_ID
         * 	  ,TN_RDS_PORT.CARR_TYP
         *   	  ,TN_RDS_PORT.PORT_TYP
         *   	  ,TN_POS_PORT.ACES_MODE_CD
         *   	  ,TN_POS_PORT.CTRL_MODE_CD
         *   	  ,TN_POS_PORT.STAT_CD
         *   	  ,TN_POS_PORT.TRSF_STAT_CD
         *   	  ,TN_POS_PORT.CARR_ID
         *   FROM TN_RDS_PORT
         *   	  ,TN_POS_PORT
         *  WHERE TN_RDS_PORT.SITE_ID = TN_POS_PORT.SITE_ID
         *    AND TN_RDS_PORT.EQP_ID = TN_POS_PORT.EQP_ID
         *    AND TN_RDS_PORT.PORT_ID = TN_POS_PORT.PORT_ID
         *    AND TN_RDS_PORT.USE_STAT_CD = 'Usable'
         *    AND TN_RDS_PORT.PORT_ID = ?
         */

        // similar with bp....

    }

    private void doSorterScenario(String messageId){

        // TODO Sorter 시나리오
        // 1. FullAutoEqp

        // 2. SelectSorterJobResv
        /**
         * SELECT OBJ_ID, JOB_ID, SORTER_JOB_TYP, DTL_SORTER_JOB_TYP, SRC_LOT_ID,
         *   SRC_CARR_ID, SRC_SLOT_NO, SRC_PROD_MTRL_ID, TGT_LOT_ID, TGT_CARR_ID,
         *   TGT_SLOT_NO, TGT_PROD_MTRL_ID, PRIRT_NO
         *   FROM WN_SORTER_JOB_RESV
         *   WHERE SITE_ID = ? AND JOB_STAT_CD = ? AND JOB_ID = ?
         */

        // 3. update sorter job reserve
        /**
         * UPDATE WN_SORTER_JOB_RESV
         *   SET JOB_STAT_CD = ?, MDFY_USER_ID = ?,
         *       MDFY_DT = SYSDATE, EVNT_NM = ?, FNL_EVNT_DT = SYSDATE, TID = ?
         *   WHERE JOB_ID = ?
         */

        // 4. update sorter job reserve history
        /**
         * INSERT INTO WH_SORTER_JOB_RESV (OBJ_ID, REF_OBJ_ID, SITE_ID, JOB_ID,
         *   EQP_ID, SRC_LOT_ID, TGT_LOT_ID, SRC_CARR_ID, SRC_SLOT_NO, SRC_PROD_MTRL_ID,
         *   TGT_CARR_ID, TGT_SLOT_NO, TGT_PROD_MTRL_ID, PRIRT_NO, SORTER_JOB_TYP,
         *   DTL_SORTER_JOB_TYP, JOB_STAT_CD, PROC_SGMT_ID, PROC_SGMT_SEQ,
         *   MDFY_USER_ID, MDFY_DT, CRT_USER_ID, CRT_DT, CSTM_EVNT_NM, EVNT_NM,
         *   FNL_EVNT_DT, PREV_CSTM_EVNT_NM, PREV_EVNT_NM, RSN_CD, TID, TRNS_CM,
         *   USE_STAT_CD)
         *   SELECT (SELECT TO_CHAR(SYSTIMESTAMP, 'YYYYMMDDHH24MISSFF3') || '-' || SYS_GUID()
         *           FROM DUAL) AS OBJ_ID, OBJ_ID AS REF_OBJ_ID, SITE_ID,
         *   JOB_ID, EQP_ID, SRC_LOT_ID, TGT_LOT_ID, SRC_CARR_ID, SRC_SLOT_NO,
         *   SRC_PROD_MTRL_ID, TGT_CARR_ID, TGT_SLOT_NO, TGT_PROD_MTRL_ID,
         *   PRIRT_NO, SORTER_JOB_TYP, DTL_SORTER_JOB_TYP, JOB_STAT_CD, PROC_SGMT_ID,
         *   PROC_SGMT_SEQ, MDFY_USER_ID, MDFY_DT, CRT_USER_ID, CRT_DT, CSTM_EVNT_NM,
         *   EVNT_NM, FNL_EVNT_DT, PREV_CSTM_EVNT_NM, PREV_EVNT_NM, RSN_CD,
         *   TID, TRNS_CM, USE_STAT_CD
         *     FROM WN_SORTER_JOB_RESV
         *     WHERE OBJ_ID = ?
         */

        // 5. get sorter port list "BP01,BP02"
        String[] portList = this.wfsDspWorkRepBody.getPortId().split(",");

        // 6. update wip stat for source carr
        // 6-1. generate Tool cond req for in port only

        // 7. update wip stat for dest carr
        // 7-1. generate Tool cond req for inout port

    }

    private void executeQueryTasks() {
        List<Runnable> tasks = Arrays.asList(
                this::queryEqp,
                this::queryPort,
                this::queryLot
        );
        executeTasks(tasks);
    }

    private void executeTasks(List<Runnable> tasks) {
        executorService = Executors.newFixedThreadPool(tasks.size());
        CompletableFuture.allOf(tasks.stream()
                .map(task -> CompletableFuture.runAsync(task, executorService))
                .toArray(CompletableFuture[]::new)).join();
        executorService.shutdown();
    }

    private void queryEqp() {
        queryEqpVo = toolQueryService.queryEqpCondition(
                QueryEqpVo.builder()
                        .siteId(wfsDspWorkRepBody.getSiteId())
                        .useStatCd(UseStatCd.Usable.name())
                        .eqpId(wfsDspWorkRepBody.getEqpId())
                        .build()
        );
    }

    private void queryPort() {
        queryPortVo = toolQueryService.queryPortCondition(
                QueryPortVo.builder()
                        .siteId(wfsDspWorkRepBody.getSiteId())
                        .useStatCd(UseStatCd.Usable.name())
                        .eqpId(wfsDspWorkRepBody.getEqpId())
                        .portId(wfsDspWorkRepBody.getPortId())
                        .build()
        );
    }

    public void queryLot(){
        queryLotVo = lotQueryService.queryLotCondition(
                QueryLotVo.builder()
                        .siteId(wfsDspWorkRepBody.getSiteId())
                        .useStatCd(UseStatCd.Usable.name())
                        .lotId(wfsDspWorkRepBody.getLotId())
                        .build()
        );

    }
}
