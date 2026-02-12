package com.example.catalogo_prodotti.controller;

import com.example.catalogo_prodotti.dto.ProdottoCreateDTO;
import com.example.catalogo_prodotti.dto.ProdottoUpdateDTO;
import com.example.catalogo_prodotti.mapper.ProdottoMapper;
import com.example.catalogo_prodotti.model.Prodotto;
import com.example.catalogo_prodotti.service.ProdottoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CatalogoController.class)
class CatalogoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdottoService prodottoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllProdotti_shouldReturnList() throws Exception {
        Prodotto prodotto = new Prodotto(
                "Mouse", "Mouse wireless", 25.99, 10, "Informatica"
        );
        prodotto.setId("1");

        when(prodottoService.getAllProdotti())
                .thenReturn(List.of(prodotto));

        mockMvc.perform(get("/prodotti"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Mouse"))
                .andExpect(jsonPath("$[0].prezzo").value(25.99));
    }

    @Test
    void getProdottoById_shouldReturnProdotto() throws Exception {
        Prodotto prodotto = new Prodotto(
                "Tastiera", "Meccanica", 79.99, 5, "Informatica"
        );
        prodotto.setId("abc");

        when(prodottoService.getProdotto("abc"))
                .thenReturn(Optional.of(prodotto));

        mockMvc.perform(get("/prodotti/abc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Tastiera"));
    }

    @Test
    void createProdotto_shouldReturnCreated() throws Exception {
        ProdottoCreateDTO dto = new ProdottoCreateDTO();
        dto.setNome("Monitor");
        dto.setDescrizione("27 pollici");
        dto.setPrezzo(199.99);
        dto.setQuantitaDisponibile(3);
        dto.setCategoria("Informatica");
        Prodotto saved = ProdottoMapper.fromCreateDTO(dto);
        saved.setId("xyz");
        when(prodottoService.addProdotto(any()))
                .thenReturn(saved);
        mockMvc.perform(post("/prodotti")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("xyz"))
                .andExpect(jsonPath("$.nome").value("Monitor"));
    }

    @Test
    void updateProdotto_shouldReturnUpdated() throws Exception {
        ProdottoUpdateDTO dto = new ProdottoUpdateDTO();
        dto.setQuantitaDaAggiungere(5);
        dto.setPrezzo(99.99);
        Prodotto updated = new Prodotto(
                "Tastiera", "Meccanica", 99.99, 10, "Informatica"
        );
        updated.setId("abc");
        when(prodottoService.updateProdotto(eq("abc"), anyInt(), anyDouble()))
                .thenReturn(updated);
        mockMvc.perform(put("/prodotti/abc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prezzo").value(99.99));
    }

    @Test
    void deleteProdotto_shouldReturnNoContent() throws Exception {
        doNothing().when(prodottoService).deleteProdotto("1");

        mockMvc.perform(delete("/prodotti/1"))
                .andExpect(status().isNoContent());
    }
}
