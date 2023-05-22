package com.example.rent.it.service;

import com.example.rent.it.autenticacao.dto.enderecoDto.EnderecoCriacao;
import com.example.rent.it.autenticacao.dto.enderecoDto.EnderecoMapper;
import com.example.rent.it.autenticacao.dto.usuarioDto.UsuarioCriacao;
import com.example.rent.it.autenticacao.dto.usuarioDto.UsuarioMapper;
import com.example.rent.it.object.endereco.Endereco;
import com.example.rent.it.object.usuario.Usuario;
import com.example.rent.it.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public void criar(EnderecoCriacao enderecoCriacaoDto) {
        final Endereco novoEndereco = EnderecoMapper.of(enderecoCriacaoDto);
        this.enderecoRepository.save(novoEndereco);
    }
}
