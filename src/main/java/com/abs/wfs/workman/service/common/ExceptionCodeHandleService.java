package com.abs.wfs.workman.service.common;


import com.abs.wfs.workman.config.ApSharedVariable;
import com.abs.wfs.workman.dao.query.model.IamMultiLangErrorCodeIVo;
import com.abs.wfs.workman.dao.query.service.IamQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class ExceptionCodeHandleService {

    @Autowired
    IamQueryService iamQueryService;



    public void loadMultiLandErrorCode(){

        ConcurrentHashMap<String, HashMap<String, String>> map = ApSharedVariable.getInstance().getMultiLangCodeMap();
        List<IamMultiLangErrorCodeIVo> codeIVoList =  this.iamQueryService.selectWfsErrMultiLangErrorInfo();
        log.info("Load error lang exception format. List: {}", codeIVoList);


        for (IamMultiLangErrorCodeIVo codeIVo : codeIVoList){

            if(codeIVo.getMtlgVal() == null) {continue;}

            String code = codeIVo.getMtlgKey();     // WFS_ERR_TRAN_JOB_STAT_UNMATCHED
            String lang = codeIVo.getMtlgTypCd();   // en
            String format = codeIVo.getMtlgVal();   // CST ({0}) ...


            HashMap<String, String> innerMap = null;
            if(map.containsKey(code)){
                innerMap = map.get(code);
                innerMap.put(lang, format);

            }else {
                /* en: error code for english */
                innerMap = new HashMap<String, String>();
                innerMap.put(lang, format);
                map.put(code, innerMap);
            }

        }
        log.info(map.toString());


    }


    public void reloadMultiLandErrorCode(){
        ApSharedVariable.getInstance().getMultiLangCodeMap().clear();
        this.loadMultiLandErrorCode();
    }


    
}
