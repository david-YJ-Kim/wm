package com.abs.wfs.workman.service.common.message;

import com.abs.wfs.workman.config.ApPropertyObject;
import com.abs.wfs.workman.config.ApSharedVariable;
import com.abs.wfs.workman.interfaces.solace.InterfaceSolacePub;
import com.solacesystems.jcsmp.JCSMPException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageSendService {

    public void sendMessageSend(String targetSystem, String eventName, String payload) throws JCSMPException {

        log.info("Message send to System: {}, EventName: {}, Payload: {}", targetSystem, eventName, payload);
        this.sendMessageSend(targetSystem, eventName, payload,
                            ApPropertyObject.getInstance().getSequenceManager().getTargetName(targetSystem, eventName, payload));
    }

    public void sendMessageSend(String targetSystem, String eventName, String payload, String topicName) throws JCSMPException {

        log.info("Message send to System: {}, EventName: {}, Payload: {}, topicName: {}", targetSystem, eventName, payload, topicName);
        InterfaceSolacePub.getInstance().sendTopicMessage(eventName, payload, topicName);
    }
}
