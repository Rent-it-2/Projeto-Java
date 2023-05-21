package com.example.rent.it.repository;

import com.example.rent.it.object.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
