package com.example.rent.it.repository;

import com.example.rent.it.object.usuario.Usuario;
import com.example.rent.it.object.usuario.UsuarioGeral;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioGeral, Long> {
    Optional<Usuario> findByEmailAndPassword(String email, String senha);

}
