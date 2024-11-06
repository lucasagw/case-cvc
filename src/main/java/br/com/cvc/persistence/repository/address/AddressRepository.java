package br.com.cvc.persistence.repository.address;

import br.com.cvc.domain.entity.address.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<AddressEntity, UUID> {
}
