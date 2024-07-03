package com.abs.wfs.workman.dao.query.service;


import com.abs.wfs.workman.dao.query.mapper.IamQueryMapper;
import com.abs.wfs.workman.dao.query.model.IamMultiLangErrorCodeIVo;
import com.abs.wfs.workman.dao.query.model.IamUserInfoIVo;
import com.abs.wfs.workman.util.code.UseYn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class IamQueryService {


    @Autowired
    IamQueryMapper iamQueryMapper;

    public List<IamUserInfoIVo> selectUserInfoByMailGrp(String siteId, String userId){

        return iamQueryMapper.selectUserInfo(IamUserInfoIVo.builder()
                                                        .siteId(siteId)
                                                        .deleteYn(UseYn.N)
                                                        .userId(userId.startsWith("OI_") ? userId.substring("OI_".length()) : userId)
                                                        .build());

    };


    public List<IamMultiLangErrorCodeIVo> selectWfsErrMultiLangErrorInfo(){

        return iamQueryMapper.selectMultiLangErrorInfo(IamMultiLangErrorCodeIVo.builder()
                .cpnntTypCd("MESSAGE")
                .solCd("ALL")
                .build());

    };

}
