package com.example.rent.it.autenticacao.dto.itemDto;

import com.example.rent.it.autenticacao.dto.usuarioDto.UsuarioCriacao;
import com.example.rent.it.autenticacao.dto.usuarioDto.UsuarioToken;
import com.example.rent.it.object.item.Item;
import com.example.rent.it.object.usuario.Usuario;

public class ItemMapper {
    public static Item of(ItemDto itemDto) {
        Item item = new Item();

        item.setCategoria(itemDto.getCategoria());
        item.setNome(itemDto.getNome());
        item.setDescricao(itemDto.getDescricao());
        item.setValorDia(itemDto.getValorDia());
        item.setTempoLocacao(itemDto.getTempoLocacao());
        return item;
    }
    public static ItemToken of(Item item, String token) {
        ItemToken itemToken = new ItemToken();

        itemToken.setId(item.getId());
        itemToken.setCategoria((item.getCategoria()));
        itemToken.setDescricao(item.getDescricao());
        itemToken.setNome(item.getNome());
        itemToken.setTempoLocacao(item.getTempoLocacao());
        itemToken.setValorDia(item.getValorDia());
        itemToken.setToken(token);
        return itemToken;
    }
}
