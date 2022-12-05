package com.neoris.api.repository.movement.impl;

import com.neoris.api.model.dto.ReportDto;
import com.neoris.api.model.entity.Movement;

import java.util.Date;
import java.util.List;

public interface IMovementRepositoryFacade {
    Movement create(Movement movement);

    Movement findById(Integer movementId);

    Movement update(Movement movement);

    Movement findLast();


    List<ReportDto> findByClientAndDate(Integer clientId, Date from, Date to);

}
