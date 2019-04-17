package com.bankWebApp.uzunIllia.bankWebApp;


import com.bankWebApp.uzunIllia.bankWebApp.domain.BankAccount;
import com.bankWebApp.uzunIllia.bankWebApp.impl.BankAccountServiceImpl;
import com.bankWebApp.uzunIllia.bankWebApp.impl.ClientServiceImpl;
import com.bankWebApp.uzunIllia.bankWebApp.rest.BankAccountResource;
import com.bankWebApp.uzunIllia.bankWebApp.rest.ClientResource;
import com.bankWebApp.uzunIllia.bankWebApp.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URISyntaxException;

@Controller
public class TransactionsController {


}