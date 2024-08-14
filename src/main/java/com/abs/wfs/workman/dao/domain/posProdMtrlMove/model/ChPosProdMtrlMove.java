package com.abs.wfs.workman.dao.domain.posProdMtrlMove.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity(name = "CH_POS_PROD_MTRL_MOVE")
@DynamicInsert
@DynamicUpdate
@Cacheable(false)
public class ChPosProdMtrlMove {
    @Id
    @GenericGenerator(name = "CH_POS_PROD_MTRL_MOVE_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "CH_POS_PROD_MTRL_MOVE_SEQ_GENERATOR")
    private String objId;
    private String refObjId;
    private String siteId;
    private String eqpId;
    private String portId;
    private String lotId;
    private String prodMtrlId;
    private String carrId;
    private Long slotNo;
    private String procSgmtId;
    private String mtrlWorkFaceCd;
    private String rcpId;
    private Timestamp carrOutDt;
    private Timestamp eqpImportDt;
    private String subEqpId;
    private Timestamp prodStartDt;
    private Timestamp prodEndDt;
    private Timestamp eqpExportDt;
    private Timestamp carrInDt;
    private String evntNm;
    private String prevEvntNm;
    private String cstmEvntNm;
    private String prevCstmEvntNm;
    private String useStatCd;
    private String rsnCd;
    private String trnsCm;
    private String crtUserId;
    private Timestamp crtDt;
    private String mdfyUserId;
    private Timestamp mdfyDt;
    private Timestamp fnlEvntDt;
    private String tid;

    public ChPosProdMtrlMove(CnPosProdMtrlMove cnPosProdMtrlMove) {
        BeanUtils.copyProperties(cnPosProdMtrlMove, this);
        this.setRefObjId(cnPosProdMtrlMove.getObjId());
        this.setObjId(null);
    }

    public ChPosProdMtrlMove() {

    }
}
