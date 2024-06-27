package com.abs.wfs.workman.config;

import com.abs.cmn.seq.SequenceManager;
import com.abs.wfs.workman.interfaces.solace.InterfaceSolacePub;
import com.abs.wfs.workman.interfaces.solace.InterfaceSolaceSub;
import com.abs.wfs.workman.util.FisCommonUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Getter
@Component
public class ApPropertyObject {


    Environment env;
    @Value("${ap.info.group}")
    private String groupName;
    @Value("${ap.info.site}")
    private String siteName;
    @Value("${ap.info.env}")
    private String envType;
    @Value("${ap.info.sequence}")
    private String processSeq;

    @Value("${ap.local.active.profile}")
    private String localActiveProfile;

    private String clientName;

    @Value("${ap.interface.destination.receive.queue}")
    private String receiveQueueName;
    @Value("${ap.interface.destination.receive.init}")
    private String receiveInitTopic;
    @Value("${ap.interface.destination.send.topic}")
    private String sendTopicName;


    @Value("${ap.shutdown.force.timeout.ms}")
    private int apShutdownForceTimeoutMs;

    @Value("${ap.shutdown.polling.interval.ms}")
    private int apShutdownPollingIntervalMs;

    @Value("${ap.worker.pool-size.core}")
    private int corePoolSize;  // 기본 실행 대기하는 Thread 수

    @Value("${ap.worker.pool-size.max}")
    private int maxPoolSize;  // 동시 동작하는 최대 Thread 수

    @Value("${ap.worker.capacity}")
    private int queueCapacity;  // MaxPoolSize 초과 요청 시, 최대 Queue 저장 수

    @Value("${ap.worker.name.prefix}")
    private String threadPrefixName; // 생성되는 Thread 접두사 명

    @Value("${ap.seq.rule.path}")
    private String seqRulePath; // 시퀀스 룰 파일 경로

    @Value("${ap.seq.rule.name}")
    private String seqRuleName; // 시퀀스 룰 파일 이름


    private InterfaceSolaceSub interfaceSolaceSub;

    private InterfaceSolacePub interfaceSolacePub;

    private SequenceManager sequenceManager;



    private static ApPropertyObject instance;

    // Public method to get the Singleton instance
    public static ApPropertyObject createInstance(Environment env) {
        if (instance == null) {
            synchronized (ApPropertyObject.class) {
                // Double-check to ensure only one instance is created
                if (instance == null) {
                    instance = new ApPropertyObject(env);
                }
            }
        }

        if(instance.clientName == null){
            instance.clientName = FisCommonUtil.generateClientName(instance.groupName, instance.siteName, instance.envType, instance.processSeq);
        }

        if(instance.sequenceManager == null){
            try {
                instance.sequenceManager = new SequenceManager(instance.groupName, instance.siteName, instance.envType,
                        instance.seqRulePath, instance.seqRuleName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        return instance;
    }

    public static ApPropertyObject getInstance(){
        return instance;
    }
    public ApPropertyObject(Environment env) {
        this.env = env;
        instance = this;
    }

    public void setInterfaceSolaceSub(InterfaceSolaceSub interfaceSolaceSub) {
        this.interfaceSolaceSub = interfaceSolaceSub;
    }

    public void setInterfaceSolacePub(InterfaceSolacePub interfaceSolacePub) {
        this.interfaceSolacePub = interfaceSolacePub;
    }

    public void setSequenceManager(SequenceManager sequenceManager) {
        this.sequenceManager = sequenceManager;
    }



}
