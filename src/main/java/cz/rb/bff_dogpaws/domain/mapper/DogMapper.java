package cz.rb.bff_dogpaws.domain.mapper;


import cz.rb.bff_dogpaws.domain.model.DogRestResponse;
import cz.rb.projectcommon.model.DogMessage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {GenderMapper.class})
public interface DogMapper {


//    List<DogMessage> toDogMessages(List<Dog> dogModel);

//    DogMessage toDogMessage(Dog dogModel);

    List<DogRestResponse> toDogs(List<DogMessage> messages);

    DogRestResponse toDog(DogMessage message);
}
