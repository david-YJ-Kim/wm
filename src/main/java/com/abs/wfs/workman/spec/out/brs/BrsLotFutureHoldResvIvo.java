package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.out.brs.common.BrsLotFutureActIVO;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

import java.util.List;

@Data
public class BrsLotFutureHoldResvIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.BRS;
	public static String cid = WorkManMessageList.BRS_LOT_FUTURE_HOLD_RESV;

    Body body;

    @Data
    public static class Body extends ApMsgBody {

        List<BrsLotFutureActIVO> futureActs;

    }
}
