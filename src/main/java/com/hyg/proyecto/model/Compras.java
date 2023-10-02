package com.hyg.proyecto.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Compras")
public class Compras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcompra;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE) // Esto indica que se debe tratar como una fecha sin hora.
    private Date fechaC;
    private String proveedorC;
    private String productoC;
    private int cantidadC;
    private int precioC;

    @ManyToOne
    @JoinColumn(name = "proveedor_id") // Nombre de la columna de la clave foránea
    private Proveedor proveedor;

    public Compras() {

    }

    public Compras(int idcompra, Date fechaC, String proveedorC, String productoC, int cantidadC, int precioC) {
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

    public Date getFechaC() {
        return fechaC;
    }

    public void setFechaC(Date fechaC) {
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
