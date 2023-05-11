package com.example.rent.it.repository;

import com.example.rent.it.object.item.Item;
import com.example.rent.it.ordenacao.ListaObj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i.id, i.nome, i.categoria, i.valorDia FROM Item i")
    List<Object []> findAllItens();
    boolean existsById(Long id);
    Optional<Item> findById(Long id);

    Optional<List<Item>> findByNomeIgnoreCase(String nome);
}
