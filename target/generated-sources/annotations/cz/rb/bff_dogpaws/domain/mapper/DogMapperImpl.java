package cz.rb.bff_dogpaws.domain.mapper;

import cz.rb.bff_dogpaws.domain.model.DogRestResponse;
import cz.rb.projectcommon.model.DogMessage;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-06T21:00:20+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class DogMapperImpl implements DogMapper {

    @Override
    public List<DogRestResponse> toDogs(List<DogMessage> messages) {
        if ( messages == null ) {
            return null;
        }

        List<DogRestResponse> list = new ArrayList<DogRestResponse>( messages.size() );
        for ( DogMessage dogMessage : messages ) {
            list.add( toDog( dogMessage ) );
        }

        return list;
    }

    @Override
    public DogRestResponse toDog(DogMessage message) {
        if ( message == null ) {
            return null;
        }

        DogRestResponse.DogRestResponseBuilder dogRestResponse = DogRestResponse.builder();

        dogRestResponse.id( message.getId() );
        dogRestResponse.name( message.getName() );
        dogRestResponse.breed( message.getBreed() );
        dogRestResponse.gender( message.getGender() );
        dogRestResponse.details( message.getDetails() );
        dogRestResponse.ownerId( message.getOwnerId() );

        return dogRestResponse.build();
    }
}
