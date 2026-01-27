package com.example.catalogo_prodotti.mapper;

import com.example.catalogo_prodotti.model.Prodotto;
import com.example.catalogo_prodotti.dto.ProdottoDTO;

public class ProdottoMapper {

    public static ProdottoDTO toDTO(Prodotto prodotto) {
        ProdottoDTO dto = new ProdottoDTO();
        dto.setNome(prodotto.getNome());
        dto.setDescrizione(prodotto.getDescrizione());
        dto.setPrezzo(prodotto.getPrezzo());
        dto.setQuantitaDisponibile(prodotto.getQuantitaDisponibile());
        dto.setCategoria(prodotto.getCategoria());
        return dto;
    }

    public static Prodotto toEntity(ProdottoDTO dto) {
        Prodotto p = new Prodotto();
        p.setNome(dto.getNome());
        p.setDescrizione(dto.getDescrizione());
        p.setPrezzo(dto.getPrezzo());
        p.setQuantitaDisponibile(dto.getQuantitaDisponibile());
        p.setCategoria(dto.getCategoria());
        return p;
    }

}
