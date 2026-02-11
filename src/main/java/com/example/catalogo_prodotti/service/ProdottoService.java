package com.example.catalogo_prodotti.service;

import com.example.catalogo_prodotti.model.Prodotto;
import com.example.catalogo_prodotti.repository.ProdottoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProdottoService {

    private final ProdottoRepository prodottoRepository;

    public ProdottoService(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }

    public List<Prodotto> getAllProdotti() {
        List<Prodotto> prodotti = prodottoRepository.findAll();
        if (prodotti.isEmpty()) System.out.println("Nessun prodotto disponibile");
        else System.out.println("Lista dei prodotti: " + prodotti);
        return prodotti;
    }

    public Optional<Prodotto> getProdotto(String id) {
        Optional<Prodotto> prodotto = prodottoRepository.findById(id);
        if (prodotto.isEmpty()) System.out.println("Nessun prodotto disponibile con l'id " + id);
        else System.out.println("Prodotto: " + prodotto.get());
        return prodotto;
    }

    public Prodotto addProdotto(Prodotto prodotto) {
        if (prodottoRepository.findByNome(prodotto.getNome()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Prodotto già presente"
            );
        }
        prodotto.setId(null);
        System.out.println("Nuovo prodotto inserito");
        return prodottoRepository.save(prodotto);
    }

    public Prodotto updateProdotto(String id, int quantitaDaAggiungere, double prezzo) {
        Prodotto prodotto = prodottoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Prodotto con id " + id + " non trovato"
                ));
        prodotto.setQuantitaDisponibile(prodotto.getQuantitaDisponibile() + quantitaDaAggiungere);
        if (prezzo != 0) prodotto.setPrezzo(prezzo);
        System.out.println("Prodotto con id " + id + " aggiornato");
        return prodottoRepository.save(prodotto);
    }

    public void deleteProdotto(String id) {
        Prodotto prodotto = prodottoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Prodotto con id " + id + " non trovato"
                ));
        System.out.println("Prodotto con id " + id + " eliminato");
        prodottoRepository.delete(prodotto);
    }


}
