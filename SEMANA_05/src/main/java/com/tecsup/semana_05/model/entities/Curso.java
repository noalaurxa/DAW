package com.tecsup.semana_05.model.entities;

public class Curso {

    private String codigo;
    private String nombres;
    private int creditos;

    public Curso() {
    }

    public Curso(String codigo, String nombres, int creditos) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.creditos = creditos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "codigo='" + codigo + '\'' +
                ", nombres='" + nombres + '\'' +
                ", creditos=" + creditos +
                '}';
    }
}
