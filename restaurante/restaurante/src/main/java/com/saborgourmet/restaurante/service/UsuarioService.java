package com.saborgourmet.restaurante.service;

import com.saborgourmet.restaurante.entity.Role;
import com.saborgourmet.restaurante.entity.Usuario;
import com.saborgourmet.restaurante.repository.RoleRepository;
import com.saborgourmet.restaurante.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepo;
    private final RoleRepository roleRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UsuarioService(UsuarioRepository usuarioRepo, RoleRepository roleRepo) {
        this.usuarioRepo = usuarioRepo;
        this.roleRepo = roleRepo;
    }

    @Transactional
    public Usuario crearAdminInicial(String username, String rawPassword) {
        Usuario u = new Usuario();
        u.setUsername(username);
        u.setPassword(encoder.encode(rawPassword));

        Set<Role> roles = new HashSet<>();
        Role r = roleRepo.findByNombre("ADMIN");
        if (r != null) {
            roles.add(r);
        }
        u.setRoles(roles);

        return usuarioRepo.save(u);
    }
}
