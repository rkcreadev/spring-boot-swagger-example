package com.rkcreadev.demo.petstore.mapping;

import com.rkcreadev.demo.petstore.api.model.DTOPet;
import com.rkcreadev.demo.petstore.model.Pet;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

/**
 * Pet mapper
 */
@Mapper
public interface PetMapper {

    @Mappings({})
    Pet fromDTO(DTOPet dtoPet);

    @Mappings({})
    DTOPet toDTO(Pet pet);

    @AfterMapping
    default void setDtoStatus(Pet pet, @MappingTarget DTOPet dtoPet) {
        dtoPet.setStatus(pet.getPetStatus());
    }

    @AfterMapping
    default void setPetStatus(DTOPet dtoPet, @MappingTarget Pet pet) {
        pet.setPetStatus(dtoPet.getStatus());
    }
}
