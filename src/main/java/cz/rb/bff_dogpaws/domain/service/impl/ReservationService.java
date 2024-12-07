package cz.rb.bff_dogpaws.domain.service.impl;

import cz.rb.bff_dogpaws.domain.messaging.ReservationMessenger;
import cz.rb.bff_dogpaws.domain.model.ReservationRestResponse;
import cz.rb.projectcommon.model.reservation.ReservationMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationMessenger messenger;

    public void deleteReservation(Long id) {
        messenger.deleteReservation(id);
    }

    public ReservationRestResponse saveReservation(ReservationMessage message) {
        return messenger.addReservation(message);
    }

    public void updateReservation(Long id, ReservationMessage message) {
        messenger.updateReservation(id, message);
    }

    public ReservationRestResponse getReservation(Long id) {
        return messenger.getReservation(id);
    }

    public List<ReservationRestResponse> getReservations() {
        return messenger.getAllReservations();
    }
}
