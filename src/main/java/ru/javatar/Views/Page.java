package ru.javatar.Views;

import org.thymeleaf.context.Context;
import org.thymeleaf.web.IWebApplication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Page {
    
    public static void main(String[] args){


    }
    public static void releasePage(ArrayList<String> array, IWebApplication app, HttpServletResponse resp) throws SQLException, IOException, ServletException{
        String url = array.get(1);
        String title = array.get(2);
        String template = array.get(3);
        Template temp_eng = new Template();
        Context context = new Context();
        context.setVariable("url", url);
        context.setVariable("title", title);
        context.setVariable("template", template);
        temp_eng.processEngine(resp, app, template, context);
    }

    public static void notFoundPage(ArrayList<String> array, IWebApplication app, HttpServletResponse resp)throws SQLException, IOException, ServletException{
        Template temp_eng = new Template();
        Context context = new Context();
        resp.setStatus(404);
        context.setVariable("title", "404 - Page not found");
        temp_eng.processEngine(resp, app, "404", context);
    }
}
