package pe.edu.tecsup.lab01.prj_crud_spring_boot_mongodb.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.tecsup.lab01.prj_crud_spring_boot_mongodb.modelo.daos.CursoRepository;
import pe.edu.tecsup.lab01.prj_crud_spring_boot_mongodb.modelo.documents.Curso;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository dao;

    @Override
    public void grabar(Curso curso) {
        dao.save(curso);
    }

    @Override
    public void eliminar(String id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Curso buscar(String id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> listar() {
        return dao.findAll();
    }
}
