package com.neoris.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Movement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "movement_id")
    private Integer movementId;

    @Basic
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Basic
    @Column(name = "type")
    private String type;

    @Basic
    @Column(name = "value")
    private BigDecimal value;

    @Basic
    @Column(name = "balance")
    private BigDecimal balance;

    @Basic
    @Column(name = "account_id")
    private Integer accountId;

    @Basic
    @Column(name = "created_on")
    private Timestamp createdOn;

    @Basic
    @Column(name = "modified_on")
    private Timestamp modifiedOn;

    @Basic
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @PrePersist
    void prePersist() {
        this.createdOn = new Timestamp(System.currentTimeMillis());
        this.modifiedOn = new Timestamp(System.currentTimeMillis());
        this.isDeleted = false;

    }

    @PreUpdate
    void preUpdate() {
        this.modifiedOn = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Movement movement = (Movement) o;
        return movementId != null && Objects.equals(movementId, movement.movementId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
