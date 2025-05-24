package com.example.sparkv_v1.CLIENTE.Clases;

public class Tarea {
    private String nombreCliente;
    private String direccion;
    private String estado;
    private String horaProgramada; // Opcional: si necesitas mostrar la hora
    private String tipoServicio;  // Opcional: si necesitas mostrar el tipo de servicio

    // Constructor
    public Tarea(String nombreCliente, String direccion, String estado) {
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.estado = estado;
    }

    // Getters y Setters
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getHoraProgramada() {
        return horaProgramada;
    }

    public void setHoraProgramada(String horaProgramada) {
        this.horaProgramada = horaProgramada;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }
}