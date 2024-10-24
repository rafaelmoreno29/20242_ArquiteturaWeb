package com.example.projetoescola.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projetoescola.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmail(String email);
}
