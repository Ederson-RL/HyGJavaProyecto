package com.hyg.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyg.proyecto.interfaces.ICompras;
import com.hyg.proyecto.interfazService.IComprasService;
import com.hyg.proyecto.model.Compras;



@Service
public class ComprasService implements IComprasService{
    @Autowired
    private ICompras data;
    @Override
    public List<Compras> listarC() {
         return (List<Compras>)data.findAll();        
    }

    @Override
    public Optional<Compras> listarCId(int Id) {
        return data.findById(Id);
    }

    @Override
    public int saveC(Compras c) {
         int res=0;
        Compras compras=data.save(c);
        if (!compras.equals(null)){
            res=1;
        }

        return res;
    }

    @Override
    public void deleteC(int idcompra) {
        data.deleteById(idcompra);
    }

    
}
