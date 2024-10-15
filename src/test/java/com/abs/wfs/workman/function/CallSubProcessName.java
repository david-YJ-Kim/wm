package com.abs.wfs.workman.function;

import java.util.HashMap;

enum IntWord {subfix, cate;}

public class CallSubProcessName {

    public static HashMap<String, HashMap<String, String>> dataMap = new HashMap();

    /* 설비 모델 타입 */
    public static final String BP_SINGLE = "BP_SINGLE";
    public static final String INOUT_SINGLE = "INOUT_SINGLE";
    public static final String BP_BATCH = "BP_BATCH";
    public static final String SORTER = "SORTER";
    public static final String INOUT_INLINE_SINGLE = "INOUT_INLINE_SINGLE";
    public static final String INLINE_DICING = "INLINE_DICING";
    public static final String UNPACKING = "UNPACKING";
    public static final String AFVI = "AFVI";
    public static final String COMMON = "COMMON";

    public static final String workflow = "workflow";
    public static final String eap = "eap";
    public static final String message = "message";

    public static final String baseFormat = "%s.%s.%s";


    static {
        dataMap = new HashMap<>();

        /* BP_SINGLE */
        HashMap<String, String> bp = new HashMap<>();
        bp.put(IntWord.subfix.name(), "BP");
        bp.put(IntWord.cate.name(), "bp");
        dataMap.put(BP_SINGLE, bp);

        /* INOUT_SINGLE */
        HashMap<String, String> inout = new HashMap<>();
        inout.put(IntWord.subfix.name(), "INOUT");
        inout.put(IntWord.cate.name(), "inout");
        dataMap.put(INOUT_SINGLE, inout);

        /* BP_BATCH */
        HashMap<String, String> batch = new HashMap<>();
        batch.put(IntWord.subfix.name(), "BPBATCH");
        batch.put(IntWord.cate.name(), "batch");
        dataMap.put(BP_BATCH, batch);

        /* SORTER */
        HashMap<String, String> sorter = new HashMap<>();
        sorter.put(IntWord.subfix.name(), "SORTER");
        sorter.put(IntWord.cate.name(), "sorter");
        dataMap.put(SORTER, sorter);

        /* INOUT_INLINE_SINGLE */
        HashMap<String, String> inoutInline = new HashMap<>();
        inoutInline.put(IntWord.subfix.name(), "SRINLINE");
        inoutInline.put(IntWord.cate.name(), "srinline");
        dataMap.put(INOUT_INLINE_SINGLE, inoutInline);

        /* INLINE_DICING */
        HashMap<String, String> inlineDicing = new HashMap<>();
        inlineDicing.put(IntWord.subfix.name(), "DICING");
        inlineDicing.put(IntWord.cate.name(), "dicing");
        dataMap.put(INLINE_DICING, inlineDicing);

        /* UNPACKING */
        HashMap<String, String> up = new HashMap<>();
        up.put(IntWord.subfix.name(), "GU");
        up.put(IntWord.cate.name(), "unpacking");
        dataMap.put(UNPACKING, up);

        /* AFVI */
        HashMap<String, String> afvi = new HashMap<>();
        afvi.put(IntWord.subfix.name(), "AFVI");
        afvi.put(IntWord.cate.name(), "afvi");
        dataMap.put(AFVI, afvi);


        System.out.println(dataMap.toString());

    }


    /* EAP 메시지 이름 */
    public static final String WFS_WORK_STARTED = "WFS_WORK_STARTED";
    public static final String WFS_WORK_START_REP = "WFS_WORK_START_REP";
    public static final String WFS_WORK_ORDER_REP = "WFS_WORK_ORDER_REP";
    public static final String WFS_WORK_ENDED = "WFS_WORK_ENDED";
    public static final String WFS_WORK_ABORT = "WFS_WORK_ABORT";
    public static final String WFS_UNLOAD_REQ = "WFS_UNLOAD_REQ";
    public static final String WFS_UNLOAD_COMP = "WFS_UNLOAD_COMP";
    public static final String WFS_TOOL_COND_REP = "WFS_TOOL_COND_REP";
    public static final String WFS_SORTER_MODE_CHANGE_REP = "WFS_SORTER_MODE_CHANGE_REP";
    public static final String WFS_READY_TO_WORK = "WFS_READY_TO_WORK";
    public static final String WFS_PROD_STARTED = "WFS_PROD_STARTED";
    public static final String WFS_PROD_ENDED = "WFS_PROD_ENDED";
    public static final String WFS_PROC_STARTED = "WFS_PROC_STARTED";
    public static final String WFS_PROC_ENDED = "WFS_PROC_ENDED";
    public static final String WFS_LOAD_REQ = "WFS_LOAD_REQ";
    public static final String WFS_LOAD_COMP = "WFS_LOAD_COMP";
    public static final String WFS_GLASS_ID_SCAN_REPORT = "WFS_GLASS_ID_SCAN_REPORT";
    public static final String WFS_SORTER_START_REP = "WFS_SORTER_START_REP";
    public static final String WFS_FLIP_STARTED = "WFS_FLIP_STARTED";
    public static final String WFS_FLIP_ENDE = "WFS_FLIP_ENDE";
    public static final String WFS_CELL_STARTED = "WFS_CELL_STARTED";
    public static final String WFS_CELL_MAP_REQ = "WFS_CELL_MAP_REQ";
    public static final String WFS_CELL_ENDED = "WFS_CELL_ENDED";
    public static final String WFS_CARR_SLOTMAP_REPORT_REQ = "WFS_CARR_SLOTMAP_REPORT_REQ";
    public static final String WFS_CARR_ID_READ = "WFS_CARR_ID_READ";


    /**
     * EAP 프로젝트의 비즈 로직 처리하는 서브 프로세스 호출 이름 가져오기
     * @param cid
     * @param modelType
     * @return
     */
    public static String getEapProjectSubProcessName(String cid, String modelType) {

        String eapPackageFormat = String.format(baseFormat, workflow, eap, message).concat(".%s.%s");

        switch (cid){
            case WFS_CARR_ID_READ:
                switch (modelType){
                    case BP_BATCH:
                    case SORTER:
                    case INLINE_DICING:
                        return String.format(eapPackageFormat, dataMap.get(BP_SINGLE).get(IntWord.cate.name()), CallSubProcessName.generateBwpName(cid, BP_SINGLE));
                    case INOUT_INLINE_SINGLE:
                        return String.format(eapPackageFormat, dataMap.get(INOUT_SINGLE).get(IntWord.cate.name()), CallSubProcessName.generateBwpName(cid, INOUT_SINGLE));
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }

            case WFS_LOAD_COMP:
                switch (modelType){
                    case INOUT_SINGLE:
                    case BP_BATCH:
                    case SORTER:
                    case INOUT_INLINE_SINGLE:
                    case INLINE_DICING:
                    case UNPACKING:
                    case AFVI:
                        return String.format(eapPackageFormat, dataMap.get(BP_SINGLE).get(IntWord.cate.name()), CallSubProcessName.generateBwpName(cid, BP_SINGLE));
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }

            case WFS_LOAD_REQ:
                switch (modelType){
                    case INOUT_SINGLE:
                    case INOUT_INLINE_SINGLE:
                    case INLINE_DICING:
                    case UNPACKING:
                        return String.format(eapPackageFormat, dataMap.get(BP_SINGLE).get(IntWord.cate.name()), CallSubProcessName.generateBwpName(cid, BP_SINGLE));
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }


            case WFS_PROC_ENDED:
                switch (modelType){
                    case INOUT_SINGLE:
                    case BP_BATCH:
                    case UNPACKING:
                    case AFVI:
                        return String.format(eapPackageFormat, dataMap.get(BP_SINGLE).get(IntWord.cate.name()), CallSubProcessName.generateBwpName(cid, BP_SINGLE));
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }

            case WFS_PROC_STARTED:
                switch (modelType){
                    case INOUT_SINGLE:
                    case BP_BATCH:
                    case UNPACKING:
                    case AFVI:
                        return String.format(eapPackageFormat, dataMap.get(BP_SINGLE).get(IntWord.cate.name()), CallSubProcessName.generateBwpName(cid, BP_SINGLE));
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }


            case WFS_PROD_ENDED:
                switch (modelType){
                    case INOUT_SINGLE:
                    case BP_BATCH:
                    case INOUT_INLINE_SINGLE:
                    case INLINE_DICING:
                    case UNPACKING:
                    case AFVI:
                        return String.format(eapPackageFormat, dataMap.get(BP_SINGLE).get(IntWord.cate.name()), CallSubProcessName.generateBwpName(cid, BP_SINGLE));
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }


                // TODO BP_BATCH 는 특화.?
            case WFS_PROD_STARTED:
                switch (modelType){
                    case INOUT_SINGLE:
                    case INOUT_INLINE_SINGLE:
                    case INLINE_DICING:
                    case UNPACKING:
                    case AFVI:
                        return String.format(eapPackageFormat, dataMap.get(BP_SINGLE).get(IntWord.cate.name()), CallSubProcessName.generateBwpName(cid, BP_SINGLE));
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }

            case WFS_READY_TO_WORK:
                switch (modelType){
                    case INOUT_SINGLE:
                    case AFVI:
                        return String.format(eapPackageFormat, dataMap.get(BP_SINGLE).get(IntWord.cate.name()), CallSubProcessName.generateBwpName(cid, BP_SINGLE));
                    case INLINE_DICING:
                        return String.format(eapPackageFormat, dataMap.get(INOUT_INLINE_SINGLE).get(IntWord.cate.name()), CallSubProcessName.generateBwpName(cid, INOUT_INLINE_SINGLE));
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }

            case WFS_TOOL_COND_REP:
                switch (modelType){
                    case AFVI:
                        return String.format(eapPackageFormat, dataMap.get(BP_SINGLE).get(IntWord.cate.name()), CallSubProcessName.generateBwpName(cid, BP_SINGLE));
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }

            case WFS_UNLOAD_COMP:
                switch (modelType){
                    case INOUT_SINGLE:
                    case BP_BATCH:
                    case SORTER:
                    case INOUT_INLINE_SINGLE:
                    case INLINE_DICING:
                    case UNPACKING:
                    case AFVI:
                        return String.format(eapPackageFormat, dataMap.get(BP_SINGLE).get(IntWord.cate.name()), CallSubProcessName.generateBwpName(cid, BP_SINGLE));
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }

             case WFS_UNLOAD_REQ:
                switch (modelType){
                    case INOUT_SINGLE:
                    case BP_BATCH:
                    case SORTER:
                    case INOUT_INLINE_SINGLE:
                    case INLINE_DICING:
                    case UNPACKING:
                    case AFVI:
                        return String.format(eapPackageFormat, dataMap.get(BP_SINGLE).get(IntWord.cate.name()), CallSubProcessName.generateBwpName(cid, BP_SINGLE));
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }

            case WFS_WORK_STARTED:
                switch (modelType){
                    case AFVI:
                        return String.format(eapPackageFormat, dataMap.get(BP_SINGLE).get(IntWord.cate.name()), CallSubProcessName.generateBwpName(cid, BP_SINGLE));
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }

            case WFS_WORK_ENDED:
                switch (modelType){
                    case AFVI:
                        return String.format(eapPackageFormat, dataMap.get(BP_SINGLE).get(IntWord.cate.name()), CallSubProcessName.generateBwpName(cid, BP_SINGLE));
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }

            case WFS_WORK_ORDER_REP:
                switch (modelType){
                    case INOUT_SINGLE:
                    case BP_BATCH:
                    case INOUT_INLINE_SINGLE:
                    case INLINE_DICING:
                    case UNPACKING:
                    case AFVI:
                        return String.format(eapPackageFormat, dataMap.get(BP_SINGLE).get(IntWord.cate.name()), CallSubProcessName.generateBwpName(cid, BP_SINGLE));
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }

            case WFS_WORK_START_REP:
                switch (modelType){
                    case INOUT_SINGLE:
                    case BP_BATCH:
                    case AFVI:
                        return String.format(eapPackageFormat, dataMap.get(BP_SINGLE).get(IntWord.cate.name()), CallSubProcessName.generateBwpName(cid, BP_SINGLE));
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }

            case WFS_CARR_SLOTMAP_REPORT_REQ:
                switch (modelType){
                    case INLINE_DICING:
                    case INOUT_INLINE_SINGLE:
                        return "workflow.eap.message.srinline.CARR_SLOTMAP-REPORT_REQ_SRINLINE";
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }

            case WFS_CELL_ENDED:
                switch (modelType){
                    case INLINE_DICING:
                        return "workflow.eap.message.dicing.CELL_ENDED";
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }

            case WFS_CELL_MAP_REQ:
                switch (modelType){
                    case INLINE_DICING:
                        return "workflow.eap.message.dicing.CELL_MAP_REQ";
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }
            case WFS_CELL_STARTED:
                switch (modelType){
                    case INLINE_DICING:
                        return "workflow.eap.message.dicing.CELL_STARTED";
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }

            case WFS_FLIP_ENDE:
                switch (modelType){
                    case SORTER:
                    case INLINE_DICING:
                        return "workflow.eap.message.sorter.FLIP_ENDED";
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }
            case WFS_FLIP_STARTED:
                switch (modelType){
                    case SORTER:
                    case INLINE_DICING:
                        return "workflow.eap.message.sorter.FLIP_STARTED";
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }
            case WFS_GLASS_ID_SCAN_REPORT:
                switch (modelType){
                    case UNPACKING:
                        return "workflow.eap.message.unpacking.PANEL_ID_SCAN_REPORT";
                    default:
                        return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);
                }

            case WFS_SORTER_START_REP:
                switch (modelType){
                    case SORTER:
                        return "workflow.eap.message.sorter.SORTER_START_REP";
                }


            default:
                return CallSubProcessName.generateNormalSubProcess(eapPackageFormat, cid, modelType);




        }



    }


    public static String generateNormalSubProcess(String format, String cid, String modelType){
        return String.format(format, dataMap.get(modelType).get(IntWord.cate.name()), CallSubProcessName.generateBwpName(cid, modelType));
    }

    /**
     * 실제 BWP로 개발된 서브 프로세스 명을 리턴
     * WFS_CARR_ID_READ, BP_SINGLE → CARR_ID_READ_BP
     * @param cid
     * @param modelType
     * @return
     */
    private static String generateBwpName(String cid, String modelType){

        String prefixToRemove = "WFS_";
        String detachedWfs = cid.substring(prefixToRemove.length());
        String subFix;

        try{
            subFix = dataMap.get(modelType).get(IntWord.subfix.name());
        }catch (Exception e){
            e.printStackTrace();
            return "ERROR";
        }


        return detachedWfs + "_" + subFix;


    }


    public static void main(String[] args) {

        String[] modelTypes = {"AFVI",
                "BP_SINGLE",
                "BP_BATCH",
                "SORTER",
                "INLINE_DICING",
                "INOUT_SINGLE",
                "INOUT_INLINE_SINGLE",
                "UNPACKING",
                "AFVI",
                "INOUT_SINGLE",
                "SORTER",
                "INLINE_DICING",
                "INOUT_INLINE_SINGLE",
                "INLINE_DICING",
                "AFVI",
                "INLINE_DICING",
                "INLINE_DICING",
                "SORTER",
                "SORTER",
                "UNPACKING",
                "BP_SINGLE",
                "INOUT_SINGLE",
                "BP_BATCH",
                "SORTER",
                "INOUT_INLINE_SINGLE",
                "INLINE_DICING",
                "UNPACKING",
                "AFVI",
                "BP_BATCH",
                "BP_SINGLE",
                "BP_SINGLE",
                "INOUT_SINGLE",
                "INOUT_SINGLE",
                "BP_BATCH",
                "INOUT_INLINE_SINGLE",
                "INOUT_INLINE_SINGLE",
                "INLINE_DICING",
                "INLINE_DICING",
                "UNPACKING",
                "UNPACKING",
                "SORTER",
                "SORTER",
                "SORTER",
                "BP_SINGLE",
                "INOUT_SINGLE",
                "BP_BATCH",
                "UNPACKING",
                "AFVI",
                "INLINE_DICING",
                "SORTER",
                "INOUT_INLINE_SINGLE",
                "BP_SINGLE",
                "INOUT_SINGLE",
                "BP_BATCH",
                "UNPACKING",
                "AFVI",
                "INLINE_DICING",
                "SORTER",
                "INOUT_INLINE_SINGLE",
                "BP_SINGLE",
                "INOUT_SINGLE",
                "BP_BATCH",
                "INOUT_INLINE_SINGLE",
                "INLINE_DICING",
                "UNPACKING",
                "AFVI",
                "SORTER",
                "BP_BATCH",
                "BP_SINGLE",
                "INOUT_SINGLE",
                "INOUT_INLINE_SINGLE",
                "INLINE_DICING",
                "UNPACKING",
                "AFVI",
                "SORTER",
                "BP_BATCH",
                "BP_SINGLE",
                "INOUT_SINGLE",
                "AFVI",
                "INOUT_INLINE_SINGLE",
                "INLINE_DICING",
                "UNPACKING",
                "SORTER",
                "BP_BATCH",
                "BP_SINGLE",
                "AFVI",
                "INLINE_DICING",
                "INOUT_SINGLE",
                "SORTER",
                "INOUT_INLINE_SINGLE",
                "UNPACKING",
                "BP_SINGLE",
                "INOUT_SINGLE",
                "BP_BATCH",
                "SORTER",
                "INOUT_INLINE_SINGLE",
                "INLINE_DICING",
                "UNPACKING",
                "AFVI",
                "BP_SINGLE",
                "BP_SINGLE",
                "INOUT_SINGLE",
                "INOUT_SINGLE",
                "BP_BATCH",
                "BP_BATCH",
                "SORTER",
                "SORTER",
                "INOUT_INLINE_SINGLE",
                "INOUT_INLINE_SINGLE",
                "INLINE_DICING",
                "INLINE_DICING",
                "UNPACKING",
                "AFVI",
                "BP_BATCH",
                "BP_SINGLE",
                "AFVI",
                "INLINE_DICING",
                "INOUT_SINGLE",
                "SORTER",
                "INOUT_INLINE_SINGLE",
                "UNPACKING",
                "BP_SINGLE",
                "INOUT_SINGLE",
                "BP_BATCH",
                "INOUT_INLINE_SINGLE",
                "INLINE_DICING",
                "UNPACKING",
                "AFVI",
                "BP_SINGLE",
                "INOUT_SINGLE",
                "BP_BATCH",
                "AFVI",
                "INLINE_DICING",
                "INOUT_INLINE_SINGLE",
                "UNPACKING",
                "BP_BATCH",
                "BP_SINGLE",
                "AFVI",
                "INLINE_DICING",
                "INOUT_SINGLE",
                "SORTER",
                "INOUT_INLINE_SINGLE",
                "UNPACKING"
        };

        String[] cidList = {"WFS_CARR_ID_READ",
                "WFS_CARR_ID_READ",
                "WFS_CARR_ID_READ",
                "WFS_CARR_ID_READ",
                "WFS_CARR_ID_READ",
                "WFS_CARR_ID_READ",
                "WFS_CARR_ID_READ",
                "WFS_CARR_ID_READ",
                "WFS_CARR_SLOTMAP_REPORT_REQ",
                "WFS_CARR_SLOTMAP_REPORT_REQ",
                "WFS_CARR_SLOTMAP_REPORT_REQ",
                "WFS_CARR_SLOTMAP_REPORT_REQ",
                "WFS_CARR_SLOTMAP_REPORT_REQ",
                "WFS_CELL_ENDED",
                "WFS_CELL_MAP_REQ",
                "WFS_CELL_MAP_REQ",
                "WFS_CELL_STARTED",
                "WFS_FLIP_ENDE",
                "WFS_FLIP_STARTED",
                "WFS_GLASS_ID_SCAN_REPORT",
                "WFS_LOAD_COMP",
                "WFS_LOAD_COMP",
                "WFS_LOAD_COMP",
                "WFS_LOAD_COMP",
                "WFS_LOAD_COMP",
                "WFS_LOAD_COMP",
                "WFS_LOAD_COMP",
                "WFS_LOAD_COMP",
                "WFS_LOAD_REQ",
                "WFS_LOAD_REQ",
                "WFS_LOAD_REQ",
                "WFS_LOAD_REQ",
                "WFS_LOAD_REQ",
                "WFS_LOAD_REQ",
                "WFS_LOAD_REQ",
                "WFS_LOAD_REQ",
                "WFS_LOAD_REQ",
                "WFS_LOAD_REQ",
                "WFS_LOAD_REQ",
                "WFS_LOAD_REQ",
                "WFS_LOAD_REQ",
                "WFS_LOAD_REQ",
                "WFS_PANEL_ID_SCAN_REPORT",
                "WFS_PROC_ENDED",
                "WFS_PROC_ENDED",
                "WFS_PROC_ENDED",
                "WFS_PROC_ENDED",
                "WFS_PROC_ENDED",
                "WFS_PROC_ENDED",
                "WFS_PROC_ENDED",
                "WFS_PROC_ENDED",
                "WFS_PROC_STARTED",
                "WFS_PROC_STARTED",
                "WFS_PROC_STARTED",
                "WFS_PROC_STARTED",
                "WFS_PROC_STARTED",
                "WFS_PROC_STARTED",
                "WFS_PROC_STARTED",
                "WFS_PROC_STARTED",
                "WFS_PROD_ENDED",
                "WFS_PROD_ENDED",
                "WFS_PROD_ENDED",
                "WFS_PROD_ENDED",
                "WFS_PROD_ENDED",
                "WFS_PROD_ENDED",
                "WFS_PROD_ENDED",
                "WFS_PROD_ENDED",
                "WFS_PROD_STARTED",
                "WFS_PROD_STARTED",
                "WFS_PROD_STARTED",
                "WFS_PROD_STARTED",
                "WFS_PROD_STARTED",
                "WFS_PROD_STARTED",
                "WFS_PROD_STARTED",
                "WFS_PROD_STARTED",
                "WFS_READY_TO_WORK",
                "WFS_READY_TO_WORK",
                "WFS_READY_TO_WORK",
                "WFS_READY_TO_WORK",
                "WFS_READY_TO_WORK",
                "WFS_READY_TO_WORK",
                "WFS_READY_TO_WORK",
                "WFS_SORTER_START_REP",
                "WFS_TOOL_COND_REP",
                "WFS_TOOL_COND_REP",
                "WFS_TOOL_COND_REP",
                "WFS_TOOL_COND_REP",
                "WFS_TOOL_COND_REP",
                "WFS_TOOL_COND_REP",
                "WFS_TOOL_COND_REP",
                "WFS_TOOL_COND_REP",
                "WFS_UNLOAD_COMP",
                "WFS_UNLOAD_COMP",
                "WFS_UNLOAD_COMP",
                "WFS_UNLOAD_COMP",
                "WFS_UNLOAD_COMP",
                "WFS_UNLOAD_COMP",
                "WFS_UNLOAD_COMP",
                "WFS_UNLOAD_COMP",
                "WFS_UNLOAD_REQ",
                "WFS_UNLOAD_REQ",
                "WFS_UNLOAD_REQ",
                "WFS_UNLOAD_REQ",
                "WFS_UNLOAD_REQ",
                "WFS_UNLOAD_REQ",
                "WFS_UNLOAD_REQ",
                "WFS_UNLOAD_REQ",
                "WFS_UNLOAD_REQ",
                "WFS_UNLOAD_REQ",
                "WFS_UNLOAD_REQ",
                "WFS_UNLOAD_REQ",
                "WFS_UNLOAD_REQ",
                "WFS_UNLOAD_REQ",
                "WFS_WORK_ENDED",
                "WFS_WORK_ENDED",
                "WFS_WORK_ENDED",
                "WFS_WORK_ENDED",
                "WFS_WORK_ENDED",
                "WFS_WORK_ENDED",
                "WFS_WORK_ENDED",
                "WFS_WORK_ENDED",
                "WFS_WORK_ORDER_REP",
                "WFS_WORK_ORDER_REP",
                "WFS_WORK_ORDER_REP",
                "WFS_WORK_ORDER_REP",
                "WFS_WORK_ORDER_REP",
                "WFS_WORK_ORDER_REP",
                "WFS_WORK_ORDER_REP",
                "WFS_WORK_START_REP",
                "WFS_WORK_START_REP",
                "WFS_WORK_START_REP",
                "WFS_WORK_START_REP",
                "WFS_WORK_START_REP",
                "WFS_WORK_START_REP",
                "WFS_WORK_START_REP",
                "WFS_WORK_STARTED",
                "WFS_WORK_STARTED",
                "WFS_WORK_STARTED",
                "WFS_WORK_STARTED",
                "WFS_WORK_STARTED",
                "WFS_WORK_STARTED",
                "WFS_WORK_STARTED",
                "WFS_WORK_STARTED"
        };


        for(int i = 0; i < cidList.length ; i++){
            String processName = CallSubProcessName.getEapProjectSubProcessName(cidList[i], modelTypes[i]);

            String resultFormat = "%s,%s,%s";

            System.out.println(String.format(resultFormat, modelTypes[i], cidList[i], processName));
        }

    }

}
