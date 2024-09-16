package com.abs.wfs.workman.spec.in.oia;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsWorkProgressOiIvo extends ApMsgCommonVo {
    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_WORK_PROGRESS_OI;
    Body body;
    @Data
    public static class Body extends ApMsgBody {
        String prodDefId;
        String procDefId;
        String procSgmtId;
    }
}
