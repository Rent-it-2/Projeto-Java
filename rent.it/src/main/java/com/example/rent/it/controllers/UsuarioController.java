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


    //Retorna um Booleando que diz se o cadastro deu certo ou não juntamente com o codigo
    @PostMapping("/cadastrar")
    public ResponseEntity<Boolean> Cadastrar(@RequestBody UsuarioGeral usuario){

        Boolean isOk = false;

      try {
          if(this.usuarioRepository.existsByEmail(usuario.getEmail())){
           return   ResponseEntity.status(409).body(isOk);
          }

          this.usuarioRepository.save(usuario);

      }catch (Exception e){
          return ResponseEntity.status(400).body(isOk);
      }
      isOk = true;

    return ResponseEntity.status(201).body(isOk);
    }




}
