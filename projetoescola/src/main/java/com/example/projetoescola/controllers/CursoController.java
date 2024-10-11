package com.example.projetoescola.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.projetoescola.dtos.InsereCursoDTO;
import com.example.projetoescola.models.Curso;
import com.example.projetoescola.repositories.CursoRepository;
import com.example.projetoescola.services.CursoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.projetoescola.dtos.CursoDTO;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/curso")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping("{id}")
    public CursoDTO getCursoPorId(@PathVariable Long id) {
        return cursoService.buscarPorId(id);
    }

    @GetMapping()
    public List<CursoDTO> getCursos() {
        return cursoService.listarTodos();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void postCurso(@RequestBody InsereCursoDTO curso) {
        cursoService.inserir(curso);
    }

}
