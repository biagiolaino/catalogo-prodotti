package com.example.catalogo_prodotti.service;

import com.example.catalogo_prodotti.model.Prodotto;
import com.example.catalogo_prodotti.repository.ProdottoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdottoService {

    private final ProdottoRepository prodottoRepository;

    public ProdottoService(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }

    public List<Prodotto> getAllProdotti() {
        return prodottoRepository.findAll();
    }

    public Optional<Prodotto> getProdotto(String id) {
        return prodottoRepository.findById(id);
    }

    public Prodotto addProdotto(Prodotto prodotto) {
        return prodottoRepository.save(prodotto);
    }

    public Prodotto updateProdotto(String id, Prodotto prod) {
        return prodottoRepository.findById(id)
                .map(prodotto -> {
                    prodotto.setNome(prod.getNome());
                    prodotto.setDescrizione(prod.getDescrizione());
                    prodotto.setPrezzo(prod.getPrezzo());
                    prodotto.setQuantitaDisponibile(prod.getQuantitaDisponibile());
                    prodotto.setCategoria(prod.getCategoria());
                    return prodottoRepository.save(prodotto);
                })
                .orElseThrow(() -> new RuntimeException("Prodotto con id: " + id + " non trovato"));
    }

    public void deleteProdotto(String id) {
        prodottoRepository.deleteById(id);
    }

}
