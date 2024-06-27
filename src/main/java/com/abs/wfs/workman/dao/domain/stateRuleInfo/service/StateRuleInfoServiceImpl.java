package com.abs.wfs.workman.dao.domain.stateRuleInfo.service;

import com.abs.wfs.workman.dao.domain.stateRuleInfo.model.WnStateRuleInfo;
import com.abs.wfs.workman.dao.domain.stateRuleInfo.repository.WnStateRuleInfoRepository;
import com.abs.wfs.workman.dao.domain.stateRuleInfo.vo.WnStateRuleInfoDto;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StateRuleInfoServiceImpl {

    @Autowired
    WnStateRuleInfoRepository wnStateRuleInfoRepository;

    public Optional<WnStateRuleInfo> getEntityByObjId(String objId){
        return Optional.of(this.wnStateRuleInfoRepository.findById(objId)).get();
    }

    public List<WnStateRuleInfo> findBySiteIdAndUseStatCd(String site, UseStatCd useStatCd){
        return this.wnStateRuleInfoRepository.findBySiteIdAndUseStatCd(site, useStatCd);
    }

    public List<WnStateRuleInfo> getAllEntities(){
        return this.wnStateRuleInfoRepository.findAll();
    }

    public WnStateRuleInfo saveEntity(WnStateRuleInfoDto wnStateRuleInfoRequestVo){
        log.info(wnStateRuleInfoRequestVo.toString());
        try{
            WnStateRuleInfo entity = wnStateRuleInfoRequestVo.toEntity();
            return this.wnStateRuleInfoRepository.save(entity);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
    }

    public void deleteEntityByObjId(String objId){
        this.wnStateRuleInfoRepository.deleteById(objId);
    }
}
