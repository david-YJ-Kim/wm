package com.abs.wfs.workman.dao.domain.common.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Timestamp;

@Repository
public class DBCommonDateRepository {

    @Autowired
    EntityManager entityManager;

    // Oracle DB의 시간을 Timestamp로 리턴.
    public Timestamp getCurrrentDbTimestamp() {
        Query query = entityManager.createNativeQuery("SELECT TO_CHAR(SYSTIMESTAMP, 'YYYY-MM-DD HH24:MI:SS.FF3') FROM DUAL");
        Object obj = query.getSingleResult();
        String strTs = obj.toString();

        Timestamp rtnTs = Timestamp.valueOf(strTs);
        return rtnTs;
    }
}
