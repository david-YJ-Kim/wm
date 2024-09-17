package com.abs.wfs.workman.message;


import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.flow.eap.impl.WfsEqpStateReportImpl;
import com.abs.wfs.workman.spec.in.eap.WfsEqpStateReportIvo;
import com.abs.wfs.workman.util.code.UseYn;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.ws.rs.core.MediaType;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
@Slf4j
public class WfsEqpStateReportTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WfsEqpStateReportImpl wfsEqpStateReport;

    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;

    ObjectMapper mapper = new ObjectMapper()
            .configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true)
            .configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);



    @Test
    @DisplayName("Normal Test")
    public void normalTest() throws Exception {

        // Prepare for payload, WFS_EQP_STATE_REPORT
        String tid = "UnitTest_".concat(String.valueOf(System.currentTimeMillis()));
        WfsEqpStateReportIvo payload = mapper.readValue(WfsEqpStateReportIvo.sampleMessage, WfsEqpStateReportIvo.class);

        log.info("Send test message. {}", payload);

        String uriFormat = "%s%s?key=%s&scenario=%s";
        String uri = String.format(uriFormat, "/flow/eap/", WfsEqpStateReportIvo.cid, tid, "COMMON");

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(WfsEqpStateReportIvo.sampleMessage))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.ExecuteSuccessYn").value(UseYn.Y.name()))
        .andDo(MockMvcResultHandlers.print());
    }
}
