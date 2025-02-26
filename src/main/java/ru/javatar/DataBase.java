package ru.javatar;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

public class DataBase{
    String db_url       =   "jdbc:postgresql://127.0.0.1:5432/db_test"; 
    String db_driver    =   "org.postgresql.Driver";


    public String connect(String url) throws SQLException, ClassNotFoundException  {
        Class.forName(db_driver);
        Properties props = new Properties();
        props.setProperty("user", "db_user");
        props.setProperty("password", "123654qq");
        props.setProperty("ssl", "false");
        Connection conn = DriverManager.getConnection(db_url, props);
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery("SELECT * FROM pages WHERE alias='" + url + "'");
        while(result.next()){
            System.out.println(result.getString("id"));
            System.out.println(result.getString("alias"));
            String title = result.getString("title");
            return title;
        }
        conn.close();
        return "Not found";
        
    }
}
