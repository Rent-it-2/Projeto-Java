package com.example.rent.it.object.item;

import jakarta.persistence.*;

import java.sql.Blob;
@Entity
@Table(name = "Itens")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "foto")
    private Blob foto;
    @Column(name = "categoria")
    private String categoria;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "valorDia")
    private double valorDia;
    @Column(name = "tempoLocacao")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTempoLocacao() {
        return tempoLocacao;
    }

    public void setTempoLocacao(int tempoLocacao) {
        this.tempoLocacao = tempoLocacao;
    }
}
