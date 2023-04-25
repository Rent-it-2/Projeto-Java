package com.example.rent.it.api.controllers;

import com.example.rent.it.autenticacao.dto.itemDto.ItemDto;
import com.example.rent.it.autenticacao.dto.itemDto.ItemMapper;
import com.example.rent.it.object.item.Item;
import com.example.rent.it.repository.ItemRepository;
import com.example.rent.it.service.ItemService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/itens")
public class itemController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;


    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado Item com o ID especificado")
    })
    public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
        if (this.itemRepository.existsById(id)) {
            item.setId(id);
            return this.itemRepository.save(item);
        }
        return null;
    }
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado Item com o ID especificado")
    })
    public void deleteItem(@PathVariable Long id) {
        itemRepository.deleteById(id);
    }

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
    public List<ItemDto> getAllresumidos() {

        return ItemMapper.of(this.itemService.acharTodos());
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
