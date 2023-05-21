package com.example.rent.it.repository;

import com.example.rent.it.autenticacao.dto.itemDto.ItemDto;
import com.example.rent.it.object.item.Item;
import com.example.rent.it.ordenacao.ListaObj;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ProblemDetail;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i.id, i.nome, i.categoria, i.valorDia FROM Item i")
    List<Object []> findAllItens();
    boolean existsById(Long id);
    Optional<Item> findById(Long id);

    Optional<List<Item>> findByNomeIgnoreCase(String nome);
    @Query("SELECT i.foto FROM Item i WHERE i.id = ?1")
    byte[] findFotoById(Long id);

    boolean existsFotoById(Long id);
    @Modifying
    @Transactional
    @Query("UPDATE Item i SET i.foto = ?2 WHERE i.id = ?1")
    void atualizaFoto(Long id, byte[] foto);

    List<Item> findByUsuarioId(Long id);
}
