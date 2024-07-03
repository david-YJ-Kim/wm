package com.abs.wfs.workman.dao.query.model;

import com.abs.wfs.workman.util.code.UseYn;
import lombok.Builder;
import lombok.Data;

@Data
public class IamUserInfoIVo {

    String siteId;
    String userId;
    Long userIndex;
    String userNm;
    String userEmail;
    String userPhoneNo;
    Long userSttus;
    UseYn deleteYn;


    @Builder
    public IamUserInfoIVo(String siteId, String userId, Long userIndex, String userNm, String userEmail, String userPhoneNo, Long userSttus, UseYn deleteYn) {
        this.siteId = siteId;
        this.userId = userId;
        this.userIndex = userIndex;
        this.userNm = userNm;
        this.userEmail = userEmail;
        this.userPhoneNo = userPhoneNo;
        this.deleteYn = deleteYn;
    }
}
