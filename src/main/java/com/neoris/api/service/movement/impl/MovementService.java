package com.neoris.api.service.movement.impl;

import com.neoris.api.model.dto.MovementDto;
import com.neoris.api.model.entity.Movement;
import com.neoris.api.model.enums.MovementType;
import com.neoris.api.repository.account.impl.IAccountRepositoryFacade;
import com.neoris.api.repository.movement.impl.IMovementRepositoryFacade;
import com.neoris.api.service.movement.IMovementService;
import com.neoris.api.util.error.ConflictException;
import com.neoris.api.util.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;

import static com.neoris.api.util.Util.converterObject;
import static com.neoris.api.util.Util.mergeObjects;

@Service
@RequiredArgsConstructor
public class
MovementService implements IMovementService {
    private final IMovementRepositoryFacade movementRepository;

    private final IAccountRepositoryFacade accountRepository;

    @Override
    public MovementDto create(MovementDto movement) {
        var account = accountRepository.findById(movement.getAccountId());

        if (ObjectUtils.isEmpty(account)) {
            throw new NotFoundException("Account not found");
        }
        var lastMovement = movementRepository.findLast();
        if (ObjectUtils.isEmpty(lastMovement)) {
            lastMovement = Movement.builder()
                    .balance(account.getInitialAmount())
                    .build();
        }
        Movement entity = operation(lastMovement, movement);
        entity.setAccountId(movement.getAccountId());
        entity.setDate(new Date());
        entity.setValue(movement.getValue());
        entity = movementRepository.create(entity);

        return converterObject(entity, MovementDto.class);
    }

    @Override
    public MovementDto update(MovementDto movement, Integer movementId) {
        var movementById = getIfExists(movementId);
        var last = movementRepository.findLast();
        if (!last.getMovementId().equals(movementById.getMovementId())) {
            throw new ConflictException("Only last movement can be updated");
        }
        var type = MovementType.valueOf(movementById.getType());

        if (type.equals(MovementType.DEBITO)) {
            movementById.setBalance(movementById.getBalance().add(movementById.getValue().negate()));
        } else {
            movementById.setBalance(movementById.getBalance().subtract(movementById.getValue()));
        }

        var movementUpdate = operation(movementById, movement);
        movementUpdate.setMovementId(movementById.getMovementId());
        movementUpdate.setValue(movement.getValue());
        mergeObjects(movementUpdate, movementById);
        var result = movementRepository.update(movementById);

        return converterObject(result, MovementDto.class);
    }

    @Override
    public Void delete(Integer movementId) {
        var movement = getIfExists(movementId);
        movement.setIsDeleted(true);
        movementRepository.update(movement);

        return null;
    }

    private Movement getIfExists(Integer movementId) {
        var movement = movementRepository.findById(movementId);

        if (ObjectUtils.isEmpty(movement)) {
            throw new NotFoundException("Movement not found");
        }
        return movement;
    }

    private Movement debit(Movement movement, MovementDto movementDto) {
        var result = movement.getBalance().subtract(movementDto.getValue().negate());
        return Movement.builder()
                .balance(result)
                .type(MovementType.DEBITO.name())
                .build();
    }

    private Movement credit(Movement movement, MovementDto movementDto) {
        var result = movement.getBalance().add(movementDto.getValue());

        if (result.signum() < 0) {
            throw new ConflictException("Amount not available");
        }
        return Movement.builder()
                .balance(result)
                .type(MovementType.CREDITO.name())
                .build();
    }

    private Movement operation(Movement movement, MovementDto movementDto) {
        if (movementDto.getValue().signum() > 0) {
            return credit(movement, movementDto);
        }
        if (movementDto.getValue().signum() < 0) {
            return debit(movement, movementDto);
        }

        throw new ConflictException("Amount not available");
    }
}
