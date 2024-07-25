package com.abs.wfs.workman.interfaces.rest.common;


import com.abs.cmn.seq.util.SequenceManageUtil;
import com.abs.wfs.workman.config.ApPropertyObject;
import com.abs.wfs.workman.dao.domain.ppsProdDef.model.TnPpsProdDef;
import com.abs.wfs.workman.service.common.UtilCommonService;
import com.abs.wfs.workman.service.common.vo.MeasureOutInfo;
import com.abs.wfs.workman.service.common.vo.MeasureOutPortCarrInfoReqVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.brs.WfsManualWorkStartIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/util")
@RequiredArgsConstructor
@Slf4j
public class ApUtilController {



    @Autowired
    UtilCommonService utilCommonService;

    @GetMapping("/call/sleep")
    public void threadSleep(@RequestParam(value = "ms", required = false) String ms) {
        log.info("threadSleep started");
        if(ms == null || ms.isEmpty()){
            log.info("Ms is null, default is 5s.");
            this.utilCommonService.threadSleep("5000");
        }else {
            this.utilCommonService.threadSleep(ms);

        }

    }

    @GetMapping("/healthCheck")
    public String healthCheck() {
        log.info("Health check started");
        return "Hello World";
    }


    @PostMapping("/seqlib/getTopicName")
    public String send(@RequestBody String payload,
                     @RequestParam(value = "system", required = true) String system,
                     @RequestParam(value = "cid", required = true) String cid) throws Exception {

        String topicName = ApPropertyObject.getInstance().getSequenceManager().getTargetName(
                system, cid, payload);


        return topicName;

    }



    @GetMapping("/get/trayloader/OutPortCarrInfo")
    public MeasureOutInfo send(@RequestParam(value = "siteId", required = false) String siteId,
                               @RequestParam(value = "lotId", required = true) String lotId,
                               @RequestParam(value = "portId", required = true) String portId,
                               @RequestParam(value = "carrId", required = true) String carrId,
                               @RequestParam(value = "prodMtrlId", required = true) String prodMtrlId) throws Exception {

        // TODO Utill 성 요청, ApFlowVo 생성 메소드 필요

        return this.utilCommonService.getMeasureOutPortCarrInfo(WorkManCommonUtil.initializeProcessVo("UITL_TRAY_LOADER_OUTPORT_INFO", "ABSCA-RANDOME", "UTIL", null),
                MeasureOutPortCarrInfoReqVo.builder()
                        .siteId(siteId.isEmpty() ? "SVM" : siteId)
                        .lotId(lotId)
                        .portId(portId)
                        .carrId(carrId)
                        .prodMtrlId(prodMtrlId)
                        .build());

    }

}
