package com.hyg.proyecto.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hyg.proyecto.Repositorio.IGastos;
import com.hyg.proyecto.interfazService.IgastoService;
import com.hyg.proyecto.model.Gastos;


@Service
public class GastosService implements IgastoService {

    @Autowired
    private IGastos GastoRepository;
 

    @Override
    public List<Gastos> ListAll(String palabraClave) {
        if(palabraClave !=null){
            return GastoRepository.findAll(palabraClave);
        }

        return GastoRepository.findAll();
        
    }

    public void saveG (Gastos gastos){
       GastoRepository.save(gastos);
    }

    public Gastos getGastos(int id){
        return GastoRepository.findById(id).get();

    }



    @Override
    public void deleteG(int id) {
        Gastos gastos =GastoRepository.findById(id).get();
        if (gastos != null) {
            GastoRepository.deleteById(id);
        }
    }

    @Override
    public Gastos Actualizargastos(Gastos gastos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Actualizargastos'");
    }
    
}
