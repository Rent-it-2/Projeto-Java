package com.example.rent.it.repository;

import com.example.rent.it.object.favoritos.Favoritos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritoRepository extends JpaRepository<Favoritos, Long> {
}
