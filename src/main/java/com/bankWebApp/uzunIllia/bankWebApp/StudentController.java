package com.bankWebApp.uzunIllia.bankWebApp;

import io.swagger.models.Model;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

@Controller
public class StudentController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/add_student")
    public String addStudent(Model model) {
        return "add_student";
    }


}