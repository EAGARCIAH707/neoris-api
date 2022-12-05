package com.neoris.api.repository.movement;

import com.neoris.api.model.dto.ReportDto;
import com.neoris.api.model.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface IMovementRepository extends JpaRepository<Movement, Integer> {

    Movement findByMovementIdAndIsDeleted(Integer movementId, Boolean isDeleted);

    Movement findFirstByOrderByDateDesc();

    @Query(value = "select new com.neoris.api.model.dto.ReportDto(m.date,c.name,a.number,a.type,a.initialAmount,a.status,m.balance)" +
            "FROM Movement m " +
            "JOIN Account a on m.accountId = a.accountId " +
            "JOIN Client c on a.clientId = a.clientId " +
            "WHERE c.clientCode = :clientId and m.date between :from and :to")
    List<ReportDto> findWithAccount(Integer clientId, Date from, Date to);
}
