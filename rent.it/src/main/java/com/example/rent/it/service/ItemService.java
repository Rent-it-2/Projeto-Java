package com.example.rent.it.service;

import com.example.rent.it.autenticacao.dto.itemDto.ItemCriacaoDto;
import com.example.rent.it.autenticacao.dto.itemDto.ItemDto;
import com.example.rent.it.autenticacao.dto.itemDto.ItemMapper;
import com.example.rent.it.autenticacao.dto.usuarioDto.UsuarioCriacao;
import com.example.rent.it.autenticacao.dto.usuarioDto.UsuarioMapper;
import com.example.rent.it.object.item.Item;
import com.example.rent.it.object.usuario.Usuario;
import com.example.rent.it.repository.CategoriaRepository;
import com.example.rent.it.repository.ItemRepository;
import com.example.rent.it.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {


    private ItemRepository itemRepository;
    private CategoriaRepository categoriaRepository;
    private UsuarioRepository usuarioRepository;

    public ItemService(ItemRepository itemRepository, CategoriaRepository categoriaRepository,
                       UsuarioRepository usuarioRepository) {
        this.itemRepository = itemRepository;
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public void criar(ItemCriacaoDto itemDto) {
        final Item novoItem = ItemMapper.of(itemDto,
                this.categoriaRepository.findById(itemDto.getCategoria()).get(),
                this.usuarioRepository.findById(itemDto.getUsuario()).get());
                this.itemRepository.save(novoItem);
    }
    // Achar todos os itens
    public List<Item> acharTodos(){
        return this.itemRepository.findAll();
    }

    public byte[] buscarFoto(Long id) {

        if(!this.itemRepository.existsFotoById(id)){
            return this.itemRepository.findFotoById(id - id);
        }

        return this.itemRepository.findFotoById(id);
    }

    public boolean atualizaFoto(Long id, byte[] foto) {

        if(this.itemRepository.existsById(id)){

            this.itemRepository.atualizaFoto(id,foto);
            return true;
        }

        return false;
    }

    public List<ItemDto> buscarPorUsuario(Long id) {

        return ItemMapper.of(this.itemRepository.findByUsuarioId(id));
    }
}
