package com.example.rent.it.object.item;

public class ItemAlugavel extends Item {

    private Boolean isFavorito;


    public Boolean getFavorito() {
        return isFavorito;
    }

    public void setFavorito(Boolean favorito) {
        this.isFavorito = favorito;
    }

    @Override
    public double getTotalLocacao() {
        return 0;
    }

    @Override
    public String toString() {
        return "ItemUsuario{" +
                "isFavorito=" + isFavorito +
                '}';


    }
}
