package com.rkcreadev.demo.petstore;

import com.rkcreadev.demo.petstore.api.config.SwaggerConfig;
import com.rkcreadev.demo.petstore.api.model.PetStatus;
import com.rkcreadev.demo.petstore.model.Pet;
import com.rkcreadev.demo.petstore.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

@SpringBootApplication
@Import(SwaggerConfig.class)
public class PetStoreApplication extends SpringBootServletInitializer implements CommandLineRunner {

    private PetRepository petRepository;

    @Autowired
    public PetStoreApplication(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PetStoreApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(PetStoreApplication.class, args);
    }

    @Override
    public void run(String... strings) {
        Pet pet1 = new Pet();
        pet1.setPetStatus(PetStatus.ALIVE);
        pet1.setBirthDate(LocalDateTime.of(1995,7,1,1,0));
        pet1.setName("Cat");

        Pet pet2 = new Pet();
        pet2.setPetStatus(PetStatus.FORGOTTEN);
        pet2.setBirthDate(LocalDateTime.of(1997,7,1,1,0));
        pet2.setName("Mouse");

        Pet pet3 = new Pet();
        pet3.setPetStatus(PetStatus.DEAD);
        pet3.setBirthDate(LocalDateTime.of(1999,7,1,1,0));
        pet3.setName("Dog");

        Pet pet4 = new Pet();
        pet4.setPetStatus(PetStatus.ALIVE);
        pet4.setBirthDate(LocalDateTime.of(2001,7,1,1,0));
        pet4.setName("Eagle");

        Pet pet5 = new Pet();
        pet5.setPetStatus(PetStatus.FORGOTTEN);
        pet5.setBirthDate(LocalDateTime.now());
        pet5.setName("Human");

        Pet pet6 = new Pet();
        pet6.setPetStatus(PetStatus.DEAD);
        pet6.setBirthDate(LocalDateTime.of(2003,7,1,1,0));
        pet6.setName("Chupakabra");

        petRepository.saveAndFlush(pet1);
        petRepository.saveAndFlush(pet2);
        petRepository.saveAndFlush(pet3);
        petRepository.saveAndFlush(pet4);
        petRepository.saveAndFlush(pet5);
        petRepository.saveAndFlush(pet6);
    }
}
