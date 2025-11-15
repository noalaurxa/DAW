package com.saborgourmet.restaurante.controller;

import com.saborgourmet.restaurante.entity.Cliente;
import com.saborgourmet.restaurante.entity.Mesa;
import com.saborgourmet.restaurante.service.ClienteService;
import com.saborgourmet.restaurante.service.MesaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final MesaService mesaService;

    public ClienteController(ClienteService clienteService, MesaService mesaService) {
        this.clienteService = clienteService;
        this.mesaService = mesaService;
    }

    @GetMapping
    public String listar(@RequestParam(value = "q", required = false) String q, Model model) {
        List<Cliente> clientes;
        if (q != null && !q.trim().isEmpty()) {
            clientes = clienteService.buscar(q.trim());
            model.addAttribute("q", q);
        } else {
            clientes = clienteService.listarTodos();
        }
        model.addAttribute("clientes", clientes);
        return "clientes/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("mesasDisponibles", mesaService.listarMesasPorEstado(Mesa.EstadoMesa.DISPONIBLE));
        return "clientes/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.buscarPorId(id);
        if (cliente == null) return "redirect:/clientes";

        List<Mesa> mesasDisponibles = mesaService.listarMesasPorEstado(Mesa.EstadoMesa.DISPONIBLE);

        if (cliente.getMesa() != null && cliente.getMesa().getEstado() != Mesa.EstadoMesa.DISPONIBLE) {
            mesasDisponibles.add(0, cliente.getMesa());
        }

        model.addAttribute("cliente", cliente);
        model.addAttribute("mesasDisponibles", mesasDisponibles);
        return "clientes/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Cliente cliente, BindingResult br, Model model) {

        if (br.hasErrors()) {
            List<Mesa> mesasDisponibles = mesaService.listarMesasPorEstado(Mesa.EstadoMesa.DISPONIBLE);
            if (cliente.getMesa() != null) mesasDisponibles.add(0, cliente.getMesa());
            model.addAttribute("mesasDisponibles", mesasDisponibles);
            return "clientes/form";
        }

        // Mesa real
        if (cliente.getMesa() != null && cliente.getMesa().getIdMesa() != null) {
            cliente.setMesa(mesaService.buscarPorId(cliente.getMesa().getIdMesa()));
        }

        // 🔥 Si pasa a INACTIVO → liberar mesa
        if (cliente.getEstado() == Cliente.Estado.INACTIVO) {
            clienteService.liberarMesaYActualizar(cliente.getIdCliente());
        } else {
            clienteService.guardar(cliente);
        }

        return "redirect:/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return "redirect:/clientes";
    }
}

