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

    public File getListaDeAlugueis(ListaObj<Transacao> transacoes) {
        File file = null;
        File fileBack = null;
        String nomeArq = "";
        FileWriter arq = null;
        Formatter saida = null;

        if (!this.diretorioBase.toFile().exists()) {
            this.diretorioBase.toFile().mkdir();
        }

        try {

            nomeArq = transacoes.getElemento(1).getFkUsuario().getNome();
            boolean ok = true;
            nomeArq += ".csv";
            String id = "Id", nome = "Nome", dtInicio = "DataInicio", dtFim = "DataFim", valor = "Valor";

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
                saida.format("%s;%s;%s;%s;%s\n", id, nome, dtInicio, dtFim, valor);
                for (int i = 0; i < transacoes.getTamanho(); i++) {
                    Transacao transacao = transacoes.getElemento(i);

                    saida.format("%d;%s;%s;%s;%.2f\n",
                            transacao.getIdTransacao(),
                            transacao.getFkUsuario().getNome(),
                            transacao.getDtInicio(),
                            transacao.getDtFim(),
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

}
