package com.example.rent.it.service;

import com.example.rent.it.armazenamento.FilaObj;
import com.example.rent.it.armazenamento.ListaAluguel;
import com.example.rent.it.armazenamento.PilhaObj;
import com.example.rent.it.dto.TransacaoDto.TransacaoMapper;
import com.example.rent.it.dto.TransacaoDto.TransacaoRetornoDto;
import com.example.rent.it.dto.itemDto.ItemDto;
import com.example.rent.it.dto.itemDto.ItemMapper;
import com.example.rent.it.files.TransacaoLeituraTxt;
import com.example.rent.it.files.TransacaoTxt;
import com.example.rent.it.object.categoria.Categoria;
import com.example.rent.it.object.item.Item;
import com.example.rent.it.object.transacao.Transacao;
import com.example.rent.it.object.usuario.Usuario;
import com.example.rent.it.ordenacao.ListaObj;
import com.example.rent.it.repository.CategoriaRepository;
import com.example.rent.it.repository.ItemRepository;
import com.example.rent.it.repository.TransacaoRepository;
import com.example.rent.it.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {
    ListaAluguel listaAluguel = new ListaAluguel();
    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private  UsuarioRepository usuarioRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ItemRepository itemRepository;
    private TransacaoTxt transacaoTxt = new TransacaoTxt();
    private TransacaoLeituraTxt letTxt =new TransacaoLeituraTxt();

    public List<ItemDto> alugaItensEmMassa(Long id, byte[] arquivoByte) {
        String arquivo = new String(arquivoByte);
        Usuario u = this.usuarioRepository.findById(id).get();
        List<Categoria> categorias =  this.categoriaRepository.findAll();

        List<Item> retorno = ItemMapper.of(letTxt.leArquivoTxt(
                transacaoTxt.criaArquivo(u.getNome() +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), arquivoByte ), categorias,u ));

        if(!retorno.isEmpty() &&
           retorno != null){
          return ItemMapper.of(this.itemRepository.saveAll(retorno));
        }

        return null;
    }


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

    public File getAlugados(Long id) {
        PilhaObj<TransacaoRetornoDto> transacoes = TransacaoMapper.of(
                this.transacaoRepository.findAllByFkUsuario(
                        this.usuarioRepository.findById(id).get()));

        return transacaoTxt.getListaDeAlugueis(transacoes);
    }


}
