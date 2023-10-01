package com.hyg.proyecto.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.hyg.proyecto.interfazService.IgastoService;
import com.hyg.proyecto.model.Gastos;
import com.hyg.proyecto.service.reporteGastos.GastosExcel;


@Controller
@RequestMapping
public class controllerG {
        @Autowired
    private IgastoService service;
    String palabraClave;
    
    // Listar todas las pruebas
    @RequestMapping("/listarG")
    public String Gastos(Model model,@Param("palabraClave")String palabraClave) {
        this.palabraClave = palabraClave;
        List<Gastos> gastos = service.ListAll(palabraClave);
       
        model.addAttribute("gastos", gastos); 
        model.addAttribute("palabraClave", palabraClave);
        return "crudGastos/indexG";
    }

    @RequestMapping("/newG")
    public String mostrarformularioGastos(Model model) {
        Gastos gastos= new Gastos();
        model.addAttribute("gastos", gastos);
        return "crudGastos/formG";
    }
     @PostMapping("/guardarGastos")
    public String guardarGastos(@ModelAttribute("gastos") Gastos gastos) {
        service.saveG(gastos);
        return "redirect:/listarG";
    }

 
    @RequestMapping("/editarG/{id}")
    public ModelAndView mostrarformularioGastosEditar(@PathVariable(name="id") int id) {
        ModelAndView modelo= new ModelAndView("crudGastos/editar_gastos");
        Gastos gastos= service.getGastos(id);
        modelo.addObject("gastos", gastos);
        return modelo;

    }

    @RequestMapping("/eliminarG/{id}")
    public String eliminargastos(@PathVariable(name="id") int id) {
    service.deleteG(id);
    return"redirect:/listarG";

    }

    @GetMapping("/gastos/exportV/excelV")
    public void exportToExcel(HttpServletResponse response) throws IOException {
            
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=Gastos"+ currentDateTime +".xlsx";
            response.setHeader(headerKey, headerValue);
            List<Gastos> gastosList = service.ListAll(palabraClave);
            GastosExcel excelExporter = new GastosExcel(gastosList);
            excelExporter.export(response);
     }

}
