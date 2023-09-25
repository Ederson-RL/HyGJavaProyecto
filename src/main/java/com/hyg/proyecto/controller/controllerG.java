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

import com.hyg.proyecto.interfazService.IgastoService;
import com.hyg.proyecto.model.Gastos;

import com.hyg.proyecto.service.reporteGastos.GastosExcel;




@Controller
@RequestMapping
public class controllerG {
        @Autowired
    private IgastoService service;
    
    @GetMapping("/listarG")
    public String listarG(Model model){
        List<Gastos>gastos=service.listarG();
        model.addAttribute("Gastos", gastos);
        return "indexG";

    }
        @GetMapping("/newG")
    public String agregarG(Model model){
        
        model.addAttribute("gastos", new Gastos());
        return "formG";

    }
    @PostMapping("/saveG")
    public String saveG(@Validated Gastos g, Model model){
        
        service.saveG(g);
        return "redirect:/listarG";

    }
        @GetMapping("/editarG/{id}")
    public String editarG(@PathVariable int id, Model model){
         Optional<Gastos>Gastos=service.listarGId(id);
        model.addAttribute("gastos",  Gastos);
        return "formG";
    }
    @GetMapping("/eliminarG/{id}")
    public String deleteG( Model model,@PathVariable int id){
        service.deleteG(id);
        return "redirect:/listarG";

    }
        @GetMapping("/gastos/exportV/excelV")
    public void exportToExcel(HttpServletResponse response) throws IOException {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=Gastos"+ currentDateTime +".xlsx";
            response.setHeader(headerKey, headerValue);
            List<Gastos> gastosList = service.listarG();
            GastosExcel excelExporter = new GastosExcel(gastosList);
            excelExporter.export(response);
     }

}
