package com.abs.wfs.workman.dao.query.wip.service;

import com.abs.wfs.workman.dao.query.wip.vo.*;

import java.util.List;

public interface WipStatQueryService {

    /**
     * Retrieves WN_WIP_STAT by Lot ID.
     *
     * @param siteId Site ID
     * @param lotId Lot ID
     * @return List of WnWipStat objects
     * @throws Exception if an error occurs during retrieval
     */
    List<WnWipStat> selectByLotId(String siteId, String lotId) throws Exception;

    /**
     * Updates WORK_STAT_CD by CarrId base.
     *
     * @param dto UpdateWorkStatusByCarrIdDto containing update details
     * @return Number of rows affected
     * @throws Exception if an error occurs during update
     */
    int updateWorkStatusByCarrId(UpdateWorkStatusByCarrIdDto dto) throws Exception;

    /**
     * Updates WORK_STAT_CD by CarrId for batch end.
     *
     * @param dto UpdateWorkStatusByCarrIdForBatchEndDto containing update details
     * @return Number of rows affected
     * @throws Exception if an error occurs during update
     */
    int updateWorkStatusByCarrIdForBatchEnd(UpdateWorkStatusByCarrIdForBatchEndDto dto) throws Exception;

    /**
     * Updates work status by LotId.
     *
     * @param dto UpdateWorkStatusByLotIdDto containing update details
     * @return Number of rows affected
     * @throws Exception if an error occurs during update
     */
    int updateWorkStatusByLotId(UpdateWorkStatusByLotIdDto dto) throws Exception;

    /**
     * Updates work status for batch end by LotId.
     *
     * @param dto UpdateWorkStatusForBatchEndByLotIdDto containing update details
     * @return Number of rows affected
     * @throws Exception if an error occurs during update
     */
    int updateWorkStatusForBatchEndByLotId(UpdateWorkStatusForBatchEndByLotIdDto dto) throws Exception;

    int updateEventNmByCarrId(UpdateEventNmByCarrIdDto dto) throws Exception;

    int updateEventNmByLotId(UpdateEventNmByLotIdDto dto) throws Exception;

    int updateTrackInCnfmYn(String siteId, String cid, String tid, String userId, String lotId, String trackInCnfmYn) throws Exception;

    int updateRcpChkYn(WipStatServiceDto dto) throws Exception;

    int clearAllChkFlag(WipStatServiceDto dto) throws Exception;

    int updateWipStatForMoveCompleteByCarrId(updateWipStatForMoveCompleteByCarrIdDto dto) throws Exception;

    int updateWipStatForMoveCancelByCarrId(updateWipStatForMoveCancelByCarrIdDto dto) throws Exception;

    int updateCurrentEqpPortByCarrId(UpdateCurrentEqpPortByCarrIdDto dto) throws Exception;

    int updateDspWorkRepBatch(UpdateDspWorkRepBatchDto dto) throws Exception;

    int updateDspWorkRepNormal(UpdateDspWorkRepNormalDto dto) throws Exception;

    int updateSelfInspInfo(UpdateSelfInspInfoDto dto) throws Exception;

    int updateSampleInfo(UpdateSampleInfoDto dto) throws Exception;

    int updateInitWipStat(String siteId, String cid, String carrId) throws Exception;


}
