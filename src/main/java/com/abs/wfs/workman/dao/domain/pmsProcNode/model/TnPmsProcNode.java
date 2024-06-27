package com.abs.wfs.workman.dao.domain.pmsProcNode.model;


import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "TN_PMS_PROC_NODE")
public class TnPmsProcNode {

    @Id
    @GenericGenerator(name = "TN_PMS_PROC_NODE_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "TN_PMS_PROC_NODE_SEQ_GENERATOR")
    @Column(name = "OBJ_ID")
    private String objId;


    private String siteId;
    private String procNodeId;
    private String procNodeNm;
    private String procDefId;
    private String procNodeFlag;
    private String subProcDefId;
    private String procSgmtId;
    private String strtNodeYn;
    private String endNodeYn;
    private Long procNodeSeq;
    private String xcoordVal;
    private String ycoordVal;
    private String evntNm;
    private String prevEvntNm;
    private String cstmEvntNm;
    private String prevCstmEvntNm;


    @Enumerated(EnumType.STRING)
    private UseStatCd useStatCd;

    private String rsnCd;
    private String trnsCm;
    private String crtUserId;
    private Timestamp crtDt;
    private String mdfyUserId;
    private Timestamp mdfyDt;
    private Timestamp fnlEvntDt;
    private String tid;
    private String ptrn;
    private String layerId;
    private String mtrlFaceCd;
    private String extProcSgmtNm;


    @Builder
    public TnPmsProcNode(String objId, String siteId, String procNodeId, String procNodeNm, String procDefId, String procNodeFlag, String subProcDefId, String procSgmtId, String strtNodeYn, String endNodeYn, Long procNodeSeq, String xcoordVal, String ycoordVal, String evntNm, String prevEvntNm, String cstmEvntNm, String prevCstmEvntNm, UseStatCd useStatCd, String rsnCd, String trnsCm, String crtUserId, Timestamp crtDt, String mdfyUserId, Timestamp mdfyDt, Timestamp fnlEvntDt, String tid, String ptrn, String layerId, String mtrlFaceCd, String extProcSgmtNm) {
        this.objId = objId;
        this.siteId = siteId;
        this.procNodeId = procNodeId;
        this.procNodeNm = procNodeNm;
        this.procDefId = procDefId;
        this.procNodeFlag = procNodeFlag;
        this.subProcDefId = subProcDefId;
        this.procSgmtId = procSgmtId;
        this.strtNodeYn = strtNodeYn;
        this.endNodeYn = endNodeYn;
        this.procNodeSeq = procNodeSeq;
        this.xcoordVal = xcoordVal;
        this.ycoordVal = ycoordVal;
        this.evntNm = evntNm;
        this.prevEvntNm = prevEvntNm;
        this.cstmEvntNm = cstmEvntNm;
        this.prevCstmEvntNm = prevCstmEvntNm;
        this.useStatCd = useStatCd;
        this.rsnCd = rsnCd;
        this.trnsCm = trnsCm;
        this.crtUserId = crtUserId;
        this.crtDt = crtDt;
        this.mdfyUserId = mdfyUserId;
        this.mdfyDt = mdfyDt;
        this.fnlEvntDt = fnlEvntDt;
        this.tid = tid;
        this.ptrn = ptrn;
        this.layerId = layerId;
        this.mtrlFaceCd = mtrlFaceCd;
        this.extProcSgmtNm = extProcSgmtNm;
    }
}
