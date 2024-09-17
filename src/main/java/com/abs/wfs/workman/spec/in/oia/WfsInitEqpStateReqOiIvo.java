package com.abs.wfs.workman.spec.in.oia;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsInitEqpStateReqOiIvo extends ApMsgCommonVo {
    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_INIT_EQP_STATE_REQ_OI;
    Body body;
    @Data
    public static class Body extends ApMsgBody {


    }
}