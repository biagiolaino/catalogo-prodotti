package com.example.catalogo_prodotti.controller;

import com.example.catalogo_prodotti.model.Prodotto;
import com.example.catalogo_prodotti.service.ProdottoService;
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
    public List<Prodotto> getAllProdotti() {
        return prodottoService.getAllProdotti();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prodotto> getProdottoById(@PathVariable String id) {
        return prodottoService.getProdotto(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Prodotto> createProdotto(@RequestBody Prodotto prodotto) {
        Prodotto saved = prodottoService.addProdotto(prodotto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prodotto> updateProdotto(
            @PathVariable String id,
            @RequestBody Prodotto prodotto) {
        Prodotto updated = prodottoService.updateProdotto(id, prodotto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProdotto(@PathVariable String id) {
        prodottoService.deleteProdotto(id);
        return ResponseEntity.noContent().build();
    }
}
