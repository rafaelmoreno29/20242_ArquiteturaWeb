package com.example.projetoescola.services;

import com.example.projetoescola.dtos.InsereCursoDTO;
import com.example.projetoescola.models.Curso;

public interface CursoService {
    Curso inserir(InsereCursoDTO curso);
}