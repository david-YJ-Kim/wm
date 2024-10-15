package com.abs.wfs.workman.service.flow.fix;

import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgHead;
import com.abs.wfs.workman.spec.in.fix.WfsFixEventReqIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.WorkStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 요청이 들어온 항목에 대해서만 재시도 로직 수행
 */
@Service
@Slf4j
public class WfsFixEventReqServiceImpl implements WfsFixEventReq{


    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsFixEventReqIvo fixEventReqIvo) throws Exception {

        WfsFixEventReqIvo.Body body = fixEventReqIvo.getBody();
        apFlowProcessVo.setApMsgBody(body);


        log.info("dispatch fix request with cid : {} and workStatCd : {}", body.getCid(), body.getWorkStatCd());
        switch (body.getCid()){
            case WorkManMessageList.WFS_RECIPE_VALIDATE_REP:
                switch (body.getWorkStatCd()){
                    case Standby:
                        break;
                    case Ready:
                        break;

                }
        }

        return null;
    }

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, ApMsgHead apMsgHead) {
        return  WorkManCommonUtil.initializeProcessVo(cid, trackingKey, scenarioType, apMsgHead);
    }
}
