package com.abs.wfs.workman.function.jpa;

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
public class WnStateRuleInfoTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("저장된 데이터 전체 조회")
    @Test
    public void getAllEntities() throws Exception {

        // Perform the POST request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.get("/staterule/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @DisplayName("저장된 데이터 중 사이트와 사용 여부 필터 조회")
    @Test
    public void getSiteAndUseStatCd() throws Exception {

        String site = "SVM";
        String useStatCd = "Usable";


        // Perform the POST request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/staterule/all/filter?siteId=%s&useStatCd=%s", site, useStatCd))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
}
