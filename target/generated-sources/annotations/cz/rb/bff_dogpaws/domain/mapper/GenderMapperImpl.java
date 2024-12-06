package cz.rb.bff_dogpaws.domain.mapper;

import cz.rb.projectcommon.model.GenderEnum;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-06T21:00:20+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class GenderMapperImpl implements GenderMapper {

    @Override
    public GenderEnum toGenderEnum(String gender) {
        if ( gender == null ) {
            return null;
        }

        GenderEnum genderEnum;

        switch ( gender ) {
            case "INVALID": genderEnum = GenderEnum.INVALID;
            break;
            case "MALE": genderEnum = GenderEnum.MALE;
            break;
            case "FEMALE": genderEnum = GenderEnum.FEMALE;
            break;
            default: genderEnum = GenderEnum.INVALID;
        }

        return genderEnum;
    }

    @Override
    public String toGenderString(GenderEnum genderEnum) {
        if ( genderEnum == null ) {
            return null;
        }

        String string;

        switch ( genderEnum ) {
            case MALE: string = "MALE";
            break;
            case FEMALE: string = "FEMALE";
            break;
            case INVALID: string = "<ANY_UNMAPPED>";
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + genderEnum );
        }

        return string;
    }
}
