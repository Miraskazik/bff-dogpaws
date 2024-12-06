package cz.rb.bff_dogpaws.domain.mapper.customer;

import cz.rb.bff_dogpaws.domain.model.CustomerRestResponse;
import cz.rb.projectcommon.model.customer.CustomerMessage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CustomerMapper {

    CustomerRestResponse toRestResponse(CustomerMessage message);

    List<CustomerRestResponse> toRestResponses(List<CustomerMessage> message);
}
