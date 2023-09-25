package com.hyg.proyecto.interfazService;

import java.util.List;
import java.util.Optional;

import com.hyg.proyecto.model.Gastos;




public interface IgastoService {

    public List<Gastos>listarG();
    public Optional<Gastos>listarGId(int Id);
    public int saveG(Gastos p);
    public void deleteG(int Id);
}
