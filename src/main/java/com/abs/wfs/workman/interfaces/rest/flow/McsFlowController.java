package com.abs.wfs.workman.interfaces.rest.flow;


import com.abs.wfs.workman.dao.domain.transferJob.service.WnTransferJobServiceImpl;
import com.abs.wfs.workman.dao.domain.transferJob.vo.CancelTransferJobResultVo;
import com.abs.wfs.workman.service.common.transferJob.TransferJobService;
import com.abs.wfs.workman.service.flow.brs.impl.WfsManualWorkStartImpl;
import com.abs.wfs.workman.service.flow.mcs.WfsCarrDataRep;
import com.abs.wfs.workman.service.flow.mcs.impl.*;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.brs.WfsManualWorkStartIvo;
import com.abs.wfs.workman.spec.in.mcs.*;
import com.abs.wfs.workman.util.WorkManMessageList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/flow/mcs/")
@RequiredArgsConstructor
public class McsFlowController {

    @Autowired
    WfsCarrMoveCompServiceImpl wfsCarrMoveCompService;

    @PostMapping(WorkManMessageList.WFS_CARR_MOVE_COMP)
    public ApFlowProcessVo execute(@RequestBody WfsCarrMoveCompIvo wfsCarrMoveCompIvo,
                                                 @RequestParam(value = "key") String trackingKey,
                                                 @RequestParam(value = "scenario") String scenarioType) throws Exception {


        String cid = WorkManMessageList.WFS_CARR_MOVE_COMP;
        ApFlowProcessVo apFlowProcessVo = this.wfsCarrMoveCompService.initialize(cid, trackingKey, scenarioType, wfsCarrMoveCompIvo.getHead());

        return this.wfsCarrMoveCompService.execute(apFlowProcessVo, wfsCarrMoveCompIvo);

    }

    @Autowired
    WfsCarrMoveCnclCompServiceImpl wfsCarrMoveCnclCompService;

    @PostMapping(WorkManMessageList.WFS_CARR_MOVE_CNCL_COMP)
    public ApFlowProcessVo execute(@RequestBody WfsCarrMoveCnclCompIvo wfsCarrMoveCnclCompIvo,
                                   @RequestParam(value = "key") String trackingKey,
                                   @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_CARR_MOVE_CNCL_COMP;
        ApFlowProcessVo apFlowProcessVo = this.wfsCarrMoveCnclCompService.initialize(cid, trackingKey, scenarioType, wfsCarrMoveCnclCompIvo.getHead());

        return this.wfsCarrMoveCnclCompService.execute(apFlowProcessVo, wfsCarrMoveCnclCompIvo);

    }

    @Autowired
    WfsCarrDataQryServiceImpl wfsCarrDataQryService;

    @PostMapping(WorkManMessageList.WFS_CARR_DATA_QRY)
    public ApFlowProcessVo execute(@RequestBody WfsCarrDataQryIvo wfsCarrDataQryIvo,
                                   @RequestParam(value = "key") String trackingKey,
                                   @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_CARR_DATA_QRY;
        ApFlowProcessVo apFlowProcessVo = this.wfsCarrDataQryService.initialize(cid, trackingKey, scenarioType, wfsCarrDataQryIvo.getHead());

        return this.wfsCarrDataQryService.execute(apFlowProcessVo, wfsCarrDataQryIvo);

    }
    @Autowired
    WfsCarrDataRepServiceImpl wfsCarrDataRepService;

    @PostMapping(WorkManMessageList.WFS_CARR_DATA_REP)
    public ApFlowProcessVo execute(@RequestBody WfsCarrDataRepIvo wfsCarrDataRepIvo,
                                   @RequestParam(value = "key") String trackingKey,
                                   @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_CARR_DATA_REP;
        ApFlowProcessVo apFlowProcessVo = this.wfsCarrDataRepService.initialize(cid, trackingKey, scenarioType, wfsCarrDataRepIvo.getHead());

        return this.wfsCarrDataRepService.execute(apFlowProcessVo, wfsCarrDataRepIvo);

    }
    @Autowired
    WfsCarrDestChgRepServiceImpl wfsCarrDestChgRepService;

    @PostMapping(WorkManMessageList.WFS_CARR_DEST_CHG_REP)
    public ApFlowProcessVo execute(@RequestBody WfsCarrDestChgRepIvo wfsCarrDestChgRepIvo,
                                   @RequestParam(value = "key") String trackingKey,
                                   @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_CARR_DEST_CHG_REP;
        ApFlowProcessVo apFlowProcessVo = this.wfsCarrDestChgRepService.initialize(cid, trackingKey, scenarioType, wfsCarrDestChgRepIvo.getHead());

        return this.wfsCarrDestChgRepService.execute(apFlowProcessVo, wfsCarrDestChgRepIvo);

    }
    @Autowired
    WfsCarrLocChgServiceImpl wfsCarrLocChgService;

    @PostMapping(WorkManMessageList.WFS_CARR_LOC_CHG)
    public ApFlowProcessVo execute(@RequestBody WfsCarrLocChgIvo wfsCarrLocChgIvo,
                                   @RequestParam(value = "key") String trackingKey,
                                   @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_CARR_LOC_CHG;
        ApFlowProcessVo apFlowProcessVo = this.wfsCarrLocChgService.initialize(cid, trackingKey, scenarioType, wfsCarrLocChgIvo.getHead());

        return this.wfsCarrLocChgService.execute(apFlowProcessVo, wfsCarrLocChgIvo);
    }
    @Autowired
    WfsCarrMoveCnclRepServiceImpl wfsCarrMoveCnclRepService;

    @PostMapping(WorkManMessageList.WFS_CARR_MOVE_CNCL_REP)
    public ApFlowProcessVo execute(@RequestBody WfsCarrMoveCnclRepIvo wfsCarrMoveCnclRepIvo,
                                   @RequestParam(value = "key") String trackingKey,
                                   @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_CARR_MOVE_CNCL_REP;
        ApFlowProcessVo apFlowProcessVo = this.wfsCarrMoveCnclRepService.initialize(cid, trackingKey, scenarioType, wfsCarrMoveCnclRepIvo.getHead());

        return this.wfsCarrMoveCnclRepService.execute(apFlowProcessVo, wfsCarrMoveCnclRepIvo);
    }
    @Autowired
    WfsCarrMoveCrtServiceImpl wfsCarrMoveCrtService;

    @PostMapping(WorkManMessageList.WFS_CARR_MOVE_CRT)
    public ApFlowProcessVo execute(@RequestBody WfsCarrMoveCrtIvo wfsCarrMoveCrtIvo,
                                   @RequestParam(value = "key") String trackingKey,
                                   @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_CARR_MOVE_CRT;
        ApFlowProcessVo apFlowProcessVo = this.wfsCarrMoveCrtService.initialize(cid, trackingKey, scenarioType, wfsCarrMoveCrtIvo.getHead());

        return this.wfsCarrMoveCrtService.execute(apFlowProcessVo, wfsCarrMoveCrtIvo);
    }
    @Autowired
    WfsCarrMoveRepServiceImpl wfsCarrMoveRepService;
    public ApFlowProcessVo execute(@RequestBody WfsCarrMoveRepIvo wfsCarrMoveRepIvo,
                                   @RequestParam(value = "key") String trackingKey,
                                   @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_CARR_MOVE_REP;
        ApFlowProcessVo apFlowProcessVo = this.wfsCarrMoveRepService.initialize(cid, trackingKey, scenarioType, wfsCarrMoveRepIvo.getHead());

        return this.wfsCarrMoveRepService.execute(apFlowProcessVo, wfsCarrMoveRepIvo);
    }
    @Autowired
    WfsCarrMoveStrtServiceImpl wfsCarrMoveStrtService;
    public ApFlowProcessVo execute(@RequestBody WfsCarrMoveStrtIvo wfsCarrMoveStrtIvo,
                                   @RequestParam(value = "key") String trackingKey,
                                   @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_CARR_MOVE_STRT;
        ApFlowProcessVo apFlowProcessVo = this.wfsCarrMoveStrtService.initialize(cid, trackingKey, scenarioType, wfsCarrMoveStrtIvo.getHead());

        return this.wfsCarrMoveStrtService.execute(apFlowProcessVo,wfsCarrMoveStrtIvo);
    }
    @Autowired
    WfsEqpAlrmChgServiceImpl wfsEqpAlrmChgService;
    public ApFlowProcessVo execute(@RequestBody WfsEqpAlrmChgIvo wfsEqpAlrmChgIvo,
                                   @RequestParam(value = "key") String trackingKey,
                                   @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_EQP_ALRM_CHG;
        ApFlowProcessVo apFlowProcessVo = this.wfsEqpAlrmChgService.initialize(cid, trackingKey, scenarioType, wfsEqpAlrmChgIvo.getHead());

        return this.wfsEqpAlrmChgService.execute(apFlowProcessVo,wfsEqpAlrmChgIvo);
    }
    @Autowired
    WfsEqpStatChgServiceImpl wfsEqpStatChgService;
    public ApFlowProcessVo execute(@RequestBody WfsEqpStatChgIvo wfsEqpAlrmChgIvo,
                                   @RequestParam(value = "key") String trackingKey,
                                   @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_EQP_STAT_CHG;
        ApFlowProcessVo apFlowProcessVo = this.wfsEqpStatChgService.initialize(cid, trackingKey, scenarioType, wfsEqpAlrmChgIvo.getHead());

        return this.wfsEqpStatChgService.execute(apFlowProcessVo,wfsEqpAlrmChgIvo);
    }
    @Autowired
    WfsEqpStatRepServiceImpl wfsEqpStatRepService;
    public ApFlowProcessVo execute(@RequestBody WfsEqpStatRepIvo wfsEqpStatRepIvo,
                                   @RequestParam(value = "key") String trackingKey,
                                   @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_EQP_STAT_REP;
        ApFlowProcessVo apFlowProcessVo = this.wfsEqpStatRepService.initialize(cid, trackingKey, scenarioType, wfsEqpStatRepIvo.getHead());

        return this.wfsEqpStatRepService.execute(apFlowProcessVo,wfsEqpStatRepIvo);
    }
    @Autowired
    WfsEqpStgCpctChgServiceImpl wfsEqpStgCpctChgService;
    public ApFlowProcessVo execute(@RequestBody WfsEqpStgCpctChgIvo wfsEqpStgCpctChgIvo,
                                   @RequestParam(value = "key") String trackingKey,
                                   @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_EQP_STG_CPCT_CHG;
        ApFlowProcessVo apFlowProcessVo = this.wfsEqpStgCpctChgService.initialize(cid, trackingKey, scenarioType, wfsEqpStgCpctChgIvo.getHead());

        return this.wfsEqpStgCpctChgService.execute(apFlowProcessVo,wfsEqpStgCpctChgIvo);
    }
    @Autowired
    WfsInvtCarrDataServiceImpl wfsInvtCarrDataService;
    public ApFlowProcessVo execute(@RequestBody WfsInvtCarrDataIvo wfsInvtCarrDataIvo,
                                   @RequestParam(value = "key") String trackingKey,
                                   @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_INVT_CARR_DATA;
        ApFlowProcessVo apFlowProcessVo = this.wfsInvtCarrDataService.initialize(cid, trackingKey, scenarioType, wfsInvtCarrDataIvo.getHead());

        return this.wfsInvtCarrDataService.execute(apFlowProcessVo,wfsInvtCarrDataIvo);
    }
    @Autowired
    WfsInvtDataRepServiceImpl wfsInvtDataRepService;
    public ApFlowProcessVo execute(@RequestBody WfsInvtDataRepIvo wfsInvtDataRepIvo,
                                   @RequestParam(value = "key") String trackingKey,
                                   @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_INVT_DATA_REP;
        ApFlowProcessVo apFlowProcessVo = this.wfsInvtDataRepService.initialize(cid, trackingKey, scenarioType, wfsInvtDataRepIvo.getHead());

        return this.wfsInvtDataRepService.execute(apFlowProcessVo,wfsInvtDataRepIvo);
    }
    @Autowired
    WfsPortStatChgServiceImpl wfsPortStatChgService;
    public ApFlowProcessVo execute(@RequestBody WfsPortStatChgIvo wfsPortStatChgIvo,
                                   @RequestParam(value = "key") String trackingKey,
                                   @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_PORT_STAT_CHG;
        ApFlowProcessVo apFlowProcessVo = this.wfsPortStatChgService.initialize(cid, trackingKey, scenarioType, wfsPortStatChgIvo.getHead());

        return this.wfsPortStatChgService.execute(apFlowProcessVo,wfsPortStatChgIvo);
    }
    @Autowired
    WfsVhclStatChgServiceImpl wfsVhclStatChgService;
    public ApFlowProcessVo execute(@RequestBody WfsVhclStatChgIvo wfsVhclStatChgIvo,
                                   @RequestParam(value = "key") String trackingKey,
                                   @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_VHCL_STAT_CHG;
        ApFlowProcessVo apFlowProcessVo = this.wfsVhclStatChgService.initialize(cid, trackingKey, scenarioType, wfsVhclStatChgIvo.getHead());

        return this.wfsVhclStatChgService.execute(apFlowProcessVo,wfsVhclStatChgIvo);
    }
    @Autowired
    WfsSysStatChgServiceImpl wfsSysStatChgService;
    public ApFlowProcessVo execute(@RequestBody WfsSysStatChgIvo wfsSysStatChgIvo,
                                   @RequestParam(value = "key") String trackingKey,
                                   @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_SYS_STAT_CHG;
        ApFlowProcessVo apFlowProcessVo = this.wfsSysStatChgService.initialize(cid, trackingKey, scenarioType, wfsSysStatChgIvo.getHead());

        return this.wfsSysStatChgService.execute(apFlowProcessVo,wfsSysStatChgIvo);
    }
    @Autowired
    WfsSysStatRepServiceImpl wfsSysStatRepService;
    public ApFlowProcessVo execute(@RequestBody WfsSysStatRepIvo wfsSysStatRepIvo,
                                   @RequestParam(value = "key") String trackingKey,
                                   @RequestParam(value = "scenario") String scenarioType) throws Exception {

        String cid = WorkManMessageList.WFS_SYS_STAT_REP;
        ApFlowProcessVo apFlowProcessVo = this.wfsSysStatRepService.initialize(cid, trackingKey, scenarioType, wfsSysStatRepIvo.getHead());

        return this.wfsSysStatRepService.execute(apFlowProcessVo,wfsSysStatRepIvo);
    }

    @Autowired
    WnTransferJobServiceImpl transferJobService;

    @PostMapping(WorkManMessageList.WFS_OI_CARR_MOVE_CANCEL_REQ)
    public ResponseEntity<CancelTransferJobResultVo> execute(
                                    @RequestParam(value = "siteId") String siteId,
                                    @RequestParam(value = "portId") String portId) throws Exception {

        CancelTransferJobResultVo resultVo = this.transferJobService.cancelTransferJob(siteId, portId);
        log.info(resultVo.toString());

        return new ResponseEntity<>(resultVo, HttpStatus.OK);
    }

}
