//package com.abs.wfs.workman.interfaces.rest.common.staterule;
//
//
//import com.abs.wfs.workman.config.ApPropertyObject;
//import com.abs.wfs.workman.dao.domain.stateRuleInfo.model.WnStateRuleInfo;
//import com.abs.wfs.workman.dao.domain.stateRuleInfo.service.StateRuleInfoServiceImpl;
//import com.abs.wfs.workman.dao.query.common.vo.StateRuleInfo;
//import com.abs.wfs.workman.service.common.staterule.StateRuleManager;
//import com.abs.wfs.workman.util.code.UseStatCd;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@Slf4j
//@RestController
//@CrossOrigin
//@RequestMapping("/common/staterule/")
//@RequiredArgsConstructor
//public class StateRuleController {
//
//    @Autowired
//    StateRuleManager stateRuleManager;
//
//    @Autowired
//    StateRuleInfoServiceImpl stateRuleInfoService;
//
//
//    @GetMapping("ruleInfo")
//    public List<WnStateRuleInfo> getRuleInfo() {
//
//        return this.stateRuleInfoService.findBySiteIdAndUseStatCd(ApPropertyObject.getInstance().getSiteName(), UseStatCd.Usable);
//
//    }
//}
