package com.neoris.api.controller;

import com.neoris.api.model.dto.MovementDto;
import com.neoris.api.model.dto.ResponseErrorDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

public interface IMovementController {

    @Tag(name = "Create Movement", description = "Service used to create a new movement")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Movement created successfully",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = MovementDto.class))),
                    @ApiResponse(
                            description = "Internal controlled error",
                            responseCode = "409",
                            content = @Content(schema = @Schema(implementation = ResponseErrorDto.class)))
            })
    ResponseEntity<MovementDto> createMovement(MovementDto movement);


    @Tag(name = "Update Movement", description = "Service used to update a existing movement")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Movement updated successfully",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = MovementDto.class))),
                    @ApiResponse(
                            description = "Internal controlled error",
                            responseCode = "409",
                            content = @Content(schema = @Schema(implementation = ResponseErrorDto.class)))
            })
    ResponseEntity<MovementDto> updateMovement(Integer movementId, MovementDto movement);


    @Tag(name = "Delete Movement", description = "Service used to delete a existing movement")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Account movement successfully",
                            responseCode = "204",
                            content = @Content(schema = @Schema())),
                    @ApiResponse(
                            description = "Internal controlled error",
                            responseCode = "409",
                            content = @Content(schema = @Schema(implementation = ResponseErrorDto.class)))
            })
    ResponseEntity<Void> deleteMovement(Integer movementId);
}
