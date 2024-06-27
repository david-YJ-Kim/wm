-- MESADM.WH_WORK_JOB_SLOT_INFO definition

CREATE TABLE "MESADM"."WH_WORK_JOB_SLOT_INFO"
   (	"OBJ_ID" VARCHAR2(100),
	"REF_OBJ_ID" VARCHAR2(100),
	"SITE_ID" VARCHAR2(40),
	"WORK_ID" VARCHAR2(40),
	"JOB_SEQ_ID" VARCHAR2(40),
	"SLOT_NO" VARCHAR2(40),
	"PROD_MTRL_ID" VARCHAR2(40),
	"PROD_MTRL_STRT_TM" TIMESTAMP (6),
	"PROD_MTRL_END_TM" TIMESTAMP (6),
	"JOB_STAT_CD" VARCHAR2(40),
	"SELF_INSP_YN" VARCHAR2(1),
	"MTRL_FACE_CD" VARCHAR2(40),
	"EVNT_NM" VARCHAR2(40),
	"PREV_EVNT_NM" VARCHAR2(100),
	"CSTM_EVNT_NM" VARCHAR2(100),
	"PREV_CSTM_EVNT_NM" VARCHAR2(100),
	"USE_STAT_CD" VARCHAR2(100),
	"RSN_CD" VARCHAR2(40),
	"TRNS_CM" VARCHAR2(4000),
	"CRT_USER_ID" VARCHAR2(40),
	"CRT_DT" TIMESTAMP (6),
	"MDFY_USER_ID" VARCHAR2(40),
	"MDFY_DT" TIMESTAMP (6),
	"FNL_EVNT_DT" TIMESTAMP (6),
	"TID" VARCHAR2(40),
	"LOT_ID" VARCHAR2(40),
	"SCAN_PROD_MTRL_ID" VARCHAR2(40),
	 CONSTRAINT "PK_WH_WORK_JOB_SLOT_INFO" PRIMARY KEY ("OBJ_ID")
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

CREATE UNIQUE INDEX "MESADM"."PK_WH_WORK_JOB_SLOT_INFO" ON "MESADM"."WH_WORK_JOB_SLOT_INFO" ("OBJ_ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TS_MES_IDX" ;

COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.OBJ_ID IS '오브젝트ID';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.REF_OBJ_ID IS 'Ref 오브젝트 ID';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.SITE_ID IS '사이트 정보';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.WORK_ID IS 'Job ID';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.JOB_SEQ_ID IS '작업 순번 ID';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.SLOT_NO IS 'SLOT 번호';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.PROD_MTRL_ID IS 'Produced Material ID';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.PROD_MTRL_STRT_TM IS '작업 시작 시간';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.PROD_MTRL_END_TM IS '작업 종료 시간';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.JOB_STAT_CD IS '작업 상태 코드';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.SELF_INSP_YN IS '자주검사여부';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.MTRL_FACE_CD IS 'FLIP여부코드 (TTT:N || TBT:Y)';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.EVNT_NM IS '이벤트명';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.PREV_EVNT_NM IS '이전이벤트명';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.CSTM_EVNT_NM IS '커스텀이벤트명';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.PREV_CSTM_EVNT_NM IS '이전커스텀이벤트명';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.USE_STAT_CD IS '사용여부';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.RSN_CD IS '사유코드';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.TRNS_CM IS 'Event Comment';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.CRT_USER_ID IS '생성자';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.CRT_DT IS '생성일';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.MDFY_USER_ID IS 'Event 요청자';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.MDFY_DT IS 'Event 시간 ';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.FNL_EVNT_DT IS '최종이벤트일시';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.TID IS '트랜잭션ID';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.LOT_ID IS 'Lot ID';
COMMENT ON COLUMN MESADM.WH_WORK_JOB_SLOT_INFO.SCAN_PROD_MTRL_ID IS 'Scan Produced Material ID';