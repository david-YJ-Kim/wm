package com.abs.wfs.workman.spec.in.rtd;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class WfsDspWorkRepVo extends ApMsgCommonVo {

    WfsDspWorkRepBody body;

    @Data
    public static class WfsDspWorkRepBody extends ApMsgBody {
        String dspType;
        String lotId;
        String eqpId;
        String portId;
        String carrId;
        String prodDefId;
        String procDefId;
        String rsltCm;
        String rsnCd;
        String evntCm;
        String evntUserId;
    }
}
