package com.hyg.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyg.proyecto.interfaces.IGastos;
import com.hyg.proyecto.interfazService.IgastoService;
import com.hyg.proyecto.model.Gastos;




@Service
public class GastosService implements IgastoService {


     @Autowired
    private IGastos data;
    @Override
    public List<Gastos> listarG() {
        return (List<Gastos>)data.findAll();
    }

    @Override
    public Optional<Gastos> listarGId(int Id) {
        return data.findById(Id);
    }

    @Override
    public int saveG(Gastos g) {
        int res=0;
        Gastos gastos=data.save(g);
        if (!gastos.equals(null)){
            res=1;
        }

        return res;
    }

    @Override
    public void deleteG(int Id) {
        data.deleteById(Id);
    }
    
}
