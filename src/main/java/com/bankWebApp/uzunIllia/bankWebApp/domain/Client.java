package com.bankWebApp.uzunIllia.bankWebApp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Client.
 */
@Entity
@Table(name = "client")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "client_id")
    private Integer clientID;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_age")
    private Integer clientAge;

    @OneToMany(mappedBy = "client")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BankAccount> bankAccounts = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getClientID() {
        return clientID;
    }

    public Client clientID(Integer clientID) {
        this.clientID = clientID;
        return this;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public Client clientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getClientAge() {
        return clientAge;
    }

    public Client clientAge(Integer clientAge) {
        this.clientAge = clientAge;
        return this;
    }

    public void setClientAge(Integer clientAge) {
        this.clientAge = clientAge;
    }

    public Set<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public Client bankAccounts(Set<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
        return this;
    }

    public Client addBankAccount(BankAccount bankAccount) {
        this.bankAccounts.add(bankAccount);
        bankAccount.setClient(this);
        return this;
    }

    public Client removeBankAccount(BankAccount bankAccount) {
        this.bankAccounts.remove(bankAccount);
        bankAccount.setClient(null);
        return this;
    }

    public void setBankAccounts(Set<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Client client = (Client) o;
        if (client.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), client.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + getId() +
                ", clientID=" + getClientID() +
                ", clientName='" + getClientName() + "'" +
                ", clientAge=" + getClientAge() +
                "}";
    }
}
