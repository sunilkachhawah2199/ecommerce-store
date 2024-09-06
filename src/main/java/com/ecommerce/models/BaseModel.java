package com.ecommerce.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

@MappedSuperclass
/* The @MappedSuperclass annotation in JPA is used to designate a class whose mapping information is applied to the entities that inherit from it. This means that the fields and mappings defined in the superclass are inherited by the subclasses, but the superclass itself is not an entity and does not have a corresponding table in the database.*/

@Getter
@Setter
public class BaseModel {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // auto increment
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
    private LocalDateTime deletedAt;
}
