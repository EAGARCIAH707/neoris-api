package com.neoris.api.controller.impl;

import com.neoris.api.model.dto.ReportDto;
import com.neoris.api.model.entity.Client;
import com.neoris.api.repository.client.impl.IClientRepositoryFacade;
import com.neoris.api.repository.movement.impl.IMovementRepositoryFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MimeTypeUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ClientIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IClientRepositoryFacade clientRepository;

    @MockBean
    private IMovementRepositoryFacade movementRepository;

    @Test
    void test() throws Exception {

        Client client = new Client();
        var report = new ArrayList<ReportDto>();
        var reportDto = new ReportDto();
        reportDto.setClient("1");
        reportDto.setAccount("1");
        reportDto.setInitialAmount(BigDecimal.valueOf(1000));
        reportDto.setAccountType("CREDITO");
        report.add(reportDto);

        String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
        String fromString = "2022-01-01 00:00:00";
        String toString = "2022-12-12 00:00:00";
        DateFormat formatter = new SimpleDateFormat(DEFAULT_PATTERN);
        Date from = formatter.parse(fromString);
        Date to = formatter.parse(toString);


        given(clientRepository.findById(1)).willReturn(client);
        given(movementRepository.findByClientAndDate(1, from, to)).willReturn(report);

        mockMvc.perform(
                        get("/v0/clients/1/report?from=2022-01-01&to=2022-12-12")
                                .accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].client").value("1"))
                .andExpect(jsonPath("$[*].accountType").value("CREDITO"))
                .andReturn();
    }
}