package com.example.rent.it.dto.avaliacao;

import com.example.rent.it.object.transacao.Transacao;

public class TransacaoAvaliacaoDto {
    private Long idItem;
    private Double nota;

    public Long getTransacao() {
        return idItem;
    }

    public void setTransacao(Long idItem) {
        this.idItem = idItem;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}
