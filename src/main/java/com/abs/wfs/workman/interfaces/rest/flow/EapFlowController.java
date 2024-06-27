package com.abs.wfs.workman.interfaces.rest.flow;


import com.abs.wfs.workman.service.flow.eap.impl.*;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eap.*;
import com.abs.wfs.workman.util.WorkManMessageList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/flow/eap/")
@RequiredArgsConstructor
public class EapFlowController {

    @Autowired
    WfsInspDataReportImpl wfsInspDataReportImpl;

    @Autowired
    WfsInspReportImpl wfsInspReportImpl;

    @Autowired
    WfsLotInfoReqImpl wfsLotInfoReqImpl;


    @Autowired
    WfsMaterialChangeReqImpl wfsMaterialChangeReq;


    @Autowired
    WfsAlarmReportImpl wfsAlarmReport;

    @PostMapping(WorkManMessageList.WFS_ALARM_REPORT)
    public ApFlowProcessVo executeWfsAlarmReport(@RequestBody WfsAlarmReportIvo wfsAlarmReportIvo,
                                                 @RequestParam(value = "key") String trackingKey,
                                                 @RequestParam(value = "scenario") String scenarioType) throws Exception {


        String cid = WorkManMessageList.WFS_ALARM_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsAlarmReport.initialize(cid, trackingKey, scenarioType);

        return this.wfsAlarmReport.execute(apFlowProcessVo, wfsAlarmReportIvo);

    }

    @Autowired
    WfsEqpStateReportImpl wfsEqpStateReportImpl;

    @PostMapping(WorkManMessageList.WFS_EQP_CONTROL_STATE_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsEqpStateReportIvo wfsEqpStateReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {


        String cid = WorkManMessageList.WFS_EQP_CONTROL_STATE_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsEqpStateReportImpl.initialize(cid, trackingKey, scenarioType);

        return this.wfsEqpStateReportImpl.execute(apFlowProcessVo, wfsEqpStateReportIvo);

    }


    @Autowired
    WfsEqpControlStateReportImpl wfsEqpControlStateReportImpl;

    @PostMapping(WorkManMessageList.WFS_EQP_STATE_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsEqpControlStateReportIvo wfsEqpControlStateReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {


        String cid = WorkManMessageList.WFS_EQP_STATE_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsEqpControlStateReportImpl.initialize(cid, trackingKey, scenarioType);

        return this.wfsEqpControlStateReportImpl.execute(apFlowProcessVo, wfsEqpControlStateReportIvo);

    }

    @Autowired
    WfsInitPortStateReportImpl wfsInitPortStateReportImpl;

    @PostMapping(WorkManMessageList.WFS_INIT_PORT_STATE_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsInitPortStateReportIvo wfsEqpControlStateReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_INIT_PORT_STATE_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsInitPortStateReportImpl.initialize(cid, trackingKey, scenarioType);

        return this.wfsInitPortStateReportImpl.execute(apFlowProcessVo, wfsEqpControlStateReportIvo);

    }


    @PostMapping(WorkManMessageList.WFS_INSP_DATA_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsInspDataReportIvo wfsInspDataReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_INSP_DATA_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsInspDataReportImpl.initialize(cid, trackingKey, scenarioType);

        return this.wfsInspDataReportImpl.execute(apFlowProcessVo, wfsInspDataReportIvo);

    }


    @PostMapping(WorkManMessageList.WFS_INSP_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsInspReportIvo wfsInspReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_INSP_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsInspReportImpl.initialize(cid, trackingKey, scenarioType);

        return this.wfsInspReportImpl.execute(apFlowProcessVo, wfsInspReportIvo);

    }


    @PostMapping(WorkManMessageList.WFS_LOT_INFO_REQ)
    public ApFlowProcessVo executeEvent(@RequestBody WfsLotInfoReqIvo wfsLotInfoReqIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_LOT_INFO_REQ;
        ApFlowProcessVo apFlowProcessVo = this.wfsLotInfoReqImpl.initialize(cid, trackingKey, scenarioType);

        return this.wfsLotInfoReqImpl.execute(apFlowProcessVo, wfsLotInfoReqIvo);

    }


    @PostMapping(WorkManMessageList.WFS_MATERIAL_CHANGE_REQ)
    public ApFlowProcessVo executeEvent(@RequestBody WfsMaterialChangeReqIvo wfsMaterialChangeReqIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_MATERIAL_CHANGE_REQ;
        ApFlowProcessVo apFlowProcessVo = this.wfsMaterialChangeReq.initialize(cid, trackingKey, scenarioType);

        return this.wfsMaterialChangeReq.execute(apFlowProcessVo, wfsMaterialChangeReqIvo);

    }


    @Autowired
    WfsMaterialKitdekitReportImpl wfsMaterialKitdekitReport;

    @PostMapping(WorkManMessageList.WFS_MATERIAL_KITDEKIT_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsMaterialKitdekitReportIvo wfsMaterialKitdekitReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        log.info(wfsMaterialKitdekitReportIvo.toString());
        String cid = WorkManMessageList.WFS_MATERIAL_KITDEKIT_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsMaterialKitdekitReport.initialize(cid, trackingKey, scenarioType);

        return this.wfsMaterialKitdekitReport.execute(apFlowProcessVo, wfsMaterialKitdekitReportIvo);

    }


    @Autowired
    WfsMaterialUsageReportImpl wfsMaterialUsageReport;

    @PostMapping(WorkManMessageList.WFS_MATERIAL_USAGE_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsMaterialUsageReportIvo wfsMaterialUsageReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_MATERIAL_USAGE_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsMaterialUsageReport.initialize(cid, trackingKey, scenarioType);

        return this.wfsMaterialUsageReport.execute(apFlowProcessVo, wfsMaterialUsageReportIvo);

    }


    @Autowired
    WfsProdDcollReportImpl wfsProdDcollReport;

    @PostMapping(WorkManMessageList.WFS_PROD_DCOLL_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsProdDcollReportIvo wfsProdDcollReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_PROD_DCOLL_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsProdDcollReport.initialize(cid, trackingKey, scenarioType);

        return this.wfsProdDcollReport.execute(apFlowProcessVo, wfsProdDcollReportIvo);

    }


    @Autowired
    WfsScrapReportImpl wfsScrapReport;

    @PostMapping(WorkManMessageList.WFS_SCRAP_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsScrapReportIvo wfsScrapReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_SCRAP_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsScrapReport.initialize(cid, trackingKey, scenarioType);

        return this.wfsScrapReport.execute(apFlowProcessVo, wfsScrapReportIvo);

    }


    @Autowired
    WfsProdInspJdgmImpl wfsProdInspJdgm;

    @PostMapping(WorkManMessageList.WFS_PROD_INSP_JDGM)
    public ApFlowProcessVo executeEvent(@RequestBody WfsProdInspJdgmIvo wfsProdInspJdgmIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_PROD_INSP_JDGM;
        ApFlowProcessVo apFlowProcessVo = this.wfsProdInspJdgm.initialize(cid, trackingKey, scenarioType);

        return this.wfsProdInspJdgm.execute(apFlowProcessVo, wfsProdInspJdgmIvo);

    }

    @Autowired
    WfsPortStateReportImpl wfsPortStateReport;

    @PostMapping(WorkManMessageList.WFS_PORT_STATE_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsPortStateReportIvo wfsPortStateReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_PORT_STATE_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsPortStateReport.initialize(cid, trackingKey, scenarioType);

        return this.wfsPortStateReport.execute(apFlowProcessVo, wfsPortStateReportIvo);

    }

    @Autowired
    WfsSorterModeChangeRepImpl wfsSorterModeChangeRep;

    @PostMapping(WorkManMessageList.WFS_SORTER_MODE_CHANGE_REP)
    public ApFlowProcessVo executeEvent(@RequestBody WfsSorterModeChangeRepIvo wfsSorterModeChangeRepIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_SORTER_MODE_CHANGE_REP;
        ApFlowProcessVo apFlowProcessVo = this.wfsSorterModeChangeRep.initialize(cid, trackingKey, scenarioType);

        return this.wfsSorterModeChangeRep.execute(apFlowProcessVo, wfsSorterModeChangeRepIvo);

    }


    @Autowired
    WfsUnloadReqImpl wfsUnloadReq;

    @PostMapping(WorkManMessageList.WFS_UNLOAD_REQ)
    public ApFlowProcessVo executeEvent(@RequestBody WfsUnloadReqIvo wfsUnloadReqIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_UNLOAD_REQ;
        ApFlowProcessVo apFlowProcessVo = this.wfsUnloadReq.initialize(cid, trackingKey, scenarioType);

        return this.wfsUnloadReq.execute(apFlowProcessVo, wfsUnloadReqIvo);

    }


    @Autowired
    WfsUnloadCompImpl wfsUnloadComp;

    @PostMapping(WorkManMessageList.WFS_UNLOAD_COMP)
    public ApFlowProcessVo executeEvent(@RequestBody WfsUnloadCompIvo wfsUnloadCompIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_UNLOAD_COMP;
        ApFlowProcessVo apFlowProcessVo = this.wfsUnloadComp.initialize(cid, trackingKey, scenarioType);

        return this.wfsUnloadComp.execute(apFlowProcessVo, wfsUnloadCompIvo);

    }


    @Autowired
    WfsLoadCompImpl wfsLoadComp;

    @PostMapping(WorkManMessageList.WFS_LOAD_COMP)
    public ApFlowProcessVo executeEvent(@RequestBody WfsLoadCompIvo wfsLoadCompIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_LOAD_COMP;
        ApFlowProcessVo apFlowProcessVo = this.wfsLoadComp.initialize(cid, trackingKey, scenarioType);

        return this.wfsLoadComp.execute(apFlowProcessVo, wfsLoadCompIvo);

    }


    @Autowired
    WfsWorkOrderRepImpl wfsWorkOrderRep;

    @PostMapping(WorkManMessageList.WFS_WORK_ORDER_REP)
    public ApFlowProcessVo executeEvent(@RequestBody WfsWorkOrderRepIvo wfsWorkOrderRepIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_WORK_ORDER_REP;
        ApFlowProcessVo apFlowProcessVo = this.wfsWorkOrderRep.initialize(cid, trackingKey, scenarioType);

        return this.wfsWorkOrderRep.execute(apFlowProcessVo, wfsWorkOrderRepIvo);

    }


    @Autowired
    WfsWorkStartRepImpl wfsWorkStartRep;

    @PostMapping(WorkManMessageList.WFS_WORK_START_REP)
    public ApFlowProcessVo executeEvent(@RequestBody WfsWorkStartRepIvo wfsWorkStartRepIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_WORK_START_REP;
        ApFlowProcessVo apFlowProcessVo = this.wfsWorkStartRep.initialize(cid, trackingKey, scenarioType);

        return this.wfsWorkStartRep.execute(apFlowProcessVo, wfsWorkStartRepIvo);

    }


    @Autowired
    WfsDurableInfoRepImpl wfsDurableInfoRep;

    @PostMapping(WorkManMessageList.WFS_DURABLE_INFO_REP)
    public ApFlowProcessVo executeEvent(@RequestBody WfsDurableInfoRepIvo wfsDurableInfoRepIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_DURABLE_INFO_REP;
        ApFlowProcessVo apFlowProcessVo = this.wfsDurableInfoRep.initialize(cid, trackingKey, scenarioType);

        return this.wfsDurableInfoRep.execute(apFlowProcessVo, wfsDurableInfoRepIvo);

    }



    @Autowired
    WfsWorkAbortImpl wfsWorkAbort;

    @PostMapping(WorkManMessageList.WFS_WORK_ABORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsWorkAbortIvo wfsWorkAbortIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_WORK_ABORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsWorkAbort.initialize(cid, trackingKey, scenarioType);

        return this.wfsWorkAbort.execute(apFlowProcessVo, wfsWorkAbortIvo);

    }


    @Autowired
    WfsPrecedeLotResumeImpl wfsPrecedeLotResume;

    @PostMapping(WorkManMessageList.WFS_PRECEDE_LOT_RESUME)
    public ApFlowProcessVo executeEvent(@RequestBody WfsPrecedeLotResumeIvo wfsPrecedeLotResumeIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_PRECEDE_LOT_RESUME;
        ApFlowProcessVo apFlowProcessVo = this.wfsPrecedeLotResume.initialize(cid, trackingKey, scenarioType);

        return this.wfsPrecedeLotResume.execute(apFlowProcessVo, wfsPrecedeLotResumeIvo);

    }


    @Autowired
    WfsLotTrackInCancelRepImpl wfsLotTrackInCancelRep;

    @PostMapping(WorkManMessageList.WFS_LOT_TRACK_IN_CANCEL_REP)
    public ApFlowProcessVo executeEvent(@RequestBody WfsLotTrackInCancelRepIvo wfsLotTrackInCancelRepIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_LOT_TRACK_IN_CANCEL_REP;
        ApFlowProcessVo apFlowProcessVo = this.wfsLotTrackInCancelRep.initialize(cid, trackingKey, scenarioType);

        return this.wfsLotTrackInCancelRep.execute(apFlowProcessVo, wfsLotTrackInCancelRepIvo);

    }



    @Autowired
    WfsInitDurableInfoReportImpl wfsInitDurableInfoReport;

    @PostMapping(WorkManMessageList.WFS_INIT_DURABLE_INFO_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsInitDurableInfoReportIvo wfsInitDurableInfoReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_INIT_DURABLE_INFO_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsInitDurableInfoReport.initialize(cid, trackingKey, scenarioType);

        return this.wfsInitDurableInfoReport.execute(apFlowProcessVo, wfsInitDurableInfoReportIvo);

    }


    @Autowired
    WfsEfemStateReportImpl wfsEfemStateReport;

    @PostMapping(WorkManMessageList.WFS_EFEM_STATE_REPORT )
    public ApFlowProcessVo executeEvent(@RequestBody WfsEfemStateReportIvo wfsEfemStateReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_EFEM_STATE_REPORT ;
        ApFlowProcessVo apFlowProcessVo = this.wfsEfemStateReport.initialize(cid, trackingKey, scenarioType);

        return this.wfsEfemStateReport.execute(apFlowProcessVo, wfsEfemStateReportIvo);

    }


    @Autowired
    WfsEfemControlStateReportImpl wfsEfemControlStateReport;

    @PostMapping(WorkManMessageList.WFS_EFEM_CONTROL_STATE_REPORT)
    public ApFlowProcessVo executeEvent(@RequestBody WfsEfemControlStateReportIvo wfsEfemControlStateReportIvo,
                                        @RequestParam(value = "key") String trackingKey,
                                        @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_EFEM_CONTROL_STATE_REPORT;
        ApFlowProcessVo apFlowProcessVo = this.wfsEfemControlStateReport.initialize(cid, trackingKey, scenarioType);

        return this.wfsEfemControlStateReport.execute(apFlowProcessVo, wfsEfemControlStateReportIvo);

    }


}