package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.out.brs.common.Slots;
import lombok.Data;

import java.util.List;

@Data
public class BrsLotSelfInspNg extends ApMsgCommonVo {
	BrsLotSelfInspNgBody body;
	@Data
	public static class BrsLotSelfInspNgBody extends ApMsgBody {

		String recipeId;
		List<Slots> slots;
		String workId;
	}

}
