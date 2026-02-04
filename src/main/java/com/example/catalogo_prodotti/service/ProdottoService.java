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
        List<Prodotto> prodotti = prodottoRepository.findAll();
        if (prodotti.isEmpty()) System.out.println("Nessun prodotto disponibile");
        else System.out.println("Lista dei prodotti: " + prodotti);
        return prodotti;
    }

    public Optional<Prodotto> getProdotto(String id) {
        Optional<Prodotto> prodotto = prodottoRepository.findById(id);
        if (prodotto.isEmpty()) System.out.println("Nessun prodotto disponibile con l'id " + id);
        else System.out.println("Prodotto: " + prodotto);
        return prodotto;
    }

    public Optional<Prodotto> getProdottoByNome(String nome) {
        return prodottoRepository.findByNome(nome);
    }

    public Prodotto addProdotto(Prodotto prodotto) {
        Optional<Prodotto> existing =
                prodottoRepository.findByNome(prodotto.getNome());
        if (existing.isPresent()) {
            Prodotto p = existing.get();

            p.setQuantitaDisponibile(
                    p.getQuantitaDisponibile() + prodotto.getQuantitaDisponibile()
            );

            System.out.println("Prodotto esistente, quantità aggiornata");
            return prodottoRepository.save(p);
        }
        prodotto.setId(null);
        System.out.println("Nuovo prodotto inserito");
        return prodottoRepository.save(prodotto);
    }

    /* public Prodotto updateProdotto(String id, Prodotto prod) {
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
    } */

    public Prodotto updateProdotto(String nome, int quantitaDaAggiungere, double prezzo) {
        Prodotto prodotto = prodottoRepository.findByNome(nome)
                .orElseThrow(() ->
                        new RuntimeException("Prodotto con nome " + nome + " non trovato"));
        prodotto.setQuantitaDisponibile(prodotto.getQuantitaDisponibile() + quantitaDaAggiungere);
        prodotto.setPrezzo(prezzo);
        return prodottoRepository.save(prodotto);
    }

    public void deleteProdotto(String nome) {
        Optional<Prodotto> prodotto = prodottoRepository.findByNome(nome);
        if (prodotto.isEmpty()) {
            System.out.println("Prodotto con id: " + nome + " non trovato");
            return;
        }
        prodottoRepository.deleteByNome(nome);
        System.out.println("Il prodotto è stato eliminato con successo");
    }

}
