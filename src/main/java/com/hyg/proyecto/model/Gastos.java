package com.hyg.proyecto.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Gastos")
public class Gastos{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;
    private String fecha;
    private String descripcion;
    private int valor;

    public Gastos() {
    }

    // Constructor que toma todos los campos como parámetros
    public Gastos(int id, String  fecha, String descripcion, int valor) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    // Getters y setters (métodos para acceder y modificar los campos)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String  getFecha() {
        return fecha;
    }

    public void setFecha(String  fecha) {
        this.fecha = fecha;
    }

    public String  getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    


}