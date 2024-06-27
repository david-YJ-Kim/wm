package com.abs.wfs.workman.spec.common;

import com.abs.wfs.workman.util.code.UseYn;
import lombok.Builder;
import lombok.Data;

@Data
public class ApMessageResultVo {

    String cid;
    String messageKey;
    String destinationName;
    UseYn ExecuteSuccessYn;
    long elapsedMilliSecond;


    @Builder
    public ApMessageResultVo(String cid, String messageKey, String destinationName, UseYn executeSuccessYn, long elapsedMilliSecond) {
        this.cid = cid;
        this.messageKey = messageKey;
        this.destinationName = destinationName;
        ExecuteSuccessYn = executeSuccessYn;
        this.elapsedMilliSecond = elapsedMilliSecond;
    }
}
