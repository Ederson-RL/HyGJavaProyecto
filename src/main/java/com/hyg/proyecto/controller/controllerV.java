package com.hyg.proyecto.controller;

import java.util.List;
import java.util.Optional;

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





@Controller
@RequestMapping
public class controllerV {
    @Autowired
    private IventaService service;
    
    @GetMapping("/listarV")
    public String listarV(Model model){
        List<Venta>ventas=service.listarV();
        model.addAttribute("Ventas", ventas);
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

}
