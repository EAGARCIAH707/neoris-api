package com.neoris.api.repository.movement.impl;

import com.neoris.api.model.dto.ReportDto;
import com.neoris.api.model.entity.Movement;
import com.neoris.api.repository.movement.IMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MovementRepository implements IMovementRepositoryFacade {

    private final IMovementRepository repository;

    @Override
    public Movement create(Movement movement) {
        return repository.save(movement);
    }

    @Override
    public Movement findById(Integer movementId) {
        return repository.findByMovementIdAndIsDeleted(movementId, false);
    }

    @Override
    public Movement update(Movement movement) {
        return repository.save(movement);
    }

    @Override
    public Movement findLast() {
        return repository.findFirstByOrderByDateDesc();
    }

    @Override
    public List<ReportDto> findByClientAndDate(Integer clientId, Date from, Date to) {
        return repository.findWithAccount(clientId, from, to);
    }
}
