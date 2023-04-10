package com.example.rent.it.controllers;

import com.example.rent.it.Services.Autenticacao;
import com.example.rent.it.object.usuario.UsuarioGeral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.rent.it.repository.UsuarioRepository;
import com.example.rent.it.object.usuario.Usuario;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Autenticacao authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginRequest) {
        Usuario user = authService.login(loginRequest.getEmail(), loginRequest.getSenha());

        if (user == null) {
            return ResponseEntity.badRequest().body("Credenciais inválidas");
        }

        return ResponseEntity.ok().body("Login realizado com sucesso");
    }



}
