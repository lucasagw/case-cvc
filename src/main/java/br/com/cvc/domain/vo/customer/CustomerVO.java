package br.com.cvc.domain.vo.customer;

import br.com.cvc.domain.vo.address.AddressVO;
import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CustomerVO {

    private String name;

    private String email;

    private AddressVO address;
}
