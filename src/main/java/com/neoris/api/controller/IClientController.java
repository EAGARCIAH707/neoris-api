package com.neoris.api.controller;

import com.neoris.api.model.dto.ClientDto;
import com.neoris.api.model.dto.ReportDto;
import com.neoris.api.model.dto.ResponseErrorDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface IClientController {
    @Tag(name = "Create Client", description = "Service used to create a new client")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Client created successfully",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = ClientDto.class))),
                    @ApiResponse(
                            description = "Internal controlled error",
                            responseCode = "409",
                            content = @Content(schema = @Schema(implementation = ResponseErrorDto.class)))
            })
    ResponseEntity<ClientDto> createClient(ClientDto client);


    @Tag(name = "Update Client", description = "Service used to update a existing client")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Client updated successfully",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ClientDto.class))),
                    @ApiResponse(
                            description = "Internal controlled error",
                            responseCode = "409",
                            content = @Content(schema = @Schema(implementation = ResponseErrorDto.class)))
            })
    ResponseEntity<ClientDto> updateClient(Integer clientId, ClientDto client);


    @Tag(name = "Delete Client", description = "Service used to delete a existing client")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Client deleted successfully",
                            responseCode = "204",
                            content = @Content(schema = @Schema(implementation = Void.class))),
                    @ApiResponse(
                            description = "Internal controlled error",
                            responseCode = "409",
                            content = @Content(schema = @Schema(implementation = ResponseErrorDto.class)))
            })
    ResponseEntity<Void> deleteClient(Integer clientId);

    @Tag(name = "Generate Report", description = "Service used to update a generate report")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Successfully query",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ReportDto.class))),
                    @ApiResponse(
                            description = "Internal controlled error",
                            responseCode = "409",
                            content = @Content(schema = @Schema(implementation = ResponseErrorDto.class)))
            })
    ResponseEntity<List<ReportDto>> generateReport(Integer accountId, Date from, Date to);

}
