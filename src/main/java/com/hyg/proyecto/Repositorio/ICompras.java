package com.hyg.proyecto.Repositorio;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.hyg.proyecto.model.Compras;


@Repository
public interface ICompras extends JpaRepository<Compras,Integer> {
    @Query("SELECT c FROM Compras c WHERE c.fechaC LIKE %?1%")
    public List<Compras>findAll(String palabraClave);
}
