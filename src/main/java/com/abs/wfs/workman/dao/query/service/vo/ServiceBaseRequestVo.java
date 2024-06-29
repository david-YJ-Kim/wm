package com.abs.wfs.workman.dao.query.service.vo;

import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.Data;

@Data
public class ServiceBaseRequestVo {

    String siteId;
    String cid;
    String tid;
    String userId;
    UseStatCd useStatCd;
}