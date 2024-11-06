package br.com.cvc.persistence.repository.customer;

import br.com.cvc.domain.entity.customer.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {


    @Query(value = "SELECT c.* FROM customer c " +
            "JOIN address a ON c.address_id = a.id " +
            "WHERE a.state = :state", nativeQuery = true)
    Page<CustomerEntity> findByAddressState(String state, Pageable pageable);


    @Query(value = "SELECT c.* FROM customer c", nativeQuery = true)
    Page<CustomerEntity> findAllCustomers(Pageable pageable);

}
