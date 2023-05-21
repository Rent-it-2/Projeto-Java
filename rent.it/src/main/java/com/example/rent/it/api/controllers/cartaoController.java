package com.example.rent.it.api.controllers;

import com.example.rent.it.autenticacao.dto.cartaoDto.CartaoCriacaoDto;
import com.example.rent.it.autenticacao.dto.cartaoDto.CartaoDto;
import com.example.rent.it.autenticacao.dto.usuarioDto.UsuarioCriacao;
import com.example.rent.it.object.cartao.Cartao;
import com.example.rent.it.service.CartaoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartaos")
public class cartaoController {


    private final CartaoService cartaoService;

    public cartaoController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }



    @PostMapping("/cadastrar")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="201", description = "Cartão cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Não foi possível cadastrar o Cartão"),
               })

    public ResponseEntity<CartaoDto> criar(@RequestBody CartaoCriacaoDto cartao, @RequestParam Long id) {
        this.cartaoService.criar(cartao, id);
        return ResponseEntity.status(201).build();
    }

}
