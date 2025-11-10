package pe.edu.tecsup.lab01.mitienda.dao;
import pe.edu.tecsup.lab01.mitienda.model.Category;
import pe.edu.tecsup.lab01.mitienda.util.DBUtil;
import jakarta.servlet.ServletContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    private ServletContext ctx;
    public CategoryDAOImpl(ServletContext ctx) { this.ctx = ctx; }

    @Override
    public List<Category> findAll() throws Exception {
        List<Category> list = new ArrayList<>();
        try (Connection c = DBUtil.getConnection(ctx);
             PreparedStatement ps = c.prepareStatement("SELECT id, name, description FROM category");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
            }
        }
        return list;
    }

    @Override
    public Category findById(int id) throws Exception {
        try (Connection c = DBUtil.getConnection(ctx);
             PreparedStatement ps = c.prepareStatement("SELECT id, name, description FROM category WHERE id=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return new Category(rs.getInt("id"), rs.getString("name"), rs.getString("description"));
            }
        }
        return null;
    }

    @Override
    public void save(Category category) throws Exception {
        if (category.getId() == null) {
            try (Connection c = DBUtil.getConnection(ctx);
                 PreparedStatement ps = c.prepareStatement("INSERT INTO category (name, description) VALUES (?, ?)")) {
                ps.setString(1, category.getName());
                ps.setString(2, category.getDescription());
                ps.executeUpdate();
            }
        } else {
            try (Connection c = DBUtil.getConnection(ctx);
                 PreparedStatement ps = c.prepareStatement("UPDATE category SET name=?, description=? WHERE id=?")) {
                ps.setString(1, category.getName());
                ps.setString(2, category.getDescription());
                ps.setInt(3, category.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try (Connection c = DBUtil.getConnection(ctx);
             PreparedStatement ps = c.prepareStatement("DELETE FROM category WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}