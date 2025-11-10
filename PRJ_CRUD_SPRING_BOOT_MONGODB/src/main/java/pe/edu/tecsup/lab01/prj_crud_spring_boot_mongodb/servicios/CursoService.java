package pe.edu.tecsup.lab01.prj_crud_spring_boot_mongodb.servicios;

import pe.edu.tecsup.lab01.prj_crud_spring_boot_mongodb.modelo.documents.Curso;
import java.util.List;

public interface CursoService {

    List<Curso> listar();

    void grabar(Curso curso);

    Curso buscar(String id);

    void eliminar(String id);
}
