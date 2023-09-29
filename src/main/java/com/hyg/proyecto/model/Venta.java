package com.hyg.proyecto.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String producto;
    private String fecha;
    private int cantidad;
    private int precio;

    public Venta() {
    }

    // Constructor que toma todos los campos como parámetros
    public Venta(int id, String producto, String fecha, int cantidad, int precio) {
        this.id = id;
        this.producto = producto;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // Getters y setters (métodos para acceder y modificar los campos)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
