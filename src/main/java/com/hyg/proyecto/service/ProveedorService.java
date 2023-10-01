package com.hyg.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hyg.proyecto.Repositorio.IProveedor;
import com.hyg.proyecto.interfazService.IProveedorService;
import com.hyg.proyecto.model.Proveedor;


@Service
public class ProveedorService implements IProveedorService {

    @Autowired
    private IProveedor ProveedorRepository;

    @Override
    public List<Proveedor> ListAllProveedor(String palabraClave) {
        if(palabraClave !=null){
            return ProveedorRepository.findAll(palabraClave);
        }

        return ProveedorRepository.findAll();
        
    }

    @Override
    public Proveedor ActualizarProveedor(Proveedor proveedor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ActualizarProveedor'");
    }

    @Override
    public void saveProveedor(Proveedor proveedor) {
        ProveedorRepository.save(proveedor);
    }

    @Override
    public void deleteProveedor(long id) {
        Proveedor proveedor = ProveedorRepository.findById(id).get();
        if (proveedor != null) {
            ProveedorRepository.deleteById(id);
        }
    }

    @Override
    public Proveedor getProveedor(long id) {

        return ProveedorRepository.findById(id).get();

    }
    
}
