package com.example.rent.it.service;

import com.example.rent.it.autenticacao.dto.itemDto.ItemDto;
import com.example.rent.it.autenticacao.dto.itemDto.ItemMapper;
import com.example.rent.it.autenticacao.dto.usuarioDto.UsuarioCriacao;
import com.example.rent.it.autenticacao.dto.usuarioDto.UsuarioMapper;
import com.example.rent.it.object.item.Item;
import com.example.rent.it.object.usuario.Usuario;
import com.example.rent.it.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    public void criar(ItemDto itemDto) {
        final Item novoItem = ItemMapper.of(itemDto);
        this.itemRepository.save(novoItem);
    }
    // Achar todos os itens
    public List<Item> acharTodos(){
        return this.itemRepository.findAll();
    }
}
