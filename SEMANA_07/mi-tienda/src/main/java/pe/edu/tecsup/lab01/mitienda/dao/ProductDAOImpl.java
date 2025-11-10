package pe.edu.tecsup.lab01.mitienda.dao;


import pe.edu.tecsup.lab01.mitienda.model.Product;
import pe.edu.tecsup.lab01.mitienda.util.DBUtil;
import jakarta.servlet.ServletContext;
import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private ServletContext ctx;
    public ProductDAOImpl(ServletContext ctx) { this.ctx = ctx; }

    @Override
    public List<Product> findAll() throws Exception {
        List<Product> list = new ArrayList<>();
        try (Connection c = DBUtil.getConnection(ctx);
             PreparedStatement ps = c.prepareStatement("SELECT id, name, price, category_id FROM product");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"), rs.getString("name"),
                        rs.getBigDecimal("price"), (Integer)rs.getObject("category_id")));
            }
        }
        return list;
    }

    @Override
    public Product findById(int id) throws Exception {
        try (Connection c = DBUtil.getConnection(ctx);
             PreparedStatement ps = c.prepareStatement("SELECT id, name, price, category_id FROM product WHERE id=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return new Product(rs.getInt("id"), rs.getString("name"),
                        rs.getBigDecimal("price"), (Integer)rs.getObject("category_id"));
            }
        }
        return null;
    }

    @Override
    public void save(Product product) throws Exception {
        if (product.getId() == null) {
            try (Connection c = DBUtil.getConnection(ctx);
                 PreparedStatement ps = c.prepareStatement("INSERT INTO product (name, price, category_id) VALUES (?, ?, ?)")) {
                ps.setString(1, product.getName());
                ps.setBigDecimal(2, product.getPrice());
                if (product.getCategoryId() == null) ps.setNull(3, Types.INTEGER);
                else ps.setInt(3, product.getCategoryId());
                ps.executeUpdate();
            }
        } else {
            try (Connection c = DBUtil.getConnection(ctx);
                 PreparedStatement ps = c.prepareStatement("UPDATE product SET name=?, price=?, category_id=? WHERE id=?")) {
                ps.setString(1, product.getName());
                ps.setBigDecimal(2, product.getPrice());
                if (product.getCategoryId() == null) ps.setNull(3, Types.INTEGER);
                else ps.setInt(3, product.getCategoryId());
                ps.setInt(4, product.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try (Connection c = DBUtil.getConnection(ctx);
             PreparedStatement ps = c.prepareStatement("DELETE FROM product WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Product> findByCategory(int categoryId) throws Exception {
        List<Product> list = new ArrayList<>();
        try (Connection c = DBUtil.getConnection(ctx);
             PreparedStatement ps = c.prepareStatement("SELECT id, name, price, category_id FROM product WHERE category_id=?")) {
            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Product(rs.getInt("id"), rs.getString("name"),
                            rs.getBigDecimal("price"), (Integer)rs.getObject("category_id")));
                }
            }
        }
        return list;
    }
}
