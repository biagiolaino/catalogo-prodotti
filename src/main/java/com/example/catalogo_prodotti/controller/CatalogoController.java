package com.example.catalogo_prodotti.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogoController {

    @GetMapping("/catalogo")
    public String catalogo() {
        return "Ecco il catalogo dei prodotti!";
    }
}
