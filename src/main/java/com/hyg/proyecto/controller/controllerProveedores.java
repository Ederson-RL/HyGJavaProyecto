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
import com.hyg.proyecto.interfazService.IProveedorService;
import com.hyg.proyecto.model.Proveedor;
import com.hyg.proyecto.service.reporteProveedores.ProveedorExcel;


@Controller
@RequestMapping
public class controllerProveedores {

    @Autowired
    private IProveedorService service;
    String palabraClave;

    @RequestMapping("/listarProveedores")
    public String Proveedor(Model model,@Param("palabraClave")String palabraClave) {
        this.palabraClave = palabraClave;
        List<Proveedor> proveedores = service.ListAllProveedor(palabraClave);
       
        model.addAttribute("proveedores", proveedores); 
        model.addAttribute("palabraClave", palabraClave);
        return "crudProveedores/indexProveedor";
    }

    @RequestMapping("/newProveedor")
    public String mostrarformularioProveedores(Model model) {
        Proveedor proveedores= new Proveedor();
        model.addAttribute("proveedores", proveedores);
        return "crudProveedores/formProveedores";
    }
     @PostMapping("/guardarProveedor")
    public String guardarProveedores(@ModelAttribute("proveedores") Proveedor proveedores) {
        service.saveProveedor(proveedores);
        return "redirect:/listarProveedores";
    }

 
    @RequestMapping("/editarProveedor/{id}")
    public ModelAndView mostrarformularioProveedoresEditar(@PathVariable(name="id") int id) {
        ModelAndView modelo= new ModelAndView("crudProveedores/editarProveedor");
        Proveedor proveedores= service.getProveedor(id);
        modelo.addObject("proveedores", proveedores);
        return modelo;

    }

    @RequestMapping("/eliminarProveedor/{id}")
    public String eliminarproveedores(@PathVariable(name="id") int id) {
    service.deleteProveedor(id);
    return"redirect:/listarProveedores";

    }

    @GetMapping("/Proveedores/export/excel")
        public void exportToExcel(HttpServletResponse response) throws IOException {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=Proveedores"+ currentDateTime +".xlsx";
            response.setHeader(headerKey, headerValue);
            List<Proveedor> ProveedorList = service.ListAllProveedor(palabraClave);
            ProveedorExcel excelExporter = new ProveedorExcel(ProveedorList);
            excelExporter.export(response);
     }
}
