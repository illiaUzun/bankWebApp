package com.bankWebApp.uzunIllia.bankWebApp;


import com.bankWebApp.uzunIllia.bankWebApp.domain.BankAccount;
import com.bankWebApp.uzunIllia.bankWebApp.domain.Client;
import com.bankWebApp.uzunIllia.bankWebApp.impl.BankAccountServiceImpl;
import com.bankWebApp.uzunIllia.bankWebApp.impl.ClientServiceImpl;
import com.bankWebApp.uzunIllia.bankWebApp.rest.BankAccountResource;
import com.bankWebApp.uzunIllia.bankWebApp.rest.ClientResource;
import com.bankWebApp.uzunIllia.bankWebApp.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.JsonPath;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@Controller
public class AccountsController {

    private BankAccountService service;
    private ClientServiceImpl clService;
    private BankAccountResource bankAccountResource;
    private ClientResource clientResource;

    @Autowired
    public void setService(BankAccountServiceImpl service, ClientServiceImpl clService) {
        this.service = service;
        this.clService = clService;
        clientResource = new ClientResource(clService);
        bankAccountResource = new BankAccountResource(service);
    }

    @GetMapping("/add_account")
    public String addAccount(@RequestParam("id") Long id, Model model) throws URISyntaxException {
        model.addAttribute("clientID_", id);
        model.addAttribute("clientName", clService.findOne(id).get().getClientName());
        BankAccount bankAccount = new BankAccount();
        bankAccount.setClientID(id);
        clService.findOne(id).get().getBankAccounts().add(bankAccount);
        System.out.println(clService.findOne(id).get().getBankAccounts().size());
        model.addAttribute("account", bankAccount);
        return "add_account";
    }

    @PostMapping("/add_account")
    public String greetingSubmit(@ModelAttribute BankAccount account, Model model) throws URISyntaxException {
        Long clientID = account.getClientID();
        bankAccountResource.createBankAccount(account);

        return "redirect:/accounts?id="+clientID;
    }

    @GetMapping("/accounts")
    public String index(@RequestParam("id") Long id, Model model) {
        model.addAttribute("accounts", service.findAllById(Pageable.unpaged(), id));
        model.addAttribute("clientName", clService.findOne(id).get().getClientName());
        model.addAttribute("clientID", id);
        return "accounts";
    }

}