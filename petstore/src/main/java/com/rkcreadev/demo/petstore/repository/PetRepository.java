package com.rkcreadev.demo.petstore.repository;

import com.rkcreadev.demo.petstore.api.model.PetStatus;
import com.rkcreadev.demo.petstore.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Pet repository
 */
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findByPetStatusIn(List<PetStatus> petStatus);
}
