package com.example.catalogo_prodotti.dto;

import jakarta.validation.constraints.Min;

public class ProdottoUpdateDTO {

    @Min(0)
    private int quantitaDaAggiungere;
    private double prezzo;

    public int getQuantitaDaAggiungere() { return quantitaDaAggiungere; }
    public void setQuantitaDaAggiungere(int quantitaDaAggiungere) { this.quantitaDaAggiungere = quantitaDaAggiungere; }

    public double getPrezzo() { return prezzo; }
    public void setPrezzo(double prezzo) { this.prezzo = prezzo; }

}
