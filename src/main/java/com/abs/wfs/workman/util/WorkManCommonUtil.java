package com.abs.wfs.workman.util;

import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.util.code.RecipeTypeCode;
import com.abs.wfs.workman.util.code.SelfInspectionCd;
import com.abs.wfs.workman.util.code.WorkManScenarioList;
import lombok.extern.slf4j.Slf4j;

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
}
