package com.neoris.api.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
public class Account {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "account_id")
    private Integer accountId;

    @Basic
    @Column(name = "number")
    private String number;

    @Basic
    @Column(name = "type")
    private String type;

    @Basic
    @Column(name = "initial_amount")
    private BigDecimal initialAmount;

    @Basic
    @Column(name = "status")
    private Boolean status;

    @Basic
    @Column(name = "created_on")
    private Timestamp createdOn;

    @Basic
    @Column(name = "modified_on")
    private Timestamp modifiedOn;

    @Basic
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Basic
    @Column(name = "client_code")
    private Integer clientId;

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
}
