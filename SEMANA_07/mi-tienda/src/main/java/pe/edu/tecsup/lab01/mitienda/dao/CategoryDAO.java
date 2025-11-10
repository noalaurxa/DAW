package pe.edu.tecsup.lab01.mitienda.dao;

import pe.edu.tecsup.lab01.mitienda.model.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> findAll() throws Exception;
    Category findById(int id) throws Exception;
    void save(Category category) throws Exception; // insert or update
    void delete(int id) throws Exception;
}