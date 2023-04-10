package com.example.rent.it.object.item;

public class ItemUsuario extends Item {

    private Boolean isDisponivel;


    public Boolean getDisponivel() {
        return isDisponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        isDisponivel = disponivel;
    }

    @Override
    public double getTotalLocacao() {
        return 0;
    }

    @Override
    public String toString() {
        return "ItemUsuario{" +
                "isDisponivel=" + isDisponivel +
                '}';
    }
}
