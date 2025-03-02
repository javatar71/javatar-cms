package ru.javatar;

import java.io.IOException;
import java.io.PrintWriter;
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


public class MainController extends HttpServlet {

    public static void main(String[] args){
        String url = "/";
        DataBase db = new DataBase(
            Props.get_prop("db.user"), 
            Props.get_prop("db.password"), 
        "false");
        ArrayList<ArrayList<String>> arr = db.dbQuery("select * from pages where alias = '"+url+"'");
        String result = arr.get(0).get(2);
        System.out.println(result);
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        String url = request.getPathInfo();
        if (url.endsWith(".txt")) {
            getServletContext().getNamedDispatcher("default").forward(request, response);
            return;
        }
        DataBase db = new DataBase(
            Props.get_prop("db.user"), 
            Props.get_prop("db.password"), 
        "false");
        db.dbQuery("SELECT * FROM pages");
        ArrayList<ArrayList<String>> arr = db.dbQuery("select * from pages where alias = '"+url+"';");
        String title = arr.get(0).get(2);
                Template template = new Template();
        IWebApplication application = JakartaServletWebApplication.buildApplication(getServletContext());
        Context context = new Context();
        context.setVariable("title", title);
        template.processEngine(response, application, "template", context);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
