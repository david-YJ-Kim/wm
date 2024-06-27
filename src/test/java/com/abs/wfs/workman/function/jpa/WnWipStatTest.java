package com.abs.wfs.workman.function.jpa;

import com.abs.wfs.workman.dao.domain.wipStat.vo.WnWipStatSaveRequestVo;
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

import java.sql.Timestamp;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
@Slf4j
public class WnWipStatTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("데이터 단건 저장")
    @Test
    public void saveEntity() throws Exception {
        String siteId = "SVM";
        String lotId = "S23C00010";
        String carrId = "-";
        String workStatCd = "Ready";
        String cstmEvntNm ="CreateWipStat";
        String evntNm ="CreateWipStat";
        String tid = "170982423839786399";
        String useStatCd = "Usable";
        String crntEqpId = "AP-LA-03-01";
        String crntPortId = "AP-LA-03-01-BP01";
        String crtUserId = "WFS";
        Timestamp crtDt = new Timestamp(System.currentTimeMillis());
        Timestamp mdfyDt = new Timestamp(System.currentTimeMillis());
        Timestamp fnlEvntDt = new Timestamp(System.currentTimeMillis());

        WnWipStatSaveRequestVo vo = WnWipStatSaveRequestVo.builder()
                .siteId(siteId)
                .lotId(lotId)
                .carrId(carrId)
                .workStatCd(workStatCd)
                .cstmEvntNm(cstmEvntNm)
                .evntNm(evntNm)
                .tid(tid)
                .useStatCd(useStatCd)
                .crntEqpId(crntEqpId)
                .crntPortId(crntPortId)
                .eqpChkYn("N")
                .rcpChkYn("N")
                .trackInCnfmYn("N")
                .selfInspYn("N")
                .smplLotYn("N")
                .crtUserId(crtUserId)
                .crtDt(crtDt)
                .mdfyDt(mdfyDt)
                .fnlEvntDt(fnlEvntDt)
                .build();



        // Perform the POST request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.post("/wip/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lotId").value(lotId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.carrId").value(carrId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.workStatCd").value(workStatCd));
    }

    @DisplayName("저장된 데이터 중 예약 설비와 포트 필터 조회")
    @Test
    public void getSiteAndUseStatCd() throws Exception {

        String site = "SVM";
        String eqpId = "AP-RD-04-01";
        String portId = "AP-RD-04-01-BP01";


        // Perform the POST request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/wip/reserved/filter?site=%s&eqpId=%s&portId=%s",site, eqpId, portId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
}
