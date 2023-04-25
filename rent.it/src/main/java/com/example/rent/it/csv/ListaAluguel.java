package com.example.rent.it.csv;

import com.example.rent.it.object.transacao.Transacao;
import com.example.rent.it.object.usuario.Usuario;
import com.example.rent.it.ordenacao.ListaObj;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;

public class ListaAluguel {
    private Path diretorioBase = Path.of(System.getProperty("java.io.tmpdir") + "/arquivos"); // temporario

    public File getListaDeAlugueis(ListaObj<Transacao> transacoes, boolean encontrarMedia) {
        File file = null;
        File fileBack = null;
        String nomeArq = "";
        FileWriter arq = null;
        Formatter saida = null;

        if (!this.diretorioBase.toFile().exists()) {
            this.diretorioBase.toFile().mkdir();
        }

        if(encontrarMedia){

            transacoes = ordenarPorValor(transacoes);
            Transacao t = encontrarMedia(transacoes);
        }

        try {

            nomeArq = transacoes.getElemento(1).getFkUsuario().getNome();
            boolean ok = true;
            nomeArq += ".csv";
            String id = "Id", nome = "Nome", dtInicio = "DataInicio", dtFim = "DataFim", valor = "ValorDia", valorTotal = "ValorTotal";

            try {
                file = new File(this.diretorioBase + "/" + nomeArq);

                arq = new FileWriter(file);
                saida = new Formatter(arq);

            } catch (IOException e) {
                System.out.println("erro ao abrir o arquivo");
                e.printStackTrace();
                System.exit(1);

            }

            try {
                saida.format("%s;%s;%s;%s;%s;%s\n", id, nome, dtInicio, dtFim, valor, valorTotal);
                for (int i = 0; i < transacoes.getTamanho(); i++) {
                    Transacao transacao = transacoes.getElemento(i);

                    saida.format("%d;%s;%s;%s;%.2f;%.2f\n",
                            transacao.getIdTransacao(),
                            transacao.getFkUsuario().getNome(),
                            transacao.getDtInicio(),
                            transacao.getDtFim(),
                            transacao.getFkItem().getValorDia(),
                            transacao.getFkItem().getValorDia() * (transacao.getDtFim().compareTo(transacao.getDtInicio())));

                }

            } catch (FormatterClosedException e) {
                System.out.println("Erro ao gravar o arquivo");
                ok = false;

            } finally {
                saida.close();
                try {

                    arq.close();


                } catch (IOException e) {
                    System.out.println("erro ao fechar o arquivo");
                    ok = false;
                }
            }
            if (!ok) {
                System.exit(1);
            }

            fileBack = file;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return fileBack;


    }

    public ListaObj<Transacao> ordenarPorValor(ListaObj<Transacao> transacoes){

      for (int i = 0; i < transacoes.getTamanho(); i++){

          for (int j = 0; j < transacoes.getTamanho(); j++){
              if(transacoes.getElemento(j + 1) == null){
                  break;
              }
              if (transacoes.getElemento(j).getFkItem().getValorDia()
                      > transacoes.getElemento(j + 1).getFkItem().getValorDia()) {
                      Transacao aux = transacoes.getElemento(j);

                      transacoes.adicionaNoIndice(transacoes.getElemento(j + 1), j);
                      transacoes.adicionaNoIndice(aux, j + 1);

              }

          }

      }
      return transacoes;
    }

    public Transacao encontrarMedia(ListaObj<Transacao> transacoes){
    return null;
    }

}
