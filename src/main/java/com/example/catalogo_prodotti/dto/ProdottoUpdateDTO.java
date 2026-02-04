package com.example.catalogo_prodotti.dto;

import jakarta.validation.constraints.Min;

public class ProdottoUpdateDTO {

    @Min(0)
    private int quantitaDaAggiungere;

    private double prezzo;

    public int getQuantitaDaAggiungere() {
        return quantitaDaAggiungere;
    }

    public double getPrezzo() {
        return prezzo;
    }
}
