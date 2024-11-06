package br.com.cvc.domain.mapper;

import br.com.cvc.api.customer.rest.v1.model.response.CustomerResponse;
import br.com.cvc.domain.entity.customer.CustomerEntity;
import br.com.cvc.domain.vo.customer.CustomerVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerDomainMapper {

    CustomerEntity fromCustomerVOToCustomerEntity(CustomerVO customerVO);

    CustomerEntity fromCreateCustomerVOToCustomerEntity(CustomerVO customerVO);

    CustomerResponse fromCustomerEntityToCostumerResponse(CustomerEntity customerEntity);

    List<CustomerResponse> fromCustomerEntityToCostumerResponse(List<CustomerEntity> customerEntity);

}
