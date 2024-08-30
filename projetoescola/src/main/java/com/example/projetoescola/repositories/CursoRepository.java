package com.example.projetoescola.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.projetoescola.models.Curso;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class CursoRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Curso salvar(Curso curso) {
        entityManager.merge(curso);
        return curso;
    }

    public List<Curso> obterTodos() {
        return entityManager
                .createQuery("from Curso", Curso.class).getResultList();
    }

    @Transactional
    public void excluir(Curso curso) {
        entityManager.remove(curso);
    }

    public List<Curso> obterPorNome(String nome) {
        String jpql = " select c from Curso c where c.nome like :nome";
        TypedQuery<Curso> query = entityManager.createQuery(jpql, Curso.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

}
