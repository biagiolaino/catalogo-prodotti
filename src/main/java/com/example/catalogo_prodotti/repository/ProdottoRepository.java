package com.example.catalogo_prodotti.repository;

import com.example.catalogo_prodotti.model.Prodotto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdottoRepository extends MongoRepository<Prodotto, String> {
}
