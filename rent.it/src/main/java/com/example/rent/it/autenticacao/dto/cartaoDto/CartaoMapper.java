package com.example.rent.it.autenticacao.dto.cartaoDto;

import com.example.rent.it.object.cartao.Cartao;

public class CartaoMapper {

    public static CartaoDto of(Cartao cartao){
          final CartaoDto cartaoDto = new CartaoDto();

         cartaoDto.setNumCartao(cartao.getNumCartao());
         cartaoDto.setValidade(cartao.getValidade());
         cartaoDto.setNomeUsuario(cartao.getUsuario().getNome());
         return cartaoDto;
    }

    public static Cartao of(CartaoCriacaoDto dto){

        final Cartao cartao = new Cartao();
        cartao.setCpfTitular(dto.getCpfTitular().replace(".", ""));
        cartao.setNomeImpresso(dto.getNomeImpresso());
        cartao.setNumCartao(dto.getNumCartao().replace(" ",""));
        cartao.setValidade(dto.getValidade());

        return cartao;

    }
}
