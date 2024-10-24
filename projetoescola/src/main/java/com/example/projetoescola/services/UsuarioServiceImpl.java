package com.example.projetoescola.services;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.projetoescola.dtos.UsuarioDTO;
import com.example.projetoescola.exceptions.RegraNegocioException;
import com.example.projetoescola.models.Usuario;
import com.example.projetoescola.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Usuario salvar(UsuarioDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setPerfil(dto.getPerfil());

        return usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioDTO obterUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id).map(u -> {
            return UsuarioDTO
                    .builder()
                    .email(u.getEmail())
                    .perfil(u.getPerfil())
                    .build();
        })
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
    }

    @Override
    public List<UsuarioDTO> obterUsuarios() {
        List<UsuarioDTO> dados = usuarioRepository.findAll().stream().map(u -> {
            return UsuarioDTO
                    .builder()
                    .email(u.getEmail())
                    .perfil(u.getPerfil())
                    .build();
        }).toList();

        return dados;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        String[] roles = usuario.getPerfil() == "Administrador" ? new String[] { "ADMIN", "USER" }
                : new String[] { "USER" };
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

    @Override
    public UserDetails autenticar(Usuario usuario) {
        UserDetails user = loadUserByUsername(usuario.getEmail());
        boolean senhaOK = passwordEncoder.matches(usuario.getSenha(), user.getPassword());
        if (senhaOK) {
            return user;
        }
        throw new RegraNegocioException("Senha inválida");
    }
}
