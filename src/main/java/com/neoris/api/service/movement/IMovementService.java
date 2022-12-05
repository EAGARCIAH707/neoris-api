package com.neoris.api.service.movement;

import com.neoris.api.model.dto.MovementDto;

public interface IMovementService {

    MovementDto create(MovementDto movement);

    MovementDto update(MovementDto movement, Integer movementId);

    Void delete(Integer movementId);
}
