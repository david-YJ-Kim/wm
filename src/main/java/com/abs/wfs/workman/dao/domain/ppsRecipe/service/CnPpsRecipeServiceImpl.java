package com.abs.wfs.workman.dao.domain.ppsRecipe.service;


import com.abs.wfs.workman.dao.domain.ppsRecipe.model.CnPpsRecipe;
import com.abs.wfs.workman.dao.domain.ppsRecipe.repository.CnPpsRecipeRepository;
import com.abs.wfs.workman.dao.domain.ppsRecipe.vo.CnPpsRecipeProcEqpRequestDto;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CnPpsRecipeServiceImpl {


    @Autowired
    CnPpsRecipeRepository cnPpsRecipeRepository;


    public Optional<List<CnPpsRecipe>> findBySiteIdAndUseStatCdAndEqpId(String siteId, UseStatCd useStatCd, String eppId){
        return cnPpsRecipeRepository.findBySiteIdAndUseStatCdAndEqpId(siteId, useStatCd, eppId);
    }


    /**
     *
     * @param dto
     * @return
     */
    public Optional<CnPpsRecipe> findEqpProcessingRecipe(CnPpsRecipeProcEqpRequestDto dto){
        return cnPpsRecipeRepository.findBySiteIdAndUseStatCdAndEqpIdAndProdDefIdAndProcDefIdAndProcSgmtId(

                dto.getSiteId(), UseStatCd.Usable, dto.getEqpId(), dto.getProdDefId(), dto.getProcDefId(), dto.getProcSgmtId()
        );
    }
}
