package com.abs.wfs.workman.dao.domain.posProdMtrlMove.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@Setter
@Entity(name = "CN_POS_PROD_MTRL_MOVE")
@DynamicInsert
@DynamicUpdate
@Cacheable(false)
public class CnPosProdMtrlMove {
    @Id
    @GenericGenerator(name = "CN_POS_PROD_MTRL_MOVE_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "CN_POS_PROD_MTRL_MOVE_SEQ_GENERATOR")
    private String objId;
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

    public void copyCommonFieldsForCreate(Timestamp workTime, String evntNm, String tid) {
        this.setCrtDt(this.getMdfyDt() != null  ? this.getMdfyDt() : workTime);
        this.setMdfyDt(this.getMdfyDt() != null  ? this.getMdfyDt() : workTime);
        this.setFnlEvntDt(workTime);

        // Modify User만 받아서 Create Mode시에는 CreateUser를 Api에서 넣도록하여 관리 포인트를 줄임
        this.setCrtUserId(this.getMdfyUserId());
        this.setMdfyUserId(this.getMdfyUserId());

        this.setTid(tid);
        this.setEvntNm(evntNm);
        this.setCstmEvntNm(evntNm);
    }
}
