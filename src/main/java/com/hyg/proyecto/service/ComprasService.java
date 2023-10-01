package com.hyg.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyg.proyecto.Repositorio.ICompras;
import com.hyg.proyecto.interfazService.IComprasService;
import com.hyg.proyecto.model.Compras;




@Service
public class ComprasService implements IComprasService{
    @Autowired
    private ICompras ComprasRepository;


    @Override
    public List<Compras> ListAllCompras(String palabraClave) {
        if(palabraClave !=null){
            return ComprasRepository.findAll(palabraClave);
        }
        return ComprasRepository.findAll();
    }



    @Override

    public Compras getCompras(int idcompra) {
        return ComprasRepository.findById(idcompra).get();

    }

    @Override
    public Compras ActualizarCompras(Compras compras) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ActualizarCompras'");
    }

    @Override
    public void saveCompras(Compras compras) {
        ComprasRepository.save(compras);
    }
    @Override
    public void deleteCompras(int idcompra) {
        Compras compras =ComprasRepository.findById(idcompra).get();
        if (compras != null) {
            ComprasRepository.deleteById(idcompra);
        }
    }
    
}
