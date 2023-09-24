package com.hyg.proyecto.interfaces;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.hyg.proyecto.model.Producto;

@Repository
public interface IProducto extends CrudRepository<Producto, Integer> {

}
