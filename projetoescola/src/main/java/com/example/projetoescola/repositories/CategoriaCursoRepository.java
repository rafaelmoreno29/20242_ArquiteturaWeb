package com.example.projetoescola.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetoescola.models.CategoriaCurso;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;

public interface CategoriaCursoRepository
        extends JpaRepository<CategoriaCurso, Integer> {
}
