package com.ast.airlinesystem.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class DbConnection {

    Connection con;
    public static Statement stmt;

    public  Connection Connect (){
        String url = "jdbc:oracle:thin:@//localhost:1521/XE";
        String user = "system";
        String password = "root";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver loaded Successfully");
            con= DriverManager.getConnection(url,user,password);
            //stmt=con.createStatement();
            System.out.println("Connection is successful");
        }
        catch(SQLException e){
            System.out.println("Connection failed");
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex){
        }
        return con;
    }
   /*public static Statement getStatement() {
        return stmt;
    }


    public Connection getConnection(){
        return con;
    }

    public void disconnect(){
        con=null;
        if(con == null) System.out.println("Connection ended");
    }*/
}

