package com.abs.wfs.workman.flow.eap;


import com.abs.wfs.workman.config.ApTestProperty;
import com.abs.wfs.workman.service.flow.eap.impl.WfsAlarmReportImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(ApTestProperty.local)
@Slf4j
public class ServiceNameTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WfsAlarmReportImpl wfsAlarmReport;


    @Before
    public void callBefore(){
        log.info("Before Test");
    }


    @After
    public void callAfter(){
        log.info("After test.");
    }

    @Test
    @DisplayName("Test display name")
    public void test(){
        log.info("Test");

    }


}
