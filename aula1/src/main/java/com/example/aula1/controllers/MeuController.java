package com.example.aula1.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.aula1.models.NumeroDTO;

import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/meu-controller")
public class MeuController {

    @GetMapping("/hello")
    public String getMethodName() {
        return "Hello World";
    }

    @GetMapping("/calcular-dobro")
    public int getDobroNumero(@RequestParam int num1) {
        return num1 * 2;
    }

    @GetMapping("/calcular-metade/{num1}")
    public int getMetadeNumero(@PathVariable("num1") int num) {
        return num / 2;
    }

    @PostMapping("calcular-soma")
    public Double postSomar(@RequestBody NumeroDTO numero) {
        return numero.somar();
    }

}
