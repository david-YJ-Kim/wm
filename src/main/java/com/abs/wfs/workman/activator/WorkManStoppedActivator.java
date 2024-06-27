package com.abs.wfs.workman.activator;

import com.abs.wfs.workman.config.ApPropertyObject;
import com.abs.wfs.workman.util.ApMessagePool;
import com.solacesystems.jcsmp.JCSMPInterruptedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WorkManStoppedActivator implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        log.warn("JVM will be stop in sec");

        boolean stopReceiving;
        try {
            stopReceiving = ApPropertyObject.getInstance().getInterfaceSolaceSub().stopQueueReceiver();
        } catch (JCSMPInterruptedException e) {
            e.printStackTrace();
            stopReceiving = false;
//            throw new RuntimeException(e);
        }
        log.info("Is Flow Receiver closed ? :{}", stopReceiving);




        int cnt = 0;
        int maxCount = ApPropertyObject.getInstance().getApShutdownForceTimeoutMs() / ApPropertyObject.getInstance().getApShutdownPollingIntervalMs();
        while (true){
            int remainedMessageSizeInStore = ApMessagePool.getMessageManageMap().size();


            log.debug(
                    String.valueOf(remainedMessageSizeInStore)
            );
            cnt++;

            if(remainedMessageSizeInStore == 0){
                log.info("All message has been cleared.! size: {}", remainedMessageSizeInStore);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                log.warn("World is Shutdown.!!");
                break;
            }


            if(cnt > maxCount){
                log.info("Shutdown timeout. meet the limit.!");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


                log.warn("World is Shutdown.!!");



                break;
            }

            try {
                Thread.sleep(ApPropertyObject.getInstance().getApShutdownPollingIntervalMs());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }




    }
}
