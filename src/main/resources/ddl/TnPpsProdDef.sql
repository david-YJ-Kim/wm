-- MESADM.TN_PPS_PROD_DEF definition

CREATE TABLE "MESADM"."TN_PPS_PROD_DEF"
(	"OBJ_ID" VARCHAR2(100),
     "SITE_ID" VARCHAR2(40) NOT NULL ENABLE,
     "PROD_DEF_ID" VARCHAR2(40) NOT NULL ENABLE,
     "PROD_DEF_NM" VARCHAR2(40),
     "PROD_CLS_ID" VARCHAR2(40),
     "PROD_TYP" VARCHAR2(40),
     "SALES_CD" VARCHAR2(40),
     "GRD_CD" VARCHAR2(40),
     "QTY" NUMBER(20,10),
     "UNIT_ID" VARCHAR2(40),
     "PROD_DEF_TYP" VARCHAR2(40),
     "CSTM_ID" VARCHAR2(40),
     "LCTN_NM" VARCHAR2(40),
     "MTRL_TYP" VARCHAR2(40),
     "SUB_MTRL_TYP" VARCHAR2(40),
     "SUB_MTRL_QTY" NUMBER(20,10),
     "STRIP_X_SPEC_VAL" NUMBER,
     "STRIP_Y_SPEC_VAL" NUMBER,
     "CELL_X_SPEC_VAL" NUMBER,
     "CELL_Y_SPEC_VAL" NUMBER,
     "STRIP_PER_PANEL_QTY" NUMBER,
     "CELL_PER_STRIP_QTY" NUMBER,
     "CELL_PER_PANEL_QTY" NUMBER,
     "SHIP_UNIT_TYP" VARCHAR2(40),
     "CSTM_PROD_ID" VARCHAR2(20),
     "CSTM_PROD_VER_VAL" VARCHAR2(20),
     "PROD_MAIN_STRTR_VAL" VARCHAR2(20),
     "PROD_SUB_STRTR_VAL" VARCHAR2(40),
     "LAYER_VAL" VARCHAR2(20),
     "UI_SEQ" NUMBER,
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
     "PROD_GROUP_ID" VARCHAR2(40),
     "MNG_YN" VARCHAR2(1),
     CONSTRAINT "PK_TN_PPS_PROD_DEF" PRIMARY KEY ("OBJ_ID")
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

CREATE UNIQUE INDEX "MESADM"."PK_TN_PPS_PROD_DEF" ON "MESADM"."TN_PPS_PROD_DEF" ("OBJ_ID")
    PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TS_MES_IDX" ;
CREATE UNIQUE INDEX "MESADM"."UK_TN_PPS_PROD_DEF" ON "MESADM"."TN_PPS_PROD_DEF" ("SITE_ID", "PROD_DEF_ID")
    PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TS_MES_IDX" ;

GRANT DELETE ON "MESADM"."TN_PPS_PROD_DEF" TO "IFADMIN";
GRANT INSERT ON "MESADM"."TN_PPS_PROD_DEF" TO "IFADMIN";
GRANT SELECT ON "MESADM"."TN_PPS_PROD_DEF" TO "IFADMIN";
GRANT UPDATE ON "MESADM"."TN_PPS_PROD_DEF" TO "IFADMIN";

COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.OBJ_ID IS '오브젝트ID';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.SITE_ID IS '사이트ID';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.PROD_DEF_ID IS '제품정의ID';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.PROD_DEF_NM IS '제품정의명';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.PROD_CLS_ID IS '제품분류ID';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.PROD_TYP IS '제품타입';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.SALES_CD IS '매출코드';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.GRD_CD IS '등급코드';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.QTY IS '수량';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.UNIT_ID IS '단위ID';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.PROD_DEF_TYP IS '제품정의타입';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.CSTM_ID IS '고객ID';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.LCTN_NM IS '소재지명';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.MTRL_TYP IS '물자타입';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.SUB_MTRL_TYP IS '부물자타입';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.SUB_MTRL_QTY IS '부물자수량';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.STRIP_X_SPEC_VAL IS 'Panel 내 Strip X Size';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.STRIP_Y_SPEC_VAL IS 'Panel 내 Strip Y Size';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.CELL_X_SPEC_VAL IS 'Panel 내 Cell X Size';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.CELL_Y_SPEC_VAL IS 'Panel 내 Cell Y Size';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.STRIP_PER_PANEL_QTY IS 'Panel 내 Strip 개수';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.CELL_PER_STRIP_QTY IS 'Strip 내 Cell 개수';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.CELL_PER_PANEL_QTY IS 'Panel 내 Cell 개수';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.SHIP_UNIT_TYP IS '출하 Unit 단위';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.CSTM_PROD_ID IS '고객사 제품 코드';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.CSTM_PROD_VER_VAL IS '고객사 제품 버전';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.PROD_MAIN_STRTR_VAL IS '제품 주요 구조';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.PROD_SUB_STRTR_VAL IS '제품 상세 구조';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.LAYER_VAL IS '제품 Layer 수 (층수)';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.UI_SEQ IS '단위순번';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.EVNT_NM IS '이벤트명';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.PREV_EVNT_NM IS '이전이벤트명';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.CSTM_EVNT_NM IS '커스텀이벤트명';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.PREV_CSTM_EVNT_NM IS '이전커스텀이벤트명';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.USE_STAT_CD IS '사용여부';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.RSN_CD IS '사유코드';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.TRNS_CM IS '트랜잭션설명';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.CRT_USER_ID IS '생성사용자ID';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.CRT_DT IS '생성일시';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.MDFY_USER_ID IS '변경사용자ID';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.MDFY_DT IS '변경일시';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.FNL_EVNT_DT IS '최종이벤트일시';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.TID IS '트랜잭션ID';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.PROD_GROUP_ID IS '제품 그룹 ID';
COMMENT ON COLUMN MESADM.TN_PPS_PROD_DEF.MNG_YN IS '관리여부';