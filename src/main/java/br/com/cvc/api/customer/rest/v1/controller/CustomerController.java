package br.com.cvc.api.customer.rest.v1.controller;

import br.com.cvc.api.customer.rest.v1.model.record.GetCustomers;
import br.com.cvc.api.customer.rest.v1.model.request.CustomerRequest;
import br.com.cvc.api.customer.rest.v1.model.response.CustomerResponse;
import br.com.cvc.api.customer.rest.v1.openapi.controller.CustomerControllerOpenApi;
import br.com.cvc.application.mapper.CustomerApplicationMapper;
import br.com.cvc.domain.service.CustomerService;
import br.com.cvc.domain.vo.customer.CustomerVO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/v1/customers")
@SecurityRequirement(name = "basicAuth")
@Validated
public class CustomerController implements CustomerControllerOpenApi {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerApplicationMapper customerApplicationMapper;


    @Override
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CustomerRequest customerRequest, UriComponentsBuilder uriBuilder) {

        CustomerVO customerVO = customerApplicationMapper.fromCustomerRequestToCustomerVO(customerRequest);

        var customerResponse = customerService.createCustomer(customerVO);

        var uri = uriBuilder.path("/v1/customers/{id}").buildAndExpand(customerResponse.getId()).toUri();

        return ResponseEntity.created(uri).body(customerResponse);
    }

    @Override
    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable UUID customerId, @RequestBody @Valid CustomerRequest customerRequest) {

        CustomerVO customerVO = customerApplicationMapper.fromCustomerRequestToCustomerVO(customerRequest);

        return ResponseEntity.ok(customerService.updateCustomer(customerId, customerVO));
    }

    @Override
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID customerId) {

        customerService.deleteCustomer(customerId);

        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable UUID customerId) {

        return ResponseEntity.ok(customerService.getCustomer(customerId));
    }

    @Override
    @GetMapping("/state/{state}")
    public ResponseEntity<GetCustomers> getCustomersByState(@PathVariable String state, @PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.ok(customerService.getCustomersByState(state, pageable));
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<GetCustomers> getCustomers(@PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.ok(customerService.getCustomers(pageable));
    }
}
