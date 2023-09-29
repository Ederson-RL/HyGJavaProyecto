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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hyg.proyecto.interfazService.IproductoService;
import com.hyg.proyecto.model.Producto;
import com.hyg.proyecto.service.reporteInventario.ProductosExcel;

import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;

@Controller
@RequestMapping
public class controller {

    @Autowired
    private IproductoService service;

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Producto> productos = service.listar();
        model.addAttribute("productos", productos);
        return "indexP";

    }

    @GetMapping("/new")
    public String agregar(Model model) {

        model.addAttribute("producto", new Producto());
        return "form";

    }

    @PostMapping("/save")
    public String save(@Validated Producto p, Model model) {

        service.save(p);
        return "redirect:/listar";

    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Optional<Producto> Producto = service.listarId(id);
        model.addAttribute("producto", Producto);
        return "form";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(Model model, @PathVariable int id) {
        service.delete(id);
        return "redirect:/listar";

    }

    @GetMapping("/login")
    public String visualzarlogin(Model modelo) {

        return "loginh";
    }

    @PostMapping("/login")
    public String visualzarloginp(Model modelo) {

        return "loginh";
    }

    @GetMapping("/registrar")
    public String visualzarregistra(Model modelo) {

        return "registrar";
    }

    @GetMapping("/index")
    public String visualzarindex(Model modelo) {

        return "inicio";
    }

    @GetMapping("/catalogo")
    public String visualzarCatalogo(Model modelo) {

        return "catalogo";
    }

    @GetMapping("/Nosotros")
    public String visualzarnosotros(Model modelo) {

        return "Nosotros";
    }

    @GetMapping("/contacto")
    public String visualzarcoontacto(Model modelo) {

        return "Contacto";
    }

    @GetMapping("/dashboardAdmin")
    public String visualzardashboardAdmin(Model modelo) {

        return "DashboardAdmin";
    }

    @GetMapping("/dashboardCliente")
    public String visualzardashboardCliente(Model modelo) {

        return "DashboardCliente";
    }

    @GetMapping("/Error404")
    public String visualzarError404(Model modelo) {

        return "Error404";
    }

    @GetMapping("/Error500")
    public String visualzarError500(Model modelo) {

        return "Error500";
    }

    @GetMapping("/productos/exportP/excelP")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Productos" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<Producto> productosList = service.listar();
        ProductosExcel excelExporter = new ProductosExcel(productosList);
        excelExporter.export(response);
    }

}
