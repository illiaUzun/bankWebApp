package com.bankWebApp.uzunIllia.bankWebApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A BankAccount.
 */
@Entity
@Table(name = "bank_account")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BankAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "account_id")
    private Integer accountID;

    @Column(name = "client_id", insertable = false, updatable = false)
    private Integer clientID;

    @Column(name = "money_ammount", precision = 10, scale = 2)
    private BigDecimal moneyAmmount;

    @OneToMany(mappedBy = "bankAccount")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Transaction> transactions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("bankAccounts")
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public BankAccount accountID(Integer accountID) {
        this.accountID = accountID;
        return this;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public Integer getClientID() {
        return clientID;
    }

    public BankAccount clientID(Integer clientID) {
        this.clientID = clientID;
        return this;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public BigDecimal getMoneyAmmount() {
        return moneyAmmount;
    }

    public BankAccount moneyAmmount(BigDecimal moneyAmmount) {
        this.moneyAmmount = moneyAmmount;
        return this;
    }

    public void setMoneyAmmount(BigDecimal moneyAmmount) {
        this.moneyAmmount = moneyAmmount;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public BankAccount transactions(Set<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }

    public BankAccount addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setBankAccount(this);
        return this;
    }

    public BankAccount removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
        transaction.setBankAccount(null);
        return this;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Client getClient() {
        return client;
    }

    public BankAccount client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BankAccount bankAccount = (BankAccount) o;
        if (bankAccount.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bankAccount.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BankAccount{" +
            "id=" + getId() +
            ", accountID=" + getAccountID() +
            ", clientID=" + getClientID() +
            ", moneyAmmount=" + getMoneyAmmount() +
            "}";
    }
}
