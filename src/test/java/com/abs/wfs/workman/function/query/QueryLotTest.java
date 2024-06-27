package com.abs.wfs.workman.function.query;

import com.abs.wfs.workman.dao.query.lot.vo.QueryLotVo;
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
public class QueryLotTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Lot 상태 조회 테스트")
    public void queryToolCondition() throws Exception {
        String site = "SVM";
        String lotId = "S24200011";
        String useStatCd = "Usable";

        QueryLotVo vo = QueryLotVo.builder()
                            .siteId(site)
                            .lotId(lotId)
                            .useStatCd(useStatCd)
                            .build();

        mockMvc.perform(MockMvcRequestBuilders.get("/lot/query/condition")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lotId").value(lotId))
                .andDo(MockMvcResultHandlers.print());
    }

}
