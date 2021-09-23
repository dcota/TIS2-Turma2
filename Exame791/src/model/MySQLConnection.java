package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MySQLConnection {
    private Properties p;
    private Connection connection;

    public MySQLConnection(){
        setConnection();
    }

    public void setConnection(){
        p = new Properties();
        try{
            InputStream input = new FileInputStream("dbconfig.properties");
            p.load(input);
            connection = DriverManager.getConnection(
                    p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));
            System.out.println("LIGADO À BD");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("OCORREU UM ERRO");
        }
    }

    public ResultSet getClientes(){
        ResultSet result = null;
        String sql = "SELECT CustomerID, CompanyName, City, Country\n" +
                "FROM customers";
        try{
            Statement stm = connection.createStatement();
            result = stm.executeQuery(sql);
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return result;
        }
    }

    public ResultSet getTotalClientes (){
        ResultSet result = null;
        String sql = "SELECT COUNT(*) FROM customers";
        try{
            Statement stm = connection.createStatement();
            result = stm.executeQuery(sql);
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return result;
        }
    }


    public ResultSet getDetalhe(String ID){
        ResultSet result = null;
        String sql = "SELECT CustomerID, CompanyName, ContactName, Address, Phone\n" +
        "FROM customers\n" +
        "WHERE CustomerID = '" + ID + "'";
        try{
            Statement stm = connection.createStatement();
            result = stm.executeQuery(sql);
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return result;
        }
    }
}
