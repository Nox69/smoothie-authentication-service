package com.nox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nox.model.Customer;

@Repository
public interface AuthenticationRepository extends JpaRepository<Customer, String> {

}
