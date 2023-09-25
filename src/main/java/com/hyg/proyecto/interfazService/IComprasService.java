package com.hyg.proyecto.interfazService;
import java.util.List;
import java.util.Optional;

import com.hyg.proyecto.model.Compras;



public interface IComprasService {
    public List<Compras>listarC();
    public Optional<Compras>listarCId(int Id);
    public int saveC(Compras c);
    public void deleteC (int Id);

}
