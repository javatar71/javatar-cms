package ru.javatar;

import java.io.IOException;
import java.sql.SQLException;

import org.thymeleaf.web.IWebApplication;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class MainController extends HttpServlet {
 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        if (url.endsWith(".txt")) {
            getServletContext().getNamedDispatcher("default").forward(request, response);
            return;
        }
        IWebApplication application = JakartaServletWebApplication.buildApplication(getServletContext());
        try{
            Router.getPage(url, application, response);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
