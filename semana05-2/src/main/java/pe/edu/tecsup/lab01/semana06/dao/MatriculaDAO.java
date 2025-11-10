package pe.edu.tecsup.lab01.semana06.dao;

import pe.edu.tecsup.lab01.semana06.model.entities.Matricula;
import java.util.List;

public interface MatriculaDAO {

    // Inserta una nueva matrícula
    void insert(Matricula matricula);

    // Actualiza una matrícula existente
    void update(Matricula matricula);

    // Elimina una matrícula por su ID
    void delete(int id);

    // Busca una matrícula por su ID
    Matricula findById(int id);

    // Lista todas las matrículas
    List<Matricula> findAll();
}
