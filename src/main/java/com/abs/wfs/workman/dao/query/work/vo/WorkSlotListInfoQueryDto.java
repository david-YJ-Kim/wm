package com.abs.wfs.workman.dao.query.work.vo;


import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.Builder;
import lombok.Data;

@Data
public class WorkSlotListInfoQueryDto {

    String prodMtrlId;
    String slotNo;
    String subMtrlGrdCd;

    String workId;
    String lotId;
    UseStatCd useStatCd;

    @Builder
    public WorkSlotListInfoQueryDto(String workId, String lotId, UseStatCd useStatCd) {
        this.workId = workId;
        this.lotId = lotId;
        this.useStatCd = useStatCd;
    }
}
