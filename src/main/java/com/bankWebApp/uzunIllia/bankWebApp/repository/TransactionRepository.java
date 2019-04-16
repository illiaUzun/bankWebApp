package com.bankWebApp.uzunIllia.bankWebApp.repository;

import com.bankWebApp.uzunIllia.bankWebApp.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
