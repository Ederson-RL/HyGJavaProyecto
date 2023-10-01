package com.hyg.proyecto.interfazService;

import java.util.List;

import com.hyg.proyecto.model.Gastos;

public interface IgastoService {

    public List <Gastos>  ListAll(String palabraClave);

    public void deleteG (int id);

    public Gastos getGastos(int id);

    public Gastos Actualizargastos(Gastos gastos);

    public void saveG(Gastos gastos);
}
