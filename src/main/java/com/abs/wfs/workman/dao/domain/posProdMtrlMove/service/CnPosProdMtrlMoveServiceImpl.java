package com.abs.wfs.workman.dao.domain.posProdMtrlMove.service;

import com.abs.wfs.workman.dao.domain.common.repository.DBCommonDateRepository;
import com.abs.wfs.workman.dao.domain.posProdMtrlMove.model.ChPosProdMtrlMove;
import com.abs.wfs.workman.dao.domain.posProdMtrlMove.model.CnPosProdMtrlMove;
import com.abs.wfs.workman.dao.domain.posProdMtrlMove.repository.ChPosProdMtrlMoveRepository;
import com.abs.wfs.workman.dao.domain.posProdMtrlMove.repository.CnPosProdMtrlMoveRepository;
import com.abs.wfs.workman.util.CommonDate;
import com.abs.wfs.workman.util.WorkManMessageList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CnPosProdMtrlMoveServiceImpl {

    @Autowired
    private CnPosProdMtrlMoveRepository cnPosProdMtrlMoveRepository;

    @Autowired
    private ChPosProdMtrlMoveRepository chPosProdMtrlMoveRepository;

    @Autowired
    private DBCommonDateRepository dbCommonDateRepository;

    public void saveCnPosProdMtrlMoveRepository(CnPosProdMtrlMove cnPosProdMtrlMove) {

        Timestamp workTime = dbCommonDateRepository.getCurrrentDbTimestamp();

        cnPosProdMtrlMove.setMdfyDt(cnPosProdMtrlMove.getMdfyDt() != null ? cnPosProdMtrlMove.getMdfyDt() : workTime);
        cnPosProdMtrlMove.setCrtDt(cnPosProdMtrlMove.getCrtDt() != null ? cnPosProdMtrlMove.getCrtDt() : workTime);
        cnPosProdMtrlMove.setCrtUserId(cnPosProdMtrlMove.getCrtUserId() != null ? cnPosProdMtrlMove.getCrtUserId() : cnPosProdMtrlMove.getMdfyUserId());
        cnPosProdMtrlMove.setFnlEvntDt(cnPosProdMtrlMove.getFnlEvntDt() != null ? cnPosProdMtrlMove.getFnlEvntDt() : workTime);

        switch(cnPosProdMtrlMove.getEvntNm()) {
            case WorkManMessageList.WFS_PANEL_MOVE_OUT:
                cnPosProdMtrlMove.setCarrOutDt(workTime);
                break;
            case WorkManMessageList.WFS_PANEL_MOVE_IN:
                cnPosProdMtrlMove.setCarrInDt(workTime);
                break;
            case WorkManMessageList.WFS_EQP_PANEL_IMPORT:
                cnPosProdMtrlMove.setEqpImportDt(workTime);
                break;
            case WorkManMessageList.WFS_EQP_PANEL_EXPORT:
                cnPosProdMtrlMove.setEqpExportDt(workTime);
                break;
        }

        cnPosProdMtrlMoveRepository.save(cnPosProdMtrlMove);

        // history
        ChPosProdMtrlMove chPosProdMtrlMove = new ChPosProdMtrlMove(cnPosProdMtrlMove);
        chPosProdMtrlMoveRepository.save(chPosProdMtrlMove);
    }

    public Optional<CnPosProdMtrlMove> findByProdMtrlId(String prodMtrlId) {
        return this.cnPosProdMtrlMoveRepository.findByProdMtrlId(prodMtrlId);
    }
}
