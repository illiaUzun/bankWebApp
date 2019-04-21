package com.bankWebApp.uzunIllia.bankWebApp.impl;

import com.bankWebApp.uzunIllia.bankWebApp.domain.BankAccount;
import com.bankWebApp.uzunIllia.bankWebApp.repository.BankAccountRepository;
import com.bankWebApp.uzunIllia.bankWebApp.service.BankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Service Implementation for managing BankAccount.
 */

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    private final Logger log = LoggerFactory.getLogger(BankAccountServiceImpl.class);

    @Autowired
    BankAccountRepository bankAccountRepository;


    /**
     * Save a bankAccount.
     *
     * @param bankAccount the entity to save
     * @return the persisted entity
     */
    @Override
    public BankAccount save(BankAccount bankAccount) {
        log.debug("Request to save BankAccount : {}", bankAccount);
        return bankAccountRepository.save(bankAccount);
    }

    /**
     * Get all the bankAccounts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BankAccount> findAll(Pageable pageable) {
        log.debug("Request to get all BankAccounts");
        return bankAccountRepository.findAll(pageable);
    }

    /**
     * Get all the bankAccounts of special client.
     *
     * @param clientId the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BankAccount> findAllById(Pageable pageable, Long clientId) {
        log.debug("Request to get all BankAccounts");
        return bankAccountRepository.findBankAccountsByClientID(pageable, clientId);
    }

    /**
     * Get one bankAccount by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BankAccount> findOne(Long id) {
        log.debug("Request to get BankAccount : {}", id);
        return bankAccountRepository.findById(id);
    }

    /**
     * Delete the bankAccount by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BankAccount : {}", id);
        bankAccountRepository.deleteById(id);
    }

    @Override
    public void chargeOff(Long id, BigDecimal moneyAmmount) {
        BankAccount ba =  bankAccountRepository.findById(id).get();
        ba.setMoneyAmmount(ba.getMoneyAmmount().subtract(moneyAmmount));
        bankAccountRepository.saveAndFlush(ba);
    }

    @Override
    public void income(Long id, BigDecimal moneyAmmount) {
        BankAccount ba =  bankAccountRepository.findById(id).get();
        ba.setMoneyAmmount(ba.getMoneyAmmount().add(moneyAmmount));
        bankAccountRepository.saveAndFlush(ba);
    }

    @Override
    public void transaction(Long idA, Long idB, BigDecimal moneyAmmount) {
        BankAccount A =  bankAccountRepository.findById(idA).get();
        BankAccount B =  bankAccountRepository.findById(idB).get();
        A.setMoneyAmmount(A.getMoneyAmmount().subtract(moneyAmmount));
        B.setMoneyAmmount(B.getMoneyAmmount().add(moneyAmmount));
    }
}
