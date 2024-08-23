package com.example.aula1.models;

public class NumeroDTO {
    private Double numero1;
    private Double numero2;

    public NumeroDTO(Double numero1, Double numero2) {
        this.numero1 = numero1;
        this.numero2 = numero2;
    }

    public Double somar() {
        return numero1 + numero2;
    }

    public NumeroDTO() {
    }

    public Double getNumero1() {
        return numero1;
    }

    public void setNumero1(Double numero1) {
        this.numero1 = numero1;
    }

    public Double getNumero2() {
        return numero2;
    }

    public void setNumero2(Double numero2) {
        this.numero2 = numero2;
    }

}
