package com.abs.wfs.workman.util;

import com.abs.wfs.workman.config.ApSharedVariable;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.util.code.RecipeTypeCode;
import com.abs.wfs.workman.util.code.SelfInspectionCd;
import com.abs.wfs.workman.util.code.SuccessYn;
import com.abs.wfs.workman.util.code.WorkManScenarioList;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class WorkManCommonUtil {


    /**
     * EQP ID로 설정된 시나리오 타입 획득
     * @param eqpId
     * @return
     */
    public static String getScenarioType(String eqpId){

        // find scenario Type with eqpId
        return WorkManScenarioList.BP_SINGLE;
    }


    /**
     * 설비 작업면 정보를 바탕으로 전산 작업면으로 변환
     * ex) TBT → Bottom   ||   BTB → Top
     * @param mtrlFaceCd
     * @return
     */
    public static String convertEqpWorkFaceIntoMesWorkFace(String mtrlFaceCd){


        if(isEnumValue(RecipeTypeCode.class, mtrlFaceCd)) return mtrlFaceCd;

        String workFace = String.valueOf(mtrlFaceCd.charAt(0));

        switch (workFace){
            case "T":
                return RecipeTypeCode.Top.name();
            case "B":
                return RecipeTypeCode.Bottom.name();
            default:
                log.error("mtrlFaceCd is undefined. It's going to be Top. mtrlFaceCd: {}", mtrlFaceCd);
                return RecipeTypeCode.Top.name();
        }


    }

    /**
     * String이 Enum class에 속하는지 확인
     * @param enumClass
     * @param value
     * @return
     * @param <T>
     */
    public static <T extends Enum<T>> boolean isEnumValue(Class<T> enumClass, String value) {
        try {
            Enum.valueOf(enumClass, value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * 자주검사 결과에 따른 MES 정보 전환
     * @param selfInspResultCode
     * @return
     */
    public static String convertEqpInspectionCodeIntoMesCode(String selfInspResultCode){

        switch (selfInspResultCode){
            case "0":
                return SelfInspectionCd.OK;
            case "1":
                return SelfInspectionCd.NG;
            case "2":
                return SelfInspectionCd.ONE_MORE;
            default:
                log.error("self inspection code is undefined. It's going to be NG. code: {}", selfInspResultCode);
                return SelfInspectionCd.NG;
        }

    }


    /**
     * ApFlowProcess VO 마지막 처리
     * @param apFlowProcessVo
     * @return
     */
    public static ApFlowProcessVo completeFlowProcessVo(ApFlowProcessVo apFlowProcessVo){
        apFlowProcessVo.setExecuteEndTime(System.currentTimeMillis());
        return apFlowProcessVo;
    }


    /**
     * ApFlowProcess VO 실패 처리
     * @param apFlowProcessVo
     * @return
     */
    public static ApFlowProcessVo setFailFlowProcessVo(ApFlowProcessVo apFlowProcessVo){
        apFlowProcessVo.setExecuteEndTime(System.currentTimeMillis());
        apFlowProcessVo.setSuccessYn(SuccessYn.N);
        return apFlowProcessVo;
    }


    /**
     * Exception 메시지 리턴
     * @param code  -> 검색에 사용하는 에러 키
     * @param lang -> 다국어 처리 언어
     * @param args -> 인자들
     * @return
     */
    public static String generateMultiLangExceptionMessage(String code, String lang, Object[] args){

        log.info("Multi lang exception request. code: {}, lang: {}", code, lang);
        ConcurrentHashMap<String, HashMap<String, String>>  codeMap =  ApSharedVariable.getInstance().getMultiLangCodeMap();

        String defaultMessage = "ERROR occur while system working";
        String defaultLang = "ko";


        if(codeMap.containsKey(code)){

            String format;

            try{
                if(codeMap.get(code).get(lang) != null){
                    format = codeMap.get(code).get(lang);
                }else {
                    format = codeMap.get(code).get(defaultLang);
                }
            }catch (Exception e){

                log.warn("Lang is not register in Data. code : {} and lang: {}", code, lang);
                return defaultMessage;
            }

            return WorkManCommonUtil.parseCodeWithArgs(format, args);

        }else{

            log.warn("Code is not register in Data. code : {}", code);
            return defaultMessage;
        }

    }


    /**
     * DB에 등록된 Code 로 다국어 처리 만들기 → 로딩 시, DB 조회 하여 메모리 적재.
     * @param format
     * @param args
     * @return
     */
    private static String parseCodeWithArgs(String format, @Nullable  Object[] args){
        if(args == null) return format;

        for(int i = 0; i < args.length; i++){
            format = format.replace("{" + i + "}", (String) args[i]);

        }
        return format;

    }


    /**
     *
     * @param tasks
     */
    public static void executeAsyncTasks(List<Runnable> tasks) {
        ExecutorService executorService = Executors.newFixedThreadPool(tasks.size());
        CompletableFuture.allOf(tasks.stream()
                .map(task -> CompletableFuture.runAsync(task, executorService))
                .toArray(CompletableFuture[]::new)).join();
        executorService.shutdown();

    }


    /**
     * 포트 아이디로, Port Type 획득하기
     * 포트 아이디 (AP-RD-03-01-BP01)
     * 결과 : BP
     *
     * @param portId : BP / IP / OP
     * @return
     */
    public static String extractPortTypWithPortId(String portId){

        int lastHyphenIndex = portId.lastIndexOf('-');
        String portPart = portId.substring(lastHyphenIndex + 1);


        return portPart.replaceAll("\\d", "");

    }


    /**
     * 포트 아이디로, 설비 아이디 획득
     * 포트 아이디 (AM-RE-00-01-BP01)
     * → AM-RE-00-01
     *
     * @param portId
     * @return
     */
    public static String extractEqpIdWithPortId(String portId){
        int lastHyphenIndex = portId.lastIndexOf('-');

        // Return the substring from the beginning to the last hyphen index
        if (lastHyphenIndex != -1) {
            return portId.substring(0, lastHyphenIndex);
        } else {
            // If there is no hyphen, return the input string
            return portId;
        }
    }


    /**
     * 두 개 의 서로 다른 String 객체의 값이 같으면 true, 다르면 false
     * @param wordA
     * @param wordB
     * @return
     */
    public static boolean compareStringWords(String wordA, String wordB){

        if (wordA == null && wordB == null) {
            return true;
        }
        if (wordA == null || wordB == null) {
            return false;
        }
        return wordA.trim().equals(wordB.trim());

    }


    /**
     * ApFlow VO에 추가 정보 적재
     * @param vo
     * @param key
     * @param value
     * @return
     */
    public static void setAdditionalData(ApFlowProcessVo vo, String key, String value){

        HashMap<String, String> additionData;
        if(vo.getAdditionData() == null){
            additionData = new HashMap<>();

        }else{
            additionData = vo.getAdditionData();

        }
        additionData.put(key, value);
        vo.setAdditionData(additionData);

    }


    /**
     * 이벤트 처리에 초기화 메소드
     * @param cid
     * @param trackingKey
     * @param scenarioType
     * @param apMsgHead
     * @return
     */
    public static ApFlowProcessVo initializeProcessVo(String cid, String trackingKey, String scenarioType, @Nullable ApMsgHead apMsgHead){

        return ApFlowProcessVo.builder()
                .eventName(cid)
                .trackingKey(trackingKey)
                .scenarioType(scenarioType)
                .executeStartTime(System.currentTimeMillis())
                .tid((apMsgHead == null) ? trackingKey : apMsgHead.getTid())
                .lang((apMsgHead == null) ? "en" : apMsgHead.getLang())
                .build();
    }


    /**
     * Null 확인하는 메소드,
     * null, empty가 아니면 true
     * @param word
     * @return
     */
    public static boolean nullPointCheck(String word){
        if(word != null && !word.isEmpty()){
            return true;
        }
        return false;
    }


    /**
     * 랜덤 키 생성 메소드
     * @return
     */
    public static String generateRandomKey(){


        return (CommonDate.getCurrentDateTimeToString().substring(6) + UUID.randomUUID().toString()).replaceAll("-", "");

    }


}
