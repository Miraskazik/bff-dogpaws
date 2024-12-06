package cz.rb.bff_dogpaws.domain.messaging;

import cz.rb.bff_dogpaws.domain.mapper.dog.DogMapper;
import cz.rb.bff_dogpaws.domain.model.DogRestResponse;
import cz.rb.projectcommon.model.dog.DogListMessage;
import cz.rb.projectcommon.model.dog.DogMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static cz.rb.bff_dogpaws.domain.constant.DogpawsConstant.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class DogMessenger {

    private final DogMapper dogMapper;
    private final RestTemplate restTemplate;


    @Value("${dog-url}")
    private String dogUrl;


    public List<DogRestResponse> getDogs() {
        log.info("DogMessenger | getDogs001 | starting request");

        var response = getAllDogsEndpoint();

        if (response == null || response.getDogMessageList() == null || response.getDogMessageList().isEmpty()) {
            return Collections.emptyList();
        }

        log.info("DogMessenger | getDogs002 | request finished, dogs: {}", response);
        return dogMapper.toDogs(response.getDogMessageList());
    }

    public DogRestResponse getDog(final String dogName) {
        log.info("DogMessenger | getDog001 | starting request for dog: {}", dogName);

        DogMessage dog = getDogByNameEndpoint(dogName);
        log.info("DogMessenger | getDog002 | request finished, dog: {}", dog);

        return dogMapper.toDog(dog);
    }

    public DogRestResponse addDog(final DogMessage dogMessage) {
        log.info("DogMessenger | addDog001 | starting request for dog: {}", dogMessage);
        var response = addNewDogEndpoint(dogMessage);
        log.info("DogMessenger | addDog002 | request finished, dog: {}", dogMessage);
        return dogMapper.toDog(response);
    }


    private DogMessage getDogByNameEndpoint(final String dogName) {

        return restTemplate.getForObject(dogUrl + GET_DOG_ENDPOINT + dogName, DogMessage.class);
    }


    private DogMessage addNewDogEndpoint(final DogMessage dogMessage) {
        return restTemplate.postForObject(dogUrl + ADD_DOG_ENDPOINT, dogMessage, DogMessage.class);
    }

    private DogListMessage getAllDogsEndpoint() {

        return restTemplate.getForObject(dogUrl + ALL_DOGS_ENDPOINT, DogListMessage.class);
    }


}
