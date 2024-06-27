package com.abs.wfs.workman.function.query;

import com.abs.wfs.workman.dao.query.tool.vo.QueryEqpVo;
import com.abs.wfs.workman.dao.query.tool.vo.QueryPortVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
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
@ActiveProfiles("local")
@Slf4j
public class QueryToolTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("EQP 상태 조회 테스트")
    public void queryToolCondition() throws Exception {
        String site = "SVM";
        String eqpId = "AP-LA-03-01";
        String useStatCd = "Usable";

        QueryEqpVo vo = QueryEqpVo.builder()
                            .siteId(site)
                            .eqpId(eqpId)
                            .useStatCd(useStatCd)
                            .build();

        mockMvc.perform(MockMvcRequestBuilders.get("/tool/query/condition/eqp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.eqpId").value(eqpId))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Port 상태 조회 테스트")
    public void queryPortCondition() throws Exception {
        String site = "SVM";
        String eqpId = "AP-TG-08-01";
        String portId = "AP-TG-08-01-BP01";
        String useStatCd = "Usable";

        QueryPortVo vo = QueryPortVo.builder()
                .siteId(site)
                .eqpId(eqpId)
                .portId(portId)
                .useStatCd(useStatCd)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.get("/tool/query/condition/port")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.portId").value(portId))
                .andDo(MockMvcResultHandlers.print());
    }
}
