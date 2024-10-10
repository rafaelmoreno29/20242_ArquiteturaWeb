package com.example.projetoescola.dtos;

import lombok.Getter;
import java.util.*;

public class ApiErrorDTO {
    @Getter
    private List<String> errors;

    public ApiErrorDTO(String mensagem) {
        this.errors = Arrays.asList(mensagem);
    }

}
