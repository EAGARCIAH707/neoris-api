package com.neoris.api.service.client;

import com.neoris.api.model.dto.ClientDto;
import com.neoris.api.model.dto.ReportDto;

import java.util.Date;
import java.util.List;

public interface IClientService {

    ClientDto create(ClientDto client);

    ClientDto update(ClientDto client, Integer clientId);

    Void delete(Integer clientId);

    List<ReportDto> generateReport(Integer clientId, Date from, Date to);

}
