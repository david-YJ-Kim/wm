package com.abs.wfs.workman.spec.in.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class WfsLotTrackInCnfmRepVo extends ApMsgCommonVo {

    WfsLotTrackInCnfmRepBody body;

    @Data
    public static class WfsLotTrackInCnfmRepBody extends ApMsgBody {
        String lotId;
        String eqpId;
        String portId;
        String carrId;
        String selfInspYn;
        String selfInspPanelCnt;
        String selfInspMasterInfoObjId;
        String smplLotYn;
        String smplWorkTyp;
        String smplQty;
        String precedeYn;
        String resultCode;
        String resultComment;
    }


    public static String getMessageSampleFormat(String eppId, String portId, String portType){
        return "";
    }


}
