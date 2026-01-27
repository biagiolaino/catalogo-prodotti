package com.example.catalogo_prodotti.dto;

import jakarta.validation.constraints.Min;

public class ProdottoQuantitaDTO {

    @Min(1)
    private int quantitaDaAggiungere;

    public int getQuantitaDaAggiungere() {
        return quantitaDaAggiungere;
    }

    public void setQuantitaDaAggiungere(int quantitaDaAggiungere) {
        this.quantitaDaAggiungere = quantitaDaAggiungere;
    }
}
