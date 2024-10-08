-- MESADM.TH_POS_PORT definition

CREATE TABLE "MESADM"."TH_POS_PORT"
   (	"OBJ_ID" VARCHAR2(100),
	"REF_OBJ_ID" VARCHAR2(100),
	"SITE_ID" VARCHAR2(40),
	"EQP_ID" VARCHAR2(40),
	"PORT_ID" VARCHAR2(40),
	"ACES_MODE_CD" VARCHAR2(40),
	"STAT_CD" VARCHAR2(40),
	"PREV_STAT_CD" VARCHAR2(40),
	"TRSF_STAT_CD" VARCHAR2(40),
	"CARR_ID" VARCHAR2(40),
	"EVNT_STAT_CD" VARCHAR2(40),
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
	"MDFY_DT" TIMESTAMP (6),
	"FNL_EVNT_DT" TIMESTAMP (6),
	"TID" VARCHAR2(40),
	"CTRL_MODE_CD" VARCHAR2(40),
	"EFEM_STAT_CD" VARCHAR2(40),
	"EFEM_CTRL_MODE_CD" VARCHAR2(40),
	 CONSTRAINT "PK_TH_POS_PORT" PRIMARY KEY ("OBJ_ID")
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

CREATE UNIQUE INDEX "MESADM"."PK_TH_POS_PORT" ON "MESADM"."TH_POS_PORT" ("OBJ_ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TS_MES_IDX" ;

GRANT ALTER ON "MESADM"."TH_POS_PORT" TO "IFADMIN";
  GRANT DELETE ON "MESADM"."TH_POS_PORT" TO "IFADMIN";
  GRANT INDEX ON "MESADM"."TH_POS_PORT" TO "IFADMIN";
  GRANT INSERT ON "MESADM"."TH_POS_PORT" TO "IFADMIN";
  GRANT SELECT ON "MESADM"."TH_POS_PORT" TO "IFADMIN";
  GRANT UPDATE ON "MESADM"."TH_POS_PORT" TO "IFADMIN";
  GRANT REFERENCES ON "MESADM"."TH_POS_PORT" TO "IFADMIN";
  GRANT READ ON "MESADM"."TH_POS_PORT" TO "IFADMIN";
  GRANT ON COMMIT REFRESH ON "MESADM"."TH_POS_PORT" TO "IFADMIN";
  GRANT QUERY REWRITE ON "MESADM"."TH_POS_PORT" TO "IFADMIN";
  GRANT DEBUG ON "MESADM"."TH_POS_PORT" TO "IFADMIN";
  GRANT FLASHBACK ON "MESADM"."TH_POS_PORT" TO "IFADMIN";

COMMENT ON COLUMN MESADM.TH_POS_PORT.OBJ_ID IS '오브젝트ID';
COMMENT ON COLUMN MESADM.TH_POS_PORT.REF_OBJ_ID IS 'Ref. 오브젝트ID';
COMMENT ON COLUMN MESADM.TH_POS_PORT.SITE_ID IS '사이트ID';
COMMENT ON COLUMN MESADM.TH_POS_PORT.EQP_ID IS '장비ID';
COMMENT ON COLUMN MESADM.TH_POS_PORT.PORT_ID IS '포트ID';
COMMENT ON COLUMN MESADM.TH_POS_PORT.ACES_MODE_CD IS '접근코드 [AUTO || MANUAL]';
COMMENT ON COLUMN MESADM.TH_POS_PORT.STAT_CD IS '상태코드 [EMPTY || OCCUPIED]';
COMMENT ON COLUMN MESADM.TH_POS_PORT.PREV_STAT_CD IS '이전상태코드';
COMMENT ON COLUMN MESADM.TH_POS_PORT.TRSF_STAT_CD IS '양도코드 [READYTOLOAD || ...]';
COMMENT ON COLUMN MESADM.TH_POS_PORT.CARR_ID IS 'Carrier ID';
COMMENT ON COLUMN MESADM.TH_POS_PORT.EVNT_STAT_CD IS 'PORTEVENT상태';
COMMENT ON COLUMN MESADM.TH_POS_PORT.EVNT_NM IS '이벤트명';
COMMENT ON COLUMN MESADM.TH_POS_PORT.PREV_EVNT_NM IS '이전이벤트명';
COMMENT ON COLUMN MESADM.TH_POS_PORT.CSTM_EVNT_NM IS '커스텀이벤트명';
COMMENT ON COLUMN MESADM.TH_POS_PORT.PREV_CSTM_EVNT_NM IS '이전커스텀이벤트명';
COMMENT ON COLUMN MESADM.TH_POS_PORT.USE_STAT_CD IS '사용여부';
COMMENT ON COLUMN MESADM.TH_POS_PORT.RSN_CD IS '사유코드';
COMMENT ON COLUMN MESADM.TH_POS_PORT.TRNS_CM IS '트랜잭션설명';
COMMENT ON COLUMN MESADM.TH_POS_PORT.CRT_USER_ID IS '생성사용자ID';
COMMENT ON COLUMN MESADM.TH_POS_PORT.CRT_DT IS '생성일시';
COMMENT ON COLUMN MESADM.TH_POS_PORT.MDFY_USER_ID IS '변경사용자ID';
COMMENT ON COLUMN MESADM.TH_POS_PORT.MDFY_DT IS '변경일시';
COMMENT ON COLUMN MESADM.TH_POS_PORT.FNL_EVNT_DT IS '최종이벤트일시';
COMMENT ON COLUMN MESADM.TH_POS_PORT.TID IS '트랜잭션ID';
COMMENT ON COLUMN MESADM.TH_POS_PORT.CTRL_MODE_CD IS '제어모드코드 [INSERVICE || OUTOFSERVICE]';
COMMENT ON COLUMN MESADM.TH_POS_PORT.EFEM_STAT_CD IS 'EFEM 상태 코드 [Run || Idle || Error]';
COMMENT ON COLUMN MESADM.TH_POS_PORT.EFEM_CTRL_MODE_CD IS 'EFEM Control Mode Code';