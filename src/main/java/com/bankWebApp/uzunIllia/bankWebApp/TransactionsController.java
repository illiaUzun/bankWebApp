package com.bankWebApp.uzunIllia.bankWebApp;


import com.bankWebApp.uzunIllia.bankWebApp.domain.BankAccount;
import com.bankWebApp.uzunIllia.bankWebApp.domain.Client;
import com.bankWebApp.uzunIllia.bankWebApp.domain.Transaction;
import com.bankWebApp.uzunIllia.bankWebApp.domain.enumeration.TransactionType;
import com.bankWebApp.uzunIllia.bankWebApp.impl.BankAccountServiceImpl;
import com.bankWebApp.uzunIllia.bankWebApp.impl.ClientServiceImpl;
import com.bankWebApp.uzunIllia.bankWebApp.rest.BankAccountResource;
import com.bankWebApp.uzunIllia.bankWebApp.rest.ClientResource;
import com.bankWebApp.uzunIllia.bankWebApp.rest.TransactionResource;
import com.bankWebApp.uzunIllia.bankWebApp.service.BankAccountService;
import com.bankWebApp.uzunIllia.bankWebApp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class TransactionsController {

    private TransactionService trService;
    private TransactionResource trResource;
    private BankAccountService service;
    private ClientServiceImpl clService;
    private BankAccountResource bankAccountResource;
    private ClientResource clientResource;

    @Autowired
    public void setService(BankAccountServiceImpl service, ClientServiceImpl clService, TransactionService trService) {
        this.service = service;
        this.clService = clService;

        clientResource = new ClientResource(clService);
        bankAccountResource = new BankAccountResource(service);


        this.trResource = new TransactionResource(trService);
        this.trService = trService;
    }

    @GetMapping("/add_transaction")
    public String addTransaction(@RequestParam(value ="id", defaultValue = "") Long id ,  Model model) throws URISyntaxException {
        Transaction transaction = new Transaction();
        transaction.setAccountID(id);
        model.addAttribute("transaction", transaction);
        model.addAttribute("allTypes", TransactionType.values());
        return "add_transaction";
    }

    @PostMapping("/add_transaction")
    public String submit(@ModelAttribute Transaction transaction, Model model) throws URISyntaxException {
        trResource.createTransaction(transaction);
        Long trans = transaction.getAccountID();
        if (transaction.getType() == TransactionType.INCOME){
            service.income(trans, transaction.getMoneyAmmount());
        } else {
            service.chargeOff(trans, transaction.getMoneyAmmount());
        }
        return "redirect:/";
    }


    @GetMapping("/transfer")
    public String addTransfer(@RequestParam("id") Long id, Model model) throws URISyntaxException {

        Transaction transaction = new Transaction();
        transaction.setAccountID(id);

        model.addAttribute("transaction", transaction);

        return "add_transaction2";
    }


    @PostMapping("/transfer")
    public String submitTransfer(@ModelAttribute Transaction transaction, Model model) throws URISyntaxException {

        trResource.createTransaction(transaction);

        Long transA = transaction.getAccountID();
        Long transB = transaction.getAccountIdB();

        service.transaction(transA, transB, transaction.getMoneyAmmount());

        return "redirect:/";
    }


    @GetMapping("/transactions")
    public String index(Long id, Model model) {
        model.addAttribute("accountID", id);
        model.addAttribute("transactions", trService.findAll(Pageable.unpaged()));

        return "transactions";
    }
}