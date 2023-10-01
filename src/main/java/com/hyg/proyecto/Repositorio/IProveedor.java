package com.hyg.proyecto.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.hyg.proyecto.model.Proveedor;

@Repository
public interface IProveedor extends JpaRepository<Proveedor, Long> {
    
    @Query(" SELECT pr FROM Proveedor pr WHERE "+" CONCAT(pr.nombre, pr.id)" +" LIKE %?1%")
    public List<Proveedor> findAll(String palabraClave);




}
