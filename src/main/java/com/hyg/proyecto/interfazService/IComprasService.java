package com.hyg.proyecto.interfazService;

import java.util.List;
import com.hyg.proyecto.model.Compras;

public interface IComprasService {

    public List<Compras> ListAllCompras(String palabraClave);

    public void deleteCompras (int idcompra);

    public Compras getCompras(int idcompra);

    public Compras ActualizarCompras(Compras compras);

    public void saveCompras(Compras compras);

}
