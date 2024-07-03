package com.abs.wfs.workman.spec.common;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ApMsgHead {

    String cid;
    String tid;
    String osrc;
    String otgt;
    String src;
    String lang;
    String srcEqp;
    String tgt;
    List<String> tgtEqp;

    @Builder
    public ApMsgHead(String cid, String tid, String osrc, String otgt, String src, String srcEqp, String tgt, String lang, List<String> tgtEqp) {
        this.cid = cid;
        this.tid = tid;
        this.osrc = osrc;
        this.otgt = otgt;
        this.lang = lang;
        this.src = src;
        this.srcEqp = srcEqp;
        this.tgt = tgt;
        this.tgtEqp = tgtEqp;
    }
}
