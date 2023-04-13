package com.example.rent.it.controllers;

import com.example.rent.it.Services.Autenticacao;
import com.example.rent.it.Services.UsuarioService;
import com.example.rent.it.object.usuario.UsuarioGeral;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import com.example.rent.it.repository.UsuarioRepository;
import com.example.rent.it.object.usuario.Usuario;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;


    private Autenticacao authService;

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o usuário com o ID especificado"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado usuário com o ID especificado")
    })
    public Usuario getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id);
    }

    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado usuário com o ID especificado")
    })
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(id, usuario);
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado usuário com o ID especificado")
    })
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginRequest) {
        Usuario user = authService.login(loginRequest.getEmail(), loginRequest.getPassword());

        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.status(200).body(user);
    }

    @PostMapping("/cadastrar")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="201", description = "Usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Não foi possível cadastrar o usuário"),
            @ApiResponse(responseCode = "409", description = "Já existe um usuário cadastrado com esse email")
    })
    public ResponseEntity<Boolean> cadastrar(@RequestBody Usuario usuario){

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