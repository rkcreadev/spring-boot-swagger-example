package com.rkcreadev.demo.petstore.controller;

import com.rkcreadev.demo.petstore.api.controller.PetApi;
import com.rkcreadev.demo.petstore.api.model.DTOPet;
import com.rkcreadev.demo.petstore.api.model.ModelApiResponse;
import com.rkcreadev.demo.petstore.api.model.PetStatus;
import com.rkcreadev.demo.petstore.repository.PetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Pet controller
 */
@RestController
public class PetController implements PetApi {

    private PetRepository petRepository;

    /**
     * Autowiring constructor
     *
     * @param petRepository pet repository
     */
    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public ResponseEntity<ModelApiResponse> addPet(DTOPet body) {
        return getOkWithMessage("Successful ADD pet");
    }

    @Override
    public ResponseEntity<ModelApiResponse> deletePet(Long petId, String apiKey) {
        return getOkWithMessage("Successful DELETE pet");
    }

    @Override
    public ResponseEntity<List<DTOPet>> findPetsByStatus(List<String> statuses) {
        List<PetStatus> statusList = statuses.stream()
                .map(PetStatus::fromValue)
                .collect(Collectors.toList());

        petRepository.findByPetStatus(statusList);

        return null;
    }

    @Override
    public ResponseEntity<DTOPet> getPetById(Long petId) {
        return null;
    }

    @Override
    public ResponseEntity<ModelApiResponse> updatePet(DTOPet body) {
        return getOkWithMessage("Successful UPDATE pet");
    }

    private ResponseEntity<ModelApiResponse> getOkWithMessage(String msg) {
        ModelApiResponse modelApiResponse = new ModelApiResponse();
        modelApiResponse.setMessage(msg);
        modelApiResponse.setCode(200);
        return ResponseEntity.ok(modelApiResponse);
    }
}
