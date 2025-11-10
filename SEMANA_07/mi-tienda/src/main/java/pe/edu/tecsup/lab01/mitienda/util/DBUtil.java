package pe.edu.tecsup.lab01.mitienda.util;

import java.sql.Connection;
import java.sql.DriverManager;
import jakarta.servlet.ServletContext;

public class DBUtil {
    public static Connection getConnection(ServletContext ctx) throws Exception {
        String url = ctx.getInitParameter("jdbcUrl");
        String user = ctx.getInitParameter("dbUser");
        String pass = ctx.getInitParameter("dbPassword");
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }
}

