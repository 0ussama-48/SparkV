package com.example.sparkv_v1.ADMIN.Clases;

import java.io.Serializable;

public class Item implements Serializable {
    private String nombre;
    private Double precio;
    private String categoria;
    private String duracion;
    private String idServicio;

    public Item(String nombre, Double precio, String categoria, String duracion, String idServicio) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.duracion = duracion;
        this.idServicio = idServicio;
    }

    public String getNombre() { return nombre; }
    public Double getPrecio() { return precio; }
    public String getCategoria() { return categoria; }
    public String getDuracion() { return duracion; }
    public String getIdServicio() { return idServicio; }
}