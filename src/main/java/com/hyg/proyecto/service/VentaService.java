package com.hyg.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyg.proyecto.interfaces.IVentas;
import com.hyg.proyecto.interfazService.IventaService;
import com.hyg.proyecto.model.Venta;

@Service
public class VentaService implements IventaService {

    @Autowired
    private IVentas data;

    @Override
    public List<Venta> listarV() {

        return (List<Venta>) data.findAll();
    }

    @Override
    public Optional<Venta> listarVId(int Id) {
        return data.findById(Id);
    }

    @Override
    public int saveV(Venta v) {
        int res = 0;
        Venta venta = data.save(v);
        if (venta != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public void deleteV(int Id) {
        data.deleteById(Id);
    }

}
