package com.hyg.proyecto.interfazService;

import java.util.List;
import java.util.Optional;

import com.hyg.proyecto.model.Venta;

public interface IventaService {

    public List<Venta> listarV();

    public Optional<Venta> listarVId(int Id);

    public int saveV(Venta v);

    public void deleteV(int Id);

}
