package com.bankWebApp.uzunIllia.bankWebApp;


import com.bankWebApp.uzunIllia.bankWebApp.domain.Client;
import com.bankWebApp.uzunIllia.bankWebApp.impl.ClientServiceImpl;
import com.bankWebApp.uzunIllia.bankWebApp.rest.ClientResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;
import java.net.URISyntaxException;

@Controller
public class ClientController {

    private ClientServiceImpl service;
    private ClientResource clientResource;

    @Autowired
    public void setService(ClientServiceImpl service) {
        this.service = service;
        clientResource = new ClientResource(service);
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("clients", service.findAll(Pageable.unpaged()));
        return "index";
    }

    @GetMapping("/add_client")
    public String addClient(Model model) {
        model.addAttribute("client", new Client());
        return "add_client";
    }

    @PostMapping("/add_client")
    public String greetingSubmit(@ModelAttribute Client client, Model model) throws URISyntaxException {
        clientResource.createClient(client);
        return "redirect:/";
    }

}