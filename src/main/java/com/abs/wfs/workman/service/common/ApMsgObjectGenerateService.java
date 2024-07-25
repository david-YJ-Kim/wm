package com.abs.wfs.workman.service.common;


import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.eap.WfsEfemControlStateReportIvo;
import com.abs.wfs.workman.spec.in.oia.WfsOiGenerateWorkReqIvo;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;

@Service
@Slf4j
public class ApMsgObjectGenerateService {


    public WfsOiGenerateWorkReqIvo messageObject(String tid, WfsOiGenerateWorkReqIvo.Body body) throws JsonProcessingException {


        WfsOiGenerateWorkReqIvo ivo = new WfsOiGenerateWorkReqIvo();

        if (body.getUserId() == null || body.getUserId().isEmpty()) {
            body.setUserId(ApSystemCodeConstant.WFS);
        }
        ivo.setHead(this.generateMessageHead(tid, WfsOiGenerateWorkReqIvo.cid, WfsOiGenerateWorkReqIvo.system, body.getEqpId()));
        ivo.setBody(body);

        return ivo;
    }

    // TODO Util로
    public ApMsgHead generateMessageHead(String tid, String cid, String targetSystem, String targetEqp){

        return this.generateMessageHead(tid, cid, ApSystemCodeConstant.WFS, targetSystem, targetEqp, null, null);
    }

    public ApMsgHead generateMessageHead (String tid, String cid, String sourceSystem, String targetSystem, String targetEqp, @Nullable String osrc, @Nullable String otgt){
        ApMsgHead apMsgHead = ApMsgHead.builder()
                .tid(tid)
                .cid(cid)
                .osrc(osrc == null ? "" : osrc)
                .otgt(otgt == null ? "" : otgt)
                .src(sourceSystem)
                .tgt(targetSystem)
                .build();

        if(!targetSystem.equals(ApSystemCodeConstant.MCS)){
            apMsgHead.setTgtEqp(targetEqp == null ? new ArrayList<>() : Collections.singletonList(targetEqp));
        }

        return apMsgHead;
    }

}
