package br.com.cvc.api.customer.rest.v1.model.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressResponse {

    private String street;
    private String city;
    private String state;
}
