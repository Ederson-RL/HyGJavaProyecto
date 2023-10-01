package com.hyg.proyecto.sistemaventasspringboot;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hyg.proyecto.model.Gastos;



public interface ProductosRepository extends JpaRepository<Producto, Integer> {
    @Query("SELECT p FROM Producto p WHERE p.codigo LIKE %?1%")
    Producto findFirstByCodigo(String codigo);

    
   
}
