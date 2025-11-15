package com.saborgourmet.restaurante.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "mesa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMesa;

    private Integer numero;

    private Integer capacidad;

    @Enumerated(EnumType.STRING)
    private EstadoMesa estado = EstadoMesa.DISPONIBLE;

    // Relación con clientes SIN CASCADE (para evitar validaciones obligatorias)
    @OneToMany(mappedBy = "mesa")
    private List<Cliente> clientes;

    public enum EstadoMesa {
        DISPONIBLE, OCUPADA, RESERVADA, MANTENIMIENTO
    }
}
