package com.example.sparkv_v1.LIMPIADOR.Clases;

public class Limpiador {
    private String idLimpiador;
    private String email; // 👈 Nuevo campo: reemplazamos "nombre" por "email"
    private String especialidad;
    private String zonaCobertura;
    private float calificacionPromedio;
    private int tareasCompletadas;

    // Constructor vacío (requerido para Firebase)
    public Limpiador() {}

    // Constructor con parámetros
    public Limpiador(String idLimpiador, String email, String especialidad, String zonaCobertura, float calificacionPromedio, int tareasCompletadas) {
        this.idLimpiador = idLimpiador;
        this.email = email;
        this.especialidad = especialidad;
        this.zonaCobertura = zonaCobertura;
        this.calificacionPromedio = calificacionPromedio;
        this.tareasCompletadas = tareasCompletadas;
    }

    // Getters y Setters

    public String getId() {
        return idLimpiador;
    }

    public void setIdLimpiador(String idLimpiador) {
        this.idLimpiador = idLimpiador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getZonaCobertura() {
        return zonaCobertura;
    }

    public void setZonaCobertura(String zonaCobertura) {
        this.zonaCobertura = zonaCobertura;
    }

    public float getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public void setCalificacionPromedio(float calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }

    public int getTareasCompletadas() {
        return tareasCompletadas;
    }

    public void setTareasCompletadas(int tareasCompletadas) {
        this.tareasCompletadas = tareasCompletadas;
    }
}