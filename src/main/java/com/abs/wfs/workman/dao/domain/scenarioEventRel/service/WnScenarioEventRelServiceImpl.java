package com.abs.wfs.workman.dao.domain.scenarioEventRel.service;


import com.abs.wfs.workman.dao.domain.scenarioEventRel.model.WnScenarioEventRel;
import com.abs.wfs.workman.dao.domain.scenarioEventRel.repository.WnScenarioEventRelRepository;
import com.abs.wfs.workman.dao.domain.scenarioEventRel.vo.WnScenarioEventSaveReqVo;
import com.abs.wfs.workman.util.code.UseYn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class WnScenarioEventRelServiceImpl {

    @Autowired
    WnScenarioEventRelRepository wnScenarioEventRelRepository;


    public WnScenarioEventRel insertEntity(WnScenarioEventSaveReqVo vo) {

        return this.wnScenarioEventRelRepository.save(WnScenarioEventRel.builder()
                        .eventName(vo.getEventName())
                        .scenarioType(vo.getScenarioType())
                        .sourceSystem(vo.getSourceSystem())
                        .targetSystem(vo.getTargetSystem())
                .build());

    }


}
