package com.rkcreadev.demo.petstore.controller;

import com.rkcreadev.demo.petstore.api.controller.PetApi;
import com.rkcreadev.demo.petstore.api.model.DTOPet;
import com.rkcreadev.demo.petstore.api.model.ModelApiResponse;
import com.rkcreadev.demo.petstore.api.model.PetStatus;
import com.rkcreadev.demo.petstore.mapping.PetMapper;
import com.rkcreadev.demo.petstore.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Pet controller
 */
@RestController
public class PetController implements PetApi {

    private PetRepository petRepository;
    private PetMapper petMapper;

    /**
     * Autowiring constructor
     *
     * @param petRepository pet repository
     * @param petMapper pet mapper
     */
    @Autowired
    public PetController(PetRepository petRepository, PetMapper petMapper) {
        this.petRepository = petRepository;
        this.petMapper = petMapper;
    }

    @Override
    public ResponseEntity<List<DTOPet>> findAllPets() {
        List<DTOPet> dtoPetList = petRepository.findAll()
                .stream()
                .map(petMapper::toDTO).collect(Collectors.toList());

        return ResponseEntity.ok(dtoPetList);
    }

    @Override
    public ResponseEntity<ModelApiResponse> addPet(DTOPet body) {
        if (petRepository.exists(body.getId())) {
            return getResponseWithMessage(HttpStatus.BAD_REQUEST, "Pet with this ID already exist");
        }
        petRepository.saveAndFlush(petMapper.fromDTO(body));
        return getResponseWithMessage(HttpStatus.OK, "Successful ADD pet");
    }

    @Override
    public ResponseEntity<ModelApiResponse> deletePet(Long petId) {
        if (!petRepository.exists(petId)) {
            return getResponseWithMessage(HttpStatus.NOT_FOUND, "Pet with this ID not found!");
        }
        petRepository.delete(petId);
        return getResponseWithMessage(HttpStatus.OK, "Successful DELETE pet");
    }

    @Override
    public ResponseEntity<List<DTOPet>> findPetsByStatus(List<String> statuses) {
        List<PetStatus> statusList = statuses.stream()
                .map(PetStatus::fromValue)
                .collect(Collectors.toList());

        List<DTOPet> dtoPetList = petRepository.findByPetStatusIn(statusList)
                .stream()
                .map(petMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtoPetList);
    }

    @Override
    public ResponseEntity<DTOPet> getPetById(Long petId) {
        return ResponseEntity.ok(petMapper.toDTO(petRepository.findOne(petId)));
    }

    @Override
    public ResponseEntity<ModelApiResponse> updatePet(Long petId, DTOPet body) {
        if (!petRepository.exists(petId) || !Objects.equals(body.getId(), petId)) {
            return getResponseWithMessage(HttpStatus.NOT_FOUND, "Pet with this ID not found!");
        }
        petRepository.saveAndFlush(petMapper.fromDTO(body));
        return getResponseWithMessage(HttpStatus.OK, "Successful UPDATE pet");
    }

    private ResponseEntity<ModelApiResponse> getResponseWithMessage(HttpStatus status, String msg) {
        ModelApiResponse modelApiResponse = new ModelApiResponse();
        modelApiResponse.setMessage(msg);
        modelApiResponse.setCode(status.value());
        return ResponseEntity.status(status).body(modelApiResponse);
    }

}
