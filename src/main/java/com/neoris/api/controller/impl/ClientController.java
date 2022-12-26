package com.neoris.api.controller.impl;

import com.neoris.api.controller.IClientController;
import com.neoris.api.model.dto.ClientDto;
import com.neoris.api.model.dto.ReportDto;
import com.neoris.api.service.client.IClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Log4j2
@RestController
@RequestMapping(value = "/v0")
@RequiredArgsConstructor
public class ClientController implements IClientController {
    private final IClientService clientService;

    @Override
    @PostMapping("/clients")
    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto client) {
        var result = clientService.create(client);
        log.info("create client response: {}", result);

        return new ResponseEntity<>(result, CREATED);
    }

    @Override
    @PatchMapping("/clients/{clientId}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Integer clientId, @RequestBody ClientDto client) {
        var result = clientService.update(client, clientId);
        log.info("update client response: {}", result);

        return ResponseEntity.ok(result);
    }

    @Override
    @DeleteMapping("/clients/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer clientId) {
        var result = clientService.delete(clientId);
        log.info("successfully delete client");

        return new ResponseEntity<>(result, NO_CONTENT);
    }

    @Override
    @GetMapping("/clients/{clientId}/report")
    public ResponseEntity<List<ReportDto>> generateReport(@PathVariable Integer clientId,
                                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
                                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
        var result = clientService.generateReport(clientId, from, to);
        log.info("client report response: {}", result);

        return ResponseEntity.ok(result);
    }
}
