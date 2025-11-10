package pe.edu.tecsup.lab01.sistemaacademico.dao;

import pe.edu.tecsup.lab01.sistemaacademico.model.Matricula;
import java.util.List;

public interface MatriculaDAO {
    void agregarMatricula(Matricula m);
    void actualizarMatricula(Matricula m);
    void eliminarMatricula(int idMatricula);
    Matricula obtenerMatricula(int idMatricula);
    List<Matricula> listarMatriculas();
}
