package com.abs.wfs.workman.dao.domain.issueReport.service;


import com.abs.wfs.workman.dao.domain.issueReport.model.WnIssueReport;
import com.abs.wfs.workman.dao.domain.issueReport.repository.WnIssueReportRepository;
import com.abs.wfs.workman.dao.domain.issueReport.vo.WnIssueReportDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WnIssueReportServiceImpl {

    @Autowired
    WnIssueReportRepository wnIssueReportRepository;
    
    
    // Case 1. 동일한 사유의 동일한 위치에서 에러가 특정 시간안에 발생 시, 이상이라 판단하고 알람


    /**
     * Issue report 데이터 적재
     * @param vo
     * @return
     */
    public WnIssueReport insertIssueRecord(WnIssueReportDto vo){
        return this.wnIssueReportRepository.save(vo.toEntity());
    }
}
