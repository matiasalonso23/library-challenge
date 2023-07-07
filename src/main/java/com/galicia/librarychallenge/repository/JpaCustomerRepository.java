package com.galicia.librarychallenge.repository;

import com.galicia.librarychallenge.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCustomerRepository extends JpaRepository<Customer, Long> {
}
