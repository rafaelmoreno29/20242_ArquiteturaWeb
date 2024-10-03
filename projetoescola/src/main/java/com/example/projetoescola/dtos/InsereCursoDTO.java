package com.example.projetoescola.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsereCursoDTO {
    private Long id;
    private String nome;
    private Integer cargaHoraria;
    private Integer categoriaCursoId;
}
