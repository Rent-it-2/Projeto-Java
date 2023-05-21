package com.example.rent.it.autenticacao.dto.favotirosDto;

public class ItemFavoritarDto {

    private Long usuario;
    private Long item;

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public Long getItem() {
        return item;
    }

    public void setItem(Long item) {
        this.item = item;
    }
}
