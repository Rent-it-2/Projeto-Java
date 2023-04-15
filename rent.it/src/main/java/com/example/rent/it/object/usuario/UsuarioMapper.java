package com.example.rent.it.object.usuario;

import com.example.rent.it.autenticacao.UsuarioDto;

public class UsuarioMapper {

    public static Usuario of(UsuarioCriacao usuarioCriacaoDto) {
        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setPassword(usuarioCriacaoDto.getPassword());
        usuario.setApelido(usuarioCriacaoDto.getApelido());
        usuario.setTelefone(usuarioCriacaoDto.getTelefone());
        return usuario;
    }

    public static UsuarioToken of(Usuario usuario, String token) {
        UsuarioToken usuarioTokenDto = new UsuarioToken();

        usuarioTokenDto.setId(usuario.getId());
        usuarioTokenDto.setApelido((usuario.getApelido()));
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }
}
