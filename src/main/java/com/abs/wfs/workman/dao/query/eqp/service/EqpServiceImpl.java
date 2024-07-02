package com.abs.wfs.workman.dao.query.eqp.service;


import com.abs.wfs.workman.dao.domain.tnPort.model.TnPosPortMapper;
import com.abs.wfs.workman.dao.query.eqp.mapper.EqpMyMapper;
import com.abs.wfs.workman.dao.query.eqp.vo.*;
import com.abs.wfs.workman.dao.query.tool.vo.QueryEqpVo;
import com.abs.wfs.workman.dao.query.tool.vo.QueryPortVo;
import com.abs.wfs.workman.util.code.ApStringConstant;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 포트와 설비에 대한 서비스
 */

@Service
@Slf4j
public class EqpServiceImpl implements EqpService{

    @Autowired
    EqpMyMapper eqpMapper;

    /**
     * UNLOAD_COMPLETED
     * UPDATE TN_POS_PORT
     * @return
     * @throws Exception
     */
    public int updateUnloadComplete(UpdateUnloadCompleteDto dto) throws Exception{


        //TODO Domain 대응 대상 메소드 - 단일 테이블 작업
        int resultVal = -1;

        try {
            TnPosPortDto param = new TnPosPortDto();

            //SET
            param.setMdfyUserId(dto.getUserId());
            param.setTid(dto.getTid());
            param.setEvntNm(dto.getCid());

            param.setCarrId(""); //UNLOAD_COMP clear

            if(!"".equals(dto.getStatCd())) param.setStatCd(dto.getStatCd());
            if(!"".equals(dto.getTrsfStatCd())) param.setTrsfStatCd(dto.getTrsfStatCd());

            //WHERE
            param.setpUseStatCd(UseStatCd.Usable.name());
            param.setpSiteId(dto.getSiteId());
            param.setpEqpId(dto.getEqpId());
            param.setpPortId(dto.getPortId());

            resultVal = eqpMapper.updateTnPosPort(param);

            if(resultVal > 0) {
                eqpMapper.createThPosPort(param.getObjId());
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
     * UPDATE TN_POS_PORT
     * CARR_ID
     * @return
     */
    public int updatePortCarrier(UpdatePortCarrierDto dto) throws Exception{

        int resultVal = -1;

        try {
            TnPosPortDto param = new TnPosPortDto();

            //SET
            param.setMdfyUserId(dto.getUserId());
            param.setTid(dto.getTid());
            param.setEvntNm(dto.getCid());
            param.setCarrId(dto.getCarrierId());

            //WHERE
            param.setpUseStatCd(UseStatCd.Usable.name());
            param.setpSiteId(dto.getSiteId());
            param.setpEqpId(dto.getEqpId());
            param.setpPortId(dto.getPortId());

            resultVal = eqpMapper.updateTnPosPort(param);

            if(resultVal > 0) {
                eqpMapper.createThPosPort(param.getObjId());
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return resultVal;

    }


    /**
     * UPDATE TN_POS_PORT
     * STAT_CD, CARR_ID
     * @return
     * @throws Exception
     */
    public int updatePortStatAndCarrier(UpdatePortStatAndCarrierDto dto) throws Exception{

        int resultVal = -1;

        try {
            TnPosPortMapper param = new TnPosPortMapper();

            //SET
            param.setMdfyUserId(dto.getUserId());
            param.setTid(dto.getTid());
            param.setEvntNm(dto.getCid());

            if(!"".equals(dto.getEfemCommStateCd())) param.setEfemCtrlModeCd(dto.getEfemCommStateCd());
            if(!"".equals(dto.getEfemStateCd())) param.setEfemStatCd(dto.getEfemStateCd());

            if(ApStringConstant.EMPTY.equals(dto.getStatCd())) {
                param.setTrsfStatCd(ApStringConstant.ReadyToLoad); //ReadyToLoad
                param.setCarrId(""); //clear

            } else {
                if(!"".equals(dto.getTrsfStatCd())) param.setTrsfStatCd(dto.getTrsfStatCd());
                if(!"".equals(dto.getCarrId())) param.setCarrId(dto.getCarrId());
            }

            if(!"".equals(dto.getStatCd()))param.setStatCd(dto.getStatCd());
            if(!"".equals(dto.getAcesModeCd())) param.setAcesModeCd(dto.getAcesModeCd());
            if(!"".equals(dto.getCtrlModeCd())) param.setCtrlModeCd(dto.getCtrlModeCd());

            //WHERE
            param.setPUseStatCd(UseStatCd.Usable.name());
            param.setPSiteId(dto.getSiteId());
            param.setPEqpId(dto.getEqpId());
            param.setPPortId(dto.getPortId());

//            param.setPUseStatCd(UseStatCd.Usable.name());
//            param.setPSiteId(dto.getSiteId());
//            param.setPEqpId(dto.getEqpId());
//            param.setPPortId(dto.getPortId());

            log.info("Print param: {}", param.toString());

            resultVal = eqpMapper.updateTnPosPort(param);

            if(resultVal > 0) {
                eqpMapper.createThPosPort(param.getObjId());
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return resultVal;

    }



    /**
     * UPDATE TN_POS_PORT
     * @return
     */
    public int updatePortStatCd(UpdatePortStatCdDto dto) throws Exception{

        int resultVal = -1;

        try {
            TnPosPortDto param = new TnPosPortDto();

            //SET
            param.setMdfyUserId(dto.getUserId());
            param.setTid(dto.getTid());
            param.setEvntNm(dto.getCid());

            if(!"".equals(dto.getStatCd()))param.setStatCd(dto.getStatCd());
            if(!"".equals(dto.getTrsfStatCd())) param.setTrsfStatCd(dto.getTrsfStatCd());
            if(!"".equals(dto.getAcesModeCd())) param.setAcesModeCd(dto.getAcesModeCd());
            if(!"".equals(dto.getCtrlModeCd())) param.setCtrlModeCd(dto.getCtrlModeCd());


            //WHERE
//            param.setPUseStatCd(UseStatCd.Usable.name());
//            param.setPSiteId(siteId);
//            param.setPEqpId(eqpId);
//            param.setPPortId(portId);

            resultVal = eqpMapper.updateTnPosPort(param);

            if(resultVal > 0) {
                eqpMapper.createThPosPort(param.getObjId());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {

        }

        return resultVal;

    }




    public Map<String, String> getEqp(String siteId, String eqpId) throws Exception{
        Map<String, String> returnVal = null;
        try {
            Map<String, String> param = new HashMap<>();
            param.put("eqpId", eqpId);
            param.put("siteId", siteId);
            param.put("useStatCd", UseStatCd.Usable.name());

            returnVal = eqpMapper.selectEqp(param);

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
        }
        return returnVal;
    }

    public QueryEqpVo getQueryEqp(String siteId, String eqpId) throws Exception{

        QueryEqpVo result = null;

        try {
            Map<String, String> param = new HashMap<String, String>();
            param.put("eqpId", eqpId);
            param.put("siteId", siteId);
            param.put("useStatCd", UseStatCd.Usable.name());
            result = eqpMapper.selectQueryEqpVO(param);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
        }

        return result;
    }

    public Map<String, String> getPort(String siteId, String eqpId, String portId) throws Exception{
        Map<String, String> returnVal = null;
        try {
            Map<String, String> param = new HashMap<String, String>();
            param.put("eqpId", eqpId);
            param.put("portId", portId);
            param.put("siteId", siteId);
            param.put("useStatCd", UseStatCd.Usable.name());

            returnVal = eqpMapper.selectPort(param);

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
        }
        return returnVal;
    }

    public QueryPortVo getQueryPort(String siteId, String eqpId, String portId) throws Exception{

        QueryPortVo result = null;

        try {
            Map<String, String> param = new HashMap<String, String>();
            param.put("eqpId", eqpId);
            param.put("portId", portId);
            param.put("siteId", siteId);
            param.put("useStatCd", UseStatCd.Usable.name());

            result = eqpMapper.selectQueryPortVO(param);

            log.info("QueryPort Result: {}", result.toString());

        } catch (Exception e) {
            log.error("Exception: {}", e);
            e.printStackTrace();
            throw e;
        } finally {
        }
        return result;
    }

}
