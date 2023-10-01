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
import com.hyg.proyecto.interfazService.IComprasService;
import com.hyg.proyecto.model.Compras;
import com.hyg.proyecto.service.reporteCompra.CompraExcel;


@Controller
@RequestMapping
public class controllerC {
    @Autowired
    private IComprasService service;
    String palabraClave;
    
    @RequestMapping("/listarC")
    public String Compras(Model model,@Param("palabraClave")String palabraClave) {
        this.palabraClave = palabraClave;
        List<Compras> compras = service.ListAllCompras(palabraClave);
       
        model.addAttribute("compras", compras); 
        model.addAttribute("palabraClave", palabraClave);
        return "crudCompras/indexC";
    }
    @RequestMapping("/newC")
    public String mostrarformularioCompras(Model model){
        Compras compras= new Compras();
        model.addAttribute("compras", compras);
        return "crudCompras/formC";

    }
    @PostMapping("/guardarCompras")
    public String guardarCompras(@ModelAttribute("compras") Compras compras){
        
        service.saveCompras(compras);
        return "redirect:/listarC";

    }
    @RequestMapping("/editarC/{idcompra}")
    public ModelAndView mostrarformularioComprasEditar(@PathVariable(name="idcompra") int idcompra){
        ModelAndView modelo= new ModelAndView("crudCompras/editarCompras");
        Compras compras= service.getCompras(idcompra);
        modelo.addObject("compras", compras);
        return modelo;
    }
    @GetMapping("/eliminarC/{idcompra}")
    public String eliminarcompras(@PathVariable(name="idcompra") int idcompra) {
        service.deleteCompras(idcompra);
        return"redirect:/listarC";

    }
    @GetMapping("/compra/exportC/excelC")
    public void exportToExcel(HttpServletResponse response) throws IOException {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=compras"+ currentDateTime +".xlsx";
            response.setHeader(headerKey, headerValue);
            List<Compras> CompraList = service.ListAllCompras(palabraClave);
            CompraExcel excelExporter = new CompraExcel (CompraList);
            excelExporter.export(response);
     }
}