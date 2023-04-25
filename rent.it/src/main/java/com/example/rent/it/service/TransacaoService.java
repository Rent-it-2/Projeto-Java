package com.example.rent.it.service;

import com.example.rent.it.csv.ListaAluguel;
import com.example.rent.it.object.transacao.Transacao;
import com.example.rent.it.object.usuario.Usuario;
import com.example.rent.it.ordenacao.ListaObj;
import com.example.rent.it.repository.TransacaoRepository;
import com.example.rent.it.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {
    ListaAluguel listaAluguel = new ListaAluguel();
    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private  UsuarioRepository usuarioRepository;

    public File getAlugueis(Long id){

        Optional<Usuario> usu = this.usuarioRepository.findById(id);
         if(!usu.isEmpty()) {
             int count = (this.transacaoRepository.countByFkUsuario(usu.get())).intValue();
             List<Transacao> transacoes = this.transacaoRepository.findAllByFkUsuario(usu.get());
             ListaObj<Transacao> list = new ListaObj<>(count);

             for (int i = 0; i < count; i++) {
                 list.adiciona(transacoes.get(i));

             }

             return listaAluguel.getListaDeAlugueis(list, false);
         }
         return null;
    }

    public File getAlugueisOrdenado(Long id){

        Optional<Usuario> usu = this.usuarioRepository.findById(id);
        if(!usu.isEmpty()) {
            int count = (this.transacaoRepository.countByFkUsuario(usu.get())).intValue();
            List<Transacao> transacoes = this.transacaoRepository.findAllByFkUsuario(usu.get());
            ListaObj<Transacao> list = new ListaObj<>(count);

            for (int i = 0; i < count; i++) {
                list.adiciona(transacoes.get(i));

            }

            return listaAluguel.getListaDeAlugueis(list, true);
        }
        return null;
    }

    public List<Transacao> getTransacoes() {
        return this.transacaoRepository.findAll();
    }

    public Optional<Transacao> getTransacaoPorPreco(int preco, Long id) {
        Optional<Usuario> usu = this.usuarioRepository.findById(id);
        if(!usu.isEmpty()) {
            int count = (this.transacaoRepository.countByFkUsuario(usu.get())).intValue();
            List<Transacao> transacoes = this.transacaoRepository.findAllByFkUsuario(usu.get());
            ListaObj<Transacao> list = new ListaObj<>(count);

            for (int i = 0; i < count; i++) {
                list.adiciona(transacoes.get(i));

            }
           return listaAluguel.encontrarProdutoAlugadoPorPreco(listaAluguel.ordenarPorValor(list), preco);
        }
        return null;
    }
}
