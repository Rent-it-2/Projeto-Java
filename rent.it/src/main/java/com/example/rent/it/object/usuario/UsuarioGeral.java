package object.usuario;

import object.item.Item;
import object.item.ItemAlugavel;
import object.item.ItemUsuario;
import object.transacao.Transacao;

import java.util.ArrayList;
import java.util.List;

public class UsuarioGeral extends Usuario {

    List<ItemUsuario> itens;
    List<ItemUsuario> favoritos;

    public UsuarioGeral() {
        this.itens = new ArrayList<>();
        this.favoritos = new ArrayList<>();
    }

    @Override
    public Transacao alugarItem(ItemAlugavel item) {
        return null;
    }

    @Override
    public void addFavorito(ItemAlugavel item) {

    }

    @Override
    public List<Item> exibeFavoritos() {
        return null;
    }

    public void addItem(ItemUsuario item){}

    public List<ItemUsuario> exibeItem(){
        return null;
    }

}
