package com.hyg.proyecto.interfaces;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hyg.proyecto.model.Compras;

@Repository
public interface ICompras extends CrudRepository<Compras,Integer> {
    
}
