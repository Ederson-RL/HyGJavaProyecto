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

import com.hyg.proyecto.interfazService.IComprasService;
import com.hyg.proyecto.model.Compras;
import com.hyg.proyecto.service.reporteCompra.CompraExcel;

@Controller
@RequestMapping
public class controllerC {
   @Autowired
    private IComprasService service;
    
    @GetMapping("/listarC")
    public String listarC(Model model){
        List<Compras>Compras=service.listarC();
        model.addAttribute("Compras", Compras);
        return "indexC";

    }
          @GetMapping("/newC")
    public String agregarC(Model model){
        
        model.addAttribute("Compras", new Compras());
        return "formC";

    }
    @PostMapping("/saveC")
    public String saveC(@Validated Compras c, Model model){
        
        service.saveC(c);
        return "redirect:/listarC";

    }
       @GetMapping("/editarC/{idcompra}")
    public String editarC(@PathVariable int idcompra, Model model){
         Optional<Compras>Compras=service.listarCId(idcompra);
        model.addAttribute("Compras",  Compras);
        return "formC";
    }
    @GetMapping("/eliminarC/{idcompra}")
    public String deleteC( Model model,@PathVariable int idcompra){
        service.deleteC(idcompra);
        return "redirect:/listarC";

    }
    @GetMapping("/compra/exportC/excelC")
    public void exportToExcel(HttpServletResponse response) throws IOException {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=compras"+ currentDateTime +".xlsx";
            response.setHeader(headerKey, headerValue);
            List<Compras> CompraList = service.listarC();
            CompraExcel excelExporter = new CompraExcel (CompraList);
            excelExporter.export(response);
     }
}
