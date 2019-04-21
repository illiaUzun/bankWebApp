package com.bankWebApp.uzunIllia.bankWebApp.service;

import com.bankWebApp.uzunIllia.bankWebApp.domain.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Service Interface for managing BankAccount.
 */


public interface BankAccountService {

    /**
     * Save a bankAccount.
     *
     * @param bankAccount the entity to save
     * @return the persisted entity
     */
    BankAccount save(BankAccount bankAccount);

    /**
     * Get all the bankAccounts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BankAccount> findAll(Pageable pageable);


    /**
     * Get all bankAccounts of client "id".
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BankAccount> findAllById(Pageable pageable, Long clientId);


    /**
     * Get the "id" bankAccount.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BankAccount> findOne(Long id);

    /**
     * Delete the "id" bankAccount.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    void chargeOff(Long id, BigDecimal moneyAmmount);

    void income(Long id, BigDecimal moneyAmmount);

    void transaction(Long idA, Long idB, BigDecimal moneyAmmount);
}
