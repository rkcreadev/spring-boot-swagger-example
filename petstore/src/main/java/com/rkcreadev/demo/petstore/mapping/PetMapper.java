package com.rkcreadev.demo.petstore.mapping;

import com.rkcreadev.demo.petstore.api.model.DTOPet;
import com.rkcreadev.demo.petstore.model.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

/**
 * Pet mapper
 */
@Mapper
public interface PetMapper {

    // TODO: add status mapping
    @Mappings({})
    Pet fromDTO(DTOPet dtoPet);
}
