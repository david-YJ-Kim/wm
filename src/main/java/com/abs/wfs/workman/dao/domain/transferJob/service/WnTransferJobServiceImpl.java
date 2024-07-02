package com.abs.wfs.workman.dao.domain.transferJob.service;


import com.abs.wfs.workman.dao.domain.transferJob.model.WnTransferJob;
import com.abs.wfs.workman.dao.domain.transferJob.repository.WnTransferJobRepository;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WnTransferJobServiceImpl implements WnTransferJobService {


    @Autowired
    WnTransferJobRepository wnTransferJobRepository;


    /**
     * Object Id로 조회
     * @param objId
     * @param useStatCd
     * @return
     */
    public Optional<WnTransferJob> findByObjIdAndUseStatCd(String siteId,String objId, UseStatCd useStatCd){

        return this.wnTransferJobRepository.findByObjIdAndUseStatCdAndSiteId(objId, useStatCd, siteId);
    };


    /**
     * Transfer 잡 아이디로 조회 & 사용가능 여부 확인
     * @param jobId
     * @param useStatCd
     * @return
     */
    public Optional<List<WnTransferJob>> findByJobIdAndUseStatCd(String siteId,String jobId, UseStatCd useStatCd){

        return this.wnTransferJobRepository.findByJobIdAndUseStatCdAndSiteId(jobId, useStatCd, siteId);
    };


    /**
     * Carr 아이디로 조회 되는 정보 조회 & 사용 가능 여부 확인
     *
     *
     * @param carrId
     * @param useStatCd
     * @return
     */
    public Optional<List<WnTransferJob>> findByCarrIdAndUseStatCd(String siteId, String carrId, UseStatCd useStatCd){

        return this.wnTransferJobRepository.findByCarrIdAndUseStatCdAndSiteId(carrId, useStatCd, siteId);
    };


}
