package com.abs.wfs.workman.config;


import com.abs.wfs.workman.dao.query.common.vo.StateRuleInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Shared Variables
 * 공통으로 사용하는 데이터, 기초 · 기준 데이터 성격
 */

@Component
public class ApSharedVariable {

    Environment env;

    @Getter
    private static ApSharedVariable instance;

    // abnormal code


    // scenario typ
    @Getter
    ConcurrentHashMap<String, String> eqpIdToScenarioTypMap;

    // 다국어 처리 Map
    @Getter
    ConcurrentHashMap<String, HashMap<String, String>> multiLangCodeMap;

    @Getter
    @Setter
    ArrayList<StateRuleInfo> stateRuleInfos;

    public ApSharedVariable(Environment env) {
        this.env = env;
        instance = this;
    }


    public static ApSharedVariable createInstance(Environment env){
        if(instance == null){
            synchronized (ApSharedVariable.class){
                if(instance == null){
                    instance = new ApSharedVariable(env);
                }
            }
        }

        if(instance.eqpIdToScenarioTypMap == null){
            instance.eqpIdToScenarioTypMap = new ConcurrentHashMap<>();
        }

        if(instance.multiLangCodeMap == null){
            instance.multiLangCodeMap = new ConcurrentHashMap<>();

        }


        if(instance.stateRuleInfos == null){
            instance.stateRuleInfos = new ArrayList<>();
        }

        return instance;
    }

}
