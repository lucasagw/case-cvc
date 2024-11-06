package br.com.cvc.api.customer.rest.v1.model.record;

import br.com.cvc.api.customer.rest.v1.model.response.CustomerResponse;

import java.util.List;

public record GetCustomers(List<CustomerResponse> customerItems,
                           int page,
                           int size,
                           int totalPages,
                           long totalElements) {
}
