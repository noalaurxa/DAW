package com.saborgourmet.restaurante.aop;

import com.saborgourmet.restaurante.entity.AuditLog;
import com.saborgourmet.restaurante.repository.AuditLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditAspect {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @AfterReturning("execution(* com.saborgourmet.restaurante.controller.*.guardar(..)) || " +
            "execution(* com.saborgourmet.restaurante.controller.*.eliminar(..)) || " +
            "execution(* com.saborgourmet.restaurante.controller.*.actualizar(..))")
    public void logAfterOperation(JoinPoint joinPoint) {
        // Obtener el usuario autenticado
        String username = "sistema";
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated()) {
                username = auth.getName();
            }
        } catch (Exception ignored) {}

        // Crear el log
        AuditLog log = new AuditLog();
        log.setAccion(joinPoint.getSignature().getName());
        log.setFecha(LocalDateTime.now());
        log.setUsuario(username);
        log.setDetalles("Operación realizada en: " + joinPoint.getSignature().getDeclaringTypeName());
        log.setEntidad(joinPoint.getSignature().getDeclaringTypeName());
        log.setEntidadId(""); // Aquí puedes asignar un ID si lo tienes

        auditLogRepository.save(log);
    }
}
