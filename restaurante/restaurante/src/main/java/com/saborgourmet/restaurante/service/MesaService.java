package com.saborgourmet.restaurante.service;

import com.saborgourmet.restaurante.entity.Cliente;
import com.saborgourmet.restaurante.entity.Mesa;
import com.saborgourmet.restaurante.repository.ClienteRepository;
import com.saborgourmet.restaurante.repository.MesaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MesaService {

    private final MesaRepository mesaRepo;
    private final ClienteRepository clienteRepo;

    public MesaService(MesaRepository mesaRepo, ClienteRepository clienteRepo) {
        this.mesaRepo = mesaRepo;
        this.clienteRepo = clienteRepo;
    }

    // Listar todas las mesas
    public List<Mesa> listarTodas() {
        return mesaRepo.findAll();
    }

    // Buscar mesa por ID
    public Mesa buscarPorId(Long id) {
        return mesaRepo.findById(id).orElse(null);
    }

    // Guardar o crear mesa
    @Transactional
    public Mesa guardar(Mesa mesa) {
        return mesaRepo.save(mesa);
    }

    // Eliminar mesa
    @Transactional
    public void eliminar(Long id) {
        mesaRepo.deleteById(id);
    }

    // Liberar mesa y desvincular cliente automáticamente
    @Transactional
    public Mesa liberarMesa(Long id) {
        Mesa mesa = buscarPorId(id);
        if (mesa == null) throw new IllegalArgumentException("Mesa no encontrada");

        Cliente cliente = clienteRepo.findByMesa(mesa);
        if (cliente != null) {
            // NO BORRAR LA MESA DEL CLIENTE
            cliente.setEstado(Cliente.Estado.INACTIVO);
            clienteRepo.save(cliente);
        }

        mesa.setEstado(Mesa.EstadoMesa.DISPONIBLE);
        return mesaRepo.save(mesa);
    }


    // Listar mesas disponibles
    public List<Mesa> listarMesasDisponibles() {
        return mesaRepo.findByEstado(Mesa.EstadoMesa.DISPONIBLE);
    }

    // Listar mesas por estado específico
    public List<Mesa> listarMesasPorEstado(Mesa.EstadoMesa estado) {
        return mesaRepo.findByEstado(estado);
    }
}
