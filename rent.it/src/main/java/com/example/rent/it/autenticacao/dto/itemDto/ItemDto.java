package com.example.rent.it.autenticacao.dto.itemDto;

import java.sql.Blob;

public class ItemDto {
    private Long id;


    private String nome;

    private String descricao;
    private double valorDia;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorDia() {
        return valorDia;
    }

    public void setValorDia(double valorDia) {
        this.valorDia = valorDia;
    }


}
