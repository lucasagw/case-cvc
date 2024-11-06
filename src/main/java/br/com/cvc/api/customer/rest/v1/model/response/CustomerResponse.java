package br.com.cvc.api.customer.rest.v1.model.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerResponse {

    private String id;
    private String name;
    private String email;
    private AddressResponse address;
}
