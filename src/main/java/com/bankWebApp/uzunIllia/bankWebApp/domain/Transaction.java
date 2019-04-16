package com.bankWebApp.uzunIllia.bankWebApp.domain;

import com.bankWebApp.uzunIllia.bankWebApp.domain.enumeration.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Transaction.
 */
@Entity
@Table(name = "transaction")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "transaction_id")
    private Integer transactionID;

    @Column(name = "account_id")
    private Integer accountID;

    @Column(name = "money_ammount", precision = 10, scale = 2)
    private BigDecimal moneyAmmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_type")
    private TransactionType type;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JsonIgnoreProperties("transactions")
    private BankAccount bankAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTransactionID() {
        return transactionID;
    }

    public Transaction transactionID(Integer transactionID) {
        this.transactionID = transactionID;
        return this;
    }

    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public Transaction accountID(Integer accountID) {
        this.accountID = accountID;
        return this;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public BigDecimal getMoneyAmmount() {
        return moneyAmmount;
    }

    public Transaction moneyAmmount(BigDecimal moneyAmmount) {
        this.moneyAmmount = moneyAmmount;
        return this;
    }

    public void setMoneyAmmount(BigDecimal moneyAmmount) {
        this.moneyAmmount = moneyAmmount;
    }

    public TransactionType getType() {
        return type;
    }

    public Transaction type(TransactionType type) {
        this.type = type;
        return this;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public Transaction description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public Transaction bankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transaction transaction = (Transaction) o;
        if (transaction.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transaction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Transaction{" +
            "id=" + getId() +
            ", transactionID=" + getTransactionID() +
            ", accountID=" + getAccountID() +
            ", moneyAmmount=" + getMoneyAmmount() +
            ", type='" + getType() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
