package com.abs.wfs.workman.spec.in.rms;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class WfsRecipeValidateRepVo extends ApMsgCommonVo {

    WfsDspWorkRepBody body;

    @Data
    public static class WfsDspWorkRepBody extends ApMsgBody {
        String dspType;
        String lotId;
        String eqpId;
        String prodDefId;
        String procDefId;
        String procSgmtId;
        RecipeList recipeList;
        String resultCode;
        String resultMessage;
    }
}
