package com.example.projetoescola.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.projetoescola.models.Curso;
import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByNome(String nome);

}
