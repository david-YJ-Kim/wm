package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsEqpPanelExportIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_EQP_PANEL_EXPORT;
    Body body;

    @Data
    public static class Body extends ApMsgBody {

        String siteId;
        String eqpId;
        String subEqpId;
        String ppId;
        String portId;
        String carrId;
        String lotId;
        String slotNo;
        String prodMtrlId;
        String mtrlFace;
        String userId;
    }
}
