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
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;

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



    @PostMapping("SEND_MSG_SAMPLE")
    public void send(@RequestBody String payload,
                     @RequestParam(value = "tgt", required = false) String tgt,
                     @RequestParam(value = "cid", required = false) String cid,
                     @RequestParam(value = "eqpId", required = false) String eqpId,
                     @RequestParam(value = "topic", required = false) String topic) throws Exception {


        String topicName = topic == null ? ApPropertyObject.getInstance().getSequenceManager().getTargetName(tgt, cid, payload)
                                        : topic;
        this.messageSendService.sendMessageSend(tgt, cid, payload, topicName);


    }



    @PostMapping(WorkManMessageList.WFS_MANUAL_WORK_START)
    public void send(@RequestBody WfsManualWorkStartIvo.WfsManualWorkStartBody body,
                     @RequestParam(value = "src", required = false) String src,
                     @RequestParam(value = "eqpId", required = false) String eqpId,
                     @RequestParam(value = "topic", required = false) String topic) throws Exception {

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        log.info("body: {}, src: {}, eqpId: {}, topic: {}", body.toString(), src, eqpId, topic);

        String toolCode = (eqpId == null || eqpId.isEmpty()) ? body.getEqpId() : eqpId;
        String tid = SequenceManageUtil.generateMessageID();

        WfsManualWorkStartIvo payload = new WfsManualWorkStartIvo();
        ApMsgHead head = this.apPayloadGenerateService.generateMessageHead(tid, WfsManualWorkStartIvo.cid, WfsManualWorkStartIvo.system, toolCode);
        payload.setHead(head);
        payload.setBody(body);
        String payloadString = objectMapper.writeValueAsString(payload);
        log.info(payloadString);

        String topicName = ApPropertyObject.getInstance().getSequenceManager().getTargetName(WfsManualWorkStartIvo.system, WfsManualWorkStartIvo.cid, payloadString);
        this.messageSendService.sendMessageSend(WfsManualWorkStartIvo.system, WfsManualWorkStartIvo.cid, payloadString, topicName);


    }






}