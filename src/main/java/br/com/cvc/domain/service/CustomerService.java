package br.com.cvc.domain.service;

import br.com.cvc.api.customer.rest.v1.model.record.GetCustomers;
import br.com.cvc.api.customer.rest.v1.model.response.CustomerResponse;
import br.com.cvc.domain.vo.customer.CustomerVO;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerVO customerVO);

    CustomerResponse updateCustomer(UUID customerId, CustomerVO customerVO);

    void deleteCustomer(UUID customerId);

    CustomerResponse getCustomer(UUID customerId);

    GetCustomers getCustomersByState(String state, Pageable pageable);

    GetCustomers getCustomers(Pageable pageable);

}
