package com.abs.wfs.workman.dao.query.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.abs.wfs.workman.dao.query.mapper.WfsMapper;
import org.apache.ibatis.session.SqlSession;



public class CreateWorkRequestVo {

    private String siteId;
    private String cid;
    private String tid;
    private String userId;
    private String eqpId;
    private String workId;
    private String batchYn;
    private String inlineYn;
    private String eqpInlineId;
    private String inlineStatCd;
    private String dspWorkId;
    private String lotId;
    private String batchId;
    private String carrId;
    private String inPortId;
    private String inCarrId;
    private String inCarrTyp;
    private String lotQty;
    private String outPortId;
    private String outCarrId;
    private String outCarrTyp;
    private String prodDefId;
    private String procDefId;
    private String procSgmtId;
    private String selfInspYn;
    private String selfInspCnt;
    private boolean isToolHasFlipper;
    private List<RecipeVo> recipeList;
    private String nextWorkSide; // Top / Bottom / Both / Both_Flip
    private String currenntMtrlLoadingSide;  // Top || Bottom
    private SqlSession session;
    XMLManager xmlManager;
    WfsMapper wfsMapper;


    public String getSiteId() {
        return siteId;
    }
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
    public String getCid() {
        return cid;
    }
    public void setCid(String cid) {
        this.cid = cid;
    }
    public String getTid() {
        return tid;
    }
    public void setTid(String tid) {
        this.tid = tid;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getEqpId() {
        return eqpId;
    }
    public void setEqpId(String eqpId) {
        this.eqpId = eqpId;
    }
    public String getWorkId() {
        return workId;
    }
    public void setWorkId(String workId) {
        this.workId = workId;
    }
    public String getBatchYn() {
        return batchYn;
    }
    public void setBatchYn(String batchYn) {
        this.batchYn = batchYn;
    }
    public String getInlineYn() {
        return inlineYn;
    }
    public void setInlineYn(String inlineYn) {
        this.inlineYn = inlineYn;
    }
    public String getEqpInlineId() {
        return eqpInlineId;
    }
    public void setEqpInlineId(String eqpInlineId) {
        this.eqpInlineId = eqpInlineId;
    }
    public String getInlineStatCd() {
        return inlineStatCd;
    }
    public void setInlineStatCd(String inlineStatCd) {
        this.inlineStatCd = inlineStatCd;
    }
    public String getDspWorkId() {
        return dspWorkId;
    }
    public void setDspWorkId(String dspWorkId) {
        this.dspWorkId = dspWorkId;
    }
    public String getLotId() {
        return lotId;
    }
    public void setLotId(String lotId) {
        this.lotId = lotId;
    }
    public String getBatchId() {
        return batchId;
    }
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }
    public String getCarrId() {
        return carrId;
    }
    public void setCarrId(String carrId) {
        this.carrId = carrId;
    }
    public String getInPortId() {
        return inPortId;
    }
    public void setInPortId(String inPortId) {
        this.inPortId = inPortId;
    }
    public String getInCarrId() {
        return inCarrId;
    }
    public void setInCarrId(String inCarrId) {
        this.inCarrId = inCarrId;
    }
    public String getInCarrTyp() {
        return inCarrTyp;
    }
    public void setInCarrTyp(String inCarrTyp) {
        this.inCarrTyp = inCarrTyp;
    }
    public String getLotQty() {
        return lotQty;
    }
    public void setLotQty(String lotQty) {
        this.lotQty = lotQty;
    }
    public String getOutPortId() {
        return outPortId;
    }
    public void setOutPortId(String outPortId) {
        this.outPortId = outPortId;
    }
    public String getOutCarrId() {
        return outCarrId;
    }
    public void setOutCarrId(String outCarrId) {
        this.outCarrId = outCarrId;
    }
    public String getOutCarrTyp() {
        return outCarrTyp;
    }
    public void setOutCarrTyp(String outCarrTyp) {
        this.outCarrTyp = outCarrTyp;
    }
    public String getProdDefId() {
        return prodDefId;
    }
    public void setProdDefId(String prodDefId) {
        this.prodDefId = prodDefId;
    }
    public String getProcDefId() {
        return procDefId;
    }
    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }
    public String getProcSgmtId() {
        return procSgmtId;
    }
    public void setProcSgmtId(String procSgmtId) {
        this.procSgmtId = procSgmtId;
    }
    public String getSelfInspYn() {
        return selfInspYn;
    }
    public void setSelfInspYn(String selfInspYn) {
        this.selfInspYn = selfInspYn;
    }
    public String getSelfInspCnt() {
        return selfInspCnt;
    }
    public void setSelfInspCnt(String selfInspCnt) {
        this.selfInspCnt = selfInspCnt;
    }
    public List<RecipeVo> getRecipeList() {
        return recipeList;
    }
    public void setRecipeList(List<Map<String,String>> eleList) {

        this.recipeList = new ArrayList<RecipeVo>();
        for(Map<String, String> element : eleList) {
            RecipeVo rvo = new RecipeVo();
            rvo.setWorkFace(element.get("workFace").trim());
            rvo.setRecipeId(element.get("recipeId").trim());
            this.recipeList.add(rvo);
        }

    }
    public XMLManager getXmlManager() {
        return xmlManager;
    }
    public void setXmlManager(XMLManager xmlManager) {
        this.xmlManager = xmlManager;
    }
    public WfsMapper getWfsMapper() {
        return wfsMapper;
    }
    public void setWfsMapper(WfsMapper wfsMapper) {
        this.wfsMapper = wfsMapper;
    }
    public boolean isToolHasFlipper() {
        return isToolHasFlipper;
    }
    public void setToolHasFlipper(boolean isToolHasFlipper) {
        this.isToolHasFlipper = isToolHasFlipper;
    }
    public String getCurrenntMtrlLoadingSide() {
        return currenntMtrlLoadingSide;
    }
    public void setCurrenntMtrlLoadingSide(String currenntMtrlLoadingSide) {
        this.currenntMtrlLoadingSide = currenntMtrlLoadingSide;
    }
    public String getNextWorkSide() {
        return nextWorkSide;
    }
    public void setNextWorkSide(String nextWorkSide) {
        this.nextWorkSide = nextWorkSide;
    }




}

