package com.example.rent.it.object.transacao;

import com.example.rent.it.object.item.ItemAlugavel;

public class Transacao {

    private ItemAlugavel item;
    private double valorTransacao;

    public ItemAlugavel getItem() {
        return item;
    }

    public void setItem(ItemAlugavel item) {
        this.item = item;
    }

    public double getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(double valorTransacao) {
        this.valorTransacao = valorTransacao;
    }
}
