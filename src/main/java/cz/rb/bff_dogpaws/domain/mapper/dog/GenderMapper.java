package cz.rb.bff_dogpaws.domain.mapper.dog;

import cz.rb.projectcommon.model.dog.GenderEnum;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.ValueMapping;

import static org.mapstruct.MappingConstants.ANY_REMAINING;
import static org.mapstruct.MappingConstants.ANY_UNMAPPED;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface GenderMapper {

    @ValueMapping(target = "INVALID", source = ANY_REMAINING)
    GenderEnum toGenderEnum(String gender);

    @ValueMapping(target = "MALE", source = "MALE")
    @ValueMapping(target = "FEMALE", source = "FEMALE")
    @ValueMapping(target = ANY_UNMAPPED, source = "INVALID")
    String toGenderString(GenderEnum genderEnum);
}
