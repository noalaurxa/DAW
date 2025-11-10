package com.tecsup.semana_05.model.daos.Impl;

import com.tecsup.semana_05.model.daos.AdministradorDao;
import com.tecsup.semana_05.util.Tipo;

public class DaoFactory {

    public static AdministradorDao getAdministradorDao(Tipo tipo) {
        switch (tipo) {
            case CST:
                return new AdministradorDaoCallableStatement();
                default:
                    return null;
        }
    }
}
