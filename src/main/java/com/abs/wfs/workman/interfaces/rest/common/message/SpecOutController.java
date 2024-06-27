package com.abs.wfs.workman.interfaces.rest.common.message;


import com.abs.cmn.seq.util.SequenceManageUtil;
import com.abs.wfs.workman.config.ApPropertyObject;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.brs.WfsManualWorkStartIvo;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/common/message/")
@RequiredArgsConstructor
public class SpecOutController {


    private static ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Autowired
    MessageSendService messageSendService;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;


    @PostMapping(WorkManMessageList.WFS_MANUAL_WORK_START)
    public void send(@RequestBody WfsManualWorkStartIvo.WfsManualWorkStartBody body,
                                                 @RequestParam(value = "src", required = false) String src,
                                                 @RequestParam(value = "eqpId", required = false) String eqpId,
                                                 @RequestParam(value = "topic", required = false) String topic) throws Exception {


        String toolCode = (eqpId == null || eqpId.isEmpty()) ? body.getEqpId() : eqpId;
        String sourceSystem = (eqpId == null || eqpId.isEmpty()) ? ApSystemCodeConstant.WFS : src;
        String tid = SequenceManageUtil.generateMessageID();

        WfsManualWorkStartIvo payload = new WfsManualWorkStartIvo();
        ApMsgHead head = this.apPayloadGenerateService.generateMessageHead(tid, sourceSystem, WfsManualWorkStartIvo.system, toolCode);
        payload.setHead(head);
        payload.setBody(body);
        String payloadString = objectMapper.writeValueAsString(payload);

//        String topicName = (eqpId == null || eqpId.isEmpty()) ? topic : ApPropertyObject.getInstance().getSequenceManager().getTargetName(WfsManualWorkStartIvo.system, WfsManualWorkStartIvo.cid, payloadString);
        String topicName = ApPropertyObject.getInstance().getSequenceManager().getTargetName(WfsManualWorkStartIvo.system, WfsManualWorkStartIvo.cid, payloadString);
        this.messageSendService.sendMessageSend(WfsManualWorkStartIvo.system, WfsManualWorkStartIvo.cid, payloadString, topicName);


    }



}