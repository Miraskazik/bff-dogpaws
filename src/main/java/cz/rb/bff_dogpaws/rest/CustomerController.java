package cz.rb.bff_dogpaws.rest;

import cz.rb.bff_dogpaws.domain.model.CustomerRestResponse;
import cz.rb.bff_dogpaws.domain.service.impl.CustomerService;
import cz.rb.projectcommon.model.customer.CustomerMessage;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService service;

    @GetMapping("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<CustomerRestResponse> getCustomerById(final @PathVariable("customerId") Long customerId) {
        log.info("CustomerController | getCustomerById001 | starting request with id: {}", customerId);
        var response = service.getCustomer(customerId);

        log.info("CustomerController | getCustomerById002 | request finished: {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<CustomerRestResponse>> getCustomers() {
        log.info("CustomerController | getCustomers001 | starting request for all customers");
        var response = service.getCustomers();
        log.info("CustomerController | getCustomers002 | request finished: {}", response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<CustomerRestResponse> addCustomer(final @RequestBody CustomerMessage customerMessage) {
        log.info("CustomerController | addCustomer001 | starting request with customer: {}", customerMessage);
        var response = service.addCustomer(customerMessage);
        log.info("CustomerController | addCustomer002 | request finished: {}", response);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{customerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Void> updateCustomer(final @PathVariable("customerId") Long customerId, final @RequestBody CustomerMessage customerMessage) {
        log.info("CustomerController | updateCustomer001 | starting request with customer: {}", customerMessage);
        service.updateCustomer(customerId, customerMessage);
        log.info("CustomerController | updateCustomer002 | request finished");
        return ResponseEntity.ok().build();
    }
}
