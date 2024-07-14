package com.abs.wfs.workman.activator;

import com.abs.wfs.workman.config.ApPropertyObject;
import com.abs.wfs.workman.config.GracefulShutdownTomcatConnector;
import com.abs.wfs.workman.util.ApMessagePool;
import com.abs.wfs.workman.util.ApplicationContextProvider;
import com.solacesystems.jcsmp.JCSMPInterruptedException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class WorkManStoppedActivator implements ApplicationListener<ContextClosedEvent> {

    @Getter
    @Value("${gracefulShutdown.awaitTerminateTime:180}")
    private String awaitTerminateTime;

    private final GracefulShutdownTomcatConnector gracefulShutdownTomcatConnector;

    public WorkManStoppedActivator(GracefulShutdownTomcatConnector gracefulShutdownTomcatConnector) {
        this.gracefulShutdownTomcatConnector = gracefulShutdownTomcatConnector;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        log.info("Start onApplicationEvent");
        log.info("Start GracefulShutdownEventListener");

        long defaultAwait = 180;
        Long awaitTime = null;
        String strAwaitTime = this.getAwaitTerminateTime();

        if(NumberUtils.isNumber(strAwaitTime)){
            awaitTime = Long.valueOf(strAwaitTime);
        } else
        {
            // default 값
            awaitTime = defaultAwait;
        }

        gracefulShutdownTomcatConnector.getConnector().pause();

        log.info("@@@@@@@@@@@@@@@@@@@ Web Application Gracefully Shutdown event receive");

        // 아래 소스는  Aysnc로 진행 할 때의 소스로 보인듯...
//        ThreadPoolTaskExecutor threadPoolTaskExecutor = (ThreadPoolTaskExecutor) ApplicationContextProvider.getBean("threadPoolTaskExecutor");
//        for (int i = 1 ; i <= awaitTime ; i++) {
//            int nActiveCount = threadPoolTaskExecutor.getActiveCount();
//            if(nActiveCount != 0) {
//                try {
//                    log.info("@@@@@@@@@@@@@@@@@@@ thread kill wait : " + i + " ActiveCount : " + nActiveCount);
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        }

        log.info("@@@@@@@@@@@@@@@@@@@ Web Application Gracefully Shutdown start.");

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) gracefulShutdownTomcatConnector.getConnector()
                .getProtocolHandler()
                .getExecutor();

        threadPoolExecutor.shutdown();

        try {
            log.info("await Terminate Time: " + String.valueOf(awaitTime) + " Seconds.");
            threadPoolExecutor.awaitTermination(awaitTime, TimeUnit.SECONDS);

            log.info("Web Application Gracefully Stopped.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();

            log.error("Web Application Graceful Shutdown Failed.");
        }
    }


    // 이전 소스, 메시지 풀로 서렁하는
//    @Override
//    public void onApplicationEvent(ContextClosedEvent event) {
//        log.warn("JVM will be stop in sec");
//
//        boolean stopReceiving;
//        try {
//            stopReceiving = ApPropertyObject.getInstance().getInterfaceSolaceSub().stopQueueReceiver();
//        } catch (JCSMPInterruptedException e) {
//            e.printStackTrace();
//            stopReceiving = false;
////            throw new RuntimeException(e);
//        }
//        log.info("Is Flow Receiver closed ? :{}", stopReceiving);
//
//
//
//
//        int cnt = 0;
//        int maxCount = ApPropertyObject.getInstance().getApShutdownForceTimeoutMs() / ApPropertyObject.getInstance().getApShutdownPollingIntervalMs();
//        while (true){
//            int remainedMessageSizeInStore = ApMessagePool.getMessageManageMap().size();
//
//
//            log.debug(
//                    String.valueOf(remainedMessageSizeInStore)
//            );
//            cnt++;
//
//            if(remainedMessageSizeInStore == 0){
//                log.info("All message has been cleared.! size: {}", remainedMessageSizeInStore);
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//
//                log.warn("World is Shutdown.!!");
//                break;
//            }
//
//
//            if(cnt > maxCount){
//                log.info("Shutdown timeout. meet the limit.!");
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//
//
//                log.warn("World is Shutdown.!!");
//
//
//
//                break;
//            }
//
//            try {
//                Thread.sleep(ApPropertyObject.getInstance().getApShutdownPollingIntervalMs());
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//
//
//
//    }
}
