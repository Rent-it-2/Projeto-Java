package com.example.rent.it.repository;

import com.example.rent.it.object.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    boolean existsById(Long id);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findById(Long id);

}
