package com.abs.wfs.workman.interfaces.rest.dao;


import com.abs.wfs.workman.dao.query.eqp.service.EqpServiceImpl;
import com.abs.wfs.workman.dao.query.model.IamMultiLangErrorCodeIVo;
import com.abs.wfs.workman.dao.query.model.IamUserInfoIVo;
import com.abs.wfs.workman.dao.query.model.QueryEqpVO;
import com.abs.wfs.workman.dao.query.model.QueryPortVO;
import com.abs.wfs.workman.dao.query.service.IamQueryService;
import com.abs.wfs.workman.dao.query.service.WfsCommonQueryService;
import com.abs.wfs.workman.dao.query.service.vo.UpdatePortInfoRequestIvo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/dao/query/")
@RequiredArgsConstructor
public class DaoQueryController {


    @Autowired
    WfsCommonQueryService wfsCommonQueryService;

    @PostMapping("common/update/portUnloadCompleted")
    public int search(@RequestBody UpdatePortInfoRequestIvo ivo) throws Exception {

        return this.wfsCommonQueryService.updatePortUnloadCompleted(ivo.getSiteId(),ivo.getCid(), ivo.getTid(), ivo.getUserId(), ivo.getStatCd(), ivo.getTrsfStatCd(), ivo.getEqpId(), ivo.getPortId());

    }



    @GetMapping("eqp/search/siteId/{siteId}/eqpId/{eqpId}/portId/{portId}")
    public QueryPortVO getQueryPort(@PathVariable String siteId, @PathVariable String eqpId, @PathVariable String portId) throws Exception {

        return this.wfsCommonQueryService.getQueryPortVO(siteId, eqpId, portId);
    }


    @GetMapping("eqp/search/siteId/{siteId}/eqpId/{eqpId}/")
    public QueryEqpVO getQueryEqp(@PathVariable String siteId, @PathVariable String eqpId) throws Exception {

        return this.wfsCommonQueryService.getQueryEqpVO(siteId, eqpId);
    }



    @Autowired
    IamQueryService iamQueryService;

    @GetMapping("iam/search/userInfo/siteId/{siteId}/userId/{userId}")
    public List<IamUserInfoIVo> findBySiteIdAndUseStatCdAndEqpId(@PathVariable String siteId, @PathVariable String userId) {

        return this.iamQueryService.selectUserInfoByMailGrp(siteId, userId);
    }

    @GetMapping("iam/search/errorCd")
    public List<IamMultiLangErrorCodeIVo> selectWfsErrMultiLangErrorInfo() {

        return this.iamQueryService.selectWfsErrMultiLangErrorInfo();
    }

}
