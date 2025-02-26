package ru.javatar;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLDataException;
import java.sql.SQLException;

//import org.thymeleaf.context.Context;
//import org.thymeleaf.web.IWebApplication;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {

    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        DataBase db = new DataBase();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        String path = request.getPathInfo();
        DataBase db = new DataBase();
        try {
            String answer = db.connect(path);
            pw.println("title is " + answer);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            pw.println("<h1>not found</h1>");
            e.printStackTrace();
        } 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
