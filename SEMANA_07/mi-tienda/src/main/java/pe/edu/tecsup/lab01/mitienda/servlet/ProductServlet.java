package pe.edu.tecsup.lab01.mitienda.servlet;

import jakarta.servlet.http.HttpServlet;
import pe.edu.tecsup.lab01.mitienda.dao.CategoryDAO;
import pe.edu.tecsup.lab01.mitienda.dao.CategoryDAOImpl;
import pe.edu.tecsup.lab01.mitienda.dao.ProductDAO;
import pe.edu.tecsup.lab01.mitienda.dao.ProductDAOImpl;
import pe.edu.tecsup.lab01.mitienda.model.Category;
import pe.edu.tecsup.lab01.mitienda.model.Product;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    @Override
    public void init() {
        productDAO = new ProductDAOImpl(getServletContext());
        categoryDAO = new CategoryDAOImpl(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if (action == null || action.equals("list")) {
                List<Product> list = productDAO.findAll();
                req.setAttribute("products", list);
                req.getRequestDispatcher("/jsp/products/list.jsp").forward(req, resp);
            } else if (action.equals("new")) {
                List<Category> categories = categoryDAO.findAll();
                req.setAttribute("categories", categories);
                req.getRequestDispatcher("/jsp/products/form.jsp").forward(req, resp);
            } else if (action.equals("edit")) {
                int id = Integer.parseInt(req.getParameter("id"));
                Product p = productDAO.findById(id);
                req.setAttribute("product", p);
                req.setAttribute("categories", categoryDAO.findAll());
                req.getRequestDispatcher("/jsp/products/form.jsp").forward(req, resp);
            } else if (action.equals("delete")) {
                int id = Integer.parseInt(req.getParameter("id"));
                productDAO.delete(id);
                resp.sendRedirect(req.getContextPath() + "/products");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idStr = req.getParameter("id");
            String name = req.getParameter("name");
            String priceStr = req.getParameter("price");
            String categoryIdStr = req.getParameter("categoryId");

            Product p = new Product();
            p.setName(name);
            p.setPrice(new BigDecimal(priceStr != null && !priceStr.isEmpty() ? priceStr : "0.00"));
            if (categoryIdStr != null && !categoryIdStr.isEmpty()) p.setCategoryId(Integer.parseInt(categoryIdStr));
            if (idStr != null && !idStr.isEmpty()) p.setId(Integer.parseInt(idStr));

            productDAO.save(p);
            resp.sendRedirect(req.getContextPath() + "/products");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
