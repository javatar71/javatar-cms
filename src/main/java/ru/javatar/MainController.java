package ru.javatar;

import java.io.IOException;
import java.io.PrintWriter;
//import java.sql.SQLException;
//import java.util.ArrayList;

//import org.thymeleaf.context.Context;
//import org.thymeleaf.web.IWebApplication;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//import ru.javatar.Model.DataBase;

public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        pw.println("<h1>Test Page</h1>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
