package cz.rb.bff_dogpaws.domain.messaging;

import cz.rb.bff_dogpaws.domain.mapper.reservation.ReservationMapper;
import cz.rb.bff_dogpaws.domain.model.ReservationRestResponse;
import cz.rb.projectcommon.model.reservation.ReservationListMessage;
import cz.rb.projectcommon.model.reservation.ReservationMessage;
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
public class ReservationMessenger {

    private final RestTemplate restTemplate;

    private final ReservationMapper mapper;


    @Value("${reservation-url}")
    private String reservationUrl;

    public List<ReservationRestResponse> getAllReservations() {
        log.info("ReservationMessenger | getAllReservations001 | starting request for all reservations");

        var response = getAllReservationsEndpoint();

        if (response == null || response.getReservationMessages() == null || response.getReservationMessages().isEmpty()) {
            return Collections.emptyList();
        }

        log.info("ReservationMessenger | getAllReservations002 | request finished with number of reservations: {}", response.getReservationMessages().size());
        return mapper.toRestResponse(response.getReservationMessages());
    }

    public ReservationRestResponse getReservation(final Long reservationId) {
        log.info("ReservationMessenger | getReservation001 | starting request for reservation with id: {}", reservationId);
        var response = getReservationByIdEndpoint(reservationId);
        log.info("ReservationMessenger | getReservation002 | request finished with reservation: {}", response);
        return mapper.toRestResponse(response);
    }

    public ReservationRestResponse addReservation(final ReservationMessage reservation) {
        log.info("ReservationMessenger | addReservation001 | starting request for adding reservation");
        var response = addReservationEndpoint(reservation);
        log.info("ReservationMessenger | addReservation002 | request finished with reservation: {}", response);
        return mapper.toRestResponse(response);
    }

    public void updateReservation(final Long reservationId, final ReservationMessage reservation) {
        log.info("ReservationMessenger | updateReservation001 | starting request for updating reservation with id: {}", reservationId);
        updateReservationEndpoint(reservationId, reservation);
        log.info("ReservationMessenger | updateReservation002 | request finished");
    }

    public void deleteReservation(final Long reservationId) {
        log.info("ReservationMessenger | deleteReservation001 | starting request for deleting reservation with id: {}", reservationId);
        deleteReservationEndpoint(reservationId);
        log.info("ReservationMessenger | deleteReservation002 | request finished");
    }

    private ReservationListMessage getAllReservationsEndpoint() {
        return restTemplate.getForObject(reservationUrl + ALL_RESERVATIONS_ENDPOINT, ReservationListMessage.class);
    }

    private ReservationMessage getReservationByIdEndpoint(final Long reservationId) {
        return restTemplate.getForObject(reservationUrl + RESERVATIONS_ENDPOINT + reservationId, ReservationMessage.class);
    }

    private ReservationMessage addReservationEndpoint(final ReservationMessage reservation) {
        return restTemplate.postForObject(reservationUrl + ADD_RESERVATIONS_ENDPOINT, reservation, ReservationMessage.class);
    }

    private void updateReservationEndpoint(final Long reservationId, final ReservationMessage reservation) {
        restTemplate.put(reservationUrl + RESERVATIONS_ENDPOINT + reservationId, reservation);
    }

    private void deleteReservationEndpoint(final Long reservationId) {
        restTemplate.delete(reservationUrl + RESERVATIONS_ENDPOINT + reservationId);
    }
}
