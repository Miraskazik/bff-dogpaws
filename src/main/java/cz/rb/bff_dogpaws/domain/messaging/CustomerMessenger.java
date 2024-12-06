package cz.rb.bff_dogpaws.domain.messaging;

import cz.rb.projectcommon.model.customer.CustomerListMessage;
import cz.rb.projectcommon.model.customer.CustomerMessage;
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
public class CustomerMessenger {

    private final RestTemplate restTemplate;


    @Value("${customer-url}")
    private String customerUrl;

    public List<CustomerMessage> getAllCustomers() {
        log.info("CustomerMessenger | getAllCustomers001 | starting request for all customers");

        var response = getAllCustomersEndpoint();

        if (response == null || response.getCustomerMessage() == null || response.getCustomerMessage().isEmpty()) {
            return Collections.emptyList();
        }

        log.info("CustomerMessenger | getAllCustomers002 | request finished, all customers");
        return response.getCustomerMessage();
    }

    public CustomerMessage getCustomerById(final Long customerId) {
        log.info("CustomerMessenger | getCustomerById001 | starting request for customer: {}", customerId);

        var response = getCustomerByIdEndpoint(customerId);

        log.info("CustomerMessenger | getCustomerById002 | request finished, customer: {}", customerId);
        return response;
    }

    public CustomerMessage addCustomer(final CustomerMessage customer) {
        log.info("CustomerMessenger | addCustomer001 | starting request for customer: {}", customer);

        var response = addCustomerEndpoint(customer);

        log.info("CustomerMessenger | addCustomer002 | request finished, customer: {}", customer);
        return response;
    }

    public void updateCustomer(final Long customerId, final CustomerMessage customer) {
        log.info("CustomerMessenger | updateCustomer001 | starting request for customer: {}", customer);

        updateCustomerEndpoint(customerId, customer);

        log.info("CustomerMessenger | updateCustomer002 | request finished, customer: {}", customer);
    }


    private CustomerListMessage getAllCustomersEndpoint() {
        return restTemplate.getForObject(customerUrl + ALL_CUSTOMERS_ENDPOINT, CustomerListMessage.class);
    }

    private CustomerMessage getCustomerByIdEndpoint(final Long customerId) {
        return restTemplate.getForObject(customerUrl + CUSTOMER_ENDPOINT + customerId, CustomerMessage.class);
    }

    private CustomerMessage addCustomerEndpoint(final CustomerMessage customer) {
        return restTemplate.postForObject(customerUrl + ADD_CUSTOMER_ENDPOINT, customer, CustomerMessage.class);
    }

    private void updateCustomerEndpoint(final Long customerId, final CustomerMessage customer) {
        restTemplate.put(customerUrl + CUSTOMER_ENDPOINT + customerId, customer);
    }
}
