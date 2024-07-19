package com.abs.wfs.workman.service.common.work;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@NoArgsConstructor
@Slf4j
public class WorkFaceGenerateManager {


    public String callMethod(){
        return "a";
    }


    /**
     *
     * @param crntLoadingFace: 	    CST 적재된 패널면 (현 설비 투입면)
     * @param crntWorkFace: 	    현 공정 · 설비 작업면 (T: 상면 / B: 하면 / F: 양면)
     * @param nextRecipeType: 	    다음 공정 작업면 레시피 (Top: 상면 / Bottom: 하면 / Both: Both 레시피 / Both_Flip: 양면)
     * @param isToolHasFilpper:		현 설비 Flipper 존재 여부 (true: 존재 / false: 미 존재)
     * @param isBothRecipe:		    BOTH 레시피 여부         (true: Both 레시피 / false: 양면 or 단면)
     * @param isBothFlipRecipe:	    BOTH FLIP 레시피 여부    (true: 양면 / false: 단면)
     * @return
     * @throws Exception
     */
    public String[] generateMtrlFace(String crntLoadingFace, String crntWorkFace, String nextRecipeType,
                                     boolean isToolHasFilpper, boolean isBothRecipe, boolean isBothFlipRecipe)
            throws Exception {
        String nextRecipeTypeUpper = nextRecipeType.toUpperCase();
        /*
            Top         →       T
            Bottom      →       B
            Both        →       작업면
            BothFlip    →       배출면
         */


        String startFace = crntLoadingFace.startsWith("T") || crntLoadingFace.toUpperCase().startsWith("TOP") ? "T" : "B";
        String workFace = crntWorkFace.startsWith("T") || crntWorkFace.toUpperCase().startsWith("TOP") ? "T" : "B";
        String nextWorkFace = nextRecipeTypeUpper.startsWith("T") || nextRecipeTypeUpper.toUpperCase().startsWith("TOP") ?
                "T" : (nextRecipeTypeUpper.toUpperCase().startsWith("BOTTOM") ? "B" : null);
        String endFace = null;
        String mtrlFacePattern = "%s%s%s";

        ArrayList<String> mtrlFaceList = new ArrayList<>();


        // 양면 (Both) 작업인 경우
        if(isBothRecipe) {
            log.info("Both recipe Yn : {}", isBothRecipe);
            // Both 레시피 작업 → 작업면과 상관없이 투입면으로 작업 진행
            workFace = startFace;

            // Both 레시피 작업
            // Flipper 있다면, 다음공정 투입면으로 배출
            // 투입면과 다음 공정 작업면 비교 → 같으면: 시작면으로 배출 (Both/Both_Flip 포함) / 다르면: (hasFlipper) ? 반전 배출 : 시작면 배출

            // 다음 공정: Both || Both_Flip → 투입면으로 배출 (조건: 투입면으로 작업 startWork ≒ workFace)
            if(nextWorkFace == null){

                endFace = startFace;

                // 다음 공정: T(Top) or B(Bottom)
            }else{
                // 투입면과 다음 공정 작업면이 같은 경우
                if(startFace.equals(nextWorkFace)){
                    endFace = startFace;

                    // 투입면과 다음 공정 작업면이 다른 경우
                }else {
                    // Flipper 有 → 시작면으로 배출 (조건: 투입면으로 작업 startWork ≒ workFace)
                    // Flipper 無 → 반전하여 배출
                    endFace = (isToolHasFilpper) ? nextWorkFace : startFace;
                }
            }

//            if(isToolHasFilpper) {
//                // 다음 공정, Both || Both_Flip → nextWorkFace is null → 작업면 배출 (Flip 최소)
//                endFace = (nextWorkFace == null) ? workFace : nextWorkFace;
//            }
//            // Flipper 없다면, 작업면으로 배출
//            else {
//                endFace = startFace;
//            }
            addMtrlFaceAtArray(mtrlFaceList, mtrlFacePattern, startFace, workFace,endFace);


            // 단면 작업 인 경우
        }else if(!isBothFlipRecipe){
            log.info("No both recipe Yn : {} and One side recipe Yn: {}", isBothRecipe, isBothFlipRecipe);

            // Filpper 부재, 투입면과 작업면이 다른 경우 → 작업 진행 불가 → Abnormal 케이스
            if(!isToolHasFilpper && !startFace.equals(workFace)){
                log.error("Start face and work face is not matched. No permit to start ");
                // TODO Error Code 식별 필요
                throw new Exception("No Flipper and Work Face is not matched");
            }

            if(!(nextWorkFace == null)){
                // Flipper 존재 → 다음 공정면 배출
                endFace = (isToolHasFilpper) ? nextWorkFace : workFace;
            }
            // 다음 공정, Both || Both_Flip → 작업면 배출
            else{
                endFace = startFace;
            }
            addMtrlFaceAtArray(mtrlFaceList, mtrlFacePattern, startFace, workFace,endFace);


            // 양면 작업 인 경우 → Seq1에서 최대한 반전하여, Seq2에서 반전 없이 작업 진행
        }else if(isBothFlipRecipe){

            // 다음 공정 Both || Both_Flip → 투입면, 배출
            if(nextWorkFace == null) nextWorkFace = crntLoadingFace;

            // 투입면과 배출면이 같으면  → seq1 다른면 작업 후 배출면으로 담는다.
            // 투입면과 배출면이 다르면  → seq1 투입면으로 작업 후 반전 진행 / seq2 배출면 작업
            // ** 첫번째 작업 후 배출면으로 담는다.
            for(int i=1; i < 3; i++){

                // 반전하여 작업 → 반전하여 배출면 맞춤
                if(i == 1){
                    // 투입면과 배출면이 같으면 → 반전 작업 먼저 진행 (배출면 기준 반대면)
                    if(crntLoadingFace.equals(nextWorkFace)){
                        workFace = (nextWorkFace.equals("T")) ? "B" : "T";
                    }
                    // 투입면과 배출면이 다르면 → 투입면 먼저 작업 진행
                    else {
                        workFace = startFace;
                    }
                    endFace = nextWorkFace;
                    addMtrlFaceAtArray(mtrlFaceList, mtrlFacePattern, startFace, workFace,endFace);
                }

                // (seq 1에서 맞춤) 배출면 작업 후 배출, 반전 작업 없음
                if(i == 2){
                    startFace = nextWorkFace;
                    workFace = nextWorkFace;
                    endFace = nextWorkFace;
                    addMtrlFaceAtArray(mtrlFaceList, mtrlFacePattern, startFace, workFace,endFace);
                }

            }
        }else{

            log.error("Recipe Type is not defined");
            throw new Exception("Recipe type is not defined. Current type: Both / Both Flip / Side");
        }



        return mtrlFaceList.toArray(new String[mtrlFaceList.size()]);

    }




    private void addMtrlFaceAtArray(ArrayList list, String mtrlFacePattern, String startFace, String workFace, String endFace){
        list.add(String.format(
                mtrlFacePattern, startFace, workFace, endFace
        ));
    }

}
