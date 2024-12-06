package cz.rb.bff_dogpaws.domain.service.impl;


import cz.rb.bff_dogpaws.domain.mapper.customer.CustomerMapper;
import cz.rb.bff_dogpaws.domain.messaging.CustomerMessenger;
import cz.rb.bff_dogpaws.domain.model.CustomerRestResponse;
import cz.rb.projectcommon.model.customer.CustomerMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMessenger messenger;
    private final CustomerMapper mapper;

    public CustomerRestResponse getCustomer(final Long id) {
        var customers = messenger.getCustomerById(id);
        return mapper.toRestResponse(customers);
    }

    public CustomerRestResponse addCustomer(final CustomerMessage customer) {
        var customers = messenger.addCustomer(customer);
        return mapper.toRestResponse(customers);
    }

    public void updateCustomer(final Long id, final CustomerMessage customer) {
        messenger.updateCustomer(id, customer);
    }

    public List<CustomerRestResponse> getCustomers() {
        var customers = messenger.getAllCustomers();
        return mapper.toRestResponses(customers);
    }


}
