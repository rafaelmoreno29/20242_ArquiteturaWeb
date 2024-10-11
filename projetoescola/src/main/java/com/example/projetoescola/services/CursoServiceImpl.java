package com.example.projetoescola.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.projetoescola.dtos.CursoDTO;
import com.example.projetoescola.dtos.InsereCursoDTO;
import com.example.projetoescola.exceptions.RegraNegocioException;
import com.example.projetoescola.models.CategoriaCurso;
import com.example.projetoescola.models.Curso;
import com.example.projetoescola.repositories.CategoriaCursoRepository;
import com.example.projetoescola.repositories.CursoRepository;
import java.util.stream.Collectors;

@Service
public class CursoServiceImpl implements CursoService {
    private CursoRepository cursoRepository;
    private CategoriaCursoRepository categoriaCursoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository,
            CategoriaCursoRepository categoriaCursoRepository) {
        this.cursoRepository = cursoRepository;
        this.categoriaCursoRepository = categoriaCursoRepository;
    }

    @Override
    public Curso inserir(InsereCursoDTO curso) {
        CategoriaCurso categ = categoriaCursoRepository.findById(curso.getCategoriaCursoId())
                .orElseThrow(() -> new RegraNegocioException("Categoria não encontrada"));

        Curso cursoObj = new Curso();
        cursoObj.setNome(curso.getNome());
        cursoObj.setCargaHoraria(curso.getCargaHoraria());
        cursoObj.setCategoriaCurso(categ);

        return cursoRepository.save(cursoObj);
    }

    @Override
    public List<CursoDTO> listarTodos() {
        List<Curso> cursos = cursoRepository.findAll();

        return cursos.stream().map((Curso c) -> {
            /*
             * return new CursoDTO(
             * c.getId(), c.getNome(), c.getCargaHoraria(),
             * c.getCategoriaCurso() != null ? c.getCategoriaCurso().getId() : 0,
             * c.getCategoriaCurso() != null ? c.getCategoriaCurso().getNome() : "");
             */
            return CursoDTO.builder()
                    .id(c.getId())
                    .nome(c.getNome())
                    .cargaHoraria(c.getCargaHoraria())
                    .categoriaCursoId(
                            c.getCategoriaCurso() != null ? c.getCategoriaCurso().getId() : 0)
                    .categoriaCursoNome(c.getCategoriaCurso() != null ? c.getCategoriaCurso().getNome() : "")
                    .build();
        }).collect(Collectors.toList());

    }

    @Override
    public CursoDTO buscarPorId(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Curso não encontrado"));

        return CursoDTO.builder()
                .id(curso.getId())
                .nome(curso.getNome())
                .cargaHoraria(curso.getCargaHoraria())
                .categoriaCursoId(
                        curso.getCategoriaCurso() != null ? curso.getCategoriaCurso().getId() : 0)
                .categoriaCursoNome(
                        curso.getCategoriaCurso() != null ? curso.getCategoriaCurso().getNome() : "")
                .build();
    }
}
