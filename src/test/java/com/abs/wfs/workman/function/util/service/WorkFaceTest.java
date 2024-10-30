package com.abs.wfs.workman.function.util.service;

import com.abs.wfs.workman.service.common.work.WorkFaceGenerateManager;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;


@Data
class MtrlTestRequestVo{


    int id;
    String recipeType;
    String crntLoadingFace;
    String crntWorkFace;
    String nextRecipeType;
    boolean isToolHasFilpper;
    boolean isBothRecipe;
    boolean isBothFlipRecipe;

    String answerSeq1;
    String answerSeq2;

    String valueSeq1;
    String valueSeq2;

    boolean isSeq1Pass;

    boolean isSeq2Pass;
    boolean isPass;

    boolean isAbnormal = false;
    String errorComment;

    MtrlTestRequestVo(int id, boolean isBothRecipe, boolean isBothFlipRecipe,boolean isToolHasFilpper,
                      String crntLoadingFace, String crntWorkFace, String nextRecipeType,
                      String answerSeq1, String answerSeq2){
        this.id = id;
        this.crntLoadingFace = crntLoadingFace;
        this.crntWorkFace = crntWorkFace;
        this.nextRecipeType = nextRecipeType;
        this.isToolHasFilpper = isToolHasFilpper;
        this.isBothRecipe = isBothRecipe;
        this.isBothFlipRecipe = isBothFlipRecipe;
        if(isBothRecipe){
            this.recipeType = "BOTH";
        }else if(isBothFlipRecipe){
            this.recipeType = "BOTH_FLIP";
        }else if(!isBothRecipe && !isBothFlipRecipe){
            this.recipeType = "SIDE";
        }
        this.answerSeq1 = answerSeq1;
        this.answerSeq2 = answerSeq2;
    }

    public void setPass(){
        if(isSeq1Pass && isSeq2Pass){
            setPass(true);
        }
    }

    public void setValueSeq1(String valueSeq1) {
        this.valueSeq1 = valueSeq1;
        if(this.valueSeq1.equals(this.answerSeq1)){
            this.setSeq1Pass(true);
        }
    }


    public void setValueSeq2(String valueSeq2) {
        this.valueSeq2 = valueSeq2;
        if(this.valueSeq2.equals(this.answerSeq2)){
            this.setSeq2Pass(true);
        }
    }


    @Override
    public String toString() {
        return "MtrlTestRequestVo{" +
                "id='" + id + '\'' +
                ", recipeType='" + recipeType + '\'' +
                ", crntLoadingFace='" + crntLoadingFace + '\'' +
                ", crntWorkFace='" + crntWorkFace + '\'' +
                ", nextRecipeType='" + nextRecipeType + '\'' +
                ", isToolHasFilpper=" + isToolHasFilpper +
                ", isBothRecipe=" + isBothRecipe +
                ", isBothFlipRecipe=" + isBothFlipRecipe +
                ", answerSeq1='" + answerSeq1 + '\'' +
                ", answerSeq2='" + answerSeq2 + '\'' +
                ", valueSeq1='" + valueSeq1 + '\'' +
                ", valueSeq2='" + valueSeq2 + '\'' +
                ", isSeq1Pass=" + isSeq1Pass +
                ", isSeq2Pass=" + isSeq2Pass +
                ", isPass=" + isPass +
                ", isAbnormal=" + isAbnormal +
                ", errorComment='" + errorComment + '\'' +
                '}';
    }
}

@Slf4j
@SpringBootTest
public class WorkFaceTest {

    static WorkFaceGenerateManager workFaceGenerateManager = new WorkFaceGenerateManager();

    @Test
    public void testManager(){

        String a = workFaceGenerateManager.callMethod();
        System.out.println(a);
    }

    @Test
    @DisplayName("작업면 생성 테스트")
    public void testGenerateMtrlFace() throws Exception {

        ArrayList<MtrlTestRequestVo> list = new ArrayList<>();
        list.add(new MtrlTestRequestVo(1,false,false,true,"T","B","Bottom","TBB","-"));
        list.add(new MtrlTestRequestVo(2,false,false,true,"B","T","Bottom","BTB","-"));
        list.add(new MtrlTestRequestVo(3,false,false,false,"T","B","Bottom","-","-"));
        list.add(new MtrlTestRequestVo(4,false,false,false,"B","T","Bottom","-","-"));
        list.add(new MtrlTestRequestVo(5,false,false,false,"B","B","Bottom","BBB","-"));
        list.add(new MtrlTestRequestVo(6,false,false,false,"T","T","Bottom","TTT","-"));
        list.add(new MtrlTestRequestVo(7,true,false,true,"T","B","Bottom","TTB","-"));
        list.add(new MtrlTestRequestVo(8,true,false,true,"B","T","Bottom","BBB","-"));
        list.add(new MtrlTestRequestVo(9,true,false,true,"B","B","Bottom","BBB","-"));
        list.add(new MtrlTestRequestVo(10,true,false,false,"T","B","Bottom","TTT","-"));
        list.add(new MtrlTestRequestVo(11,true,false,false,"B","T","Bottom","BBB","-"));
        list.add(new MtrlTestRequestVo(12,true,false,false,"B","B","Bottom","BBB","-"));
        list.add(new MtrlTestRequestVo(13,false,true,true,"T","F","Bottom","TTB","BBB"));
        list.add(new MtrlTestRequestVo(14,false,true,true,"B","F","Bottom","BTB","BBB"));
        list.add(new MtrlTestRequestVo(15,false,false,true,"T","B","Top","TBT","-"));
        list.add(new MtrlTestRequestVo(16,false,false,true,"B","T","Top","BTT","-"));
        list.add(new MtrlTestRequestVo(17,false,false,false,"T","B","Top","-","-"));
        list.add(new MtrlTestRequestVo(18,false,false,false,"B","T","Top","-","-"));
        list.add(new MtrlTestRequestVo(19,false,false,false,"B","B","Top","BBB","-"));
        list.add(new MtrlTestRequestVo(20,false,false,false,"T","T","Top","TTT","-"));
        list.add(new MtrlTestRequestVo(21,true,false,true,"T","B","Top","TTT","-"));
        list.add(new MtrlTestRequestVo(22,true,false,true,"B","T","Top","BBT","-"));
        list.add(new MtrlTestRequestVo(23,true,false,true,"T","T","Top","TTT","-"));
        list.add(new MtrlTestRequestVo(24,true,false,false,"T","B","Top","TTT","-"));
        list.add(new MtrlTestRequestVo(25,true,false,false,"B","T","Top","BBB","-"));
        list.add(new MtrlTestRequestVo(26,true,false,false,"T","T","Top","TTT","-"));
        list.add(new MtrlTestRequestVo(27,false,true,true,"T","F","Top","TBT","TTT"));
        list.add(new MtrlTestRequestVo(28,false,true,true,"B","F","Top","BBT","TTT"));
        list.add(new MtrlTestRequestVo(29,false,false,true,"T","B","Both","TBT","-"));
        list.add(new MtrlTestRequestVo(30,false,false,true,"B","T","Both","BTB","-"));
        list.add(new MtrlTestRequestVo(31,false,false,false,"T","B","Both","-","-"));
        list.add(new MtrlTestRequestVo(32,false,false,false,"B","T","Both","-","-"));
        list.add(new MtrlTestRequestVo(33,false,false,false,"B","B","Both","BBB","-"));
        list.add(new MtrlTestRequestVo(34,false,false,false,"T","T","Both","TTT","-"));
        list.add(new MtrlTestRequestVo(35,true,false,true,"T","B","Both","TTT","-"));
        list.add(new MtrlTestRequestVo(36,true,false,true,"B","T","Both","BBB","-"));
        list.add(new MtrlTestRequestVo(37,true,false,true,"B","B","Both","BBB","-"));
        list.add(new MtrlTestRequestVo(38,true,false,false,"T","B","Both","TTT","-"));
        list.add(new MtrlTestRequestVo(39,true,false,false,"B","T","Both","BBB","-"));
        list.add(new MtrlTestRequestVo(40,true,false,false,"B","B","Both","BBB","-"));
        list.add(new MtrlTestRequestVo(41,false,true,true,"T","F","Both","TBT","TTT"));
        list.add(new MtrlTestRequestVo(42,false,true,true,"B","F","Both","BTB","BBB"));
        list.add(new MtrlTestRequestVo(43,false,false,true,"T","B","Both_Flip","TBT","-"));
        list.add(new MtrlTestRequestVo(44,false,false,true,"B","T","Both_Flip","BTB","-"));
        list.add(new MtrlTestRequestVo(45,false,false,false,"B","B","Both_Flip","BBB","-"));
        list.add(new MtrlTestRequestVo(46,false,false,false,"T","T","Both_Flip","TTT","-"));
        list.add(new MtrlTestRequestVo(47,true,false,true,"T","B","Both_Flip","TTT","-"));
        list.add(new MtrlTestRequestVo(48,true,false,true,"B","T","Both_Flip","BBB","-"));
        list.add(new MtrlTestRequestVo(49,true,false,true,"T","T","Both_Flip","TTT","-"));
        list.add(new MtrlTestRequestVo(50,true,false,false,"T","B","Both_Flip","TTT","-"));
        list.add(new MtrlTestRequestVo(51,true,false,false,"B","T","Both_Flip","BBB","-"));
        list.add(new MtrlTestRequestVo(52,true,false,false,"T","T","Both_Flip","TTT","-"));
        list.add(new MtrlTestRequestVo(53,false,true,true,"T","F","Both_Flip","TBT","TTT"));
        list.add(new MtrlTestRequestVo(54,false,true,true,"B","F","Both_Flip","BTB","BBB"));


        for(MtrlTestRequestVo requestVo : list){
            try{

                String[] values = workFaceGenerateManager.generateMtrlFace(requestVo.getCrntLoadingFace(), requestVo.getCrntWorkFace(), requestVo.getNextRecipeType(),
                        requestVo.isToolHasFilpper, requestVo.isBothRecipe, requestVo.isBothFlipRecipe);
                System.out.println(Arrays.toString(values));
                requestVo.setValueSeq1(values[0]);

                if(values.length > 1){
                    requestVo.setValueSeq2(values[1]);
                }else{
                    requestVo.setValueSeq2("-");
                }

                requestVo.setPass();
            }catch (Exception e){
                requestVo.setAbnormal(true);
                requestVo.setErrorComment(e.getMessage());
                requestVo.setValueSeq1("-");
                requestVo.setValueSeq2("-");
            }
        }


        for(MtrlTestRequestVo requestVo : list){
            System.out.println(requestVo);
        }

    }

}
