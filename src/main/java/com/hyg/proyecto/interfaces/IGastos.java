package com.hyg.proyecto.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hyg.proyecto.model.Gastos;


@Repository
public interface IGastos  extends CrudRepository<Gastos,Integer> {
    
}
