package com.tecsup.semana_05.model.daos;

import com.tecsup.semana_05.model.entities.Administrador;

public interface AdministradorDao {

    public Administrador validar(String user, String password);

}
