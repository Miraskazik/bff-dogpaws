package cz.rb.bff_dogpaws.domain.mapper.reservation;

import cz.rb.bff_dogpaws.domain.model.ReservationRestResponse;
import cz.rb.projectcommon.model.reservation.ReservationMessage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ReservationMapper {

    ReservationRestResponse toRestResponse(final ReservationMessage reservationMessage);

    ReservationMessage toMessage(final ReservationRestResponse reservationRestResponse);

    List<ReservationMessage> toMessage(final List<ReservationRestResponse> reservationRestResponse);

    List<ReservationRestResponse> toRestResponse(final List<ReservationMessage> reservationMessage);


}
