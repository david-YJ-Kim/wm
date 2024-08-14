package com.abs.wfs.workman.dao.domain.posProdMtrlMove.repository;

import com.abs.wfs.workman.dao.domain.posProdMtrlMove.model.CnPosProdMtrlMove;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CnPosProdMtrlMoveRepository extends JpaRepository<CnPosProdMtrlMove, String> {

    public Optional<CnPosProdMtrlMove> findByProdMtrlId(String prodMtrlId);
}
