package cz.rb.bff_dogpaws.domain.model;

import cz.rb.projectcommon.model.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DogRestResponse {

    private Long id;
    private String name;
    private String breed;
    private GenderEnum gender;
    private String details;
    private Long ownerId;

}
