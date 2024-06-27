package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.eap.common.EqpStatusVo;
import com.abs.wfs.workman.spec.in.eap.common.MaterialListVo;
import com.abs.wfs.workman.spec.in.MsgReasonVo;
import lombok.Data;

@Data
public class WfsToolCondRepVo extends ApMsgCommonVo {

    WfsToolCondRepBody body;

    @Data
    public static class WfsToolCondRepBody extends ApMsgBody {
        String eqpId;
        MaterialListVo materialList;
        EqpStatusVo status;
        MsgReasonVo reason;
    }




}
