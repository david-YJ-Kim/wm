package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.out.brs.common.CarrInfo;
import com.abs.wfs.workman.spec.out.brs.common.ProcCellInfo;
import com.abs.wfs.workman.spec.out.brs.common.ScrapStrip;
import com.abs.wfs.workman.spec.out.brs.common.ScrapUnit;
import lombok.Data;

import java.util.List;

@Data
public class BrsLotTrackOutReq extends ApMsgCommonVo {
	private BrsLotTrackOutReqBody body;

	@Data
	public static class BrsLotTrackOutReqBody extends ApMsgBody {

		private String autoReposition;
		private String asgnQty;
		private String workId;
		private String recipeId;
		private List<ProcCellInfo> procCellInfoList;
		private List<CarrInfo> carrList;
		private List<ScrapStrip> scrapStripList;
		private List<ScrapUnit> scrapUnitList;
		private String mtrlFaceCd;

	}
}
