package com.abs.wfs.workman.flow.eap;


import com.abs.wfs.workman.config.ApTestProperty;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.flow.eap.impl.WfsAlarmReportImpl;
import com.abs.wfs.workman.spec.in.eap.WfsTrayLoadCompIvo;
import com.abs.wfs.workman.util.code.UseYn;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(ApTestProperty.local)
@Slf4j
public class WfsTrayLoadCompImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WfsAlarmReportImpl wfsAlarmReport;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;


    @Before
    public void callBefore(){
        log.info("Before Test");
    }


    @After
    public void callAfter(){
        log.info("After test.");
    }

    @Test
    @DisplayName("1. Interface test")
    public void interfaceTest() throws Exception {
        log.info("Start interface test.");

        String eqpId = "AM-RE-00-01";
        String portId = "AM-RE-00-01-BP01";
        String carrId = "Tray_00001";
        String prodMtrlId = "ET24400043";

        WfsTrayLoadCompIvo.Body body = new WfsTrayLoadCompIvo.Body();
        body.setEqpId(eqpId); body.setProdMtrlId(prodMtrlId); body.setPortId(portId); body.setCarrId(carrId);


        mockMvc.perform(MockMvcRequestBuilders.post("/wm/flow/eap/" + WfsTrayLoadCompIvo.cid + "?key=testTrackinKey&scenario=COMMON")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.apPayloadGenerateService.generateBody("TestTid", body)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.ExecuteSuccessYn").value(UseYn.Y.name()))
        .andDo(MockMvcResultHandlers.print());

    }


}
