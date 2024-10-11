package com.example.projetoescola.services;

import com.example.projetoescola.dtos.InsereCursoDTO;
import com.example.projetoescola.models.Curso;
import java.util.List;
import com.example.projetoescola.dtos.CursoDTO;

public interface CursoService {
    Curso inserir(InsereCursoDTO curso);

    List<CursoDTO> listarTodos();

    CursoDTO buscarPorId(Long id);
}