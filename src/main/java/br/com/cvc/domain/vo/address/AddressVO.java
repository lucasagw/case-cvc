package br.com.cvc.domain.vo.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AddressVO {

    private String street;
    private String city;
    private String state;
}
