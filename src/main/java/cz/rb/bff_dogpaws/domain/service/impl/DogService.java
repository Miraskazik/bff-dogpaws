package cz.rb.bff_dogpaws.domain.service.impl;

import cz.rb.bff_dogpaws.domain.messaging.DogMessenger;
import cz.rb.bff_dogpaws.domain.model.DogRestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DogService {

    private final DogMessenger dogMessenger;

    public List<DogRestResponse> getAllDogs() {
        return dogMessenger.getDogs();
    }

    public DogRestResponse getDog(final String dogName) {
        return dogMessenger.getDog(dogName);
    }
}
