package com.hyg.proyecto.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hyg.proyecto.model.Venta;





@Repository
public interface IVentas extends CrudRepository<Venta,Integer> {
    
}
