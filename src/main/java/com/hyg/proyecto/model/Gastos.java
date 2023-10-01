package com.hyg.proyecto.model;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Gastos")
public class Gastos implements Serializable{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;
     @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE) 
    private Date fecha;
    private String descripcion;
    private int valor;

    public Gastos() {
    }


    public Gastos(int id, Date fecha, String descripcion, int valor) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.valor = valor;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date   getFecha() {
        return fecha;
    }

    public void setFecha(Date  fecha) {
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