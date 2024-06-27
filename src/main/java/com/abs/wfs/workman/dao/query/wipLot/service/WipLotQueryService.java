package com.abs.wfs.workman.dao.query.wipLot.service;

import com.abs.wfs.workman.dao.query.wipLot.vo.WipLotDto;

import java.util.Optional;

public interface WipLotQueryService {

    Optional<WipLotDto> selectWipLotInfo(WipLotDto requestDto);
}
