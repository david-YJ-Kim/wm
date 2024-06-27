package com.abs.wfs.workman.spec.common;

import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Builder;
import lombok.Data;

@Data
public class ApMsgBody {

    private String siteId;
    private String lotId;
    private String eqpId;
    private String portId;
    private String carrId;
    private String userId;


}
