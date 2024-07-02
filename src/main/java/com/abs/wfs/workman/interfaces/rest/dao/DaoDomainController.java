package com.abs.wfs.workman.interfaces.rest.dao;


import com.abs.wfs.workman.config.ApPropertyObject;
import com.abs.wfs.workman.dao.domain.pmsProcNode.model.TnPmsProcNode;
import com.abs.wfs.workman.dao.domain.pmsProcNode.service.TnPmsProcNodeServiceImpl;
import com.abs.wfs.workman.dao.domain.posEqpGrpRel.model.CnPosEqpGrpRel;
import com.abs.wfs.workman.dao.domain.posEqpGrpRel.service.CnPosEqpGrpRelServiceImpl;
import com.abs.wfs.workman.dao.domain.ppsProdDef.model.TnPpsProdDef;
import com.abs.wfs.workman.dao.domain.ppsProdDef.service.TnPpsProdDefServiceImpl;
import com.abs.wfs.workman.dao.domain.ppsRecipe.model.CnPpsRecipe;
import com.abs.wfs.workman.dao.domain.ppsRecipe.service.CnPpsRecipeServiceImpl;
import com.abs.wfs.workman.dao.domain.ppsRecipe.vo.CnPpsRecipeProcEqpRequestDto;
import com.abs.wfs.workman.dao.domain.tnLot.model.TnPosLot;
import com.abs.wfs.workman.dao.domain.tnLot.service.TnPosLotServiceImpl;
import com.abs.wfs.workman.dao.domain.tnPort.model.TnPosPort;
import com.abs.wfs.workman.dao.domain.tnPort.service.TnPosPortServiceImpl;
import com.abs.wfs.workman.dao.domain.tnProducedMaterial.model.TnProducedMaterial;
import com.abs.wfs.workman.dao.domain.tnProducedMaterial.service.TnProducedMaterialServiceImpl;
import com.abs.wfs.workman.dao.domain.transferJob.model.WnTransferJob;
import com.abs.wfs.workman.dao.domain.transferJob.service.WnTransferJobServiceImpl;
import com.abs.wfs.workman.dao.domain.wipStat.model.WnWipStat;
import com.abs.wfs.workman.dao.domain.wipStat.repository.WnWipStatRepository;
import com.abs.wfs.workman.dao.domain.wipStat.service.WipStatServiceImpl;
import com.abs.wfs.workman.dao.domain.workJobSlotInfo.model.WnWorkJobSlotInfo;
import com.abs.wfs.workman.dao.domain.workJobSlotInfo.service.WnWorkJobSlotInfoServiceImpl;
import com.abs.wfs.workman.dao.domain.workStat.model.WnWorkStat;
import com.abs.wfs.workman.dao.domain.workStat.service.WnWorkStatServiceImpl;
import com.abs.wfs.workman.dao.query.wipLot.vo.WipLotDto;
import com.abs.wfs.workman.util.code.UseStatCd;
import com.netflix.discovery.converters.Auto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/dao/domain/")
@RequiredArgsConstructor
public class DaoDomainController {

    @Autowired
    CnPosEqpGrpRelServiceImpl cnPosEqpGrpRelService;

    @GetMapping("search/eqpGroup")
    public List<CnPosEqpGrpRel> searchEqpGroup(@RequestParam String groupName) {

        return this.cnPosEqpGrpRelService.findBySiteIdAndUseStatCdAndEqpGrpId(ApPropertyObject.getInstance().getSiteName(),
                UseStatCd.Usable, groupName);
    }

    @GetMapping("search/eqpGroup/{groupName}/eqpId/{eqpId}")
    public Optional<CnPosEqpGrpRel> searchEqpGroupWithEqpId(@PathVariable String groupName, @PathVariable String eqpId) {

        return this.cnPosEqpGrpRelService.findBySiteIdAndUseStatCdAndEqpGrpIdAndEqpId(ApPropertyObject.getInstance().getSiteName(),
                UseStatCd.Usable, groupName, eqpId);
    }

    /**
     * tnPort
     */
    @Autowired
    TnPosPortServiceImpl tnPosPortService;

    @GetMapping("search/tnPort/siteId/{siteId}/portId/{portId}")
    public Optional<TnPosPort> findByPortIdAndSiteIdAndUseStatCd(@PathVariable String siteId, @PathVariable String portId) {

        return this.tnPosPortService.findByPortIdAndSiteIdAndUseStatCd(portId, siteId);
    }


    @GetMapping("search/tnPort/siteId/{siteId}/eqpId/{eqpId}")
    public Optional<List<TnPosPort>> findByEqpIdAndSiteIdAndUseStatCd(@PathVariable String siteId, @PathVariable String eqpId) {

        return this.tnPosPortService.findByEqpIdAndSiteIdAndUseStatCd(eqpId, siteId);
    }



    /**
     * transfer
     */
    @Autowired
    WnTransferJobServiceImpl wnTransferJobService;

    @GetMapping("search/transfer/siteId/{siteId}/objId/{objId}")
    public Optional<WnTransferJob> findByObjIdAndUseStatCd(@PathVariable String siteId, @PathVariable String objId) {

        return this.wnTransferJobService.findByObjIdAndUseStatCd(siteId, objId, UseStatCd.Usable);
    }



    @GetMapping("search/transfer/siteId/{siteId}/jobId/{jobId}")
    public Optional<List<WnTransferJob>> findByJobIdAndUseStatCd(@PathVariable String siteId, @PathVariable String jobId) {

        return this.wnTransferJobService.findByJobIdAndUseStatCd(siteId, jobId, UseStatCd.Usable);
    }



    @GetMapping("search/transfer/siteId/{siteId}/carrId/{carrId}")
    public Optional<List<WnTransferJob>> findByCarrIdAndUseStatCd(@PathVariable String siteId, @PathVariable String carrId) {

        return this.wnTransferJobService.findByCarrIdAndUseStatCd(siteId, carrId, UseStatCd.Usable);
    }




    /**
     * wipStat
     */
    @Autowired
    WipStatServiceImpl wipStatService;

    @GetMapping("search/wipStat/siteId/{siteId}/lotId/{lotId}")
    public Optional<WnWipStat> findByLotIdAndSiteIdAndUseStatCd(@PathVariable String siteId, @PathVariable String lotId) {

        return this.wipStatService.findByLotIdAndSiteIdAndUseStatCd(lotId, siteId);
    }

    @GetMapping("search/wipStat/siteId/{siteId}/lotId/{lotId}/resvEqpId/{resvEqpId}/resvPortId/{resvPortId}")
    public Optional<WnWipStat> findByLotIdAndSiteIdAndUseStatCdAndResvEqpIdAndResvPortIdAndUseStatCd(@PathVariable String siteId, @PathVariable String lotId,
                                                                                                       @PathVariable String resvEqpId, @PathVariable String resvPortId) {

        return this.wipStatService.findByLotIdAndSiteIdAndUseStatCdAndResvEqpIdAndResvPortIdAndUseStatCd(lotId, siteId, resvEqpId, resvPortId);
    }


    @GetMapping("search/wipStat/siteId/{siteId}/carrId/{carrId}")
    public Optional<WnWipStat> findByOnlyCarrIdAndSiteIdAndUseStatCd(@PathVariable String siteId, @PathVariable String carrId) {

        return this.wipStatService.findByOnlyCarrIdAndSiteIdAndUseStatCd(carrId, siteId);
    }




    @GetMapping("search/wipStat/siteId/{siteId}/carrId/{carrId}/lotId/{lotId}")
    public Optional<WnWipStat> findByCarrIdAndLotIdAndSiteIdAndUseStatCd(@PathVariable String siteId, @PathVariable String carrId, @PathVariable String lotId) {

        return this.wipStatService.findByCarrIdAndLotIdAndSiteIdAndUseStatCd(carrId, lotId, siteId);
    }





    /**
     * workJosSlotInfo
     */
    @Autowired
    WnWorkJobSlotInfoServiceImpl wnWorkJobSlotInfoService;

    @GetMapping("search/workJobSlotInfo/workId/{workId}/siteId/{siteId}")
    public Optional<List<WnWorkJobSlotInfo>> findByWorkIdAndSiteIdAndUseStatCd(@PathVariable String workId, @PathVariable String siteId) {

        return this.wnWorkJobSlotInfoService.findByWorkIdAndSiteIdAndUseStatCd(workId, siteId);
    }


    @GetMapping("search/workJobSlotInfo/StrtTmIsNotNull/workId/{workId}/siteId/{siteId}")
    public Optional<List<WnWorkJobSlotInfo>> findByWorkIdAndSiteIdAndUseStatCdAndProdMtrlStrtTmIsNotNull(@PathVariable String workId, @PathVariable String siteId) {

        return this.wnWorkJobSlotInfoService.findByWorkIdAndSiteIdAndUseStatCdAndProdMtrlStrtTmIsNotNull(workId, siteId);
    }


    @GetMapping("search/workJobSlotInfo/SelfInspYn/workId/{workId}/siteId/{siteId}")
    public Optional<List<WnWorkJobSlotInfo>> findByWorkIdAndSiteIdAndUseStatCdAndSelfInspYn(@PathVariable String workId, @PathVariable String siteId) {

        return this.wnWorkJobSlotInfoService.findByWorkIdAndSiteIdAndUseStatCdAndSelfInspYn(workId, siteId);
    }


    /**
     * workStat
     */
    @Autowired
    WnWorkStatServiceImpl wnWorkStatService;

    @GetMapping("search/workStat/siteId/{siteId}/workId/{workId}")
    public Optional<WnWorkStat> findByActiveAndUsableWorkWithWorkId(@PathVariable String siteId, @PathVariable String workId) {

        return this.wnWorkStatService.findByActiveAndUsableWorkWithWorkId(siteId, workId);
    }





    /**
     * tnProducedMaterial
     */
    @Autowired
    TnProducedMaterialServiceImpl tnProducedMaterialService;

    @GetMapping("search/tnProducedMaterial/siteId/{siteId}/lotId/{lotId}")
    public Optional<List<TnProducedMaterial>> findBySiteIdAndLotIdAndUseStatCdOrderBySlotNo(@PathVariable String siteId, @PathVariable String lotId) {

        return this.tnProducedMaterialService.findBySiteIdAndLotIdAndUseStatCdOrderBySlotNo(siteId, lotId, UseStatCd.Usable);
    }




    /**
     * pmsProcNode
     */
    @Autowired
    TnPmsProcNodeServiceImpl tnPmsProcNodeService;

    @GetMapping("search/pmsProcNode/siteId/{siteId}/procNodeId/{procNodeId}")
    public Optional<TnPmsProcNode> findBySiteIdAndUseStatCdAndProcNodeId(@PathVariable String siteId, @PathVariable String procNodeId) {

        return this.tnPmsProcNodeService.findBySiteIdAndUseStatCdAndProcNodeId(siteId, UseStatCd.Usable, procNodeId);
    }



    /**
     * ppsProdDef
     */
    @Autowired
    TnPpsProdDefServiceImpl tnPpsProdDefService;

    @GetMapping("search/ppsProdDef/siteId/{siteId}/prodDefId/{prodDefId}")
    public Optional<TnPpsProdDef> findBySiteIdAndUseStatCdAndProdDefIdAndShipUnitTypIsNotNull(@PathVariable String siteId, @PathVariable String prodDefId) {

        return this.tnPpsProdDefService.findBySiteIdAndUseStatCdAndProdDefIdAndShipUnitTypIsNotNull(siteId, UseStatCd.Usable, prodDefId);
    }





    /**
     * tnLot
     */
    @Autowired
    TnPosLotServiceImpl tnPosLotService;


    @GetMapping("search/tnLot/siteId/{siteId}/lotId/{lotId}")
    public Optional<TnPosLot> findBySiteIdAndLotIdAndUseStatCd(@PathVariable String siteId, @PathVariable String lotId) {

        return this.tnPosLotService.findBySiteIdAndLotIdAndUseStatCd(siteId, lotId, UseStatCd.Usable);
    }



    /**
     * ppsRecipe
     */
    @Autowired
    CnPpsRecipeServiceImpl cnPpsRecipeService;


    @GetMapping("search/ppsRecipe/siteId/{siteId}/eqpId/{eqpId}")
    public Optional<List<CnPpsRecipe>> findBySiteIdAndUseStatCdAndEqpId(@PathVariable String siteId, @PathVariable String eqpId) {

        return this.cnPpsRecipeService.findBySiteIdAndUseStatCdAndEqpId(siteId, UseStatCd.Usable, eqpId);
    }



    @PostMapping("search/ppsRecipe/findEqpProcessingRecipe")
    public Optional<CnPpsRecipe> findEqpProcessingRecipe(@RequestBody CnPpsRecipeProcEqpRequestDto dto) throws Exception {

        return this.cnPpsRecipeService.findEqpProcessingRecipe(dto);

    }

}
