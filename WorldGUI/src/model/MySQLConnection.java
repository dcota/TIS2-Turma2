package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MySQLConnection {
    //atributos
    private Properties p;
    private Connection connection;
    //construtor
    public MySQLConnection(){
        setConnection();
    }
    //método para fazer a ligação
    public void setConnection(){
        p = new Properties();
        try{
            InputStream input = new FileInputStream("dbconnect.properties");
            p.load(input);
            connection = DriverManager.getConnection
                    (p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));
            System.out.println("Ligado à BD.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            System.out.println("ocorreu um problema...");
        }
    }

    public ResultSet getCidades(){
        ResultSet result = null;
        String sql = "SELECT city.ID, city.Name, country.Name\n" +
                "FROM city, country\n" +
                "WHERE city.CountryCode = country.Code\n" +
                "ORDER BY country.name ASC";
        try{
            Statement s = connection.createStatement();
            result = s.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }



}
