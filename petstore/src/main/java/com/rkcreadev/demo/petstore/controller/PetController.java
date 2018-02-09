package com.rkcreadev.demo.petstore.controller;

import com.rkcreadev.demo.petstore.api.controller.PetApi;
import com.rkcreadev.demo.petstore.api.model.ModelApiResponse;
import com.rkcreadev.demo.petstore.api.model.Pet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Pet controller
 */
@RestController
public class PetController implements PetApi {

    @Override
    public ResponseEntity<ModelApiResponse> addPet(Pet body) {
        return getOkWithMessage("Successful added pet");
    }

    @Override
    public ResponseEntity<ModelApiResponse> deletePet(Long petId, String apiKey) {
        return null;
    }

    @Override
    public ResponseEntity<List<Pet>> findPetsByStatus(List<String> status) {
        return null;
    }

    @Override
    public ResponseEntity<Pet> getPetById(Long petId) {
        return null;
    }

    @Override
    public ResponseEntity<ModelApiResponse> updatePet(Pet body) {
        return null;
    }

    @Override
    public ResponseEntity<ModelApiResponse> updatePetWithForm(Long petId, String name, String status) {
        return null;
    }

    private ResponseEntity<ModelApiResponse> getOkWithMessage(String msg) {
        ModelApiResponse modelApiResponse = new ModelApiResponse();
        modelApiResponse.setMessage(msg);
        modelApiResponse.setCode(200);
        return ResponseEntity.ok(modelApiResponse);
    }
}
