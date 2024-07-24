package com.abs.wfs.workman.interfaces.rest.common.staterule;


import com.abs.wfs.workman.config.ApPropertyObject;
import com.abs.wfs.workman.dao.domain.stateRuleInfo.model.WnStateRuleInfo;
import com.abs.wfs.workman.dao.domain.stateRuleInfo.service.StateRuleInfoServiceImpl;
import com.abs.wfs.workman.dao.query.common.vo.StateRuleInfo;
import com.abs.wfs.workman.dao.query.model.QueryEqpVO;
import com.abs.wfs.workman.dao.query.model.QueryPortVO;
import com.abs.wfs.workman.dao.query.service.WfsCommonQueryService;
import com.abs.wfs.workman.service.common.staterule.StateRuleManager;
import com.abs.wfs.workman.util.code.StateRuleList;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/common/staterule/")
@RequiredArgsConstructor
public class StateRuleController {

    @Autowired
    StateRuleManager stateRuleManager;

    @Autowired
    StateRuleInfoServiceImpl stateRuleInfoService;

    @Autowired
    WfsCommonQueryService wfsCommonQueryService;


    @GetMapping("ruleInfo")
    public List<WnStateRuleInfo> getRuleInfo() {

        return this.stateRuleInfoService.findBySiteIdAndUseStatCd(ApPropertyObject.getInstance().getSiteName(), UseStatCd.Usable);

    }


    @GetMapping("check/siteId/{siteId}/eqpId/{eqpId}/portId/{portId}/type/{type}")
    public String getQueryPort(@PathVariable String siteId, @PathVariable String eqpId, @PathVariable String portId, @PathVariable String type) throws Exception {

        QueryPortVO queryPortVO = this.wfsCommonQueryService.getQueryPortVO(siteId, eqpId, portId);

       try{
           this.stateRuleManager.validatePortStateRule(siteId, eqpId, portId, type, queryPortVO);
            return "Pass";
       }catch (Exception e){
           return e.getMessage();
       }
    }


    @GetMapping("check/siteId/{siteId}/eqpId/{eqpId}/")
    public QueryEqpVO getQueryEqp(@PathVariable String siteId, @PathVariable String eqpId) throws Exception {

        return this.wfsCommonQueryService.getQueryEqpVO(siteId, eqpId);
    }

}
