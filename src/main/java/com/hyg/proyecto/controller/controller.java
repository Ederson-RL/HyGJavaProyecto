package com.hyg.proyecto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;


import com.hyg.proyecto.interfazService.IproductoService;
import com.hyg.proyecto.model.Producto;


import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;


@Controller
@RequestMapping
public class controller {

    @Autowired
    private IproductoService service;
    
    @GetMapping("/listar")
    public String listar(Model model){
        List<Producto>productos=service.listar();
        model.addAttribute("productos", productos);
        return "indexP";

    }
        @GetMapping("/new")
    public String agregar(Model model){
        
        model.addAttribute("producto", new Producto());
        return "form";

    }
    @PostMapping("/save")
    public String save(@Validated Producto p, Model model){
        
        service.save(p);
        return "redirect:/listar";

    }
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model){
         Optional<Producto>Producto=service.listarId(id);
        model.addAttribute("producto",  Producto);
        return "form";
}
   @GetMapping("/eliminar/{id}")
    public String delete( Model model,@PathVariable int id){
        service.delete(id);
        return "redirect:/listar";

    }
    @GetMapping("/login")
    public String visualzarlogin(Model modelo){
   
               return "login";
       }
    
    
   @GetMapping("/registrar")
    public String visualzarregistra(Model modelo){
   
               return "registrar";
       }

   @GetMapping("/index")
    public String visualzarindex(Model modelo){
   
               return "inicio";
       }
         
    @GetMapping("/catalogo")
    public String visualzarCatalogo(Model modelo){
   
               return "catalogo";
       }
    

    @GetMapping("/Nosotros")
    public String visualzarnosotros(Model modelo){
   
               return "Nosotros";
       }

    @GetMapping("/contacto")
    public String visualzarcoontacto(Model modelo){
   
               return "Contacto";
       }

           @GetMapping("/dashboardAdmin")
    public String visualzardashboardAdmin(Model modelo){
   
               return "DashboardAdmin";
       }
           @GetMapping("/dashboardCliente")
    public String visualzardashboardCliente(Model modelo){
   
               return "DashboardCliente";
       }
    

}
