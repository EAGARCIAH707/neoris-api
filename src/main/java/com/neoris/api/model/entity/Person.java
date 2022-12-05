package com.neoris.api.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
public class Person {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "person_id")
    private Integer personId;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "gender")
    private String gender;

    @Basic
    @Column(name = "age")
    private Integer age;

    @Basic
    @Column(name = "identification")
    private String identification;

    @Basic
    @Column(name = "address")
    private String address;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "created_on")
    private Timestamp createdOn;

    @Basic
    @Column(name = "modified_on")
    private Timestamp modifiedOn;

    @Basic
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Person person = (Person) o;
        return personId != null && Objects.equals(personId, person.personId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
