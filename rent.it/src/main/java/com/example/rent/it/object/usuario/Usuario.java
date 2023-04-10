package com.example.rent.it.object.usuario;

import com.example.rent.it.object.item.Item;
import com.example.rent.it.object.item.ItemAlugavel;
import com.example.rent.it.object.transacao.Transacao;
import jakarta.persistence.*;

import java.sql.Blob;
import java.util.List;
@Entity
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String apelido;

    @Column(unique = true)
    private String email;
    private String senha;
    private Blob foto;
    @Column(unique = true)
    private String telefone;
    private double avaliacao;
    private List<ItemAlugavel> favoritos;
    private List<Transacao> transacoes;

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

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public List<ItemAlugavel> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<ItemAlugavel> favoritos) {
        this.favoritos = favoritos;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }
}
