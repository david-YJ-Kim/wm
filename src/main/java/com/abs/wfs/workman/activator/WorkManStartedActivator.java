package com.abs.wfs.workman.activator;

import com.abs.cmn.seq.SequenceManager;
import com.abs.wfs.workman.config.ApPropertyObject;
import com.abs.wfs.workman.config.ApSharedVariable;
import com.abs.wfs.workman.config.SolaceSessionConfiguration;
import com.abs.wfs.workman.interfaces.solace.InterfaceSolacePub;
import com.abs.wfs.workman.interfaces.solace.InterfaceSolaceSub;
import com.abs.wfs.workman.service.common.ExceptionCodeHandleService;
import com.abs.wfs.workman.service.common.staterule.StateRuleManager;
import com.abs.wfs.workman.util.ApMessagePool;
import com.solacesystems.jcsmp.JCSMPException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class WorkManStartedActivator implements ApplicationRunner {

    @Autowired
    private Environment env;


    @Autowired
    ExceptionCodeHandleService exceptionCodeHandleService;

    @Autowired
    StateRuleManager stateRuleManager;

    @Override
    public void run(ApplicationArguments args){


        try {
            this.initializeSequenceManager();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        this.initializeSolaceResources();
        log.info("Complete initialize solace resources.");


        this.stateRuleManager.initializeResource();

        this.initializeSharedVariables();

//        this.exceptionCodeHandleService.loadMultiLandErrorCode();
        log.info("Complete loadMultiLandErrorCode.");


        ApMessagePool.getMessageManageMap();
        log.info("Initialize Message Pool. is null?: {}", ApMessagePool.getMessageManageMap() == null);


    }

    private void initializeSharedVariables(){

        // Shared Variable 초기화
        ApSharedVariable.createInstance(env);
    }


    private void initializeSequenceManager() throws IOException {
        
        // TODO 스프링 부트 테스트 시, JSON Object keySet 호출 실패로 진행 불가
//        SequenceManager sequenceManager = new SequenceManager(
//                ApPropertyObject.getInstance().getGroupName(),
//                ApPropertyObject.getInstance().getSiteName(),
//                ApPropertyObject.getInstance().getEnvType(),
//                ApPropertyObject.getInstance().getSeqRulePath(),
//                ApPropertyObject.getInstance().getSeqRuleName()
//        );

//        ApPropertyObject.getInstance().setSequenceManager(sequenceManager);
    }

    private void initializeSolaceResources(){

        SolaceSessionConfiguration sessionConfiguration = SolaceSessionConfiguration.createSessionConfiguration(env);

        try {
            InterfaceSolacePub interfaceSolacePub = InterfaceSolacePub.getInstance();
            interfaceSolacePub.init();
            ApPropertyObject.getInstance().setInterfaceSolacePub(interfaceSolacePub);

        } catch (JCSMPException e) {
            throw new RuntimeException(e);
        }

//        try {
//            InterfaceSolaceSub interfaceSolaceSub = new InterfaceSolaceSub();
//            interfaceSolaceSub.run();
//            ApPropertyObject.getInstance().setInterfaceSolaceSub(interfaceSolaceSub);
//
//        } catch (JCSMPException e) {
//            throw new RuntimeException(e);
//        }

    }
}
