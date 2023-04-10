package com.example.rent.it.Services;

import com.example.rent.it.object.usuario.Usuario;
import com.example.rent.it.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Autenticacao {
    @Autowired
    private UsuarioRepository userRepository;

    public Usuario login(String email, String senha) {
        Optional<Usuario> optionalUser = userRepository.findByEmailAndSenha(email, senha);

        return optionalUser.orElse(null);

    }
}
