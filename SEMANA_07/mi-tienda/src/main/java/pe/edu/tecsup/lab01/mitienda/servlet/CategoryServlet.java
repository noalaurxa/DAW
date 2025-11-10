package pe.edu.tecsup.lab01.mitienda.servlet;
import pe.edu.tecsup.lab01.mitienda.dao.CategoryDAO;
import pe.edu.tecsup.lab01.mitienda.dao.CategoryDAOImpl;
import pe.edu.tecsup.lab01.mitienda.model.Category;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {
    private CategoryDAO dao;

    @Override
    public void init() {
        dao = new CategoryDAOImpl(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if (action == null || action.equals("list")) {
                List<Category> list = dao.findAll();
                req.setAttribute("categories", list);
                req.getRequestDispatcher("/jsp/categories/list.jsp").forward(req, resp);
            } else if (action.equals("new")) {
                req.getRequestDispatcher("/jsp/categories/form.jsp").forward(req, resp);
            } else if (action.equals("edit")) {
                int id = Integer.parseInt(req.getParameter("id"));
                Category c = dao.findById(id);
                req.setAttribute("category", c);
                req.getRequestDispatcher("/jsp/categories/form.jsp").forward(req, resp);
            } else if (action.equals("delete")) {
                int id = Integer.parseInt(req.getParameter("id"));
                dao.delete(id);
                resp.sendRedirect(req.getContextPath() + "/categories");
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
            String description = req.getParameter("description");

            Category c = new Category();
            c.setName(name);
            c.setDescription(description);
            if (idStr != null && !idStr.isEmpty()) c.setId(Integer.parseInt(idStr));

            dao.save(c);
            resp.sendRedirect(req.getContextPath() + "/categories");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

