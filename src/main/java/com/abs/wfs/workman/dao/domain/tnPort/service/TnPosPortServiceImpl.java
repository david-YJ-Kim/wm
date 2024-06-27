package com.abs.wfs.workman.dao.domain.tnPort.service;

import com.abs.wfs.workman.dao.domain.tnPort.repository.TnPosPortRepository;
import com.abs.wfs.workman.dao.domain.tnPort.vo.TnPosPortUpdateRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TnPosPortServiceImpl {

    @Autowired
    TnPosPortRepository tnPosPortRepository;

//    public int updateTnPosPort(TnPosPortUpdateRequestDto updateRequestDto){
//        return this.tnPosPortRepository.updateTnPosPort(updateRequestDto.toEntity());
//    }
}
