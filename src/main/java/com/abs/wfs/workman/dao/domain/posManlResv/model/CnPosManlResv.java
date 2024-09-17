package com.abs.wfs.workman.dao.domain.posManlResv.model;

import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CN_POS_MANL_RESV")
public class CnPosManlResv {

    @Id
    @GenericGenerator(name = "CN_POS_MANL_RESV_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "CN_POS_MANL_RESV_SEQ_GENERATOR")
    @Column(name = "OBJ_ID")
    private String objId;
    private String siteId;
    private String lotId;
    private String procSgmtId;
    private String eqpId;
    private String evntNm;
    private String prevEvntNm;
    private String cstmEvntNm;
    private String prevCstmEvntNm;

    @Enumerated(EnumType.STRING)
    @Column(name="USE_STAT_CD")
    private UseStatCd useStatCd;
    private String rsnCd;
    private String trnsCm;
    private String crtUserId;
    private Timestamp crtDt;
    private String mdfyUserId;
    private Timestamp mdfyDt;
    private Timestamp fnlEvntDt;
    private String tid;
}
