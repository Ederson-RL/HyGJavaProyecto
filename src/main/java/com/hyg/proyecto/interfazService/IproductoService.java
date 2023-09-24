package com.hyg.proyecto.interfazService;

import java.util.List;
import java.util.Optional;

import com.hyg.proyecto.model.Producto;

public interface IproductoService {

    public List<Producto> listar();

    public Optional<Producto> listarId(int Id);

    public int save(Producto p);

    public void delete(int Id);

}
