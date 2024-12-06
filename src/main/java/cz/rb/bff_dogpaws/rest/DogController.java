package cz.rb.bff_dogpaws.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.rb.bff_dogpaws.domain.model.DogRestResponse;
import cz.rb.bff_dogpaws.domain.service.impl.DogService;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dogs")
@RequiredArgsConstructor
@Slf4j
public class DogController {

    private final DogService dogService;

    @GetMapping("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<DogRestResponse>> getAllDogs() throws JsonProcessingException {
        log.info("DogController | getAllDogs001 | starting request");
        var listOfDogs = dogService.getAllDogs();
        log.info("DogController | getAllDogs002 | request finished, dogs: {}", listOfDogs);
        return ResponseEntity.ok(listOfDogs);
    }

    @GetMapping("/{dogName}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<DogRestResponse> getDog(@PathVariable("dogName") final String dogName) throws JsonProcessingException {
        log.info("DogController | getDog001 | starting request for dog: {}", dogName);
        var dog = dogService.getDog(dogName);
        log.info("DogController | getDog002 | request finished, dog: {}", dog);
        return ResponseEntity.ok(dog);
    }
}
