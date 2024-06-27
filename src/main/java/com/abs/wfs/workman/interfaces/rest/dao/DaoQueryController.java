package com.abs.wfs.workman.interfaces.rest.dao;


import com.abs.wfs.workman.dao.query.eqp.service.EqpServiceImpl;
import com.abs.wfs.workman.dao.query.eqp.vo.UpdatePortCarrierDto;
import com.abs.wfs.workman.dao.query.eqp.vo.UpdatePortStatAndCarrierDto;
import com.abs.wfs.workman.dao.query.eqp.vo.UpdatePortStatCdDto;
import com.abs.wfs.workman.dao.query.eqp.vo.UpdateUnloadCompleteDto;
import com.abs.wfs.workman.dao.query.lot.service.LotQueryServiceImpl;
import com.abs.wfs.workman.dao.query.tool.vo.QueryEqpVo;
import com.abs.wfs.workman.dao.query.tool.vo.QueryPortVo;
import com.abs.wfs.workman.dao.query.wipLot.service.WipLotQueryServiceImpl;
import com.abs.wfs.workman.dao.query.wipLot.vo.WipLotDto;
import com.abs.wfs.workman.dao.query.work.service.WorkService;
import com.abs.wfs.workman.dao.query.work.vo.WorkJobLotQueryDto;
import com.abs.wfs.workman.dao.query.work.vo.WorkSlotListInfoQueryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/dao/query/")
@RequiredArgsConstructor
public class DaoQueryController {

    @Autowired
    EqpServiceImpl eqpService;


    @PostMapping("eqp/update/updateUnloadComplete")
    public int updateUnloadComplete(@RequestBody UpdateUnloadCompleteDto dto) throws Exception {

        return this.eqpService.updateUnloadComplete(dto);

    }

    @PostMapping("eqp/update/updatePortCarrier")
    public int updatePortCarrier(@RequestBody UpdatePortCarrierDto dto) throws Exception {

        return this.eqpService.updatePortCarrier(dto);

    }


    @PostMapping("eqp/update/portStatAndCarrier")
    public int updatePortStatAndCarrier(@RequestBody UpdatePortStatAndCarrierDto dto) throws Exception {

        return this.eqpService.updatePortStatAndCarrier(dto);

    }


    @PostMapping("eqp/update/updatePortStatCd")
    public int updatePortStatCd(@RequestBody UpdatePortStatCdDto dto) throws Exception {

        return this.eqpService.updatePortStatCd(dto);

    }



    @GetMapping("eqp/search/siteId/{siteId}/eqpId/{eqpId}/portId/{portId}")
    public Optional<QueryPortVo> getQueryPort(@PathVariable String siteId, @PathVariable String eqpId, @PathVariable String portId) throws Exception {

        return Optional.ofNullable(this.eqpService.getQueryPort(siteId, eqpId, portId));
    }

    @GetMapping("eqp/search/siteId/{siteId}/eqpId/{eqpId}/")
    public Optional<QueryEqpVo> getQueryEqp(@PathVariable String siteId, @PathVariable String eqpId) throws Exception {

        return Optional.ofNullable(this.eqpService.getQueryEqp(siteId, eqpId));
    }


    /**
     * LOT QUERY MAPPER
     * com.abs.wfs.workman.dao.query.lot.mapper.LotQueryMapper
     */

    @Autowired
    private LotQueryServiceImpl lotQueryService;





    /**
     * LOT WIP LOT Mapper
     * com.abs.wfs.workman.dao.query.wipLot.mapper.WipLotMapper
     */


    @Autowired
    WipLotQueryServiceImpl wipLotQueryService;


    @PostMapping("wipLot/search/selectWipLotInfo")
    public Optional<WipLotDto> selectWipLotInfo(@RequestBody WipLotDto dto) throws Exception {

        log.info(dto.toString());
        return this.wipLotQueryService.selectWipLotInfo(dto);

    }


    /**
     * WORK MAPPER
     * com.abs.wfs.workman.dao.query.work.mapper.WorkMapper
     */
    @Autowired
    WorkService workService;

    @PostMapping("work/search/selectWorkJobLotQueryDto")
    public Optional<WorkJobLotQueryDto> search(@RequestBody WorkJobLotQueryDto dto) throws Exception {

        log.info(dto.toString());
        return this.workService.searchWorkJobLotQueryDto(dto);

    }

    @PostMapping("work/search/searchWorkSlotListInfoQueryDto")
    public Optional<List<WorkSlotListInfoQueryDto>> search(@RequestBody WorkSlotListInfoQueryDto dto) throws Exception {

        log.info(dto.toString());
        return this.workService.searchWorkSlotListInfoQueryDto(dto);

    }
}
