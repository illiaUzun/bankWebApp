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
        clientResource = new ClientResource(clService);
        bankAccountResource = new BankAccountResource(service);
    }

    @GetMapping("/add_account")
    public String addAccount(Model model) {
        model.addAttribute("account", new BankAccount());
        return "add_account";
    }

    @PostMapping("/add_account")
    public String greetingSubmit(@RequestParam("id") Long id, @ModelAttribute BankAccount account,  Model model) throws URISyntaxException {
        account.setId(id);
        bankAccountResource.createBankAccount(account);

        return "redirect:/";
    }

    @GetMapping("/accounts")
    public String index(@RequestParam("id") Long id, Model model) {
        model.addAttribute("accounts", bankAccountResource.getBankAccountsWithId(Pageable.unpaged(), id));
        model.addAttribute("client", clientResource.getClient(id));
        return "accounts";
    }

}