package com.hyg.proyecto.Repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.hyg.proyecto.model.Gastos;

@Repository
public interface IGastos  extends  JpaRepository<Gastos,Integer> {

    @Query("SELECT g FROM Gastos g WHERE g.fecha LIKE %?1%")
    public List<Gastos>findAll(String palabraClave);
  
}