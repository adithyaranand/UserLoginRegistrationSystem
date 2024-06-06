package com.LoginRegister;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("JDBC Driver loaded successfully.");

            // Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UserLoginRegister1", "root", "1234567890");
            System.out.println("Database connected successfully.");

            // Prepare SQL query
            String sql = "INSERT INTO users (email, password) VALUES (?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            System.out.println("PreparedStatement created successfully with email: " + email + " and password: " + password);

            // Execute update
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
                if (conn != null) conn.close();
                System.out.println("Database resources closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Redirect to login page after successful registration
        response.sendRedirect("index.html");
    }
}
