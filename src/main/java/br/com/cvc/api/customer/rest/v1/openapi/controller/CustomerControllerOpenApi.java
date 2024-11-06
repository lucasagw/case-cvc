package br.com.cvc.api.customer.rest.v1.openapi.controller;

import br.com.cvc.api.customer.rest.v1.model.record.GetCustomers;
import br.com.cvc.api.customer.rest.v1.model.request.CustomerRequest;
import br.com.cvc.api.customer.rest.v1.model.response.CustomerResponse;
import br.com.cvc.configuration.rest.springdoc.OpenAPIResponseCodes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Tag(name = CustomerControllerOpenApi.CUSTOMER_TAG, description = "Customer operations v1")
public interface CustomerControllerOpenApi {

    String CUSTOMER_TAG = "Customer v1";

    @Operation(summary = "Create a customer", tags = CustomerControllerOpenApi.CUSTOMER_TAG)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenAPIResponseCodes.CREATED, description = "Customer created"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.BAD_REQUEST, description = "Invalid request"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.INTERNAL_SERVER_ERROR, description = "Internal server error"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.SERVICE_UNAVAILABLE, description = "Service unavailable")
    })
    ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CustomerRequest customerRequest, UriComponentsBuilder uriBuilder);


    @Operation(summary = "Update a customer", tags = CustomerControllerOpenApi.CUSTOMER_TAG)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenAPIResponseCodes.OK, description = "Customer updated"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.BAD_REQUEST, description = "Invalid request"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.NOT_FOUND, description = "Customer not found"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.UNAUTHORIZED, description = "Unauthorized"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.INTERNAL_SERVER_ERROR, description = "Internal server error"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.SERVICE_UNAVAILABLE, description = "Service unavailable")
    })
    ResponseEntity<CustomerResponse> updateCustomer(@PathVariable UUID customerId, @Valid CustomerRequest customerRequest);


    @Operation(summary = "Delete a customer", tags = CustomerControllerOpenApi.CUSTOMER_TAG)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenAPIResponseCodes.NO_CONTENT, description = "Customer deleted"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.BAD_REQUEST, description = "Invalid request"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.NOT_FOUND, description = "Customer not found"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.UNAUTHORIZED, description = "Unauthorized"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.INTERNAL_SERVER_ERROR, description = "Internal server error"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.SERVICE_UNAVAILABLE, description = "Service unavailable")
    })
    ResponseEntity<Void> deleteCustomer(@PathVariable UUID customerId);


    @Operation(summary = "Get a customer", tags = CustomerControllerOpenApi.CUSTOMER_TAG)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenAPIResponseCodes.OK, description = "Customer found"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.BAD_REQUEST, description = "Invalid request"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.NOT_FOUND, description = "Customer not found"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.UNAUTHORIZED, description = "Unauthorized"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.INTERNAL_SERVER_ERROR, description = "Internal server error"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.SERVICE_UNAVAILABLE, description = "Service unavailable")
    })
    ResponseEntity<CustomerResponse> getCustomer(@PathVariable UUID customerId);


    @Operation(summary = "Get a customer by state", tags = CustomerControllerOpenApi.CUSTOMER_TAG)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenAPIResponseCodes.OK, description = "Customers found"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.BAD_REQUEST, description = "Invalid request"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.NOT_FOUND, description = "Customers not found"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.UNAUTHORIZED, description = "Unauthorized"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.INTERNAL_SERVER_ERROR, description = "Internal server error"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.SERVICE_UNAVAILABLE, description = "Service unavailable")
    })
    ResponseEntity<GetCustomers> getCustomersByState(@PathVariable String state, @PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC) Pageable pageable);


    @Operation(summary = "Get all customers", tags = CustomerControllerOpenApi.CUSTOMER_TAG)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenAPIResponseCodes.OK, description = "Customers found"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.BAD_REQUEST, description = "Invalid request"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.NOT_FOUND, description = "Customers not found"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.UNAUTHORIZED, description = "Unauthorized"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.INTERNAL_SERVER_ERROR, description = "Internal server error"),
            @ApiResponse(responseCode = OpenAPIResponseCodes.SERVICE_UNAVAILABLE, description = "Service unavailable")
    })
    ResponseEntity<GetCustomers> getCustomers(@PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC) Pageable pageable);

}
