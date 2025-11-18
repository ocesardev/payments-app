package com.caio.payments.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caio.payments.datasource.model.client.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	Optional<Client> findByEmail(String email);
}