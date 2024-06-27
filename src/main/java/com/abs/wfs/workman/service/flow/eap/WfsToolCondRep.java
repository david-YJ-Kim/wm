package com.abs.wfs.workman.service.flow.eap;

import com.abs.wfs.workman.service.flow.WfsMessageService;

public interface WfsToolCondRep extends WfsMessageService {
    void scenarioDispatch(String messageId) throws Exception;

}
