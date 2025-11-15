package com.saborgourmet.restaurante.service;

import com.saborgourmet.restaurante.entity.Cliente;
import com.saborgourmet.restaurante.entity.Mesa;
import com.saborgourmet.restaurante.repository.ClienteRepository;
import com.saborgourmet.restaurante.repository.MesaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ClienteService {

    private final ClienteRepository clienteRepo;
    private final MesaRepository mesaRepo;

    public ClienteService(ClienteRepository clienteRepo, MesaRepository mesaRepo) {
        this.clienteRepo = clienteRepo;
        this.mesaRepo = mesaRepo;
    }

    public List<Cliente> listarTodos() {
        return clienteRepo.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepo.findById(id).orElse(null);
    }

    public Cliente buscarPorMesa(Mesa mesa) {
        return clienteRepo.findByMesa(mesa);
    }

    @Transactional
    public Cliente guardar(Cliente cliente) {

        Mesa mesa = cliente.getMesa(); // mesa asignada al cliente

        if (mesa != null) {

            // Si cliente activo → mesa ocupada
            if (cliente.getEstado() == Cliente.Estado.ACTIVO) {
                mesa.setEstado(Mesa.EstadoMesa.OCUPADA);
                mesaRepo.save(mesa);
            }

            // Si cliente inactivo → liberar mesa PERO sin quitar la mesa del cliente
            if (cliente.getEstado() == Cliente.Estado.INACTIVO) {
                mesa.setEstado(Mesa.EstadoMesa.DISPONIBLE);
                mesaRepo.save(mesa);

                // ❌ NO borrar la mesa del cliente
                // cliente.setMesa(null);  <-- JAMÁS hacer esto
            }
        }

        return clienteRepo.save(cliente);
    }






    @Transactional
    public void eliminar(Long id) {
        Cliente cliente = buscarPorId(id);
        if (cliente != null) {
            liberarMesa(cliente);
            clienteRepo.delete(cliente);
        }
    }

    public List<Cliente> buscar(String texto) {
        return clienteRepo.findByDniContainingIgnoreCaseOrNombresContainingIgnoreCase(texto, texto);
    }

    public List<Cliente> listarClientesActivos() {
        return clienteRepo.findByEstado(Cliente.Estado.ACTIVO);
    }


    // ----------- 🔥 LO CORRECTO PARA LIBERAR MESA ----------------
    @Transactional
    public void liberarMesa(Cliente cliente) {
        if (cliente.getMesa() != null) {

            Mesa mesa = cliente.getMesa();
            mesa.setEstado(Mesa.EstadoMesa.DISPONIBLE);
            mesaRepo.save(mesa);

            cliente.setEstado(Cliente.Estado.INACTIVO);
            clienteRepo.save(cliente);
        }
    }

    @Transactional
    public Cliente liberarMesaYActualizar(Long idCliente) {

        Cliente cliente = clienteRepo.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        if (cliente.getMesa() != null) {
            Mesa mesa = cliente.getMesa();
            mesa.setEstado(Mesa.EstadoMesa.DISPONIBLE);
            mesaRepo.save(mesa);

            // ❌ NO borrar la mesa del cliente
            // cliente.setMesa(null);  <-- quitar esta línea!
        }

        cliente.setEstado(Cliente.Estado.INACTIVO);
        return clienteRepo.save(cliente);
    }


}
