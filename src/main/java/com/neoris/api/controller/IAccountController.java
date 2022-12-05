package com.neoris.api.controller;

import com.neoris.api.model.dto.AccountDto;
import com.neoris.api.model.dto.ResponseErrorDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

public interface IAccountController {
    @Tag(name = "Create Account", description = "Service used to create a new account")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Account created successfully",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = AccountDto.class))),
                    @ApiResponse(
                            description = "Internal controlled error",
                            responseCode = "409",
                            content = @Content(schema = @Schema(implementation = ResponseErrorDto.class)))
            })
    ResponseEntity<AccountDto> createAccount(AccountDto account);


    @Tag(name = "Update Account", description = "Service used to update a existing account")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Account updated successfully",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = AccountDto.class))),
                    @ApiResponse(
                            description = "Internal controlled error",
                            responseCode = "409",
                            content = @Content(schema = @Schema(implementation = ResponseErrorDto.class)))
            })
    ResponseEntity<AccountDto> updateAccount(Integer accountId, AccountDto account);


    @Tag(name = "Delete Account", description = "Service used to delete a existing account")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Account deleted successfully",
                            responseCode = "204",
                            content = @Content(schema = @Schema())),
                    @ApiResponse(
                            description = "Internal controlled error",
                            responseCode = "409",
                            content = @Content(schema = @Schema(implementation = ResponseErrorDto.class)))
            })
    ResponseEntity<Void> deleteAccount(Integer accountId);

}
