package com.abs.wfs.workman.dao.query.lot.service;

import com.abs.wfs.workman.dao.query.lot.vo.*;

import java.util.Map;

public interface LotQueryService {

        /**
         * TN_POS_CARRIER MOVE_STAT_CD Update
         * @param dto UpdateCarrierMoveStatCdDto containing the parameters
         * @return
         * @throws Exception
         */
        int updateCarrierMoveStatCd(UpdateCarrierMoveStatCdDto dto) throws Exception;


        /**
         * Get lot information
         * @param siteId
         * @param lotId
         * @return
         * @throws Exception
         */
        Map<String, String> getLotInfo(String siteId, String lotId) throws Exception;

        /**
         * Get query lot information
         * @param siteId
         * @param lotId
         * @return
         * @throws Exception
         */
        QueryLotVo getQueryLot(String siteId, String lotId) throws Exception;

        /**
         * Get lot information by carrier ID
         * @param siteId
         * @param carrId
         * @return
         * @throws Exception
         */
        Map<String, String> getLotInfoByCarr(String siteId, String carrId) throws Exception;

        /**
         * Get carrier information
         * @param siteId
         * @param carrId
         * @return
         * @throws Exception
         */
        Map<String, String> getCarrierInfo(String siteId, String carrId) throws Exception;

        /**
         * Insert WN_MTRL_USAGE_INFO
         * @param dto InsertWnMtrlUsageInfoDto containing the parameters
         * @return
         * @throws Exception
         */
        int insertWnMtrlUsageInfo(InsertWnMtrlUsageInfoDto dto) throws Exception;


        /**
         * Insert WN_MTRL_USAGE_INFO for Dicing
         * @param dto InsertWnMtrlUsageInfoDicingDto containing the parameters
         * @return
         * @throws Exception
         */
        int insertWnMtrlUsageInfoDicing(InsertWnMtrlUsageInfoDicingDto dto) throws Exception;



        /**
         * Insert WH_ERROR_INFO
         * @param dto InsertWhErrorInfoDto containing the parameters
         * @return
         * @throws Exception
         */
        int insertWhErrorInfo(InsertWhErrorInfoDto dto) throws Exception;
}
