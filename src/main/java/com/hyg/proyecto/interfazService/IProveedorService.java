package com.hyg.proyecto.interfazService;

import java.util.List;

import com.hyg.proyecto.model.Proveedor;

public interface IProveedorService {

    public List <Proveedor>  ListAllProveedor(String palabraClave);

    public void deleteProveedor (long id);

    public Proveedor getProveedor(long id);

    public Proveedor ActualizarProveedor(Proveedor proveedor);

    public void saveProveedor(Proveedor proveedor);

}