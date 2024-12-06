package cz.rb.bff_dogpaws.rest;

import cz.rb.bff_dogpaws.domain.model.DogRestResponse;
import cz.rb.bff_dogpaws.domain.service.impl.DogService;
import cz.rb.projectcommon.model.dog.DogMessage;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dogs")
@RequiredArgsConstructor
@Slf4j
public class DogController {

    private final DogService dogService;

    @GetMapping("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<DogRestResponse>> getAllDogs() {
        log.info("DogController | getAllDogs001 | starting request");
        var listOfDogs = dogService.getAllDogs();
        log.info("DogController | getAllDogs002 | request finished, dogs: {}", listOfDogs);
        return ResponseEntity.ok(listOfDogs);
    }

    @GetMapping("/{dogName}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<DogRestResponse> getDog(@PathVariable("dogName") final String dogName) {
        log.info("DogController | getDog001 | starting request for dog: {}", dogName);
        var dog = dogService.getDog(dogName);
        log.info("DogController | getDog002 | request finished, dog: {}", dog);
        return ResponseEntity.ok(dog);
    }

    @PostMapping("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<DogRestResponse> addDog(@RequestBody final DogMessage dogName) {
        log.info("DogController | addDog001 | starting request for dog: {}", dogName);
        var response = dogService.addDog(dogName);
        log.info("DogController | addDog002 | request finished, new dog saver: {}", response);
        return ResponseEntity.ok(response);
    }
}
