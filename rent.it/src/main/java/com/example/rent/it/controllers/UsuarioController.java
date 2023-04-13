package com.example.rent.it.controllers;

//import com.example.rent.it.Services.Autenticacao;
import com.example.rent.it.object.usuario.UsuarioGeral;
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

    //private Autenticacao authService;


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
    public ResponseEntity<UsuarioGeral> addUsuario(@RequestBody UsuarioGeral usuario) {
        try {
            if(this.usuarioRepository.existsByEmail(usuario.getEmail())){
                return ResponseEntity.status(400).build();
            }
            UsuarioGeral novoUsuario = usuarioRepository.save(usuario);
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
    public UsuarioGeral updateUsuario(@PathVariable Long id, @RequestBody UsuarioGeral usuario) {
        if (this.usuarioRepository.existsById(id)) {
            usuario.setId(id);
            return this.usuarioRepository.save(usuario);
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
    @PostMapping("/login/{email}/{senha}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    public ResponseEntity<?> login(@PathVariable String email, @PathVariable String senha) {
        Optional<UsuarioGeral> user = this.usuarioRepository.findByEmailAndPassword(email, senha);

        if (!user.isPresent()) {
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
    public ResponseEntity<Boolean> cadastrar(@RequestBody UsuarioGeral usuario){

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