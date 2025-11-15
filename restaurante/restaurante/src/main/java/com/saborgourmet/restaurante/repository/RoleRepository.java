package com.saborgourmet.restaurante.repository;

import com.saborgourmet.restaurante.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNombre(String nombre);
}
