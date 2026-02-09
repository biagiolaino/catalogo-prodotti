package com.example.catalogo_prodotti.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProdottoCreateDTO {

    @NotBlank
    private String nome;
    @NotBlank
    private String descrizione;
    @Min(1)
    private double prezzo;
    @Min(1)
    private int quantitaDisponibile;
    @NotBlank
    private String categoria;

    public String getNome() { return nome; }

    public String getDescrizione() { return descrizione; }

    public double getPrezzo() { return prezzo; }

    public int getQuantitaDisponibile() { return quantitaDisponibile; }

    public String getCategoria() { return categoria; }

}
