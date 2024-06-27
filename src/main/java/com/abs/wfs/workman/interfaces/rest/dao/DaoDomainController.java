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
import com.abs.wfs.workman.dao.domain.tnProducedMaterial.model.TnProducedMaterial;
import com.abs.wfs.workman.dao.domain.tnProducedMaterial.service.TnProducedMaterialServiceImpl;
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
