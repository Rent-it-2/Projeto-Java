package com.example.rent.it.dto.enderecoDto;

import com.example.rent.it.object.endereco.Endereco;

public class EnderecoMapper {
    public static Endereco of(EnderecoCriacao enderecoCriacaoDto) {
        Endereco endereco = new Endereco();

        endereco.setBairro(enderecoCriacaoDto.getBairro());
        endereco.setCep(enderecoCriacaoDto.getCep());
        endereco.setCidade(enderecoCriacaoDto.getCidade());
        endereco.setComplemento(enderecoCriacaoDto.getComplemento());
        endereco.setEstado(enderecoCriacaoDto.getEstado());
        endereco.setNumero(enderecoCriacaoDto.getNumero());
        endereco.setLogradouro(enderecoCriacaoDto.getLogradouro());
        return endereco;
    }
}
