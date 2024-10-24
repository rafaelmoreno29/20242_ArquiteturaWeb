package com.example.projetoescola.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.projetoescola.dtos.AutenticacaoDTO;
import com.example.projetoescola.dtos.TokenDTO;
import com.example.projetoescola.exceptions.RegraNegocioException;
import com.example.projetoescola.models.Usuario;
import com.example.projetoescola.security.JwtService;
import com.example.projetoescola.services.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AutenticarController {
    private UsuarioService usuarioService;
    private JwtService jwtService;

    public AutenticarController(UsuarioService usuarioService, JwtService jwtService) {
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
    }

    @PostMapping()
    public TokenDTO autenticar(@RequestBody AutenticacaoDTO autenticacao) {
        // try {
        Usuario usuario = new Usuario(0, "", autenticacao.getEmail(), autenticacao.getSenha(), "");
        UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
        String token = jwtService.gerarToken(usuario);
        return new TokenDTO(usuario.getEmail(), token);
        // } catch (UsernameNotFoundException | RegraNegocioException ex) {
        // throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
        // }
    }
}
