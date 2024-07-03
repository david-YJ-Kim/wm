package com.abs.wfs.workman.service.common;


import com.abs.wfs.workman.config.ApSharedVariable;
import com.abs.wfs.workman.dao.query.model.IamMultiLangErrorCodeIVo;
import com.abs.wfs.workman.dao.query.service.IamQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class ExceptionCodeHandleService {

    @Autowired
    IamQueryService iamQueryService;



    public void loadMultiLandErrorCode(){

        ConcurrentHashMap<String, HashMap<String, String>> map = ApSharedVariable.getInstance().getMultiLangCodeMap();
        List<IamMultiLangErrorCodeIVo> codeIVoList =  this.iamQueryService.selectWfsErrMultiLangErrorInfo();
        for (IamMultiLangErrorCodeIVo codeIVo : codeIVoList){

            if(codeIVo.getMtlgVal() == null) {continue;}

            HashMap<String, String> innerMap = new HashMap<String, String>();
            innerMap.put(codeIVo.getMtlgTypCd(), codeIVo.getMtlgVal());

            map.put(codeIVo.getMtlgKey(),innerMap);
        }

        log.info(map.toString());

    }


    public void reloadMultiLandErrorCode(){
        ApSharedVariable.getInstance().getMultiLangCodeMap().clear();
        this.loadMultiLandErrorCode();
    }


    
}
