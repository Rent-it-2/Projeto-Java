package com.example.rent.it.autenticacao.dto.favotirosDto;

import com.example.rent.it.object.favoritos.Favoritos;
import com.example.rent.it.object.item.Item;
import com.example.rent.it.object.usuario.Usuario;

public class FavoritosMapper {

    public static Favoritos of(Usuario usuario, Item item){
        final Favoritos favorito = new Favoritos();

        favorito.setFkItem(item);
        favorito.setUsuario(usuario);

        return favorito;
    }
}
