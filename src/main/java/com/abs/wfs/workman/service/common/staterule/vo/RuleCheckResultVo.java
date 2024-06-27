package com.abs.wfs.workman.service.common.staterule.vo;

import lombok.Data;

import java.util.List;

@Data
public class RuleCheckResultVo {

    private boolean result;
    private final List<String> resultList;
}
