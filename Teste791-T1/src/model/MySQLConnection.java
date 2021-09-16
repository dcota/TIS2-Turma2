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

    public ResultSet getEncomendas(){
        ResultSet result = null;
        String sql = "SELECT orders.orderID, customers.CompanyName, DATE(orders.OrderDate), DATE(orders.ShippedDate)\n" +
                "FROM orders, customers\n" +
                "WHERE orders.CustomerID=customers.CustomerID\n" +
                "ORDER BY orders.OrderID";
        try{
            Statement stm = connection.createStatement();
            result = stm.executeQuery(sql);
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return result;
        }
    }

    public ResultSet getTotalEncomendas (){
        ResultSet result = null;
        String sql = "SELECT COUNT(*) FROM orders";
        try{
            Statement stm = connection.createStatement();
            result = stm.executeQuery(sql);
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return result;
        }
    }


    public ResultSet getMedia (){
        ResultSet result = null;
        String sql = "SELECT MyRound(AVG(UnitPrice*Quantity),2)\n" +
                "FROM orderdetails";
        try{
            Statement stm = connection.createStatement();
            result = stm.executeQuery(sql);
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return result;
        }
    }

    public ResultSet getDetalhe(int ID){
        ResultSet result = null;
        String sql = "SELECT orderdetails.OrderID, customers.CompanyName, MyRound(SUM(orderdetails.UnitPrice*orderdetails.Quantity),2)\n" +
                "FROM orderdetails, customers, orders\n" +
                "WHERE orderdetails.OrderID = orders.OrderID\n" +
                "AND orders.CustomerID=customers.CustomerID\n" +
                "AND orderdetails.OrderID = " + ID;
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
