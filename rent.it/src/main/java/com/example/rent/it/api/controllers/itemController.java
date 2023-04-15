package com.example.rent.it.api.controllers;

import com.example.rent.it.autenticacao.dto.itemDto.ItemDto;
import com.example.rent.it.autenticacao.dto.usuarioDto.UsuarioCriacao;
import com.example.rent.it.object.item.Item;
import com.example.rent.it.object.usuario.Usuario;
import com.example.rent.it.repository.ItemRepository;
import com.example.rent.it.service.ItemService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/itens")
public class itemController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o item com o ID especificado"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado item com o ID especificado")
    })
    public ResponseEntity<Item> getItensById(@PathVariable Long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if(itemOptional.isPresent()) {
            return ResponseEntity.ok(itemOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Itens listados com sucesso")
    })
    public ResponseEntity<List<Item>> getAllItens() {
        List<Item> itens = itemRepository.findAll();
        return ResponseEntity.ok(itens);
    }
    @PostMapping("/cadastrar")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="201", description = "Item cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Não foi possível cadastrar o item")
    })
    public ResponseEntity<Void> cadastrar(@RequestBody ItemDto itemDto) {
        this.itemService.criar(itemDto);
        return ResponseEntity.status(201).build();
    }

}
