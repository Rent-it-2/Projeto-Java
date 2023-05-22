package com.example.rent.it.api.controllers;

import com.example.rent.it.dto.enderecoDto.EnderecoCriacao;
import com.example.rent.it.object.endereco.Endereco;
import com.example.rent.it.repository.EnderecoRepository;
import com.example.rent.it.service.EnderecoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereços listados com sucesso")
    })
    public ResponseEntity<List<Endereco>> getAllEnderecos() {
        List<Endereco> endereco = enderecoRepository.findAll();
        return ResponseEntity.ok(endereco);
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado Endereço com o ID especificado")
    })
    public Endereco updateEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
        if (this.enderecoRepository.existsById(id)) {
            endereco.setId(id);
            return this.enderecoRepository.save(endereco);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado Endereço com o ID especificado")
    })
    public void deleteEndereco(@PathVariable Long id) {
        enderecoRepository.deleteById(id);
    }

    @PostMapping("/cadastrar")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="201", description = "Endereço cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Não foi possível cadastrar o Endereço"),
    })
    public ResponseEntity<Void> criar(@RequestBody EnderecoCriacao enderecoCriacaoDto) {
        this.enderecoService.criar(enderecoCriacaoDto);
        return ResponseEntity.status(201).build();
    }
}