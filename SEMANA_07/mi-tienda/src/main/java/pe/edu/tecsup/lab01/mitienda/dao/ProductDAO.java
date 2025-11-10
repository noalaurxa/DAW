package pe.edu.tecsup.lab01.mitienda.dao;


import pe.edu.tecsup.lab01.mitienda.model.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> findAll() throws Exception;
    Product findById(int id) throws Exception;
    void save(Product product) throws Exception;
    void delete(int id) throws Exception;
    List<Product> findByCategory(int categoryId) throws Exception;
}
