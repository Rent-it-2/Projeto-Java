package com.example.rent.it.object.item;

import java.sql.Blob;

public abstract class Item {
    private String nome;
    private Blob foto;
    private String categoria;
    private String descricao;
    private double valorDia;
    private int tempoLocacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    public int getTempoLocacao() {
        return tempoLocacao;
    }

    public void setTempoLocacao(int tempoLocacao) {
        this.tempoLocacao = tempoLocacao;
    }


    public abstract double getTotalLocacao();

    @Override
    public String toString() {
        return "Item{" +
                "nome='" + nome + '\'' +
                ", foto=" + foto +
                ", categoria='" + categoria + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valorDia=" + valorDia +
                ", tempoLocacao=" + tempoLocacao +
                '}';
    }
}
