package com.abs.wfs.workman.dao.query.common.service;

import com.abs.wfs.workman.dao.query.common.mapper.WfsCommonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WfsCommonServiceImpl implements WfsCommonService{


    @Autowired
    WfsCommonMapper wfsCommonMapper;

    /**
     * get Generate ID
     * @param name
     * @return
     */
    public String getID(String name) throws Exception{
        String returnVal = null;
        try {
            returnVal = wfsCommonMapper.getID(name);

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
        }
        return returnVal;
    }

}
