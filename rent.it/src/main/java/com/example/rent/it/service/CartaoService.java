package com.example.rent.it.service;

import com.example.rent.it.autenticacao.dto.cartaoDto.CartaoCriacaoDto;
import com.example.rent.it.autenticacao.dto.cartaoDto.CartaoDto;
import com.example.rent.it.autenticacao.dto.cartaoDto.CartaoMapper;
import com.example.rent.it.object.cartao.Cartao;
import com.example.rent.it.repository.CartaoRepository;
import com.example.rent.it.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {

    private final CartaoRepository cartaoRepository;
    private final UsuarioRepository usuarioRepository;

    public CartaoService(CartaoRepository cartaoRepository,
                         UsuarioRepository usuarioRepository) {
        this.cartaoRepository = cartaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public CartaoDto criar(CartaoCriacaoDto cartaoDto, Long id) {
        Cartao cartao = new Cartao();

        cartao = CartaoMapper.of(cartaoDto);
        if(this.usuarioRepository.existsById(id)){
           cartao.setUsuario(this.usuarioRepository.findById(id).get());
           cartao = this.cartaoRepository.save(cartao);

            return CartaoMapper.of(cartao);


        }
         return null;
    }
}
