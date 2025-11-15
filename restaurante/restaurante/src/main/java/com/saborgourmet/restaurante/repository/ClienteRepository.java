package com.saborgourmet.restaurante.repository;

import com.saborgourmet.restaurante.entity.Cliente;
import com.saborgourmet.restaurante.entity.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByDniContainingIgnoreCaseOrNombresContainingIgnoreCase(String dni, String nombres);

    List<Cliente> findByEstado(Cliente.Estado estado);

    // Este es el que necesitas para liberar mesas
    Cliente findByMesa(Mesa mesa); // <- método para buscar cliente por mesa
}
