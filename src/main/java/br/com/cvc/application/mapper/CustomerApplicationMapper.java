package br.com.cvc.application.mapper;

import br.com.cvc.api.customer.rest.v1.model.request.CustomerRequest;
import br.com.cvc.api.customer.rest.v1.model.response.CustomerResponse;
import br.com.cvc.domain.vo.customer.CustomerVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerApplicationMapper {

    CustomerResponse fromCustomerVOToCustomerResponse(CustomerVO customerVO);

    CustomerVO fromCustomerRequestToCustomerVO(CustomerRequest customerRequest);
}
