package ru.javatar;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import org.thymeleaf.web.IWebApplication;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import org.thymeleaf.context.Context;
import org.thymeleaf.web.IWebApplication;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ru.javatar.Model.DataBase;
import ru.javatar.Views.Template;


public class MainController extends HttpServlet {

    public static void main(String[] args){
        String url = "/' OR 1=1--";
        DataBase db = new DataBase(
            Props.get_prop("db.user"), 
            Props.get_prop("db.password"), 
        "false");
        url = db.clearQuery(url);
        ArrayList<ArrayList<String>> arr = db.dbQuery("select * from pages where alias = '"+url+"'");
        String result = arr.get(0).get(2);
        System.out.println(result);  
    }

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
