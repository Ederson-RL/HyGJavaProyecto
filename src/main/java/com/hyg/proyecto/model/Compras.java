package com.hyg.proyecto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Compras")
public class Compras {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int idcompra;
    private String fechaC;
    private String proveedorC;
    private String productoC;
    private int cantidadC;
    private int precioC;   
    
    public Compras() {
    }

    
    public Compras(int idcompra, String fechaC, String proveedorC, String productoC, int cantidadC, int precioC) {
        this.idcompra = idcompra;
        this.fechaC = fechaC;
        this.proveedorC = proveedorC;
        this.productoC = productoC;
        this.cantidadC = cantidadC;
        this.precioC = precioC;
    }

    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public String getFechaC() {
        return fechaC;
    }

    public void setFechaC(String fechaC) {
        this.fechaC = fechaC;
    }

    public String getProveedorC() {
        return proveedorC;
    }

    public void setProveedorC(String proveedorC) {
        this.proveedorC = proveedorC;
    }

    public String getProductoC() {
        return productoC;
    }

    public void setProductoC(String productoC) {
        this.productoC = productoC;
    }

    public int getCantidadC() {
        return cantidadC;
    }

    public void setCantidadC(int cantidadC) {
        this.cantidadC = cantidadC;
    }

    public int getPrecioC() {
        return precioC;
    }

    public void setPrecioC(int precioC) {
        this.precioC = precioC;
    }

}
