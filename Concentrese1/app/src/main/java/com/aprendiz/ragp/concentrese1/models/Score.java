package com.aprendiz.ragp.concentrese1.models;

public class Score {
    private String nombre;
    private int punutacion;
    private String modo;
    private String dificultad;

    public Score() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPunutacion() {
        return punutacion;
    }

    public void setPunutacion(int punutacion) {
        this.punutacion = punutacion;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }
}
