package com.abs.wfs.workman.interfaces.rest.common;


import com.abs.cmn.seq.util.SequenceManageUtil;
import com.abs.wfs.workman.config.ApPropertyObject;
import com.abs.wfs.workman.dao.domain.ppsProdDef.model.TnPpsProdDef;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.brs.WfsManualWorkStartIvo;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/util")
@RequiredArgsConstructor
@Slf4j
public class ApUtilController {

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

}
