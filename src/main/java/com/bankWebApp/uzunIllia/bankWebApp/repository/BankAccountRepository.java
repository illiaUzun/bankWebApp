package com.bankWebApp.uzunIllia.bankWebApp.repository;

import com.bankWebApp.uzunIllia.bankWebApp.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

}
