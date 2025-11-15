package com.saborgourmet.restaurante.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @NotBlank(message = "El DNI es obligatorio")
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombres;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellidos;

    private String telefono;
    private String correo;

    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.ACTIVO;

    // Relación con mesa (un cliente puede tener una mesa)
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idMesa")
    private Mesa mesa;


    // Getters y Setters
    public Long getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Mesa getMesa() {
        return mesa;
    }
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    // Otros getters y setters de atributos...

    public enum Estado {
        ACTIVO, INACTIVO
    }
}
