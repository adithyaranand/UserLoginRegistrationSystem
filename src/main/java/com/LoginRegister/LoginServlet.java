package com.LoginRegister;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        boolean loginSuccessful = false;
        
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("JDBC Driver loaded successfully.");

            // Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UserLoginRegister1", "root", "1234567890");
            System.out.println("Database connected successfully.");

            // Prepare SQL query
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            System.out.println("PreparedStatement created successfully with email: " + email + " and password: " + password);

            // Execute query
            resultSet = statement.executeQuery();
            
            // Check if user exists
            if (resultSet.next()) {
                loginSuccessful = true;
                System.out.println("Login successful for user: " + email);
            } else {
                System.out.println("Login failed for user: " + email);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
                System.out.println("Database resources closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (loginSuccessful) {
            response.sendRedirect("home.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid email or password. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
            dispatcher.forward(request, response);
        }
    }
}
