package ru.javatar.Model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ru.javatar.Props;

public class DataBase{
    static String db_url       =   Props.get_prop("db.url"); 
    static String db_driver    =   Props.get_prop("db.driver");
    String user;
    String password;
    String sslStatus;

    public DataBase(String user, String password, String sslStatus){
        this.user = user;
        this.password = password;
        this.sslStatus = sslStatus;
        DataBase.loadDriver();
    }

    private static void loadDriver() {
        try{
            Class.forName(db_driver);
        }
        catch(ClassNotFoundException e){
            System.out.println(e);
        }
    }

    public Connection dbConnection() throws SQLException{
        System.out.println("DB connection OK!");
        return DriverManager.getConnection(db_url, this.user, this.password);
    }

    public String clearQuery(String str){
        return str.replaceAll("[\"'<>]|--", "")
        .trim();
    }

    public ArrayList<ArrayList<String>> dbQuery(String query){
        //query = clearQuery(query);
        ArrayList<ArrayList<String>> result_arr = new ArrayList<>();
        try{
            Connection conn = dbConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            int columns = result.getMetaData().getColumnCount();
            while(result.next()){
                ArrayList<String> row = new ArrayList<>();
                for(int i = 1; i <= columns; i++){
                    row.add(result.getString(i));
                }
                result_arr.add(row);
            }
            conn.close();
            return result_arr;
        }
        catch(SQLException e){
            System.out.println("DB connection err:" + e);
            return null;
        }
    }
}
