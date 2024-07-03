package com.abs.wfs.workman.dao.query.mapper;

import com.abs.wfs.workman.dao.query.model.IamMultiLangErrorCodeIVo;
import com.abs.wfs.workman.dao.query.model.IamUserInfoIVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface IamQueryMapper {

    List<IamUserInfoIVo> selectUserInfo(IamUserInfoIVo iamUserInfoIVO);


    /**
     * 다국어 처리 에러 코드 조회
     * @param iamMultiLangErrorCodeIVo
     * @return
     */
    List<IamMultiLangErrorCodeIVo> selectMultiLangErrorInfo(IamMultiLangErrorCodeIVo  iamMultiLangErrorCodeIVo);


}
