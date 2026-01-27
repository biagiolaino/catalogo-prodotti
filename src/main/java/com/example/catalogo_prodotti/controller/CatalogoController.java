package com.example.catalogo_prodotti.controller;

import com.example.catalogo_prodotti.dto.ProdottoQuantitaDTO;
import com.example.catalogo_prodotti.model.Prodotto;
import com.example.catalogo_prodotti.service.ProdottoService;
import com.example.catalogo_prodotti.dto.ProdottoDTO;
import com.example.catalogo_prodotti.mapper.ProdottoMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prodotti")
public class CatalogoController {

    private final ProdottoService prodottoService;

    public CatalogoController(ProdottoService prodottoService) {
        this.prodottoService = prodottoService;
    }

    @GetMapping
    public List<ProdottoDTO> getAllProdotti() {
        return prodottoService.getAllProdotti()
                .stream()
                .map(ProdottoMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdottoDTO> getProdottoById(@PathVariable String id) {
        return prodottoService.getProdotto(id)
                .map(ProdottoMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<ProdottoDTO> getByNome(@PathVariable String nome) {
        return prodottoService.getProdottoByNome(nome)
                .map(ProdottoMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProdottoDTO> createProdotto(
            @Valid @RequestBody ProdottoDTO dto) {
        Prodotto entity = ProdottoMapper.toEntity(dto);
        Prodotto saved = prodottoService.addProdotto(entity);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ProdottoMapper.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdottoDTO> updateProdotto(
            @PathVariable String id,
            @Valid @RequestBody ProdottoDTO dto) {
        Prodotto entity = ProdottoMapper.toEntity(dto);
        Prodotto updated = prodottoService.updateProdotto(id, entity);
        return ResponseEntity.ok(ProdottoMapper.toDTO(updated));
    }

    @PutMapping("/nome/{nome}/quantita")
    public ResponseEntity<ProdottoDTO> aggiornaQuantita(
            @PathVariable String nome,
            @Valid @RequestBody ProdottoQuantitaDTO dto) {
        Prodotto aggiornato =
                prodottoService.aggiungiQuantita(nome, dto.getQuantitaDaAggiungere());
        return ResponseEntity.ok(ProdottoMapper.toDTO(aggiornato));
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity<Void> deleteProdotto(@PathVariable String nome) {
        prodottoService.deleteProdotto(nome);
        return ResponseEntity.noContent().build();
    }
}

