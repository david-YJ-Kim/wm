package com.abs.wfs.workman.spec.out.fis;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Builder;
import lombok.Data;

@Data
public class FisFileReportIvo extends ApMsgCommonVo {
	
	FisFileReportBody body;

	@Data
	public static class FisFileReportBody extends ApMsgBody {
		String prodMtrlId;
		String mtrlFace;
		String fileType;
		String fileName;
		String filePath;

		@Builder
		public FisFileReportBody(String prodMtrlId, String mtrlFace, String fileType, String fileName, String filePath) {
			this.prodMtrlId = prodMtrlId;
			this.mtrlFace = mtrlFace;
			this.fileName = fileName;
			this.filePath = filePath;
			this.fileType = (fileType != null) ? fileType
					: (fileName.trim().startsWith("I") ? "INSPECTION"
					: (fileName.trim().startsWith("M") ? "MEASUREMENT"
					: "Undefined"))
			;

		}
	}


}
