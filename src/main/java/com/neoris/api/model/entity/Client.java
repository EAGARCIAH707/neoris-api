package com.neoris.api.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
public class Client extends Person implements Serializable {

    @Generated(GenerationTime.INSERT)
    @Column(name = "client_code")
    private Integer clientCode;

    @Basic
    @Column(name = "person_id")
    private Integer personId;


    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "status")
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Client client = (Client) o;
        return getPersonId() != null && Objects.equals(getPersonId(), client.getPersonId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @PrePersist
    void prePersist() {
        super.setCreatedOn(new Timestamp(System.currentTimeMillis()));
        super.setModifiedOn(new Timestamp(System.currentTimeMillis()));
        super.setIsDeleted(false);

    }

    @PreUpdate
    void preUpdate() {
        super.setModifiedOn(new Timestamp(System.currentTimeMillis()));
    }
}
