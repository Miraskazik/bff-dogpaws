package cz.rb.bff_dogpaws.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRestResponse {

    private Long id;

    private LocalDateTime reservationDateStart;

    private LocalDateTime reservationDateEnd;

    private Long dogId;

    private Long ownerId;

    private Long fullPrice;

    private Integer numberOfDays;
}

