package com.example.catalogo_prodotti.repository;

import com.example.catalogo_prodotti.model.Prodotto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProdottoRepository extends MongoRepository<Prodotto, String> {

    Optional<Prodotto> findByNome(String nome);
    // boolean existsByNome(String nome);
    void deleteByNome(String nome);

}
