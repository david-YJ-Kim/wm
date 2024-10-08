package com.abs.wfs.workman.dao.domain.transferJob.service;


import com.abs.wfs.workman.dao.domain.transferJob.model.WnTransferJob;
import com.abs.wfs.workman.dao.domain.transferJob.repository.WnTransferJobRepository;
import com.abs.wfs.workman.dao.domain.transferJob.vo.CancelTransferJobResultVo;
import com.abs.wfs.workman.dao.domain.wipStat.model.WnWipStat;
import com.abs.wfs.workman.dao.domain.wipStat.service.WipStatServiceImpl;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.spec.out.mcs.McsCarrMoveCnclReqIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.UseStatCd;
import com.abs.wfs.workman.util.code.UseYn;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.solacesystems.jcsmp.JCSMPException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WnTransferJobServiceImpl implements WnTransferJobService {


    @Autowired
    WnTransferJobRepository wnTransferJobRepository;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;

    @Autowired
    MessageSendService messageSendService;

    @Autowired
    WipStatServiceImpl wipStatService;

    /**
     * Object Id로 조회
     * @param objId
     * @param useStatCd
     * @return
     */
    public WnTransferJob findByObjIdAndUseStatCd(String siteId,String objId, UseStatCd useStatCd){

        return this.wnTransferJobRepository.findByObjIdAndUseStatCdAndSiteId(objId, useStatCd, siteId);
    };


    /**
     * Transfer 잡 아이디로 조회 & 사용가능 여부 확인
     * @param jobId
     * @param useStatCd
     * @return
     */
    public List<WnTransferJob> findByJobIdAndUseStatCd(String siteId,String jobId, UseStatCd useStatCd){

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
    public List<WnTransferJob> findByCarrIdAndUseStatCd(String siteId, String carrId, UseStatCd useStatCd){

        return this.wnTransferJobRepository.findByCarrIdAndUseStatCdAndSiteId(carrId, useStatCd, siteId);
    };


    /**
     * Source 포트 기준으로 생성된 반송 잡 조회
     * @param srcPortId
     * @param siteId
     * @return
     */
    public List<WnTransferJob> findBySrcPortIdSiteId(String srcPortId, String siteId){

        return this.wnTransferJobRepository.findBySrcPortIdAndUseStatCdAndSiteId(srcPortId, UseStatCd.Usable, siteId);
    }


    /**
     * 목적지 포트 기준으로 생성된 반송 잡 조회
     * @param destPortId
     * @param siteId
     * @return
     */
    public List<WnTransferJob> findByDestPortIddAndSiteId(String destPortId,String siteId){
        return this.wnTransferJobRepository.findByDestPortIdAndUseStatCdAndSiteId(destPortId, UseStatCd.Usable, siteId);
    }


    /**
     * 포트 기준으로 생성된 반송 잡에 대해서 삭제 요청
     * @param siteId
     * @param portId
     */
    public CancelTransferJobResultVo cancelTransferJob(String siteId, String portId){


        List<WnTransferJob> srcTransferJobs = this.findBySrcPortIdSiteId(siteId, portId);
        List<WnTransferJob> tgtTransferJobs = this.findByDestPortIddAndSiteId(portId, siteId);

        log.info("Search transfer job with portId: {}. SourceJobs: {}, TargetJobs: {}"
                , portId, srcTransferJobs, tgtTransferJobs);


        List<WnTransferJob> cancelTargetJobs = new ArrayList<>();
        
        // 위치에 맞게 조건을 넣어서 필터링
        srcTransferJobs.stream()
                .filter(jobs -> this.checkJobDeleteYn(siteId, jobs, UseYn.Y))
                .forEach(cancelTargetJobs::add);
        log.info("Filter delete able from source transfer job. {}", cancelTargetJobs.size());

        tgtTransferJobs.stream()
                        .filter(jobs -> this.checkJobDeleteYn(siteId, jobs, UseYn.N))
                        .forEach(cancelTargetJobs::add);
        log.info("Filter delete able from target transfer job. {}", cancelTargetJobs.size());



        for(WnTransferJob job : cancelTargetJobs){
            String carrId = job.getCarrId();
            String commId = job.getJobId();

            try {
                this.sendMcsCancelTransferJob(siteId, carrId, commId);

            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }

        }


        return CancelTransferJobResultVo.builder()
                .siteId(siteId)
                .portId(portId)
                .canceledSrcTransferJobs(srcTransferJobs)
                .canceledDstTransferJobs(tgtTransferJobs)
                .totalCanceledCount(cancelTargetJobs.size())
                .build();

    }


    /**
     * 삭제 대상 반송 잡 여부 확인
     * @param siteId
     * @param wnTransferJob
     * @param isSrcTyp
     * @return
     */
    private boolean checkJobDeleteYn(String siteId, WnTransferJob wnTransferJob, UseYn isSrcTyp){

        String carrId = wnTransferJob.getCarrId();
        WnWipStat wipStat = this.wipStatService.findByOnlyCarrIdAndSiteIdAndUseStatCd(carrId, siteId);

        switch (isSrcTyp){
            case Y:
                log.info("");
                if(wipStat.getCrntEqpId().equals(wnTransferJob.getCrntEqpId()) &&
                    wipStat.getCrntPortId().equals(wnTransferJob.getCrntEqpId())){
                    log.info("Source dest and current location is same. now delete it");
                    return true;
                }

            case N:
                log.info("If target dest port is now un-available. Cancel all the job.");
                return true;

            default:
                return false;
        }



    }

    /**
     *
     * @param siteId
     * @param carrId
     * @param commId
     */
    private void sendMcsCancelTransferJob(String siteId, String carrId, String commId) throws JsonProcessingException {

        McsCarrMoveCnclReqIvo.Body body = new McsCarrMoveCnclReqIvo.Body();
        body.setSiteId(siteId);
        body.setCarrId(carrId);
        body.setCommId(commId);
        body.setEventComment("Cancel transfer job because of unmatched port state."); // TODO 에러 코드 정의

        this.messageSendService.sendMessageSend(
                McsCarrMoveCnclReqIvo.system,
                McsCarrMoveCnclReqIvo.cid,
                this.apPayloadGenerateService.generateBody(WorkManCommonUtil.generateRandomKey(), body)
        );

    }


}
