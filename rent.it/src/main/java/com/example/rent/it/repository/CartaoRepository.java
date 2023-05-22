package com.example.rent.it.repository;

import com.example.rent.it.object.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    Cartao findByUsuarioId(Long id);
}
