package br.com.cvc.domain.service;

import br.com.cvc.api.customer.rest.v1.model.record.GetCustomers;
import br.com.cvc.api.customer.rest.v1.model.response.CustomerResponse;
import br.com.cvc.domain.entity.address.AddressEntity;
import br.com.cvc.domain.entity.customer.CustomerEntity;
import br.com.cvc.domain.mapper.CustomerDomainMapper;
import br.com.cvc.domain.vo.customer.CustomerVO;
import br.com.cvc.persistence.repository.address.AddressRepository;
import br.com.cvc.persistence.repository.customer.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerDomainMapper customerDomainMapper;


    @Transactional
    @Override
    public CustomerResponse createCustomer(CustomerVO customerVO) {

        var customer = customerDomainMapper.fromCreateCustomerVOToCustomerEntity(customerVO);

        var address = customer.getAddress();

        var savedAddress = addressRepository.save(address);

        customer.setAddress(savedAddress);

        customerRepository.save(customer);

        return customerDomainMapper.fromCustomerEntityToCostumerResponse(customer);
    }

    @Transactional
    @Override
    public CustomerResponse updateCustomer(UUID customerId, CustomerVO customerVO) {

        var originalCustomer = getCustomerById(customerId);

        var updatedCustomer = customerDomainMapper.fromCustomerVOToCustomerEntity(customerVO);
        originalCustomer.setName(updatedCustomer.getName());
        originalCustomer.setEmail(updatedCustomer.getEmail());

        var originalAddress = originalCustomer.getAddress();
        var updatedAddress = updatedCustomer.getAddress();
        if (!originalAddress.equals(updatedAddress)) {

            addressRepository.save(AddressEntity.builder()
                    .id(originalAddress.getId())
                    .street(updatedAddress.getStreet())
                    .city(updatedAddress.getCity())
                    .state(updatedAddress.getState())
                    .build());
        }

        customerRepository.save(originalCustomer);

        return customerDomainMapper.fromCustomerEntityToCostumerResponse(originalCustomer);
    }

    @Transactional
    @Override
    public void deleteCustomer(UUID customerId) {

        var customer = getCustomerById(customerId);
        var address = customer.getAddress();

        customerRepository.delete(customer);
        addressRepository.delete(address);
    }

    @Override
    public CustomerResponse getCustomer(UUID customerId) {

        var customer = getCustomerById(customerId);

        return customerDomainMapper.fromCustomerEntityToCostumerResponse(customer);
    }

    @Override
    public GetCustomers getCustomersByState(String state, Pageable pageable) {

        Page<CustomerEntity> byAddressState = customerRepository.findByAddressState(state, pageable);

        List<CustomerResponse> customerResponses = customerDomainMapper.fromCustomerEntityToCostumerResponse(byAddressState.getContent());

        return new GetCustomers(customerResponses,
                pageable.getPageNumber(),
                pageable.getPageSize(), byAddressState.getTotalPages(), byAddressState.getTotalElements());
    }

    @Override
    public GetCustomers getCustomers(Pageable pageable) {

        Page<CustomerEntity> allCustomers = customerRepository.findAllCustomers(pageable);

        List<CustomerResponse> customerResponses = customerDomainMapper.fromCustomerEntityToCostumerResponse(allCustomers.getContent());

        return new GetCustomers(customerResponses,
                pageable.getPageNumber(),
                pageable.getPageSize(), allCustomers.getTotalPages(), allCustomers.getTotalElements());
    }

    private CustomerEntity getCustomerById(UUID customerId) {

        return customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));
    }

}
