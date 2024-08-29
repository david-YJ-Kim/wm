package com.abs.wfs.workman.spec.in.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsNpgProcReqIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_NPG_PROC_REQ;

    Body body;
    @Data
    public static class Body extends ApMsgBody {
        String npgId;
        String npgTyp;
        String condProcTyp;
        String condQty;
        String monQty;
    }

}
