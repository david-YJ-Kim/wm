package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.eap.common.EqpStatusVo;
import com.abs.wfs.workman.spec.in.eap.common.MaterialListVo;
import com.abs.wfs.workman.spec.in.MsgReasonVo;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsToolCondRepVo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_TOOL_COND_REP;

    WfsToolCondRepBody body;

    @Data
    public static class WfsToolCondRepBody extends ApMsgBody {
        String eqpId;
        MaterialListVo materialList;
        EqpStatusVo status;
        MsgReasonVo reason;
    }




}
