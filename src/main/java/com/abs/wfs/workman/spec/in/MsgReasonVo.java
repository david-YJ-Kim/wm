package com.abs.wfs.workman.spec.in;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MsgReasonVo {

    private String reasonCode;

    private String reasonComment;
}
