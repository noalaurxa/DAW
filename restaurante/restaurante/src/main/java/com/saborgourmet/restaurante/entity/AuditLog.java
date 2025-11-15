package com.saborgourmet.restaurante.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String entidad;
    private String entidadId;
    private String accion; // CREATE, UPDATE, DELETE
    private String usuario;
    private LocalDateTime fecha;
    @Lob
    private String detalles;
}
