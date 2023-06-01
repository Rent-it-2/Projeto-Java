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

    public void criar(EnderecoCriacao enderecoCriacaoDto) {
        final Endereco novoEndereco = EnderecoMapper.of(enderecoCriacaoDto,
                usuarioRepository.findById(enderecoCriacaoDto.getUsuario()).get());
        this.enderecoRepository.save(novoEndereco);
    }

    public List<EnderecoDto> findByUsuario(Long id) {

        return EnderecoMapper.of(this.enderecoRepository.findAllByUsuario(
                this.usuarioRepository.findById(id).get()
        ));
    }
}
