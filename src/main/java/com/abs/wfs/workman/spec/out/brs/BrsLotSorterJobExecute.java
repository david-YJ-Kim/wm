package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.out.brs.common.Slots;
import lombok.Data;

import java.util.List;

@Data
public class BrsLotSorterJobExecute extends ApMsgCommonVo {
	BrsLotSorterJobExecuteBody body;
	@Data
	public static class BrsLotSorterJobExecuteBody extends ApMsgBody {

		String jobId;
		String srcLotId;
		String srcCarrId;
		String tgtLotId;
		String tgtCarrId;
		String sorterJobTyp;
		String dtlSorterJobTyp;
		String fnlEvntDt;
		List<Slots> slots;
	}

}
