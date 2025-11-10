package com.tecsup.semana_05.services.Impl;

import com.tecsup.semana_05.model.daos.AdministradorDao;
import com.tecsup.semana_05.model.daos.Impl.DaoFactory;
import com.tecsup.semana_05.model.entities.Administrador;
import com.tecsup.semana_05.services.AdministradorService;
import com.tecsup.semana_05.util.Util;

public class AdministradorServiceImpl implements AdministradorService {

    private AdministradorDao dao;

    public AdministradorServiceImpl() {
        dao = DaoFactory.getAdministradorDao(Util.opc);
    }

    @Override
    public Administrador validar(String u, String p) {
        return dao.validar(u,p);
    }
}
