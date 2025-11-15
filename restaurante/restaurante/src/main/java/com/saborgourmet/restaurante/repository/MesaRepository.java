package com.saborgourmet.restaurante.repository;

import com.saborgourmet.restaurante.entity.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MesaRepository extends JpaRepository<Mesa, Long> {

    // Buscar mesa por número
    Optional<Mesa> findByNumero(Integer numero);

    // Buscar mesas por estado (DISPONIBLE, OCUPADA, RESERVADA)
    List<Mesa> findByEstado(Mesa.EstadoMesa estado);

}
