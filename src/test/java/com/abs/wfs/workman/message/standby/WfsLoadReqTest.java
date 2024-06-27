//package com.abs.wfs.workman.message.standby;
//
//import com.abs.wfs.workman.service.flow.eap.impl.WfsLoadReqImpl;
//import com.abs.wfs.workman.spec.in.eap.WfsLoadReqVo;
//import com.abs.wfs.workman.util.code.UseYn;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("local")
//@Slf4j
//public class WfsLoadReqTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private WfsLoadReqImpl wfsLoadReqService;
//
//
//    @Test
//    @DisplayName("Standby | WFS_LOAD_REQ | Normal Test")
//    public void wfsLoadReq_NormalTest() throws Exception {
//
//        String eqpId = "AP-TG-08-01";
//        String portId = "AP-TG-08-01-BP01";
//        String portType = "BP";
//
//        String payload = WfsLoadReqVo.getMessageSampleFormat(eqpId, portId, portType);
//        log.info(payload);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/message/standby/wfLoadReq")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(payload))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.ExecuteSuccessYn").value(UseYn.Y.name()))
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//
//}
