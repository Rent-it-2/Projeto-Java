package com.example.rent.it.autenticacao.dto.itemDto;
import com.example.rent.it.object.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemMapper {
    public static Item of(ItemDto itemDto) {
        Item item = new Item();

//        item.setCategoria(itemDto.getCategoria());
        item.setNome(itemDto.getNome());
        item.setDescricao(itemDto.getDescricao());
        item.setValorDia(itemDto.getValorDia());
      //  item.setTempoLocacao(itemDto.getTempoLocacao());
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

    public static List<ItemDto> of(List<Item> itens){
        List<ItemDto> itensDto = new ArrayList<>();
        ItemDto itemDto;

        for (int i = 0; i < itens.size() - 1;i++ ) {
           itemDto  = new ItemDto();
           itemDto.setId(itens.get(i).getId());
           itemDto.setNome(itens.get(i).getNome());
          // itemDto.setCategoria(itens.get(i).getCategoria());
           itemDto.setValorDia(itens.get(i).getValorDia());
           itensDto.add(itemDto);

        }
       return itensDto;
    }
}
