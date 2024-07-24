package com.abs.wfs.workman.service.common.staterule;

import com.abs.wfs.workman.config.ApPropertyObject;
import com.abs.wfs.workman.dao.domain.stateRuleInfo.model.WnStateRuleInfo;
import com.abs.wfs.workman.dao.domain.stateRuleInfo.service.StateRuleInfoServiceImpl;
import com.abs.wfs.workman.dao.query.model.QueryPortVO;
import com.abs.wfs.workman.dao.query.tool.vo.QueryEqpVo;
import com.abs.wfs.workman.dao.query.tool.vo.QueryPortVo;
import com.abs.wfs.workman.util.code.StateRuleList;
import com.abs.wfs.workman.util.code.UseStatCd;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class StateRuleManager {

    RuleChecker ruleChecker;

    @Autowired
    StateRuleInfoServiceImpl stateRuleInfoService;

    @Getter
    private List<WnStateRuleInfo> wnStateRuleInfoList;



    public void initializeResource(){
        if(ruleChecker == null) {this.ruleChecker = new RuleChecker();}

        if(this.wnStateRuleInfoList != null){
            log.warn("Already set wnStateRuleInfoList. re load data.");
        }
        this.wnStateRuleInfoList = loadUsableRuleInfoBySite();

    }



    public void validateEqpStateRule(String siteId, String eqpId, String portId, String validationType, QueryEqpVo queryEqpVo) {
        try {
            switch (validationType) {
                case StateRuleList.ValidEqp:
                    this.validEqp(siteId, eqpId, queryEqpVo);
                    break;
                case StateRuleList.FullAutoEqp:
                    this.fullAutoEqp(siteId, eqpId, queryEqpVo);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid validation type: " + validationType);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




    public void validatePortStateRule(String siteId, String eqpId, String portId, String validationType, QueryPortVO queryPortVO) {
        try {
            switch (validationType) {
                case StateRuleList.ValidPort:
                    this.validPort(siteId, eqpId, portId, queryPortVO);
                    break;
                case StateRuleList.FullAutoPort:
                    this.fullAutoPort(siteId, eqpId, portId, queryPortVO);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid validation type: " + validationType);
            }
        } catch (Exception e) {
            
            // TODO Scenario Exception 처리하기
            throw new RuntimeException(e);
        }
    }


    /**
     * Rule : EQP Validation
     * TN_POS_EQP - STAT_CD : Up
     * 			  - PROC_STAT_CD : Run or Idle
     * 			  - SORTER_MODE_YN : !Y
     * @param siteId
     * @param eqpId
     * @return
     * @throws Exception
     */
    public boolean validEqp(String siteId, String eqpId, QueryEqpVo eqpVO) throws Exception {
        log.info("StateRule Check : validEqp");
        ObjectMapper objectMapper = new ObjectMapper();
        //TN_POS_EQP Table Select
//		Map<String, String> eqpData = CommonDAO.getInstance().getEqp(siteId, eqpId);

        Map<String, String> eqpData = objectMapper.convertValue(eqpVO, Map.class);


        log.info("eqpData select >> " + eqpData);

        // validEqp Rule Check
        RuleChecker.RuleCheckResult checkResult = ruleChecker.checkRule(StateRuleList.ValidEqp, eqpData, this.wnStateRuleInfoList);

        log.info("RuleCheckResult >> " + checkResult);

        if(!checkResult.isResult())
            throw new Exception("ValidEqp ERROR : "+checkResult.getResultList().toString());

        return checkResult.isResult();
    }

    /**
     * Rule : EQP FullAuto
     * TN_POS_EQP - CTLR_MODE_CD : OnlineRemote
     * 			  - OPRTN_MODE_CD : Auto
     * 			  - AUTO_DSP_YN : Y
     * @param siteId
     * @param eqpId
     * @return
     * @throws Exception
     */
    public boolean fullAutoEqp(String siteId, String eqpId, QueryEqpVo eqpVO) throws Exception {
        log.info("StateRule Check : fullAutoEqp");
        ObjectMapper objectMapper = new ObjectMapper();
        @SuppressWarnings("unchecked")
        Map<String, String> eqpData = objectMapper.convertValue(eqpVO, Map.class);

        // validEqp Rule Check
        RuleChecker.RuleCheckResult checkResult = ruleChecker.checkRule(StateRuleList.FullAutoEqp, eqpData, this.wnStateRuleInfoList);

        if(!checkResult.isResult())
            throw new Exception("fullAutoEqp ERROR : "+checkResult.getResultList().toString());

        return checkResult.isResult();
    }


    /**
     * Rule : EQP AutoModeEqp
     * TN_POS_EQP - CTLR_MODE_CD : OnlineRemote
     * 			  - OPRTN_MODE_CD : Auto
     * 			  - AUTO_DSP_YN : N
     * @param siteId
     * @param eqpId
     * @return
     * @throws Exception
     */
    public boolean autoModeEqp(String siteId, String eqpId, QueryEqpVo eqpVO) throws Exception {
        log.info("StateRule Check : AutoModeEqp");
        ObjectMapper objectMapper = new ObjectMapper();
        @SuppressWarnings("unchecked")
        Map<String, String> eqpData = objectMapper.convertValue(eqpVO, Map.class);

        // validEqp Rule Check
        RuleChecker.RuleCheckResult checkResult = ruleChecker.checkRule(StateRuleList.AutoModeEqp, eqpData, this.wnStateRuleInfoList);

        if(!checkResult.isResult())
            throw new Exception("AutoModeEqp ERROR : "+checkResult.getResultList().toString());

        return checkResult.isResult();
    }


    /**
     * Rule : Port Validation
     * @param siteId
     * @param eqpId
     * @param portId
     * @param portVO
     * @return
     * @throws Exception
     */
    public boolean validPort(String siteId, String eqpId, String portId, QueryPortVO portVO) throws Exception {
        log.info("StateRule Check : validPort");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> portData = objectMapper.convertValue(portVO, Map.class);


        log.info("query result. Port data: {} ", portData.toString());

        // ValidPort Rule Check
        RuleChecker.RuleCheckResult checkResult = ruleChecker.checkRule(StateRuleList.ValidPort, portData, this.wnStateRuleInfoList);

        if(!checkResult.isResult()){
            log.error("Valid port : {}", checkResult.getResultList().toString());
            throw new Exception("ValidPort ERROR : " + checkResult.getResultList().toString());
        }

        return checkResult.isResult();
    }


    /**
     * Port FullAuto
     * @param siteId
     * @param eqpId
     * @param portId
     * @return
     * @throws Exception
     */
    public boolean fullAutoPort(String siteId, String eqpId, String portId, QueryPortVO portVO) throws Exception {
        log.info("StateRule Check : FullAutoPort");
        ObjectMapper objectMapper = new ObjectMapper();

        //TN_POS_PORT Table Select
//		Map<String, String> portData = CommonDAO.getInstance().getPort(siteId, eqpId, portId);

        @SuppressWarnings("unchecked")
        Map<String, String> portData = objectMapper.convertValue(portVO, Map.class);

        // FullAotoPort Rule Check
        RuleChecker.RuleCheckResult checkPortResult = ruleChecker.checkRule(StateRuleList.FullAutoPort, portData, this.wnStateRuleInfoList);

        if(!checkPortResult.isResult())
            throw new Exception("FullAutoPort ERROR : "+checkPortResult.getResultList().toString());

        return  checkPortResult.isResult();
    }

    /**
     * Port Auto
     * TN_POS_PORT	CTRL_MODE_CD	InService	eq
     ACES_MODE_CD	Auto	eq
     * @param siteId
     * @param eqpId
     * @param portId
     * @return
     * @throws Exception
     */
    public boolean autoPort(String siteId, String eqpId, String portId, QueryPortVo portVO) throws Exception {
        log.info("StateRule Check : AutoPort");
        ObjectMapper objectMapper = new ObjectMapper();

        //TN_POS_PORT Table Select
//		Map<String, String> portData = CommonDAO.getInstance().getPort(siteId, eqpId, portId);

        @SuppressWarnings("unchecked")
        Map<String, String> portData = objectMapper.convertValue(portVO, Map.class);

        // FullAotoPort Rule Check
        RuleChecker.RuleCheckResult checkPortResult = ruleChecker.checkRule(StateRuleList.AutoPort, portData, this.wnStateRuleInfoList);

        if(!checkPortResult.isResult())
            throw new Exception("AutoPort ERROR : "+checkPortResult.getResultList().toString());

        return  checkPortResult.isResult();
    }


//    /**
//     * Lot Validation
//     * @param siteId
//     * @param lotId
//     * @return
//     * @throws Exception
//     */
//    public boolean ValidLot(String siteId, String lotId, QueryLotVO lotVO) throws Exception {
//        log.info("StateRule Check : ValidLot");
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        //TN_POS_LOT Table Select
////		Map<String, String> lotData = CommonDAO.getInstance().getLotInfo(siteId, lotId);
//
//
//        @SuppressWarnings("unchecked")
//        Map<String, String> lotData = objectMapper.convertValue(lotVO, Map.class);
//
//
//        RuleCheckResult checkLotResult = ruleChecker.checkRule(StateRuleConstant.ValidLot, lotData);
//
//        if(!checkLotResult.isResult())
//            throw new Exception("ValidLot ERROR : "+checkLotResult.getResultList().toString());
//
//        return checkLotResult.isResult();
//    }
//
//    /**
//     * Carrier Validation
//     * @param siteId
//     * @param carrId
//     * @return
//     * @throws Exception
//     */
//    public boolean ValidCarr(String siteId, String carrId) throws Exception {
//
//        // TN_POS_CARRIER
//        Map<String, String> carrData = CommonDAO.getInstance().getCarrierInfo(siteId, carrId);
//
//        RuleCheckResult checkCarrResult = ruleChecker.checkRule(StateRuleConstant.ValidCarr, carrData);
//
//        return checkCarrResult.isResult();
//
//    }
//
//    /**
//     * Validate Carrier Location
//     * @param siteId
//     * @param eqpId
//     * @param portId
//     * @param carrId
//     * @return
//     * @throws Exception
//     */
//    public boolean ValidCarrLoc(String siteId, String eqpId, String portId, String carrId) throws Exception {
//
//        Map<String, String> lotData = CommonDAO.getInstance().getLotInfoByCarr(siteId, carrId);
//
//        lotData.put("#EQP_ID", eqpId);
//        lotData.put("#PORT_ID", portId);
//
//        RuleCheckResult checkLotResult = ruleChecker.checkRule(StateRuleConstant.ValidCarrLoc, lotData);
//
//        return checkLotResult.isResult();
//
//    }
//
    /**
     * Port Status : LOADING PORT
     * @param siteId
     * @param eqpId
     * @param portId
     * @return
     * @throws Exception
     */
    public boolean IsLoadedPort(String siteId, String eqpId, String portId, QueryPortVo portVO) throws Exception {
        log.info("StateRule Check : IsLoadedPort");
        ObjectMapper objectMapper = new ObjectMapper();

        //TN_POS_PORT Table Select
//		Map<String, String> portData = CommonDAO.getInstance().getPort(siteId, eqpId, portId);

        @SuppressWarnings("unchecked")
        Map<String, String> portData = objectMapper.convertValue(portVO, Map.class);

        RuleChecker.RuleCheckResult checkPortResult = ruleChecker.checkRule(StateRuleList.IsLoadedPort, portData, this.wnStateRuleInfoList);

        if(!checkPortResult.isResult())
            throw new Exception("IsLoadedPort ERROR : "+checkPortResult.getResultList().toString());

        return checkPortResult.isResult();
    }
//
//    /**
//     * Work 유효성 체크
//     * @param siteId
//     * @return
//     * @throws Exception
//     */
//    public RuleCheckResult ValidWork(String siteId) throws Exception {
//        RuleCheckResult checkWorkResult = null;
//
//        return checkWorkResult;
//    }


    synchronized public boolean reloadStateRuleList(){

        this.wnStateRuleInfoList.clear();
        try{
            this.wnStateRuleInfoList = loadUsableRuleInfoBySite();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        }
    }

    private List<WnStateRuleInfo> loadUsableRuleInfoBySite(){
        return this.stateRuleInfoService.findBySiteIdAndUseStatCd(ApPropertyObject.getInstance().getSiteName(), UseStatCd.Usable);
    }

    class RuleChecker {


        private String toCamelCalse(String s) {
            String[] parts = s.split("_");
            String camelCaseString = "";
            boolean isFirst = true; //ON
            for (String part : parts) {
                camelCaseString = camelCaseString + toProperCase(isFirst, part);
                isFirst = false; // OFF
            }
            return camelCaseString;
        }

        private String toProperCase(boolean firstUpper, String s) {
            if(firstUpper) {
                return s.toLowerCase();
            }
            return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
        }


        /**
         * Rule Name별 상태 체크
         * @param ruleName 체크 대상 Rule 명칭
         * @param data Map<String, String> rule check Query Select 결과 전달
         * @return RuleCheckResult
         * 			isResult() : rule Check 성공여부
         * 			getResultList() : rule Check 결과 List<String>
         */
        public RuleCheckResult checkRule(String ruleName, Map<String, String> data, List<WnStateRuleInfo> ruleList) {
            RuleCheckResult result = new RuleCheckResult();

            for(WnStateRuleInfo rule : ruleList) {
                String comment = "";
                String ruleColumnName = toCamelCalse(rule.getColumnNm());
                log.info("### Rule Column Name [camelCase] >>" + ruleColumnName);

                if(data.get(ruleColumnName) != null) {

                    comment = rule.getTableNm() + "[" + rule.getColumnNm() + "=" + data.get(ruleColumnName) + "(" + rule.getColumnVal() + ":" + rule.getCondVal()+ ")]";
                    String compareVal = "";
                    if(rule.getColumnVal().startsWith("#")) {
                        compareVal = data.get(rule.getColumnVal());
                    } else {
                        compareVal = rule.getColumnVal();
                    }


                    switch (rule.getCondVal().name()) {
                        case "eq":
                            if (data.get(ruleColumnName).equals(compareVal)) {
                                comment += "- OK";
                            } else {
                                result.setResult(false);
                                comment += "- NG";
                            }
                            break;
                        case "ne":
                            if (!data.get(ruleColumnName).equals(compareVal)) {
                                comment += "- OK";
                            } else {
                                result.setResult(false);
                                comment += "- NG";
                            }
                            break;
                        case "in":
                            if(rule.getColumnVal().contains(data.get(ruleColumnName))) {
                                comment += "- OK";
                            } else {
                                result.setResult(false);
                                comment += "- NG";
                            }
                            break;
                        default:
                            ;
                    }
                }
                else {
                    result.setResult(false);
                    comment = "Data Not Exist + " + rule.getTableNm() + "[" + rule.getColumnNm() +"]";
                }


                result.putResultList(comment);
                log.info("### RuleCheckResult >> " + comment);
            }


            return result;
        }

        class RuleCheckResult {
            private boolean result;
            private final List<String> resultList;

            public RuleCheckResult() {
                result = true;
                resultList = new ArrayList<String>();
            }

            public boolean isResult() {
                return result;
            }

            public void setResult(boolean result) {
                this.result = result;
            }

            public List<String> getResultList() {
                return resultList;
            }

            public boolean putResultList(String result) {
                return resultList.add(result);
            }

        }
    }
}

