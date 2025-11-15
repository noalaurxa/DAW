package com.saborgourmet.restaurante.repository;

import com.saborgourmet.restaurante.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> { }
