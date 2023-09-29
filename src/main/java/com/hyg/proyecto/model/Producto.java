package com.hyg.proyecto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String NombreP;
    private int cantidadP;
    private int precio;

    public Producto() {

        this.id = 0;
        this.NombreP = "";
        this.cantidadP = 0;
        this.precio = 0;
    }

    public Producto(int id, String NombreP, int cantidadP, int precio) {

        this.id = id;
        this.NombreP = NombreP;
        this.cantidadP = cantidadP;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreP() {
        return NombreP;
    }

    public void setNombreP(String NombreP) {
        this.NombreP = NombreP;
    }

    public int getCantidadP() {
        return cantidadP;
    }

    public void setCantidadP(int cantidadP) {
        this.cantidadP = cantidadP;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}