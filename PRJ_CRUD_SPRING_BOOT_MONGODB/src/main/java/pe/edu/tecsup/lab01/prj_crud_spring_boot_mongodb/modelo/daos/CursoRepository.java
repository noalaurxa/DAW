package pe.edu.tecsup.lab01.prj_crud_spring_boot_mongodb.modelo.daos;

import org.springframework.data.mongodb.repository.MongoRepository;
import pe.edu.tecsup.lab01.prj_crud_spring_boot_mongodb.modelo.documents.Curso;

public interface CursoRepository extends MongoRepository<Curso, String> {
}
