package cz.rb.bff_dogpaws.domain.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.rb.bff_dogpaws.domain.mapper.DogMapper;
import cz.rb.bff_dogpaws.domain.model.DogRestResponse;
import cz.rb.projectcommon.model.DogListMessage;
import cz.rb.projectcommon.model.DogMessage;
import cz.rb.projectcommon.service.MessagingFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static cz.rb.bff_dogpaws.domain.constant.DogpawsConstant.ALL_DOGS_ENDPOINT;
import static cz.rb.bff_dogpaws.domain.constant.DogpawsConstant.DOG_ENDPOINT;

@Slf4j
@Component
@RequiredArgsConstructor
public class DogMessenger {

    private final MessagingFactory messagingFactory;
    private final ObjectMapper objectMapper;
    private final DogMapper dogMapper;
    private final RestTemplate restTemplate;


    public List<DogRestResponse> getDogs() {
        log.info("DogMessenger | getDogs001 | starting request");

        var response = getAllDogs();

        // check response
//        messagingFactory.checkResponse(response);

        DogListMessage dogs = response;

        if (dogs == null || dogs.getDogMessageList() == null || dogs.getDogMessageList().isEmpty()) {
            return Collections.emptyList();
        }

        log.info("DogMessenger | getDogs002 | request finished, dogs: {}", dogs);
        return dogMapper.toDogs(response.getDogMessageList());
    }

    public DogRestResponse getDog(final String dogName) {
        log.info("DogMessenger | getDog001 | starting request for dog: {}", dogName);
        var response = getDogByName(dogName);
        // check response
//        messagingFactory.checkResponse(response);

        DogMessage dog = response;
        log.info("DogMessenger | getDog002 | request finished, dog: {}", dog);

        return dogMapper.toDog(dog);
    }

    @Value("${dog-url}")
    private String dogUrl;

    private DogMessage getDogByName(final String dogName) {

        return restTemplate.getForObject(dogUrl + DOG_ENDPOINT + dogName, DogMessage.class);
    }

    private DogListMessage getAllDogs() {

        return restTemplate.getForObject(dogUrl + ALL_DOGS_ENDPOINT, DogListMessage.class);
    }


}
