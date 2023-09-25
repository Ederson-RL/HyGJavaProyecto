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

import com.hyg.proyecto.interfazService.IComprasService;
import com.hyg.proyecto.model.Compras;

@Controller
@RequestMapping
public class controllerC {
    @Autowired
    private IComprasService service;

    @GetMapping("/listarC")
    public String listarC(Model model) {
        List<Compras> Compras = service.listarC();
        model.addAttribute("Compras", Compras);
        return "indexC";

    }

    @GetMapping("/newC")
    public String agregarC(Model model) {

        model.addAttribute("Compras", new Compras());
        return "formC";

    }

    @PostMapping("/saveC")
    public String saveC(@Validated Compras c, Model model) {

        service.saveC(c);
        return "redirect:/listarC";

    }

    @GetMapping("/editarC/{idcompra}")
    public String editarC(@PathVariable int idcompra, Model model) {
        Optional<Compras> Compras = service.listarCId(idcompra);
        model.addAttribute("Compras", Compras);
        return "formC";
    }

    @GetMapping("/eliminarC/{idcompra}")
    public String deleteC(Model model, @PathVariable int idcompra) {
        service.deleteC(idcompra);
        return "redirect:/listarC";

    }
}
