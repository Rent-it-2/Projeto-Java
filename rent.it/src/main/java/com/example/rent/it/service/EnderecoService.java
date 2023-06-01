package com.example.rent.it.service;

import com.example.rent.it.dto.enderecoDto.EnderecoCriacao;
import com.example.rent.it.dto.enderecoDto.EnderecoDto;
import com.example.rent.it.dto.enderecoDto.EnderecoMapper;
import com.example.rent.it.object.endereco.Endereco;
import com.example.rent.it.repository.EnderecoRepository;
import com.example.rent.it.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private  UsuarioRepository usuarioRepository;

    public EnderecoDto criar(EnderecoCriacao enderecoCriacaoDto) {
        final Endereco novoEndereco = EnderecoMapper.of(enderecoCriacaoDto,
                usuarioRepository.findById(enderecoCriacaoDto.getUsuario()).get());

          if(!this.enderecoRepository.existsByUsuario(novoEndereco.getUsuario())) {
             return EnderecoMapper.of(this.enderecoRepository.save(novoEndereco));
          }else{
              return null;
          }

    }

    public EnderecoDto findByUsuario(Long id) {

        return EnderecoMapper.of(this.enderecoRepository.findByUsuario(
                this.usuarioRepository.findById(id).get()
        ));
    }
}
