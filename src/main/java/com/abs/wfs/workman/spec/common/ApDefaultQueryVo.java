package com.abs.wfs.workman.spec.common;

import com.abs.wfs.workman.dao.query.lot.vo.QueryLotVo;
import com.abs.wfs.workman.dao.query.tool.vo.QueryEqpVo;
import com.abs.wfs.workman.dao.query.tool.vo.QueryPortVo;
import lombok.Data;


/**
 * 기본 쿼리 수행 결과 저장 구조체
 */
@Data
public class ApDefaultQueryVo {

    private QueryLotVo queryLotVo;  // Lot 기본 쿼리
    private QueryEqpVo queryEqpVo;  // 설비 기본 쿼리
    private QueryPortVo queryPortVo;  // 포트 기본 쿼리
}