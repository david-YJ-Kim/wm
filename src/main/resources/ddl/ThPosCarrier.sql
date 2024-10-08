-- MESADM.TH_POS_CARRIER definition

CREATE TABLE "MESADM"."TH_POS_CARRIER"
   (	"OBJ_ID" VARCHAR2(100),
	"SITE_ID" VARCHAR2(40),
	"REF_OBJ_ID" VARCHAR2(100),
	"CARR_ID" VARCHAR2(40),
	"CARR_NM" VARCHAR2(40),
	"CARR_DEF_ID" VARCHAR2(40),
	"CARR_CLS_ID" VARCHAR2(40),
	"MTRL_TYP" VARCHAR2(40),
	"EQP_ID" VARCHAR2(40),
	"LOAD_STAT_CD" VARCHAR2(40),
	"SLOT_QTY" NUMBER,
	"LOT_ASGN_QTY" NUMBER(20,10),
	"MAX_CPCT" NUMBER(20,10),
	"VNDR_ID" VARCHAR2(40),
	"STAT_CD" VARCHAR2(40),
	"PREV_STAT_CD" VARCHAR2(40),
	"LCTN_NM" VARCHAR2(40),
	"PREV_LCTN_NM" VARCHAR2(40),
	"USG_TYP" VARCHAR2(40),
	"USG_LMT_CNT" NUMBER,
	"USG_CNT" NUMBER,
	"USG_PRD_UNIT_CD" VARCHAR2(40),
	"USG_LMT_PRD" NUMBER,
	"USG_PRD" NUMBER,
	"CLN_TYP" VARCHAR2(40),
	"CLN_LMT_CNT" NUMBER,
	"CLN_CNT" NUMBER,
	"CLN_PRD_UNIT_CD" VARCHAR2(40),
	"CLN_LMT_PRD" NUMBER,
	"CLN_PRD" NUMBER,
	"FNL_CLN_DT" TIMESTAMP (6),
	"NEXT_CLN_DT" TIMESTAMP (6),
	"RPR_TYP" VARCHAR2(40),
	"RPR_LMT_CNT" NUMBER,
	"RPR_CNT" NUMBER,
	"RPR_PRD_UNIT_CD" VARCHAR2(40),
	"RPR_LMT_PRD" NUMBER,
	"RPR_PRD" NUMBER,
	"FNL_RPR_DT" TIMESTAMP (6),
	"NEXT_RPR_DT" TIMESTAMP (6),
	"UNIT_ID" VARCHAR2(40),
	"CARR_TYP" VARCHAR2(40),
	"CLN_STAT_CD" VARCHAR2(40),
	"SUB_MTRL_ASGN_QTY" NUMBER(20,10),
	"LOAD_QTY" NUMBER,
	"MTRL_ASGN_QTY" NUMBER(20,10),
	"MOVE_STAT_CD" VARCHAR2(40),
	"EVNT_NM" VARCHAR2(100),
	"PREV_EVNT_NM" VARCHAR2(100),
	"CSTM_EVNT_NM" VARCHAR2(100),
	"PREV_CSTM_EVNT_NM" VARCHAR2(100),
	"USE_STAT_CD" VARCHAR2(40),
	"RSN_CD" VARCHAR2(40),
	"TRNS_CM" VARCHAR2(4000),
	"CRT_USER_ID" VARCHAR2(40),
	"CRT_DT" TIMESTAMP (6),
	"MDFY_USER_ID" VARCHAR2(40),
	"MDFY_DT" TIMESTAMP (6) NOT NULL ENABLE,
	"FNL_EVNT_DT" TIMESTAMP (6),
	"TID" VARCHAR2(40),
	"ERP_CARR_ID" VARCHAR2(40),
	 CONSTRAINT "PK_TH_POS_CARRIER" PRIMARY KEY ("OBJ_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TS_MES_IDX"  ENABLE
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TS_MES_DAT" ;

CREATE UNIQUE INDEX "MESADM"."PK_TH_POS_CARRIER" ON "MESADM"."TH_POS_CARRIER" ("OBJ_ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TS_MES_IDX" ;

GRANT SELECT ON "MESADM"."TH_POS_CARRIER" TO "RPTADM";
  GRANT SELECT ON "MESADM"."TH_POS_CARRIER" TO "BWUSER";
  GRANT SELECT ON "MESADM"."TH_POS_CARRIER" TO "MESSPL";
  GRANT DELETE ON "MESADM"."TH_POS_CARRIER" TO "IFADMIN";
  GRANT INSERT ON "MESADM"."TH_POS_CARRIER" TO "IFADMIN";
  GRANT SELECT ON "MESADM"."TH_POS_CARRIER" TO "IFADMIN";
  GRANT UPDATE ON "MESADM"."TH_POS_CARRIER" TO "IFADMIN";

COMMENT ON COLUMN MESADM.TH_POS_CARRIER.OBJ_ID IS '최종이벤트순번';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.SITE_ID IS '사이트ID';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.REF_OBJ_ID IS '참조OBJID';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.CARR_ID IS '캐리어ID';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.CARR_NM IS '캐리어명';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.CARR_DEF_ID IS '캐리어정의ID';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.CARR_CLS_ID IS '캐리어분류ID';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.MTRL_TYP IS '물자타입';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.EQP_ID IS '장비ID';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.LOAD_STAT_CD IS '부하상태코드';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.SLOT_QTY IS '슬롯수량';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.LOT_ASGN_QTY IS 'Lot 할당수량';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.MAX_CPCT IS '최대CAPACITY';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.VNDR_ID IS '벤더ID';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.STAT_CD IS '상태코드';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.PREV_STAT_CD IS '이전상태코드';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.LCTN_NM IS '소재지명';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.PREV_LCTN_NM IS '이전소재지명';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.USG_TYP IS '사용타입';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.USG_LMT_CNT IS '사용한계수';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.USG_CNT IS '사용수';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.USG_PRD_UNIT_CD IS '사용기간단위코드';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.USG_LMT_PRD IS '사용한계기간';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.USG_PRD IS '사용기간';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.CLN_TYP IS '세정타입';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.CLN_LMT_CNT IS '세정한계수';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.CLN_CNT IS '세정수';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.CLN_PRD_UNIT_CD IS '세정기간단위코드';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.CLN_LMT_PRD IS '세정한계기간';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.CLN_PRD IS '세정기간';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.FNL_CLN_DT IS '최종세정일시';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.NEXT_CLN_DT IS '다음세정일시';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.RPR_TYP IS '보수타입';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.RPR_LMT_CNT IS '보수한계수';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.RPR_CNT IS '보수수';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.RPR_PRD_UNIT_CD IS '보수기간단위코드';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.RPR_LMT_PRD IS '보수한계기간';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.RPR_PRD IS '보수기간';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.FNL_RPR_DT IS '최종보수일시';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.NEXT_RPR_DT IS '다음보수일시';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.UNIT_ID IS 'Unit Id';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.CARR_TYP IS '캐리어타입';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.SUB_MTRL_ASGN_QTY IS 'Sub Material (Unit) 할당 수량';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.LOAD_QTY IS '적재 수량';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.MTRL_ASGN_QTY IS 'Produced Material (Glass) 할당 수량';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.MOVE_STAT_CD IS '반송 Move 상태 코드 [ON-PORT||VHL-LOAD||MOVING||VHL-UNLOARD||IN-ALT||IN-BUFFER||IN-STOCKER||IN-EQP||IN-BAY]';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.EVNT_NM IS '이벤트명';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.PREV_EVNT_NM IS '이전이벤트명';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.CSTM_EVNT_NM IS '커스텀이벤트명';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.PREV_CSTM_EVNT_NM IS '이전커스텀이벤트명';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.USE_STAT_CD IS '사용여부';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.RSN_CD IS '사유코드';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.TRNS_CM IS '트랜잭션설명';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.CRT_USER_ID IS '생성사용자ID';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.CRT_DT IS '생성일시';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.MDFY_USER_ID IS '변경사용자ID';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.MDFY_DT IS '변경일시';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.FNL_EVNT_DT IS '최종이벤트일시';
COMMENT ON COLUMN MESADM.TH_POS_CARRIER.TID IS '트랜잭션ID';