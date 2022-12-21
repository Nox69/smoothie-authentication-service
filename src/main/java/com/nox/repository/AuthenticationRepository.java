package com.nox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nox.model.Customer;

/**
 * This is a Domain Driven Design Repository interface for the Customer.
 *
 */
@Repository
public interface AuthenticationRepository extends JpaRepository<Customer, String> {

}
