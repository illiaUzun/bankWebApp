package com.bankWebApp.uzunIllia.bankWebApp.repository;


import com.bankWebApp.uzunIllia.bankWebApp.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
