package com.abs.wfs.workman.dao.query.service.vo;

import lombok.Data;

@Data
public class UpdatePortInfoRequestIvo extends ServiceBaseRequestVo{

    String statCd;
    String trsfStatCd;
    String eqpId;
    String portId;
}
/*
{
  "siteId": "SVM",
  "cid": "WFS_UNLOAD_COMP",
  "tid": "TID000123",
  "userId": "WFS",
  "useStatCd": "Usable",
  "statCd": "Auto",
  "trsfStatCd": "OutOfService",
  "eqpId": "AP-TG-01-01",
  "portId": "AP-TG-01-01-IP01"
}
 */