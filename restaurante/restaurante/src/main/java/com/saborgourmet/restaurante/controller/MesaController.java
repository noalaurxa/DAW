package com.saborgourmet.restaurante.controller;

import com.saborgourmet.restaurante.entity.Cliente;
import com.saborgourmet.restaurante.entity.Mesa;
import com.saborgourmet.restaurante.service.ClienteService;
import com.saborgourmet.restaurante.service.MesaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mesas")
public class MesaController {

    private final MesaService mesaService;
    private final ClienteService clienteService;

    public MesaController(MesaService mesaService, ClienteService clienteService) {
        this.mesaService = mesaService;
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("mesas", mesaService.listarTodas());
        return "mesas/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(@RequestParam(value = "id", required = false) Long id, Model model) {
        Mesa mesa;
        if (id != null) {
            // Cargar mesa existente para editar
            mesa = mesaService.buscarPorId(id);
            if (mesa == null) {
                // Si no existe, redirigir a la lista o mostrar error
                return "redirect:/mesas";
            }
        } else {
            // Crear una nueva mesa
            mesa = new Mesa();
        }
        model.addAttribute("mesa", mesa);
        return "mesas/form";
    }


    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Mesa mesa = mesaService.buscarPorId(id);
        if (mesa == null) {
            // Manejo básico si no se encuentra la mesa
            return "redirect:/mesas";
        }
        model.addAttribute("mesa", mesa);
        return "mesas/form"; // reutiliza el mismo formulario de creación
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Mesa mesa, BindingResult br, Model model) {

        if (br.hasErrors()) {
            model.addAttribute("mesa", mesa);
            return "mesas/form";
        }

        mesaService.guardar(mesa);
        return "redirect:/mesas";
    }



    @GetMapping("/liberar/{id}")
    public String liberar(@PathVariable Long id) {
        mesaService.liberarMesa(id); // liberación y desvinculación del cliente
        return "redirect:/mesas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        mesaService.eliminar(id);
        return "redirect:/mesas";
    }
}
