package com.example.projetoescola.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.projetoescola.dtos.UsuarioDTO;
import com.example.projetoescola.models.Usuario;

public interface UsuarioService {
    Usuario salvar(UsuarioDTO dto);

    UsuarioDTO obterUsuarioPorId(Integer id);

    List<UsuarioDTO> obterUsuarios();

    UserDetails autenticar(Usuario usuario);
}
