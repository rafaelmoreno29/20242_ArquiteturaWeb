package com.example.projetoescola.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.projetoescola.models.CategoriaCurso;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public class CategoriaCursoRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public CategoriaCurso salvar(CategoriaCurso categoriaCurso) {
        categoriaCurso = entityManager.merge(categoriaCurso);
        return categoriaCurso;
    }

    public List<CategoriaCurso> obterTodos() {
        return entityManager
                .createQuery("SELECT c FROM CategoriaCurso c",
                        CategoriaCurso.class)
                .getResultList();
    }
}
