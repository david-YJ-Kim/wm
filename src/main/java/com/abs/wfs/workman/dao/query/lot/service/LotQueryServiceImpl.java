package com.abs.wfs.workman.dao.query.lot.service;

import com.abs.wfs.workman.dao.query.common.vo.TnPosCarrier;
import com.abs.wfs.workman.dao.query.common.vo.WhErrorInfo;
import com.abs.wfs.workman.dao.query.common.vo.WnMtrlUsageInfo;
import com.abs.wfs.workman.dao.query.lot.mapper.LotQueryMapper;
import com.abs.wfs.workman.dao.query.lot.vo.*;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class LotQueryServiceImpl implements LotQueryService {

    @Autowired
    LotQueryMapper lotQueryMapper;

    public QueryLotVo queryLotCondition(QueryLotVo vo) {
        return lotQueryMapper.queryLotCondition(vo);
    }


    @Override
    public int updateCarrierMoveStatCd(UpdateCarrierMoveStatCdDto dto) throws Exception {
        int resultVal = -1;

        try {
            TnPosCarrier param = new TnPosCarrier();

            // SET
            param.setMdfyUserId(dto.getUserId());
            param.setMoveStatCd(dto.getMoveStatCd());
            param.setEvntNm(dto.getCid());
            param.setTid(dto.getTid());
            param.setSiteId(dto.getSiteId());
            param.setUseStatCd(UseStatCd.Usable.name());
            param.setCarrId(dto.getCarrId());

            resultVal = lotQueryMapper.updateTnPosCarrierMoveStatCd(param);

            if (resultVal > 0) {
                lotQueryMapper.insertThPosCarrier(param.getObjId());
            }


        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
        }

        return resultVal;
    }


    public Map<String, String> getLotInfo(String siteId, String lotId) throws Exception{
        Map<String, String> returnVal = null;

        try {
            Map<String, String> param = new HashMap<>();
            param.put("lotId", lotId);
            param.put("siteId", siteId);
            param.put("useStatCd", UseStatCd.Usable.name());

            returnVal = lotQueryMapper.selectLot(param);

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
        }
        return returnVal;
    }

    public QueryLotVo getQueryLot(String siteId, String lotId) throws Exception{

        QueryLotVo result = null;

        try {
            Map<String, String> param = new HashMap<String, String>();
            param.put("lotId", lotId);
            param.put("siteId", siteId);
            param.put("useStatCd", UseStatCd.Usable.name());

            result = lotQueryMapper.selectQueryLotVo(param);

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
        }
        return result;
    }

    public Map<String, String> getLotInfoByCarr(String siteId, String carrId) throws Exception{
        Map<String, String> returnVal = null;

        try {
            Map<String, String> param = new HashMap<String, String>();
            param.put("carrId", carrId);
            param.put("siteId", siteId);
            param.put("useStatCd", UseStatCd.Usable.name());

            returnVal = lotQueryMapper.selectLot(param);

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
        }
        return returnVal;
    }

    public Map<String, String> getCarrierInfo(String siteId, String carrId) throws Exception{
        Map<String, String> returnVal = null;

        try {
            Map<String, String> param = new HashMap<String, String>();
            param.put("carrId", carrId);
            param.put("siteId", siteId);
            param.put("useStatCd", UseStatCd.Usable.name());

            returnVal = lotQueryMapper.selectCarrier(param);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
        }
        return returnVal;

    }

    @Override
    public int insertWnMtrlUsageInfo(InsertWnMtrlUsageInfoDto dto) throws Exception {
        int resultVal = -1;

        try {
            WnMtrlUsageInfo param = new WnMtrlUsageInfo();
            param.setSiteId(dto.getSiteId());
            param.setWorkId(dto.getWorkId());
            param.setEqpId(dto.getEqpId());
            param.setSubEqpId(dto.getSubEqpId());
            param.setLotId(dto.getLotId());
            param.setSpecId(dto.getSpecId());
            param.setSpecTyp(dto.getSpecTyp());
            param.setSpecUseCnt(Double.valueOf(dto.getSpecUseCnt()));
            param.setSpecLimitCnt(dto.getSpecLimitCnt());
            param.setEvntNm(dto.getCid());
            param.setPrevEvntNm("");
            param.setCstmEvntNm("");
            param.setPrevCstmEvntNm("");
            param.setRsnCd("");
            param.setTrnsCm("");
            param.setCrtUserId(dto.getUserId());
            param.setMdfyUserId(dto.getUserId());
            param.setUseStatCd("Usable");
            param.setTid(dto.getTid());

            resultVal = lotQueryMapper.insertWnMtrlUsageInfo(param);

            if (resultVal > 0) {
                lotQueryMapper.insertWhMtrlUsageInfo(param.getObjId());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
        }

        return resultVal;
    }




    @Override
    public int insertWnMtrlUsageInfoDicing(InsertWnMtrlUsageInfoDicingDto dto) throws Exception {
        int resultVal = -1;

        try {
            String[] useCntList = dto.getSpecUseCnt().split(",");
            String[] subSpecUseCntList = dto.getSubSpecUseCnt().split(",");
            int posnId = 1;
            for (int i = 0; i < useCntList.length; i++) {
                WnMtrlUsageInfo param = new WnMtrlUsageInfo();
                param.setSiteId(dto.getSiteId());
                param.setWorkId(dto.getWorkId());
                param.setEqpId(dto.getEqpId());
                param.setSubEqpId(dto.getSubEqpId());
                param.setLotId(dto.getLotId());
                param.setSpecId(dto.getSpecId() + "-" + posnId);
                param.setSpecTyp(dto.getSpecTyp());
                param.setPosnId(String.valueOf(posnId));
                param.setSpecUseCnt(Double.valueOf(useCntList[i]));
                param.setSubSpecUseCnt(Double.valueOf(subSpecUseCntList[i]));
                param.setSpecLimitCnt(dto.getSpecLimitCnt());
                param.setEvntNm(dto.getCid());
                param.setPrevEvntNm("");
                param.setCstmEvntNm("");
                param.setPrevCstmEvntNm("");
                param.setRsnCd("");
                param.setTrnsCm("");
                param.setCrtUserId(dto.getUserId());
                param.setMdfyUserId(dto.getUserId());
                param.setUseStatCd("Usable");
                param.setTid(dto.getTid());

                resultVal = lotQueryMapper.insertWnMtrlUsageInfo(param);
                if (resultVal > 0) {
                    lotQueryMapper.insertWhMtrlUsageInfo(param.getObjId());
                }

                posnId++;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {

        }

        return resultVal;
    }

    /**
     * insert WH_ERROR_INFO
     * @return
     */
    @Override
    public int insertWhErrorInfo(InsertWhErrorInfoDto dto) throws Exception {
        int resultVal = -1;

        try {
            WhErrorInfo whErrorInfo = new WhErrorInfo();

            whErrorInfo.setSiteId(dto.getSiteId());
            whErrorInfo.setMsgId(dto.getMsgId());
            whErrorInfo.setMsgCtntsCm(dto.getMsgCtnsCm());
            whErrorInfo.setWorkStatCd(dto.getWorkStatCd());
            whErrorInfo.setLotId(dto.getLotId());
            whErrorInfo.setCarrId(dto.getCarrId());
            whErrorInfo.setEqpId(dto.getEqpId());
            whErrorInfo.setPortId(dto.getPortId());
            whErrorInfo.setErrCd(dto.getErrCd());
            whErrorInfo.setErrCm(dto.getErrCm());
            whErrorInfo.setCrtUserId(dto.getUserId());
            whErrorInfo.setMdfyUserId(dto.getUserId());
            whErrorInfo.setEvntNm(dto.getCid());
            whErrorInfo.setTid(dto.getTid());

            resultVal = lotQueryMapper.insertWhErrorInfo(whErrorInfo);

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {

        }

        return resultVal;
    }

}
