package com.example.rent.it.object.usuario;

import com.example.rent.it.object.item.Item;
import com.example.rent.it.object.item.ItemAlugavel;
import com.example.rent.it.object.item.ItemUsuario;
import com.example.rent.it.object.transacao.Transacao;

import java.util.ArrayList;
import java.util.List;

public class UsuarioLocador extends Usuario {
    private String contaBancaria;
    private List<ItemUsuario> itens;

    public String getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(String contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public List<ItemUsuario> getItens() {
        return itens;
    }

    public void setItens(List<ItemUsuario> itens) {
        this.itens = itens;
    }

    public UsuarioLocador() {
        this.itens = new ArrayList<>();
    }

    public void addItem(ItemUsuario item){
        this.itens.add(item);
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



    @Override
    public String toString() {
        return "UsuarioLocador{" +
                "contaBancaria='" + contaBancaria + '\'' +
                ", itens=" + itens +
                '}';
    }


}
