package com.hyg.proyecto.sistemaventasspringboot;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductosRepository extends CrudRepository<Producto, Integer> {

    Producto findFirstByCodigo(String codigo);

  
}
