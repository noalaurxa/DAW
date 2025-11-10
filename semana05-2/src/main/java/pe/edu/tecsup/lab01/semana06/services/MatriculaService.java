package pe.edu.tecsup.lab01.semana06.services;

import pe.edu.tecsup.lab01.semana06.model.entities.Matricula;
import java.util.List;

public interface MatriculaService {

    // Registrar una nueva matrícula
    void registrarMatricula(Matricula matricula);

    // Actualizar una matrícula existente
    void actualizarMatricula(Matricula matricula);

    // Eliminar una matrícula por su ID
    void eliminarMatricula(int id);

    // Obtener matrícula por ID
    Matricula obtenerMatricula(int id);

    // Listar todas las matrículas
    List<Matricula> listarMatriculas();
}
