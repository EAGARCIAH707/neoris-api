package com.neoris.api.model.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Account {

    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account account = (Account) o;
        return accountId != null && Objects.equals(accountId, account.accountId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
