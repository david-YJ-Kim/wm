package com.abs.wfs.workman.service.flow.far;


import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.ApStringConstant;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class WfsArCommonUtil {


    /**
     * 메시지 전문 내 Body에서 필요로 하는 항목을 조회하여 Return
     * @param payload
     * @param args
     * @return
     */
    public Map<String, String> getValueFromParsedMsgObj(String payload, String... args)  {


        JSONObject obj = new JSONObject(payload);
        JSONObject bodyObj;

        if(obj.has("body")) {

            bodyObj = obj.getJSONObject("body");
        }else {
            bodyObj = obj;
        }

        Map<String, String> map = new HashMap<>();
        for(String arg: args){

            try{

                if(arg.contains(ApStringConstant.DepthDelimeter)){
                    map.put(arg, this.getValueFromDepthCase(bodyObj, arg));
                }else {

                    if(bodyObj.has(arg)){
                        map.put(arg, bodyObj.getString(arg));
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
                log.error(e.getMessage());
                map.put(arg, null);

            }

        }

        log.info("Get some value from message payload. resultMap: {}", map.toString());
        return map;

    }

    /**
     * '>' 가 포함된 경로가 있는 value 를 획득하는 메소드
     * 
     * ex) Status>inPortId
     * ex) A>B>C
     * @param bodyObj
     * @param arg
     * @return  해당 경로에 존재하는 값
     */
    private String getValueFromDepthCase(JSONObject bodyObj, String arg){

        String[] depth = arg.split(ApStringConstant.DepthDelimeter);
        if(depth.length == 2){
            return bodyObj.getJSONObject(depth[0]).getString(depth[1]);
        }else{

            String nextDepthArg = WorkManCommonUtil.removePrefix(arg, depth[0] + ApStringConstant.DepthDelimeter);
            return this.getValueFromDepthCase(bodyObj.getJSONObject(depth[0]), nextDepthArg);
        }
        
    }


}
