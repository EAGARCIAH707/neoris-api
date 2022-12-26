package com.neoris.api.controller.impl;

import com.neoris.api.controller.IMovementController;
import com.neoris.api.model.dto.MovementDto;
import com.neoris.api.service.movement.IMovementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Log4j2
@RestController
@RequestMapping(value = "/v0")
@RequiredArgsConstructor
public class MovementController implements IMovementController {

    private final IMovementService movementService;

    @Override
    @PostMapping("/movements")
    public ResponseEntity<MovementDto> createMovement(@Valid @RequestBody MovementDto movement) {
        var result = movementService.create(movement);
        log.info("create movement response: {}", movement);

        return new ResponseEntity<>(result, CREATED);
    }

    @Override
    @PatchMapping("/movements/{movementId}")
    public ResponseEntity<MovementDto> updateMovement(@PathVariable Integer movementId, @RequestBody MovementDto movement) {
        var result = movementService.update(movement, movementId);
        log.info("update movement response {}", result);

        return ResponseEntity.ok(result);
    }

    @Override
    @DeleteMapping("/movements/{movementId}")
    public ResponseEntity<Void> deleteMovement(@PathVariable Integer movementId) {
        var result = movementService.delete(movementId);
        log.info("successfully delete movement");

        return new ResponseEntity<>(result, NO_CONTENT);
    }
}
