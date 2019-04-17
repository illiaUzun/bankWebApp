package com.bankWebApp.uzunIllia.bankWebApp.repository;

import com.bankWebApp.uzunIllia.bankWebApp.domain.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    Page<BankAccount> findBankAccountsByClientID(Pageable page, @Param("active") Long id);
}
