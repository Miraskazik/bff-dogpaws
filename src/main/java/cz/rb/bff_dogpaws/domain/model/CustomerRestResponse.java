package cz.rb.bff_dogpaws.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRestResponse {


    private Long id;

    private String firstName;

    private String lastName;

    private String city;

    private Integer price;

    private String details;

    private List<Long> dogsId;
}

