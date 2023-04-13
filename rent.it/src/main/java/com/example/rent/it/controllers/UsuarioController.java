package com.example.rent.it.controllers;

import com.example.rent.it.Services.Autenticacao;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    private Autenticacao authService;

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o usuário com o ID especificado"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado usuário com o ID especificado")
    })
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso")
    })
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }


    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao processar a requisição")
    })
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
        try {
            if(this.usuarioRepository.existsByEmail(usuario.getEmail())){
                return ResponseEntity.status(400).build();
            }
            Usuario novoUsuario = usuarioRepository.save(usuario);
            return ResponseEntity.status(201).body(novoUsuario);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado usuário com o ID especificado")
    })
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioExistente = usuarioOptional.get();
            usuarioExistente.setNome(usuario.getNome());
            usuarioExistente.setApelido(usuario.getApelido());
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioExistente.setPassword(usuario.getPassword());
            usuarioExistente.setTelefone(usuario.getTelefone());
            usuarioExistente.setAvaliacao(usuario.getAvaliacao());
            return usuarioRepository.save(usuarioExistente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado usuário com o ID especificado")
    })
    public void deleteUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    public ResponseEntity<Usuario> login(@RequestBody Usuario loginRequest) {
        Usuario user = authService.login(loginRequest.getEmail(), loginRequest.getPassword());

        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(user);
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