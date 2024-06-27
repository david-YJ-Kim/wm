package com.abs.wfs.workman.dao.domain.tnLot.model;

import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity(name = "TN_POS_LOT")
@DynamicInsert
@DynamicUpdate
@Cacheable(false)
public class TnPosLot {

    @Id
    @GenericGenerator(name = "TN_POS_LOT_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "TN_POS_LOT_SEQ_GENERATOR")
    private String objId;
    private String siteId;
    private String lotId;
    private String lotNm;
    private String statCd;
    private String prevStatCd;
    private Long orgnQty;
    private Long qty;
    private Long prevQty;
    private Long lossQty;
    private Long prevLossQty;
    private Long dfctQty;
    private String unitId;
    private String prodDefId;
    private String prntLotId;
    private String rootPrntLotId;
    private String prodTyp;
    private String prodOrdId;
    private String workOrdId;
    private String procDefId;
    private String subProcDefId;
    private String procNodeId;
    private String procSgmtId;
    private String eqpId;
    private String procSgmtRuleId;
    private Long ruleSeq;
    private String procStatCd;
    private String lctnNm;
    private String nextSubProcDefId;
    private String nextProcSgmtId;
    private Timestamp mvinDt;
    private Timestamp mvoutDt;
    private Timestamp lotFnshdDt;
    private Timestamp lotShipDt;
    private Timestamp procStrtDt;
    private Timestamp procEndDt;
    private String holdYn;
    private String rwrkYn;
    private String rsrvFtactYn;
    private String rwrkTyp;
    private Long totRwrkCnt;
    private Long rwrkCnt;
    private String prevProdDefId;
    private String prevProcDefId;
    private String prevSubProcDefId;
    private String prevProcNodeId;
    private String prevProcSgmtId;
    private String prevEqpId;
    private String prevLctnNm;
    private String ownSiteId;
    private String destSiteId;
    private String srcInptId;
    private String srcBtchId;
    private String portId;
    private String cstmId;
    private Long pono;
    private String grdCd;
    private Timestamp dueDt;
    private String mergLotId;
    private String rpstLotId;
    private Timestamp rcvDt;
    private String rsrvEqpId;
    private Timestamp rsrvDt;
    private String prevGrdCd;
    private String mvinUserId;
    private String mvoutUserId;
    private String mainProcDefId;
    private String adhcProcSysId;
    private String chldLotId;
    private String mtrlTyp;
    private String prevMtrlTyp;
    private String subMtrlTyp;
    private String prevSubMtrlTyp;
    private String prevProdTyp;
    private String prevSiteId;
    private Long subMtrlQty;
    private Long prevSubMtrlQty;
    private String carrId;
    private String rcpDefId;
    private Timestamp sgmtInDt;
    private String packNo;
    private String boxId;
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
    private Timestamp mdfyDt; // NOT NULL ENABLE
    private Timestamp fnlEvntDt;
    private String tid;
    private String lotOrdTyp;
    private Long initSubMtrlDfctQty;
    private String sgmtStatCd;
    private Long prevDfctQty;
    private String asgnMtrlYn;
    private Long subMtrlDfctQty;
    private Long prevSubMtrlDfctQty;
    private String dtlStatCd;
    private String subEqpId;
    private String subProcSgmtId;
    private String selfInspYn = "N"; // DEFAULT 'N'
    private String autoDspYn;
    private String autoInvldYn;
    private String autoInvldCm;
    private Long realQty;
    private String workId;
    private String mtrlDefId;
    private String fnlTkinObjId;
    private String fnlTkoutObjId;
    private String engrIssueNoList;




}
