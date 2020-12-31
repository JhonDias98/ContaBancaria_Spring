package com.orangetalents.contaBancaria.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContaController {

}
