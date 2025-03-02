package ru.javatar;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.thymeleaf.web.IWebApplication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import ru.javatar.Model.DataBase;
import ru.javatar.Views.Page;

public class Router {


    public static void getPage(String url, IWebApplication app, HttpServletResponse resp) throws SQLException, IOException, ServletException{
        try{
            ArrayList<String> page_data = pageQuery(url).get(0);
            Page.releasePage(page_data, app, resp);
        }
        catch(Exception e){
            Page.notFoundPage(null, app, resp);
        }
        

    }

    public static ArrayList<ArrayList<String>> pageQuery(String url){
        DataBase db = new DataBase(
        Props.get_prop("db.user"), 
        Props.get_prop("db.password"), 
        "false");
        ArrayList<ArrayList<String>> arr_main = db.dbQuery("select * from pages where alias = '"+url+"';");
        return arr_main;
    }
}
