//package com.abs.wfs.workman.service.flow.eap.impl;
//
//import com.abs.wfs.workman.service.flow.eap.WfsToolCondRep;
//import com.abs.wfs.workman.spec.common.ApMessageResultVo;
//import com.abs.wfs.workman.spec.in.eap.WfsToolCondRepVo;
//import com.abs.wfs.workman.dao.query.tool.service.ToolQueryServiceImpl;
//import com.abs.wfs.workman.dao.query.tool.vo.QueryEqpVo;
//import com.abs.wfs.workman.dao.query.tool.vo.QueryPortVo;
//import com.abs.wfs.workman.util.code.UseStatCd;
//import com.abs.wfs.workman.util.code.UseYn;
//import com.abs.wfs.workman.util.service.StateRuleManager;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//public class WfsToolCondRepImpl implements WfsToolCondRep {
//
//    @Autowired
//    ToolQueryServiceImpl toolQueryService;
//
//    @Autowired
//    StateRuleManager stateRuleManager;
//
//    private String cid;
//
//    private WfsToolCondRepVo.WfsToolCondRepBody wfsToolCondRepBody;
//    private long executeStartTime;
//
//    private QueryPortVo queryPortVo;
//    private QueryEqpVo queryEqpVo;
//
//    @Override
//    public void init(String cid, Object messageObj) {
//        this.cid = cid;
//        this.wfsToolCondRepBody = ((WfsToolCondRepVo) messageObj ).getBody();
//        this.executeStartTime = System.currentTimeMillis();
//
//    }
//
//    @Override
//    public ApMessageResultVo execute(String messageId) throws Exception {
//
//        // query
//        this.queryEqpVo = this.toolQueryService.queryEqpCondition(
//                            QueryEqpVo.builder()
//                                    .siteId(wfsToolCondRepBody.getSiteId())
//                                    .useStatCd(UseStatCd.Usable.name())
//                                    .eqpId(wfsToolCondRepBody.getEqpId())
//                                    .build()
//        );
//
//        this.queryPortVo = toolQueryService.queryPortCondition(
//                            QueryPortVo.builder()
//                                    .siteId(wfsToolCondRepBody.getSiteId())
//                                    .useStatCd(UseStatCd.Usable.name())
//                                    .eqpId(wfsToolCondRepBody.getEqpId())
//                                    .portId(wfsToolCondRepBody.getStatus().getInPortId())
//                                    .build()
//        );
//
//        this.stateRuleManager.validEqp(wfsToolCondRepBody.getSiteId(), this.wfsToolCondRepBody.getEqpId(), queryEqpVo);
//        this.stateRuleManager.autoPort(wfsToolCondRepBody.getSiteId(), this.wfsToolCondRepBody.getEqpId(),
//                                        wfsToolCondRepBody.getStatus().getInPortId(), queryPortVo);
//
//
//        // 1. reserve lot query
//        /**
//         * SELECT OBJ_ID, LOT_ID, CARR_ID, WORK_STAT_CD, DTL_WORK_STAT_CD
//         *   FROM WN_WIP_STAT
//         *   WHERE LOT_ID <> '-' AND USE_STAT_CD = 'Usable' AND RESV_EQP_ID = ? AND RESV_PORT_ID = ?
//         */
//
//        // if("Result of lot query" in work stat cd is equlas Ready)
//        this.stateRuleManager.IsLoadedPort(wfsToolCondRepBody.getSiteId(), wfsToolCondRepBody.getEqpId(), wfsToolCondRepBody.getStatus().getInPortId(), queryPortVo);
//
//        // TODO Generate Track In Confirm payload
//
//        // TODO Send Track In Confirm payload to BRS
//
//
//        ApMessageResultVo apMessageResultVo = ApMessageResultVo.builder()
//                .cid(cid)
//                .messageKey(messageId)
//                .elapsedMilliSecond(System.currentTimeMillis() - executeStartTime)
//                .executeSuccessYn(UseYn.Y)
//                .build();
//        return apMessageResultVo;
//    }
//
//    @Override
//    public void scenarioDispatch(String messageId) throws Exception {
//
//    }
//}
