package com.hyg.proyecto.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyg.proyecto.interfazService.IventaService;
import com.hyg.proyecto.model.Venta;
import com.hyg.proyecto.service.reporteVenta.VentasExcel;





@Controller
@RequestMapping
public class controllerV {
    @Autowired
    private IventaService service;
    
    @GetMapping("/listarV")
    public String listarV(Model model){
        List<Venta>ventas=service.listarV();
        model.addAttribute("venta", ventas);
        return "indexV";

    }
        @GetMapping("/newV")
    public String agregar(Model model){
        
        model.addAttribute("venta", new Venta());
        return "formV";

    }
    @PostMapping("/saveV")
    public String saveV(@Validated Venta v, Model model){
        
        service.saveV(v);
        return "redirect:/listarV";

    }
    @GetMapping("/editarV/{id}")
    public String editarV(@PathVariable int id, Model model){
         Optional<Venta>Venta=service.listarVId(id);
        model.addAttribute("venta",  Venta);
        return "formV";
}
   @GetMapping("/eliminarV/{id}")
    public String deleteV( Model model,@PathVariable int id){
        service.deleteV(id);
        return "redirect:/listar";

    }
        @GetMapping("/ventas/exportV/excelV")
    public void exportToExcel(HttpServletResponse response) throws IOException {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=Ventas"+ currentDateTime +".xlsx";
            response.setHeader(headerKey, headerValue);
            List<Venta> VentaList = service.listarV();
            VentasExcel excelExporter = new VentasExcel(VentaList);
            excelExporter.export(response);
     }

}
