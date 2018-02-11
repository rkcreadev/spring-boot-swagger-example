package com.rkcreadev.demo.petstore.model;

import com.rkcreadev.demo.petstore.api.model.PetStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Pet entity
 */
@Data
@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDateTime birthDate;

    @Enumerated(EnumType.STRING)
    private PetStatus petStatus;
}
